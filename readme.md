# Policy Kafka test

This program will test the functionality of kafka to integrate three microservices: Policy generator, policy booking and log management usinga mongo data engine

## Installation

To setup the needed enviroment you need to install the following applications:
 * [Kafka](https://kafka.apache.org/downloads) latest version
 * [Mongo](https://www.mongodb.com/try/download/community) community edition
 * [Intellj](https://www.jetbrains.com/es-es/idea/) community edition

* [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=es) Chrome extension

For kafka installation you can use this [guide](https://dzone.com/articles/running-apache-kafka-on-windows-os) and create these three topics using this commands in a windows machine: 

```bash
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic logTopic

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic bookCallbackTopic

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic bookedTopic

```

For mongo you will need to create two database called  logdb and policydb

## Usage

Please start mongo database and kafka server before run the microservices. 

Once you ran the microservices the following endpoints will be available:


```bash
POST: http://localhost:8080/policy-kafka/newSubmission   Creates a new policy
{
	"policyNumber" : "12345678"
}

GET: http://localhost:8080/policy-kafka/ Fetches all the created policies

GET: http://localhost:8081/log-kafka/ Fetches all the log entries

GET: http://localhost:8080/policy-kafka/book/123456 Books an existant policy 

```