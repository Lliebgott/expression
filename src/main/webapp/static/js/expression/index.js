function addClick() {
    debugger
    window.Ewin.dialog({title:"查找好友：",url:"user/searchUserPage", gridId:"addFriend",width:800,height:500, btnok: "查询"})
}

function infoClick() {
    $.eAlert({content: "提示内容info", type: 'info'});
}

function warnClick() {

    $.eAlert({content: "提示内容warn", type: 'warning'});
}

function errorClick() {

    $.eAlert({content: "提示内容error", type: 'error'});
}

function successClick() {

    $.eAlert({content: "提示内容success", type: 'success'});
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
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    treeObj.expandNode(treeNode, !treeNode.open, true, true);
                }
            },
            onRightClick: function(event, treeId, treeNode){
                //判断是否为点击事件;如果是节点点击事件，给全局变量ztreeNode赋值
                showRMenu("node", event.clientX, event.clientY)
            }
        }
    };
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
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