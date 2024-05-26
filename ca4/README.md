# Class Assignment 4 

## Introduction:
The goal of this assignment is to create Docker containers. It is divided into two parts. The first part consists of 
creating a Docker container with the chat application from CA2. The second part consists of creating two Docker 
containers, one for the web application and another for the database, using Docker Compose.


## Table of Contents
1. [Setting up the Docker environment](#setting-up-the-docker-environment)
2. [Part1 - Version 1](#part1---version-1)
3. [Part1 - Version 2](#part1---version-2)
4. [Part2 - Database dockerfile](#part2---database-dockerfile)
5. [Part2 - Web application dockerfile](#part2---web-application-dockerfile)
6. [Part2 - Final steps - Docker-compose](#part2---final-steps---docker-compose)

## Setting up the Docker environment
1. Install Docker on your machine. You can find the installation instructions [here](https://docs.docker.com/get-docker/).
2. Create the package structure for the project. This particular project has the following structure:
```
ca4
├── part1_v1
├── part1_v2
└── part2
```
3. Create the Dockerfiles for each part of the assignment. The Dockerfiles are then placed at the directories.

## Part1 - Version 1
1. Copy the chat application files from CA2 - part1 to the part1_v1 directory.

2. Create a Dockerfile in the part1_v1 directory with the following content

3. Use the OpenJDK 17 slim image as the base. For this particular application, the slim version is sufficient:
```Dockerfile
FROM openjdk:17-jdk-slim
```

4. Sets the working directory to /app. This is where the application files will be copied to within the container:
```Dockerfile
WORKDIR /app
```

5. Copies the application files into the container. The . refers to the current directory. So it is copying all the contents:
```Dockerfile
COPY . /app
```

6. Windows users require the following two steps:
```Dockerfile
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix ./gradlew
```
Summarily, they are required because Windows uses different line endings than Unix-based systems. The dos2unix utility 
is used to convert the line endings to Unix format. The second command converts the gradlew script to Unix line endings.

7. Makes the gradlew script executable by providing execute permissions:
```Dockerfile
RUN chmod +x gradlew
```

8. EXPOSE documents that the container will use port 59001:
```Dockerfile
EXPOSE 59001
```
This is the port that the chat application uses. Evidence can be found within the build.gradle file.

9. Defines the default command to start the server using Gradle:
```Dockerfile
CMD ["./gradlew", "runServer"]
```
10. Build the Docker image using the following command. This will publish the image to the local Docker registry:
```bash
docker build -t hugosousaw/basic_demo:latest .
```
The -t command allows you to tag the image with a name and a tag. In this case, the name is hugosousaw/basic_demo and 
the tag is latest.

11. Run the Docker container using the following command:
```bash
docker run -p 59001:59001 hugosousaw/basic_demo:latest
```
The -p command maps the port 59001 from the container to the host machine.

12. The server should now be running. To verify this open a new terminal and perform runClient. A new window with the
chat application will pop up.

# Part1 - Version 2
1. In this version, we will simply copy the jar file from the build/libs directory of the part2 project to the part1_v2
directory.
2. Create a new DockerFile on the part1_v2 directory with the following content.
3. Use the OpenJDK 21 slim image as the base. For this particular application, the slim version is sufficient:
```Dockerfile
FROM openjdk:21-jdk-slim
```
Note: The version of the JDK is different from the previous version. This is because the jar file was compiled using a 
newer version of the JDK.
4. Sets the working directory to /app. This is where the application files will be copied to within the container:
```Dockerfile
WORKDIR /app
```
5. Copies the jar file into the container. This will create a new directory called app and copy the jar file into it:
```Dockerfile
COPY basic_demo-0.1.0.jar /app/basic_demo-0.1.0.jar
```
6. EXPOSE documents that the container will use port 59001:
```Dockerfile
EXPOSE 59001
```
7. Defines the default command to start the server using the jar file:
```Dockerfile
CMD ["java", "-jar", "basic_demo-0.1.0.jar"]
```
8. Build the Docker image using the following command. This will publish the image to the local Docker registry:
```bash
docker build -t hugosousaw/basic_demo_v2:latest .
```
9. Run the Docker container using the following command:
```bash
docker run -p 59001:59001 hugosousaw/basic_demo_v2:latest
```
10. The application should now be running, and some output should be displayed on the terminal.

# Part2 - Database dockerfile
1. First we need to setup two Dockerfiles, one for the web application and another for the database.
2. The Dockerfile for the database is as follows
3. Ensure that the base image is Ubuntu:
```Dockerfile
FROM ubuntu
```
4. Update the package list and install the necessary packages:
```Dockerfile
RUN apt-get update && \
  apt-get install -y openjdk-17-jdk-headless && \
  apt-get install unzip -y && \
  apt-get install wget -y
```
5. Create a directory for the application:
```Dockerfile
RUN mkdir -p /usr/src/app
```
6. Set the working directory to the application directory:
```Dockerfile
WORKDIR /usr/src/app/
```
7. Download the H2 database and run it:
```Dockerfile
RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar -O /opt/h2.jar
```
8. Expose the ports for the H2 database:
```Dockerfile
EXPOSE 8082
EXPOSE 9092
```
9. Start the H2 server:
```Dockerfile
CMD java -cp /opt/h2.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists
```

# Part2 - Web application dockerfile
1. The Dockerfile for the web application is as follows
2. Ensure that the base image is Ubuntu:
```Dockerfile
FROM openjdk:17-jdk-slim
```
3. Install additional utilities:
```Dockerfile
RUN apt-get update -y && apt-get install -y git unzip
```
4. Create a directory for the application:
```Dockerfile
RUN mkdir /app
```
5. Set the working directory to the application directory:
```Dockerfile
WORKDIR /app/
```
6. Clone the Spring Boot application repository:
```Dockerfile
RUN git clone https://github.com/hugosousa1231830/devops-23-24-JPE-PSM-1231830.git
```
7. Set the working directory to the application directory:
```Dockerfile
WORKDIR /app/devops-23-24-JPE-PSM-1231830/ca2/part2/react-and-spring-data-rest-basic
```
8. Ensure the gradlew script is executable and build the application:
```Dockerfile
RUN chmod +x ./gradlew && ./gradlew clean build
```
9. Configure the container to run as an executable:
```Dockerfile
ENTRYPOINT ["./gradlew"]
CMD ["bootRun"]
```
# Part2 - Final steps - Docker-compose
1. Create a docker-compose.yml file with the following content:
```yaml
services:
  db:
    build:
      context: .
      dockerfile: Dockerfile_db
    ports:
      - "8082:8082"
      - "9092:9092"
    volumes:
      - ./data:/usr/src/data-backup
    networks:
      default:
        ipv4_address:192.168.33.11
```
This file defines the database service. It builds the image using the Dockerfile_db file, exposes the ports 8082 and 9092,
creates a volume for the database, and sets the network configuration. The volume will be used to store the database file.
And can be found in the data directory.

2. Add the web service to the docker-compose.yml file:
```yaml
    web:
    build:
      context: .
      dockerfile: Dockerfile_web
    ports:
      - "8080:8080"
    networks:
      default:
        ipv4_address: 192.168.33.10
    depends_on:
      - "db"
```

3. Define the network configuration:
```yaml
networks:
  default:
    ipam:
      driver: default
      config:
        - subnet: 192.168.33.0/24
```

4. Build the Docker images using the following command:
```bash
docker compose up
```
This command will build the images and start the containers.

5. The web application should now be running on port 8080. You can access it by opening a browser and navigating to
http://localhost:8080.

6. The database should be running on port 8082. You can access it by opening a browser and navigating to
http://localhost:8082.

This concludes part 2 and ca4.