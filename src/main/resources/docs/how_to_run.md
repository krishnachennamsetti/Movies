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

* The jar file name FavouriteRecipes-1.0.jar will be created under the target folder
* Below mongo DB configuration needs to be updated in Production environment
```shell
mongbdb://username:password@hostname:portname/dbname
```
* All the secrets and sensitive keys can be stored in vault.
* JWT secret will be stored as .key file.
* Cloud containarization frameworks can be used to deploy the Application
