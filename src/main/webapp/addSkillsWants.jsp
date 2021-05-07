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
    <h2>Adding Skill-Want...</h2>
    <a href="addMainSkill?linkBackType=wants"><button class="btn btn-info">Don't see the skill you want? Add it to our main list!</button></a>

    <table class="table table-hover" id="skillsTable">
        <thead>
        <tr>
            <th scope="col">Available Skills:</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="skill" items="${allSkills}">
            <tr>
                <td scope="row">${skill.skillName}
                    <form action="addSkillAction" method="POST">
                        <input type="hidden" name="skillId" value="${skill.skillId}">
                        <input type="hidden" name="skillType" value="wants">
                        <input type="submit" class="btn btn-info btn-sm"  value="Add Skill-Want">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <ul class="list-group">--%>
<%--        <c:forEach var="skill" items="${allSkills}">--%>
<%--            <li class="list-group-item"><p>${skill.skillName}</p>--%>
<%--                <form action="addSkillAction" method="POST">--%>
<%--                    <input type="hidden" name="skillId" value="${skill.skillId}">--%>
<%--                    <input type="hidden" name="skillType" value="wants">--%>
<%--                    <input type="submit" class="btn btn-info btn-sm" value="Add Skill-Want">--%>
<%--                </form>--%>
<%--            </li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>
