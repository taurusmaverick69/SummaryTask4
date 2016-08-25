<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%--@elvariable id="role" type="Role"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf"%>

<html>
<head>

</head>

<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo center">Неназначеные на доктора пациенты</a>
        <ul id="nav-mobile">
            <li><a href="sass.html">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">JavaScript</a></li>
        </ul>
    </div>
</nav>
<div class="row">
    <table class="striped centered">
        <thead>
        <tr>
            <th>ФИО</th>
            <th>Адрес</th>
            <th>Дата рождения</th>
            <th>Состояние</th>
        </tr>
        </thead>

        <tbody>
        <%--@elvariable id="unassignedPatients" type="java.util.List"--%>
        <c:forEach var="patient" items="${unassignedPatients}">
            <tr>
                <td>${patient.name}</td>
                <td>${patient.address}</td>
                <td>${patient.formatBirthDate()}</td>
                <td>${patient.state.name}</td>

                <td>
                    <a href="controller?command=assignPatient&patientId=${patient.id}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">add</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>