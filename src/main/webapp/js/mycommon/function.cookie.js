/**
 * Created by a88u on 2016/11/28.
 */

//写cookies
function setCookie(name, value, days) {
    var Days = days;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

// 随机字符串
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function checkEntIfInMonitor(id) {
    var entMonitorStr = getCookie("entMonitor");

    if (!entMonitorStr) {
        return false;
    }
    var entList = entMonitorStr.split("|");
    for (var i = 0; i < entList.length; i++) {
        if (id == entList[i]) {
            return true;
        }
    }
    return false;
}

function checkOrgIfInMonitor(id) {
    var orgMonitorStr = getCookie("orgMonitor");
    console.log(id, orgMonitorStr);
    if (orgMonitorStr) {
        var orgList = orgMonitorStr.split("|");
        for (var i = 0; i < orgList.length; i++) {
            if (id == orgList[i]) {
                return true;
            }
        }
    }
    return false;
}

function getUserMonitorListByCookie() {
    if (location.href.indexOf("homePage.html") < 0) {
        var entMonitorStr = getCookie("entMonitor");
        var orgMonitorStr = getCookie("orgMonitor");

        var entList = entMonitorStr.split("|");
        var orgList = orgMonitorStr.split("|");

        v_navModel.$data.entMonitorList = entList;
        v_navModel.$data.orgMonitorList = orgList;
    }
}

function getUserMonitorCount() {
    var entMonitorStr = getCookie("entMonitor");
    var orgMonitorStr = getCookie("orgMonitor");

    var entList = entMonitorStr.split("|");
    var orgList = orgMonitorStr.split("|");
    return entList.length + orgList.length;
}

