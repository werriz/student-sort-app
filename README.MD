# Sorting app

Its a simple web application, which allows user to upload file with students and their performance list and sort them with different sorting algorithms.

## Getting Started

Project is located in github, download it using Git clone or manually download zip file.

### Prerequisites

To run this application there are several things needed:

Java 8 (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 
Maven (https://maven.apache.org/install.html)


### Installing

Open command line or terminal, 
navigate to project folder, where project was Git-cloned (unpacked from zip file)

There should be _pom.xml_ file

Run Maven clean install command

```
mvn clean install
```

Run Spring Boot run command

```
mvn spring-boot:run
```

Open your browser and enter following address:

```
localhost:8080
```

## Tests

There are two unit tests, one is to test how file parsing works.
Other is to check if sorting algorithms actually sort.

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - Framework for Spring framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Vaadin](https://vaadin.com/) - Java web framework

