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


    <script src="js/sorttable.js"></script>
</head>
<body>


<style>
    table.sortable thead {
        /*background-color:#eee;*/
        /*color:#666666;*/
        /*font-weight: bold;*/
        cursor: hand;
    }
</style>

<table class="striped sortable">

    <tr><th>Number (spelled)</th><th>Person</th></tr>
    <tr><td sorttable_customkey="3">two</td><td>Jan</td></tr>
    <tr><td sorttable_customkey="2">three</td><td>Bruce</td></tr>
    <tr><td sorttable_customkey="1">one</td><td>Steve</td></tr>
    <thead>
    <tr>
        <th data-field="id">Name</th>
        <th data-field="name">Item Name</th>
        <th data-field="price">Item Price</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>Вася</td>
        <td>Eclair</td>
        <td>$0.87</td>
    </tr>
    <tr>
        <td>Alan</td>
        <td>Jellybean</td>
        <td>$3.76</td>
    </tr>
    <tr>
        <td>Игорь</td>
        <td>Lollipop</td>
        <td>$7.00</td>
    </tr>
    </tbody>
</table>





</body>
</html>
