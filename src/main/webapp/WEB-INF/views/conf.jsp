<%-- 
Document   : conf
Created on : Sep 13, 2024, 11:36:02 PM
Author     : sargula
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation Page</title>
    </head>
    <body>
        <h1>Confirmation Page</h1>
        <h2>Staff is created!</h2>

        <ul>
            <li>Staff Id: ${requestScope.staff.id}</li>
            <li>Staff First Name: ${requestScope.staff.firstName}</li>
            <li>Staff Last Name: ${requestScope.staff.lastName}</li>
            <li>Staff Email: ${requestScope.staff.email}</li>
            <li>Staff Address Id: ${requestScope.staff.addressId}</li>
            <li>Staff Store Id: ${requestScope.staff.storeId}</li>
            <li>Staff Active: ${requestScope.staff.active}</li>
            <li>Staff Username: ${requestScope.staff.username}</li>
            <li>Staff Password: ${requestScope.staff.password}</li>
            <li>Staff Last Update Date: ${requestScope.staff.lastUpdate}</li>
        </ul>
    </body>
</html>
