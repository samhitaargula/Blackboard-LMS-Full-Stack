**Samhita Argula Final Project README**

# Project Summary
Welcome to Blackboard, a Learning Management System. Similar to the Canvas we all use, it's a platform for tracking learning. It has its main people entities of Teacher and Student, who each have their own roles like teaching lesson, grading and submitting assignments, respectively. Teachers are specialized in different subjects and teach different types of lessons. Students access these lessons, work on the assignments and submit them. Teachers finally grade the assignments.

1. Persistence Layer
- Designed a domain model with the main entities of Lesson, Teacher and Student. Mapped relationships between them with annotations like OneToMany, ManyToOne, etc. Used Bean API for constraints.
2. Service Layer:
- Added EJB components and controllers to handle all the business logic. Wrote logic for many operations like creation, updation, deletion and retrieval of lessons, assignments. Added welcome controllers to handle the home page logic for each user role.
3. Presentation Layer:
- Developed a dynamic UI with JSF and facelets. Designed separate dashboards for admins, teachers and students, providing different functionalities for each. Used Bootstrap styles, navigation and datatables to make up a good UI.
4. Security Layer:
- Created security users and groups to provide role based access. Each user or group only sees and accesses the data that they have permission to view. They can only make authorized, valid changes to the database. And an external user is not able to login.


# Design
In my Blackboard LMS, I have the following entities:
1.	Lesson
* This is the basic learning entity which represents learning content in the system. It is the smallest learning unit, comprising of a single topic.
* Attributes: id, lessondate, title, type.
* Relationships: A lesson is assigned to every student and taught by a teacher. Lesson and student relate in a many to many relationship. Each lesson is assigned to multiple students, who in turn have multiple lessons assigned to them.

2.	Student
* A learner entity who is enrolled in the LMS and consumes lessons and assignments.
* Attributes: id, name, email.
* Relationships: A student is assigned multiple lessons and thus can have multiple assignments.

3.	Teacher
* A teaching entity who creates and manages lessons and assignments in the LMS.
* Attributes: id, name, teacher_subject.
* Relationships: A teacher creates multiple lessons for their subject and creates assignments for their students.

4.	Assignment
* A learning entity which represents a task or project assigned to students as part of a lesson.
* Attributes: id, date, time, lesson_id, student_id, teacher_id.
* Relationships: A lesson can have multiple assignments; a student can be assigned multiple assignments and a teacher can generate multiple assignments.

5.	Subject
* Represents specialization of a teacher, what area a teacher is teaching to students.
* Attributes: id, name.
* Relationships: A teacher teaches one subject, in which their specialization is.

6.	Student_Lessons
* Represents the many-to-many relationship between students and lessons, recording the assignment of lessons to students.
* Attributes: id, lessondate, title, type.
* Relationships: Links students and lessons in a many-to-many relationship.

### Relationships
* Student M:N Lesson
* Assignment N:1 Lesson
* Assignment N:1 Student
* Teacher 1:1 Subject

