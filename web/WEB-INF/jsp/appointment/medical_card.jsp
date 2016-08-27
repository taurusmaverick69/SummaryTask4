<%--@elvariable id="user" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<html>
<body>

<div class="row">

    <jsp:include page='/WEB-INF/jspf/header.jspf'>
        <jsp:param name="end" value="1"/>
    </jsp:include>

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
                    <%--<td>${appointment.doctor}</td>--%>

                <td>${appointment.diagnose}</td>
                <td>${appointment.type.name}</td>
                <td>
                    <a class="btn-floating waves-effect modal-trigger" href="#${appointment.id}">
                        <i class="material-icons">info_outline</i>
                    </a>
                    <div id="${appointment.id}" class="modal left-align">
                        <div class="modal-content">
                            <h4>Дополнительная информация</h4>
                            <p>${appointment.info}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="modal-action modal-close waves-effect waves-green btn-flat">Закрыть</a>
                        </div>
                    </div>
                </td>
                <td> ${appointment.formatDate()}</td>
                <td>${appointment.doctor.category.name} ${appointment.doctor.name}</td>
                <td>

                    <c:choose>
                        <c:when test="${appointment.doctor.id == user.id}">
                            <a href="controller?command=getAppointmentOnUpdate&id=${appointment.id}"
                               class="btn-floating waves-effect"><i class="material-icons">edit</i></a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn-floating disabled"><i class="material-icons">edit</i></a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>

        <script>
            $(document).ready(function () {
                // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
                $('.modal-trigger').leanModal();
            });
        </script>
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


