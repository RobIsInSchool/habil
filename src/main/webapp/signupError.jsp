<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/10/21
  Time: 10:49 AM
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
    <h1>Oops!</h1>
    <p>Sorry! There was an error signing you up. It's possible the email you entered is already in use. Please try and <a href="signup.jsp">sign up again.</a></p>
</body>
</html>
