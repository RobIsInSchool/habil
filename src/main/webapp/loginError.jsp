<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/5/21
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import var="footer" url="components/footer.jsp" />
<html>
<head>
    <title>Error</title>
</head>
<body>
<p>Login failed, please <a href="login.jsp"> try again</a>.</p>
<c:out value="${footer}" escapeXml="false"/>
</body>
</html>
