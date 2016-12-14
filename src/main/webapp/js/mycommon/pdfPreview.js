/**
 * Created by a88u on 2016/8/7.
 */



var isTest=true;
//var isTest=false;

$(function(){
    var psrc="";
    if(isTest){
        psrc="http://www.peseer.com/";
    }

    //var type=getUrlQueryStr('type',location.href);
    //var id=getUrlQueryStr('id',location.href);
    //
    //if(type=="trader"){
    //    psrc=psrc+"trader_rpt?id="+id;
    //}else if(type=="cv"){
    //    psrc=psrc+"cv_rpt?id="+id;
    //}
    var path=getUrlQueryStr('path',location.href);
    psrc=psrc+path;

    $.ajax({
        url: psrc,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        success: function (res) {
            console.log(res);
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                $("#viewIframe").attr('src',"404.html");
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else{
                console.log("success...");
                $("#viewIframe").attr('src',psrc);
                //alert(res);
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
});






