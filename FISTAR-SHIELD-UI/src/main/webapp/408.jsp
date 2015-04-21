<%@page import="eu.ubitech.ubises.util.translation.TranslationRetriever"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String ui = "cmui";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="<%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null, ui, "page_title")%> | ${client.clientName}">
        <meta name="author" content="UBITECH">
        <link rel="shortcut icon" href="../UBISES-RESOURCES/resources/img/${client.clientLogo}">
        <link rel="stylesheet" href="../UBISES-RESOURCES/resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="../UBISES-RESOURCES/resources/css/ubises.css">
        <title><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null, ui, "page_title")%> | ${client.clientName}</title>
    </head>
    <body>

        <div class="container">

            <form class="form-signin" method="POST" action='<%= response.encodeURL("j_security_check")%>'>
                <h2 class="form-signin-heading"><center><img src="../UBISES-RESOURCES/resources/img/${client.clientLogoLogin}"/></center></h2>
                <br /><br />
                <div class="error"><center><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "session_expired_msg") %></center></div>
                <br /><br />
                <label for="inputText" class="sr-only"><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "login_username") %></label>
                <input type="text" name="j_username" class="form-control" placeholder="<%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "login_username") %>" required autofocus>
                <label for="inputPassword" class="sr-only"><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "login_password") %></label>
                <input type="password" name="j_password" class="form-control" placeholder="<%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "login_password") %>" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><%= TranslationRetriever.INSTANCE.getFieldValueTranslation(null,  ui, "login_button") %></button>
            </form>

        </div> <!-- /container -->

        <br /><br />
        <div class="footer">		 
            <div class="footer-inner">
                <div class="poweredBy">
                    <center>
                        &copy; 2015 - <a href="http://${client.clientWebURL}" target="_blank">${client.clientName}</a>
                    </center>
                </div>
            </div>
        </div>                
    </body>
</html>