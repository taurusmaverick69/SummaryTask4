<html>
<body>
<form action="controller?command=insertPatient" method="post">
    <div class="card white center">
        <div class="card-content blue">
            <span class="card-title white-text">
                  <fmt:message key="patient.action.add"/>
            </span>
        </div>
        <div class="section"></div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">perm_identity</i>
                <input id="name" name="name" type="text" class="validate" required maxlength="45">
                <label for="name">
                    <fmt:message key="patient.field.fullName"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">home</i>
                <input id="address" name="address" type="text" class="validate" required maxlength="255">
                <label for="address">
                    <fmt:message key="patient.field.address"/>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s10 offset-s1 ">
                <i class="material-icons prefix">date_range</i>
                <input id="birthDate" name="birthDate" type="date"
                       class="datepicker validate" required>
                <label for="birthDate">
                    <fmt:message key="patient.field.birthDate"/>
                </label>
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
                <label for="state">
                    <fmt:message key="patient.field.state"/>
                </label>
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
                <i class="material-icons right">add</i>
            </button>
        </div>
    </div>
</form>
</body>
</html>