import re
import itertools
from itertools import combinations
import time
import random
import concurrent.futures


def parse_dat_file(filename):
    """ Parses the .dat file and extracts vertices, functionalities, edges, weights b, and weights d. """
    with open(filename, 'r') as file:
        data = file.read()

    # Extract set V (Vertices)
    vertices = re.findall(r'set V:=\n(.*?)\n;', data, re.DOTALL)[0].split()

    # Extract functionalities
    functionalities = re.findall(r'set FC:=\n(.*?)\n;', data, re.DOTALL)[0].split()

    # Extract edges
    edges = re.findall(r'set E:=\n(.*?)\n;', data, re.DOTALL)[0].split('\n')
    edges = [tuple(e.strip('()').split(',')) for e in edges]

    # Extract edge weights (b)
    b_values = re.findall(r'param b:=\n(.*?)\n;', data, re.DOTALL)[0].split('\n')
    b = {tuple(k.strip('[]').split(',')): float(v) for k, v in (line.split() for line in b_values)}

    # Extract vertex weights (d)
    d_values = re.findall(r'param d:=\n(.*?)\n;', data, re.DOTALL)[0].split('\n')
    d = {line.split()[0]: float(line.split()[1]) for line in d_values}

    # Extract functionality vertices (C) for all functionalities
    functionalities_dict = {
        func: re.findall(rf'set F\[{func}\]:=\n(.*?)\n;', data, re.DOTALL)[0].split()
        for func in functionalities
    }

    return vertices, functionalities_dict, edges, b, d


def calculate_L(b):
    return sum(b.values())


def calculate_Q(L, clusters, edges, b, d):
    edge_set = set(edges)

    total_b = 0
    total_sum_d = 0

    # Iterate over each cluster
    for cluster in clusters:
        cluster_elements = list(cluster)

        # Calculate total_b for the cluster using combinations
        cluster_b = 0
        for e1, e2 in itertools.combinations(cluster_elements, 2):
            if (e1, e2) in b:
                cluster_b += b[(e1, e2)]
            if (e2, e1) in b:
                cluster_b += b[(e2, e1)]

        total_b += cluster_b

        # Calculate total_sum_d for the cluster
        cluster_d = 0
        for e in cluster_elements:
            cluster_d += d[e]

        total_sum_d += cluster_d * cluster_d
    return 4 * L * total_b - total_sum_d


def greedy_community_detection(L, clusters, edges, b, d):
    Q_total = calculate_Q(L, clusters, edges, b, d)  # Compute total modularity for initial state

    while True:
        best_Q = Q_total
        best_merge = None

        for cluster1, cluster2 in combinations(clusters, 2):
            new_clusters = [c for c in clusters if c != cluster1 and c != cluster2] + [cluster1 | cluster2]
            Q_new = calculate_Q(L, new_clusters, edges, b, d)
            if Q_new > best_Q:
                best_Q = Q_new
                best_merge = (cluster1, cluster2)

        if best_merge:
            clusters = [c for c in clusters if c not in best_merge] + [best_merge[0] | best_merge[1]]
            Q_total = best_Q
        else:
            break

    return clusters, Q_total


def destroy_and_repair(functionality_name, vertices, C, edges, b, d, retrys):
    L = calculate_L(b)

    clusters = [{v} for v in C]
    Q = 0

    community_clusters, Q = greedy_community_detection(L, clusters, edges, b, d)

    counter = 0
    while counter < retrys:
        counter += 1
        Qm_values = {
            tuple(cluster): calculate_Q(L, [cluster], edges, b, d)
            for cluster in community_clusters if len(cluster) > 1
        }

        valid_clusters = {c: Qm for c, Qm in Qm_values.items() if Qm > 0}

        if len(valid_clusters) < 2:
            break  # Stop if fewer than 2 valid clusters

        random_clusters = random.sample(list(valid_clusters), 2)

        random_clusters = [set(cluster) for cluster in random_clusters]

        community_clusters_new = [c for c in community_clusters if c not in random_clusters]
        for cluster in random_clusters:
            community_clusters_new.extend([{v} for v in cluster])  # Convert elements into singletons

        refined_clusters, refined_Q = greedy_community_detection(L, community_clusters_new, edges, b, d)
        if refined_Q > Q:
            community_clusters, Q = refined_clusters, refined_Q
            counter = 0

    return community_clusters, Q


def run_destroy_and_repair(functionality_name, vertices, functionalities_dict, edges, b, d, retrys):
    """ Runs destroy_and_repair for a given functionality, filtering C dynamically. """
    C = functionalities_dict.get(functionality_name, [])
    if not C:
        return functionality_name, ([], 0)

    return functionality_name, destroy_and_repair(functionality_name, vertices, C, edges, b, d, retrys)


def parallel_destroy_and_repair(filename, functionalities, retrys):
    """ Runs destroy_and_repair in parallel for multiple functionalities and aggregates results. """

    # Parse input data file
    vertices, functionalities_dict, edges, b, d = parse_dat_file(filename)

    # Start timing
    start_time = time.time()

    # Run local_search in parallel
    results = []
    with concurrent.futures.ProcessPoolExecutor() as executor:
        future_to_func = {
            executor.submit(run_destroy_and_repair, func, vertices, functionalities_dict, edges, b, d, retrys): func
            for func in functionalities
        }
        for future in concurrent.futures.as_completed(future_to_func):
            func_name = future_to_func[future]
            try:
                func_name, (clusters, Q) = future.result()
                results.append((func_name, clusters, Q))
            except Exception as e:
                print(f'Error in {func_name}: {e}')

    # Aggregate results
    total_Q = sum(Q for _, _, Q in results)
    all_clusters = [clusters for _, clusters, _ in results]

    # End timing
    elapsed_time = time.time() - start_time

    # Print results
    print('\nFinal Results:')
    for func_name, clusters, Q in results:
        print(f'Functionality: {func_name}, Q = {Q}, Clusters = {clusters}')

    print(f'\nTotal Q = {total_Q}')
    print(f'Execution Time: {elapsed_time:.4f} seconds')

    return all_clusters, total_Q


if __name__ == '__main__':
    filename = '../optimization_input_data/data30-01.dat'
    functionalities = ['f1', 'f2', 'f3']  # List of functionalities
    retrys = 10

    all_clusters, total_Q = parallel_destroy_and_repair(filename, functionalities, retrys)
