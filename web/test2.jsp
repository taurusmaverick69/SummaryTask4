<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<button id="show-dialog" type="button" class="mdl-button">Show Dialog</button>
<dialog class="mdl-dialog">

<%@ include file="/test.jsp" %>



<div class="mdl-dialog__actions">
<button type="button" class="mdl-button">Agree</button>
<button type="button" class="mdl-button close">Disagree</button>
</div>
</dialog>




<script>
    var dialog = document.querySelector('dialog');
    var showDialogButton = document.querySelector('#show-dialog');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    showDialogButton.addEventListener('click', function() {
        dialog.showModal();

    });
    dialog.querySelector('.close').addEventListener('click', function() {
        dialog.close();
    });
</script>





</body>
</html>
