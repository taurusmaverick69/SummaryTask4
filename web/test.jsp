<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script src="js/sortable.js"></script>
    <link rel="stylesheet" href="mdl/material.min.css">
    <script src="mdl/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    <link rel="stylesheet" href="mdl-selectfield-master/mdl-selectfield.min.css">
    <script src="mdl-selectfield-master/mdl-selectfield.min.js"></script>

</head>
<body>


<div class="mdl-layout__container">
    <div class="mdl-layout mdl-js-layoutmdl-layout--fixed-drawermdl-layout--fixed-header">
        <main class="mdl-layout__content mdl-color--grey-100">
            <div class="mdl-grid">
                <div class="mdl-color--whitemdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                    <div class="mdl-cell mdl-cell--12-col">
                        <!-- Simple Textfield -->
                        <form action="#">
                            <div class="mdl-selectfield mdl-js-selectfield mdl-selectfield--floating-label">
                                <select id="profile_information_form_country_id" name="profile_information_form_country" class="mdl-selectfield__select" required>
                                    <option value=""></option>
                                    <option value="192">Abkhazia</option>
                                    <option value="1">Afghanistan</option>
                                    <option value="255">Aland</option>
                                    <option value="2">Albania</option>
                                    <option value="3">Algeria</option>
                                    <option value="235">American Samoa</option>

                                </select>
                                <label for="profile_information_form_country_id" class="mdl-selectfield__label">Country</label>
                                <span class="mdl-selectfield__error">Input is not a empty!</span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
