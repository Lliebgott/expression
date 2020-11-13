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
        ol {
            counter-reset: li;
            list-style: none;
            *list-style: decimal;
            font: 13px 'trebuchet MS', 'lucida sans';
            padding: 0;
            margin-bottom: 4em;
            text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
        }

        ol ol {
            margin: 0 0 0 2em;
        }

        .rounded-list a {
            position: relative;
            display: block;
            padding: .4em .4em .4em 2em;
            *padding: .4em;
            margin: .8em 0;
            background: #ddd;
            color: #444;
            text-decoration: none;
            border-radius: .3em;
            transition: all .3s ease-out;
        }

        .rounded-list a:hover {
            background: #eee;
        }

        .rounded-list a:hover:before {
            transform: rotate(360deg);
        }

        .rounded-list a:before {
            content: counter(li);
            counter-increment: li;
            position: absolute;
            left: -1.3em;
            top: 50%;
            margin-top: -1.3em;
            background: #87ceeb;
            height: 2em;
            width: 2em;
            line-height: 2em;
            border: .3em solid #fff;
            text-align: center;
            font-weight: bold;
            border-radius: 2em;
            transition: all .3s ease-out;
        }

        rectangle-list a {
            position: relative;
            display: block;
            padding: .4em .4em .4em .8em;
            *padding: .4em;
            margin: .5em 0 .5em 2.5em;
            background: #ddd;
            color: #444;
            text-decoration: none;
            transition: all .3s ease-out;
        }

        .rectangle-list a:hover {
            background: #eee;
        }

        .rectangle-list a:before {
            content: counter(li);
            counter-increment: li;
            position: absolute;
            left: -2.5em;
            top: 50%;
            margin-top: -1em;
            background: #fa8072;
            height: 2em;
            width: 2em;
            line-height: 2em;
            text-align: center;
            font-weight: bold;
        }

        .rectangle-list a:after {
            position: absolute;
            content: '';
            border: .5em solid transparent;
            left: -1em;
            top: 50%;
            margin-top: -.5em;
            transition: all .3s ease-out;
        }

        .rectangle-list a:hover:after {
            left: -.5em;
            border-left-color: #fa8072;
        }

        .rectangle-list a {
            position: relative;
            display: block;
            padding: .4em .4em .4em .8em;
            *padding: .4em;
            margin: .5em 0 .8em 2.5em;
            background: #ddd;
            color: #444;
            text-decoration: none;
            transition: all .3s ease-out;
        }

        .rectangle-list a:hover {
            background: #eee;
        }

        .rectangle-list a:before {
            content: counter(li);
            counter-increment: li;
            position: absolute;
            left: -2.5em;
            top: 50%;
            margin-top: -1em;
            background: #fa8072;
            height: 2em;
            width: 2em;
            line-height: 2em;
            text-align: center;
            font-weight: bold;
        }

        .rectangle-list a:after {
            position: absolute;
            content: '';
            border: .5em solid transparent;
            left: -1em;
            top: 50%;
            margin-top: -.5em;
            transition: all .3s ease-out;
        }

        .rectangle-list a:hover:after {
            left: -.5em;
            border-left-color: #fa8072;
        }
    </style>
</head>
<body>
<jsp:include page="../include/include.jsp"></jsp:include>
<h1 style="text-align:center">zeroModal</h1>
<h5 style="text-align:center">jQuery弹出层组件，扁平化风格，默认可拖动，支持弹出常用的模态框及操作提示框、等待层等。支持amd或cmd规范，或直接引入。</h5>
<div style="position:absolute;width:200px;left:220px;">
    <ol class="rounded-list">
        <li><a href="javascript:_basic()">basic</a></li>
        <li><a href="javascript:_params()">带标题</a></li>
        <li><a href="javascript:_escape()">内容不转义</a></li>
        <li><a href="javascript:_button()">带按钮</a></li>
        <li><a href="javascript:_setsize()">自定义尺寸(px、pt、%)</a></li>
        <li><a href="javascript:_custombutton()">自定义按钮</a></li>
        <li><a href="javascript:_notoverlay()">不要遮罩</a></li>
        <li><a href="javascript:_setOpacity()">设置遮罩层透明度</a></li>
        <li><a href="javascript:_iframe()">嵌套iframe</a></li>
        <li><a href="javascript:_esc()">esc键退出</a></li>
        <li><a href="javascript:_resize()">允许拖放大小</a></li>
        <li><a href="javascript:_maxmin()">允许最大化最小化</a></li>
        <li><a href="javascript:_drag()">点击层任意地方拖动</a></li>
        <li><a href="javascript:_setPosition()">自定义弹框位置</a></li>
    </ol>
</div>
<div style="position:absolute;width:200px;left:480px;">
    <ol class="rectangle-list">
        <li><a href="javascript:_loading(1)">等待框1 (loading)</a></li>
        <li><a href="javascript:_loading(2)">等待框2 (loading)</a></li>
        <li><a href="javascript:_loading(3)">等待框3 (loading)</a></li>
        <li><a href="javascript:_loading(4)">等待框4 (loading)</a></li>
        <li><a href="javascript:_loading(5)">等待框5 (loading)</a></li>
        <li><a href="javascript:_loading(6)">等待框6 (loading)</a></li>
        <!--<li><a href="javascript:_progress(1)">progress1</a></li>-->
    </ol>
</div>
<div style="position:absolute;width:200px;left:760px;">
    <ol class="rounded-list">
        <li><a href="javascript:_alert1()">提示框1 (alert)</a></li>
        <li><a href="javascript:_alert2()">提示框2 (alert)</a></li>
        <li> <a href="javascript:_confirm1()">确认框1 (confirm)</a></li>
        <li> <a href="javascript:_confirm2()">确认框2 (confirm)</a></li>
        <li> <a href="javascript:_error()">失败提示框 (error)</a></li>
        <li> <a href="javascript:_success()">成功提示框 (success)</a></li>
    </ol>
</div>
<script src="../../../static/js/expression/login/zeroModal.js"></script>
</body>
</html>