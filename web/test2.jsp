<%@include file="/WEB-INF/jspf/head.jspf" %>
<!DOCTYPE html>
<html>
<head>


    <script type="text/javascript" src="//code.jquery.com/jquery-git.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/result-light.css">
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
    <style type="text/css"></style>


    <script type='text/javascript'>//<![CDATA[
    $(window).on('load', function() {
        var doc = new jsPDF();
        var specialElementHandlers = {
            '#editor': function (element, renderer) {
                return true;
            }
        };

        $('#cmd').click(function () {
            doc.fromHTML($('#content').html(), 15, 15, {
                'width': 170,
                'elementHandlers': specialElementHandlers
            });
            doc.save('sample-file.pdf');
        });
    });//]]>

    </script>

</head>

<body>
<div id="content">

    <table class="striped">
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


    <p>a pararaph</p>
</div>

<a href="" id="cmd">generate PDF</a>
</body>
</html>
