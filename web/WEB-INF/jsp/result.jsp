<%@ page import="ua.nure.lyubimtsev.SummaryTask4.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--@elvariable id="result" type="java.lang.String"--%>
<c:if test="${not empty result}">
    <c:out value="${result}">
    </c:out>
</c:if>

<a href="controller?command=forward&page=<%=Path.DOCTORS_PAGE%>">Вернуться к списку</a>

</body>
</html>
