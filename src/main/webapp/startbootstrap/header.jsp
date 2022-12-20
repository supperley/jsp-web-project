<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15/12/2022
  Time: 08:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title>Topbar</title>
</head>
<body>
<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

  <!-- Sidebar Toggle (Topbar) -->
  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
    <i class="fa fa-bars"></i>
  </button>

  <!-- Topbar Navbar -->
  <ul class="navbar-nav ml-auto">
    <!-- Nav Item - Alerts -->
    <li class="nav-item dropdown font-weight-bolder">
      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
         role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <fmt:message key="language" />
      </a>
      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">На русском</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_US">In English</a>
      </div>
    </li>

    <div class="topbar-divider d-none d-sm-block"></div>

    <!-- Nav Item - User Information -->
    <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">${username}</span>
        <img class="img-profile rounded-circle"
             src="${pageContext.request.contextPath}/startbootstrap/img/undraw_profile.svg" alt="Avatar">
      </a>
      <!-- Dropdown - User Information -->
      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
           aria-labelledby="userDropdown">
        <a class="dropdown-item"
           href="${pageContext.request.contextPath}/startbootstrap/profile.jsp">
          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
          <fmt:message key="edit.profile" />
        </a>
        <div class="dropdown-divider"></div>
        <form action="${pageContext.request.contextPath}/controller">
          <input type="hidden" name="command" value="logout">
          <input class="dropdown-item" data-toggle="modal" data-target="#logoutModal"
                 type="submit" value="<fmt:message key="logout" />">
        </form>
      </div>
    </li>
  </ul>
</nav>
<!-- End of Topbar -->
</body>
</html>
