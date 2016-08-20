<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <script src="js/sortable.js"></script>
    <link rel="stylesheet" href="mdl/material.min.css">
    <script src="mdl/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    <link rel="stylesheet" href="https://code.getmdl.io/1.1.1/material.indigo-pink.min.css">

    <!--getmdl-select-->
    <script src="getmdl-select-master/getmdl-select.min.js"></script>
    <link rel="stylesheet" href="getmdl-select-master/getmdl-select.min.css">

</head>
<body>

<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label getmdl-select">
    <input class="mdl-textfield__input" value="Belarus" type="text" id="country" readonly tabIndex="-1" data-val="BLR"/>
    <label class="mdl-textfield__label" for="country">Country</label>
    <ul class="mdl-menu mdl-menu--bottom-left mdl-js-menu" for="country">
        <li class="mdl-menu__item" data-val="BLR">Belarus</li>
        <li class="mdl-menu__item" data-val="RUS">Russia</li>
    </ul>
</div>

</body>
</html>
