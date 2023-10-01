# FROM openjdk:17-jdk-slim
FROM maven:3.8.5-openjdk-17
VOLUME /tmp
COPY target/microservicetemplate-0.0.1-SNAPSHOT.jar microservicetemplate.jar
ENTRYPOINT ["java","-jar","/microservicetemplate.jar"]