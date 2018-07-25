# Portfolio Weight Calculator

This is the implementation of the Calculator that can calculate a fund weight within the 
portfolio. The flattened fund structure and market values are provided as the input. 
The application calculates the fund weight for all base (leaf nodes) funds within the portfolio.

## Prerequisites

    - Java version: 1.8.0_162-b12
    - Maven version: Apache Maven 3.5.3
JDK and Maven of the above specified versions (or similar).

## Getting Started

Application Installation
Copy portfolio.zip into the executing folder and unpack it.

Open the cmd console, then go to /portfolio folder. 
Run: '**mvn clean install**' to compile the application in \portfolio folder
Run: **..\portfolio\target>java -jar portfolio-1.0-SNAPSHOT.jar** to start the application  

Then the below text appears in the console:

    Start Portfolio Calculator.
    Enter the full path to Portfolio File for calculation: >

The Portfolio Weight Calculator is ready to operate. 
Now enter the absolute path to the input file.

# Next steps

Enter the absolute path in the command line as follows: 

    Enter the full path to Portfolio File for calculation: 
    >C:\portfolio\src\main\resources\portfolio_1.txt

Click Enter. If the input file is consistent and there are no errors,
 you will see the following result:

    Calculation has started....
    All done, here are the results:
    A,D,0.167
    A,E,0.083
    A,F,0.083
    A,G,0.333
    A,H,0.333
    
    Successfully calculated. See the results in output file: 
    C:\imaltsev\git\portfolio\src\main\resources\portfolio_1.output
    Another calculation? Enter the full path to Portfolio File or EXIT: >

The resulting file will have the ***.output** extension and will be created 
in the same folder as the input file. You can enter a path to another input file 
to calculate again.
    
Use **exit** to stop the Calculator.

## Constraints:

The input file should hold valid data which presents **the tree structure** (not a graph).
The parent value should be equal to the sum of its child values.
Based on the specified requirements of the task, the precision of calculation 
is limited to 3 (three) digits; no greater precision has been requested,
therefore, it was not implemented, this part can be regarded as a TODO task.

## How to work with the application

Enter the absolute path to input file, e.g. for Windows: 
    C:\portfolio\src\main\resources\portfolio_1.txt

The input file must have format like:
    
    A,B,1000
    A,C,2000
    B,D,500
    B,E,250
    B,F,250
    C,G,1000
    C,H,1000

## Running the tests

To run the tests only, run '**mvn test**'in \robot folder 
The JUnit tests cover the following basic functions:
- calls for the main method to check if the application can be launched
- checks if the application exits correctly
- checks the correct path to an input file
- checks if the output file has been created correctly
- validates the node tree
- checks calculations

## Author

I. Maltsev

