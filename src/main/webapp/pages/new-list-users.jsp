<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New list of users</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap4.min.css">
</head>
<body>
<div class="alert alert-success">
    <c:if test="${not empty user_deleted}">
    <strong>Success! Deleted User: ${user_deleted}</strong> <%--Indicates a successful or positive action.--%>
    </c:if>
</div>
<table id="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>Login</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <c:forEach var="tempUser" items="${users}">

<%--    <c:url var="updateLink" value="/customer/showFormForUpdate">--%>
<%--        <c:param name="customerId" value="${tempUser.id}"/>--%>
<%--    </c:url>--%>

<%--    <c:url var="deleteLink" value="/customer/delete">--%>
<%--        <c:param name="customerId" value="${tempUser.id}"/>--%>
<%--    </c:url>--%>

    <tr>
        <td>${tempUser.login}</td>
        <td>${tempUser.firstName}</td>
        <td>${tempUser.lastName}</td>
        <td>${tempUser.email}</td>

        <td>
<%--            <form action="${pageContext.request.contextPath}/controller" method="post">--%>
<%--                <input type="hidden" name="userId" value="${tempUser.id}">--%>
<%--                <input type="hidden" name="command" value="update_user">--%>
<%--                <input type="submit" value="Update">--%>
<%--                    &lt;%&ndash;                <a href="${updateLink}">Update</a>&ndash;%&gt;--%>
<%--            </form>--%>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="user_id" value="${tempUser.id}">
                <input type="hidden" name="command" value="delete_user">
                <input type="submit" value="Delete" onclick="if (!(confirm('Are you sure to delete this medicine'))) return false">
                    <%--                <a href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this medicine'))) return false">Delete</a>--%>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>
</body>
</html>
