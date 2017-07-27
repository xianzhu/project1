/**
 * Created by a88u on 2017/7/14.
 */

function login() {
    var url=commonUrls.loginUrl;
    var uname = $("#unameIpt").val(), password = $("#pwdIpt").val();
    console.log(uname, password);
    if (uname == "" || password == "") {
        $("#warning").html("请输入用户名、密码").css("display", 'block');
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
                $("#warning").html("用户名或密码错误，请重新输入！").css("display", 'block');
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



