# Class Assignment 1 - Version Control with GIT

## Introduction:
This assignment was introduced to the class within the DEVOPS discipline within the Switch course.
The assignment focuses on the learning obtained within the first weeks, and focuses on version control with git.
The following tutorial will provide a detailed walkthrough of the assignment, however it must be noted that it required 
these two steps to be completed beforehand:
1. Creation of a local personal [repository](https://github.com/hugosousa1231830/devops-23-24-JPE-PSM-1231830).
2. Cloning of the [Tutorial React.ks and Spring Data Rest](https://github.com/spring-guides/tut-react-and-spring-data-rest) to the root of the repository.

## Table of Contents

1. [Overview](#Overview)

2. [First Part - #ca1-part1](#First-Part---#ca1-part1)
    1. [v1.0.0](#v1.0.0)
    2. [v1.2.0](#v1.2.0)

3. [Second Part - #ca1-part2](#Second-Part---#ca1-part2)
    1. [v1.3.0](#v1.3.0)
    2. [v1.3.1](#v1.3.1)

4. [Alternative solution](#Alternative-solution)
    1. [Subversion (SVN) vs Git](#Subversion-(SVN)-vs-Git)
    2. [Solving the assignment using Subversion](#Solving-the-assignment-using-Subversion)
    3. [Basic svn commands](#Basic-svn-commands)

## Overview
This assignment will be completed in two sections. The first part will focus on adding a new feature to our project, commiting, pushing and taging.
The second part will perform a similar task, but in a different branch, and then merge the changes into the main branch.
Within each part, the steps are separated into smaller tasks (within a pre-specified version tag), and each task will be completed using the command line.

## First Part - #ca1-part1
## v1.0.0
1. Start by creating an Issue on GitHub, with the title "Create CA1 package and move tut inside". This issue will be used to track the changes made in the first part of the assignment. This will be the first issue so it will be assigned the number 1. 

2. Open bash at the root of the repository. Alternatively the following command can be used from anywhere:
```bash
cd path/to/repository
```
3. Create a new folder called CA1:
```bash  
mkdir CA1
```
4. Move the tutorial application into the new CA1 folder. We could also copy it using the cp command, which would require us to delete the original folder afterwards. Using cp would require us to use -r (recursive) to copy the entire directory and its contents. The mv command is used to move the directory to the new location. The -r flag is not required as we are moving the entire directory and its contents. The command is as follows:
```bash  
mv tut-react-and-spring-data-rest/ CA1
```
5. Commit the recent changes using the following command (the -m flag is used to add a message to the commit). 
```bash 
git add .
git commit -m "Fixes #1. Created CA1 package and moved tut inside."
git push
```
The commit message is linked to the issue by including the issue number in the commit message. This is done by adding the issue number after the word "Fixes" in the commit message. This will automatically close the issue when the commit is pushed to the remote repository
6. Tag the version of the application using the following command:
```bash
git tag v1.0.0
```
The above command produces a simple tag without any additional information. To add a message to the tag, the -a flag can be used. This will open a text editor where a message can be added. The command is as follows:
```bash  
git tag -a v1.0.0 -m "First version of the application"
```
7. The tags are not automatically pushed with the rest of the code, a separate command must be used. To push the tag to the remote repository use the following command:
```bash
git push master v1.0.0
```
Alternatively, to push all tags created:
```bash  
git push --tags
```

## v1.2.0
1. Create a new issue on GitHub: #2 New feature - Add a new field to the application.


2. Add a new field to the Employee class called jobYears. This field should be an integer and should represent the number of years the employee has been working in the current job.
```java
private int jobYears;
```
Make sure to include this new field on the Employee class constructor, getters, setters, toString, equals and hashCode methods.  
3. Add unit tests for the new field in the EmployeeTest class. The tests should cover all methods and validations. The tests will be abstracted as they do not add much relevance to this assignment, but the full code can be viewed in the EmployeeTest.java file.
 

4. Add the new field to app.js file and to the DatabaseLoader class. The app.js file is a JavaScript file that contains the front-end code for the application. The DatabaseLoader class is a Java class that contains the back-end code for the application.
  

5. Commit the recent changes using the following command (Make sure to include the issue number in the commit message):
```bash
git add .
git commit -m "Fixes #2. Added new functionality. Included jobYears in the employee class. Also included code to validate employee creation parameters and respective tests."
git push
```
6. Tag the version of the application using the following command:
```bash
git tag v1.2.0
```
7. Push the tag to the remote repository using the following command:
```bash
git push master v1.2.0
```

8. Finally, to conclude the first part of the assignment. Add the tag ca1-part1 to the commit and push it to the remote repository. This will be used to mark the end of the first part of the assignment. The command is as follows:
```bash
git tag ca1-part1
git push master ca1-part1
```

## Second Part - #ca1-part2
## v1.3.0

Note: As per the instruction: "You should use the master branch to ”publish” the ”stable” versions of the Tutorial
React.js and Spring Data REST Application", all new features will be completed in a new branch, however these branches will not be formally pushed to the remote repository. Extra care will be made however to ensure the commit history is preserved when merging secondary branch with the master branch locally.
1. Create a new issue on GitHub: #3 New feature - Add a new field to the application.

2. Create a new branch called email-field using the following command:
```bash
git branch email-field
```
3. Switch to the new branch using the following command:
```bash
git checkout email-field
```
Alternatively, we can perform the following command, which will create the branch and switch to it in one step:
```bash
git checkout -b email-field
```
4. Ensure you are working on the email-field branch by using the following command (the active branch is highlighted with "*" next to it):
```bash
git branch
```
![img_5.png](img_5.png)

Alternatively we can also verify the branch we are currently on by looking at the top of the terminal, where the branch name is displayed (blue text).

Creating a branch is a way to work on a new feature without affecting the main branch. This allows us to work on the new feature without affecting the main branch, and then merge the new feature into the main branch when it is ready.

The decision to be made afterwards is wether to push the new branch to the remote repository or not. The pros of pushing a branch onto a remote repository are that other people may be able to access/check the work being done and also it may serve as backup. Alternatively, the branch does not get pushed immediately to the remote repository, which helps ensure the feature is fully developed and the remote repository remains clean.

For the purpose of this assignment we will not push the branch to the remote repository, but we will ensure the commit history is preserved when merging the email-field branch with the master branch locally. However, when the changes are pushed to the remote environment, special care will be made to ensure the commit history from the branch is accessible.

To verify that the branch is not indeed present on the remote repository:
```bash
git branch -r
```
5. Add a new field to the Employee class called email. This field should be a string and should represent the email address of the employee. Make sure to include this new field on the Employee class constructor, getters, setters, toString, equals and hashCode methods.


6. Add unit tests for the new field in the EmployeeTest class. The tests should cover all methods and validations.


7. Add and commit the new changes to the branch using the following command (Make sure to include the issue number in the commit message):
```bash 
git add .
git commit -m "Fixes #3 Create new funcionality - Add email field"
```

8. Prepare the merge of the email-field branch into the master branch. First, switch to the master branch using the following command:
```bash
git checkout master
```
9. Use the following command to merge the email-field branch into the master branch:
```bash
git merge email-field
```
This command is used to merge the email-field branch into the current branch with a non-fast-forward merge. This means that even if the merge can be done without creating a merge commit (which is the case with fast-forward merges), a merge commit will still be created to preserve the commit history of the email-field branch.

10. Unless more work is to be performed on the email-field branch, it can be deleted using the following command:
```bash
git branch -d email-field
```
The -d flag is considered a safe delete, as it is only possible to perform on a branch that was previously merged into the current branch. If the branch was not merged, the -D flag should be used instead to force the deletion of the branch. The -D is used to delete the branch regardless of its merge status. It is often used when a particular feature/fix is no longer needed.

11. Push the changes to the remote repository using the following command:
```bash
git push origin master
``` 

12. If you visit the remote project, you will see that the email-field branch is not present. This is because the branch was not pushed to the remote repository. However, the commit history from the email-field branch is preserved in the master branch.
![img_9.png](img_9.png)11. 

13. Tag the version of the application using the following command:
```bash
git tag v1.3.0
```
14. Push the tag to the remote repository using the following command:
```bash
git push master v1.3.0
```
## v1.3.1
1. Create an Issue on GitHub: #4 Fix - Ensure valid email.


2. Create and switch to another branch called fix-invalid-email using the following command:
```bash
git checkout -b fix-invalid-email
```

3. Add a validation to the email field in the Employee class. The validation should ensure that the email field is a valid email address. The validation should be done using a regular expression. The regular expression should be added to the Employee class and should be used to validate the email field in the constructor and the setter method. The regular expression should be added to the EmployeeTest class and should be used to test the email field in the EmployeeTest class.


4. Add and commit the new changes to the branch using the following command (Make sure to include the issue number in the commit message):
```bash
git add .
git commit -m "Fixes #4. Added validation to the email field in the Employee class. Also added tests to validate the email field."
```

5. Prepare the merge of the fix-invalid-email branch into the master branch. First, switch to the master branch using the following command:
```bash
git checkout master
```

6. Use the following command to merge the fix-invalid-email branch into the master branch:
```bash
git merge fix-invalid-email
```

7. Unless more work is to be performed on the fix-invalid-email branch, it can be deleted using the following command:
```bash
git branch -d fix-invalid-email
```

8. Push the changes to the remote repository using the following command:
```bash
git push origin master
```

Again, the branch will not be pushed to the remote repository, but the commit history from the fix-invalid-email branch is preserved in the master branch.


![img_10.png](img_10.png)

9. Tag the version of the application using the following command:
```bash
git tag v1.3.1
```

10. Push the tag to the remote repository using the following command:
```bash
git push master v1.3.1
```

11. Add the tag ca1-part2 to the commit and push it to the remote repository. This will be used to mark the end of the second part of the assignment. The command is as follows:
```bash
git tag ca1-part2
git push master ca1-part2
```

## Alternative solution
# Subversion (SVN) vs Git
Subversion (SVN), is one of the older version control systems and was quite popular before Git's rise to dominance. 
It is a centralized version control system with a single, central repository, while Git has a distributed
repository. Users interact with a centralized server for all operations, which can limit offline work and branching capabilities 
as it requires a continuous online connection. On SVN The only files that are stored locally are the ones that have been checked out, 
opposite to Git, where the entire repository is cloned locally.

When pushing, GIT utilizes a push command to send the changes to the remote repository, while SVN uses a commit command to send the changes to the central repository.
Both use DIFF to show the differences between the working copy and the repository.

Regarding branching and merging, Subversion has a different approach than Git. In Subversion, branches are created as directiories
inside the repository, while in Git, branches are created as pointers to a specific commit. This means that in Subversion, branches
are heavier and more expensive to create and manage, while in Git, branches are lightweight and easy to create and manage. This makes
Subversion less suitable for branching and merging, especially for larger projects. In Subversion, merging is also more complex and
error-prone than in Git, as it requires more manual intervention and tracking of changes.

In terms of performance, Subversion is slower than Git, especially for large 
repositories. This is because Subversion has to communicate with the central server for all operations, while Git can perform most 
operations locally. This means that in Subversion, all users interact with the same repository, while in Git, each user has their own 
local repository. This can make Subversion more suitable for smaller teams, while Git is better for larger teams and open-source projects. However,
Subversion has better support for large binary files, while Git is better for text files.

In terms of IDE integration, Subversion has quite a lot of tools and plugins for different IDEs, however, not as extensive as Git.

Ultimately, the choice between Git and Subversion depends on factors such as project size, team workflow, offline work requirements, 
and integration preferences. Both systems have proven track records in managing source code effectively, and the decision should align 
with the specific needs and preferences of the development team and organization.

# Solving the assignment using Subversion
Note: The following commands are used to perform the same tasks as the ones performed with Git, but using Subversion. 
The commands are similar, but the syntax and workflow are different. They are a bit more complex and less intuitive than Git,
but they can still be used to perform the same tasks.

1. To create a repository in Subversion, the svnadmin create command is used. This command creates a new, empty repository. The command is as follows:
```bash 
svnadmin create /path/to/repository
```
2. To import the tutorial application into the repository, the svn import command is used. This command commits an unversioned tree of files into a repository (and creates intermediate directories, if needed). The command is as follows:
```bash
svn import /path/to/tut-react-and-spring-data-rest file:///path/to/repository -m "Initial import"
```
3. To checkout a working copy from the repository, the svn checkout command is used. This command is sometimes shortened to svn co. The command is as follows:
```bash
svn checkout file:///path/to/repository /path/to/working/copy
```
4. After performing the changes, the svn commit command is used to send the changes back to the SVN server. The command is as follows:
```bash 
svn commit -m "Commit message"
```
5. To create a branch in Subversion, the svn copy command is used. This command copies a file or a directory in a working copy or in the repository. The command is as follows:
```bash
svn copy file:///path/to/repository/trunk file:///path/to/repository/branches/email-field -m "Creating email-field branch"
```
6. To merge the email-field branch into the trunk, the svn merge command is used. This command combines two different versions into your working copy. The command is as follows:
```bash
svn merge file:///path/to/repository/branches/email-field file:///path/to/repository/trunk
```
7. To delete the email-field branch, the svn delete command is used. This command deletes a file or a directory from the repository. The command is as follows:
```bash
svn delete file:///path/to/repository/branches/email-field -m "Deleting email-field branch"
```
8. To tag the version of the application, the svn copy command is used. This command copies a file or a directory in a working copy or in the repository. The command is as follows:
```bash
svn copy file:///path/to/repository/trunk file:///path/to/repository/tags/v1.3.0 -m "Tagging version 1.3.0"
```
9. To list the tags in the repository, the svn list command is used. This command shows a list of files in a repository without creating a working copy. The command is as follows:
```bash
svn list file:///path/to/repository/tags
```
10. To delete a tag from the repository, the svn delete command is used. This command deletes a file or a directory from the repository. The command is as follows:
```bash
svn delete file:///path/to/repository/tags/v1.3.0 -m "Deleting tag v1.3.0"
```

# Basic svn commands
**svn admincreate** - Creates a new, empty repository.

**svn import** - Commits an unversioned tree of files into a repository (and creates intermediate directories, if needed).

**svn checkout** - Checks out a working copy from the repository. This command is sometimes shortened to svn co.

**svn commit** - Sends your changes back to the SVN server.

**svn add** - Add a new file to the repository — but only after you've done a svn commit.

**svn delete** - Delete a file from your working copy of the repository.

**svn list** - See a list of files in a repository without creating a working copy.

**svn diff** - Reveals the differences between your working copy and the copy in the master SVN repository.

**svn status** - Prints the status of working copy files and directories.

**svn info** - Displays information about a local or remote item.

**svn log** - Shows log messages from the repository.

**svn move** - Moves a file from one directory to another (or renames it).

**svn merge** - Combines two different versions into your working copy.

**svn revert** - Reverts changes in your working copy, as well as property changes. For example, you can use svn revert to undo svn add.

**svn update** - Updates your working copy with changes from the repository.

**svn shelve** - Stores your changes without submitting them.

**svn help** - Provides a summary of available commands.
