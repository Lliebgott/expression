// 跳转注册页面
function register() {
    $(window.location).attr('href', '/register');
}

$(function () {
    // 给登录按钮绑定点击事件
    $('#login-btn').click(function () {
        // 定义一个是否允许提交的标志位
        var flag = true;
        // 1. 找到登录框中所有的input框，判断值是否为空
        $('form input').each(function () {
            var value = $(this).val();
            if (value.length===0){
                // 2. 为空就显示提示信息
                // 2.1 给下面的span标签设置文本提示信息
                var errMsg = $(this).prev().text() + '不能为空';
                $(this).next().text(errMsg);
                // 2.2 给父标签设置has-error的样式
                $(this).parent().addClass('has-error');
                // 2.3 阻止表单提交
                flag = false;
                return false;
            }
        });
        return flag;
    });

    // 给input框绑定focus事件
    $('form input').focus(function () {
        // 1. 去掉当前input框后面的span标签的文本
        $(this).next().text('');
        // 2. 去掉父标签的has-error样式
        $(this).parent().removeClass('has-error');
    })
});