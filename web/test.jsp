<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/jspf/head.jspf"%>
<html>
<head>
    <title>Title</title>
</head>
<body>


<button id="demo-show-toast" class="mdl-button mdl-js-button mdl-button--colored" type="button">Show Toast</button>



<div id="demo-toast-example" class="mdl-js-snackbar mdl-snackbar">
    <div class="mdl-snackbar__text"></div>
    <button class="mdl-snackbar__action" type="button"></button>
</div>
<script>
    (function() {
        'use strict';
        window['counter'] = 0;
        var snackbarContainer = document.querySelector('#demo-toast-example');
        var showToastButton = document.querySelector('#demo-show-toast');
        showToastButton.addEventListener('click', function() {
            'use strict';
            var data = {message: 'Invalid Login or password'};
            snackbarContainer.MaterialSnackbar.showSnackbar(data);
        });
    }());
</script>

</body>
</html>
