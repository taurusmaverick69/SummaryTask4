<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="medicalCard" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>
    Медицинская карта
</h3>

<p>
    ФИО пациента: ${param.name}
</p>

<p>
    Дата создания: ${medicalCard.registrationDate}
</p>

<table cellpadding="10">
    <caption>История болезней</caption>
    <tr>
        <th>№</th>
        <th>Диагноз</th>
        <th>Назначение</th>
        <th>Доп. информация</th>
        <th>Дата</th>
        <th>Доктор</th>
    </tr>

    <%--@elvariable id="patients" type="java.util.List"--%>
    <%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
    <c:forEach var="appointment" items="${medicalCard.appointments}">
        <tr>
            <td>${appointment.id}</td>
            <td title="${appointment.diagnose}">
                <c:choose>
                    <c:when test="${appointment.diagnose.length() > 25}">
                        ${appointment.diagnose.substring(0, 25).concat('...')}
                    </c:when>

                    <c:otherwise>
                        ${appointment.diagnose}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${appointment.type.name}</td>
            <td title=${appointment.info}>
                <c:choose>
                    <c:when test="${appointment.info.length() > 25}">
                        ${appointment.info.substring(0, 25).concat('...')}
                    </c:when>

                    <c:otherwise>
                        ${appointment.info}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${appointment.date}</td>
            <td>${appointment.doctor.category.name} ${appointment.doctor.name}</td>
            </td>
        </tr>
    </c:forEach>
</table>


<p>
    <a href="controller?command=forward&page=<%=Path.INSERT_APPOINTMENT_PAGE%>">Добавить назначение</a>
</p>


</body>
</html>
