<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/4/2022
  Time: 8:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page='../bootstrap/bootstrap.jsp'>
    <jsp:param name="articleId" value=""/>
</jsp:include>
<html>
<head>
    <title>Title</title>
    <style>
        .bg-image-vertical {
            position: relative;
            overflow: hidden;
            background-repeat: no-repeat;
            background-position: right center;
            background-size: auto 100%;
        }



        @media (min-width: 1025px) {
            .h-custom-2 {
                height: 100%;
            }
        }
    </style>
</head>
<body>
<section class="vh-100">
    <div class="container-fluid" style="background-color: #84fab0">
        <div class="row">
            <div class="col-sm-6 text-black">


                <div class="d-flex align-items-top h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

                    <form style="width: 23rem;" method="post">

                        <h3 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Log in</h3>
                        <c:if test='${requestScope["message"]!=null}'>
                            <span style="color: red">${requestScope["message"]}</span>
                        </c:if>

                        <div class="form-outline mb-4">
                            <input name="username" type="text" id="form2Example18" class="form-control form-control-lg" />
                            <label class="form-label" for="form2Example18">Username</label>
                        </div>

                        <div class="form-outline mb-4">

                            <input name="password" type="password" id="form2Example28" class="form-control form-control-lg" />
                            <label class="form-label" for="form2Example28">Password</label>
                        </div>

                        <div class="pt-1 mb-4">
                            <button  class="btn btn-info btn-lg btn-block" type="submit">Login</button>

                        </div>


                        <%--                        <p class="small mb-5 pb-lg-2"><a class="text-muted" href="#!">Forgot password?</a></p>--%>
                        <p>Don't have an account? <a href="users?action=register" class="link-info">Register here</a></p>

                    </form>

                </div>

            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">

                <img src="https://firebasestorage.googleapis.com/v0/b/phulinh-fa18e.appspot.com/o/1792336.jpg?alt=media&token=aa3456d5-df97-48d7-a2cc-2b1f408ea0a5"
                     alt="Login image" width="100%" height="100%" style="" >
            </div>
        </div>
    </div>
</section>
</body>
</html>
