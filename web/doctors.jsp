<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.summarytask4.entities.Doctor"--%>
<%--@elvariable id="doctorsByCategory" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>

<table cellpadding="10">
    <caption>Doctors</caption>
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Имя</th>
        <th>Категория</th>
        <th>Пациенты</th>
    </tr>


    <c:forEach var="doctor" items="${doctorsByCategory}">
        <tr>
            <td>${doctor.login}</td>
            <td>${doctor.password}</td>
            <td>${doctor.name}</td>
            <td>${doctor.category.name}</td>
            <td><a href="patients?id=${doctor.id}">Список пациентов</a></td>

            <td><a href="getDoctorOnEditServlet?id=${doctor.id}">Edit</a>
            <td><a href="getDoctorOnDeleteServlet?id=${doctor.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
