<%--@elvariable id="user" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<html>

<head>
    <script src="${pageContext.request.contextPath}/addons/sorttable.js"></script>
    <style>
        table.sortable thead {
            cursor: hand;
        }

        .modal {
            width: 40% !important;
            max-height: 100% !important
        }
    </style>
</head>


<body>

<header>
    <div class="row">
        <%@include file="/WEB-INF/jspf/header.jspf" %>
    </div>
</header>

<main>
    <table class="striped centered">
        <thead>
        <tr>
            <th><fmt:message key="appointment.field.diagnose"/></th>
            <th><fmt:message key="appointment.field.appointment"/></th>
            <th><fmt:message key="appointment.field.info"/></th>
            <th><fmt:message key="appointment.field.date"/></th>
            <th><fmt:message key="appointment.field.doctor"/></th>
        </tr>
        </thead>

        <tbody>
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
        </tbody>
    </table>

    <%--@elvariable id="appointmentById" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
    <c:if test="${not empty appointmentById}">
        <div id="edit-appointment" class="modal">
            <%@include file="/WEB-INF/jsp/appointment/update_appointment.jsp" %>
        </div>

        <script>
            $('#edit-appointment').openModal();
        </script>
    </c:if>

</main>

<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="#insert-appointment" class="btn-floating btn-large pink modal-trigger">
        <i class="material-icons">add</i>
    </a>
</div>


<div id="insert-appointment" class="modal">
    <%@include file="/WEB-INF/jsp/appointment/insert_appointment.jsp" %>
</div>

<script>
    $(document).ready(function () {
        $('.modal-trigger').leanModal();
    });
</script>

<%@include file="/WEB-INF/jspf/result.jspf" %>

<footer class="page-footer center">
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</footer>

</body>
</html>


