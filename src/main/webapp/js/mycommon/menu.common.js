/**
 * Created by a88u on 2016/10/8.
 */

// 获取用户名密码
var uname = getUrlQueryStr("uname", location.href);

var score=0;
var type=getUrlQueryStr("comtype",location.href);

var searchTypeMap = {
    person:"person",
    orgnazation:"org",
    company:"company",
    all:"all"
};

var v_userModel=new Vue({
    el:"#v-userModel",
    data:{
        uname: uname,
        uscore: score,
        searchList:[
            {
                value:searchTypeMap.all,
                text:"所有"
            },
            {
                value:searchTypeMap.person,
                text:"投资者"
            },
            {
                value:searchTypeMap.orgnazation,
                text:"机构"
            },
            {
                value:searchTypeMap.company,
                text:"企业"
            }
        ],
        currentSearchSelect:searchTypeMap.all,
        organizeName:"",
        logoPath:"img/kdlogo-1.png",
        // logoPath:"img/logo.png",
        newMessageNum:0
    },
    methods:{
        changeSearchSelection:function(){
            this.$data.currentSearchSelect = $("#currentSearchSelect").val();
            console.log(this.$data.currentSearchSelect);
            if(this.$data.currentSearchSelect==searchTypeMap.company){
                $(".navbar-top-links .search-bar").addClass("company-selected-li");
            }else{
                $(".navbar-top-links .search-bar").removeClass("company-selected-li");
            }
        },
        gotoProject:function () {
            gotoProjectPage();
        },
        gotoNews:function(){
            gotoNewsPage();
        },
        gotoSimulation:function(){
            gotoSimulationPage();
        },
        gotoCustomerSetting:function(){
            gotoCustSettingPage();
        },
        gotoTraderReport:function () {
            gotoTraderReportPage();
        },
        gotoCvReport:function () {
            gotoCvReportPage();
        }
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
        menuList: menuList,
    },
    ready: function () {
    },
    methods: {
        goto:function (url) {
            var uname=v_userModel.$data.uname,score=v_userModel.$data.score;
            var murl = url + ".html?uname=" + uname + "&score=" + score;
            window.location.href = murl;
        }
    }
});

