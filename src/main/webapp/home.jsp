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
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
<div class="container">
    <header>
        <h1>Welcome to Habil!</h1>
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <main>
        <h2>Welcome, ${user.username}</h2>
        <p>Currently at zip code ${user.zip}</p>
        <button id="zipChange">Change zip?</button>
        <div id="zipChangeInputArea"></div>
    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>