<%--
  Created by IntelliJ IDEA.
  User: Minh Duc
  Date: 10/6/2022
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
  <progress value="0" max="100" id="uploader">0%</progress>
  <input type="file" value="upload" accept=".jpg" id="fileButton">
  <input type="text" name="avatar" id="avatar" style="display: none">
  <div id="imgDiv"></div>
  <progress value="0" max="100" id="uploaderMP3">0%</progress>
  <input type="file" value="upload" accept=".mp3" id="fileButtonMP3">
  <input name="mp3" type="text" id="mp3" style="display: none">
  <div id="imgDivMp3"></div>
<%--  <button type="submit">Upload</button>--%>
<script src="https://www.gstatic.com/firebasejs/4.2.0/firebase.js"></script>
<script type="text/javascript">
  const firebaseConfig = {
    apiKey: "AIzaSyCcgbDWgLu3CRA_JgQoOc45okxWJS-w6ds",
    authDomain: "ducmin-530bc.firebaseapp.com",
    projectId: "ducmin-530bc",
    storageBucket: "ducmin-530bc.appspot.com",
    messagingSenderId: "545765053033",
    appId: "1:545765053033:web:f0e4707dfb6549953308f8",
    measurementId: "G-PGFWSP0QMH"
  };
  firebase.initializeApp(firebaseConfig);
  var audio = '';
  // var fbBucketName = 'audios';
  var uploaderMP3 = document.getElementById('uploaderMP3');
  var fileButtonMP3 = document.getElementById('fileButtonMP3');
  fileButtonMP3.addEventListener('change', function (e) {
    console.log('file upload event', e);
    var file = e.target.files[0];
    const storageRef = firebase.storage().ref(file.name);
    // upload file
    var uploadTask = storageRef.put(file);
    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
            function (snapshot) {
              var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
              uploaderMP3.value = progress;
              console.log('Upload is ' + progress + '% done');
              switch (snapshot.state) {
                case firebase.storage.TaskState.PAUSED:
                  console.log('Upload is paused');
                  break;
                case firebase.storage.TaskState.RUNNING:
                  console.log('Upload is running');
                  break;
              }
            }, function (error) {
              switch (error.code) {
                case 'storage/unauthorized':
                  break;
                case 'storage/canceled':
                  break;
                case 'storage/unknown':
                  break;
              }
            }, function () {
              var downloadURL = uploadTask.snapshot.downloadURL;
              audio = downloadURL;
              console.log('downloadURL ===>', downloadURL);
              let divLocation = document.getElementById("imgDivMp3");
              var x = document.createElement("audio");
              if (x.canPlayType("audio/mpeg")) {
                x.setAttribute("src",downloadURL);
              } else {
                x.setAttribute("src",downloadURL);
              }
              x.setAttribute("controls", downloadURL);
              document.body.appendChild(x);
              divLocation.append(x);
              document.getElementById('mp3').value = downloadURL;
            });
  });
  var image = '';
  // firebase bucket name
  // REPLACE WITH THE ONE YOU CREATE
  // ALSO CHECK STORAGE RULES IN FIREBASE CONSOLE
  var fbBucketName = 'images';

  // get elements
  var uploader = document.getElementById('uploader');
  var fileButton = document.getElementById('fileButton');

  // listen for file selection
  fileButton.addEventListener('change', function (e) {

    // what happened
    console.log('file upload event', e);

    // get file
    var file = e.target.files[0];
    const storageRef = firebase.storage().ref(file.name);
    // upload file
    var uploadTask = storageRef.put(file);
    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
            function (snapshot) {
              var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
              uploader.value = progress;
              console.log('Upload is ' + progress + '% done');
              switch (snapshot.state) {
                case firebase.storage.TaskState.PAUSED: // or 'paused'
                  console.log('Upload is paused');
                  break;
                case firebase.storage.TaskState.RUNNING: // or 'running'
                  console.log('Upload is running');
                  break;
              }
            }, function (error) {

              switch (error.code) {
                case 'storage/unauthorized':
                  // User doesn't have permission to access the object
                  break;

                case 'storage/canceled':
                  // User canceled the upload
                  break;

                case 'storage/unknown':
                  // Unknown error occurred, inspect error.serverResponse
                  break;
              }
            }, function () {
              // Upload completed successfully, now we can get the download URL
              // save this link somewhere, e.g. put it in an input field
              var downloadURL = uploadTask.snapshot.downloadURL;
              image = downloadURL;
              console.log('downloadURL ===>', downloadURL);
              let divLocation = document.getElementById("imgDiv");
              let imgElement = document.createElement("img");
              imgElement.src = downloadURL
              imgElement.width = 100;
              imgElement.height = 100;
              console.log('pic ==', downloadURL)
              divLocation.append(imgElement);
              document.getElementById('avatar').value = downloadURL;
            });

  });
</script>
</body>
</html>