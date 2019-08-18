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

## Spring Cloud Configuration Service

Service name **photoappapiconfigserver**

The service will work external git repository to provide global properties for all microservices. Properties from the service have high priority over local microservice properties. To define properties for particular microservice use next pattern `<service-name>.yml` or `<service-name>.properties`, to define properties for particular profile use the next schema `<service-name>-<profile-name>.yml` or `<service-name>-<profile-name>.properties`

## Users Service

Service name **photoappapiusers**

Users service that has possibility to register and login into the system

## Account Management Service

Service name **photoappapiaccountmanagement**

## Albums Service

Service name **photoappapialbums**

## How to run

You need to follow the next steps:


1. Run RabbitMQ server
2. run Spring Clound Configuration Service
3. run Eureka Discover Service
4. run Zuul Gateway Service
5. run other services

## How to use

1. Check if services are registered in Eureka Discovery Service (http://localhost:8010/). Please note, Spring Cloud Configuration service will not be present in EDS

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

## Communication

### Types

1. Synchronous HTTP communication
2. Asynchronous communication over AMQP (Advanced Messaging Queue Protocol)

## Sensitive data encryption

### Symmetric

1. Install JCE (Java Cryptography Extension)
   1. Download https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
   2. Follow the steps from README.txt and copy jar file into your JRE security folder (for example: /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/security/)
   3. Close all java applications or restart a computer
2. Add **encrypt.key** in **bootstrap.yml** for Spring Config Service

### Asymmetric

1. Run next command to generate key-pair

`keytool -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=<common_name>,OU=API Development,O=<organisation>,L=<locality_name>,S=<state>,C=<city>" -keypass 1q2w3e4r -keystore apiEncryptionKey.jks -storepass 1q2w3e4r`

Where:

* **dname** contains next data
  * **CN** - common name, any value (for example your name and last name)
  * **OU** - organisational unit
  * **O** - Organization
  * **L** - locality
  * **S** - state
  * **C** - country

Change **keypass** and **storepass** to any values

Keytool is provided by JDK

2. Run next command to migrate to the industrial format

`keytool -importkeystore -srckeystore apiEncryptionKey.jks -destkeystore apiEncryptionKey.jks -deststoretype pkcs12`

3. Update Spring Config Service **bootstrap.yml** file
   1. encrypt.key-store.location - where your file, that created in step 1 is located
   2. encrypt.key-store.password - password to this file
   3. encrypt.key-store.alias - alias of the file, that used in the step 1

### Test Encryption

1. Start Spring Config Service
2. Send POST request to http://localhost:8012/encrypt with body of any value with type text/plain
3. Send POST request to http://localhost:8012/decrypt with body from previous response and check if returns request body of encryp url.

### Using

Encrypt all required data

1. Use next properties style to store encrypted data **{cipher}encrypted_value** (do not forget to surround data with single quote if you are using yaml **'{cipher}encrypted_value'**)