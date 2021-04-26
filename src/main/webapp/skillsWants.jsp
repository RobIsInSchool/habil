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
    <h2>Skills-Wants</h2>
    <form class="addSkillsForm" action="addSkillsHasWantsView" method="GET">
        <input type="hidden" name="viewType" value="wants">
        <input type="submit" class="btn btn-info btn-lg" value="Add New">
    </form>
    <ul class="list-group">
        <c:forEach var="skill" items="${skillsWants}">
            <li class="list-group-item">${skill.skillName}
                <form action="removeSkillAction" method="GET">
                    <input type="hidden" name="skillId" value="${skill.skillId}">
                    <input type="hidden" name="skillType" value="wants">
                    <input type="submit" class="btn btn-danger btn-sm" value="X">
                </form>
            </li>
        </c:forEach>
    </ul>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>
