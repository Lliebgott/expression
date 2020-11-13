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
    <style>
    .box{
        text-align: center;
    }
    .t_img{
        width: 75px;
        height: 75px;
        border-radius: 100%;
    }
    .s_box{
        border-radius: 10px;
        /*width: 500px;*/
        height: auto;
        border: 1px #c2c2d6 solid;
        /*margin-left: 430px;*/
        background-color: #4cb7cc ;
        /*position:fixed;*/
        display: none;
    }
    .s_box img{
        width: 61px;
        height: 61px;
        margin: 5px;
        border:1px solid #ccc;
    }
    .s_box img:hover{
        border-color:red ;
        transform: scale(1.25);
        transition: .5s;
    }
    .header{
        width: 100%;
        text-align: center;
        font-size: 14px;
        margin-top: 30px;
    }
    .close{
        color:#000;
        font-size: 21px;
        opacity: .7;
        position:absolute;
        right:8px;
        top:1px;
        cursor: pointer;
    }
    .bt_box .gb{
        display:inline-block;
        width:80px;
        height:35px;
        border-radius: 10px;
        background:#f3f3f3;
        color:#444;
        line-height: 35px;
        margin: 10px;
    }
    .bt_box .queren{
        display:inline-block;
        width:80px;
        height:35px;
        border-radius: 10px;
        background:#1a53ff;
        color:white;
        line-height: 35px;
    }
    .bt_box .gb:hover,.bt_box .queren:hover{
        box-shadow: 0 6px 10px 0 rgba(0,0,0,0.24),0 9px 25px 0 rgba(0,0,0,0.19);
    }
</style>
</head>
<body>
<jsp:include page="../include/include.jsp"></jsp:include>
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
                    <form id="registerForm" role="form" method="post" action="/user/save">
                        <div class="box">
                            <div class="ft_img" id="ft_img">
                                <img src="../../../static/image/header/h1.png" class="t_img"/>
                            </div>
                            <div class="s_box" id="sbox">
                                <div class="header">
                                    <p>设置头像</p>
                                    <span class="close" id="close">x</span>
                                </div>
                                <hr width="80%" color="#e0e0eb"/>
                                <div id="t_img">
                                    <img src="../../../static/image/header/h1.png" />
                                    <img src="../../../static/image/header/h2.png" />
                                    <img src="../../../static/image/header/h3.png" />
                                    <img src="../../../static/image/header/h4.png" />
                                    <img src="../../../static/image/header/h5.png" />
                                    <img src="../../../static/image/header/h6.png" />
                                    <img src="../../../static/image/header/h7.png" />
                                </div>
                                <div align="center">
                                    <input type="file" name="file0" id="file0" accept="image/*"/>
                                </div>
                                <hr width="80%" color="#e0e0eb"/>
                                <div class="bt_box">
                                    <a class="gb" href="javascript:" id="hide">关闭</a>
                                    <%--<a class="queren" href="javascript:" id="but">保存头像</a>--%>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" >
                            <label for="name">姓名:</label>
                            <input type="text" name="headFile" id="headFile" value="__h1.png" hidden/>
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
                        <div class="form-group">
                            <label for="sex">性别:</label>
                            <select name="sex" id="sex" class="form-control">
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="birthStr">生日:</label>
                            <input type="date" class="form-control" name="birthStr" id="birthStr"  placeholder="请输入出生日期" />
                        </div>
                        <div class="form-group">
                            <label for="label">心情:</label>
                            <textarea type="text" class="form-control" name="label" id="label"  placeholder="请输入心情"></textarea>
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
<script src="../../../static/js/expression/login/register.js"></script>
</body>
</html>