# WestpacAssignment


Selenium, Cucumber, Spring Boot and TestNG based UI Test Automation framework 

Quick and Lean test automation framework that uses BDD framework Cucumber to describe the behavior of the application,
Selenium as engine to drive the UI interactions, TestNG as test framework and runner, Spring Boot to hand dependency 
injections and manage objects and uses extent-cucumber for reports and logging.

## Development and Test Environment 

Maven: 3.5.4
Java version: 9.0.4
OS name: "mac os x", version: "10.13.5", arch: "x86_64"
OS name: "windows", version: "10", arch: "x86_64"

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
Java 8
Maven 3.4.5
Git cli 
Chromedriver version 2.40
ChromeBrowser version > 66.0 (lastest is preferable)
```

### Installing

A step by step series of examples that tell you how to get a development env running

Clone the git repository 

```
git clone https://github.com/Galileo1/WestpacAssignment.git
```
Change directory into WestpacAssignment folder

```
cd WestpacAssignment
```
Build and run tests 

```
mvn clean install
```

## Project Structure 

```
- org.galileo1.bdd.datamodel -> contains class as data structure
- org.galileo1.bdd.driver -> contain custom driver configuration 
- org.galileo1.bdd.feature -> contains cucumber feature files
- org.galileo1.bdd.pageobj -> contains page object classes
- org.galileo1.bdd.stepdefs -> contains step definitions
```

## Extent Reports 

![Extent Cucumber Reports](https://i.imgur.com/QMPXNF5.png)

![Screncast](https://i.imgur.com/oX5nn1n.mp4)
