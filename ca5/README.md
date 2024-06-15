# Class Assignment 5

## Introduction:
In this assignment we will create 2 piplines, 1 is related to the basic demo chat app,
and the other one is related to frodobaggins. 



## Table of Contents
1. [

## Setting up Jenkins
1. Follow the jenkins and docker tutorial at: https://www.jenkins.io/doc/book/installing/docker/
2. Open docker application
3. docker network create jenkins
4. This example will be on windows powershell, adapt the command as follows:
```powershell
   docker run --name jenkins-docker --rm --detach `
   --privileged --network jenkins --network-alias docker `
   --env DOCKER_TLS_CERTDIR=/certs `
   --volume jenkins-docker-certs:/certs/client `
   --volume jenkins-data:/var/jenkins_home `
   --publish 2376:2376 `
   docker:dind
```
5. In notepad, create a Dockerfile, save it as Dockerfile:
```Dockerfile
FROM jenkins/jenkins:2.452.2-jdk17
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"
   ```
6. Next, build the docker image:
```powershell
docker build -t myjenkins-blueocean:2.452.2-1 .
```
7. Run the docker image:
```powershell
docker run --name jenkins-blueocean --restart=on-failure --detach `
  --network jenkins --env DOCKER_HOST=tcp://docker:2376 `
  --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 `
  --volume jenkins-data:/var/jenkins_home `
  --volume jenkins-docker-certs:/certs/client:ro `
  --publish 8080:8080 --publish 50000:50000 myjenkins-blueocean:2.452.2-1
```
8. Open the browser and go to localhost:8080:
9. Open DockerDesktop, and view to password;
10. Copy the password and paste it in the Jenkins page;
11. Install the suggested plugins;
12. Create an admin user;
13. Save and Finish;
14. Start using Jenkins.
15. Go to Manage Jenkins and ensure the JSON path API plugin is installed.
16. Visit the the credential page and add a new credential, insert your DockerHub username and password and define an ID
17. Go back to the dashboard and click on New Item, name it as "ca5_part1" and select Pipeline.
18. On the pipeline section, select the pipeline script from SCM, and insert the repository URL;
19. On the branch specification, ensure your main branch is selected, in this case its master;
20. Save the pipeline.
21. On the ca5_part1 directory create a new Jenkinsfile with the following content:
```groovy
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out...'
                script {
                    git 'https://github.com/hugosousa1231830/devops-23-24-JPE-PSM-1231830.git'
                }
            }
        }

        stage('Prepare Environment') {
            steps {
                echo 'Setting file permissions...'
                dir('ca2/part1/gradle_basic_demo/') {
                    sh 'chmod +x ./gradlew'
                }
            }
        }

        stage('Assemble') {
            steps {
                dir('ca2/part1/gradle_basic_demo/') {
                    sh './gradlew assemble'
                }
            }
        }

        stage('Test') {
            steps {
                dir('ca2/part1/gradle_basic_demo/') {
                    sh './gradlew test'
                    junit 'build/test-results//*.xml'
                }
            }
            post {
                always {
                    dir('ca2/part1/gradle_basic_demo/') {
                        archiveArtifacts 'build/test-results//*.xml'
                    }
                }
            }
        }

        stage('Archive') {
            steps {
                dir('ca2/part1/gradle_basic_demo/') {
                    archiveArtifacts artifacts: 'build//*.jar', allowEmptyArchive: true
                }
            }
        }
    }
}
```


