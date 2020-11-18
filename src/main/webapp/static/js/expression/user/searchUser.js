function searchClick(unique) {
    debugger
    var searchValue = $('#adSearchInput').val();
    if (searchValue) {
        $.ajax({
            url: 'user/searchUser',
            type: 'post',
            async:false,
            data: {
                name: searchValue
            },
            dataType: 'json',
            success: function (data) {
                if (data.length == 0) {
                    $.eAlert({content: '尚未查询到符合条件的用户!', type: 'warning'});
                } else {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += '<div class="col-md-4 pb-5px">';
                        html += '   <div style="float:left;">';
                        html += '       <img style="width:60px;height:60px;margin-right:10px" class="events-object img-responsive img-rounded"';
                        html += '				 style="width: 57px; height: 57px" src="' + data[i].image_path + '" />';
                        html += '	</div>';
                        html += '   <div style="max-width: 300px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">';
                        html += '	    <span title="' + data[i].name + '(' + data[i].username +')">' + data[i].name + '(' + data[i].username + ')</span><br>';
                        html += '	    <span title="' + data[i].city + '"><img style="margin-right:5px" src="../../static/image/' + data[i].sex + '.png"/>' + data[i].city + '</span><br>';
                        if (data[i].id) {
                            html += '   <span style="color: gray">已添加</span>';
                        } else {
                            html += '	<span><a onclick="addFriendModal(' + data[i].friendId +  ', \'' + data[i].name + '(' + data[i].username +')\')">加好友</a></span>';
                        }
                        html += '	</div>';
                        html += '</div>';
                    }
                    $('#searchUserDiv').empty().append(html);
                }
            }
        });
    } else {
        $.eAlert({content: '请输入姓名/账号!', type: 'warning'});
    }
}

function addFriendModal(id, name) {
    debugger
    zeroModal.show({title: '添加好友',
        url: "user/addFriendPage",
        ajaxType: 'post',
        ajaxData: {
            id: id,
            name: name
        },
        width: '30%',
        height: '38%',
        ok: true,
        cancel: true,
        okFn: function(opt) {
            addFriend();
            return false;
        }
    });
}