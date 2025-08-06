# Optimizing Cell-Based Software Architecture through Heuristic Community Detection Approach

This repository contains data for the research paper titled *"Optimizing Cell-Based Software Architecture through Heuristic Community Detection Approach"* by Miloš Milić, Nebojša Nikolić, and Dragana Makajić-Nikolić. The repository includes the following artefacts:

- [`code`](code) – Contains the source code of the developed software system related to a manufacturing domain. [Docker Engine](https://www.docker.com) must be installed to build and run the container.
- [`simulation_test_plan`](simulation_test_plan) – Includes the file `community_detection_test_plan.jmx` and accompanying CSV files with mock data. [Apache JMeter](https://jmeter.apache.org) must be installed to execute the simulation test plan.
- [`simulation_results`](simulation_results) – Contains the file `results.csv` with simulation results.
- [`optimization_input_data`](optimization_input_data) – Provides various input data used for optimization.
- [`optimization_results`](optimization_results) – Includes the results of the optimization process.
- [`heuristic`](heuristic) – Contains the source code for the developed heuristic. Python must be installed to run the heuristic.

### Steps for running the software system:

1. Clone the repository:  
   ```bash
   git clone https://github.com/milicm/optimizing_cell_based_software_architecture_community_detection_test.git

2. Navigate to the `code` folder:  
   ```bash
   cd code

3. Run the following command:  
   ```bash
   docker-compose up --build
