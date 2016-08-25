<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="${pageContext.request.contextPath}/addons/sorttable.js"></script>
    <style>
        .mdl-badge {
            position: relative;
            white-space: nowrap;
            margin-right: 24px;
            z-index: 1;
        }

        .mdl-badge[data-badge]:after {
            content: attr(data-badge);
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-flex-direction: row;
            -ms-flex-direction: row;
            flex-direction: row;
            -webkit-flex-wrap: wrap;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            -webkit-justify-content: center;
            -ms-flex-pack: center;
            justify-content: center;
            -webkit-align-content: center;
            -ms-flex-line-pack: center;
            align-content: center;
            -webkit-align-items: center;
            -ms-flex-align: center;
            align-items: center;
            position: absolute;
            top: -11px;
            right: -24px;
            font-family: "Roboto", "Helvetica", "Arial", sans-serif;
            font-weight: 600;
            font-size: 12px;
            width: 22px;
            height: 22px;
            border-radius: 50%;
            background: #ff4081;
            color: #fff
        }

        .mdl-badge.mdl-badge--overlap {
            margin-right: 10px
        }

        .mdl-badge.mdl-badge--overlap:after {
            right: -10px
        }

        table.sortable thead {
            cursor: hand;
        }
    </style>
</head>
<body>

<nav>
    <div class="nav-wrapper deep-purple darken-3">
        <a href="#" class="brand-logo center">Доктора</a>
        <ul id="nav-mobile">
            <li><a href="sass.html">Sass</a></li>
            <li><a href="badges.html">Components</a></li>
            <li><a href="collapsible.html">JavaScript</a></li>
        </ul>
    </div>
</nav>
<div class="row">

    <ul class="tabs deep-purple darken-3">
        <%--@elvariable id="categories" type="java.util.List"--%>
        <c:forEach var="category" items="${categories}">
            <li class="tab col s3"><a class="white-text" href="#${category.name}">${category.name}</a></li>
        </c:forEach>
    </ul>

    <c:forEach var="category" items="${categories}">
        <div id=${category.name}>
            <table class="striped centered sortable">
                <thead>
                <tr>
                    <th>Логин</th>
                    <th>Пароль</th>
                    <th>Имя</th>
                    <th>Категория</th>
                    <th>Пациенты</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="doctor" items="${sessionScope.get(category.name)}">
                    <tr>
                        <td>${doctor.login}</td>
                        <td>${doctor.password}</td>
                        <td>${doctor.name}</td>
                        <td>${doctor.category.name}</td>

                        <td sorttable_customkey=${doctor.patients.size()}>
                            <a href="controller?command=patients&doctorId=${doctor.id}"
                               class="btn-floating waves-effect">
                                <i class="material-icons">group</i>
                                <a class="mdl-badge mdl-badge--overlap" data-badge=${doctor.patients.size()}></a>
                            </a>
                        </td>

                        <td>
                            <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                               class="btn-floating waves-effect">
                                <i class="material-icons">edit</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</div>

<%@include file="/WEB-INF/jspf/result.jspf" %>

<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="controller?command=forward&page=<%=Path.INSERT_DOCTOR_PAGE%>"
       class="btn-floating btn-large pink">
        <i class="material-icons">add</i>
    </a>
</div>

</body>
</html>
