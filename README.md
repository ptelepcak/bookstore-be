## Bookstore Inventory Management System

### Java

This project uses Java 17.

### Docker
Docker is used to run Elasticsearch instance. No further configuration required.

    docker compose -f docker-compose.yml up

For consecutive use

    docker compose -f src/main/docker/docker-compose.yml start

### Running Spring Boot Instance

Via IntelliJ or command line.

    ./gradlew bootRun

### Swagger Docs

    http://localhost:8080/swagger-ui/index.html#/

### Auth

Basic auth implemented.

    user/password for USER ROLE (for GET endpoints)
    admin/password for ADMIN ROLE (for all the endpoints)
