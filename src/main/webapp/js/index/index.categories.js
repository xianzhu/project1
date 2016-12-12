/**
 * Created by a88u on 2016/10/9.
 */

var id=getUrlQueryStr("id",location.href);
var catename=getUrlQueryStr("catename",location.href);

getNewsList(id);

setInterval(getNewsList,1000*5);

$(function(){
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

//var catename=getCategoryName(id);
//function getCategoryName(value){
//    var text="分类新闻";
//    for(var i=0;i<categoriesList.length;i++){
//        var items=categoriesList[i];
//        for(var j=0;j<items.length;j++){
//            if(items[j].id==value){
//                text=items[j].name;
//                return text;
//            }
//        }
//    }
//    return text;
//}

var v_cateNewsModel=new Vue({
    el:"#v-cateNewsModel",
    data:{
        categoryName:catename,
        cateNews:[]
    }
});
function getNewsList(){
    $.ajax({
        url:indexCommonUrls.newsUrl,
        type:'post',
        dataType:'json',
        data:{
            from:0,
            id:id
        },
        success: function (data) {
            var dataList = data;
            v_cateNewsModel.$data.cateNews=dataList;
        },
        error:function(status){
        }
    })
}














