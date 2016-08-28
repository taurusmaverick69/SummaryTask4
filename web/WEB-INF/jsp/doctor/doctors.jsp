<%@include file="/WEB-INF/jspf/head.jspf" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/addons/sorttable.js"></script>

    <style>
        .mdl-badge {
            position: relative;
            white-space: nowrap;
            margin-right: 24px;
            z-index: 1;
        }

        .mdl-badge[data-badge]:after {
            content: attr(data-badge);
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-flex-direction: row;
            -ms-flex-direction: row;
            flex-direction: row;
            -webkit-flex-wrap: wrap;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            -webkit-justify-content: center;
            -ms-flex-pack: center;
            justify-content: center;
            -webkit-align-content: center;
            -ms-flex-line-pack: center;
            align-content: center;
            -webkit-align-items: center;
            -ms-flex-align: center;
            align-items: center;
            position: absolute;
            top: -11px;
            right: -24px;
            font-family: "Roboto", "Helvetica", "Arial", sans-serif;
            font-weight: 600;
            font-size: 12px;
            width: 22px;
            height: 22px;
            border-radius: 50%;
            background: #ff4081;
            color: #fff
        }

        .mdl-badge.mdl-badge--overlap {
            margin-right: 10px
        }

        .mdl-badge.mdl-badge--overlap:after {
            right: -10px
        }

        table.sortable thead {
            cursor: hand;
        }

        .modal {
            width: 40% !important;
            max-height: 100% !important
        }
    </style>
</head>
<body>
<header>
    <div class="row">
        <%@include file="/WEB-INF/jspf/header.jspf" %>

        <%--<jsp:include page='/WEB-INF/jspf/header.jspf'>--%>
        <%--<jsp:param name="end" value="0"/>--%>
        <%--</jsp:include>--%>

        <ul class="tabs deep-purple darken-3">
            <%--@elvariable id="categories" type="java.util.List"--%>
            <c:forEach var="category" items="${categories}">
                <li class="tab col s3"><a class="white-text" href="#${category.name}">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</header>


<main>
    <c:forEach var="category" items="${categories}">
        <div id=${category.name}>
            <table class="striped centered sortable">
                <thead>
                <tr>
                    <th><fmt:message key="doctor.field.login"/></th>
                    <th><fmt:message key="doctor.field.password"/></th>
                    <th><fmt:message key="doctor.field.fullName"/></th>
                    <th><fmt:message key="doctor.field.category"/></th>
                    <th><fmt:message key="doctor.field.patients"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="doctor" items="${sessionScope.get(category.name)}">
                    <tr>
                        <td>${doctor.login}</td>
                        <td>${doctor.password}</td>
                        <td>${doctor.name}</td>
                        <td>${doctor.category.name}</td>

                        <td sorttable_customkey=${doctor.patients.size()}>
                            <a href="controller?command=patients&doctorId=${doctor.id}"
                               class="btn-floating waves-effect">
                                <i class="material-icons">group</i>
                                <a class="mdl-badge mdl-badge--overlap" data-badge=${doctor.patients.size()}></a>
                            </a>
                        </td>


                        <td>
                            <a href="controller?command=getDoctorOnUpdate&id=${doctor.id}"
                               class="btn-floating waves-effect">
                                <i class="material-icons">edit</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>

    <%--@elvariable id="doctorById" type="ua.nure.lyubimtsev.SummaryTask4.db.entities.Doctor"--%>
    <c:if test="${not empty doctorById}">
        <div id="edit-doctor" class="modal">
            <%@include file="/WEB-INF/jsp/doctor/update_doctor.jsp" %>
        </div>

        <script>
            $('#edit-doctor').openModal();
        </script>
    </c:if>

</main>


<div class="fixed-action-btn" style="bottom: 25px; right: 25px;">
    <a href="#insert-doctor" class="btn-floating btn-large pink modal-trigger"><i class="material-icons">add</i>
    </a>
</div>

<div id="insert-doctor" class="modal">
    <%@include file="/WEB-INF/jsp/doctor/insert_doctor.jsp" %>
</div>

<script>
    $(document).ready(function () {
        $('.modal-trigger').leanModal();
    });
</script>

<%@include file="/WEB-INF/jspf/result.jspf" %>

<footer class="page-footer center">
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</footer>


</body>
</html>