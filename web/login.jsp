<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>

<body>

<%--@elvariable id="loginResult" type="java.lang.String"--%>
<c:if test="${not empty loginResult}">
    <c:out value="${loginResult}">
    </c:out>
</c:if>

<form action="controller" method="post">

    <input type="hidden" name="command" value="login"/>

    Login: <input name="login" required>
    <hr/>
    Password: <input type="password" name="password" required>
    <hr/>
    <input type="submit" value="Login">
</form>

</body>
</html>
