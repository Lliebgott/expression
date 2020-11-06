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

$(function () {
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
            }
        }
    };
    // var zNodes =[
    //     {id:1, pId:0, name:"[core] 基本功能 演示", open:true},
    //     {id:101, pId:1, name:"最简单的树 --  标准 JSON 数据"},
    //     {id:102, pId:1, name:"最简单的树 --  简单 JSON 数据"},
    //     {id:103, pId:1, name:"不显示 连接线"},
    //     {id:104, pId:1, name:"不显示 节点 图标"},
    //     {id:108, pId:1, name:"异步加载 节点数据"},
    //     {id:109, pId:1, name:"用 zTree 方法 异步加载 节点数据"},
    //     {id:110, pId:1, name:"用 zTree 方法 更新 节点数据"},
    //     {id:111, pId:1, name:"单击 节点 控制"},
    //     {id:112, pId:1, name:"展开 / 折叠 父节点 控制"},
    //     {id:113, pId:1, name:"根据 参数 查找 节点"},
    //     {id:114, pId:1, name:"其他 鼠标 事件监听"},
    //     {id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
    //     {id:201, pId:2, name:"Checkbox 勾选操作"},
    //     {id:206, pId:2, name:"Checkbox nocheck 演示"},
    //     {id:211, pId:2, name:"Radio halfCheck 演示"},
    //     {id:205, pId:2, name:"用 zTree 方法 勾选 Radio"},
    //     {id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
    //     {id:301, pId:3, name:"拖拽 节点 基本控制"},
    //     {id:302, pId:3, name:"拖拽 节点 高级控制"},
    //     {id:304, pId:3, name:"基本 增 / 删 / 改 节点"},
    //     {id:305, pId:3, name:"高级 增 / 删 / 改 节点"},
    //     {id:307, pId:3, name:"异步加载 & 编辑功能 共存"},
    //     {id:308, pId:3, name:"多棵树之间 的 数据交互"},
    //     {id:4, pId:0, name:"大数据量 演示", open:false},
    //     {id:401, pId:4, name:"一次性加载大数据量"},
    //     {id:402, pId:4, name:"分批异步加载大数据量"},
    //     {id:403, pId:4, name:"分批异步加载大数据量"},
    // ];

    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});