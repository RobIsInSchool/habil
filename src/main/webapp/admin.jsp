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
<c:out value="${head}" escapeXml="false"/>
<body>
<div class="container">
    <header>
        <h1>Welcome to the First Deploy of Habil!</h1>
        <c:out value="${nav}" escapeXml="false"/>
    </header>
    <main>
        <h2>Welcome, administrator</h2>
        <section id="users">
            <h3>Users</h3>
            <table>
                <th><td>Username</td><td>User ID</td><td>Email</td></th>
            <c:forEach var="user" items="${allUsers}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.userId}</td>
                    <td>${user.email}</td>
                    <td>
                        <form action="" method="post">
                            <input type="hidden" value="${user.userId}" name="userToDelete">
                            <input type="submit" value="Delete ${user.username}" name="submitDelete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
        </section>
    </main>
</div>
</body>
</html>