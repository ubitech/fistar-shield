<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="eu.ubitech.ubises.util.translation.TranslationRetriever"%>
<%
    String ui = "cmui";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="<%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "page_title") %> | ${client.clientName}">
        <meta name="author" content="UBITECH">
        <title><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "page_title") %> | ${client.clientName}</title>
        <link rel="icon" href="../UBISES-RESOURCES/resources/img/${client.clientLogo}">
        <link href="../UBISES-RESOURCES/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="../UBISES-RESOURCES/resources/css/dashboard.css" rel="stylesheet">
    </head>

    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12 main">
                    <h1 class="sub-header"><center><img src="../UBISES-RESOURCES/resources/img/${client.clientLogoLogin}" height="300px;"/></center></h1>
                    <div id="notificationMSG" class="alert alert-danger">
                        <div class="text-center"><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "unavailable_msg") %></div>
                    </div>
                </div>
            </div>
        </div>
        <script src="../UBISES-RESOURCES/resources/js/jquery-2.1.1.min.js"></script>
        <script src="../UBISES-RESOURCES/resources/js/bootstrap.min.js"></script>
        <script src="../UBISES-RESOURCES/resources/js/docs.min.js"></script>
    </body>
</html>