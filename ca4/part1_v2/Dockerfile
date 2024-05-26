# FROM ubuntu:latest
# LABEL authors="Hugo Sousa"

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar

EXPOSE 59001

CMD ["java", "-jar", "basic_demo-0.1.0.jar"]