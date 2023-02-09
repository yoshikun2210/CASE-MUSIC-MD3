<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/7/2022
  Time: 8:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <style>
        body {
            background-color: #ededed;
        }

        h1 {
            font-family: 'Open Sans', sans-serif;
            font-size: 13pt;
            font-weight: 600;
            text-transform: uppercase;
            color: white;
            cursor: default;
        }

        h4 {
            font-family: 'Open Sans', sans-serif;
            font-size: 8pt;
            font-weight: 400;
            cursor: default;
        }

        h2 {
            font-family: 'Open Sans', sans-serif;
            font-size: 13pt;
            font-weight: 300;
            color: white;
            cursor: default;
        }

        .player {
            height: 190px;
            width: 80%;
            background-color: #1e2125;
            position: absolute;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            top: 30%;
            left: 60%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
        }

        .player ul {
            list-style: none;
        }

        .player ul li {
            display: inline-block;
        }

        .cover {
            position: absolute;
            top: 0;
            left: 0;
        }

        .cover img {
            height: 190px;
            width: 190px;
        }

        .info h1 {
            text-align: center;
            margin-top: 15px;
            margin-left: 180px;
            line-height: 0;
        }

        .info h4 {
            text-align: center;
            margin-left: 180px;
            line-height: 20px;
            color: #636367;
        }

        .info h2 {
            text-align: center;
            margin-left: 180px;
        }

        .button-items {
            margin-left: 180px;
        }

        #slider {
            width: 182px;
            height: 4px;
            background: #151518;
            border-radius: 2px;
        }

        #slider div {
            width: 4px;
            height: 4px;
            margin-top: 1px;
            background: #ef6dbc;
            border-radius: 2px;
        }

        #timer {
            color: #494b4e;
            line-height: 0;
            font-size: 9pt;
            float: right;
            font-family: Arial, Sans-Serif;
        }

        .controls {
            margin-top: 20px;
        }

        .controls svg:nth-child(2) {
            margin-left: 5px;
            margin-right: 5px;
        }

        #play {
            padding: 0 3px;
            width: 30px;
            height: 30px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
        }

        #play g {
            stroke: #fefefe;
            stroke-width: 1;
            stroke-miterlimit: 10;
        }

        #play g path {
            fill: #fefefe;
        }

        #play:hover {
            cursor: pointer;
        }

        #play:hover g {
            stroke: #8f4da9;
            cursor: pointer;
        }

        #play:hover g path {
            fill: #9b59b6;
            cursor: pointer;
        }

        .step-backward {
            width: 18px;
            height: 18px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            margin-bottom: 5px;
        }

        .step-backward g polygon {
            fill: #fefefe;
        }

        .step-foreward {
            width: 18px;
            height: 18px;
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            margin-bottom: 5px;
        }

        .step-foreward g polygon {
            fill: #fefefe;
        }

        #pause {
            x: 0px;
            y: 0px;
            enable-background: new 0 0 25 25;
            width: 30px;
            height: 30px;
            position: absolute;
            margin-left: -38px;
            cursor: pointer;
        }

        #pause rect {
            fill: white;
        }

        #pause:hover rect {
            fill: #8f4da9;
        }

        .step-backward g polygon:hover, .step-foreward g polygon:hover {
            fill: #ef6dbc;
            cursor: pointer;
        }

        .social {
            text-align: center;
        }

        .twitter {
            color: #bdbdbd;
            font-family: sans-serif;
            text-decoration: none;
        }

        .twitter:hover {
            color: #ecf0f1;
        }

        .github {
            color: #bdbdbd;
            font-family: sans-serif;
            text-decoration: none;
        }

        .github:hover {
            color: #ecf0f1;
        }

        p {
            color: #bdbdbd;
        }

        #skip {
            float: right;
            margin-top: 10px;
        }

        #skip p {
            color: #2980b9;
        }

        #skip p:hover {
            color: #e74c3c;
            cursor: pointer;
        }

        .expend {
            padding: 0.5px;
            cursor: pointer;
        }

        .expend svg:hover g polygon {
            fill: #ef6dbc;
        }
    </style>
</head>
<body>

