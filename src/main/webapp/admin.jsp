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
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
<div class="container">
    <header>
        <h1>Welcome, administrator</h1>
        <p><a href="logout">Log Out</a></p>
    </header>
    <main>
        <section id="users">
            <h3>Users</h3>
            <table>
                <tr><th>Username</th><th>User ID</th><th>Email</th><th>Delete</th></tr>
            <c:forEach var="user" items="${allUsers}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.userId}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="deleteUser" method="post">
                            <input type="hidden" value="${user.userId}" name="userToDelete">
                            <input type="hidden" value="${user.username}" name="username">
                            <input type="submit" value="Delete ${user.username}" name="submitUserDelete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </section>
        <section id="skills">
            <h3>Skills</h3>
            <table>
                <tr><th>Skill Name</th><th>Skill ID</th><th>Delete</th></tr>
                <c:forEach var="skill" items="${allSkills}">
                    <tr>
                        <td>${skill.skillName}</td>
                        <td>${skill.skillId}</td>
                        <td>
                            <form action="deleteSkill" method="post">
                                <input type="hidden" value="${skill.skillId}" name="skillId">
                                <input type="hidden" value="${skill.skillName}" name="skillName">
                                <input type="submit" class="btn btn-success btn-sm"  value="Delete ${skill.skillName}" name="submitSkillDelete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>