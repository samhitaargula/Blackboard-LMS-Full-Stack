**Samhita Argula Lab 4 README**

Q. Why are these dependencies required if we already have the javaee-api in our pom.xml?

  Javaee API may already have the interfaces we need to compile, but we need to add dependencies to use them at runtime or for testing.
<br/><br/>

1. I have chosen the **Education/Learning domain** to build a Learning Management System like Canvas. I felt this would be a good domain for final project and I can get ideas of the visual structure from our Canvas.


2. Write a second paragraph answering the following questions: There is only one entity required for Lab 4, but what other entities from your business domain can you think of? How might they relate to one another? You can answer this in narrative form, or you can answer it with a database diagram. One of your midterm questions will be very similar, about the design of your FP, so this is to help get you started early.


**JPA test class**
![image](https://github.com/user-attachments/assets/66ba08ca-3337-4fa6-99f9-cad78d45b3cb)


```
cd C:\Users\nothi\OneDrive\Documents\NetBeansProjects\sargula-fp; "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.12.7-hotspot" cmd /c "\"C:\\Program Files\\NetBeans-22\\netbeans\\java\\maven\\bin\\mvn.cmd\" \"-Dmaven.ext.class.path=C:\\Program Files\\NetBeans-22\\netbeans\\java\\maven-nblib\\netbeans-eventspy.jar\" --no-transfer-progress test"
Scanning for projects...

--------------< edu.iit.sat.itmd4515.sargula:sargula-fp >---------------
Building sargula-fp-1.0-SNAPSHOT 1.0-SNAPSHOT
  from pom.xml
--------------------------------[ war ]---------------------------------

--- resources:3.3.1:resources (default-resources) @ sargula-fp ---
Copying 1 resource from src\main\resources to target\classes

--- compiler:3.8.1:compile (default-compile) @ sargula-fp ---
Nothing to compile - all classes are up to date

--- resources:3.3.1:testResources (default-testResources) @ sargula-fp ---
skip non existing resourceDirectory C:\Users\nothi\OneDrive\Documents\NetBeansProjects\sargula-fp\src\test\resources

--- compiler:3.8.1:testCompile (default-testCompile) @ sargula-fp ---
Changes detected - recompiling the module!
Compiling 1 source file to C:\Users\nothi\OneDrive\Documents\NetBeansProjects\sargula-fp\target\test-classes

--- surefire:3.2.2:test (default-test) @ sargula-fp ---
Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running edu.iit.sat.itmd4515.sargula.LessonJPATest
Sep 21, 2024 3:17:05 PM org.hibernate.validator.internal.util.Version <clinit>
INFO: HV000001: Hibernate Validator 8.0.1.Final
[EL Info]: 2024-09-21 15:17:05.45--ServerSession(1970073944)--EclipseLink, version: Eclipse Persistence Services - 4.0.4.v202407190748-059428cdd2583c46f1f3e50d235854840a6fa9a7
Before each - create lesson	Test Data
Read lesson 	Lesson{id=2, title=Lesson 2 - OOPs concepts, lessonDate=2024-09-21, type=INTRO}
Before each - delete lesson	Test Data
Before each - create lesson	Test Data
Update lesson 	Lesson{id=3, title=Test Data, lessonDate=2024-09-19, type=INTRO}
Before each - delete lesson	Test Data
Before each - create lesson	Test Data
Create lesson 	Lesson{id=5, title=Lesson 1 - Introduction, lessonDate=2024-09-19, type=INTRO}
Before each - delete lesson	Test Data
Before each - create lesson	Test Data
Delete lesson 	Lesson{id=2, title=Lesson 2 - OOPs concepts, lessonDate=2024-09-21, type=INTRO}
Before each - delete lesson	Test Data
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.915 s -- in edu.iit.sat.itmd4515.sargula.LessonJPATest

Results:

Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  5.922 s
Finished at: 2024-09-21T15:17:06-05:00
------------------------------------------------------------------------
```
