function addFriend() {
    var friendId = $('#friendId').val();
    var friendName = $('#friendName').val();
    var userGroup = $('#userGroup').val();
    $.ajax({
        url: 'user/addFriend',
        type: 'post',
        data: {
            friendId: $('#friendId').val(),
            userGroup: $('#userGroup').val(),
            userNote: $('#userNote').val(),
            userCheckMsg: $('#userCheckMsg').val(),
        },
        dataType: 'json',
        success: function (data) {
            debugger
            zeroModal.alert({
                content: data.msg,
                height: '200px',
                top: '0px',
                okFn: function() {
                    var ztree = $.fn.zTree.getZTreeObj("friendTree");
                    debugger
                    var friendLists = ztree.getNodes();
                    for (var i = 0; i < friendLists.length; i++) {
                        var node = friendLists[i];
                        if (node.id == userGroup) {
                            debugger
                            ztree.addNodes(node, {id: friendId, name: friendName, icon: "../../../static/image/user.png"})
                        }
                    }
                    zeroModal.closeAll()
                }
            });
        }
    });
}

function initGroup() {
    var option = "" ;
    for (var i = 0; i < userGroups.length; i++) {
        option += "<option value='"+userGroups[i].id+"'>"+userGroups[i].name+"</option>"
    }
    $("#userGroup").html(option);//将循环拼接的字符串插入第二个下拉列表
}

$(function () {
    initGroup();
});