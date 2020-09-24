function goBack() {
    history.back()
}

$(function () {
    $('#registerForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '名字验证失败',
                validators: {
                    notEmpty: {
                        message: '名字不能为空'
                    } ,
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: '名字长度必须在2到30之间'
                    }
                }
            },
            username: {
                message: '账号验证失败',
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '账号长度必须在6到30之间'
                    },
                    threshold :  6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: "user/checkExist",//验证地址
                        data:function(validator) {// 获取需要传送到后台的验证的数据
                            return {
                                username:$('#username').val()
                            }
                        },
                        message: '账号已存在',//提示消息
                        delay :  500,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            },
            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password', //需要进行比较的input name值
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'login',//需要进行比较的input name值
                        message: '不能和用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码必须是数字字母和下划线不能有特殊符号！'
                    }
                }
            },
            repassword: {
                message: '密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '用户名长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'login',
                        message: '不能和用户名相同'
                    },
                    regexp: {//匹配规则
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '密码必须是数字字母和下划线不能有特殊符号！'
                    }
                }
            },
            streetAddress:{
                message: '街道地址无效',
                validators: {
                    notEmpty: {
                        message: '街道地址不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '街道地址长度必须在6到30之间'
                    }
                }
            },
            mobile : {
                message: '手机号无效',
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    }
                }
            },
            email:{
                message: '邮箱无效',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {//点击提交之后
        e.preventDefault();
        var $form = $(e.target);
        // var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data 提交至form标签中的action，result自定义
        $.post($form.attr('action'), $form.serialize(), function(result) {
            //恢复submit按钮disable状态。
            $('#registerForm').bootstrapValidator('disableSubmitButtons', false);
            if (result.success) {
                window.Ewin.alert({message:'注册成功!'});
                setTimeout(function() {
                    $(window.location).attr('href', '/login')
                }, 2000);
            }
        });
    });

    // 加载bootstrap的省市区的下拉菜单
    $("#distpicker").distpicker({
        province: "北京市",
        city: "北京市市辖区",
        district: "东城区"
    });

});