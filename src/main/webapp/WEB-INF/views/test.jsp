<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="${BASE_PATH }/static/js/jquery-3.3.1.min.js"></script>
    <link href="${BASE_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${BASE_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <!--标题 放在row上面-->
    <h3>信息收集卡<small>共三步</small></h3>
    <div class="row">
        <!--放在一个大的col-md-12里-->
        <div class="col-md-12">
            <!--进度条-->
            <div class="progress">
                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 33%">
                    <span>1/3</span>
                </div>
            </div>
            <!--面板-->
            <div class="panel panel-primary">
                <!--panel-heading-->
                <div class="panel-heading">
                    <!--panel-title-->
                    <h3 class="panel-title">火之国精英忍者信息
                        <!--用到了pull-right-->
                        <span class="glyphicon glyphicon-pushpin pull-right" aria-hidden="true"></span>
                    </h3>
                </div>
                <!--panel-body-->
                <div class="panel-body">
                    <!--把其他的组件放到panel-body里面-->
                    <form class="form-horizontal">
                        <div class="form-group">
                            <!--注意，这里为了让label中的内容与input的内容在一行，将label放在外面了-->
                            <label for="username" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="username" placeholder="姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <!--注意，这里为了让label中的内容与input的内容在一行，将label放在外面了-->
                            <label for="phone" class="col-sm-2 control-label">手机</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="phone" placeholder="手机号">
                            </div>
                        </div>
                        <div class="form-group">
                            <!--注意，这里为了让label中的内容与input的内容在一行，将label放在外面了-->
                            <label for="email" class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="email" placeholder="邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <!--注意，这里为了让label中的内容与input的内容在一行，将label放在外面了-->
                            <label for="pwd" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="pwd" placeholder="密码">
                            </div>
                        </div>
                        <!--上传头像-->
                        <div class="form-group">
                            <label for="avatar" class="col-sm-2 control-label">头像</label>
                            <div class="col-sm-4">
                                <input type="file" class="" id="avatar" placeholder="avatar">
                                <span class="help-block">传一张最帅的</span>
                            </div>
                        </div>
                        <br>
                        <!--属性-->
                        <div class="form-group">
                            <div class="col-sm-2 control-label">属性</div>
                            <div class="col-sm-4">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1"
                                               checked>
                                        上忍
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                                        特别上忍
                                    </label>
                                </div>
                                <div class="radio disabled">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3"
                                               disabled>
                                        中忍
                                    </label>
                                </div>
                                <div class="radio disabled">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios4" value="option3"
                                               disabled>
                                        下忍
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--下一步按钮-->
            <div>
                <button class="btn btn-success pull-right">下一步</button>
            </div>
        </div>

    </div>
</div>
</body>
</html>