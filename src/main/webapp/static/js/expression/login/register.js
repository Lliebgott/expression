function goBack() {
    history.back()
}

$(function () {
    /* 隐藏，显现效果 */
    $(".t_img").click(function(){
        $("#sbox").show("slow");
    });
    $("#hide").click(function(){
        $("#sbox").hide("slow");
    });
    $("#close").click(function(){
        $("#sbox").hide("slow");
    });
    /*  选定图像获取图像src值 */
    var $t_img = document.getElementById('t_img');
    var $img = $t_img.getElementsByTagName('img');
    var index = 0;
    for(var i = 0; i<$img.length;i++){
        $img[i].index=i;
        $img[i].onclick = function(){
            debugger
            $img[index].style.borderRadius="15%";
            $img[index].style.border="none"
            this.style.borderRadius="50%";
            this.style.border="1px solid red"
            index = this.index;
            var $newsrc = $img[index].src;
            $(".t_img").attr('src',$newsrc);
            $('#headFile').val("__h" + (index + 1) + ".png");
        }
    }
    //点击确认修改按钮更换头像
    $("#but").click(function(){
        $("#sbox").hide("slow");
    })

    //讲选中的图片替换头像的图片
    $("#file0").change(function(e){
        var objUrl = getObjectURL(this.files[0]) ;
        if (objUrl) {
            $(".t_img").attr("src", objUrl) ;
        }
        $('#headFile').val($('#file0').val());
    }) ;
    //创建一个可存取到该file的url
    function getObjectURL(file) {
        var url = null ;
        // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }

    $("#sbox").hide();
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
                        url: "/user/checkExist",//验证地址
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
            },
            birthStr:{
                message: '生日无效',
                validators: {
                    notEmpty: {
                        message: '生日不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {//点击提交之后
        var formData = new FormData($(e.target)[0]);
        formData.append("file0", $('#file0')[0].files[0]);
        $.ajax({
            url: 'user/save',
            type: 'post',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (data) {
                zeroModal.alert({
                    content: data.msg,
                    okFn: function() {
                        if (data.result) {
                            $(window.location).attr('href', '/login')
                        }
                    }
                });
            }
        })
    });
    $('#birthStr').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),
        autoclose: true,//选中自动关闭
        todayBtn: true,//显示今日按钮
        timepicker:false    //关闭时间选项
        //locale: moment.locale('zh-cn')
    });
    // $('#s').datetimepicker({
    //     lang:"ch", //语言选择中文 注：旧版本 新版方法：$.datetimepicker.setLocale('ch');
    //     format:"Y-m-d",      //格式化日期
    //     timepicker:false,    //关闭时间选项
    //     yearStart：2000,     //设置最小年份
    //     yearEnd:2050,        //设置最大年份
    //     todayButton:false    //关闭选择今天按钮
    // });

    // 加载bootstrap的省市区的下拉菜单
    $("#distpicker").distpicker({
        province: "北京市",
        city: "北京市市辖区",
        district: "东城区"
    });
});