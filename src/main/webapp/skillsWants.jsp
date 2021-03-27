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
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <h2>Skill Wants</h2>
    <form action="addSkillsHasWantsView" method="GET">
        <input type="hidden" name="viewType" value="wants">
        <input type="submit" value="Add New Skill Want">
    </form>
    <ul>
        <c:forEach var="skill" items="${skillsWants}">
            <li>${skill.skillName}<form><input type="hidden" name="${skill.skillId}"><input type="submit" value="Remove Skill?"></form></li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
