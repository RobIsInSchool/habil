<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:out value="${head}" escapeXml="false"/>
    <body>
    <div class="container">
        <header>
            <h1>Welcome to the First Deploy of Habil!</h1>
        </header>
        <p>The application is still in development, but you can click <a href="displayUsers">here</a> to display all users</p>
        <p>You can access the login page <a href="login.jsp">here.</a></p>
    </div>
    </body>
</html>
