# Movies

Movies is a microservice application with REST APIs exposed to  below functionalities:
1) Checking whether a movie won Best Picture oscar or not.
2) Allows users to give rating for a specific movie.
3) Fetch the Top 10 rated movies based on their Box Office performance

## Architecture Overview

This is an application built using microservices architecture with Java, Springboot, REST web services, and Mongo DB. Below is the architectural system design UML diagram explaining the end-to-end flow.

<img width="908" alt="solution_diagarm" src="https://github.com/krishnachennamsetti/Movies/assets/39440188/418f8fd2-0ecf-46fa-9455-b4718314f2ea">


## Tech stack and plugins used for Development


- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Springboot version 3.1.0](https://spring.io/projects/spring-boot)
- [Apache Maven](https://maven.apache.org)
- [MongoDB](https://www.mongodb.com)
- [REST webservices](https://spring.io/guides/gs/rest-service/)
- [JWT](https://jwt.io/)
- [JACOCO Plugin](https://www.eclemma.org/jacoco/)
- [Springdoc OpenAPI](https://swagger.io/specification/)

## API Layer and Authentication Mechanism

* For API authentication we are using Json Web Token (JWT).
* We are accepting username and password from the exposed authentication API and generate JWT based on credentials stored in Database.
* Generated JWT will be passed in Authorization header as Bearer + JWT, and will be validated against the user for each request and is mandatory.
* The JWT is valid for 30 minutes and the value is configurable.

## Business Logic and Persistence

* All the business logic is handled at the service layer of the Application
* MongoDB is used for persisting and retrieving the data from Database

## API documentation
* OpenAPI-3 is used for the documentation of the APIs.
* Once the Application is started the API documentation can be accessed using the below localhost URL
- [Swagger Documentation](http://localhost:8080/assignment/swagger-ui/index.html#/)
* Swagger documentation view appears as below

<img width="1679" alt="Screenshot 2023-07-25 at 10 19 53 PM" src="https://github.com/krishnachennamsetti/Movies/assets/39440188/5b37e48e-2a88-4e7d-bfc8-ae66f3451bea">


## API Endpoint Details

| Method. | Endpoint Details                   | Remarks                                                                                            | 
|---------|------------------------------------|----------------------------------------------------------------------------------------------------|
| GET     | /assignment/v1/movies/best-picture | Returns whether a movie won best picture oscar or not based on given title.                        | 
| PATCH   | /assignment/v1/movies/ratings      | Updates user rating for a movie based on given title and rating value and returns status code 204. | 
|  GET    | /assignment/v1/movies/top-rated    | Fetched top 10 rated movies ordered by box office collections in Descending order.                 | 

## For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Apache Maven](https://maven.apache.org)
- [Mongo DB](https://www.mongodb.com)

## Running the application in local environment

* Clone the code from GIT using below command
```shell
git clone https://github.com/krishnachennamsetti/Movies.git
```
* Download all the dependencies , run tests and build the project using the below command
```shell
mvn clean install
```
* The jar file name Movies-1.0.jar will be created under the target folder
* Local mongoDB is needed to run the application in local, you can use the default localhost mongo URI connection string as below to connect to the local mongo DB, i have created a database named RecipesDB for my local environment.
```shell
mongodb://localhost:27017/MoviesDB
```
* Refer below link for mongo installation and setup in local
- [Mongo DB Local setup](https://www.prisma.io/dataguide/mongodb/setting-up-a-local-mongodb-database#:~:text=Open%20up%20MongoDB%20Compass%20to%20begin.&text=If%20you%20click%20Connect%20without,MongoDB%20server%20you%20are%20running.)
* You can run the application as a java application by running the main method of class RecipesApplication.java
* Alternatively you can use below command to run the application
```shell
mvn spring-boot:run
```
* The application will be started on default port 8080

## Deploying the application to Production

* The jar file name Movies-1.0.jar will be created under the target folder
* Below mongo DB configuration needs to be updated in Production environment
```shell
mongbdb://username:password@hostname:portname/dbname
```
* All the secrets and sensitive keys can be stored in vault.
* JWT secret will be stored as .key file.
* Cloud containarization frameworks can be used to deploy the Application

## Test cases execution and code coverage

* Test case were available for all the business logic with 100 % code coverage.
* You can execute the junit test cases with below command
```shell
mvn test
```
* Jacoco plugin is used to calculate the coverage and by executing below maven command the code coverage files will be generated under the Application path /target/jacoco-report/index.html to view the coverage in web and /target/jacoco-report/jacoco.csv as a .csv file
```shell
mvn clean install
```
* sample Jacoco code coverage appears as below

<img width="1326" alt="Screenshot 2023-07-25 at 11 25 34 PM" src="https://github.com/krishnachennamsetti/Movies/assets/39440188/8ec4858d-6a95-4095-b32b-89d511444ce0">


## Future Enhancements

* API authentication can be replaced with Oauth2 by using client id, client secret and Authorization server for generating the token.
* Caching can be implemented for retrieving the master data.
* Indexes can be applied to database fields for faster retrieval.
* Creating dedicated APIs for user registration and  managing movies related data.
* Sensitive information and keys can be stored in vault.
* JwtFilter can be replaced by Spring security to make full use of the authentication and security mechanism.
* CICD pipeline can be configured and deploy with Containerization frameworks in cloud.

## Open for suggestions

Any Improvements and suggestions are always welcome you can reach me at my Email : Krishna.chennamsetti@gmail.com
