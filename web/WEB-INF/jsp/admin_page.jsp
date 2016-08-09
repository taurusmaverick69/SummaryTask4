<%--@elvariable id="user" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Вы вошли как администратор ${user.login}</p>

<ul>
    <li><a href="controller?command=doctors&role=all">Просмотр врачей</a>
        <ul>
            <li><a href="controller?command=doctors&role=pediatrician">Педиатры</a></li>
            <li><a href="controller?command=doctors&role=traumatologist">Травматологи</a></li>
            <li><a href="controller?command=doctors&role=surgeon">Хирурги</a></li>
            <li><a href="controller?command=doctors&role=nurse">Медсёстры</a></li>
        </ul>
    </li>

    <li><a href="patients?id=0">Просмотр пациентов</a>
    </li>
</ul>

</body>
</html>
