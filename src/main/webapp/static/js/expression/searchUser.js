function searchUser() {
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
                        html += '	    <span><a onclick="addFriend(' + data[i].friendId +  ')">加好友</a></span>';
                        html += '	</div>';
                        html += '</div>';
                    }
                    $('#searchUserDiv').empty().append(html)
                }
            }
        });
    } else {
        window.Ewin.alert({message: "请输入姓名/账号！"});
    }
}

function addFriend(id) {
    alert(id);
    window.Ewin.dialog({title:"添加好友",url:"user/addFriendPage?id=" + id,gridId:"forward",width:700,height:400})
}


$(function () {
    $("#btnOk",window.top.document).click(function() {
        searchUser();
    });
});