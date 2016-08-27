<html>
<head>
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
                document.getElementById("password2").setCustomValidity("");
        }
    </script>
</head>

<body>
<form action="controller?command=insertDoctor" method="post">
    <div class="card white center">
        <div class="card-content blue">
            <span class="card-title white-text">
                <fmt:message key="doctors.actions.add"/>
            </span>
        </div>

        <div class="section"></div>
        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">perm_identity</i>
                <input id="name" name="name" type="text" class="validate" required minlength="5"
                       maxlength="45">
                <label for="name">
                    <fmt:message key="doctors.field.fullName"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">account_circle</i>
                <input id="login" name="login" type="text" class="validate" required minlength="5"
                       maxlength="45">
                <label for="login">
                    <fmt:message key="doctors.field.login"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">vpn_key</i>
                <input id="password1" name="password" type="password" class="validate" required
                       minlength="5" maxlength="45">
                <label for="password1">
                    <fmt:message key="doctors.field.password"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">vpn_key</i>
                <input id="password2" type="password" class="validate" required minlength="5"
                       maxlength="45">
                <label for="password2">
                    <fmt:message key="doctors.field.confirmPassword"/>
                </label>
            </div>
        </div>


        <div class="row">
            <div class="input-field col s10 offset-s1">
                <select id="category" name="category">
                    <%--@elvariable id="categories" type="java.util.List"--%>
                    <c:forEach var="category" items="${categories}">
                        <c:if test="${category.id != 0}">
                            <option value="${category.id}">${category.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label for="category">
                    <fmt:message key="doctors.field.category"/>
                </label>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $("select").material_select();
            });
        </script>

        <div class="card-action">
            <button class="btn btn-large waves-effect waves-light" type="submit" name="action">
                <fmt:message key="submit"/>
                <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
</form>
</body>
</html>