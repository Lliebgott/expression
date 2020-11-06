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
    <title>转发</title>
    <script>
        var userGroups = ${userGroups};
    </script>
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-body">
        <form id="addFriendForm">
            <input id="friendId" name="friendId" value="${friendId}"/>
            <div class="form-group">
                <label for="userGroup" class="col-sm-2 control-label">分组:</label>
                <div class="col-sm-10">
                    <select name="userGroup" id="userGroup" class="form-control">
                    </select>
                </div>
                <label for="checkMeg" class="col-sm-2 control-label">验证信息:</label>
                <div class="col-sm-10">
                    <textarea type="text" class="form-control" name="checkMeg" id="checkMeg" rows="4" maxlength="140" placeholder="请输入，最多140字"></textarea>
                </div>
            </div>
            <div class="form-group">

            </div>
        </form>
    </div>
</div>
<script src="../../static/js/expression/addFriend.js"></script>
</body>
</html>
