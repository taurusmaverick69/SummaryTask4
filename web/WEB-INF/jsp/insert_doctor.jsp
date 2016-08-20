<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>


    <link rel="stylesheet" href="mdl-selectfield-master/mdl-selectfield.min.css">
    <script src="mdl-selectfield-master/mdl-selectfield.min.js"></script>

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
                document.getElementById("password2").setCustomValidity("Passwords Don't Match");
            else
                document.getElementById("password2").setCustomValidity('');
        }
    </script>
</head>

<body>

<%--@elvariable id="result" type="java.lang.String"--%>
<c:if test="${not empty result}">
    <c:out value="${result}">
    </c:out>
</c:if>


<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
    <h2 class="mdl-card__title-text">Добавить доктора</h2>
</div>
<div class="mdl-card__supporting-text">

        <div class="mdl-selectfield mdl-js-selectfield mdl-selectfield--floating-label">
            <select id="category_id" name="category" class="mdl-selectfield__select" required>
                <%--@elvariable id="categories" type="java.util.List"--%>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
            <label for="category_id" class="mdl-selectfield__label">Category</label>
            <span class="mdl-selectfield__error">Category not null</span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" id="login" name="login" required minlength="5" maxlength="45">
            <label class="mdl-textfield__label" for="login">Login</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="password" id="password1" required minlength="5" maxlength="45">
            <label class="mdl-textfield__label" for="password1">Password</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="password" id="password2" required minlength="5" maxlength="45">
            <label class="mdl-textfield__label" for="password2">Confirm password</label>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="password" id="name" required minlength="5" maxlength="45">
            <label class="mdl-textfield__label" for="name">Name</label>
        </div>
</div>

</body>
</html>
