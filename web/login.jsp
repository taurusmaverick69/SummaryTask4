<%@include file="/WEB-INF/jspf/head.jspf" %>
<html>
<body>
<div class="section"></div>

<main>
    <div style="text-align: center;">
        <div class="section"></div>

        <h5 class="indigo-text">Hello, куку!</h5>
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
                            <label for="icon_prefix"><fmt:message key="login.label.username"/></label>
                        </div>
                    </div>
                    <div class="row">

                        <div class="input-field col s12">
                            <i class="material-icons prefix">vpn_key</i>
                            <input name="password" id="icon_key" type="password" class="validate" required>
                            <label for="icon_key"><fmt:message key="login.label.password"/></label>
                        </div>
                    </div>


                    <br/>
                    <div style="text-align: center;">
                        <div class='row'>
                            <button id="login_button" type='submit' name='btn_login'
                                    class='col s12 btn btn-large waves-effect indigo'>
                                <fmt:message key="login.button.submit"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <%--@elvariable id="loginFailed" type="java.lang.String"--%>
    <c:if test="${not empty loginFailed}">
        <script>
            Materialize.toast("<span><fmt:message key="${loginFailed}"/></span>", 3000, "rounded");
        </script>
    </c:if>

    <%--@elvariable id="endSession" type="java.lang.String"--%>
    <c:if test="${not empty endSession}">
        <script>
            Materialize.toast("<span><fmt:message key="${endSession}"/></span>", 3000, "rounded");
        </script>
    </c:if>

    <style>
        #toast-container {
            top: auto !important;
            right: auto !important;
            bottom: 10%;
            left: 7%;
        }
    </style>
</main>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>