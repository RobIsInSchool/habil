<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:out value="${head}" escapeXml="false"/>
    <body>
    <div class="container">
        <header>
            <h1>Welcome to the First Deploy of Habil!</h1>
        </header>

        <p>Log-in <a href="loginAction">here.</a></p>

        <aside>
            <p>Psst! Hey! Are you an admin? <a href="admin.jsp">Go here</a> </p>
        </aside>
    </div>
    </body>
</html>
