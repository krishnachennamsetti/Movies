# Movies

Movies is a microservice application with REST APIs exposed to  below functionalities:
1) Checking whether a movie won Best Picture oscar or not.
2) Allows users to give rating for a specific movie.
3) Fetch the Top 10 rated movies based on their Box Office performance

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

## API Endpoint Details

Method.      | Endpoint Details                   | Remarks                                                                                            | 
------------ |------------------------------------|----------------------------------------------------------------------------------------------------|
GET          | /assignment/v1/movies/best-picture | Returns whether a movie won best picture oscar or not based on given title.                        | 
PATCH        | /assignment/v1/movies/ratings      | Updates user rating for a movie based on given title and rating value and returns status code 204. | 
GET          | /assignment/v1/movies/top-rated    | Fetched top 10 rated movies ordered by box office collections in Descending order.                 | 