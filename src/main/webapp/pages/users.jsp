
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REGISTRATION CONFIRMATION PAGE</title>
</head>
<body>
<h2>User successfully registered</h2>
<hr>
<form action="controller">
    <a href="${pageContext.request.contextPath}/">Login with new user</a>
</form>
<h4>Added User</h4>
${user}
${updated_user}
</body>
</html>
