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

                        <%                            if (null != request.getParameter("msg")) {
                                String msg = request.getParameter("msg");
                                if (msg.equalsIgnoreCase("ROK")) {
                        %>
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Role assigned successfully!</strong>
                        </div>

                        <%
                        } else if (msg.equalsIgnoreCase("DROK")) {
                        %>                            
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Role deassigned successfully!</strong>
                        </div>

                        <%
                        } else if (msg.equalsIgnoreCase("NR")) {
                        %>                            
                        <div class="alert alert-warning">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>All available roles are assigned to this user!</strong>
                        </div>

                        <%
                        } else if (msg.equalsIgnoreCase("ERROR")) {
                        %>                            
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Problem Occured! Please try again!</strong>
                        </div>

                        <% }
                            }
                        %>

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
                                                <th>Email</th>
                                                <th>Roles</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <c:forEach items="${users}" var="user">

                                                <tr>
                                                    <td>${user.username}</td>
                                                    <td>${user.DN}</td>
                                                    <td>${user.email}</td>
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
        <script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
        <script type="text/javascript" src="resources/js/shield.min.js"></script>

        <script>

            $(document).ready(function () {

                $("body").tooltip({
                    selector: 'a[rel=tooltip]',
                    html: true,
                    placement: "right"

                });

            });
        </script>
    </body>
</html>
