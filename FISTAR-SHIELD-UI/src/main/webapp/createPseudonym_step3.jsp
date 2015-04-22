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


                            <div class="row bs-wizard" style="border-bottom:0;">

                                <div class="col-xs-4 bs-wizard-step complete">
                                    <div class="text-center bs-wizard-stepnum">Step 1</div>
                                    <div class="progress"><div class="progress-bar"></div></div>
                                    <a href="#" class="bs-wizard-dot"></a>
                                    <div class="bs-wizard-info text-center">Complete form with real data</div>
                                </div>

                                <div class="col-xs-4 bs-wizard-step complete"><!-- complete -->
                                    <div class="text-center bs-wizard-stepnum">Step 2</div>
                                    <div class="progress"><div class="progress-bar"></div></div>
                                    <a href="#" class="bs-wizard-dot"></a>
                                    <div class="bs-wizard-info text-center">Validate generated pseudonym</div>
                                </div>

                                <div class="col-xs-4 bs-wizard-step complete"><!-- complete -->
                                    <div class="text-center bs-wizard-stepnum">Step 3</div>
                                    <div class="progress"><div class="progress-bar"></div></div>
                                    <a href="#" class="bs-wizard-dot"></a>
                                    <div class="bs-wizard-info text-center">Create certificate request</div>
                                </div>
                            </div>

                        </div>
                    </div>




                    <div class="container">
                        <div class="row">
                            <div class="row bs-wizard">
                                <div class="col-md-12">
                                    <center>
                                    <%
                                        if (null !=  request.getAttribute("pseudonymKey")) {
                                            out.println("<div class=\"alert alert-success\"><strong><span class=\"glyphicon glyphicon-ok\"></span> Certificate request successfully submitted! Please use this pseudonym code to fetch the certificate: <i>" + (String) request.getAttribute("pseudonymKey") + "</i></strong></div>");
                                        } else {
                                            out.println("<div class=\"alert alert-danger\"><span class=\"glyphicon glyphicon-remove\"></span><strong> Error! Please check all page inputs.</strong></div>");
                                        }

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
