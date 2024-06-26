# Welcome to CustomFeederApp

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

### Installing and Run

A step-by-step series of examples that tell you how to get a development environment running:

Clone the repository:

```bash
git clone https://github.com/AndreAlvesFreitasEngineer/korberChalange.git
cd korberChalange
```
Install dependencies:
```bash
docker-compose up -d
```
all containers must be running, kafka, zookeper and the database and all dependencies.

Run the application:
```bash
cd korber-consumer
java -jar target/korber-consumer-0.0.1-SNAPSHOT.jar
```
```bash
cd korber
java -jar target/korber-0.0.1-SNAPSHOT.jar
```
```bash
The service korber should now be running on http://localhost:8081
```

and 

```bash
The service korber-consumer should now be running on http://localhost:8080
```

Example

Fetching the data from the database for based on a price range.:
```bash
http://localhost:8080/v1/api/rides?minPrice=10&maxPrice=20&page=0&size=10
```
Running the Tests
```bash
cd korber-consumer
mvn test
```
Authors

    André Freitas 

API doc 

    http://localhost:8080/swagger-ui/index.html

