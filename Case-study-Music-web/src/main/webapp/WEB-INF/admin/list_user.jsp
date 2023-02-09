<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/7/2022
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="user?action=create">Create User</a>
</div>
<table border="1" width="100%">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>username</th>
        <th>email</th>
        <th>password</th>
        <th>avatar</th>
        <th>role</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${requestScope['list_user']}" var="user">
        <tr align="center">
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>
                <img src="${user.avatar}" alt="avatar" width="40" height="40">
            </td>
            <td>${user.role.name}</td>
            <td>
                <a href="user?action=edit&id=${user.id}">Edit</a>
            </td>
            <td>
                <a href="user?action=delete&id=${user.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
