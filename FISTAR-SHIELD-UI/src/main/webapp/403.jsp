<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>SHIELD | Welcome  Page</title>
    </head>
    <body>

        <div class="container">

            <form class="form-signin" method="POST" action='<%= response.encodeURL("j_security_check")%>'>
                <h2 class="form-signin-heading"><center><img src="resources/img/shield.png" /></center></h2>
                <br /><br />
                <div class="error"><center>You are not authorized! Please try again!</center></div>
                <br /><br />
                <label for="inputText" class="sr-only">Username></label>
                <input type="text" name="j_username" class="form-control" placeholder="Username" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="j_password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>


        </div> <!-- /container -->

        <br /><br />
        <div class="footer">		 
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