/**
 * Created by a88u on 2016/10/26.
 */

// 获取url参数
function getUrlQueryStr(sArgName, urlStr) {
    var url=decodeURI(urlStr);
    var args = url.split("?");
    var retval;

    if (args[0] == url) {
        return retval;
    }
    var str = args[1];
    args = str.split("&");
    for (var i = 0; i < args.length; i++) {
        str = args[i];
        var arg = str.split("=");
        if (arg.length <= 1) continue;
        if (arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}

function re(){
    var cW=$(window).width();
    if(cW>=992){
        var rightW=$(".row_right").width();
        $(".sidebar").css({"width":rightW +"px","right":"10%"});
    }
    else{
        $(".sidebar").css({
            "width":"100%",
            "right":"0",
        });
    }

    setCategoryImg();
}

function setCategoryImg(){
    var cwidth=$(".category-preview").width();
    //console.log(cwidth);
    $(".category-preview").css({"height":cwidth+"px"});
}

function login(){
    var name=$('#iptName').val();
    var password=$('#iptPwd').val();
    var url=indexCommonUrls.loginUrl;
    //var url="http://192.168.0.67:18083/login";

    var pwd=hex_md5(password);
    console.log("login:",name,', ',pwd);
    $.ajax({
        "url":url,
        type:'POST',
        dataType:'json',
        data:{
            user_name:name,
            password:pwd
        },
        success:function(res){
            console.log(res);
            if (res.status=="failure") {
                // console.log("login success");
                $("#login-fail").show().html("用户名密码错误,请重新输入!")
            } else {
                var response=res;

                console.log("login success:",response);
                window.location.href="homePage.html?uname="+name;
            }
        },
        fail:function(res){
            console.log("fail:",res);
        }
    });

    return false;
}




