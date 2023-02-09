<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/4/2022
  Time: 3:53 PM
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
    <input type="text" name="name">
    <button>Create</button>
</form>
<table border="1" style="width: 100%">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>
    <c:forEach var="ct" items='${requestScope["category"]}'>
    <tr>
        <td><a href="category?action=detail&id=${ct.id}">${ct.id}</a></td>
        <td>${ct.name}</td>
        <td>
            <a href="category?action=edit&id=${ct.id}">Edit</a>
        </td>
        <td><a href="category?action=delete&id=${ct.id}">Delete</a>
        </td>
    </tr>
    </c:forEach>
</body>
</html>
