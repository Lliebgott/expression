<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/29
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找好友</title>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-body">
        <form id="searchUserForm" class="form-horizontal">
            <input id="afUserId" name="afUserId" value="${userId}" hidden="hidden"/>
            <div class="form-group">
                <label for="adSearchInput" class="col-sm-2 control-label">姓名/账号:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="adSearchInput" placeholder="请输入姓名/账号">
                </div>
            </div>
            <div id="searchUserDiv"></div>
        </form>
    </div>
</div>
<script src="../../../static/js/expression/user/searchUser.js"></script>
</body>
</html>
