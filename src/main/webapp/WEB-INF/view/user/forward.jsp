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
</head>
<body>
<div class="panel panel-primary">
    <div class="panel-body">
        <form id="forwardForm">
            <div class="form-group">
                <input id="contentId" name="contentId" value="${contentId}" hidden="hidden"/>
                <textarea type="text" class="form-control" name="contentText" id="contentText" rows="4" maxlength="140" placeholder="请输入，最多140字"></textarea>
            </div>
        </form>
    </div>
</div>
<script src="../../../static/js/expression/user/forward.js"></script>
</body>
</html>
