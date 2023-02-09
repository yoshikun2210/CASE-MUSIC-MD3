
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1"%>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="songcss.css">

    <style>
        /* width */
        ::-webkit-scrollbar {
            width: 10px;
            z-index: -1;

        }

        /* Track */
        ::-webkit-scrollbar-track {
            background: rgba(0, 0, 0, 0.5);
        }

        /* Handle */
        ::-webkit-scrollbar-thumb {
            background: #888;
        }

        /* Handle on hover */
        ::-webkit-scrollbar-thumb:hover {
            background: #555;
        }

        .menu {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            display: flex;
            z-index: 1;

        }

        .menu1 {
            width: 241px;
            height: 100vh;
            padding-top: 24px;
            padding-left: 16px;
            background-color: #000001;
            color: #8c8c93;
            font-size: 15px;
            font-family: fantasy;
        }

        .navbar {
            display: grid;
            grid-template-columns: 30% 70%;
            width: 100%;
            background: rgba(0, 0, 0, 0.5);
            height: 64px;
            position: fixed;
            z-index: 10;
            margin-left: 241px;
        }

        .notification {
            background-image: linear-gradient(to right, purple, cornflowerblue);
            position: fixed;
            bottom: 0;
            width: 100%;
            height: 66px;
            z-index: 1;
        }

        .new {
            display: grid;
            grid-template-columns: 241px auto;
            background-color: #121212;
        }

        .logo {
            height: 66px;
        }

        .logo img {
            width: 100%;
        }

        .select {
            width: 100%;
            height: 120px;
            display: grid;
            grid-template-rows: repeat(3, 1fr);
            padding-top: 20px;
        }

        a {
            text-decoration: none;
            color: #8c8c93;
        }

        .my-menu {
            padding-top: 30px;
        }

        a:hover {
            text-decoration: none;
            color: white;
        }

        i {
            font-size: 150%;
        }

        i:hover {
            text-decoration: none;
            color: white;
        }

        .back a {
            display: flex;
            justify-content: center;
            align-items: center;
            list-style-type: none;
            float: left;
            height: 35px;
            width: 35px;
            background-color: #000001;
            border-radius: 50%;
            margin-left: 20px;
        }

        ul li {
            display: inline;
        }

        .my-taskbar {
            float: right;
            display: flex;
            align-items: center;
            justify-content: space-around;
            width: 800px;
        }

        .back {
            display: flex;
            align-items: start;
        }

        .login {
            width: 100%;
            float: right;
            display: flex;
            align-items: center;
            justify-content: space-around;
            font-size: 20px;
            font-family: fantasy;
        }

        .button-login {
            border-radius: 30px;
            height: 50px;
            width: 140px;
            border: none;
        }

        .my-text p {
            display: inline;
            text-align: center;
            color: white;
            font-family: Georgia, serif;
            font-size: 15px;
            /*padding: 7px;*/
            left: 50px;
            width: 100%;
            padding-left: 50px;

        }

        .button {
            width: 200px;
            float: right;
            text-align: center;
            font-family: fantasy;
        }

        .signup {
            border-radius: 50px;
            width: 150px;
            height: 30px;
            border: none;
        }

        button:hover {
            background-color: #cfd6db;
            font-family: fantasy;
        }

        .music {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            column-gap: 10px;
            height: 200px;
        }

        .topHit {
            display: grid;
            grid-template-rows: 30% auto;
        }

        .song {
            display: block;
            border-radius: 10px;
            background-color: #181818;
            text-align: center;
            margin: 0px 0px 16px;
            position: relative;
        }

        .grid-row {
            display: grid;
            margin-top: 80px;
            grid-template-rows: repeat(6, 300px);
            row-gap: 100px;
            padding-left: 60px;

        }

        body {
            background-color: #121212;
        }

        h2 {
            color: #cfd6db;
            font-family: fantasy;
        }

        p {
            color: #cfd6db;
            font-family: fantasy;
        }

        .play-song {
            width: 50px;
            height: 50px;
            background-color: aqua;
            border-radius: 100%;
            position: absolute;
            top: 70%;
            left: 50%;
            transform: translate(-50%, -50%);
            opacity: 0;
            transition-duration: 0.5s;
        }

        .song:hover .play-song {
            opacity: 1;
            top: 50%;
        }

        .bi-play-circle-fill {
            font-size: 50px;
            color: chartreuse;
            position: absolute;
            top: 70%;
            left: 50%;
            transform: translate(-50%, -50%);
            opacity: 0;
            transition-duration: 0.5s;
        }

        .song:hover .bi-play-circle-fill {
            width: 100px;
            height: 100px;
            opacity: 1;
            top: 50%;
        }

        .song:hover .song-shawdow {
            opacity: 0.1;
            z-index: 1;

        }

        .song-shawdow {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background: white;
            opacity: 0;
            border-radius: 10px;
        }

        .song img {
            border-radius: 10px;
            width: 80%;
            height: 50%;
            margin-top: 20px;
        }

        .listSong {
            position: relative;
            z-index: 1;
        }
    </style>
