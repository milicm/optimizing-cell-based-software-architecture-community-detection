## Mathematical Model

Contains the `GLPK` `.mod` file for the developed mathematical model. [GLPK – GNU Linear Programming Kit](https://www.gnu.org/software/glpk) must be installed to run the model.

### Instructions for running the model:

Prerequisites: GLPK – GNU Linear Programming Kit is installed.

1. Clone the repository:  
   ```bash
   git clone https://github.com/milicm/optimizing-cell-based-software-architecture-community-detection.git

2. Navigate to the `mathematical_model` folder:  
   ```bash
   cd mathematical_model

3. Run the `glpsol` command with three arguments (i.e., mod, dat, and output files):  
   ```bash
   glpsol -m comsisMMNNDMN.mod -d ../input_data/data_30vertices_test/data30-10_aggregated.dat -o solution_output.txt
