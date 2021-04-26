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
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <main>
        <table>
            <tr>
                <th>Username</th>
                <th>Zip</th>
                <th>Has</th>
                <th>Wants</th>
            </tr>
            <c:forEach var="user" items="${matchedUsers}">
                <tr>
                    <td>
                            ${user.username}
                    </td>
                    <td>
                            ${user.zip}
                    </td>
                    <td>
                            <ul>
                            <c:forEach var="skill" items="${user.skillsHas}">
                                <li>${skill.skillName}</li>
                            </c:forEach>
                            </ul>
                    </td>
                    <td>
                        <ul>
                            <c:forEach var="skill" items="${user.skillsWants}">
                                <li>${skill.skillName}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
<%--        <ul>--%>
<%--            <c:forEach var="user" items="${matchedUsers}">--%>
<%--                <li>${user.firstName} -> ${user.zip}</li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>