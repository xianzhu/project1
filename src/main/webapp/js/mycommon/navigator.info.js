var commonNavInfo={
    "referurl":document.referrer, // 上一跳url
    "nuseragent":navigator.userAgent, // 浏览器属性
    "nplatform":navigator.platform, // 系统平台
    "resolution":window.screen.width+"x"+window.screen.height, // 屏幕分辨率
    "colorpepth":window.screen.colorDepth, // 颜色质量
    "checkFlash":checkePlugs('Shockwave Flash'), // 是否有flash插件
    "pluginsNum":navigator.plugins.length, // 插件数量
    "pluginsName":getPluginName(), // 插件名称
    "cookieId":"", // cookieId
    "ipAddress":"", // ip地址
    "token":"" // 登录服务器返回的token
};
$(function(){
    getIps();
    // console.log(commonNavInfo);
});
//获取插件所有的名称
function getPluginName() {
    var info = "";
    var plugins = navigator.plugins;
    if (plugins.length > 0) {
        for (i = 0; i < navigator.plugins.length; i++) {
            info += navigator.plugins[i].name + ";";
        }
    }
    return info;
}
//检查是否安装了某插件，如果安装了返回版本号
function checkePlugs(pluginname) {
    var f = "-"
    var plugins = navigator.plugins;
    if (plugins.length > 0) {
        for (i = 0; i < navigator.plugins.length; i++) {
            if (navigator.plugins[i].name.indexOf(pluginname) >= 0) {
                f = navigator.plugins[i].description.split(pluginname)[1];
                return f;
                break;
            }
        }
    }
    return false;
}
//判断是否IE
function isIe() {
    var i = navigator.userAgent.toLowerCase().indexOf("msie");
    return i >= 0;
}
//判断是否firefox
function isFireFox() {
    var i = navigator.userAgent.toLowerCase().indexOf("firefox");
    return i >= 0;
}

function getIps() {
    var url = 'http://chaxun.1616.net/s.php?type=ip&output=json&callback=?&_='+Math.random();
    $.getJSON(url, function(data){
        commonNavInfo.ipAddress=data.Ip;
        // $("#allPCInfo").html("显示：IP【"+data.Ip+"】 地址【"+data.Isp+"】 浏览器【"+data.Browser+"】 系统【"+data.OS+"】");
    });
}
