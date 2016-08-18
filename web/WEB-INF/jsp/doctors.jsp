<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%--@elvariable id="category" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Category"--%>
<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%--@elvariable id="doctorsByCategory" type="java.util.List"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="js/sortable.js"></script>
    <link rel="stylesheet" href="mdl/material.min.css">
    <script src="mdl/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    <style>
        .full-width {
            width: 100%;
        }

        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>


</head>
<body>


<!-- Simple header with fixed tabs. -->
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header
            mdl-layout--fixed-tabs">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <!-- Title -->
            <span class="mdl-layout-title">Доктора</span>


            <!-- Add spacer, to align navigation to the right -->
            <div class="mdl-layout-spacer"></div>
            <nav class="mdl-navigation mdl-layout--large-screen-only">
                <a class="mdl-navigation__link" href="">Доктора</a>
                <a class="mdl-navigation__link" href="">Пациенты</a>
                <a class="mdl-navigation__link" href="">Выход</a>
            </nav>
        </div>


        <!-- Tabs -->
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
            <a href="#allDoctors" class="mdl-layout__tab is-active">Все</a>
            <a href="#pediatricians" class="mdl-layout__tab">Педиатры</a>
            <a href="#traumatologists" class="mdl-layout__tab">Травматологи</a>
            <a href="#surgeons" class="mdl-layout__tab">Хирурги</a>
            <a href="#nurses" class="mdl-layout__tab">Медсёстры</a>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">Title</span>
    </div>
    <main class="mdl-layout__content">

        <section class="mdl-layout__tab-panel is-active" id="allDoctors">
            <div class="page-content">

                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
                        <th class="mdl-data-table__cell--non-numeric">Пациенты</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%--@elvariable id="allDoctors" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${allDoctors}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.login}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.password}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.category.name}</td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <form action="controller?command=patients&id=${doctor.id}">
                                    <button id="${doctor.id}" type="submit" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab" >
                                        <i class="material-icons">group</i>
                                    </button>
                                </form>
                                <div class="mdl-tooltip mdl-tooltip--large mdl-tooltip--right" data-mdl-for="${doctor.id}">
                                    Пациенты: (${doctor.patients.size()})
                                </div>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <form action="controller?command=getDoctorOnUpdate&id=${doctor.id}">
                                    <button id="${doctor.id}" type="submit" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab" >
                                        <i class="material-icons">edit</i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="pediatricians">
            <div class="page-content">

                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
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
                    <%--@elvariable id="pediatricians" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${pediatricians}">
                        <tr>
                            <td>${doctor.login}</td>
                            <td>${doctor.password}</td>
                            <td>${doctor.name}</td>
                            <td>${doctor.category.name}</td>

                            <td><a href="controller?command=patients&id=${doctor.id}">Список пациентов
                                (${doctor.patients.size()})</a>
                            </td>
                            <td><a href="controller?command=getDoctorOnUpdate&id=${doctor.id}">Edit</a>
                            <td><a href="controller?command=getDoctorOnDelete&?id=${doctor.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="traumatologists">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
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
                    <%--@elvariable id="traumatologists" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${traumatologists}">
                        <tr>
                            <td>${doctor.login}</td>
                            <td>${doctor.password}</td>
                            <td>${doctor.name}</td>
                            <td>${doctor.category.name}</td>

                            <td><a href="controller?command=patients&id=${doctor.id}">Список пациентов
                                (${doctor.patients.size()})</a>
                            </td>
                            <td><a href="controller?command=getDoctorOnUpdate&id=${doctor.id}">Edit</a>
                            <td><a href="controller?command=getDoctorOnDelete&?id=${doctor.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="surgeons">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
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
                    <%--@elvariable id="surgeons" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${surgeons}">
                        <tr>
                            <td>${doctor.login}</td>
                            <td>${doctor.password}</td>
                            <td>${doctor.name}</td>
                            <td>${doctor.category.name}</td>

                            <td><a href="controller?command=patients&id=${doctor.id}">Список пациентов
                                (${doctor.patients.size()})</a>
                            </td>
                            <td><a href="controller?command=getDoctorOnUpdate&id=${doctor.id}">Edit</a>
                            <td><a href="controller?command=getDoctorOnDelete&?id=${doctor.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="nurses">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
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
                    <%--@elvariable id="nurses" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${nurses}">
                        <tr>
                            <td>${doctor.login}</td>
                            <td>${doctor.password}</td>
                            <td>${doctor.name}</td>
                            <td>${doctor.category.name}</td>

                            <td><a href="controller?command=patients&id=${doctor.id}">Список пациентов
                                (${doctor.patients.size()})</a>
                            </td>
                            <td><a href="controller?command=getDoctorOnUpdate&id=${doctor.id}">Edit</a>
                            <td><a href="controller?command=getDoctorOnDelete&?id=${doctor.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </main>

</div>


<%--<ul>--%>
    <%--<li>Сортировка--%>
        <%--<ul>--%>
            <%--<li><a href="controller?command=sortDoctors&sort=alphabetically">По алфавиту</a></li>--%>
            <%--<li><a href="controller?command=sortDoctors&sort=category">По категории</a></li>--%>
            <%--<li><a href="controller?command=sortDoctors&sort=numberOfPatients">По количеству пациентов</a></li>--%>
        <%--</ul>--%>
    <%--</li>--%>

    <%--<li><a href="controller?command=forward&page=<%=Path.INSERT_DOCTOR_PAGE%>">Добавить</a></li>--%>

<%--</ul>--%>


<a href="https://github.com/google/material-design-lite/blob/mdl-1.x/templates/dashboard/" target="_blank" id="view-source" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">


    <i class="material-icons">add</i>

</a>

</body>
</html>
