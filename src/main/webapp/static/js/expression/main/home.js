function initContent() {
    var html = '<div id="content_1" class="pb-10px col-md-12">'
        + '	<div id="time_1" class="pb-5px green">'
        + '		<span style="padding-top: 5px">'
        + '			<img style="width: 20px"'
        + '				 src="../../../static/image/liebgott.jpg" />'
        + '			<span>Liebgott&nbsp;&nbsp;&nbsp;</span>'
        + '			<span>2020-09-27 13:30:30</span>'
        + '		</span>'
        + '	</div>'
        + '	<div id="text_1" class="pb-5px col-md-8">'
        + '		美丽的一天'
        + '	</div>'
        + '	<div class="col-md-8 pb-5px">'
        + '     <div class="col-md-4 pull-left pb-5px">'
        + '			<img class="events-object img-responsive img-rounded img-width"'
        + '				 src="../../../static/image/liebgott.jpg" />'
        + '		</div>'
        + '		<div class="col-md-4 pull-left pb-5px">'
        + '			<img class="events-object img-responsive img-rounded img-width"'
        + '				 src="../../../static/image/liebgott.jpg" />'
        + '		</div>'
        + '		<div class="col-md-4 pull-left pb-5px">'
        + '			<img class="events-object img-responsive img-rounded img-width"'
        + '				 src="../../../static/image/liebgott.jpg" />'
        + '		</div>'
        + '		<div class="col-md-4 pull-left pb-5px">'
        + '			<img class="events-object img-responsive img-rounded img-width"'
        + '				 src="../../../static/image/liebgott.jpg" />'
        + '		</div>'
        + '	</div>'
        + '	<div id="btn_area_1" class="col-md-8">'
        + '		<span class="glyphicon glyphicon-star-empty">收藏</span>'
        + '		<span class="glyphicon glyphicon-share-alt">转发</span>'
        + '		<span class="glyphicon glyphicon glyphicon-comment">评论</span>'
        + '		<span class="glyphicon glyphicon-thumbs-up">点赞</span>'
        + '	</div>'
        + '</div>';
}

function addPicture() {
    $('#choose-file').click();
}


//选择要上传的所有文件
var fileList = [];

function uploadClick() {
    ////////////////////////////////////////////////图片上传//////////////////////////////////////////////
    //声明变量
    var $button = $('#upload'),
        //选择文件按钮
        $file = $("#choose-file"),
        //回显的列表
        $list = $('.file-list');
    fileList = [];
    //当前选择上传的文件
    var curFile;
    // 选择按钮change事件，实例化fileReader,调它的readAsDataURL并把原生File对象传给它，
    // 监听它的onload事件，load完读取的结果就在它的result属性里了。它是一个base64格式的，可直接赋值给一个img的src.
    $file.on('change', function (e) {
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
            $parent.remove()
        });
        var filess = document.getElementById('choose-file');
        filess.value = '';
        if (fileList.length == 0) {
            $('#pic_ul').css("height", "0px")
        }
    });
}

function publish() {
    var content = $('#content').val();
    if (content == '' && fileList.length == 0) {
        window.Ewin.alert({message:'请输入心情或选择添加图片!'});
        return;
    }
    var formData = new FormData();
    for (var i = 0, len = fileList.length; i < len; i++) {
        //console.log(fileList[i]);
        formData.append('files[]', fileList[i]);
    }
    formData.append('content', content);
    console.log(formData)
    $.ajax({
        url: 'user/publish',
        type: 'post',
        data: formData,
        dataType: 'json',
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.status == '1') {
                layer.msg(data.content, {icon: 6});
            } else if (data.status == '2') {
                layer.msg(data.content, {icon: 1});
            }
        }
    })
}


$(function () {
    // $('#homeForm').bootstrapValidator({
    //     message: 'This value is not valid',
    //     feedbackIcons: {
    //         valid: 'glyphicon glyphicon-ok',
    //         invalid: 'glyphicon glyphicon-remove',
    //         validating: 'glyphicon glyphicon-refresh'
    //     },
    //     fields: {
    //         content: {
    //             message: '验证失败',
    //             validators: {
    //                 notEmpty: {
    //                     message: '不能为空'
    //                 }
    //             }
    //         }
    //     }
    // }).on('success.form.bv', function(e) {//点击提交之后
    //     e.preventDefault();
    //     var $form = $(e.target);
    //     // var bv = $form.data('bootstrapValidator');
    //     // Use Ajax to submit form data 提交至form标签中的action，result自定义
    //     $.post($form.attr('action'), $form.serialize(), function(result) {
    //         //恢复submit按钮disable状态。
    //         $('#homeForm').bootstrapValidator('disableSubmitButtons', false);
    //         debugger
    //         if (result.success) {
    //             window.Ewin.alert({message:'发布成功!'});
    //             setTimeout(function() {
    //                 $('#content').val('');
    //             }, 2000);
    //         } else {
    //             window.Ewin.alert({message:'发布失败!'});
    //         }
    //     });
    // });
    initContent();

    uploadClick();
});