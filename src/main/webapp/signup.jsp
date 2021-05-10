<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:import var="footer" url="components/footer.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>
<script src="scripts/config.js"></script>
<script src="scripts/validateUsername.js"></script>
<script src="scripts/validateMatchedPasswords.js"></script>
<div class="container">
    <h1>Sign Up for Habil</h1>
    <form action="signupUser" method="POST" id="signupForm">
        <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" name="firstName" id="firstName" pattern="[a-zA-Z-]{1,50}" title="Letters or Dashes only" required>

        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" name="lastName" id="lastName" pattern="[a-zA-Z-]{1,50}" title="Letters or Dashes only" required>
        </div>

        <div class="form-group">
        <label for="username">Username - must be unique</label>
        <input type="text" class="form-control usernameInput" name="username" id="username" pattern="[a-zA-Z0-9]{1,50}" title="Letters or Numbers only" required>

        <label for="email">email</label>
        <input type="email" class="form-control" name="email" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter a valid email address" required>

        <label for="zip">Zip Code - for skill matching</label>
        <input type="text" class="form-control zipTextInput" name="zip" id="zip" pattern="[0-9]{5}" title="5-digit US zip" required>

        <label for="password">Password - letters, numbers, or ! @ # $ % ^ & * . ,</label>
        <input type="password" class="form-control" name="password" id="password" pattern="[a-zA-Z0-9!@#$%^&*.,]{8,80}" title="Passwords should be comprised of letters, numbers, or the following: !@#$%^&*.," required>

        <label for="passwordConfirm">Confirm Password</label>
        <input type="password" class="form-control" id="passwordConfirm" pattern="[a-zA-Z0-9!@#$%^&*.,]{8,80}" required>

        <input type="submit" id="submit" class="btn btn-info btn-sm submitButton" value="submit" title="Passwords should be comprised of letters, numbers, or the following: !@#$%^&*.,"  disabled>
        </div>
    </form>
    <br>
    <a href="/habil">Go Back</a>
    <c:out value="${footer}" escapeXml="false"/>
</div>
</body>
<script>initValidateZip();</script>
<script>initValidatePwdMatch();</script>
<script>initValidateUsername();</script>
</html>
