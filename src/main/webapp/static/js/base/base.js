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
        $('#pic_ul').css("height", "75px")
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
        let $parent = $(this).parent();
        console.log($parent);
        let index = $parent.index();
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
            html += '<div id="content_' + i + '" class="pb-10px col-md-12">'
                + '	<div id="time_' + data.id + '" class="pb-5px green">'
                + '		<span style="padding-top: 5px">'
                + '			<img style="width: 20px" src="../../../static/image/liebgott.jpg" />'
                + '			<span>' + data.name + '&nbsp;&nbsp;&nbsp;</span>'
                + '			<span>' + data.create_date + '</span>'
                + '		</span>'
                + '	</div>'
                + '	<div id="text_' + data.id + '" class="pb-5px col-md-8">'
                + '		' + data.content + ''
                + '		<input id="input_' + data.id + '" value="' + data.user_id + '" hidden="hidden" />'
                + '	</div>';
            var imgs = data.imgs;
            if (imgs.length > 0) {
                html += '	<div class="col-md-8 pb-5px">';
                for (var j = 0; j < imgs.length; j++) {
                    html += '     <div class="col-md-4 pull-left pb-5px">'
                        + '			<img class="events-object img-responsive img-rounded img-width"'
                        + '				 style="width: 57px; height: 57px" src="' + imgs[j].file_path + '" />'
                        + '		</div>'
                }
                html += '	</div>'
            }
            html += '<div id="btn_area_' + data.id + '" class="col-md-12">'
                + '		<span id="collect_' + data.id + '" class="glyphicon ' + data.collect_class + '" onclick="collect(' + data.id + ')">' + data.collect_text + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;'
                + '		<span id="share_' + data.id + '" class="glyphicon glyphicon-forward-alt" onclick="forward(' + data.id + ')">' + data.sharenum + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;'
                + '		<span id="comment_' + data.id + '" class="glyphicon glyphicon glyphicon-comment" onclick="comment(' + data.id + ')">' + data.commentnum + '</span>&nbsp;&nbsp;|&nbsp;&nbsp;'
                + '		<span id="thumbs_' + data.id + '" class="glyphicon ' + data.thumb_class + '" onclick="thumbs(' + data.id + ')">' + data.thumb_text + '</span>'
                + '	</div>'
                + '</div>';
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
            if (data.success) {
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
            if (data.success) {
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