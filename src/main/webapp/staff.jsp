<%-- 
    Document   : staff
    Created on : Sep 13, 2024, 10:09:55 PM
    Author     : sargula
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a New Staff</title>
    </head>
    <body>
        <h1>New Staff Form</h1>

    <c:if test="${not empty requestScope.violations}">
        <h2>Please fix the following errors with your input:</h2>
        <table border=1>
            <tr>
                <th>Field</th>
                <th>Error</th>
            </tr>
            <c:forEach var="v" items="${requestScope.violations}">
                <tr>
                    <td><c:out value="${v.propertyPath}" /></td>
                <td><c:out value="${v.message}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br/>

    <form method="post" action="/sargula-fp/staff">
        <div>
            <label for="staffId">Staff ID</label>
            <input type="text" id="staffId" name="staffId" value="${requestScope.staff.id}" />
        </div>

        <div>
            <label for="firstName">Staff First Name</label>
            <input type="text" id="firstName" name="firstName" value="${requestScope.staff.firstName}" />
        </div>

        <div>
            <label for="lastName">Staff Last Name</label>
            <input type="text" id="lastName" name="lastName" value="${requestScope.staff.lastName}" />
        </div>

        <div>
            <label for="addressId">Staff Address ID</label>
            <input type="text" id="addressId" name="addressId" value="${requestScope.staff.addressId}"/>
        </div>

        <div>
            <label for="email">Staff Email Address</label>
            <input type="email" id="email" name="email" value="${requestScope.staff.email}" />
        </div>

        <div>
            <label for="storeId">Staff Store ID</label>
            <select id="storeId" name="storeId">
                <option value="1" ${requestScope.staff.storeId == 1 ? 'selected' : ''}>Store #1</option>
                <option value="2" ${requestScope.staff.storeId == 2 ? 'selected' : ''}>Store #2</option>
            </select>
        </div>

        <div>
            <label for="activeInd">Active?</label>
            <input type="checkbox" id="activeInd" name="activeInd" ${requestScope.staff.active ? 'checked' : ''} />
        </div>

        <div>
            <label for="username">Staff Username</label>
            <input type="text" id="username" name="username" value="${requestScope.staff.username}" />
        </div>

        <div>
            <label for="password">Staff Password</label>
            <input type="password" id="password" name="password" value="${requestScope.staff.password}" />
        </div>

        <div>
            <label for="lastUpdate">Staff Last Update Date</label>
            <input type="date" id="lastUpdate" name="lastUpdate" value="${requestScope.staff.lastUpdate}" />
        </div>

        <button type="submit">Create Staff</button>
    </form>
</body>
</html>
