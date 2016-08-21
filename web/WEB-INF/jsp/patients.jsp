<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="result" type="java.lang.String"--%>
<c:if test="${not empty result}">
    <c:out value="${result}">
    </c:out>
</c:if>

<%--<li>Сортировка--%>
<%--<ul>--%>
<%--<li><a href="controller?command=sortPatients&sort=alphabetically">По алфавиту</a></li>--%>
<%--<li><a href="controller?command=sortPatients&sort=birthDate">По дате рождения</a></li>--%>
<%--</ul>--%>

<%--<li><a href="controller?command=forward&page=<%=Path.INSERT_PATIENT_PAGE%>">Добавить</a></li>--%>
<%--</li>--%>

<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/materializecss/css/materialize.min.css" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/materializecss/js/materialize.min.js"></script>
</head>

<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo center">Пациенты</a>
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
            <th>Мед. карта</th>
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
                    <a href="controller?command=getMedicalCard&id=${patient.id}&name=${patient.name}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">assignment</i>
                    </a>
                </td>

                <td>
                    <a href="getPatientOnEditServlet?id=${patient.id}"
                       class="btn-floating waves-effect">
                        <i class="material-icons">edit</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="controller?command=forward&page=<%=Path.INSERT_DOCTOR_PAGE%>"
       class="btn-floating btn-large pink">
        <i class="material-icons">add</i>
    </a>
</div>

</body>
</html>