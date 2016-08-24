<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materializecss/css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="materializecss/js/materialize.min.js"></script>

</head>
<body>


<style>
    #toast-container {
        top: auto !important;
        right: auto !important;
        bottom: 10%;
        left:45%;
    }
</style>

<script>



    var $toastContent = $('<center>I am toast content</center>');
    Materialize.toast($toastContent, 5000);

</script>

</body>
</html>
