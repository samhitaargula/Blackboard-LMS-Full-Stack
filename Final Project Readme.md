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

### ERD diagram
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


