<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="UBITECH">
        <title>SHIELD | Users</title>
        <link rel="shortcut icon" href="resources/img/shield.png">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link href="resources/css/jquery-ui.min.css" rel="stylesheet">
        <link href="resources/css/shield.min.css" rel="stylesheet">
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



                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading" data-original-title>
                                    <h5><i class="fa fa-users"></i><span class="break"></span> Users</h5>  
                                </div>
                                <div class="panel-body">

                                    <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                        <thead>
                                            <tr>
                                                <th>Username</th>
                                                <th>DN</th>
                                                <th>Roles</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <c:forEach items="${users}" var="user">

                                                <tr>
                                                    <td>${user.username}</td>
                                                    <td>${user.DN}</td>
                                                    <td>${user.idmRoles}</td>
                                                    <td>
                                                        <center>
                                                            <form class='form-inline' action='assignIDMRole' method='post' style='display:inline;'>
                                                                <input type='hidden' value='${user.userID}' name='id' />
                                                                <button class='btn btn-primary' type='submit'><i class='fa fa-plus'></i> </button>
                                                            </form>
                                                        </center>
                                                   </td>
                                                </tr>

                                        </c:forEach>
                                        </tbody>
                                    </table>            
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
        <script type="text/javascript" src="resources/js/shield.min.js"></script>
    </body>
</html>
