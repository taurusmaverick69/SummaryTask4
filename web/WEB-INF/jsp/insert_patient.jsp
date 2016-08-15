<%--@elvariable id="states" type="java.util.List"--%>
<%--@elvariable id="admin" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="controller?command=insertPatient" method="post">

    Name: <input name="name" required maxlength="45"/>
    <hr>
    Address: <input name="address" required/>
    <hr>
    Birth Date:<input type="date" name="date" required/>
    <hr>

    State:
    <select name="state">

        <c:forEach var="state" items="${states}">
            <option value="${state.id}" >${state.name}</option>
        </c:forEach>
    </select>
    <hr>


    <c:if test="${not empty admin}">
        Doctor:
        <select name="doctor">
            <c:forEach var="doctor" items="${admin.doctors}">
                <option value="${doctor.id}">${doctor.name}</option>
            </c:forEach>
        </select>
        <hr>
    </c:if>



    <input type="submit" value="submit"/>

</form>


</body>
</html>
