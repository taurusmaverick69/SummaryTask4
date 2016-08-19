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
        #add-doctor {
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
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs">
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

                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
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
                                <a href="controller?command=patients&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">group</i>
                                    <a class="mdl-badge mdl-badge--overlap"
                                       data-badge="${doctor.patients.size()}"></a>
                                </a>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
        </section>


        <section class="mdl-layout__tab-panel" id="pediatricians">
            <div class="page-content">

                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%--@elvariable id="pediatricians" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${pediatricians}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.login}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.password}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.category.name}</td>


                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=patients&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">group</i>
                                    <a class="mdl-badge mdl-badge--overlap"
                                       data-badge="${doctor.patients.size()}"></a>
                                </a>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="traumatologists">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%--@elvariable id="traumatologists" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${traumatologists}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.login}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.password}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.category.name}</td>


                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=patients&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">group</i>
                                    <a class="mdl-badge mdl-badge--overlap"
                                       data-badge="${doctor.patients.size()}"></a>
                                </a>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>


            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="surgeons">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%--@elvariable id="surgeons" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${surgeons}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.login}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.password}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.category.name}</td>


                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=patients&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">group</i>
                                    <a class="mdl-badge mdl-badge--overlap"
                                       data-badge="${doctor.patients.size()}"></a>
                                </a>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>


            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="nurses">
            <div class="page-content">


                <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp sortable" width="100%">
                    <thead>
                    <tr>
                        <th class="mdl-data-table__cell--non-numeric">Логин</th>
                        <th class="mdl-data-table__cell--non-numeric">Пароль</th>
                        <th class="mdl-data-table__cell--non-numeric">Имя</th>
                        <th class="mdl-data-table__cell--non-numeric">Категория</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%--@elvariable id="nurses" type="java.util.List"--%>
                    <c:forEach var="doctor" items="${nurses}">
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.login}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.password}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.name}</td>
                            <td class="mdl-data-table__cell--non-numeric">${doctor.category.name}</td>


                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=patients&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">group</i>
                                    <a class="mdl-badge mdl-badge--overlap"
                                       data-badge="${doctor.patients.size()}"></a>
                                </a>
                            </td>

                            <td class="mdl-data-table__cell--non-numeric">
                                <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                                   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                                    <i class="material-icons">edit</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>


<ul>
    <li>Сортировка
        <ul>
            <li><a href="controller?command=sortDoctors&sort=alphabetically">По алфавиту</a></li>
            <li><a href="controller?command=sortDoctors&sort=category">По категории</a></li>
            <li><a href="controller?command=sortDoctors&sort=numberOfPatients">По количеству пациентов</a></li>
        </ul>
    </li>


</ul>


<%--<a href="controller?command=forward&page=<%=Path.INSERT_DOCTOR_PAGE%>" id="add-doctor"--%>
<%--class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">--%>
<%--<i class="material-icons">add</i>--%>
<%--</a>--%>

<a id="add-doctor"
   class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">add</i>
</a>


<dialog class="mdl-dialog">


    <style>
        .mdl-layout2 {
            align-items: center;
            justify-content: center;
        }

        .mdl-layout__content2 {
            padding: 24px;
            flex: none;
        }
    </style>


    <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
        <h2 class="mdl-card__title-text">Добавить доктора</h2>
    </div>
    <div class="mdl-card__supporting-text">
        <form action="#">
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="text" id="username"/>
                <label class="mdl-textfield__label" for="username">Username</label>
            </div>
            <div class="mdl-textfield mdl-js-textfield">
                <input class="mdl-textfield__input" type="password" id="userpass"/>
                <label class="mdl-textfield__label" for="userpass">Password</label>
            </div>
        </form>
    </div>


    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button close">Отмена</button>
        <%--<button type="button" class="mdl-button">Добавить</button>--%>


        <!-- Colored raised button -->
        <button id="demo-show-toast" class="mdl-button close mdl-js-button mdl-button--raised mdl-button--colored ">
            Добавить
        </button>



        <div id="demo-toast-example" class="mdl-js-snackbar mdl-snackbar">
            <div class="mdl-snackbar__text"></div>
            <button class="mdl-snackbar__action" type="button"></button>
        </div>
        <script>
            (function() {
                'use strict';
                window['counter'] = 0;
                var snackbarContainer = document.querySelector('#demo-toast-example');
                var showToastButton = document.querySelector('#demo-show-toast');
                showToastButton.addEventListener('click', function() {
                    'use strict';
                    var data = {message: 'Example Message # ' + ++counter};
                    snackbarContainer.MaterialSnackbar.showSnackbar(data);
                });
            }());
        </script>





    </div>

</dialog>
<script>
    var dialog = document.querySelector('dialog');
    var showDialogButton = document.querySelector('#add-doctor');
    if (!dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    showDialogButton.addEventListener('click', function () {
        dialog.showModal();
    });
    dialog.querySelector('.close').addEventListener('click', function () {
        dialog.close();
    });
</script>


</body>
</html>
