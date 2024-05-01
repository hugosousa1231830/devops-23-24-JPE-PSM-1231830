# Class Assignment 3 - Part 1 - Virtualization with Vagrant

## Introduction:


## Table of Contents
1. [Part 2](#part-2)
    1. [Arranging the project structure](#arranging-the-project-structure)
    2. [Adding the frontend support](#adding-the-frontend-support)
    3. [Adding the copyJar task](#adding-the-copyjar-task)
    4. [Adding the deleteWebpackFiles task](#adding-the-deletewebpackfiles-task)
    5. [Merging the branches](#merging-the-branches)
    6. [Alternative solution - Utilizing maven](#alternative-solution---utilizing-maven)

## Setting up the VirtualBox Virtual Machine
1. To create a virtual machine, first install VirtualBox. To download it from the official website:
[here](https://www.virtualbox.org/). 
2. After installing VirtualBox, download the Ubuntu 22.04.3 iso image from the official website.
3. To create a new virtual machine, open VirtualBox and click on the "New" button.
4. Select the type of operating system and version. In this case, we must select "Linux" and "Ubuntu (64-bit)".
5. Select the amount of memory (RAM) that we want to allocate to the virtual machine. In this case, we  will allocate 
2GB of RAM. 
6. Create a virtual hard disk for the virtual machine. We can leave the default settings and click on "Create".
7. Select the size of the virtual hard disk. We can leave the default size and click on "Create".
8. Select the Ubuntu iso image that was downloaded earlier and click on "Start" to follow the instructions to install 
Ubuntu on the virtual machine.
10. After installing Ubuntu, install the VirtualBox Guest Additions to enable features such as shared folders
11. To configure the network settings of the virtual machine, go to the settings of the virtual machine in
VirtualBox and select the "Network" tab, select "Host-only Adapter" and click on "OK".
12. To configure the network settings of the virtual machine in Ubuntu edit the /etc/netplan/00-installer-config.yaml 
file and adding the following configuration:
```yaml
enp0s8:
  addresses:
    - 192.168.56.5/24;
```
13. Apply the changes by running the following command:
```bash
sudo netplan apply
```
14. Install the openssh-server package to enable SSH access to the virtual machine:
```bash
sudo apt update
sudo apt install openssh-server
```
15. Enable password authentication by editing the /etc/ssh/sshd_config file and changing the following
line:
```bash
PasswordAuthentication yes
```
16. Restart the SSH service by running the following command:
```bash
sudo systemctl restart ssh
```
17. To access the VM utilizing the ssh protocol, run the following command:
```bash
ssh hugosousa@192.168.56.5
```
We can either continue using the VM or utilize the Windows command prompt to run the commands after the ssh connection 
is established.

## Cloning the repository and installing the necessary dependencies
As a side note, it is important to mention that the projects within the repository have wrapper scripts for both Maven
and Gradle. This means that we can run the projects without having to install Maven or Gradle on the virtual machine.
This tutorial will show how to install Maven and Gradle on the virtual machine and within the specific projects, it
will be shown how to run the projects utilizing with or without the wrapper scripts.

1. Clone the repository by running the following command:
```bash
git clone https://github.com/hugosousa1231830/devops-23-24-JPE-PSM-1231830.git
```
2. Install the necessary dependencies by running the following commands:
```bash
sudo apt install openjdk-17-jdk-headless
sudo apt install git
```
Note, if you install a version of jdk lower than 17, ca2/part2 will not work as it requires jdk 17.
3. Install the maven and gradle build tools by running the following commands:
```bash
sudo apt install maven
sudo apt install gradle
```

# Running CA1
Note: Some files may require execution permissions. To give execution permissions to the wrapper scripts, run the 
following command:
```bash
chmod +x mvnw 
```
1. To run the CA1 project without the wrapper script, navigate to the CA1/react-and-spring-data-rest/basic folder and 
run the following commands:
```bash
cd CA1/basic
mvn compile
mvn spring-boot:run
```
Alternatively, we can build and run the project utilizing the wrapper script by running the following command:
```bash
./mvnw spring-boot:run
```
To ensure that the project is running, open a web browser and navigate to the following address:
```bash
http://192.168.56.5:8080/
```
To clarify, the IP address is the one that was assigned to the virtual machine and the port 8080 is the default port 
that the embedded Tomcat server utilizes.

# Running CA2 - Part 1
An important consideration is that the ca2/part1 project necessitates both server and client sides to be operational.
The client side specifically demands a graphical interface, hence it is advisable to execute it on the host machine,
while the server side is better suited for operation on a virtual machine. Attempting to run the client side on the 
virtual machine may result in a Headless Exception, indicating the necessity of a graphical environment. Conversely, 
deploying a server side application on a virtual machine aligns with common practices, as servers are frequently 
accessed remotely or operated in headless environments. 

1. Some files may require execution permissions. To give execution permissions to the wrapper scripts, run the
following command:
```bash
chmod +x gradlew 
```
2. On the VM, navigate to the ca2/part1 folder and run the following command to build and run the server side (without
wrapper script):
```bash
gradle runServer
```
Or with the wrapper script:
```bash
./gradlew runServer
```

3. On the host machine, navigate to the ca2/part1 folder and run the following commands to build and run the client 
side (without wrapper script):
```bash
gradle runClient --args="192.168.56.5 59001"
```
Or with the wrapper script:
```bash
./gradlew runClient --args="192.168.56.5 59001"
```
The IP address and port number are passed as arguments to the client side application. The IP address corresponds to
the address of the virtual machine, while the port number is the port on which the server side is listening. This port
number is specified in the server side application, which is checked by going over to the gradle.build file and
inspecting the runServer task. On this case, the port number is 59001.

# Running CA2 - Part 2
Part2 follows the same structure as before. It is of note that this project requires java version 17 to run. 
First ensure that the files has execution permissions, utilize gradle or ./gradlew to run the application and execute
the tasks highlighted in ca2/part2/README.md.