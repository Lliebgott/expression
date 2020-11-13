function addPicture() {
    $('#choose-file').click();
}

function publish() {
    var content = $('#content').val();
    if (content == '' && fileList.length == 0) {
        zeroModal.alert({
            content: '请输入心情或选择添加图片!',
            height: '200px',
            top: '0px',
        });
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
            zeroModal.alert({
                content: data.msg,
                height: '200px',
                top: '0px',
                okFn: function() {
                    if (data.result) {
                        $('#content').val('');
                        $('.file-item').remove();
                        $('#pic_ul').css("height", "0px");
                        fileList = [];
                        // 初始化内容
                        $('#content_div').empty().append(initContents(data.contents));
                    }
                }
            });
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