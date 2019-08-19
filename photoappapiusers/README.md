# Users Service

You can start service with unique ID and pirt, for Eureka and Zuul Gateway
Using IDEA `spring.application.instance_id=artem;server.port=55555` add this line in environment properties, or with command line `-Dspring-boot.run.arguments=--spring.application.instance_id=artem,server.port=55555`

Connect to H2 `http://<zuul-api-gateway-service-address>/users-ws/h2-console`

## MySQL database

To enable MySQL database add active profile **mysql_db** to the **bootstrap.yml** in this service

Run next command on your MySQL instance

Login as **root** user

```mysql
create database photo_app;
create user 'user'@'localhost' identified by 'user';
GRANT ALL privileges on photo_app.* to 'user'@'localhost';
ALTER USER 'user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'user';
```

## Services
This Service work and connect to the next services Eureka, Feign Client, Hystrix, Actuator,  Config Server, Spring Cloud Sleuth, and Zipkin

## Sleuth and Zipkin

Sleuth log info `[users-ws,90b2180464d31e17,90b2180464d31e17,true]`, where:
1. Application name
2. Trace ID - is not changing during transaction 
3. Span ID - is changes from microservice to microservice.
4. true - should be send to Zipkin
  

To enable Zipkin follow next website and start zipkin server https://zipkin.io/pages/quickstart.html

Zipkin start url - http://localhost:9411