// 头部查询模块
var v_search_resultModel=new Vue({
    el:"#v-search-result-mask-info",
    data:{
        searchKey:"",
        title:"查询结果", // 标题
        showMask:false, // 是否显示
        showResult:0, // 显示结果：0 - loading；1 - table；2 - no result；3 - none
        currentModule:1, // 当前查询内容：1 - 投资者；2 - 机构；3 - 企业
        pResult:[], // 投资者查询结果
        oResult:[], // 机构查询结果
        cResult:[], // 公司查询结果
        currentPage:0,
        searchResultEmpty:true, // 查询结果是否为空
        isEnd:false
    },
    methods:{
        close:function(){
            this.$data.showMask=false;
            v_search_resultModel.$data.showResult=3;
        },
        pageControlShow:function(value){
            if(value==0){
                return this.currentPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        checkModule:function(value){
            return this.$data.currentModule==value;
        },
        changeResultPage:function(value){
            if(value==0){ // 上一页
                this.currentPage--;
            }else{ // 下一页
                this.currentPage++;
            }
            this.getSubResultPage();
        },
        getSubResultPage:function(){
            if(v_userModel.$data.currentSearchSelect==searchTypeMap.person){
                console.log("do person search");
                this.doPersonSearch(this.searchKey,this.currentPage);
            }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.orgnazation){
                console.log("do org search");
                this.doOrgSearch(this.searchKey,this.currentPage);
            }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.company){
                console.log("do company search");
                this.doCompanySearch(this.searchKey,this.currentPage);
            }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.all){
                console.log("do all search");
                gotoMutiSearch(this.searchKey);
            }
        },
        doPersonSearch:function(skey,page){
            console.log(this.$data.showResult,v_search_resultModel.$data.showResult);
            var turl=commonUrls.personSearchUrl;
            this.$data.title="投资者查询结果";
            this.$data.currentModule=1;
            this.$data.currentPage=page;
            v_search_resultModel.$data.showResult=0;
            this.$data.showMask=true;

            $.ajax({
                url: turl,              //请求地址
                type: "POST",                            //请求方式
                data: { //请求参数
                    key:skey,
                    from:page*commonPageNum.topSearch_person
                },
                dataType: "json",
                success: function (res) {
                    if(res.status=='success') {
                        $('#personal_result_table').DataTable().destroy();
                        v_search_resultModel.$data.pResult=res.userList;
                        v_search_resultModel.$data.searchResultEmpty=(res.userList.length==0);
                        v_search_resultModel.$data.isEnd=(res.userList.length<commonPageNum.topSearch_person);

                        v_search_resultModel.$nextTick(function () {
                            console.log("bind person",v_search_resultModel.$data.pResult.length);
                            bindExportedDataTable("personal_result_table", -1, "投资者查询结果");
                            v_search_resultModel.$data.showResult=1;
                        });
                    }else{
                        v_search_resultModel.$data.showResult=2;
                    }
                },
                fail: function (status) {
                    console.error("error. status=", status);
                    v_search_resultModel.$data.showResult=2;
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
        },
        doOrgSearch:function (skey,page) {
            var turl=commonUrls.orgSearchUrl;
            this.$data.title="机构查询结果";
            this.$data.currentModule=2;
            this.$data.currentPage=page;
            v_search_resultModel.$data.showResult=0;
            this.$data.showMask=true;

            $.ajax({
                url: turl,              //请求地址
                type: "POST",                            //请求方式
                data: { //请求参数
                    key:skey,
                    from:page*commonPageNum.topSearch_org
                },
                dataType: "json",
                success: function (res) {
                    if(res.status=='success') {
                        $('#org_result_table').DataTable().destroy();
                        v_search_resultModel.$data.oResult=res.orgList;
                        v_search_resultModel.$data.searchResultEmpty=(res.orgList.length==0);
                        v_search_resultModel.$data.isEnd=(res.orgList.length<commonPageNum.topSearch_person);

                        v_search_resultModel.$nextTick(function () {
                            console.log("bind org");
                            bindExportedDataTable("org_result_table", -1, "投资者查询结果");
                            v_search_resultModel.$data.showResult=1;
                        });
                    }else{
                        v_search_resultModel.$data.showResult=2;
                    }
                },
                fail: function (status) {
                    console.error("error. status=", status);
                    v_search_resultModel.$data.showResult=2;
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
        },
        doCompanySearch:function (skey,page) {
            var turl=commonUrls.companySearchUrl;
            this.$data.title="企业查询结果";
            this.$data.currentModule=3;
            this.$data.currentPage=page;
            v_search_resultModel.$data.showResult=0;
            this.$data.showMask=true;
            var stype=$('#stockCompanyRadio').is(":checked");
            $.ajax({
                url: turl,              //请求地址
                type: "POST",                            //请求方式
                data: { //请求参数
                    key:skey,
                    from:page*commonPageNum.topSearch_person,
                    type:stype
                },
                dataType: "json",
                success: function (res) {
                    if(res.status=='success') {
                        $('#company_result_table').DataTable().destroy();
                        v_search_resultModel.$data.cResult=res.searchResult;
                        v_search_resultModel.$data.searchResultEmpty=(res.searchResult.length==0);
                        v_search_resultModel.$data.isEnd=(res.searchResult.length<commonPageNum.topSearch_person);

                        v_search_resultModel.$nextTick(function () {
                            console.log("bind com");
                            bindExportedDataTable("company_result_table", -1, "企业查询结果");
                            v_search_resultModel.$data.showResult=1;
                        });
                    }else{
                        v_search_resultModel.$data.showResult=2;
                    }
                },
                fail: function (status) {
                    console.error("error. status=", status);
                    v_search_resultModel.$data.showResult=2;
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
        },
        gotoPersonal:function (id) {
            gotoPersonPage(id);
        },
        gotoOrg:function (id) {
            gotoOrgPage(id);
        },
        gotoCompany:function (id,type,stype,scode) { // type:是否上市[0-非上市，1-上市]；stype:是否新三板[0-A股，1-新三板]
            var ctype=0; // 0---A股；1---新三板；2---非上市
            if(type==0){
                ctype=2;
            }else{
                if(stype==0){
                    ctype=0;
                }else{
                    ctype=1;
                }
            }
            gotoCompanyPage(id,ctype,scode);
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=this.currentPage+1;
            return "第"+p+"页";
        },
        formatEmptyFilter:function(value){
            var result="--";
            if(value&&value!=""){
                result=value;
            }
            return result;
        },
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        },
        formatDataFilter:function(value){
            return toDataFormat(value);
        }
    }
});

if(typeof type != 'undefined'){
    v_navModel.$data.isCompany=true;
    v_navModel.$data.comType=type;
}else{
    v_navModel.$data.isCompany=false;
    v_navModel.$data.comType=-1;
}

$(document).ready(function () {
    // Add body-small class if window less than 768px
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }

    $.easing.def = "easeOutBounce";

    $('#side-menu>li').click(function(e){
        var dropDown = $(this).find(".menu-dropdown");
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            dropDown.slideUp('slow');
        }else {
            $('#side-menu>li').not(this).removeClass("active");
            $(this).addClass('active');
            $('.menu-dropdown').not(dropDown).slideUp('slow');
            dropDown.slideDown('slow');
        }
        e.preventDefault();
    });
    $('.navbar-report-item').mouseover(function(e){
        $(this).addClass('show-list');
    });
    $('.navbar-report-item').mouseout(function(e){
        console.log("mouseout");
        $(this).removeClass('show-list');
    });
    // Full height of sidebar
    function fix_height() {
        var navbarHeigh = $('#nav-wrapper').height();
        var wrapperHeigh = $('#page-wrapper').height();

        if (navbarHeigh > wrapperHeigh) {
            $('#page-wrapper').css("min-height", navbarHeigh - 60 + "px");
        } else {
            $('#page-wrapper').css("min-height", $(window).height() - 60 + "px");
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

    // Fixed Sidebar
    // $(window).bind("load", function () {
    //     if ($("body").hasClass('fixed-sidebar')) {
    //         $('.sidebar-collapse').slimScroll({
    //             height: '100%',
    //             railOpacity: 0.9
    //         });
    //     }
    // });

    $("[data-toggle=popover]") .popover();
});

// Minimalize menu when screen is less than 768px
$(window).bind("resize", function () {
    if ($(this).width() < 769) {
        $('body').addClass('body-small')
    } else {
        $('body').removeClass('body-small')
    }
});

// function SmoothlyMenu() {
//     if($('body').hasClass('mini-navbar')){
//         $('#logo-img-bar').css("display","none");
//     }else{
//         $('#logo-img-bar').css("display","block");
//     }
//
//     if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
//         // Hide menu in order to smoothly turn on when maximize menu
//         $('#side-menu').hide();
//         // For smoothly turn on menu
//         setTimeout(
//             function () {
//                 $('#side-menu').fadeIn(400);
//             }, 200);
//     } else if ($('body').hasClass('fixed-sidebar')) {
//         $('#side-menu').hide();
//         setTimeout(
//             function () {
//                 $('#side-menu').fadeIn(400);
//             }, 100);
//     } else {
//         // Remove all inline style from jquery fadeIn function to reset menu state
//         $('#side-menu').removeAttr('style');
//     }
// }

// 综合查询
function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = value;
        console.log("searchBarPress#", key, "#");
        doHeaderSearch();
    }
}

function doHeaderSearch(){
    var skey=$("#top-search").val();
    if(skey==""){
        return;
    }
    v_search_resultModel.$data.searchKey=skey;
    if(v_userModel.$data.currentSearchSelect==searchTypeMap.person){
        console.log("do person search");
        v_search_resultModel.doPersonSearch(skey,0);
    }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.orgnazation){
        console.log("do org search");
        v_search_resultModel.doOrgSearch(skey,0);
    }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.company){
        console.log("do company search");
        v_search_resultModel.doCompanySearch(skey,0);
    }else if(v_userModel.$data.currentSearchSelect==searchTypeMap.all){
        console.log("do all search");
        gotoMutiSearch(skey);
    }

}


