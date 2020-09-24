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
    <title>Expression Register</title>
    <script src="../../../static/js/jquery-3.3.1.min.js"></script>
    <link href="../../../static/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../../static/js/bootstrap.min.js"></script>
    <script src="../../../static/js/bootstrap/alert/alert.js"></script>
    <link href="../../../static/css/bootstrapValidator.css" rel="stylesheet" />
    <script src="../../../static/js/validator/bootstrapValidator.min.js"></script>
    <script src="../../../static/js/distpicker/distpicker.data.js"></script>
    <script src="../../../static/js/distpicker/distpicker.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <!--放在一个大的col-md-12里-->
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <!--面板-->
            <div class="panel panel-primary">
                <!--panel-heading-->
                <div class="panel-heading">
                    <!--panel-title-->
                    <h3 class="panel-title">Expression Register
                        <!--用到了pull-right-->
                        <span class="glyphicon glyphicon-pushpin pull-right" aria-hidden="true"></span>
                    </h3>
                </div>
                <!--panel-body-->
                <div class="panel-body">
                    <!--把其他的组件放到panel-body里面-->
                    <form id="registerForm"  role="form" method="post" action="user/addUser">
                        <div class="form-group" >
                            <label for="name">名字:</label>
                            <input type="text" class="form-control" name="name" id="name"  placeholder="请输入名字" />
                        </div>
                        <div class="form-group">
                            <label for="username">账号:</label>
                            <input type="text" class="form-control" name="username" id="username"  placeholder="请输入账号" />
                        </div>
                        <div class="form-group" >
                            <label for="password">密码:</label>
                            <input type="password" class="form-control" name="password" id="password"  placeholder="请输入密码" />
                        </div>
                        <div class="form-group" >
                            <label for="repassword">核对密码:</label>
                            <input type="password" class="form-control" name="repassword" id="repassword"  placeholder="请输入密码" />
                        </div>
                        <div class="form-group" >
                            <label for="mobile">手机号:</label>
                            <input type="text" class="form-control" name="mobile" id="mobile"  placeholder="请输入手机号" />
                        </div>
                        <div class="form-group" >
                            <label for="email">邮箱:</label>
                            <input type="email" class="form-control" name="email" id="email"  placeholder="请输入邮箱" />
                        </div>
                        <div id="distpicker" class="form-inline">
                            <label for="province">省:</label>
                            <select name="province" id="province" class="form-control"></select>
                            <label for="city">市:</label>
                            <select name="city" id="city" class="form-control"></select>
                            <label for="district">区:</label>
                            <select name="district" id="district" class="form-control"></select>
                        </div>
                        <div class="form-group" style="margin-top: 10px;">
                            <label for="address">街道地址:</label>
                            <input type="text" class="form-control" name="address" id="address"  placeholder="请输入街道地址" />
                        </div>
                        <div align="center">
                            <button type="submit" name="submit" class="btn btn-primary block full-width m-b">注&nbsp;&nbsp;册</button>
                            <button type="button" name="back" class="btn" onclick="goBack()">返&nbsp;&nbsp;回</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<script src="../../../static/js/expression/register/register.js"></script>
</body>
</html>