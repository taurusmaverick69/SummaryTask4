<%--@elvariable id="patient" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Patient"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jspf/head.jspf"%>
<html>

<body>

<div class="container center-align">
    <div class="row">
        <div class="col s6 offset-s3">
            <form action="controller?command=insertPatient" method="post">
                <div class="card white">
                    <div class="card-content blue">
                        <span class="card-title white-text">Добавить пациента</span>
                    </div>
                    <div class="section"></div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">perm_identity</i>
                            <input id="name" name="name" type="text" class="validate" required maxlength="45">
                            <label for="name">Full Name</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">home</i>
                            <input id="address" name="address" type="text" class="validate" required maxlength="255">
                            <label for="address">Address</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1 ">
                            <i class="material-icons prefix">date_range</i>
                            <input id="birthDate" name="birthDate" type="date" class="datepicker validate" required>
                            <label for="birthDate">Birth Date</label>
                        </div>
                    </div>

                    <script>
                        $(".datepicker").pickadate({
                            selectMonths: true, // Creates a dropdown to control month
                            selectYears: 15, // Creates a dropdown of 15 years to control year
                            format: "dd.mm.yyyy"
                        });

                    </script>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">
                            <select id="state" name="state">

                                <%--@elvariable id="states" type="java.util.List"--%>
                                <c:forEach var="category" items="${states}">
                                    <option value=${category.id}>${category.name}</option>
                                </c:forEach>
                            </select>
                            <label for="state">State</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s10 offset-s1">


                            <%--@elvariable id="role" type="ua.nure.lyubimtsev.SummaryTask4.Role"--%>
                            <%--@elvariable id="user" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Admin"--%>
                            <%--@elvariable id="doctor" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>

                            <select id="doctor" name="doctor">
                                <c:if test="${role.name == 'Admin'}">
                                    <c:forEach var="doctor" items="${user.doctors}">
                                        <option value=${doctor.id}>${doctor.name}</option>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${role.name == 'Doctor'}">
                                    <option value=${doctor.id}>${doctor.name}</option>
                                </c:if>
                            </select>
                            <label for="doctor">Doctor</label>
                        </div>
                    </div>


                    <script>
                        $(document).ready(function () {
                            $("select").material_select();
                        });
                    </script>

                    <div class="card-action">
                        <button class="btn waves-effect waves-light btn-large" type="submit" name="action">Submit
                            <i class="material-icons right">add</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
