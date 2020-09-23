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

    <script type="text/javascript" src="${BASE_PATH }/static/js/jquery-3.3.1.min.js"></script>
    <link href="${BASE_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${BASE_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #eeeeee;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="margin-top: 70px">
            <h2 class="text-center">欢迎登录</h2>
            <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">邮箱</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    <span class="help-block"></span>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> 记住
                    </label>
                </div>
                <button type="submit" id="login-btn" class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
</div>
<script>
    // 给登录按钮绑定点击事件
    $('#login-btn').click(function () {
        // 定义一个是否允许提交的标志位
        var flag = true;
        // 1. 找到登录框中所有的input框，判断值是否为空
        $('form input').each(function () {
            var value = $(this).val();
            if (value.length===0){
                // 2. 为空就显示提示信息
                // 2.1 给下面的span标签设置文本提示信息
                var errMsg = $(this).prev().text() + '不能为空';
                $(this).next().text(errMsg);
                // 2.2 给父标签设置has-error的样式
                $(this).parent().addClass('has-error');
                // 2.3 阻止表单提交
                flag = false;
                return false;
            }
        });
        return flag;
    });

    // 给input框绑定focus事件
    $('form input').focus(function () {
        // 1. 去掉当前input框后面的span标签的文本
        $(this).next().text('');
        // 2. 去掉父标签的has-error样式
        $(this).parent().removeClass('has-error');
    })
</script>
</body>
</html>