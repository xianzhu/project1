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
            console.log("cate");
            doLoopCategory();
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
})
$(window).resize(function(){
    re();

})


var v_indexCateMenuModel=new Vue({
    el:"#v-cateMenuModel",
    data:{
        categoriesList:categoriesList
    },
    methods:{
        //gotoCategory:function(id,tid){
        //    window.location.href="category_index.html?id="+id+"&tpc="+tid;
        //}
        gotoCategory:function(id,tid,name){
            window.location.href="category_index.html?id="+id+"&tpc="+tid+"&catename="+name;
        }
    }
})


var v_indexNewsModel=new Vue({
    el:"#v-indexNewsModel",
    data:{
        cateNews:[]
    }
});
function getNewsList(){
    var url="elasticsearch?mode=top";
    $.ajax({
        url:url,
        type:'post',
        /*data:{
            mode:"top",
            key:""
        },*/
        dataType:'json',
        success: function (data) {
            var dataList = data;
            v_indexNewsModel.$data.cateNews=dataList;
        },
        error:function(status){
        }
    })
}

var pic;
function doLoopCategory(){
    //if(pic) {
    //    pic.stopPlay();
    //}
    //console.log($("#category_list_dom").PicCarousel);
    //$("#category_list_dom").PicCarousel().destroy();
    var dom=document.getElementById("test_category_row");
    var w=dom.offsetWidth;
    var h=dom.offsetHeight;
    //console.log("test_category_row:",w,',',h);

    var options={
        "width":w*0.95,		//幻灯片的宽度
        "height":400,		//幻灯片的高度
        "posterWidth":w*0.618,	//幻灯片第一帧的宽度
        "posterHeight":400, //幻灯片第一张的高度
        "scale":1,		//记录显示比例关系
        "speed":1500,		//记录幻灯片滚动速度
        "autoPlay":true,	//是否开启自动播放
        "delay":1000*5,		//自动播放间隔
        "verticalAlign":"middle"	//图片对齐位置
    };

    $("#category_list_dom").PicCarousel(options);
    //console.log($("#category_list_dom").PicCarousel());
}

//window.onresize=doLoopCategory;

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








