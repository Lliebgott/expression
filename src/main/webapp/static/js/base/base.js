var fileList = [];

function upload() {
    //声明变量
    var $file = $("#choose-file"), //选择文件按钮
        $list = $('.file-list'); //回显的列表
    //当前选择上传的文件
    var curFile;
    // 选择按钮change事件，实例化fileReader,调它的readAsDataURL并把原生File对象传给它，
    // 监听它的onload事件，load完读取的结果就在它的result属性里了。它是一个base64格式的，可直接赋值给一个img的src.
    $file.on('change', function (e) {
        debugger
        $('#pic_ul').css("height", "75px");
        curFile = this.files;
        //将FileList对象变成数组
        var selectFiles = [];
        var distinctFiles = [];
        for (var i = 0; i < fileList.length; i++) {
            selectFiles.push(fileList[i].name);
        }
        for (var i = 0; i < curFile.length; i++) {
            var curName = curFile[i].name;
            if (!selectFiles.includes(curName)) {
                distinctFiles.push(curFile[i]);
            }
        }
        if (fileList.length + distinctFiles.length > 9) {
            window.Ewin.alert({message:'最多上传9张图片!'});
            return;
        }
        if (distinctFiles.length == 0) {
            return;
        }
        fileList = fileList.concat(Array.from(distinctFiles));
        for (var i = 0, len = distinctFiles.length; i < len; i++) {
            reviewFile(distinctFiles[i])
        }
        $('.file-list').fadeIn(2500);
    })
    function reviewFile(file) {
        //实例化fileReader,
        var fd = new FileReader();
        //获取当前选择文件的类型
        var fileType = file.type;
        //调它的readAsDataURL并把原生File对象传给它，
        fd.readAsDataURL(file);//base64
        //监听它的onload事件，load完读取的结果就在它的result属性里了
        fd.onload = function () {
            if (/^image\/[jpeg|png|jpg|gif]/.test(fileType)) {
                $list.append('<li class="file-item"><img src="' + this.result + '" alt="" height="70"><span class="file-del"><i class="iconfont ">&#xe636;</i></span></li>').children(':last').hide().fadeIn(2500);
            } else {
                $list.append('<li class="file-item"><span class="file-name">' + file.name + '</span><span class="file-del"><i class="iconfont ">&#xe636;</i></span></li>')
            }
        }
    }
    //点击删除按钮事件：
    $(".file-list").on('click', '.file-del', function () {
        var $parent = $(this).parent();
        console.log($parent);
        var index = $parent.index();
        fileList.splice(index, 1);
        $parent.fadeOut(850, function () {
            $parent.remove();
            if (fileList.length == 0) {
                $('#pic_ul').css("height", "0px")
            }
        });
        var filess = document.getElementById('choose-file');
        filess.value = '';
    });
}

function initContents(datas) {
    var html = "";
    if (datas.length == 0) {
        html += "暂无动态";
    } else {
        for (var i = 0; i < datas.length; i++) {
            var data = datas[i];
            html += '<div id="content_' + i + '" class="pb-10px col-md-12">';
            html += '	<div id="time_' + data.id + '" class="pb-5px green">';
            html += '		<span style="padding-top: 5px">';
            html += '			<img style="width: 20px" src="../../../static/image/liebgott.jpg" />';
            html += '			<span><a>' + data.name + '</a>&nbsp;&nbsp;&nbsp;</span>';
            html += '			<span>' + data.create_date + '</span>';
            html += '		</span>';
            html += '	</div>';
            html += '	<div id="text_' + data.id + '" class="pb-5px col-md-8">';
            html += '		' + data.content + '';
            html += '		<input id="input_' + data.id + '" value="' + data.user_id + '" hidden="hidden" />';
            html +=  '	</div>';
            var imgs = data.imgs;
            if (imgs.length > 0) {
                html += '	<div class="col-md-8 pb-5px">';
                for (var j = 0; j < imgs.length; j++) {
                    html += '     <div class="col-md-4 pull-left pb-5px">';
                    html += '			<img class="events-object img-responsive img-rounded img-width"';
                    html += '				 style="width: 57px; height: 57px" src="' + imgs[j].file_path + '" />';
                    html += '		</div>';
                }
                html += '	</div>';
            }
            html += '<div id="btn_area_' + data.id + '" class="col-md-12 pb-5px">';
            html += '		<span id="collect_' + data.id + '" class="glyphicon ' + data.collect_class + '" onclick="collect(' + data.id + ')">' + data.collect_text + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;';
            html += '		<span id="share_' + data.id + '" class="glyphicon glyphicon-forward-alt" onclick="forward(' + data.id + ')">' + data.sharenum + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;';
            html += '		<span id="comment_' + data.id + '" class="glyphicon glyphicon glyphicon-comment" onclick="comment(' + data.id + ')">' + data.commentnum + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;';
            html += '		<span id="thumbs_' + data.id + '" class="glyphicon ' + data.thumb_class + '" onclick="thumbs(' + data.id + ')">' + data.thumb_text + '</span>';
            html += '	</div>';
            var comments = data.comments;
            html += ' <div id="comment_area_' + data.id + '" class="col-md-12 pb-5px" style="margin-left: -40px; display: none">';
            if (comments.length > 0) {
                html += '   <ul id="comment_area_ul_' + data.id + '">';
                for (var j = 0; j < comments.length; j++) {
                    html += '     <li><a>' + comments[j].name + '</a>：' + comments[j].comment_text + '</li>    ';
                }
                html += '   </ul>';
            }
            html += ' <div class="form-group col-xs-1">';
            html += '       <input id="comment_area_input_' + data.id + '" name="comment_area_input_' + data.id + '" value = "0" hidden="hidden"/>';
            html += '       <label>评论</label>';
            html += '</div>';
            html += ' <div class="form-group col-xs-11">';
            html += '       <textarea type="text" class="form-control" name="content_area_textarea_' + data.id + '" id="content_area_textarea_' + data.id + '" rows="4" maxlength="140" placeholder="请输入，最多140字"></textarea>';
            html += '</div>';
            html += ' <div class="col-xs-12" align="right">';
            html += '       <button type="button" name="submit" onclick="submitComment(' + data.id + ')" class="btn btn-success">提交</button>';
            html += '       <button type="button" name="cancel" onclick="comment(' + data.id + ')" class="btn">取消</button>';
            html += '</div>';
            html += '</div>';
            html += '</div>';
        }
    }
    return html;
}