<div class="menu">
    <div class="menu1">
        <div class="logo"><img src="image/logo.jpg"></div>
        <div class="select">
            <div><i class="bi bi-house-door-fill"></i><a href="/"> &ensp;Home</a></div>
            <div><i class="bi bi-search"></i>&ensp;<a href="/">Search</a></div>
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
                <li><c:if test="${user.role.name == 'ADMIN' || user.role.name == 'PM'}">
                    <a href="band?action=create">Song</a>
                </c:if>
                    <a href="band?action=show">Band</a></li>
                <li><c:if test="${user.role.name == 'ADMIN' || user.role.name == 'PM'}">
                    <a href="singer?action=create">Song</a>
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
    <div class="grid-row">
        <form method="get">
            <div class="player" style="z-index: 1">
                <ul>
                    <li class="cover"><img src="${requestScope["song"].img}"/></li>
                    <li class="info">
                        <br>
                        <h1>${requestScope["song"].name}</h1>
                        <br>
                        <div class="button-items">
                            <audio id="music" preload="auto" loop="false">
                                <source src="${requestScope["song"].audio}" type="audio/mp3">
                                <source src="${requestScope["song"].audio}" type="audio/ogg">
                            </audio>
                            <div id="slider">
                                <div id="elapsed"></div>
                            </div>
                            <p id="timer">0:00</p>
                            <p>Like number: ${song.countLike}  Listen: ${song.listen}</p>
                            <div class="controls">
               <span class="expend"><svg class="step-backward" viewBox="0 0 25 25" xml:space="preserve">
                  <g><polygon points="4.9,4.3 9,4.3 9,11.6 21.4,4.3 21.4,20.7 9,13.4 9,20.7 4.9,20.7"/></g>
               </svg></span>

                                <svg id="play" viewBox="0 0 25 25" xml:space="preserve">
                   <defs>
                       <rect x="-49.5" y="-132.9" width="446.4" height="366.4"/>
                   </defs>
                                    <g>
                                        <circle fill="none" cx="12.5" cy="12.5" r="10.8"/>
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                              d="M8.7,6.9V18c0,0,0.2,1.4,1.8,0l8.1-4.8c0,0,1.2-1.1-1-2L9.8,6.5 C9.8,6.5,9.1,6,8.7,6.9z"/>
                                    </g>
               </svg>


                                <svg id="pause" viewBox="0 0 25 25" xml:space="preserve">
                  <g>
                      <rect x="6" y="4.6" width="3.8" height="15.7"/>
                      <rect x="14" y="4.6" width="3.9" height="15.7"/>
                  </g>
               </svg>

                                <span class="expend"><svg class="step-foreward" viewBox="0 0 25 25" xml:space="preserve">
                  <g><polygon points="20.7,4.3 16.6,4.3 16.6,11.6 4.3,4.3 4.3,20.7 16.7,13.4 16.6,20.7 20.7,20.7"/></g>
                </svg></span>
                            </div>
                            <c:if test="${requestScope['check']==null}">
                                <c:if test="${sessionScope['user']==null}">
                                    <div onclick="alert('Dang nhap truoc khi like')" style="cursor: pointer"><i class="bi bi-heart-fill"></i></div>
                                </c:if>
                                <c:if test="${sessionScope['user']!=null}">
                                    <a href="like?action=like&id=${song.id}"><i class="bi bi-heart-fill"></i></a>
                                </c:if>
                            </c:if>
                            <c:if test="${requestScope['check']!=null}">
                                <a href="like?action=dislike&id=${song.id}&idlike=${requestScope['check'].id}"><i class="bi bi-heartbreak-fill"></i></a>
                            </c:if>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>
</div>
<script>
    var music = document.getElementById("music");
    var playButton = document.getElementById("play");
    var pauseButton = document.getElementById("pause");
    var playhead = document.getElementById("elapsed");
    var timeline = document.getElementById("slider");
    var timer = document.getElementById("timer");
    var duration;
    pauseButton.style.visibility = "hidden";

    var timelineWidth = timeline.offsetWidth - playhead.offsetWidth;
    music.addEventListener("timeupdate", timeUpdate, false);

    function timeUpdate() {
        var playPercent = timelineWidth * (music.currentTime / duration);
        playhead.style.width = playPercent + "px";

        var secondsIn = Math.floor(((music.currentTime / duration) / 3.5) * 100);
        if (secondsIn <= 9) {
            timer.innerHTML = "0:0" + secondsIn;
        } else {
            timer.innerHTML = "0:" + secondsIn;
        }
    }

    playButton.onclick = function () {
        music.play();
        playButton.style.visibility = "hidden";
        pause.style.visibility = "visible";
    }

    pauseButton.onclick = function () {
        music.pause();
        playButton.style.visibility = "visible";
        pause.style.visibility = "hidden";
    }

    music.addEventListener("canplaythrough", function () {
        duration = music.duration;
    }, false);
</script>
</body>
</html>