<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/4/21
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Habil</title>
</head>
<body>

<table>
    <thead>
    <tr>
        <th>User ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Roles</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="result" items="${users}">
        <tr>
            <td>${result.userId}</td>
            <td>${result.firstName}</td>
            <td>${result.lastName}</td>
            <td>${result.email}</td>
            <td>
                <ul>
                <c:forEach var="role" items="${result.userRoles}">
                    <li>${role.roleName}</li>
                </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>