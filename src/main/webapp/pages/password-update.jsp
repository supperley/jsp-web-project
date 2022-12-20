
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Change</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="update_password">
    <input type="hidden" name="login" value="${username}">
    <input type="submit" value="Update Password">
<div class="col-xxl-6">
    <div class="bg-secondary-soft px-4 py-5 rounded">
        <div class="row g-3">
            <h4 class="my-4">Change Password</h4>
            <!-- Old password -->
            <div class="col-md-6">
                <label for="current_password" class="form-label">Current password *</label>
                <input type="password" name="current_password" placeholder="enter current password... " id="current_password">
<%--                <input type="password" class="form-control" id="exampleInputPassword1">--%>
            </div>
            <!-- New password -->
            <div class="col-md-6">
                <label for="new_password" class="form-label">New password *</label>
                <input type="password" name="new_password" placeholder="enter new password... " id="new_password">
<%--                <input type="password" class="form-control" id="exampleInputPassword2">--%>
            </div>
            <!-- Confirm password -->
            <div class="col-md-12">
                <label for="confirm_new_password" class="form-label">Confirm Password *</label>
                <input type="password" name="confirm_new_password" placeholder="repeat new password... " id="confirm_new_password">
<%--                <input type="password" class="form-control" id="exampleInputPassword3">--%>
            </div>
        </div>
    </div>
</div>
</form>
${password_change_message}
</body>
</html>
