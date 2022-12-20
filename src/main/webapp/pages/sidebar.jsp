<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message"/>
<!doctype html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sidebars for Users</title>

    <link href="${pageContext.request.contextPath}/sources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/sidebars.css" rel="stylesheet">
</head>
<body>
<script src="${pageContext.request.contextPath}/sources/js/bootstrap.bundle.min.js"></script>
<%@ include file="../sources/parts/sidebar.jsp"%>

</body>
<div class="container">
    <%@ include file="../sources/parts/footer.jsp"%>
</div>
</html>
