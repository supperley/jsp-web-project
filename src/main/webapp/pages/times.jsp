<%--
  Created by IntelliJ IDEA.
  User: uktamnimatov
  Date: 6/16/22
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"   %>
<html>
<head>
    <title>Times</title>
</head>
<body>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:setLocale value="en-EN"/>
In English Format
Today: <fmt:formatDate value="${now}"/> <br>
<fmt:setLocale value="ru-RU"/>
In Russian Format
Today: <fmt:formatDate value="${now}"/> <br>
(short): <fmt:formatDate value="${now}" type="time" timeStyle="short"/> <br>
(medium): <fmt:formatDate value="${now}" type="time" timeStyle="medium"/> <br>
(long): <fmt:formatDate value="${now}" type="time" timeStyle="long"/> <br>
</body>
</html>
