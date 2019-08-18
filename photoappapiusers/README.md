## Users Service

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

