<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materializecss/css/materialize.min.css" media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materializecss/js/materialize.min.js"></script>


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
        <li class="tab col s3"><a class="white-text" href="#all">Все</a></li>
        <li class="tab col s3"><a class="white-text" href="#pediatricians">Педиатры</a></li>
        <li class="tab col s3"><a class="white-text" href="#traumatologists">Травматологи</a></li>
        <li class="tab col s3"><a class="white-text" href="#surgeons">Хирурги</a></li>
        <li class="tab col s3"><a class="white-text" href="#nurses">Медсёстры</a></li>
    </ul>

    <div id="all">
        <table class="striped centered">
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
            <%--@elvariable id="allDoctors" type="java.util.List"--%>
            <c:forEach var="doctor" items="${allDoctors}">
                <tr>
                    <td>${doctor.login}</td>
                    <td>${doctor.password}</td>
                    <td>${doctor.name}</td>
                    <td>${doctor.category.name}</td>

                    <td>
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

    <div id="pediatricians">
        <table class="striped centered">
            <thead>
            <tr>
                <th data-field="id">Логин</th>
                <th data-field="name">Пароль</th>
                <th data-field="price">Имя</th>
                <th data-field="price">Категория</th>
                <th data-field="price">Пациенты</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="pediatricians" type="java.util.List"--%>
            <c:forEach var="doctor" items="${pediatricians}">
                <tr>
                    <td>${doctor.login}</td>
                    <td>${doctor.password}</td>
                    <td>${doctor.name}</td>
                    <td>${doctor.category.name}</td>

                    <td>
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

    <div id="traumatologists">
        <table class="striped centered">
            <thead>
            <tr>
                <th data-field="id">Логин</th>
                <th data-field="name">Пароль</th>
                <th data-field="price">Имя</th>
                <th data-field="price">Категория</th>
                <th data-field="price">Пациенты</th>
            </tr>
            </thead>

            <tbody>
            <%--@elvariable id="traumatologists" type="java.util.List"--%>
            <c:forEach var="doctor" items="${traumatologists}">
                <tr>
                    <td>${doctor.login}</td>
                    <td>${doctor.password}</td>
                    <td>${doctor.name}</td>
                    <td>${doctor.category.name}</td>

                    <td>
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

    <div id="surgeons">
        <table class="striped centered">
            <thead>
            <tr>
                <th data-field="id">Логин</th>
                <th data-field="name">Пароль</th>
                <th data-field="price">Имя</th>
                <th data-field="price">Категория</th>
                <th data-field="price">Пациенты</th>
            </tr>
            </thead>

            <tbody>
            <%--@elvariable id="surgeons" type="java.util.List"--%>
            <c:forEach var="doctor" items="${surgeons}">
                <tr>
                    <td>${doctor.login}</td>
                    <td>${doctor.password}</td>
                    <td>${doctor.name}</td>
                    <td>${doctor.category.name}</td>

                    <td>
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

    <div id="nurses">
        <table class="striped centered">
            <thead>
            <tr>
                <th data-field="id">Логин</th>
                <th data-field="name">Пароль</th>
                <th data-field="price">Имя</th>
                <th data-field="price">Категория</th>
                <th data-field="price">Пациенты</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="nurses" type="java.util.List"--%>
            <c:forEach var="doctor" items="${nurses}">
                <tr>
                    <td>${doctor.login}</td>
                    <td>${doctor.password}</td>
                    <td>${doctor.name}</td>
                    <td>${doctor.category.name}</td>
                    <td>
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

</div>

<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="controller?command=forward&page=<%=Path.INSERT_DOCTOR_PAGE%>"
       class="btn-floating btn-large pink">
        <i class="material-icons">add</i>
    </a>
</div>

<script>
    var $toastContent = $("ewgfewg");
    Materialize.toast("toasr", 3000, "rounded");
</script>

<%--&lt;%&ndash;@elvariable id="result" type="java.lang.String"&ndash;%&gt;--%>
<%--<c:if test="${not empty result}">--%>

    <%--<script>--%>
        <%--var $toastContent = $(${result});--%>
        <%--Materialize.toast($toastContent, 3000, "rounded");--%>
    <%--</script>--%>
<%--</c:if>--%>

</body>
</html>
