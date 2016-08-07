<%--@elvariable id="user" type="ua.nure.lyubimtsev.summarytask4.entities.Admin"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Вы вошли как администратор ${user.login}</p>

<ul>
    <li><a href="doctors?category=all">Просмотр врачей</a>
        <ul>
            <li><a href="doctors?category=pediatrician">Педиатры</a></li>
            <li><a href="doctors?category=traumatologist">Травматологи</a></li>
            <li><a href="doctors?category=surgeon">Хирурги</a></li>
            <li><a href="doctors?category=nurse">Медсёстры</a></li>
        </ul>
    </li>

    <li><a href="patients?id=0">Просмотр пациентов</a>
    </li>
</ul>

</body>
</html>
