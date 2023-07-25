## Scaling of Application

Scaling an application from handling 100 users per day to 10,000,000 users per day with the same quality of service can be a challenging task as number of users grow the traffic will be increased and system needs to handle the load and process the data. By implementing few of the below strategies we can be successfully achieve this:

#### Load Balancers:

* By implementing load balancing to distribute the incoming traffic across multiple instances of our application.

#### Command Query Responsibility segregation (CQRS):

* CQRS separates the read and write operations from the data source.
* With the help of CQRS, better performance and scalability can be achieved by evenly distributing the workload between read and write operations.

#### Caching mechanism

* By caching the master data or frequently accessed data will reduce the subsequent hits for API and increases performance

#### Async calls

* All the process or tasks which takes more time or independent of each other can be made async calls which results in reduction of execution time.

#### Autoscaling

* Autoscaling helps in increasing the number of instances based on the load and configured threshold.

#### By opting for cloud providers

* Switching to cloud providers like Azure, AWS, Redhat Openshift will support features like Autoscaling, High availability, Monitoring and Fail-over & redundancy also helps to manage the load.
