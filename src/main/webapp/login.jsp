<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/5/21
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
<form action="j_security_check" method="post">
    <table>
        <tr>
            <td>User Name: <input type="text" name="j_username"></td>
        </tr>
        <tr>
            <td>Password: <input type="text" name="j_password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Log In"> </td>
        </tr>
    </table>
</form>

</body>
</html>
