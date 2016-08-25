<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%--@elvariable id="role" type="Role"--%>
<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>

<html>
<head>
    <script src="${pageContext.request.contextPath}/addons/sorttable.js"></script>
    <style>
        table.sortable thead {
            cursor: hand;
        }
    </style>


</head>

<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo center">Пациенты: Доктор ${doctor.name}</a>
        <ul id="nav-mobile">
            <li><a href="sass.html">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">JavaScript</a></li>
        </ul>
    </div>
</nav>
<div class="row">
    <table class="striped centered sortable">
        <thead>
        <tr>
            <th>ФИО</th>
            <th>Адрес</th>
            <th>Дата рождения</th>
            <th>Состояние</th>
            <th class="sorttable_nosort">Мед. карта</th>
        </tr>
        </thead>

        <tbody>
        <%--@elvariable id="patients" type="java.util.List"--%>
        <c:forEach var="patient" items="${doctor.patients}">

            <tr>
                <td>${patient.name}</td>
                <td>${patient.address}</td>
                <td>${patient.formatBirthDate()}</td>
                <td>${patient.state.name}</td>
                <td>
                    <c:if test="${role.name == 'Admin'}">
                        <a class="btn-floating disabled"> <i class="material-icons">assignment</i></a>
                    </c:if>
                    <c:if test="${role.name == 'Doctor'}">
                        <a href="controller?command=getMedicalCard&patientId=${patient.id}"
                           class="btn-floating waves-effect">
                            <i class="material-icons">assignment</i>
                        </a>
                    </c:if>
                </td>
                <td>
                    <a href="controller?command=getPatientOnUpdate&id=${patient.id}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">edit</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<style>
    .modal {
        width: 75% !important;
        max-height: 100% !important
    }
</style>


<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a class="btn-floating btn-large pink modal-trigger" href="#modal">
        <i class="material-icons">add</i>
    </a>
</div>

<!-- Modal Structure -->
<div id="modal" class="modal">
    <div class="modal-content">
        <h4>Пациент уже в базе?</h4>
        <p>Возможно пациент, который будет вам назначен, уже существует в базе.</p>
        <p>Добавить нового пациента или выбрать из существующих?</p>
    </div>
    <div class="modal-footer">
        <a href="controller?command=getUnassignedPatients&doctorId=${doctor.id}"
           class=" modal-action modal-close waves-effect waves-green btn-flat">Выбрать из существующих</a>
        <a href="controller?command=forward&page=<%=Path.INSERT_PATIENT_PAGE%>"
           class=" modal-action modal-close waves-effect waves-green btn-flat">Добавить как нового</a>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.modal-trigger').leanModal();
    });
</script>

<%@include file="/WEB-INF/jspf/result.jspf" %>

</body>
</html>