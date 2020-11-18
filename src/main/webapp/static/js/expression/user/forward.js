function subminForward(unique) {
    $.ajax({
        url: 'user/forward',
        type: 'post',
        data: {
            contentId: $('#contentId').val(),
            contentText: $('#contentText').val()
        },
        dataType: 'json',
        success: function (data) {
            if (data.result) {
                $.eAlert({content: data.msg, type: 'info'});
                // 初始化内容
                $('#content_div').empty().append(initContents(data.contents));
                zeroModal.close(unique);
            } else {
                $.eAlert({content: data.msg, type: 'error'});
            }
        }
    });
}