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
    <style>

    </style>


</head>
<body>
    <jsp:include page="../include/include.jsp"></jsp:include>
    <div class="panel panel-primary">
        <!--panel-body-->
        <div class="panel-body">
            <!--把其他的组件放到panel-body里面-->
            <form id="homeForm" role="form">
                <div class="form-group col-xs-12">
                    <div class="form-group col-xs-8">
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
                <div class="form-group col-xs-12">
                    <div class="form-group col-xs-6" align="right">

                    </div>
                    <div class="form-group col-xs-6"></div>
                </div>
                <div class="form-group col-xs-8 panel panel-primary">
                    <div class="form-group col-xs-6" id="content_div">
                        <div id="content_1" class="pb-10px col-md-12">
                            <div id="time_1" class="pb-5px green">
                                <span style="padding-top: 5px">
                                    <img style="width: 20px"
                                         src="../../../static/image/liebgott.jpg" />
                                    <span>Liebgott&nbsp;&nbsp;&nbsp;</span>
                                    <span>2020-09-27 13:30:30</span>
                                </span>
                            </div>
                            <div id="text_1" class="pb-5px col-md-8">
                                美丽的一天
                            </div>
                            <div class="col-md-8 pb-5px">
                                <div class="col-md-4 pull-left pb-5px">
                                    <img class="events-object img-responsive img-rounded img-width"
                                         src="../../../static/image/liebgott.jpg" />
                                </div>
                                <div class="col-md-4 pull-left pb-5px">
                                    <img class="events-object img-responsive img-rounded img-width"
                                         src="../../../static/image/liebgott.jpg" />
                                </div>
                                <div class="col-md-4 pull-left pb-5px">
                                    <img class="events-object img-responsive img-rounded img-width"
                                         src="../../../static/image/liebgott.jpg" />
                                </div>
                                <div class="col-md-4 pull-left pb-5px">
                                    <img class="events-object img-responsive img-rounded img-width"
                                         src="../../../static/image/liebgott.jpg" />
                                </div>
                            </div>
                            <div id="btn_area_1" class="col-md-8">
                                <span class="glyphicon glyphicon-star-empty">收藏</span>
                                <span class="glyphicon glyphicon-share-alt">转发</span>
                                <span class="glyphicon glyphicon glyphicon-comment">评论</span>
                                <span class="glyphicon glyphicon-thumbs-up">点赞</span>
                            </div>
                        </div>
                        <div id="content_2" class="pb-10px col-md-12">
                            <div id="time_2" class="pb-5px green">
                                <span style="padding-top: 5px">
                                    <img style="width: 20px"
                                         src="../../../static/image/liebgott.jpg" />
                                    <span>Liebgott&nbsp;&nbsp;&nbsp;</span>
                                    <span>2020-09-27 14:55:33</span>
                                </span>
                            </div>
                            <div id="text_2" class="pb-5px col-md-8">
                                是时候表演真正的技术了！
                            </div>
                            <div id="btn_area_2" class="col-md-8">
                                <span class="glyphicon glyphicon-star-empty">收藏</span>
                                <span class="glyphicon glyphicon-share-alt">转发</span>
                                <span class="glyphicon glyphicon-edit">评论</span>
                                <span class="glyphicon glyphicon-thumbs-up">点赞</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6"></div>
                </div>
            </form>
        </div>
    </div>

    <script src="../../../static/js/expression/main/home.js"></script>
</body>
</html>
