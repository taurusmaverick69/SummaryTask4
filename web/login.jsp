<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materializecss/css/materialize.min.css"  media="screen,projection"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materializecss/js/materialize.min.js"></script>

</head>
<body>
<div class="section"></div>
<main>
    <div style="text-align: center;">
        <div class="section"></div>

        <h5 class="indigo-text">Hello, guest!</h5>
        <h5 class="indigo-text">Please, login into your account</h5>
        <div class="section"></div>

        <div class="container">
            <div class="z-depth-1 grey lighten-4 row"
                 style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

                <form class="col s12" method="post" action="controller?command=login">
                    <div class='row'>
                        <div class='col s12'>
                        </div>
                    </div>


                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input name="login" id="icon_prefix" type="text" class="validate" required>
                            <label for="icon_prefix">Login</label>
                        </div>

                    </div>
                    <div class="row">

                        <div class="input-field col s12">
                            <i class="material-icons prefix">vpn_key</i>
                            <input name="password" id="icon_key" type="password" class="validate" required>
                            <label for="icon_key">Password</label>
                        </div>
                    </div>


                    <br/>
                    <div style="text-align: center;">
                        <div class='row'>
                            <button id="login_button" type='submit' name='btn_login'
                                    class='col s12 btn btn-large waves-effect indigo'>
                                Login
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <%--@elvariable id="loginResult" type="java.lang.String"--%>
    <c:if test="${not empty loginResult}">
        <style>
            #toast-container {
                top: auto !important;
                right: auto !important;
                bottom: 35%;
                left: 36.5%;
            }
        </style>

        <script>
            var $toastContent = $('<span>${loginResult}</span>');
            Materialize.toast($toastContent, 3000, 'rounded');
        </script>
    </c:if>

</main>
</body>
</html>
