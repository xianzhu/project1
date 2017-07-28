/**
 * Created by a88u on 2017/7/14.
 */

function login() {
    var url=commonUrls.loginUrl;
    var uname = $("#unameIpt").val(), password = $("#pwdIpt").val();
    console.log(uname, password);
    if (uname == "" || password == "") {
        $("#warning").html("请输入用户名、密码!");
        $(".form-control-warning-group").css("display", 'block');
        return;
    }
    var pwd = hex_md5(password);
    console.log("login:", uname, ', ', pwd);
    $.ajax({
        "url": url,
        type: 'POST',
        dataType: 'json',
        data: {
            user_name: uname,
            password: pwd
        },
        success: function (res) {
            console.log(res);
            if (res.status == "failure") {
                console.log("login failure");
                $("#warning").html("用户名或密码错误，请重新输入！");
                $(".form-control-warning-group").css("display", 'block');
            } else {
                var response = res;
                console.log("login success:", response);
                window.location.href = "homePage.html?uname=" + uname + "&score=0";
            }
        },
        fail: function (res) {
            console.log("fail:", res);
        }
    });
}

function showLoginView() {
    // $(".loginColumns").show(500);
    $(".form-control-warning-group").css("display", 'none');
    $(".Bomb-box").show(500);
}

function hideLoginView() {
    // $(".loginColumns").hide(500);
    $(".Bomb-box").hide(500);
}

$(function(){
    $('.bg').click(function (e) {
        $(".Bomb-box").hide(500);
    });
    $(window).resize(function () {
        resizeEarthImg();
    });
});
$(window).ready(function () {
    resizeEarthImg();
    canvasStarShow();
});
function resizeEarthImg() {
    var iheight=$(".earth-img").height(),cheight=$("body").height();
    var itop=(cheight-iheight)*0.5,ltop=(cheight-302)*0.45;
    // console.log(iheight,cheight,itop);
    if(iheight==0){
        setInterval(resizeEarthImg,100);
    }else{
        $(".earth-img").css("top",itop+"px");
        if(ltop>0) {
            $(".loginColumns").css("margin-top", ltop + "px");
        }
    }
}

