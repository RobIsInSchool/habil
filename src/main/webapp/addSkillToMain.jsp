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
<script>
    $(document).ready( function () {
        $('#skillsTable').DataTable();
    } );
</script>
<body>
<div class="container">
    <header>
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <h2>Add skill to our Main List</h2>
    <main>
        <a href="addSkillsHasWantsView?viewType=${linkBackType}">Back to Skills-${linkBackType}</a>
        <form action="addMainSkill" method="POST">
            <input type="text" name="skillToAdd" id="skillToAdd">
            <input type="submit" class="btn btn-info btn-sm" name="submit" value="Add Skill">
        </form>

        <table class="table table-hover" id="skillsTable">

            <thead>
                <tr>
                    <th scope="col">Habil Skills List:</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="skill" items="${allSkills}">
                <tr>
                    <td scope="row">${skill.skillName}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>

<%--        <ul class="list-group">--%>
<%--        <c:forEach var="skill" items="${allSkills}">--%>
<%--            <li class="list-group-item">${skill.skillName}</li>--%>
<%--        </c:forEach>--%>
<%--        </ul>--%>
    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>