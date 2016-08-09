<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table cellpadding="10">
    <caption>Patients</caption>
    <tr>
        <th>Имя</th>
        <th>Адрес</th>
        <th>Дата рождения</th>
        <th>Состояние</th>
    </tr>

    <%--@elvariable id="patients" type="java.util.List"--%>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.name}</td>
            <td>${patient.address}</td>
            <td>${patient.birthDate}</td>
            <td>${patient.state.name}</td>
            <td><a href="getPatientOnEditServlet?id=${patient.id}">Edit</a>
            <td><a href="getDoctorOnDeleteServlet?id=${doctor.id}">Delete</a></td>
            </td>
        </tr>

    </c:forEach>

</table>

<li>Сортировка
    <ul>
        <li><a href="sortPatientsServlet?sort=alphabetically">По алфавиту</a></li>
        <li><a href="sortPatientsServlet?sort=birthDate">По дате рождения</a></li>
    </ul>
</li>



</body>
</html>
