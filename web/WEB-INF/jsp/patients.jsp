<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
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
    <%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
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
        <li><a href="controller?command=sortPatients&sort=alphabetically">По алфавиту</a></li>
        <li><a href="controller?command=sortPatients&sort=birthDate">По дате рождения</a></li>
    </ul>

<li><a href="controller?command=forward&page=<%=Path.PAGE_INSERT_PATIENT_PAGE%>">Добавить</a></li>
</li>


</body>
</html>
