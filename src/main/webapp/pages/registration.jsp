<%--
  Created by IntelliJ IDEA.
  User: uktamnimatov
  Date: 5/25/22
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/reg.css"/>
</head>
<%--<h2>Registration Page</h2>--%>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <input type="hidden" name="command" value="registration">

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" id="login" required value="">

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required value="">

        <label for="first_name"><b>First Name</b></label>
        <input type="text" placeholder="Enter First Name" name="first_name" id="first_name" required value="">

        <label for="last_name"><b>Last Name</b></label>
        <input type="text" placeholder="Enter Last Name" name="last_name" id="last_name" required value="">

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" id="email" required value="">

        <select name="role" id="role">
            <option value="CLIENT">Client</option>
            <option value="PHARMACIST">Pharmacist</option>
            <option value="DOCTOR">Doctor</option>
<%--        <input type="hidden" name="role" value="client">--%>
        </select>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
<%--        <button type="submit" >Register</button>--%>
        <input type="submit" value="Register" class="registerbtn">
    </div>
</form>

    <div class="container signin">
        <form action="${pageContext.request.contextPath}/controller">
        <p>Already have an account? <a href="${pageContext.request.contextPath}/">Sign in</a>.</p>
        </form>
    </div>
${user}
${unavailable_login}
${unavailable_email_address}
</body>
</html>
