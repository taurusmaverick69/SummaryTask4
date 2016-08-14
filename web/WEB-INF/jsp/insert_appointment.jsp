<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="controller" method="get">


    <fieldset>
        <legend>Insert Appointment</legend>
        <p>Diagnose:<br/>
            <textarea name="comment" cols="40" rows="5" maxlength="255" required></textarea>
        </p>


        Appointment:
        <select name="appointment">
            <%--@elvariable id="types" type="java.util.List"--%>
            <c:forEach var="type" items="${types}">
                <option value="${type.id}">${type.name}</option>
            </c:forEach>
        </select>
        <br/>


        <p>Info:<br/>
            <textarea name="comment" cols="40" rows="5" maxlength="255" required></textarea>
        </p>
        <input type="submit" value="Insert">
    </fieldset>

</form>

</body>
</html>
