/**
 * Created by a88u on 2016/11/28.
 */

function sendMonitor(options){
    var img = new Image(),
        id = 'img' + new Date();
    img.id = id;
    img.onload = img.onerror = img.onabort = function() { window[id] = undefined; };
    window[id] = img;
    var src="testData/loginout.json";

    var params="";
    $.each(options,function(name,value){
        params=params+"&"+name;
        params+="=";
        params=params+value;
        console.log(params);
    });
    if(params.length>1){
        src=src+"?"+params.substr(1,params.length-1);
    }
    img.src = src; // 此处设置src
}

// 获取浏览器信息
function appInfo(){
    var agent = navigator.userAgent.toLowerCase() ;
    var browerInfo="";

    var regStr_ie = /msie [\d.]+;/gi ;
    var regStr_ff = /firefox\/[\d.]+/gi
    var regStr_chrome = /chrome\/[\d.]+/gi ;
    var regStr_saf = /safari\/[\d.]+/gi ;
//IE
    if(agent.indexOf("msie") > 0)
    {
        browerInfo=agent.match(regStr_ie) ;
    }

//firefox
    if(agent.indexOf("firefox") > 0)
    {
        browerInfo=agent.match(regStr_ff) ;
    }

//Chrome
    if(agent.indexOf("chrome") > 0)
    {
        browerInfo=agent.match(regStr_chrome) ;
    }

//Safari
    if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
    {
        browerInfo=agent.match(regStr_saf) ;
    }
    var verinfo = "0";
    if(browerInfo.length>0){
        verinfo=(browerInfo+"").replace(/[^0-9.]/ig,"");
    }

    var browser={browser:browerInfo,version:verinfo};
    //console.log(browser);
    return browser;
}

//写cookies

function setCookie(name,value,days)
{
    var Days = days;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

// 随机字符串
function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}





