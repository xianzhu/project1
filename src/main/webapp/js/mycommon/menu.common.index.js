/**
 * Created by a88u on 2016/10/8.
 */

// 获取用户名密码
var uname = getUrlQueryStr("uname", location.href);
var password="";

var score=0;
var id = getUrlQueryStr("id", location.href);

if(!uname){
    uname="test";
    password="test321";
    console.log(uname,password);
    login();
}

var v_userModel=new Vue({
    el:"#v-userModel",
    data:{
        uname: uname,
        uscore: score,
        organizeName:"",
        logoPath:"img/logo.jpg",
        newMessageNum:0
    },
    filters:{
        userScoreFilter:function(value){
            return value+"分";
        },
        welcomeFilter:function(value){
            return value+", ";
        },
        showNewMessage:function(value){
            if(value>0){
                return value;
            }else{
                return "";
            }
        }
    }
});

var v_navModel = new Vue({
    el: "#v-navModel",
    data: {
        id: id,
        logoPath:"img/logo.jpg",
        menuList: menuList,
        isCompany:false,
        comType:-1
    },
    ready: function () {
    },
    methods: {
        goto: function (url, id) {
            if (url != '#') {
                if(checkIfCompanyMenu(url)){ // 判断是否是企业菜单，如果是企业菜单，需要加企业类型
                    gotoCompanyPage(url,id,this.comType);
                }else {
                    //console.log(url, ", ", id);
                    gotoMenuPage(url, id);
                }
            }
        }
    }
});


$(document).ready(function () {
    // Add body-small class if window less than 768px
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }

    // MetsiMenu
    $('#side-menu').metisMenu();

    // Full height of sidebar
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 61;
        //$(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");

        var navbarHeigh = $('nav.navbar-default').height();
        var wrapperHeigh = $('#page-wrapper').height();

        if (navbarHeigh > wrapperHeigh) {
            $('#page-wrapper').css("min-height", navbarHeigh + "px");
        }

        if (navbarHeigh < wrapperHeigh) {
            $('#page-wrapper').css("min-height", $(window).height() + "px");
        }

        if ($('body').hasClass('fixed-nav')) {
            if (navbarHeigh > wrapperHeigh) {
                $('#page-wrapper').css("min-height", navbarHeigh - 60 + "px");
            } else {
                $('#page-wrapper').css("min-height", $(window).height() - 60 + "px");
            }
        }

    }

    fix_height();

    $('.check-link').click(function () {
        var button = $(this).find('i');
        var label = $(this).next('span');
//console.log(label.attr("value"));
        button.toggleClass('fa-check-square').toggleClass('fa-square-o');

        if (label.hasClass("select-all-itemsList")) {
            var par = $(this).closest("ul").find("li").find("a")
                .find("i");
            if (button.hasClass("fa-square-o")) {
                par.addClass("fa-square-o").removeClass("fa-check-square");

            } else if (button.hasClass("fa-check-square")) {
                par.removeClass("fa-square-o").addClass("fa-check-square");
            }
        }

        return false;
    });

    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    // Fixed Sidebar
    $(window).bind("load", function () {
        if ($("body").hasClass('fixed-sidebar')) {
            $('.sidebar-collapse').slimScroll({
                height: '100%',
                railOpacity: 0.9
            });
        }
    });

    $(window).bind("load resize scroll", function () {
        if (!$("body").hasClass('body-small')) {
            fix_height();
        }
    });

    $("[data-toggle=popover]")
        .popover();
});

// Minimalize menu when screen is less than 768px
$(window).bind("resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

// check if browser support HTML5 local storage
/*function localStorageSupport() {
    return (('localStorage' in window) && window['localStorage'] !== null)
}*/

function SmoothlyMenu() {
    if($('body').hasClass('mini-navbar')){
        $('#logo-img-bar').css("display","none");
    }else{
        $('#logo-img-bar').css("display","block");
    }

    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 200);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 100);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
}

function login(){
    var name=uname;
    var pwd=password;
    var url=commonUrls.loginUrl;

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
                 console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            } else {
                var response=res;
                console.log("login success:",response);
            }
        },
        fail:function(res){
            console.log("fail:",res);
        }
    });
}




