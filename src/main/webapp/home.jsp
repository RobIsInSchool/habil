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
<script src="scripts/changeZip.js"></script>
<script src="scripts/validateZip.js"></script>
<script src="scripts/config.js"></script>
<div class="container">
    <header>
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <main>
        <h2>Welcome, ${user.username}</h2>
        <p id="zip" style="display:inline-block">Currently at zip code ${user.zip}</p>
        <button class="btn btn-info btn-sm" id="zipChange">Change zip?</button>
        <div id="zipChangeInputArea"></div>

        <p>You've TAUGHT: ${numTaught} lessons</p>
        <p>You've TAKEN: ${numTaken} lessons</p>

        <c:choose>
            <c:when test="${(numTaught == 0) && (numTaken > 0)}">
                <p>You haven't taught any lessons yet. Time to start giving back!</p>
            </c:when>
            <c:when test="${(numTaught == 0) && (numTaken == 0)}">
                <p>Welcome to Habil!</p>
            </c:when>
            <c:when test="${(numTaught > 0) && (numTaken == 0)}">
                <p>You haven't taken any lessons yet! Don't be afraid, it's why you're here!</p>
            </c:when>
            <c:otherwise>
                <h3>Your lesson ratio (taught/taken): ${lessonRatio}</h3>
                <c:choose>
                    <c:when test="${lessonRatio == 1.00}">
                        <p>You have a perfect ratio! Keep it up!</p>
                    </c:when>
                    <c:when test="${lessonRatio > 1}">
                        <p>You have taught more lessons than you've taken! That's awesome!</p>
                    </c:when>
                    <c:otherwise>
                        <p>It seems you've taken more lessons than you've taught... Consider giving back to the community!</p>
                    </c:otherwise>
                </c:choose>

            </c:otherwise>
        </c:choose>


    </main>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
<script>initChange();</script>
</html>