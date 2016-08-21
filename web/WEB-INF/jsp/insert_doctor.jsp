<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/materializecss/css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/materializecss/js/materialize.min.js"></script>


    <title>Title</title>
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("password1").onchange = validatePassword;
            document.getElementById("password2").onchange = validatePassword;
        };
        function validatePassword() {
            var pass2 = document.getElementById("password2").value;
            var pass1 = document.getElementById("password1").value;
            if (pass1 != pass2)
                document.getElementById("password2").setCustomValidity("Пароли не совпадают");
            else
                document.getElementById("password2").setCustomValidity('');
        }
    </script>
</head>

<body>


<div class="container center-align">


    <div class="row">
        <div class="col s6 offset-s3">

            <form action="controller?command=insertDoctor" method="post">
                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Добавить доктора</span>
                    </div>

                    <div class="section"></div>
                    <div class='row'>
                        <div class='input-field col s8 offset-s2 '>
                            <i class="material-icons prefix">perm_identity</i>
                            <input id="name" name="name" type="text" class="validate" required minlength="5" maxlength="45">
                            <label for="name">Full Name</label>
                        </div>
                    </div>

                    <div class='row'>
                        <div class='input-field col s8 offset-s2 '>
                            <i class="material-icons prefix">account_circle</i>
                            <input id="login" name="login" type="text" class="validate" required minlength="5" maxlength="45">
                            <label for="login">Login</label>
                        </div>
                    </div>

                    <div class='row'>
                        <div class='input-field col s8 offset-s2 '>
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="password1" name="password" type="password" class="validate" required minlength="5" maxlength="45">
                            <label for="password1">Password</label>
                        </div>
                    </div>

                    <div class='row'>
                        <div class='input-field col s8 offset-s2 '>
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="password2" type="password" class="validate" required minlength="5" maxlength="45">
                            <label for="password2">Confirm Password</label>
                        </div>
                    </div>


                    <div class='row'>
                        <div class='input-field col s8 offset-s2'>
                            <select id="category" name="category">
                                <%--@elvariable id="categories" type="java.util.List"--%>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            <label for="category">Category</label>
                        </div>
                    </div>

                    <script>
                        $(document).ready(function () {
                            $('select').material_select();
                        });

                    </script>

                    <div class="card-action">
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
