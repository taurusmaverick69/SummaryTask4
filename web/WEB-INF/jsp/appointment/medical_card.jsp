<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf"%>

<html>
<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo center">Мед. карта</a>
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
            <th>№</th>
            <th>Диагноз</th>
            <th>Назначение</th>
            <th>Доп. информация</th>
            <th>Дата</th>
            <th>Доктор</th>
        </tr>
        </thead>

        <%--@elvariable id="medicalCard" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.MedicalCard"--%>
        <c:forEach var="appointment" items="${medicalCard.appointments}">
            <tr>
                <td>${appointment.id}</td>
                <td title="${appointment.diagnose}">
                    <c:choose>
                        <c:when test="${appointment.diagnose.length() > 25}">
                            ${appointment.diagnose.substring(0, 25).concat("...")}
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
                            ${appointment.info.substring(0, 25).concat("...")}
                        </c:when>

                        <c:otherwise>
                            ${appointment.info}
                        </c:otherwise>
                    </c:choose>
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


