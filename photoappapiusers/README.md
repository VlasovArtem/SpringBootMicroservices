You can start service with unique ID and pirt, for Eureka and Zuul Gateway
Using IDEA `spring.application.instance_id=artem;server.port=55555` add this line in environment properties, or with command line `-Dspring-boot.run.arguments=--spring.application.instance_id=artem,server.port=55555`

Connect to H2 `http://<zuul-api-gateway-service-address>/users-ws/h2-console`
