function subminForward(contentId, contentText) {
    debugger

    $.ajax({
        url: 'user/forward',
        type: 'post',
        data: {
            contentId: contentId,
            contentText: contentText
        },
        dataType: 'json',
        success: function (data) {
            if (data.success) {
                $('.modal-dialog', window.top.document).parent('div').remove()
                $('body', window.top.document).find('.modal-backdrop').remove();
                window.Ewin.alert({message:'转发成功!'});
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