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


    <title>The Materialize Example</title>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">--%>
    <%--<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>--%>

    <nav>
        <div class="nav-wrapper teal lighten-2">
            <a href="#!" class="brand-logo center">Logo</a>
            <ul>
                <li><a href="sass.html">Sass</a></li>
                <li><a href="badges.html">Components</a></li>
                <li class="active"><a href="collapsible.html">JavaScript</a></li>
            </ul>
        </div>
    </nav>


    <div class="row">
        <div class="col s12">
            <ul class="tabs">
                <li class="tab col s2">
                    <a target="_blank" href="https://github.com/Dogfalo/materialize">External link in new window</a>
                </li>
                <li class="tab col s2">
                    <a target="_self" href="https://github.com/Dogfalo/materialize">External link in same window</a>
                </li>

            </ul>
        </div>
        <div id="test1" class="col s12">


            <table class="centered">
                <thead>
                <tr>
                    <th data-field="id">Name</th>
                    <th data-field="name">Item Name</th>
                    <th data-field="price">Item Price</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td>Alvin</td>
                    <td>Eclair</td>
                    <td>$0.87</td>
                </tr>
                <tr>
                    <td>Alan</td>
                    <td>Jellybean</td>
                    <td>$3.76</td>
                </tr>
                <tr>
                    <td>Jonathan</td>
                    <td>Lollipop</td>
                    <td>$7.00</td>
                </tr>
                </tbody>
            </table>


        </div>
        <div id="test2" class="col s12">Test 2</div>
        <div id="test3" class="col s12">Test 3</div>
        <div id="test4" class="col s12">Test 4</div>
    </div>


</head>
<body>

</body>
</html>
