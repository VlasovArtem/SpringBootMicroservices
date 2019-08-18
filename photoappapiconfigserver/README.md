# Spring Cloud Configuration Service
The service will work external git repository to provide global properties for all microservices. Properties from the service have high priority over local microservice properties. To define properties for particular microservice use next pattern `<service-name>.yml` or `<service-name>.properties`, to define properties for particular profile use the next schema `<service-name>-<profile-name>.yml` or `<service-name>-<profile-name>.properties`

The service also will work as Spring Config Bus, to provide properties at runtime. Based on AMQP (RabbitMQ)

To update configuration from Cloud Configuration using Spring Config Bus use next url http://localhost:8012/actuator/bus-refresh

Additional details on updating configuration:

You need push chahges to you git repository and then execute POST request to http://localhost:8012/actuator/bus-refresh

For updating configuration git repository url update this property spring:
**spring.cloud.config.server.git.uri** and if this is required **spring.cloud.config.server.git.username** and **spring.cloud.config.server.git.password** in **application.yml**.

Please note, that instance of RabbitMQ is required to work with Spring Config Bus, update application.yml if this is required to match your server configuration.

Config Server is allow to store data on local file system do that you need to update **spring.profiles.active=native** (or both **spring.profiles.active=native, git**) and spring.cloud.config.server.native.search-locations - with your location and with name PhotoAppAPIConfigServer.yml or PhotoAppAPIConfigServer.properties, or application.properties/yml to use it for all microservices.

Check properties by GET request to the **http://localhost:8012/PhotoAppAPIConfigServer/native**, or **http://localhost:8012/PhotoAppAPIConfigServer/default** for default profile, or **http://localhost:8012/users-ws/default** for application users-ws for default profile.

If you are using application specific properties for example for photoappapiusers (or users-ws), then you will create file with name users-ws.properties or users-ws.yml and place it into required folder or on the required git repository and you need to change next property **spring.cloud.config.name=users-ws** or remove completely because name of the spring application is matching with properties file name.

 