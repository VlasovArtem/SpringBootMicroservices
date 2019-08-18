## Zuul Gateway Service
The service will work with clients request, will path through Security level. This service will work Discover service and then with particular microservice. 

Also it will works as load balancer if multiple microservice of the same tupe is registered in Discovery service.
## Spring Actuator
This service is allow to check status of the microservices and memory information

**/health** - health check

**/beans** - Display complete list of Beans in your Microservice

**/httptrace** - Displays HTTP trace information (by default, the last 100 HTTP request-response exchanges)

For more information https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready

http://localhost:8011/actuator/health

http://localhost:8011/actuator/routes - show routes to the microservices registered in discovery service