This document is a short guideline to get the appropriate resources for all of parts of the challenge.

General instruct
------------

- PART 1: Test Design and Bug challenges

Please refer the document in this package.

- Part 2: Automation

This repository includes UI and API automation test cases of the challenge. 


Instruction
------------
Getting Started
These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites
- Java version: 1.8
- Platform: Windows, Mac
- Support browser:
     + Chrome: version 88
     + Firefox: version 86


### How to execution


1. Open TestRunnerClass.java. Location: `TrialSeleniumFW/src/test/java/Runners/TestRunnerClass.java`
     + Parallel  : @DataProvider(parallel = `true`)
     + Sequential: @DataProvider(parallel = `false`)

2. Delegated feature file: Set features in nested `@CucumberOptions`

For example:
- Run delegated UI feature file: `features = {"src/test/resources/GUITestCases.feature"}`
- Run delegated UI feature file. `features = {"src/test/resources/APITestCases.feature"}`

3. Nominate browser: Change the browser set in global.properties file
Location: `TrialSeleniumFW/src/main/java/Properties/global.properties`

It could be set into CHROME, chrome, FIREFOX, firefox

### How to get report file

As the current designed framework, each report file contains one scenario. It supports adding a test log in each test run in the Test Management tool.
Report folder: `TrialSeleniumFW/src/main/java/Result`


### Structure of Source code
Feature file -> Step definition -> Page Object -> Selenium Wrapper

1. Feature file: it contains all scenarios of feature in Gherkin steps. Location: `TrialSeleniumFW/src/test/resources`

2. Step Definition: it contains all methods defined under Gherkin steps. Location: `TrialSeleniumFW/src/test/java/StepDefinitions`

3. Page Object: located all By locators at the top of page and all methods interacted with them. It extends from Selenium Wrapper. Location: `TrialSeleniumFW/src/main/java/Pages`

4. Selenium Wrapper: wrap up all selenium actions with Try - catch and Report which log info. It is necessary when debugging.








