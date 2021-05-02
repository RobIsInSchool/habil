<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
    <body>
    <div class="container" id="indexContainer">
        <header>
            <h1>Welcome to Habil!</h1>
        </header>

        <main>
            <div id="welcomeButtons">
                <div class="buttonContainer">
                    <p>New to Habil?</p>
                    <a href="signup.jsp"><button class="btn btn-info btn-lg">Sign Up Here</button></a>
                </div>
                <div class="buttonContainer">
                    <p>Already a User?</p>
                    <a href="loginAction"><button class="btn btn-info btn-lg">Log In Here</button></a>
                </div>
            </div>
        </main>

        <c:out value="${footer}" escapeXml="false"/>
        <aside>
            <p>Psst! Hey! Are you an admin? <a href="adminHome">Go here</a> </p>
        </aside>
    </div>
    </body>
</html>
