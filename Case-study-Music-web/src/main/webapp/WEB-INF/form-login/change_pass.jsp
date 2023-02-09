<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/4/2022
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-image: url("https://firebasestorage.googleapis.com/v0/b/phulinh-fa18e.appspot.com/o/0106_hinh-nen-4k-may-tinh4.jpg?alt=media&token=99f90dfb-5ce5-41f5-9f25-006460811da9");
            background-size: cover;
            width: 100%;
            height: 100vh;
            background-position: center;


        }
    </style>
</head>
<body>
<h1>Change pass</h1>

<c:if test="${requestScope['message'] != null}">
    <p style="color: red">${requestScope['message']}</p>
</c:if>
<form method="post">
    <p style="color: #f4f5f7">Old password </p><br>
    <input type="password" name="old-pass"><br>
    <p style="color: #f4f5f7">New password</p><br>
    <input type="password" name="new-pass"><br>
    <p style="color: #f4f5f7">Repeat password</p><br>
    <input type="password" name="repeat-pass"><br>
    <button>Submit</button>
</form>
<c:if test="${user.name!=null}">
    <a class="nav-link " href="users?action=profile">
        <button type="button">Back</button>
    </a>
</c:if>
</body>
</html>
