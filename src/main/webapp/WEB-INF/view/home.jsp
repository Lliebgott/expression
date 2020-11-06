<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>这是一个首页</title>
    <script>
        var contents = ${contents};
        var flag = ${flag};
    </script>
</head>
<body>
    <jsp:include page="include/include.jsp"></jsp:include>
    <div class="panel panel-primary">
        <!--panel-body-->
        <div class="panel-body">
            <!--把其他的组件放到panel-body里面-->
            <form id="homeForm" role="form">
                <div class="form-group col-xs-12">
                    <div class="form-group col-xs-8" id="publish_div">
                        <label for="content">心情:</label>
                        <textarea type="text" class="form-control" name="content" id="content" rows="4" maxlength="140" placeholder="请输入心情，最多140字"></textarea>
                        <input type="file" name id="choose-file" multiple="multiple" style="opacity: 0"/>
                        <ul id="pic_ul" class="file-list" style="display: block"></ul>
                        <div align="right" style="padding-top: 5px">
                            <button type="button" name="addPic" onclick="addPicture()" class="btn btn-primary block full-width m-b">添加图片</button>
                            <button type="button" name="submit" onclick="publish()" class="btn btn-primary block full-width m-b">发&nbsp;&nbsp;布</button>
                        </div>
                    </div>
                    <div class="form-group col-xs-6"></div>
                </div>
                <div class="form-group col-xs-8">
                    <div class="form-group col-xs-6" id="content_div">
                    </div>
                    <div class="form-group col-xs-6"></div>
                </div>
            </form>
        </div>
    </div>
    <script src="../../static/js/expression/home.js"></script>
</body>
</html>
