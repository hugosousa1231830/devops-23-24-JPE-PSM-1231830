# FROM ubuntu:latest
# LABEL authors="Hugo Sousa"

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . /app

## For windows users------
RUN apt-get update && apt-get install -y dos2unix

RUN dos2unix ./gradlew
# ------------------------

RUN chmod +x gradlew

EXPOSE 59001

CMD ["./gradlew", "runServer"]
