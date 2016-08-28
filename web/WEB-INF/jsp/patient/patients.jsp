<%--@elvariable id="role" type="Role"--%>
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
    <table class="striped centered sortable">
        <thead>
        <tr>
            <th><fmt:message key="patient.field.fullName"/></th>
            <th><fmt:message key="patient.field.address"/></th>
            <th><fmt:message key="patient.field.birthDate"/></th>
            <th><fmt:message key="patient.field.state"/></th>
            <th class="sorttable_nosort"><fmt:message key="patient.field.medicalCard"/></th>
        </tr>
        </thead>

        <tbody>
        <%--@elvariable id="patients" type="java.util.List"--%>
        <c:forEach var="patient" items="${patients}">

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
                        <a href="controller?command=getMedicalCard&patientId=${patient.id}&patientName=${patient.name}"
                           class="btn-floating waves-effect">
                            <i class="material-icons">assignment</i>
                        </a>
                    </c:if>
                </td>
                <td>
                    <a href="controller?command=getPatientOnUpdate&patientId=${patient.id}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">edit</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--@elvariable id="patientById" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
    <c:if test="${not empty patientById}">

        <div id="edit-patient" class="modal">
            <%@include file="/WEB-INF/jsp/patient/update_patient.jsp" %>
        </div>

        <script>
            $('#edit-patient').openModal();
        </script>

    </c:if>

</main>


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
        <a href="controller?command=getUnassignedPatients&doctorId=${doctorId}"
           class=" modal-action modal-close waves-effect waves-green btn-flat">Выбрать из существующих</a>
        <a href="#insert-patient"
           class=" modal-action modal-close waves-effect waves-green btn-flat modal-trigger">Добавить как нового</a>
    </div>
</div>

<div id="insert-patient" class="modal">
    <%@include file="/WEB-INF/jsp/patient/insert_patient.jsp" %>
</div>

<script>
    $(document).ready(function () {
        $('.modal-trigger').leanModal();
    });
</script>

<%@include file="/WEB-INF/jspf/result.jspf" %>


<%@include file="/WEB-INF/jspf/footer.jspf" %>


</body>
</html>