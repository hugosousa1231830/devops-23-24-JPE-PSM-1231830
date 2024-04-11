The goal of Part 2 of this assignment is to convert the basic version (i.e., ”basic”
folder) of the Tutorial application to Gradle (instead of Maven)
1 In your repository create a new branch called tut-basic-gradle. You should use this
branch for this part of the assignment (do not forget to checkout this branch).
2 As instructed in the readme file of the tutorial, use https://start.spring.io to
start a new gradle spring boot project with the following dependencies: Rest
Repositories; Thymeleaf; JPA; H2.

Extract the generated zip file inside the folder ”CA2/Part2/” of your repository. We
now have an ”empty” spring application that can be built using gradle. You can
check the available gradle tasks by executing ./gradlew tasks.
4 Delete the src folder. We want to use the code from the basic tutorial...
5 Copy the src folder (and all its subfolders) from the basic folder of the tutorial into
this new folder.
Copy also the files webpack.config.js and package.json
Delete the folder src/main/resources/static/built/ since this folder should be
generated from the javascrit by the webpack tool.
6 You can now experiment with the application by using ./gradlew bootRun.
Notice that the web page http://localhost:8080 is empty! This is because gradle is
missing the plugin for dealing with the frontend code!

We will add the gradle plugin org.siouan.frontend to the project so that gradle is
also able to manage the frontend. See
https://github.com/Siouan/frontend-gradle-plugin
This plugin is similar to the one used in the Maven Project (frontend-maven-plugin).
See https://github.com/eirslett/frontend-maven-plugin
8 Add one of the following lines to the plugins block in build.gradle (select the line
according to your version of java: 8, 11 or 17):

Add also the following code in build.gradle to configure the previous plug-in:
frontend {
nodeVersion = "16.20.2"
assembleScript = "run build"
cleanScript = "run clean"
checkScript = "run check"
}
10 Update the scripts section/object in package.json to configure the execution of
webpack:
"scripts": {
"webpack": "webpack",
"build": "npm run webpack",
"check": "echo Checking frontend",
"clean": "echo Cleaning frontend",
"lint": "echo Linting frontend",
"test": "echo Testing frontend"
},
JPE, PSM (ISEP) CA2, Part 2 - Build Tools with Gradle 2023/2024 7 / 10CA2, Part 2: Goals/Requirements (5/5)
11 You can now execute ./gradlew build. The tasks related to the frontend are also
executed and the frontend code is generated.
12 You may now execute the application by using ./gradlew bootRun
13 Add a task to gradle to copy the generated jar to a folder named ”dist” located a
the project root folder level
14 Add a task to gradle to delete all the files generated by webpack (usually located at
src/resources/main/static/built/). This new task should be executed
automatically by gradle before the task clean.
15 Experiment all the developed features and, when you feel confident that they all
work commit your code and merge with the master branch.
16 Document all your work in a readme.md file (including the analysis of alternatives, if
developed).
17 At the end of part 2 of this assignment mark your repository with the tag ca2-part2.
JPE, PSM (ISEP) CA2, Part 2 - Build Tools with Gradle 2023/2024 8 / 10CA2, Part 2: Alternative Solution
For this assignment you should present an alternative technological solution for the
build automation tool (i.e., not based on Gradle).
You should Analyze how the alternative solution compares to your base solution. You
should:
1 Present how the alternative tool compares to Gradle regarding build automation
features;
In particular, compare how the build tool can be extended with new functionality (i.e.,
new plugins or new tasks)
2 Describe how the alternative tool could be used (i.e., only the design of the solution)
to solve the same goals as presented for this assignment (see previous slides);
To potentially achieve a complete solution of the assignment you should also implement
the alternative design presented in the previous item 2.
JPE, PSM (ISEP) CA2, Part 2 - Build Tools with Gradle 2023/2024 9 / 10CA2, Part 2: Technical Report
You should produce a technical report documenting your assignment.
The technical report must be produced in the readme.md file located in the
repository folder related to CA2, part 2 (e.g., /ca2/part2)
You should use the markdown syntax in a readme.md file inside the folder for CA2
part 2.1
The report should include:
A section dedicated to the description of the analysis, design and implementation of
the requirements
Should follow a ”tutorial” style (i.e., it should be possible to reproduce the assignment by
following the instructions in the tutorial).
Should include a description of the steps used to achieve the requirements.
Should include justifications for the options (when required)
A section dedicated to the analysis of the alternative
A section dedicated to the implementation of the alternative.


tag v2.2.0
1. First, I created a new branch called tut-basic-gradle. To create a branch
```bash
git checkout -b tut-basic-gradle
```
2. Generated the project using https://start.spring.io with the following dependencies: Rest Repositories; Thymeleaf; JPA; H2.

3. Extracted the generated zip file inside the folder ”CA2/Part2/” of the repository. To unzip to the desired folder, I used the command:
```bash
unzip -d CA2/Part2/ react-and-spring-data-rest-basic.zip
```

tag v2.2.1
4. Delete the src folder from CA2-part2. Copy the contents of the src folder from CA1 into the src folder of CA2-part2.
Copy the webpack.config.js and package.json files from CA1 into the CA2-part2 folder. Delete the main/resources/static/built
folder as it should be generated by the webpack tool.

5. Use ./gradlew bootRun to run the application. The web page http://localhost:8080 is empty as gradle is missing the 
plugin for dealing with the frontend code.
