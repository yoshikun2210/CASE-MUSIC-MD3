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
<h1><%="FORM CREATE BAND" %>
</h1>
<br/>
<a href="band?action=create">Create Band</a>
<form method="post">
    <input type="text" name="search">
    <button type="submit">Search</button>
</form>
<table border="1" style="width: 100%">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>YEAR</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>

    <c:forEach var="st" items='${requestScope["listBand"]}'>
        <tr>
            <td><a href="band?action=detail&id=${st.id}">${st.id}</a></td>
            <td>${st.name}</td>
            <td>${st.year}</td>
            <td>
                <a href="band?action=edit&id=${st.id}">Edit</a>
            </td>
            <td>
                <a href="band?action=delete&id=${st.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
