<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/10/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>FORM CREATE BAND</h1>
<c:if test='${requestScope["message"]!=null}'>
    <span style="color: blue">${requestScope["message"]}</span>
</c:if>
<form  method="post">
    <label>NAME</label><br>
    <input type="text" name="name"><br>
    <label>YEAR</label><br>
    <input type="text" name="year"><br>
    <button>Create</button><br>
    <a href="/">Back Menu</a>
</form>
</body>
</html>
