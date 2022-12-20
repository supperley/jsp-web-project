
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"   %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="message"/>
<html lang="${sessionScope.locale}">
<head>
    <title>Home Page</title>
</head>
<body>
<fmt:message key="label.welcome" />
${username} <br>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="find_all_users">
    <input type="submit" value="Find All Users">
</form>

<hr>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="change_locale">
<%--    <input type="hidden" name="locale" id="locale">--%>
    <select id="locale" name="locale">
        <option value="ru_RU">Russian</option>
        <option value="en_EN">English</option>
    </select>
    <input type="submit" value="Change Locale">
</form>
<hr>

<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">
</form>

<a href="${pageContext.request.contextPath}/pages/password-update.jsp">Update Password</a>

<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="find_all_medicine">
    <input type="submit" value="Find All Medicines">
</form>
<fmt:message key="footer.copyright" />
${password_change_message}
</body>
</html>
