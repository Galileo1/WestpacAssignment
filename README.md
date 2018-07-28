[![Build Status](https://travis-ci.org/Galileo1/WestpacAssignment.svg?branch=first_branch)](https://travis-ci.org/Galileo1/WestpacAssignment)

# WestpacAssignment


Selenium, Cucumber, Spring Boot and TestNG based UI Test Automation framework 

Quick and Lean test automation framework that uses BDD framework Cucumber to describe the behavior of the application,
Selenium as engine to drive the UI interactions, TestNG as test framework and runner, Spring Boot to hand dependency 
injections and manage objects and uses extent-cucumber for reports and logging.

## Development and Test Environment 

```
Maven: 3.5.4
Java version: 9.0.4
OS name: "mac os x", version: "10.13.5", arch: "x86_64"
OS name: "windows", version: "10", arch: "x86_64"
```
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

## Test Configuration 

```
Test are configured to run either on MacOS or Windows platform. This has been tested as well.
To change the browser: 

Please change property "app.browser" in the "application.properties" file under "src.main.resources"

It can run on Selenium Grid as well using RemoteWebDriver.To do that -
Please change property "app.browser" to "remote" in the "application.properties" file under "src.main.resources"
and change the url to that of grid.

Note : Firefox on windows doesn't seem to work. But firefox on MacOS works like a charm.
```

## Extent Reports 

![Extent Cucumber Reports](https://i.imgur.com/QMPXNF5.png)'

## Test Run 

![Screencast](https://i.imgur.com/vyREbRY.gif)
