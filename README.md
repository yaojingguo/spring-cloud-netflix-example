## Projects
### eureka-service-registry
Provides Eureka registry service.

### eureka-client-service-provider
All the REST services in this project will be registered automatically on the
Eureka registry.

### eureka-client-service-consumer
Invoke the REST services provided by eureka-client-service-provider with REST
service URL and Eureka serviceId.

### zuul-api-gateway
API gateway.

### rest-service-consumer
Privide a REST service which call the hello service through the API gateway in
turn.

## How to Run

$PRJ_DIR

```
> cd $PRJ_DIR/eureka-service-registry
> gradle bootRun
```

Open http://localhost:8761/ in your browser and visit Eureka registry.

```
> cd $PRJ_DIR/eureka-client-service-provider
> gradle bootRun
```

Open the following URLs in your browser and make sure that the REST services are
working:
- hello service: http://10.200.79.89:8080/hello
- http://10.200.79.89:8080/service-instances/eureka-client-service-provider

In Eureka registry Web UI, you can see `EUREKA-CLIENT-SERVICE-PROVIDER` in
section `Instances currently registered with Eureka`. If you can't see it, 
wait one minute and refresh the Web UI. Eureka takes some time to register a
instance. 

```
> cd $PRJ_DIR/eureka-client-service-consumer
> gradle bootRun
```

Open http://localhost:8081/route in your browser and checks log messages in
your console.


```
> cd $PRJ_DIR/zuul-api-gateway
> gradle bootRun
```

Open http://localhost:8090/get in your browser to access the httpbin service
through the httpbin route. Open http://localhost:8090/hello-service/hello in your
browser to access the hello service through the hello route which uses Eureka
serviceId.


```
> cd $PRJ_DIR/rest-service-consumer
> gradle bootRun
```

Open http://localhost:8083/greet to make sure that the REST service is working.
