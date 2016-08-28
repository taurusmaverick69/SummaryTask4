<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%--@elvariable id="role" type="Role"--%>
<%@include file="/WEB-INF/jspf/head.jspf" %>

<html>
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
            <th><fmt:message key="patient.field.fullName"/></th>
            <th><fmt:message key="patient.field.address"/></th>
            <th><fmt:message key="patient.field.birthDate"/></th>
            <th><fmt:message key="patient.field.state"/></th>
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
                    <a href="controller?command=assignPatient&patientId=${patient.id}&doctorId=${doctorId}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">add</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>


<%@include file="/WEB-INF/jspf/footer.jspf" %>


</body>
</html>