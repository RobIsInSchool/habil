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
<c:import var="head" url="components/head.jsp" />
<c:import var="nav" url="components/navigation.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
<div class="container">
    <header>
        <h1>Welcome to the First Deploy of Habil!</h1>
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <main>
        <ul>
            <c:forEach var="user" items="${matchedUsers}">
                <li>${user.firstName} -> ${user.zip}</li>
            </c:forEach>
        </ul>
    </main>
</div>
</body>
</html>