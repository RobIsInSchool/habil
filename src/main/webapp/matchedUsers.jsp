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
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Zip</th>
                <th scope="col">Has</th>
                <th scope="col">Wants</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="otherUser" items="${matchedUsers}">
                <tr>
                    <td>
                            ${otherUser.username}
                        <br>
                        <c:forEach var="lesson" items="${userLessons}">
                            <c:if test = "${otherUser.userId == lesson.key}">
                                <p>You've taken ${lesson.value}
                                    <c:choose>
                                        <c:when test="${lesson.value == 1}">
                                            lesson
                                        </c:when>
                                        <c:otherwise>
                                            lessons
                                        </c:otherwise>
                                    </c:choose>
                                from this person</p>
                            </c:if>
                        </c:forEach>
                            <br>
                            <form action="addLesson" method="post">
                                <input type="hidden" name="teacher" value="${otherUser.userId}">
                                <input type="hidden" name="student" value="${user.userId}">
                                <input type="submit" class="btn btn-info btn-sm" value="I took a new lesson from this person">
                            </form>
                    </td>
                    <td>
                        ${otherUser.username}${otherUser.userId}@habil.com
                    </td>
                    <td>
                            ${otherUser.zip}
                    </td>
                    <td>
                            <ul class="matchedSkillsList">
                            <c:forEach var="skill" items="${otherUser.skillsHas}">
                                <li>${skill.skillName}</li>
                            </c:forEach>
                            </ul>
                    </td>
                    <td>
                        <ul class="matchedSkillsList">
                            <c:forEach var="skill" items="${otherUser.skillsWants}">
                                <li>${skill.skillName}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
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