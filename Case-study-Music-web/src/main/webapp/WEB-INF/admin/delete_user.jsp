<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/7/2022
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test='${requestScope["message"]!=null}'>
    <span style="color: blue">${requestScope["message"]}</span>
</c:if>
<form method="post">
    <label >USERNAME</label><br>
    <input value="${requestScope["user"].getUsername()}" ><br>

    <p>ARE YOU SURE DELETE?</p>
    <button>YES</button>
</form>
<a href="/">NO and Back Menu</a>
</body>
</html>
