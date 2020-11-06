function searchUser() {
    debugger
    var searchValue = $('#adSearchInput').val();
    if (searchValue) {
        $.ajax({
            url: 'user/searchUser',
            type: 'post',
            async:false,
            data: {
                name: name
            },
            dataType: 'json',
            success: function (data) {
                if (data.length == 0) {

                } else {
                    var html = "";
                    for (var i = 0; i < data.length; i++) {
                        html += '	<div class="col-md-4 pb-5px">';
                        html += '			<img style="float:left;width:80px;height:80px;margin-right:10px" class="events-object img-responsive img-rounded"';
                        html += '				 style="width: 57px; height: 57px" src="' + data[i].image_path + '" />';
                        html += '			<div>' + data[i].name + '(' + data[i].username + ')</div>';
                        html += '			<div>' + data[i].sex + '</div>';
                        html += '			<div>' + data[i].city + '</div>';
                        html += '			<div><a onclick="addFriend(' + data[i].id +  ')">加好友</a></div>';
                        html += '		</div>';
                    }
                    $('#searchUserDiv').append(html)
                }
            }
        });
    } else {
        window.Ewin.alert({message: "请输入姓名/账号！"});
    }
}

function addFriend(id) {
    alert(id);
}


$(function () {
    $("#btnOk",window.top.document).click(function() {
        searchUser();
    });
});