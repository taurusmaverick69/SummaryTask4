<%--@elvariable id="user" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<html>
<body>

<nav>
    <ul id="doctorDropdown" class="dropdown-content">
        <li><a href="#!">Изменить профиль</a></li>
        <li class="divider"></li>
        <li><a href="#!">Выход</a></li>
    </ul>
    <div class="nav-wrapper deep-purple darken-3">
        <a href="controller?command=patients" class="breadcrumb">Пациенты доктора ${user.name}</a>
        <a href="controller?command=getMedicalCard" class="breadcrumb">Мед.карта: ${param.patientName}</a>
        <ul class="right">
            <li><a class="dropdown-button" data-activates="doctorDropdown">Вы вошли как ${user.login}
                <i class="material-icons right">arrow_drop_down</i>
            </a></li>
        </ul>
    </div>
</nav>


<div class="row">
    <table class="striped centered">
        <thead>
        <tr>
            <th>Диагноз</th>
            <th>Назначение</th>
            <th>Инфо</th>
            <th>Дата</th>
            <th>Доктор</th>
        </tr>
        </thead>

        <%--@elvariable id="medicalCard" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard"--%>
        <c:forEach var="appointment" items="${medicalCard.appointments}">
            <tr>
                <td>${appointment.diagnose}</td>
                <td>${appointment.type.name}</td>

                <td>
                    <a class="btn-floating waves-effect tooltipped" data-position="right" data-delay="50"
                       data-tooltip=${appointment.info}>
                        <i class="material-icons">info_outline</i>
                    </a>
                </td>

                <td>${appointment.formatDate()}</td>
                <td>${appointment.doctor.category.name} ${appointment.doctor.name}</td>


                <td>
                    <a href="controller?command=getAppointmentOnUpdate&id=${appointment.id}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">edit</i>
                    </a>

                </td>

            </tr>
        </c:forEach>
    </table>
</div>

<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="controller?command=forward&page=<%=Path.INSERT_APPOINTMENT_PAGE%>"
       class="btn-floating btn-large pink">
        <i class="material-icons">add</i>
    </a>
</div>


<%@include file="/WEB-INF/jspf/result.jspf" %>

</body>
</html>


