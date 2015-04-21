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

                                <div class="col-xs-4 bs-wizard-step disabled"><!-- complete -->
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
                            <form role="form" method="post">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="InputName">First Name</label>
                                        <div class="input-group">
                                            <input disabled="true" type="text" value="${pseudonym.firstName}" class="form-control" name="InputName" id="InputName" required>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="InputName">Last Name</label>
                                        <div class="input-group">
                                            <input  disabled="true" type="text" value="${pseudonym.lastName}" class="form-control" name="InputName" id="InputName"  required>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="InputEmail">Email</label>
                                        <div class="input-group">
                                            <input  disabled="true" type="email" value="${pseudonym.email}" class="form-control" id="InputEmailFirst" name="InputEmail"  required>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>
                                        </div>
                                    </div>
                                    <input type="submit" name="submit" id="submit" value="Next" class="btn btn-info pull-right">
                                </div>


                                <input type="hidden" name="nextStep" id="nextStep" value="step2" required>
                            </form>
                            <!--                            <div class="col-lg-5 col-md-push-1">
                                                            <div class="col-md-12">
                                                                <div class="alert alert-success">
                                                                    <strong><span class="glyphicon glyphicon-ok"></span> Success! Message sent.</strong>
                                                                </div>
                                                                <div class="alert alert-danger">
                                                                    <span class="glyphicon glyphicon-remove"></span><strong> Error! Please check all page inputs.</strong>
                                                                </div>
                                                            </div>
                                                        </div>-->
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

    });
</script>

</body>
</html>
