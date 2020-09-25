// 跳转注册页面
function register() {
    $(window.location).attr('href', '/register');
}

$(function () {
    $('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '账号验证失败',
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            },
            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
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
            debugger
            if (result.success) {
                $(window.location).attr('href', '/index');
            } else {
                $('#warnSpan').empty().html("用户名或密码不正确")
            }
        });
    });

});