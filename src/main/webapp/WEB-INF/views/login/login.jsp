<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/23
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <style>
        body {
            background-color: #eeeeee;
        }
    </style>
</head>
<body>
<jsp:include page="../include/include.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="margin-top: 70px">
            <h2 class="text-center">欢迎登录</h2>
            <form id="loginForm" role="form" method="post" action="/login">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" value="${username}" class="form-control" id="username" name="username" placeholder="用户名">
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                    <span id="warnSpan" style="color: red;" class="help-block"></span>
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
                    <div class="form-group col-xs-6">
                        <span style="color: red">${result}</span>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<script src="../../../static/js/expression/login/login.js"></script>
</body>
</html>