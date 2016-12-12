/**
 * Created by a88u on 2016/10/9.
 */

getNewsList();
setInterval(getNewsList,1000*30);
$(function(){
    var url=window.location.href;
    var ind=url.indexOf("#");
    console.log(ind);
    if(ind>0) {
        var page =url.substr(ind,url.length-ind);
        console.log(page);
        if(page=="#cate"){
            $('.home-page-posts').addClass("hide");
            $('.home-page-categories').removeClass("hide");
            $('.select-posts').removeClass("active");
            $('.select-categories').addClass("active");
        }
    }

    $(".input1").click(function(event) {
        $(".input1").addClass('border');
        $(".input2").removeClass('border')
    });
    $(".input2").click(function(event) {
        $(".input2").addClass('border');
        $(".input1").removeClass('border')
    });
    $(".primary-info h1").click(function(event) {
        $(".Bomb-box").show(500);
    });
    $(".bg").click(function(event) {
        $(".Bomb-box").hide(500);
        // $(this).css('display', 'none');
        // $(".Suspension").css('display', 'none');
    });
    //console.log("re");
    re();
    $("#iptName").focus(function(){
        $("#login-fail").css('display', 'none');
    });
    $("#iptPwd").focus(function(){
        $("#login-fail").css('display', 'none');
    });
});

var v_indexCateMenuModel=new Vue({
    el:"#v-cateMenuModel",
    data:{
        menuList:cateGoriesList
    },
    methods:{
        gotoCategory:function(id,tid){
            window.location.href="category_index.html?id="+id+"&tpc="+tid;
        }
    }
});

$(window).resize(function(){
    re();
});

var v_indexNewsModel=new Vue({
    el:"#v-indexNewsModel",
    data:{
        cateNews:[]
    }
});
function getNewsList(){
    $.ajax({
        url:indexCommonUrls.newsUrl,
        type:'post',
        data:{},
        dataType:'json',
        success: function (data) {
            var dataList = data;
            v_indexNewsModel.$data.cateNews=dataList;
        },
        error:function(status){
        }
    })
}

function sendMonitorCV(){
    var options={
        name:"name",
        type:"click",
        param1:"param_01",

    };

    options.cookieId=getCookie("COOKIEID");
    if(options.cookieId == null){
        var cookieid=randomString(15);
        setCookie("COOKIEID",cookieid);
        options.cookieId=cookieid;
    }
    options.appInfo=appInfo();
    sendMonitor(options);
}






