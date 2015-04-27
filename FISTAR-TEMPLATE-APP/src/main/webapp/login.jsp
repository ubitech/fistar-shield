<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="author" content="UBITECH">
        <link rel="shortcut icon" href="resources/img/shield.png">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/shield.css">
        <title>FISTAR APP | Main  Page</title>
    </head>
    <body style="text-align:center"> 

        <div class="container">

            <form class="form-signin" method="POST" action='login'>
                <br><br>
                <h2 class="form-signin-heading"><center><img src="img/fistar.png" /><br>
                FI-STAR Template Application</center></h2>
                <br><br>
                User principal: <strong>${userDN}</strong><br>
                Authorized Roles: <strong>${roles}</strong>
            </form>


        </div> <!-- /container -->

        <br /><br />
        <div class="footer">	
            <br>
            <br>
            <br>
            <div class="footer-inner">
                <div class="poweredBy">
                    <center>
                        &copy; 2015 - <a href="https://www.fi-star.eu/fi-star.html" target="_blank">FISTAR</a>
                    </center>
                </div>
            </div>
        </div>                
    </body>
</html>