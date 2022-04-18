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
git clone https://github.com/mosesalphonse/moses-card-processing.git

cd moses-card-processing

mvn compile quarkus:dev

```

Note: Postgress DB runs in a docker container


## Verify

Note: Change your IP/DNS where your code is running while testing the below endpoints

### UI

```
http://localhost:8080/cards.html

```
### Swagger UI

```
http://localhost:8080/q/swagger-ui/

```
### Health and Metrics

```
http://localhost:8080/q/health

http://localhost:8080/q/metrics

http://localhost:8080/q/openapi

```

## Build and Run as Native Executable

```
mvn clean package -Dquarkus.package.type=native

docker run -it --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3

./target/creditcard-processing-1.0.0-SNAPSHOT-native-image-source-jarurce-jar

```
