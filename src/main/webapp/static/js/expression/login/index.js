function addClick() {
    zeroModal.show({
        title: '查找好友',
        url: "user/searchUserPage",
        width: '40%',
        height: '40%',
        ok: true,
        cancel: true,
        okFn: function(opt) {
        },
        buttons: [{
            className: 'zeromodal-btn zeromodal-btn-primary',
            name: '查询',
            fn: function(opt) {
                searchClick(opt.unique);
                return false;
            }
        }, {
            className: 'zeromodal-btn zeromodal-btn-default',
            name: '取消',
            fn: function(opt) {
            }
        }]
    });
}

function searchClick() {
    alert("123123123")
}

var rMenu = $("#rMenu");

function initTree(zNodes) {
    // 初始化nav
    $.fn.bootstrapNav({index:'main',navTitle:'XXXX管理系统'});
    // 初始化标签页
    $("#tabContainer").tabs({
        data: [{
            id: '99999999',
            text: '首页',
            url: "home?id=0&flag=false",
            closeable: false
        }],
        showIndex: 0,
        loadAll: false
    });

    var setting = {
        view: {
            addHoverDom: false,
            removeHoverDom: false,
            selectedMulti: false
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: false
        },
        callback : {
            onClick: function (e, treeId, treeNode) {
                if (treeNode.level != 0) {
                    $("#tabContainer").data("tabs").addTab({id: treeNode.id, text: treeNode.name, closeable: true, url: "/home?id=" + treeNode.id + "&flag=true"});
                } else {
                    var treeObj = $.fn.zTree.getZTreeObj("friendTree");
                    treeObj.expandNode(treeNode, !treeNode.open, true, true);
                }
            }
            // ,
            // onRightClick: function(event, treeId, treeNode){
            //     //判断是否为点击事件;如果是节点点击事件，给全局变量ztreeNode赋值
            //     showRMenu("node", event.clientX, event.clientY)
            // }
        }
    };
    $.fn.zTree.init($("#friendTree"), setting, zNodes);
}

function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if (type=="root") {
        $("#m_add").hide();
        $("#m_rename").hide();
        $("#m_del").hide();
    } else {
        $("#m_add").show();
        $("#m_rename").show();
        $("#m_del").show();
    }
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
    $("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}

$(function () {
   initTree(zNodes);
});