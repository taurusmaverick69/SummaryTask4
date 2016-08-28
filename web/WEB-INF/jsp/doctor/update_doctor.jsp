<%--@elvariable id="doctorById" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
<html>
<body>
<form action="controller?command=updateDoctor" method="post">

    <input type="hidden" value="${doctorById.id}" name="id">

    <div class="card white center">
        <div class="card-content blue">
            <span class="card-title white-text">
                <fmt:message key="doctor.action.edit"/>
            </span>
        </div>
        <div class="section"></div>
        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">perm_identity</i>
                <input value="${doctorById.name}" id="name" name="name" type="text"
                       class="validate" required
                       minlength="5" maxlength="45">
                <label for="name"><fmt:message key="doctor.field.fullName"/></label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1">
                <select id="category" name="category">
                    <%--@elvariable id="categories" type="java.util.List"--%>
                    <c:forEach var="category" items="${categories}">
                        <c:if test="${category.id != 0}">
                            <c:choose>
                                <c:when test="${category.id eq doctorById.category.id}">
                                    <option selected value=${category.id}>${category.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value=${category.id}>${category.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </select>
                <label for="category"><fmt:message key="doctor.field.category"/></label>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $("select").material_select();
            });
        </script>

        <div class="card-action">
            <button class="btn waves-effect waves-light btn-large" type="submit" name="action">
                <fmt:message key="submit"/>
                <i class="material-icons right">edit</i>
            </button>
        </div>
    </div>
</form>
</body>
</html>
