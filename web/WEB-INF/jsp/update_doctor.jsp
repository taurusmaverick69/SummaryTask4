<%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/materializecss/css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/materializecss/js/materialize.min.js"></script>


</head>
<body>

<div class="container center-align">
    <div class="row">
        <div class="col s6 offset-s3">

            <form action="controller?command=updateDoctor&id=${doctor.id}" method="post">
                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Редактировать доктора</span>
                    </div>
                    <div class="section"></div>
                    <div class='row'>
                        <div class='input-field col s8 offset-s2 '>
                            <i class="material-icons prefix">perm_identity</i>
                            <input value="${doctor.name}" id="name" name="name" type="text" class="validate" required
                                   minlength="5" maxlength="45">
                            <label for="name">Full Name</label>
                        </div>
                    </div>
                    <div class='row'>
                        <div class='input-field col s8 offset-s2'>
                            <select id="category" name="category">
                                <%--@elvariable id="categories" type="java.util.List"--%>
                                <c:forEach var="category" items="${categories}">
                                    <c:choose>
                                        <c:when test="${category.id eq doctor.category.id}">
                                            <option selected value=${category.id}>${category.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value=${category.id}>${category.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <label for="category">Category</label>
                        </div>
                    </div>
                    <script>
                        $(document).ready(function () {
                            $('select').material_select();
                        });

                    </script>

                    <div class="card-action">
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                            <i class="material-icons right">edit</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
