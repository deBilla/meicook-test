# MeiCook Test

Use Java 17 run this application.

`./gradlew clean build`

`./gradlew test`

## Database Setup
This uses a MySQL version 8 and if you have install it in the local environment
create a database named `bank` and update the `application.proprties` file to 
with the db credentials of your local environment.

## Running the full application using Docker-Compose

Install docker-compose in your local environment and run the following command in the root folder of this project. 
This will create the database automatically in a docker container.

`docker-compose up`

# API Documentation

First run the `create-account` and then use the returned uuid for the next end points.
Documentation can be found in following URL.

https://documenter.getpostman.com/view/3298451/VUjPGQUd