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
        <title>SHIELD | Roles</title>
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
                                if (msg.equalsIgnoreCase("NROK")) {
                        %>
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Role added successfully!</strong>
                        </div>

                        <%
                        } else if (msg.equalsIgnoreCase("ERROR")) {
                        %>                            
                        <div class="alert alert-danger">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Problem Occured! Please try again!</strong>
                        </div>

                        <%
                        } else if (msg.equalsIgnoreCase("DROK")) {
                        %>                            
                        <div class="alert alert-success">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            <strong>Role deleted successfully!</strong>
                        </div>

                        <% }
                            }
                        %>

                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading" data-original-title>
                                    <h5><i class="fa fa-star-half-o"></i><span class="break"></span> Roles</h5> 
                                    <div class="btn-group pull-right" style="margin-top:-34px;">
                                        <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#newRoleModal" >Add Role</button>
                                    </div>
                                </div>
                                <div class="panel-body">

                                    <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                        <thead>
                                            <tr>
                                                <th>Role</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <c:forEach items="${roles}" var="role">

                                                <tr>
                                                    <td>${role.role}</td>
                                                    <td>
                                            <center>
<!--                                                <div class='form-inline' style='display:inline;'>
                                                    <input type='hidden' value='${role.role}' name='role' />
                                                    <button class='btn btn-primary' type='button' onclick="editRoleFunction();"><i class='fa fa-pencil'></i> </button>
                                                </div>-->
                                                <form class='form-inline' action='deleteRole' method='post' style='display:inline;'>
                                                    <input type='hidden' value='${role.role}' name='role' />
                                                    <button class='btn btn-danger' type='submit'><i class='fa fa-trash-o'></i> </button>
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

        <div class="modal fade" id="newRoleModal" name="newRoleModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Add Role</h4>
                    </div>
                    <div class="modal-body form-group" >
                        <form action="addRole" method="post" class="form-horizontal" id="addRoleForm">
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="text-input">Role</label>
                                <div class="col-md-9">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="roleName" name="roleName"  value="" placeholder="" >
                                    </div>
                                </div>
                            </div>
                        </form>
                        <p id="newRoleNotificationModal" style="visibility:hidden;">
                        </p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" aria-expanded="false" data-dismiss="modal">
                            Reset
                        </button>
                        <button type="button" class="btn btn-primary" aria-expanded="false" onclick="addRoleFunction();">
                            Add
                        </button>
                    </div>
                </div>
            </div> 
        </div>              

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
                               
                               $("#roleName").keypress(function (event) {
                                    if (event.keyCode === 13) {
                                        addRoleFunction();
                                    }
                                });

                           });
                           
                           function addRoleFunction() {
                               var roleName = $("#roleName").val();
                                if (roleName === '') {
                                    $('#newRoleNotificationModal').val("");
                                    $('#newRoleNotificationModal').text("");
                                    $('#newRoleNotificationModal').css("visibility", "visible");
                                    var notification = '<label class="msg">Please fill the role name field!</label>';
                                    $('#newRoleNotificationModal').append(notification);
                                } else {
                                    $("#addRoleForm").submit();
                                }
                           }
                           
        </script>
    </body>
</html>
