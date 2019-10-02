# Plank Progress application

This is a basic web application we will use to track plank progress in ALtran Norge offices

## Things to remember

- We never check in to the master branch
- Every change should be made in its own branch
- Create pull requests and share on ALT-LAB teams channel for review
- Try and follow TDD and clean code policies as much as possible :) :)

## Pre-requisites
- Open JDK 12 (Upgraded to use the latest java date time API)
- Maven
- Node.js (npm)
- A good internet connection :)
- Postgres installation (You should have psql command working to be able to create DB using the init-db script), The 
shell script is for linux or mac environments, similar bat script for windows can be written easily

## Unit & Integration test environment
You don't need a working copy of the Postgres DB to be able to build the app as it uses an H2 in memory database for 
executing the tests. 

## Useful commands
For application build and test execution
```
mvn clean install
```

## For running the application

- Maven Spring boot starter plugin (Recommended... it supports remote debugging on port 5005)

```
mvn spring-boot:run
```

### Backend Only

Bundling and deploying node modules takes a lot of time. While developing one might feel that they just need execute the 
backend and not worry about the frontend build much like while making db changes using liquibase, executing tests or 
changing REST endpoints. To allow for that we have added a new maven profile which simply compiles the backend and 
execute its tests to use the same add `-Pbackend` to your commands. For e.g.

```
mvn clean install -Pbackend
mvn spring-boot:run -Pbackend
```   