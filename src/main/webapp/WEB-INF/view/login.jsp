<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<jsp:include page="include/include.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="margin-top: 70px">
            <h2 class="text-center">欢迎登录</h2>
            <form id="loginForm" role="form" method="post"action="/login" >
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> 记住
                    </label>
                </div>
                <div class="form-group col-xs-12">
                    <div class="form-group col-xs-6">
                        <button type="submit" name="submit" class="btn btn-success btn-block">登录</button>
                    </div>
                    <div class="form-group col-xs-6">
                        <button type="button" onclick="register()" id="register-btn" class="btn btn-success btn-block">注册</button>
                    </div>
                </div>

                <div class="form-group col-xs-12" align="center">
                    <c:if test="${not empty param.error}">
                        <span style="color: red">用户名或密码错误,请重新输入</span>
                    </c:if>
                    <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message!='Bad credentials'}">
                        <span style="color: red">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</span>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../../static/js/expression/login.js"></script>
</body>
</html>