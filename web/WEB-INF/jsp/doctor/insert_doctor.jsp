<%@include file="/WEB-INF/jspf/head.jspf"%>
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


<div class="container center-align">
    <div class="row">
        <div class="col s6 offset-s3">
            <form action="controller?command=insertDoctor" method="post">
                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Добавить доктора</span>
                    </div>

                    <div class="section"></div>
                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">perm_identity</i>
                            <input id="name" name="name" type="text" class="validate" required minlength="5" maxlength="45">
                            <label for="name">Full Name</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="login" name="login" type="text" class="validate" required minlength="5" maxlength="45">
                            <label for="login">Login</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="password1" name="password" type="password" class="validate" required minlength="5" maxlength="45">
                            <label for="password1">Password</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="password2" type="password" class="validate" required minlength="5" maxlength="45">
                            <label for="password2">Confirm Password</label>
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
                            <label for="category">Category</label>
                        </div>
                    </div>

                    <script>
                        $(document).ready(function () {
                            $("select").material_select();
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
