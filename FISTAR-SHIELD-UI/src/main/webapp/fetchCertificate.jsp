<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="UBITECH">
        <title>SHIELD | User Home Page</title>
        <link rel="shortcut icon" href="resources/img/shield.png">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link href="resources/css/jquery-ui.min.css" rel="stylesheet">
        <link href="resources/css/jquery.dataTables.css" rel="stylesheet">
        <link href="resources/css/cmui.min.css" rel="stylesheet">
        <link href="resources/css/steps.css" rel="stylesheet">

    </head>

    <body>

        <div id="page-wrapper" class="active">

            <!-- Sidebar -->
            <%@include file="sidebar.jsp" %>
            <!-- End Sidebar -->

            <div id="content-wrapper">
                <div class="page-content">

                    <!-- Header Bar -->
                    <%@include file="header.jsp" %>
                    <!-- End Header Bar -->

                    <!-- Main Content -->
                    <div class="row">

                        <div class="container">

                            <p> Please use the pseudonym code generated during <i>Create Pseudonym</i> process in order to fetch you certificate<br><br></p>
                            <div class="col-lg-4">
                                <form role="form" action="fetchCertificate" method="post">

                                    <div class="form-group">
                                        <label for="pwd">Pseudonym Code</label>
                                        <input type="password" class="form-control" name="pseudonymCode" id="pseudonymCode" placeholder="Enter pseudonym code">
                                    </div>
                                    <button  class="btn btn-info pull-right" style="margin:0 5px;"><span class="glyphicon glyphicon-download"></span> Fetch</button>
                                </form>

                            </div>

                            <div class="container">
                                <div class="row">
                                    <div class="row bs-wizard">
                                        <div class="col-md-12">
                                            <center>
                                                <%
                                                    //if (null != request.getAttribute("pseudonymKey")) {
                                                    //  out.println("<div class=\"alert alert-success\"><strong><span class=\"glyphicon glyphicon-ok\"></span> Certificate request successfully submitted! Please use this pseudonym code to fetch the certificate: <i>" + (String) request.getAttribute("pseudonymKey") + "</i></strong></div>");
                                                    //  } else {
                                                    //      out.println("<div class=\"alert alert-danger\"><span class=\"glyphicon glyphicon-remove\"></span><strong> Error! Please check all page inputs.</strong></div>");
                                                    //   }

                                                %>
                                            </center>
                                        </div>
                                    </div>
                                </div>
                            </div>





                        </div>
                    </div>
                    <!-- End Main Content -->

                </div><!-- End Page Content -->
            </div><!-- End Content Wrapper -->
        </div><!-- End Page Wrapper -->

        <script type="text/javascript" src="resources/js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap_new.js"></script>
        <script type="text/javascript" src="resources/js/jquery-ui.min.js"</script>
        <script type="text/javascript" src="resources/js/moment.js"></script>
        <script type="text/javascript" src="resources/js/cmui.min.js"></script>
        <script>
            //Do some stuff...
            $(document).ready(function () {
                $("#firstName").val($("#firstNameO").val());
                $("#lastName").val($("#lastNameO").val());
                $("#email").val($("#emailO").val());
            });
        </script>

    </body>
</html>
