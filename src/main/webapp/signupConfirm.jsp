<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
    <p>You have successfully signed up as user ${userName}. Go <a href="loginAction">here</a> to log in.</p>
    <c:out value="${footer}" escapeXml="false"/>
</body>
</html>
