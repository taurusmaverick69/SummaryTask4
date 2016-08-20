<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/sortable.js"></script>
    <link rel="stylesheet" href="mdl/material.min.css">
    <script src="mdl/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>
<body>

<div id="demo-toast-example" class="mdl-js-snackbar mdl-snackbar">
    <div class="mdl-snackbar__text"></div>
    <button class="mdl-snackbar__action" type="button"></button>
</div>

<script>
    r(function(){
        var snackbarContainer = document.querySelector('#demo-toast-example');
        var data = { message: 'Example Message.'};
        snackbarContainer.MaterialSnackbar.showSnackbar(data);
    });
    function r(f){ /in/.test(document.readyState)?setTimeout('r('+f+')', 9):f()}
</script>


</body>
</html>
