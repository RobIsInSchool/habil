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
<script>
    $(document).ready( function () {
        $('#usersTable').DataTable();
        $('#skillsTable').DataTable();
    } );
</script>
<body>
<div class="container">
    <header>
        <c:choose>
            <c:when test="${isOwner}">
                <h1>Welcome, Omnipotent Overlord</h1>
            </c:when>
            <c:otherwise>
                <h1>Welcome, Administrator</h1>
            </c:otherwise>
        </c:choose>
        <p><a href="logout">Log Out</a></p>
    </header>
    <main>
        <section id="users">
            <h3>Users</h3>
            <table class="table table-hover" id="usersTable">
                <thead>
                <tr><th scope="col">Username</th><th scope="col">User ID</th><th scope="col">Email</th><th scope="col">Delete</th></tr>
                </thead>
                <tbody>
            <c:forEach var="user" items="${allUsers}">
                <tr>
                    <td scope="row">
                        <p>
                            ${user.username}
                        </p>
                        <c:set var="isAdmin" scope="page" value="${false}"/>
                        <c:forEach var="role" items="${user.userRoles}">
                            <c:if test="${role.roleName.equals('admin')}">
                                <c:set var="isAdmin" scope="page" value="${true}"/>
                                <c:set var="roleId" scope="page" value="${role.roleId}"/>
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${isAdmin}">
                                <p> (Admin)</p>
                                <c:if test="${isOwner}">
                                    <a href="adminAddRemove?action=remove&userId=${user.userId}&role=${roleId}">Remove admin privileges</a>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <a href="adminAddRemove?action=add&userId=${user.userId}&role=null">Add admin privileges</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${user.userId}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="deleteUser" method="post">
                            <input type="hidden" value="${user.userId}" name="userToDelete">
                            <input type="hidden" value="${user.username}" name="username">
                            <input type="submit" class="btn btn-info btn-sm" value="Delete ${user.username}" name="submitUserDelete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
                </tbody>
            </table>
        </section>
        <section id="skills">
            <h3>Skills</h3>
            <table class="table table-hover" id="skillsTable">
                <thead>
                <tr><th scope="col">Skill Name</th><th scope="col">Skill ID</th><th scope="col">Delete</th></tr>
                </thead>
                <tbody>
                <c:forEach var="skill" items="${allSkills}">
                    <tr>
                        <td scope="row">${skill.skillName}</td>
                        <td>${skill.skillId}</td>
                        <td>
                            <form action="deleteSkill" method="post">
                                <input type="hidden" value="${skill.skillId}" name="skillId">
                                <input type="hidden" value="${skill.skillName}" name="skillName">
                                <input type="submit" class="btn btn-info btn-sm"  value="Delete ${skill.skillName}" name="submitSkillDelete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
</html>