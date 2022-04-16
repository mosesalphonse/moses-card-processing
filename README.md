# moses-card-processing
moses-card-processing API

## Prerequisite

```
Apache Maven 3.6.3 or above

JDK 11 or above

Docker 20.10.14

```

## Build and Run in local

```
mvn compile quarkus:dev

```

Note: Postgress DB runs in a docker container


## Verify

### UI

```
https://localhost:8080/cards.html


```
### Swagger UI

```
https://localhost:8080/q/swagger-ui/

```
### Health and Metrics

```
https://localhost:8080/q/health

https://localhost:8080/q/metrics

https://localhost:8080/q/openapi

```

