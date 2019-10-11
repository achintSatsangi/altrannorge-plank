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

Bundling and deploying node modules takes a lot of time. While developing one might feel that they just need to execute 
the backend and not worry about the frontend build much like while making db changes using liquibase, executing tests or 
changing REST endpoints. To allow for that we have added a new maven profile which simply compiles the backend and 
execute its tests to use the same add `-Pbackend` to your commands. For e.g.

```
mvn clean install -Pbackend
mvn spring-boot:run -Pbackend
```

### Setup TEST environment

Since the application has evolved and has multiple database entities and relationships, the need has arisen to have a 
persistent test environment where developers can deploy there local changes and play around. Follow the instructions 
below to be able to access the test environment

1. Setup your account on Heroku and make sure you have access to TEST instance. (Ask Admin to give it to your heroku user)

2. Install Heroku CLI on your development machine. You can follow the steps mentioned on: 

https://devcenter.heroku.com/articles/heroku-cli.

3. Run below command to attach to the TEST instance:

```
heroku git:remote -a altrannorge-plank-test
```

4. Run `git remote -v` to verify that you are connected properly. you will see output like below:

```
heroku	https://git.heroku.com/altrannorge-plank-test.git (fetch)
heroku	https://git.heroku.com/altrannorge-plank-test.git (push)
origin	git@github.com:achintSatsangi/altrannorge-plank.git (fetch)
origin	git@github.com:achintSatsangi/altrannorge-plank.git (push)
```

5. To deploy to the test instance, commit your changes to git branch and run:

```
git push heroku <<BRANCH_NAME>>:master
```

6. Everytime we merge to master the master branch is deployed to TEST and PRODUCTION both.