<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="sidebar-wrapper">
    <ul class="sidebar">
        <li id="toggle-sidebar" class="sidebar-main">
            <a href="dashboard">
                <img src="resources/img/FISTARSHIELD_SIDEBAR.png"/>
            </a>
        </li>
        <li class="sidebar-title"><span>NAVIGATION</span></li>

        <% if (null != request.getAttribute("userRole") && request.getAttribute("userRole").toString().equalsIgnoreCase("admin")) {
        %>
        <li class="sidebar-list">
            <a href="users">Users<span class="menu-icon fa fa-users"></span></a>
        </li>
        <li class="sidebar-list">
            <a href="roles">Roles<span class="menu-icon fa fa-star-half-o"></span></a>
        </li>
        <%
        } else {
        %>
        <li class="sidebar-list">
            <a href="createPseudonym">Create Pseudonym<span class="menu-icon fa fa-tasks"></span></a>
        </li>
        <li class="sidebar-list">
            <a href="fetchCertificate.jsp">Fetch Certificate<span class="menu-icon fa fa-file"></span></a>
        </li>
        <!--<li class="sidebar-title separator"><span>QUICK LINKS</span></li>-->
        <%
            }
        %>
    </ul>
    <div class="sidebar-footer">
        <div class="col-xs-12">
            <a href="https://www.fi-star.eu/fi-star.html" target="_blank"> &copy; 2015 - FISTAR</a>
        </div>

    </div>

</div>
