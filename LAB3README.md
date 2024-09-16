**Samhita Argula Lab 3 README**

1. When user input is all correct, the form passes validation and goes to confirmation page.

Pass input form
![image](https://github.com/user-attachments/assets/8b3c0ffe-0422-4787-b0c7-8be94e9c941a)

Confirmation page
![image](https://github.com/user-attachments/assets/051e11da-676b-487e-bff1-6d9ca6431a62)

2. When user gives wrong or missing inputs, the form throws errors upon submitting.

Bad input form
![image](https://github.com/user-attachments/assets/5f9f686e-73c5-44d4-8691-9086438ad233)

Form errors
![image](https://github.com/user-attachments/assets/ed3609f3-8137-4ad9-8c49-2308f700e2b4)

3. Write a brief summary paragraph to document:
    1. Your understanding of the difference between the forward and redirect operations.

        Redirect initiates a completely new request and response. It changes the URL, redirecting you to a new URL.
        Forward only passes control of the request to another servlet, sending it the request and response objects. It does not change the URL.

    2. How would you be validating user submissions without the Bean Validation API
    standard?

        We could use custom Java validations, like checking for null or blank values, email formats.
        Or use a validator through Resource Injection in servlet, like we did in this project.

    3. How do you think this approach would scale to a real application with 100's of
    entities?

        Writing manual validations for 100's of entities would lead to messy, unmanageable code and it would not be a best practice.

    4. Why didn't we need to include any additional dependencies (i.e. Bean Validation,
    JDBC) in this project?

        Since we created basic servlets and JSP files, we used a manual validator and DataSourceDefinition annotation for JDBC.
        We used resource injection instead of dependency injection.


4. Which of the above options require us to do "extra steps" as system or application administrators to handle this?

    (3) Configuring a Payara JDBC Connection Pool and JDBC Resource

    This would need admin operations to add resource or new connection pool.

Row added to database
![image](https://github.com/user-attachments/assets/838b6336-61ff-41a6-88f7-12231686f0c1)