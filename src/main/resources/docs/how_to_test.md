## Testing using postman







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