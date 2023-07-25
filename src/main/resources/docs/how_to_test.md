## Testing using postman

* Postman scripts were uploaded under resources/postman/MoviesApplication.postman_collection.
* /assignment/v1/movies/authenticate API takes username and password as parameters and will return a JWT in response.
* With every REST API this JWT should be passed in Authorization header as Bearer+space+JWT.

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