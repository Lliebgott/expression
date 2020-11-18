function _basic() {
    zeroModal.show();
}

function _params() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        close: false
    });
}

function _escape() {
    zeroModal.show({
        title: 'hello world',
        content: '<b>this is zeroModal</b>',
        escape: false
    });
}

function _button() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        ok: true,
        cancel: true,
        okFn: function(opt) {
            console.log(opt);
            alert('clicked ok and not close');
            return false;
        }
    });
}

function _setsize() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        width: '60%',
        height: '40%'
    });
}

function _notoverlay() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        width: '60%',
        height: '40%',
        overlay: false
    });
}

function _iframe() {
    zeroModal.show({
        title: 'hello world',
        iframe: true,
        url: 'http://www.baidu.com',
        width: '80%',
        height: '80%',
        cancel: true
    });
}

function _esc() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        esc: true
    });
}

function _resize() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        width: '60%',
        height: '40%',
        resize: true
    });
}

function _maxmin() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        width: '60%',
        height: '40%',
        max: true,
        min: true
    });
}

function _loading(type) {
    zeroModal.loading(type);
}

function _progress() {
    zeroModal.progress();
}

function _alert1() {
    zeroModal.alert('请选择数据进行操作!');
}

function _alert2() {
    zeroModal.alert({
        content: '操作提示!',
        contentDetail: '请选择数据后再进行操作',
        okFn: function() {
            alert('ok callback');
        }
    });
}

function _alert3() {
    $.eAlert({content: "提示内容info", type: 'info'});
}

function _alert4() {
    $.eAlert({content: "提示内容warn", type: 'warning'});
}

function _alert5() {
    $.eAlert({content: "提示内容error", type: 'error'});
}

function _alert6() {
    $.eAlert({content: "提示内容success", type: 'success'});
}

function _confirm1() {
    zeroModal.confirm("确定提交审核吗？", function() {
        alert('ok');
        //return false;
    });
}

function _confirm2() {
    zeroModal.confirm({
        content: '确定提交审核吗？',
        contentDetail: '提交后将不能进行修改。',
        okFn: function() {
            alert('ok');
        },
        cancelFn: function() {
            alert('cancel');
        }
    });
}

function _error() {
    zeroModal.error('请选择数据进行操作!');

    /*zeroModal.error({
        content: '请选择数据进行操作!',
        width: '800px'
    });*/
}

function _success() {
    zeroModal.success('操作成功!');
}

function _setOpacity() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        width: '60%',
        height: '40%',
        opacity: 0.8
    });
}

function _drag() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        dragHandle: 'container',
        ok: true
    });
}

function _custombutton() {
    zeroModal.show({
        title: 'hello world',
        iframe: true,
        url: 'http://www.baidu.com',
        width: '60%',
        height: '60%',
        buttons: [{
            className: 'zeromodal-btn zeromodal-btn-primary',
            name: '这是自定义按钮',
            fn: function(opt) {
                alert(1);
                return false;
            }
        }, {
            className: 'zeromodal-btn zeromodal-btn-default',
            name: '取消',
            fn: function(opt) {
                alert(2);
            }
        }]
    });
}

function _setPosition() {
    zeroModal.show({
        title: 'hello world',
        content: 'this is zeroModal',
        top: '30px',
        left: '30px',
        width: '280px',
        height: '600px'
    });
}
$(function () {

});