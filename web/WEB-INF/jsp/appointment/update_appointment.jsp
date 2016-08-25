<%--@elvariable id="appointmentById" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Appointment"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf" %>

<html>
<body>


<div class="container center-align">
    <div class="row">
        <div class="col s6 offset-s3">
            <form action="controller?command=updateAppointment" method="post">


                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Редактировать назначение</span>
                    </div>

                    <div class="section"></div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <i class="material-icons prefix">mode_edit</i>
                            <textarea id="diagnose" name="diagnose" class="materialize-textarea" length="255" required>${appointmentById.diagnose}</textarea>
                            <label for="diagnose">Diagnose</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <select id="type" name="type">
                                <%--@elvariable id="types" type="java.util.List"--%>
                                <c:forEach var="type" items="${types}">
                                    <c:choose>
                                        <c:when test="${type.id eq appointmentById.type.id}">
                                            <option selected value=${type.id}>${type.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value=${type.id}>${type.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <label for="type">Appointment Type</label>
                        </div>
                    </div>

                    <script>
                        $(document).ready(function () {
                            $('select').material_select();
                        });
                    </script>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <i class="material-icons prefix">mode_edit</i>
                            <textarea id="info" name="info" class="materialize-textarea" length="255">${appointmentById.info}</textarea>
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
