<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="editDoctorServlet" method="post">

    <input type="hidden" name="id" value="${doctor.id}">
    <hr>
    Login: <input name="login" value="${doctor.login}">
    <hr>
    Password: <input type="password" name="password">
    <hr>
    Name: <input name="name" value="${doctor.name}">
    <hr>

   Category:
    <select name="category">

    <%--@elvariable id="categories" type="java.util.List"--%>
    <c:forEach var="category" items="${categories}">
        <c:choose>
            <c:when test="${category.id eq doctor.category.id}">
                <option selected>${category.name}</option>
            </c:when>
            <c:otherwise>
                <option name="category">${category.name}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </select>
    <hr>
    <input type="submit" value="Edit">

</form>
</body>
</html>
