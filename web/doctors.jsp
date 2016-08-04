<%--@elvariable id="admin" type="ua.nure.lyubimtsev.summarytask4.entities.Admin"--%>
<%--@elvariable id="doctorsByCategory" type="java.util.List"--%>
<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.summarytask4.entities.Doctor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table cellpadding="10">
    <caption>Doctor list</caption>
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Имя</th>
        <th>Категория</th>
    </tr>

    <c:forEach var="doctor" items="${doctorsByCategory}">
        <tr>
            <td>${doctor.login}</td>
            <td>${doctor.password}</td>
            <td>${doctor.name}</td>
            <td>${doctor.category}</td>
            <td>
                <%--<a href="editDoctor.jsp?&login=${doctor.login}&password=${doctor.password}&name=${doctor.name}&category=${doctor.category}">Edit</a>--%>
                <%--<a href="editDoctor.jsp?&doctor=${doctor}">Edit</a>--%>
                <%--<a href="delete?id=${doctor.id}">Delete</a>--%>

                    <c:url value="editDoctor.jsp" var="editDoctor">
                        <c:param name="id" value="${doctor.id}"/>
                        <c:param name="login" value="${doctor.login}"/>
                        <c:param name="password" value="${doctor.password}"/>
                        <c:param name="name" value="${doctor.name}"/>
                        <c:param name="category" value="${doctor.category}"/>
                    </c:url>

                    <c:url value="editDoctor.jsp" var="deleteDoctor">
                        <c:param name="login" value="${doctor.login}"/>
                        <c:param name="password" value="${doctor.password}"/>
                        <c:param name="name" value="${doctor.name}"/>
                        <c:param name="category" value="${doctor.category}"/>
                    </c:url>

                    <a href="${editDoctor}">Edit</a>
                    <a href="${deleteDoctor}">Delete</a>

            </td>
        </tr>

    </c:forEach>

</table>

</body>
</html>
