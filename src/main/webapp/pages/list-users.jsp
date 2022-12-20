<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>List of Users</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <hr2>List of customers</hr2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Login</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr><c:forEach var="tempUser" items="${users}">

            <c:url var="updateLink" value="/customer/showFormForUpdate">
                <c:param name="customerId" value="${tempUser.id}"/>
            </c:url>

            <c:url var="deleteLink" value="/customer/delete">
                <c:param name="customerId" value="${tempUser.id}"/>
            </c:url>

            <tr>
                <td>${tempUser.login}</td>
                <td>${tempUser.firstName}</td>
                <td>${tempUser.lastName}</td>
                <td>${tempUser.email}</td>

                    <td>
                            <a href="${updateLink}">Update</a>
                            <a href="${deleteLink}" onclick="if (!(confirm('Are you sure to delete this customer'))) return false">Delete</a>
                    </td>
            </tr>
        </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
