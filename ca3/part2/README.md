# Class Assignment 3 - Part 2

## Introduction:
The goal for this part of the assignment is to use Vagrant to setup a virtual environment to execute the tutorial spring 
boot application, gradle ”basic” version (developed in CA2, Part2). In this part of the assignment we will be creating
a vagrant script to create two virtual machines, one for the database and another for the webserver. The database VM 
will be used to execute the H2 server database and the webserver VM will be used to run tomcat and the spring boot basic
application.


## Table of Contents
1. [Analysing the Vagrantfile](#analysing-the-vagrantfile)
2. [Altering ca2/part2 project to use H2 server in the db VM](#altering-ca2-part2-project-to-use-h2-server-in-the-db-vm)
3. [Making the ca2/part2 project resistant to change](#making-the-ca2part2-project-resistant-to-change)
4. [Implementing the alternative solution](#implementing-the-alternative-solution)


## Analysing the Vagrantfile
1. To start, utilize the vagrant file present at https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/.
2. Configure the provision common to both VMs:
    - Update the ubuntu version to focal64.
    - Update the provision to install iputils-ping, avahi-daemon, libnss-mdns, unzip and openjdk-17-jdk-headless.
3. Configure the database VM:
    - Update the ubuntu version to ubuntu2204.
    - Set the hostname to db.
    - Set the private network ip to 192.168.56.11
    - Set the forwarded ports to 8082 and 9092. These ports will be used to access the H2 console and connect to the H2 
server respectively.
    - Download the H2 database.
    - Run the H2 server process.
    - The H2 server process will be executed always.
    - The H2 server process will be executed in the background.
    - The H2 server process will be executed with the following parameters:
        - web
        - webAllowOthers
        - tcp
        - tcpAllowOthers
        - ifNotExists
4. Configure the webserver VM:
    - Update the ubuntu version to ubuntu2204.
    - Set the hostname to web.
    - Set the private network ip to 192.168.56.10
    - Set the memory to 1024.
    - Set the forwarded port to 8080. This port will be used to access tomcat from the host.
    - Install tomcat9 and tomcat9-admin.
    - Clone ca2/part2 repository.
    - Confer permissions to gradlew file.
    - Build the project.
    - Run the project.

## Altering ca2/part2 project to use H2 server in the db VM
1. Firstly, we need to add an application.properties file to the resources folder of the project. This is where we will
configure the H2 server. Add the following code to the application.properties file:
```
server.servlet.context-path=/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT
spring.data.rest.base-path=/api
#spring.datasource.url=jdbc:h2:mem:jpadb
# In the following settings the h2 file is created in /home/vagrant folder
spring.datasource.url=jdbc:h2:tcp://192.168.56.11:9092/./jpadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
# So that spring will no drop de database on every execution.
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true
```

2. Next, we need to ensure our build.gradle file is configured to use the H2 server and Tomcat:
```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-data-rest'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	runtimeOnly 'com.h2database:h2'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
```

3. Finally, we need to ensure our application is configured to use the H2 server. Ensure the following code is present 
at the main class of the project:
```
@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

}
```
4. Change the path at app.js to /react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.


5. Change the path at index.html to /react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.
The two steps above adjust paths in app.js and index.html to match the deployment context of the Spring Boot app with React 
frontend, ensuring proper asset loading and deployment consistency.


6. Configure servlet initializer to use the correct path:
```
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BasicApplication.class);
    }

}
```

# Making the ca2/part2 project resistant to change
Note:
The alterations to "ca2/part2" caused reliance on a specific database setup, leading to page loading issues if the 
required vm wasn't running. Reverting changes to use Spring's H2 database addressed this. However, alternative solutions 
like using VirtualBox posed similar issues, requiring frequent changes in application configuration, undermining project 
adaptability.

1. To fix this issue, revert the changes made to ca2/part2: remove servletInitializer, alter paths in app.js and 
index.html, alter gradle.build and finally the application.properties to only include the main path.


2. Instead of utilizing apache Tomcat separately, utilize spring's embedded Tomcat server, to do this, comment out the 
following lines on the vagrantFile:
```
#web.vm.provision "shell", inline: <<-SHELL, privileged: false
      #wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.23/bin/apache-tomcat-10.1.23.tar.gz

      #sudo tar xzvf apache-tomcat-10*tar.gz -C .

      #sudo chown -R vagrant:vagrant apache-tomcat-10*

      #sudo chmod -R u+x apache-tomcat-10*
       #SHELL

    #web.vm.provision "shell", :run => 'always', inline: <<-SHELL
       #./apache-tomcat-10*/bin/startup.sh
    #SHELL
```
3. As we are using the embedded Tomcat server, we no longer need to deploy a WAR file, so we can remove the following
lines from the vagrantFile:
```
#sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war ~/apache-tomcat-10*/webapps/
```
An external Tomcat server typically requires a WAR (Web Application Archive) file for deployment because it's a 
standard packaging format for Java web applications. In the case of a Spring Tomcat server, the application is packaged
as a JAR (Java Archive) file, which is executable and doesn't require a separate server to run. This change simplifies
the deployment process and reduces the need for manual configuration, making the application more adaptable to different
environments.

# Implementing the alternative solution
The alternative solution to VirtualBox is Hyper-V, which is a native hypervisor for Windows operating systems. Hyper-V
provides similar virtualization features to VirtualBox, such as the ability to create and manage virtual machines,
networking, and storage. However, Hyper-V is more integrated with Windows and offers better performance and scalability
for enterprise environments. To implement the alternative solution, we need to modify the Vagrantfile to use the Hyper-V
provider instead of VirtualBox. The following steps outline the changes required to use Hyper-V with Vagrant:

1. Install the Hyper-V feature on Windows:
    - Open the Control Panel and go to Programs and Features.
    - Click on Turn Windows features on or off.
    - Check the box next to Hyper-V and click OK.
    - Restart the computer to apply the changes.
   

2. Update the Vagrantfile to use the Hyper-V provider.


3. Change the provider from VirtualBox to Hyper-V:
```
config.vm.provider "hyperv" do |hv|
```
4. Configure the number of CPUs and memory for the VM (Hyper-v requires a minimum of 2 CPUs and 1024 MB of memory to 
run the VMs):
```
hv.cpus = 2
hv.memory = 1024
```
5. Configure the network settings for the VM:
```
hv.linked_clone = true
```
6. Update the network configuration to use a private network:
```
db.vm.network "private_network", ip: "
```
7. Update the provisioning script to install the required packages:
```
db.vm.provision "shell", inline: <<-SHELL
```
8. Update the provisioning script to download and run the H2 server:
```
db.vm.provision "shell", inline: <<-SHELL
```
9. Update the network configuration for the webserver VM:
```
web.vm.network "private_network", ip: "
```
10. Update the provisioning script for the webserver VM:
```
web.vm.provision "shell", inline: <<-SHELL, privileged = true
```
11. Save the changes to the Vagrantfile and run the vagrant up command to create the VMs using Hyper-V. The Vagrantfile
should now be configured to use Hyper-V as the provider and create the VMs with the specified settings.


12. Test the VMs to ensure they are running correctly and the application is accessible from the host machine. You can
use the vagrant ssh command to access the VMs and verify the installation and configuration of the application.


Note: Hyper-v attributes a default IP address to the VMs, which can be found by running the ipaddr command in the
provisioning script. This IP address can be used to access the VMs from the host machine. Alternatively, you can open
the Hyper-V Manager and check the IP address assigned to the VMs. This means that when attempting to verify that the
server is running, you will need to use the IP address assigned by Hyper-V, rather than the one specified in the
Vagrantfile: XXXXXX:8080.
