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

            <form class="form-signin" method="POST" action='registerUser'>
                <h2 class="form-signin-heading"><center><img src="resources/img/shield.png" /></center></h2>

                <%                            if (null != request.getParameter("msg")) {
                        String msg = request.getParameter("msg");
                        if (msg.equalsIgnoreCase("NUOK")) {
                %>
                <br /><br />
                <div class="error"><center>User created successfully! Please log in!</center></div>
                <br /><br />

                <%
                } else if (msg.equalsIgnoreCase("ERROR")) {
                %>                            
               <br /><br />
                <div class="error"><center>Problem occured. Please try again!</center></div>
                <br /><br />

                <%
                } else {

                %>

                <br /><br />
                <%                        }
                    }
                %>

                

                <label for="inputText" class="sr-only">Username></label>
                <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <label for="inputRepeatPassword" class="sr-only">Repeat Password</label>
                <input type="password" name="repeat_password" class="form-control" placeholder="Repeat Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
            </form>
            <div class="col-md-12">
                <center>  
                    <a class="btn btn-link" href="createPseudonym">Login</a>
                </center>
            </div>

        </div> <!-- /container -->

        <br /><br />
        <div class="footer">		 
            <div class="footer-inner">
                <div class="poweredBy">
                    <center>
                        &copy; 2015 - <a href="https://www.fi-star.eu/fi-star.html" target="_blank">FI-STAR</a>
                    </center>
                </div>
            </div>
        </div>                
    </body>
</html>