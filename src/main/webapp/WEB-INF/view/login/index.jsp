<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head >
    <meta charset="UTF-8">
    <title>Expression Main</title>
    <link href="../../../static/css/sb-admin.css" rel="stylesheet"/>
    <style>
        div#rMenu {
            position:absolute;
            visibility:hidden;
            top:0;
            background-color: #555;
            text-align: left;
            padding: 2px;
        }
        div#rMenu ul li{
            margin: 2px 0;
            padding: 0 2px;
            cursor: pointer;
            list-style: none outside none;
            background-color: #DFDFDF;
        }
    </style>
    <script>
        var zNodes = ${zNodes};
    </script>
</head>
<body >
<jsp:include page="../include/include.jsp"></jsp:include>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" id="navbar_header">

        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="" />
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="" />
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="" />
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-footer">
                        <a href="#">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <font th:text="张三"></font> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#" ><i class="fa fa-fw fa-gear"></i> 修改密码 </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="/logout" ><i class="fa fa-fw fa-power-off"></i>退 出</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav" id="menu_tree">
                <div id="friendTree" class="ztree" style="border: 1px solid red"></div>
                <div style="padding-top: 5px">
                    <button type="button" name="addPic" onclick="addClick()" title="添加好友" class="btn btn-primary glyphicon glyphicon-plus">添加好友</button>
                </div>
            </ul>
            <div id="rMenu"  style="width: 60px;">
                <ul>
                    <li id="m_add">新增</li>
                    <li id="m_rename">修改</li>
                    <li id="m_del">删除</li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="page-wrapper" style="border-radius:5px 5px 0 0;">
        <div id="tabContainer"></div>
    </div>
</div>
<script src="../../../static/js/expression/login/index.js"></script>
</body>
</html>
