<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
    <body>
    <div class="container">
        <header>
            <h1>Welcome to Habil!</h1>
        </header>

        <p>Log-in <a href="loginAction">here.</a></p>
        <p><a href="signup.jsp">Sign up here</a></p>

        <aside>
            <p>Psst! Hey! Are you an admin? <a href="adminLoginAction">Go here</a> </p>
        </aside>
        <c:out value="${footer}" escapeXml="false"/>
    </div>
    </body>
</html>
