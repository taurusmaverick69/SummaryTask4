<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%--@elvariable id="loginResult" type="java.lang.String"--%>
<c:if test="${not empty loginResult}">
    <c:out value="${loginResult}">
    </c:out>
</c:if>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content center">

            <form id="login_form" action="controller" method="post">

                <input type="hidden" name="command" value="login"/>

                <fieldset>
                    <legend>Login</legend>
                    <input name="login" required/><br/>
                </fieldset>
                <br/>
                <fieldset>
                    <legend>Password</legend>
                    <input type="password" name="password" required/>
                </fieldset>
                <br/>

                <input type="submit" value="Login">
            </form>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
