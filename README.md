# Spring Boot Microservices
This project provide an example how to create microservices project using Spring Boot and Spring Cloud.

## Eureka Discover Service
Serivce name **photoappdiscoveryservice**

Service url http://localhost:8010/

The service will define and discover all microservices in the system

## Zuul Gateway Service

Service name **photoappapizuulapigateway**

The service will work with clients request, will path through Security level. This service will work Discover service and then with particular microservice. 

Also it will works as load balancer if multiple microservice of the same tupe is registered in Discovery service.

## Spring Clound Configuration Service

Service name **photoappapiconfigserver**

The service will work external git repository to provide global properties for all microservices. Properties from the service have high priority over local microservice properties. To deifne properties for particular microservice use next pattern `<service-name>.yml` or `<service-name>.properties`, to define properties for particular profile use the next schema `<service-name>-<profile-name>.yml` or `<service-name>-<profile-name>.properties`

The service also will work as Spring Config Bus, to provide properties at runtime. Based on AMQP (RabbitMQ)

To update configuration from Cloud Configuration using Spring Config Bus use next url http://localhost:8012/actuator/bus-refresh

Additional details on updating configuration:

You need push chahges to you git repository and then execute POST request to http://localhost:8012/actuator/bus-refresh

For updating configuration git repository url update this property spring:
**spring.cloud.config.server.git.uri** and if this is required **spring.cloud.config.server.git.username** and **spring.cloud.config.server.git.password** in **application.yml**.

Please note, that instance of RabbitMQ is required to work with Spring Config Bus, update application.yml if this is required to match your server configuation.

## Users Service

Service name **photoappapiusers**

Users service that has possibility to register and login into the system

## Account Management Service

Service name **photoappapiaccountmanagement**
## How to run

You need to follow the next steps:

1. Run RabbitMQ server
2. run Eureka Discover Service
3. run Spring Clound Configuration Service
4. run Zuul Gateway Service
5. run other serives

## How to use

1. Check if services are registered in Eureka Discovery Service (http://localhost:8010/). Please note, Spring Clound Configuration service will not be present in EDS

### Users API

#### Register new user

POST http://localhost:8011/users-ws/api/users

```json
{
	"firstName": "name",
	"lastName": "name",
	"password": "password",
	"email": "test3@mail.com"
}
```

#### Login

POST http://localhost:8011/users-ws/api/users/login

```json
{
	"password": "password",
	"email": "test3@mail.com"
}
```

After successful login find **token** header and use this token as Basic authorization for next operations `Baere <token>`

