function addPicture() {
    $('#choose-file').click();
}

function publish() {
    var content = $('#content').val();
    if (content == '' && fileList.length == 0) {
        $.eAlert({content: '请输入心情或选择添加图片!', type: 'warning'});
        return;
    }
    var formData = new FormData();
    for (var i = 0, len = fileList.length; i < len; i++) {
        formData.append('files[]', fileList[i]);
    }
    formData.append('content', content);
    $.ajax({
        url: 'user/publish',
        type: 'post',
        data: formData,
        dataType: 'json',
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.result) {
                $.eAlert({content: data.msg, type: 'info'});
                $('#content').val('');
                $('.file-item').remove();
                $('#pic_ul').css("height", "0px");
                fileList = [];
                // 初始化内容
                $('#content_div').empty().append(initContents(data.contents));
            } else {
                $.eAlert({content: data.msg, type: 'error'});
            }
        }
    })
}

$(function () {
    if (flag) {
        $('#publish_div').hide();
    } else{
        $('#publish_div').show();
    }
    // 初始化内容
    $('#content_div').empty().append(initContents(contents));
    // 初始化上传
    upload();
});