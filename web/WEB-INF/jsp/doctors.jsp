<%@ page import="static ua.nure.lyubimtsev.SummaryTask4.Path.PAGE_INSERT_PATIENT_PAGE" %>
<%@ page import="static ua.nure.lyubimtsev.SummaryTask4.Path.PAGE_INSERT_DOCTOR_PAGE" %><%--@elvariable id="category" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Category"--%>
<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%--@elvariable id="doctorsByCategory" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>

<table cellpadding="10">
    <caption>Список докторов по категории ${category}</caption>
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

            <td><a href="controller?command=patients&id=${doctor.id}">Список пациентов (${doctor.patients.size()})</a></td>
            <td><a href="controller?command=getDoctorOnUpdate&id=${doctor.id}">Edit</a>
            <td><a href="controller?command=getDoctorOnDelete&?id=${doctor.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>


<ul>
    <li>Сортировка
        <ul>
            <li><a href="controller?command=sortDoctors&sort=alphabetically">По алфавиту</a></li>
            <li><a href="controller?command=sortDoctors&sort=category">По категории</a></li>
            <li><a href="controller?command=sortDoctors&sort=numberOfPatients">По количеству пациентов</a></li>
        </ul>
    </li>

    <li><a href="controller?command=forward&page=<%=PAGE_INSERT_DOCTOR_PAGE%>">Добавить</a></li>

</ul>

</body>
</html>
