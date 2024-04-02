# Class Assignment 2 - Part 1 - Build Tools With Gradle

## Introduction:
This assignment was introduced to the class within the DEVOPS discipline within the Switch course.
The following tutorial will provide a detailed walkthrough of the assignment, however it must be noted that it required
the creation of a local personal [repository](https://github.com/hugosousa1231830/devops-23-24-JPE-PSM-1231830).

The assignment will be tagged along the way, following this format: Tag v.assignmentNumber.part.section. For example, the 
first Tag will be 2.1.0 - Which means assignment 2, part 1, section 0.

NOTE: Some commands such as mkdir, cd, tag and commit+push will be abstracted as they were covered in the previous assignment.

## Table of Contents
1. [Part 1](#part-1)
    1. [Cloning the new repository](#cloning-the-new-repository)
    2. [Adding a new task: JavaExec](#adding-a-new-task-javaexec)
    3. [Create a test class](#create-a-test-class)
    4. [Adding a new task: Copy](#adding-a-new-task-copy)
    5. [Adding a new task: Zip](#adding-a-new-task-zip)

## Overview
This assignment will be divided into three parts. The first part will consist of adding a new task to the build.gradle file.
The second part will consist of adding a test class and a unit test. The third part will consist of adding a new task of the type
Copy.


## Part 1
# Cloning the new repository
1. In order to start the assignment, the package structure was created. Instructions for this will be abstracted as they
were covered in the previous assignment.
2. Clone the repository to CA2/part1 by first navigating to the designated package (cd) and then cloning the repository using
the following command:
```bash
git clone https://bitbucket.org/pssmatos/gradle_basic_demo/
```
3. Delete the .git folder in the cloned repository to avoid conflicts with the new repository (the -f flag is used to force the deletion):
```bash
rm -rf .git
```
4. Commit the changes to the new repository.

5. create tag v.2.1.0.

# Adding a new task: JavaExec
1. Read the instructions and experiment with the application. Build the server and generate a .jar file using:
```bash
./gradlew build
```
2. Run the application using the new .jar file:
```bash
java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001
```
3. On a new terminal run the client using the gradle task:
```bash
./gradlew runClient
```
4. To create create a new task of the type "JavaExec", edit the "build.gradle" file and add the following task:
```gradle
task runServer(type:JavaExec, dependsOn: classes){
    group = "DevOps"
    description = "Launches a chat server that listens on localhost:59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
```
Breaking down the task: runServer is the name of the task. Within brackets we specify that is of type JavaExec and depends
on the classes task (this is a task that is created implicitly by gradle). The group section sets the task's group to 
"DevOps" and the description provides a brief description of the task. The classpath sets the path to the runtime classpath, whose
value is the main source set. The mainClass specifies the class that will be executed, in this case the ChatServerApp class.
Finally, the args section specifies the arguments that will be passed to the main class, in this case the port number 59001.

5. Tag v.2.1.1.

# Create a test class
1. In order to create a test class, first create a new directory and a new test class.

2. The tests will use Junit, so add the following dependencies to the build.gradle file:
```gradle
dependencies {
    (...)
    testImplementation 'junit:junit:4.12'
}

```
3. Ensure the project is compiled by running the following command:
```bash
./gradlew build
```

4. Create a test within the AppTest Class. The test will check if the App class has a greeting. The test will be as follows:
```java
package basic_demo;
import org.junit.Test;
import static org.junit.Assert.*;
public class AppTest {
   @Test public void testAppHasAGreeting() {
      App classUnderTest = new App();
      assertNotNull("app should have a greeting", classUnderTest.getGreeting());
   }
}
```

5. To validate tests, run the following command:
```bash
./gradlew test
```

6. Tag v.2.1.2.

# Adding a new task: Copy
1. Add a new task of the type copy. The task will be named "BackupCopy" and will copy the source files to a new directory.
```gradle
task BackupCopy(type: Copy){
    group = "DevOps"
    description = "Creates a backup copy of the source package unto the backup directory"

    from 'src/'
    into 'backup/'
}
```
The above code will perform a task of type "Copy", with the group "DevOps" and description "Creates a backup copy of the
source package unto the backup directory". The from section specifies the source directory, and the into section specifies
the directory where the files will be copied to.

2. Run the task independently by using the following command:
```bash
./gradlew BackupCopy
```

3. Tag v2.1.3.

# Adding a new task: Zip

1. Add a new task of the type "Zip". The task will be named "BackupZip" and will create a .zip file of the source files.
```gradle
task BackupZip(type: Zip){
    group = "DevOps"
    description = "Creates a .zip file of the source package unto the backup directory"

    from 'src/'
    archiveFileName = 'src.zip'
    destinationDirectory = file('backup/')
}
```
The above code will perform a task of type Zip, with the group "DevOps" and description "Creates a .zip file of the 
source package unto the backup directory". The from section specifies the source directory, the archiveFileName specifies 
the name of the .zip file and the destinationDirectory specifies the directory where the .zip file will be stored.

2. Run the task independently by using the following command:
```bash
./gradlew BackupZip
```

3. Tag v2.1.4.

4. Tag ca2-part1. This tag will mark the end of the first part of the assignment.

