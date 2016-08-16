<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/jspf/head.jspf" %>
<html>
<head>

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
                            <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect indigo'>
                                Login
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <a href="#!">Create account</a>
    </div>

    <div class="section"></div>
    <div class="section"></div>
</main>


</body>
</html>
