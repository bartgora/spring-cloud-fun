# spring-cloud-fun

A showcase project for simple system in microservice architecture. </br>

To Run prometheus:
```shell
docker compose up
```

## Components:
<b>Api Gateway - Spring gateway with reactive router, Load Balancer, and Circuit Breaker</b>.</br>
To add circut breaker to gateway we use Resilience4j library
```xml
       </dependency>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-reactor-resilience4j</artifactId>
        </dependency>
```
Config:

```java
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("greetings", r -> r.path("/greetings/*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("circut").setFallbackUri("forward:/fallback")))
                        .uri("lb://hello-service"))
                .route("fallback", r -> r.path("/fallback").uri("lb://fallback-service"))
                .build();
    }
```
Above config will set route to hello service, and if hello service will be unavailable, reroute request to fallback service  

<b>Discovery Service - Netflix Eureka</b>. </br>
<b>Hello Service - Springboot application</b> with simple message Hello.</br>
In helllo serice we are using prometheus to count number of call to hello endpoint.
First we need to create Mettrics Manager
```java
@Component
public class MetricsManager {
    private final Counter counter;

    public MetricsManager(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("get_counter_functional");
    }

    public void inc(){
        counter.increment();
    }
}
```

And now we can use it in our service:
```java

  @Bean
  RouterFunction<ServerResponse> router() {
    return route().nest(RequestPredicates.path("greetings"), builder -> builder.GET("/hello", h -> get())).build();
  }

  public Mono<ServerResponse> get() {
    return Mono.fromRunnable(metricsManager::inc).subscribeOn(Schedulers.boundedElastic())
        .then(ServerResponse.ok().bodyValue("Hello From service! " + value));

  }
```

<b>Fallback Service - Springboot application</b> that is used for Fallback in case Hello Service is not reacheble.</br>

