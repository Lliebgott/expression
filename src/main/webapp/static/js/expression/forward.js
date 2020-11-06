function subminForward(contentId, contentText) {
    $.ajax({
        url: 'user/forward',
        type: 'post',
        data: {
            contentId: contentId,
            contentText: contentText
        },
        dataType: 'json',
        success: function (data) {
            debugger
            if (data.result) {
                $('.modal-dialog', window.top.document).parent('div').remove();
                $('body', window.top.document).find('.modal-backdrop').remove();
                window.Ewin.alert({message: data.msg});
                // 初始化内容
                $('#content_div').empty().append(initContents(data.contents));
            } else {
                window.Ewin.alert({message: data.msg});
            }
        }
    });
}

$(function () {
    $("#btnOk",window.top.document).click(function() {
        var contentId = $("#forwardForm",window.top.document)[0][0].value;
        var contentText = $("#forwardForm",window.top.document)[0][1].value;
        subminForward(contentId, contentText);
    });
});