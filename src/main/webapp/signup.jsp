<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:import var="head" url="components/head.jsp" />
<c:out value="${head}" escapeXml="false"/>
<body>

    <form action="signupUser" method="POST">
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" id="firstName" pattern="[a-zA-Z]{1,50}" required>

        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" id="lastName" pattern="[a-zA-Z]{1,50}" required>

        <label for="username">Username - must be unique</label>
        <input type="text" name="username" id="username" pattern="[a-zA-Z]{1,50}" required>

        <label for="email">email</label>
        <input type="email" name="email" id="email" required>

        <label for="zip">Zip Code - for skill matching</label>
        <input type="text" name="zip" id="zip" pattern="[0-9]{5}" required>

        <label for="password">Password - at least 8 characters, but no more than 80</label>
        <input type="text" name="password" id="password" pattern="[a-zA-Z0-9!@#$%^&*.,]{8,80}" required>

        <label for="passwordConfirm">Confirm Password</label>
        <input type="text" id="passwordConfirm" pattern="[a-zA-Z0-9!@#$%^&*.,]{8,80}" required>

        <input type="submit" value="submit">
    </form>

</body>
</html>