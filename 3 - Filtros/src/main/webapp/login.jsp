<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="username">${usernameLabel}:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">${passwordLabel}:</label><br>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="${loginButton}">
    </form>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>
