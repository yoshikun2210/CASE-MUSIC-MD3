<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10/6/2022
  Time: 5:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-image: url("https://firebasestorage.googleapis.com/v0/b/phulinh-fa18e.appspot.com/o/hinh-nen-dep-cho-laptop-1366x768-13.jpg?alt=media&token=ff79003a-93d1-4c47-b809-a6f6e8dabeba");
            background-size: cover;
            width: 100%;
            height: 100vh;
            background-position: center;


        }
    </style>
</head>
<body>
<div style="text-align: center; color: orangered">
<c:if test="${requestScope['message'] != null}">
    <p style="color: red">${requestScope['message']}</p>
</c:if>
<form method="post">
    Old name <br>
    <input type="text" name="oldName"><br>
    New name<br>
    <input type="text" name="newName"><br>
    Old email <br>
    <input type="text" name="oldEmail"><br>
    New email<br>
    <input type="text" name="newEmail"><br>
    <button>Submit</button>
</form>
<c:if test="${user.name!=null}">
    <a class="nav-link " href="users?action=profile" ><button type="button">Back</button> </a>
</c:if>
</div>
</body>
</html>
