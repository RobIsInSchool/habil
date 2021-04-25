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
        <form action="addMainSkill" method="POST">
            <label for="skillToAdd">Add Skill:</label>
            <input type="text" name="skillToAdd" id="skillToAdd">
            <input type="submit" name="submit" value="Add Skill">
        </form>
        <ul>
        <c:forEach var="skill" items="${allSkills}">
            <li>${skill.skillName}</li>
        </c:forEach>
        </ul>
    </main>
</div>
</body>
</html>