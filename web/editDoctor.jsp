<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="NewEditDoctorServlet" method="post">

    <input type="hidden" name="id" value="${param.id}">
    <hr>
    Login: <input name="login" value="${param.login}">
    <hr>
    Password: <input type="password" name="password">
    <hr>
    Name: <input name="name" value="${param.name}">
    <hr>
    Category: <input name="category" value="${param.category}">
    <hr>
    <input type="submit" value="Edit">

</form>
</body>
</html>
