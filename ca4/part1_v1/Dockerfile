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

# docker instala um ubuntu por default
#java slim p n ocupar espaço
#1 pasta chamada app como working directory
#copiar o .app para a app
#dar permissoes ao gradlew
#expor 59001

# link como gradle project

# registar no docker

# docker build -t hugosousaw/basic_demo:latest .

######### Para utilizadores windows
# RUN apt-get update && apt-get install -y dos2unix

# RUN dos2unix ./gradlew
###################################

# docker run -p 59001:59001 hugosousaw/basic_demo:latest

# O server fica no docker
# runclient localmente

#####################################################################################################################
# copiar o jar do diretorio build/libs do part2
# criar novo ficheiro docker, no copy

# docker build -t hugosousaw/basic_demo_v2:latest .
# docker run -p 59001:59001 hugosousaw/basic_demo_v2:latest
####################################################################################################################
# part2
# 2 ficheiros dockerfile db e web
# dockercompose
#