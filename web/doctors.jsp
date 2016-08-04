<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.summarytask4.entities.Doctor"--%>
<%--@elvariable id="doctorsByCategory" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="editDoctor.jsp" var="editDoctor">
    <c:param name="id" value="${doctor.id}"/>
    <c:param name="login" value="${doctor.login}"/>
    <c:param name="password" value="${doctor.password}"/>
    <c:param name="name" value="${doctor.name}"/>
    <c:param name="category" value="${doctor.category}"/>
</c:url>

<c:url value="editDoctor.jsp" var="deleteDoctor">
    <c:param name="login" value="${doctor.login}"/>
    <c:param name="password" value="${doctor.password}"/>
    <c:param name="name" value="${doctor.name}"/>
    <c:param name="category" value="${doctor.category}"/>
</c:url>


<html>
<head>
    <title>Title</title>
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
            <td>${doctor.category}</td>
            <td>
                <a href="patients?id=${doctor.id}">Список пациентов</a>
            </td>

            <td><a href="${editDoctor}">Edit</a>
                <a href="${deleteDoctor}">Delete</a>
            </td>
        </tr>

    </c:forEach>

</table>

</body>
</html>
