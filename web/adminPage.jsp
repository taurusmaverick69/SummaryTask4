<%--@elvariable id="admin" type="ua.nure.lyubimtsev.summarytask4.entities.Admin"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Вы вошли как администратор ${admin.login}</p>

Меню
<ul>
    <li>Просмотр врачей
        <ul>
            <li><a href="doctors?category=all">Все</a></li>
            <li><a href="doctors?category=pediatrician">Педиатры</a></li>
            <li><a href="doctors?category=traumatologist">Травматологи</a></li>
            <li><a href="doctors?category=surgeon">Хирурги</a></li>
            <li><a href="doctors?category=nurse">Медсёстры</a></li>
        </ul>
    </li>

    <li><a href="doctors?category=patients">Просмотр пациентов</a>
    </li>
</ul>

</body>
</html>
