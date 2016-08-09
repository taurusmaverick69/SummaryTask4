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
    <li><a href="controller?command=doctors&category=all">Просмотр врачей</a>
        <ul>
            <li><a href="controller?command=doctors&category=pediatrician">Педиатры</a></li>
            <li><a href="controller?command=doctors&category=traumatologist">Травматологи</a></li>
            <li><a href="controller?command=doctors&category=surgeon">Хирурги</a></li>
            <li><a href="controller?command=doctors&category=nurse">Медсёстры</a></li>
        </ul>
    </li>

    <li><a href="controller?command=patients&id=0">Просмотр пациентов</a>
    </li>
</ul>

</body>
</html>
