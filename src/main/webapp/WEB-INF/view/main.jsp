<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head th:include="include/includebase"></head>
<link th:href="@{css/sb-admin.css}" rel="stylesheet"/>
<script th:inline="javascript">
    $(function () {
        // 页面加载完成以后开启websocket的连接
        var options = new Array();
        options.sockurl = '/ricky-websocket';
        options.stompClienturl = '/ricky/topic/greetings';
        options.login = [[${authentication.name}]];
        options.success = function(greeting){
            var r = eval("("+JSON.parse(greeting.body).content+")")
            alert(r);
            // $("#greetings").append("<tr><td>" + JSON.parse(greeting.body).content + "</td></tr>");
        }
        $.fn.socketConnect(options);
        // 初始化nav
        $.fn.bootstrapNav({index:'main',navTitle:'XXXX管理系统'});
        // 初始化标签页
        $("#tabContainer").tabs({
            data: [{
                id: '99999999',
                text: '首页',
                url: "home",
                closeable: false
            }],
            showIndex: 0,
            loadAll: false
        })
    });
</script>
<body >
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
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>${authentication.name}<b class="caret"></b></a>
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

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>
    <div id="page-wrapper" style="border-radius:5px 5px 0 0;">
        <div id="tabContainer"></div>
    </div>
</div>
</body>
</html>