### Entity Relationship Diagram
![Final ERD](https://github.com/user-attachments/assets/b20a91f2-6dd1-4056-9f9c-a962b17f006b)


## JPA inheritance strategy
Created 2 classes with @MappedSuperclass and added common properties like Id and Name. These classes can be extended in other classes and these properties can be used in them.
In our LMS, all our entities have an Id, so they inherit AbstractEntity class which has a reusable Id property. The Student and Teacher entities have a common Name property, so they inherit from an AbstractPerson class which inherits AbstractEntity and contains a reusable Name property.

## Functionality with roles & security

The primary roles in our LMS right now are Student and Teacher. It can also have an Admin role. These roles will have specific functionality and permissions.

Functionality for Students
* View Lessons: Students can view lessons and learn from them.
* View and Submit Assignments: Students can view assignments and upload their submissions.

Security
* Lessons – Read Only
* Assignments – Read, Write

Functionality for Teachers
* Create and Manage Lessons: Teachers can create, update, and delete lessons for the subjects they teach.
* Create and Assign Assignments: Teachers can create assignments and assign them to students.

Security
* Lessons – Read, Write, Delete
* Assignments – Read, Write, Delete

## Navigation flow
1. Login Page
- All users start here. If you have valid credentials, you are authorized against the database and are able to login and access LMS.

2. Sign up Page
- New students are able to register on the LMS with the sign up page. They have to simply input their name, email, provide a good username and password. They are then redirected to login page where they can enter their credentials and login.

3. Security Access Page
- If an unauthorized user tries to login to the LMS, the credentials fail to validate against the database, a security error is thrown and they are not let in.

4. Home Page
- Admins, teachers and students all have home pages where they first land after login. It just welcomes the user with the correct user details.

5. Admin Page
- If user is admin, they have access to Admin page where they can see lesson plans of all teachers and admin has access to every lesson.

6. Teacher Page
- If user is teacher, they have access to Teacher page where they can see their own lesson plans and assignments grading. They are able to run operations like view, edit, delete on all those records.

7. Student Page
- If user is teacher, they have access to Teacher page where they can see their assignments and submit them for grading. They are able to run operations like view, edit, delete on all those records, with the fields that they can access.

8. Lesson Page
- On the Lesson pages, Admin/Teacher can view, edit or delete lessons.

9. Assignment Page
- On the Assignment pages, Admin/Teacher can view, edit or delete assignments and grade them. Students can submit, edit assignments or delete their drafts.

10. Error page
- If an unauthorized action is attempted, the user is redirected to an error page.

11. Logout
- Logout button on all pages logs the user out and takes them back to the login page.

## Extra Credit
1. Incorporated an EJB Timer Service.
- Wrote pretty basic code. After sign up, a class SuccessTimer is called which creates a timer of 5 seconds until the Student is registered in the database. It then times out and gives back successful student user created message in logs.
```
@Stateless
public class SuccessTimer {

    @Resource
    private SessionContext context;

    public void createTimer() {
        context.getTimerService().createTimer(5000, "Student user created!");
    }

    @Timeout
    public void timeOutHandler(Timer timer) {
        System.out.println("TimeoutHandler : " + timer.getInfo());
        timer.cancel();
    }

}
```
![image](https://github.com/user-attachments/assets/27b6ccf7-1d3f-4667-b090-560966eb7d30)


# Requirements
- Apache Netbeans IDE 22
- Java version "17.0.12" (OpenJDK 17)
- JRE 1.8
- Payara Server 6.2
- MySQL Community Server 8.0
- MySQL Notifier 1.1.8
- MySQL Workbench 8.0

### Installation and Setup
1. Download GitHub code from https://github.com/itmd4515/itmd4515-f24-fp-samhitaargula
2. Open Netbeans and create a new project.
3. Clone repository from Team->Remote->Clone.
![image](https://github.com/user-attachments/assets/c9a0753c-1326-4ec6-a84b-4d58c26b9dd9)
4. Add repository URL and give user and password from personal access token.
![image](https://github.com/user-attachments/assets/92cd507b-97dc-4a04-8883-207f2d571630)
5. Add Payara Server from the sidebar, Services->Right click on Servers->Add Server
![image](https://github.com/user-attachments/assets/7fac5c80-ac37-4218-b31a-67a147fa8ea3)
6. Add the Payara server installation location, keep default domain, host and port.
![image](https://github.com/user-attachments/assets/d9767cea-a679-4119-83a4-280158e8d448)

### Compile
Once you finish installation, select the project, right click and select Clean and Build. This will compile the project and show compile time errors, if any.
![image](https://github.com/user-attachments/assets/cd774d2d-e65f-43fb-b70c-75f16e1fac8a)
![image](https://github.com/user-attachments/assets/8e6cabca-8ff6-402c-9ef6-adeac09a2068)

### Runtime
To run, you can right click on project once again and select run or click on the run button from the toolbar. This will run the project and give you runtime and Payara logs. This will automatically open http://localhost:8080/sargula-fp/ in your browser, for a successful run.
![image](https://github.com/user-attachments/assets/2a456321-0284-4d99-97b8-5050ead5f409)
![image](https://github.com/user-attachments/assets/3f6e82c0-635a-4dc8-89bb-1e8e708cde89)

### Database
To check database changes, you can use MySQL Workbench or CLI.
1. In MySQL Workbench, click on New connection. Setup database itmd4515 with the same username and password we have been using.
<img width="960" alt="image" src="https://github.com/user-attachments/assets/8eeac420-94bd-4b5a-bd37-bf65aee266c5">
2. You can view and query all the tables here.

![image](https://github.com/user-attachments/assets/dcaa3ff9-4944-44e6-a92b-12952dbe2bee)


# Screen Captures
<a name="signup">Signup</a>

![image](https://github.com/user-attachments/assets/8eb109ec-56ce-45a6-9719-4e1e4cd9451c)
![image](https://github.com/user-attachments/assets/ea004ffb-ad51-4ce3-b77e-34aa739fb9d9)

<a name="admin">Admin flow</a>

![image](https://github.com/user-attachments/assets/d2b9f7cd-c6fa-4d75-981e-7c157782436f)
![image](https://github.com/user-attachments/assets/504772da-960d-42a1-85b4-6e1fd04cf916)
![image](https://github.com/user-attachments/assets/651b20f5-39f3-4636-8bcc-5f992acf4116)

<a name="teacher">Teacher1 flow</a>

![image](https://github.com/user-attachments/assets/8c6926b8-453f-476c-88f9-0c21de6e0855)
![image](https://github.com/user-attachments/assets/aaf052ba-5083-422a-b65a-c639cf120b3f)
![image](https://github.com/user-attachments/assets/b78089ff-2b5c-45fd-8e03-4940f62155b2)

Teacher Lessons table

![image](https://github.com/user-attachments/assets/8695b17e-4a95-44e8-bff4-d970978882b9)
![image](https://github.com/user-attachments/assets/5ca033df-2d4a-4b9a-8961-aecf9ad58508)
![image](https://github.com/user-attachments/assets/17a7213d-3065-473f-9df0-8c5b23f6157d)
![image](https://github.com/user-attachments/assets/11e54fe5-4e32-47c0-858b-16b21063514e)
![image](https://github.com/user-attachments/assets/bb5a8ae3-deae-4d13-96ba-6361a32e595c)
![image](https://github.com/user-attachments/assets/a4e79ae9-be2a-486e-8f24-8f56394e4afd)

Teacher Assignments table

![image](https://github.com/user-attachments/assets/62ef4851-b2bf-4721-914c-854ff7448c2c)
![image](https://github.com/user-attachments/assets/16135379-2a08-4193-a0a3-469449016e22)
![image](https://github.com/user-attachments/assets/5fd7a273-213e-418c-b9c2-12968e01ed74)
![image](https://github.com/user-attachments/assets/79c6fad6-b33c-4f42-9ef9-a912fddc50b8)
![image](https://github.com/user-attachments/assets/4579d40e-49a4-4454-ac90-72f2dce8115e)
![image](https://github.com/user-attachments/assets/a4e1b009-5d95-4816-ad33-17f3acd69d37)
![image](https://github.com/user-attachments/assets/e36fd4d2-603a-40d9-a101-f7e697bbc72d)
![image](https://github.com/user-attachments/assets/76cda8ba-e9dd-4f7a-80a7-07a8e0b043d0)

<a name="student">Student1 flow</a>

![image](https://github.com/user-attachments/assets/ff60f71e-92f3-4ae5-b530-59248afeae38)
![image](https://github.com/user-attachments/assets/f59fcc42-7c36-4072-8104-795cce1b3b13)
![image](https://github.com/user-attachments/assets/adcb1e27-2679-4e6e-b573-30915816b980)
![image](https://github.com/user-attachments/assets/c7222bd4-93bc-4672-a2da-e47f096eae6f)
![image](https://github.com/user-attachments/assets/e4cd51f4-2d8d-43d2-8623-fb300c333195)
![image](https://github.com/user-attachments/assets/0a81b34f-61d8-41e5-850b-828a3917c0e8)
![image](https://github.com/user-attachments/assets/73f191b3-73df-40be-bde9-0cc5a1a9cc0f)
![image](https://github.com/user-attachments/assets/4f9e94c7-f16e-4ed4-987f-35a15e71f0da)
![image](https://github.com/user-attachments/assets/7947c70a-7409-407e-8f35-a1b80e2ff725)

<a name="teacher2">Teacher2 flow</a>

![image](https://github.com/user-attachments/assets/bc5d5885-7bbf-43f4-952c-3a2ad606eb1b)
![image](https://github.com/user-attachments/assets/34762090-db86-4462-9046-a211c15d2891)
![image](https://github.com/user-attachments/assets/62bf26b7-b6e3-4663-a6d2-8ad003bd2b25)
![image](https://github.com/user-attachments/assets/ea8e1e47-c305-43d2-a5a9-c3a8421bd324)
![image](https://github.com/user-attachments/assets/1a86e988-be4f-4151-bc0e-81275d3ac821)
![image](https://github.com/user-attachments/assets/ba83c4c0-85db-4ba8-849b-67c848edb40a)

<a name="student2">Student2 flow</a>

![image](https://github.com/user-attachments/assets/934e73e9-7668-4c8a-a023-dca0751c7025)
![image](https://github.com/user-attachments/assets/8b3f4ad4-f851-44c8-b565-953732341ae1)
![image](https://github.com/user-attachments/assets/32de9147-26d4-47a7-8647-b02eba6c7763)

<a name="database">Database</a>

![image](https://github.com/user-attachments/assets/a609518a-935c-42ad-ae7c-413960494805)
![image](https://github.com/user-attachments/assets/0d6d9a1e-db53-41cf-baf7-05fc9a79d4ed)


# Test Script/Expected Results
[Signup](#signup)
1. Click on New Student Register link on login page.
2. Give your details name, email, username and password and click on Register.
3. You can now login with your new credentials!

[Admin flow](#admin)
1. Login as user admin with password admin. You land on Admin home page.
2. Navigate to Admin access page to see an overall list of all teacher lesson plans.
3. Make note that only admin tables have a different dark shading in this app.
4. Logout.

[Teacher flow](#teacher)
1. Login as user teacher1 with password teacher1. You land on Teacher home page.
2. Since teacher1 is an admin, navigate to Admin Access to see the same admin page with customized teacher details on top.
3. Navigate to Teacher access. The first table is the Lessons taught by the teacher.
4. Click Add New Lesson. Give Lesson Name as Sample, today's date and keep it as INTRO default or any type, and click on Create Lesson.
5. You will now see the new Lesson in the list. Click on Details and you'll see the details we have given.
6. Go back to the table and click on Edit. Change the Lesson name to XYZ now and click Edit. You can see the changed Lesson name in the table and if you changed any other details.
7. Scroll down to second table, which is the Assignments given by the teacher. Click on Grade Assignment.
8. Give Title: Sample Assign, Lesson: XYZ, current time and date, Student: Java Student, Grade: A and click Submit.
9. You will now see the new Assignment in the list. Click on Details and you'll see the details we have given.
10. Go back to the table and click on Edit. Change the Assignment name to WXYZ now and click Edit. You can see the changed Assignment name in the table and if you changed any other details.
11. Now I don't like these gibberish names, so I want them gone. So if I scroll up to Lessons table, click Delete next to the XYZ lesson we created and delete lesson.
12. You see that now the Lesson and the related Assignment are both deleted from their respective tables.
13. Logout

[Student flow](#student)
1. Login as user student1 with password student1. You land on Student home page.
2. Navigate to Student access. The table shown are the assignments assigned to the student, drafts and graded ones.
3. Click on Submit Assignment. Give Assignment Title: New Assign, Lesson: Intro to Java, current date and time for submission, click on Submit.
4. You will now see the new draft Assignment in the list. Click on Details and you'll see the details we have given.
5. Go back to the table and click on Edit. Change the Title to New Assignment and Lesson to Java History, select current time and click Edit. You can see the changed Assignment details in the table.
6. Lastly let's delete the old Java History Assignment with Grade B. After deletion it's gone from the table and now student waits for new grading.
7. Logout.