</head>
<body>

<div class="menu">
    <div class="menu1">
        <div class="logo"><img src="image/logo.jpg"></div>
        <div class="select">
            <div><i class="bi bi-house-door-fill"></i><a href="/"> &ensp;Home</a></div>
            <div><i class="bi bi-search"></i>&ensp;<a href="#aaaaa">Search</a></div>
            <div><i class="bi bi-music-note-list"></i><a href="song?action=playsong">&ensp; Your Library</a></div>
        </div>
        <div class="my-menu">
            <div><i class="bi bi-file-plus-fill"></i><a href="#aaa">&ensp; Create Playlist</a></div>
            <div><i class="bi bi-chat-square-heart-fill"></i><a href="#aammm"> &ensp;Love Song</a></div>
        </div>
    </div>
    <div></div>
</div>
<div class="new">
    <div class="navbar">
        <div class="back">
            <ul>
            </ul>
        </div>
        <div class="my-taskbar">
            <ul class="login">
                <li><c:if test="${user.roles.name == 'ADMIN' || user.roles.name == 'PM'}">
                    <a href="band?action=create"> Create Band </a>
                </c:if>
                    <a href="band?action=show"> Show Band</a></li>
                <li><c:if test="${user.role.name == 'ADMIN' || user.role.name == 'PM'}">
                    <a href="singer?action=create">Singer</a>
                </c:if>
                    <a href="singer?action=list">Singer</a></li>
                <li> <c:if test="${user.role.name == 'ADMIN' || user.role.name == 'PM'}">
                    <a href="song?action=create">Song</a>
                </c:if>
                    <a href="song?action=list">Song</a></li>
                <li>
                    <c:if test="${user.role.name == 'ADMIN' || user.role.name == 'PM'}">
                        <a href="category?action=create">Category</a>
                    </c:if>
                    <a href="category?action=show">Category</a>
                </li>
                <li style="font-size: 25px; color: white">|</li>
                <li><c:if test="${sessionScope['user']==null}">
                    <a href="users?action=register">Sign up</a>
                </c:if>
                    <c:if test="${sessionScope['user']!=null}">
                        <a href="users?action=profile">Profile</a>
                    </c:if> </li>
                <li>
                    <c:if test="${sessionScope['user']==null}">
                        <a href="users?action=login"> <button class="button-login" >LOG IN</button></a>
                    </c:if>
                    <c:if test="${sessionScope['user']!=null}">
                        <img src="${sessionScope['user'].avatar}">
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
    <div></div>
    <div class="grid-row" style="color: #f4f5f7; z-index: 1">
        <div class="music">
            <c:forEach var="sg" items='${requestScope["listSinger"]}'>
                <a style="z-index: 1 " href="singer?action=detail&id=${sg.id}">
                    <div class="song" style="width: 186px; height: 276px">
                        <img class="img-song" src="${sg.img}">
                        <p></p>
                        <p>
                                ${sg.name}
                        </p>
                        <i class="bi bi-play-circle-fill" style="width: 50px;height: 50px"></i>
                        <div class="song-shawdow"></div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
</div>
<c:if test="${sessionScope['user']==null}">
    <div class="notification">
        <div class="my-text">
            <p>PREVIEW OF SPOTIFY</p><br>
            <p>Sign up to get unlimited songs and podcasts with occasional ads. No credit card needed</p>
            <div class="button">
                <button class="signup" href="#aabbb"> SIGN UP FREE</button>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${sessionScope['user']!=null}"><div></div></c:if>
<h1 class="test">Test</h1>
</body>
</html>