function collect(id) {
    var state;
    var text = $('#collect_' + id)[0].innerText;
    if ('收藏' == text) {
        state = true;
    } else {
        state = false;
    }
    $.ajax({
        url: 'user/collect',
        type: 'post',
        data: {
            contentId: id,
            collectId: 1,
            friendId: $('#input_' + id).val(),
            state: state
        },
        dataType: 'json',
        success: function (data) {
            window.Ewin.alert({message: data.msg});
            if (data.result) {
                if ('收藏' == text) {
                    $('#collect_' + id)[0].innerText = '取消收藏';
                    $('#collect_' + id).removeAttr("class").attr("class", "glyphicon glyphicon-star");
                } else {
                    $('#collect_' + id)[0].innerText = '收藏';
                    $('#collect_' + id).removeAttr("class").attr("class", "glyphicon glyphicon-star-empty");
                }
            }
        }
    });
}

function forward(id) {
    // window.Ewin.dialog({title:"修改",url:"user/updatePage?id="+id,width:700,height:610});
    window.Ewin.dialog({title:"转发",url:"user/forwardPage?id=" + id,gridId:"forward",width:700,height:325})
}

function comment(id) {
    var value = $('#comment_area_input_' + id).val();
    if (value == 0) {
        $('#comment_area_input_' + id).val('1');
        $('#comment_area_' + id).css("display", "block");
    } else {
        $('#comment_area_input_' + id).val('0');
        $('#comment_area_' + id).css("display", "none");
    }
}

function submitComment(id) {
    var comment = $('#content_area_textarea_' + id).val();
    if ('' == comment) {
        window.Ewin.alert({message:'请输入评论内容!'});
        return;
    }
    $.ajax({
        url: 'user/comment',
        type: 'post',
        data: {
            contentId: id,
            friendId: $('#input_' + id).val(),
            comment: comment
        },
        dataType: 'json',
        success: function (data) {
            window.Ewin.alert({message: data.msg});
            $('#comment_' + id).empty().append(data.commentnum);
            var html = '';
            for (var i = 0; i < data.comments.length; i++) {
                html += '<li><a>' + data.comments[i].name + '</a>：' + data.comments[i].comment_text + '</li>    ';
            }
            $('#comment_area_ul_' + id).empty().append(html);
            $('#content_area_textarea_' + id).val('');
        }
    });
}

function thumbs(id) {
    var state;
    var text = $('#thumbs_' + id)[0].innerText;
    if ('点赞' == text) {
        state = true;
    } else {
        state = false;
    }
    $.ajax({
        url: 'user/thumbs',
        type: 'post',
        data: {
            contentId: id,
            friendId: $('#input_' + id).val(),
            state: state
        },
        dataType: 'json',
        success: function (data) {
            window.Ewin.alert({message: data.msg});
            if (data.result) {
                if ('点赞' == text) {
                    $('#thumbs_' + id)[0].innerText = '取消点赞';
                    $('#thumbs_' + id).removeAttr("class").attr("class", "glyphicon glyphicon-heart");
                } else {
                    $('#thumbs_' + id)[0].innerText = '点赞';
                    $('#thumbs_' + id).removeAttr("class").attr("class", "glyphicon glyphicon-heart-empty");
                }
            }
        }
    });
}