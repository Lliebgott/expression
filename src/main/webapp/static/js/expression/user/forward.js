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
            zeroModal.alert({
                content: data.msg,
                height: '200px',
                top: '0px',
                okFn: function() {
                    if (data.result) {
                        // 初始化内容
                        $('#content_div').empty().append(initContents(data.contents));
                        zeroModal.close(unique);
                    }
                }
            });
        }
    });
}