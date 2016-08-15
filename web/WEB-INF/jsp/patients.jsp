<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--@elvariable id="result" type="java.lang.String"--%>
<c:if test="${not empty result}">
    <c:out value="${result}">
    </c:out>
</c:if>

<table cellpadding="10">
    <caption>Patients</caption>
    <tr>
        <th>ФИО</th>
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
            <td>${patient.formatBirthDate()}</td>
            <td>${patient.state.name}</td>
            <td><a href="controller?command=getMedicalCard&id=${patient.id}&name=${patient.name}">Мед. карта</a>
            <td><a href="getPatientOnEditServlet?id=${patient.id}">Edit</a>
            </td>
        </tr>

    </c:forEach>

</table>

<li>Сортировка
    <ul>
        <li><a href="controller?command=sortPatients&sort=alphabetically">По алфавиту</a></li>
        <li><a href="controller?command=sortPatients&sort=birthDate">По дате рождения</a></li>
    </ul>

<li><a href="controller?command=forward&page=<%=Path.INSERT_PATIENT_PAGE%>">Добавить</a></li>
</li>


</body>
</html>
