[![GitHub license](https://img.shields.io/github/license/cneinert/expenseTracker.svg)](https://github.com/cneinert/expenseTracker/blob/main/LICENSE.md)
[![GitHub release](https://img.shields.io/github/release/cneinert/expenseTracker.svg)](https://GitHub.com/cneinert/expenseTracker/releases/)
![example workflow](https://github.com/cneinert/expenseTracker/actions/workflows/maven.yml/badge.svg)

# Expense-Tracker

A Java Application to track your personal spending.


## Installation

[Java 11](https://jdk.java.net/java-se-ri/11) or higher is required for the Application to run.  
[Download](https://www.oracle.com/java/technologies/downloads/) and install the latest Java Version.

The Application is bundled as a standalone jar file containing all dependencies.  
[Download](https://github.com/CNeinert/expenseTracker/releases) the newest Version from the Releases Tab.

## Run
Just double click the jar file. 
The application creates a `data.db` file which holds all of your data entries. It is a simple SQLite Database file.
The Database can also be opened with the [DB Browser for SQLite](https://sqlitebrowser.org/).

If double clicking **does not** work, open the jar File via the terminal: 
```
java -jar shade/expenseTracker.jar
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[GPL-v3.0](https://choosealicense.com/licenses/gpl-3.0/)
