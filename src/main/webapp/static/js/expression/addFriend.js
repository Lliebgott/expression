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

function initGroup() {
    var option = "<option>"+"选择您购买的专柜"+"</option>" ;
    for (var i = 0; i < userGroups.length; i++) {
        option += "<option value='"+userGroups[i].id+"'>"+userGroups[i].name+"</option>"
    }
    $("#userGroup").html(option);//将循环拼接的字符串插入第二个下拉列表
}

$(function () {

    initGroup();

    $("#btnOk",window.top.document).click(function() {
        alert('提交水水水水水水');
    });
});