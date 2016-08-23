<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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

    <title>Title</title>

</head>
<body>

<div class="container center-align">
    <div class="row">
        <div class="col s6 offset-s3">
            <form action="controller?command=insertAppointment" method="post">
                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Добавить назначение</span>
                    </div>

                    <div class="section"></div>
                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <i class="material-icons prefix">mode_edit</i>
                            <textarea id="diagnose" name="diagnose" class="materialize-textarea" length="255" required></textarea>
                            <label for="diagnose">Diagnose</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <select id="type" name="type">
                                <%--@elvariable id="types" type="java.util.List"--%>
                                <c:forEach var="type" items="${types}">
                                    <option value="${type.id}">${type.name}</option>
                                </c:forEach>
                            </select>
                            <label for="type">Appointment Type</label>
                        </div>
                    </div>

                    <script>
                        $(document).ready(function() {
                            $('select').material_select();
                        });
                    </script>


                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <i class="material-icons prefix">info</i>
                            <textarea id="info" name="info" class="materialize-textarea" length="255"></textarea>
                            <label for="info">Info</label>
                        </div>
                    </div>


                    <div class="card-action">
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
