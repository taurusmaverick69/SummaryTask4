<%--@elvariable id="doctorName" type="java.lang.String"--%>
<%--@elvariable id="doctorId" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
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

<%@include file="/WEB-INF/jspf/header.jspf" %>

<ul class="tabs blue darken-3">
    <li class="tab col s3">
        <a class="white-text">
            <fmt:message key="tab.doctor"/>
            ${doctorName}
        </a>
    </li>
</ul>

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

<div id="modal" class="modal">
    <div class="modal-content">
        <h4><fmt:message key="modal.header"/></h4>
        <p><fmt:message key="modal.content"/></p>
    </div>
    <div class="modal-footer">
        <a href="controller?command=getUnassignedPatients&doctorId=${doctorId}"
           class=" modal-action modal-close waves-effect waves-green btn-flat">
            <fmt:message key="modal.footer.choose"/>
        </a>
        <a href="#insert-patient"
           class=" modal-action modal-close waves-effect waves-green btn-flat modal-trigger">
            <fmt:message key="modal.footer.add"/>
        </a>
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