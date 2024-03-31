# Welcome to Koerber Pharma Backend Challenge
Koerber Pharma | Backend Developement :: Challenge

GitHub Repository Information API

In this repository you have two projects, the first for data ingestion and the second for consumption and access of that same data.
Korber 
Korber-Consumer[API]

## Features

- Failover system
- Sistributed System
- Ingestion of large amounts of data
- REST endpoint to get the data from the database for based on a price range.


![image](https://github.com/AndreAlvesFreitasEngineer/korberChalange/assets/165584356/9ab16913-0fe5-421e-b670-23ecba123977)

Database:

![image](https://github.com/AndreAlvesFreitasEngineer/korberChalange/assets/165584356/2bed4f38-c53f-46d5-a0dd-c19ef46ccdb4)


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them:

- JDK 17
- Maven
- Git

### Installing

A step-by-step series of examples that tell you how to get a development environment running:

Clone the repository:

```bash
git clone https://github.com/drealves/tui-challenge-api.git
cd tui-challenge-api.git
```
Install dependencies:
```bash
mvn install
```
Run the application:
```bash
mvn spring-boot:run
```
The service should now be running on http://localhost:8080

Example

Fetching repository information for user drealves:
```bash
http://localhost:8080/api/v1/github/repositories/drealves?page=1&size=5
```
Running the Tests
```bash
mvn test
```

Built With

    Spring Boot - The framework used
    Maven - Dependency Management
    Reactor - For building reactive applications
    WebFlux - async no-bloking system


Authors

    André Freitas - drealves

Acknowledgments

    2 hours analyze
    6 hours to test
    7 hours coding
    1 hour to comment
    Dev total: 16 Hours


API doc 

    http://localhost:8080/swagger-ui/index.html

Jenkis File in Repository

Docker File in Repository

Fargate file and api-gateway file are not designed for evaluation

LoadBalancer URL: 

    load-repo-api-225205433.eu-north-1.elb.amazonaws.com

Swegger:

    http://load-repo-api-225205433.eu-north-1.elb.amazonaws.com:8080/swagger-ui/index.html

Example of request:
    
    GET http://load-repo-api-225205433.eu-north-1.elb.amazonaws.com:8080/api/v1/github/users/drealves/repositories?page=1&size=5

Details:
I didn't create the environment in ECS using cloudFormation, but I configured two EC2 instances, one with Jenkis and the other as a docker container. If the services are not up when they evaluate it is possible that they are down, but in the interview I will demonstrate the devop Build that I created. See you soon Any doubts, please let us know

