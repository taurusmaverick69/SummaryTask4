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
            <td>${patient.state}</td>
            <%--<td>--%>
                <%--<a href="patients?id=${doctor.id}">Список пациентов</a>--%>
            <%--</td>--%>

            <%--<td><a href="${editDoctor}">Edit</a>--%>
                <%--<a href="${deleteDoctor}">Delete</a>--%>
            <%--</td>--%>
        </tr>

    </c:forEach>

</table>


</body>
</html>
