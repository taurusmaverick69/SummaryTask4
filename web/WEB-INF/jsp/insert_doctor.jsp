<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
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

${param.result}


<form action="controller" method="post">

    <input type="hidden" name="command" value="insertDoctor"/>

    Login: <input name="login"/>
    <hr>
    Password: <input type="password" name="password" id="password1"/>
    <hr>
    Confirm Password:<input type="password" id="password2"/>
    <hr>
    Name:<input name="name"/>
    <hr>

    Category:
    <select name="category">
        <%--@elvariable id="categories" type="java.util.List"--%>
        <c:forEach var="category" items="${categories}">
            <option>${category.name}</option>
        </c:forEach>
    </select>
    <hr>
    <input type="submit" value="submit"/>
</form>


</body>
</html>
