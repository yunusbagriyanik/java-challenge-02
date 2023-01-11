### h-case-02

### Requirements

* JDK 17(openjdk-17)
* Spring Boot 3.0.1
* Maven 3.6.3 or newer
* Docker
* RabbitMQ

### RabbitMQ
    $ docker-compose -f docker-compose.yml up -d

### Build App
    $ mvn clean install

#### Containers should be in the same network to communicate.


    $ docker network create hcase02

#### Volume used to persist data and share data between Docker containers.


    $ docker create -v /var/lib/mysql --name hcase02-server mysql/mysql-server:latest

####  Run database container.


    $ docker container run --platform linux/x86_64 --volumes-from hcase02-server --name hcase02-db --network hcase02 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=hcase02 -d mysql:8.0.28

### Run App

    $ mvn spring-boot:run

#### Database Web UI url: http://localhost:8000/


    $ docker run --name db-dashboard --network hcase02 -p 8000:80 --link hcase02-db:db -d phpmyadmin/phpmyadmin