/**
 * Created by a88u on 2016/10/8.
 */

// 获取用户名密码
var uname = getUrlQueryStr("uname", location.href);

var score=0;
var type=getUrlQueryStr("comtype",location.href);

resetDivSize();

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
                    from:page*commonPageNum.topSearch_person,
                    count:commonPageNum.topSearch_person
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
                    from:page*commonPageNum.topSearch_org,
                    count:commonPageNum.topSearch_org
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
                    count:commonPageNum.topSearch_person,
                    type:stype
                },
                dataType: "json",
                success: function (res) {
                    if(res.status=='success') {
                        $('#company_result_table').DataTable().destroy();
                        // v_search_resultModel.$data.cResult=res.searchResult;
                        var cresult=[];
                        for(var i=0;i<res.searchResult.length&&i<commonPageNum.topSearch_company;i++){
                            cresult.push(res.searchResult[i]);
                        }
                        v_search_resultModel.$data.cResult=cresult;
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
        },
        strFormatFilter:function (value) {
            var result="-";
            if(typeof value!="undefined"&&value!=null&&value!="null"&&value!=""){
                result=value;
            }
            return result;
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
    // $('.navbar-report-item').mouseover(function(e){
    //     $(this).addClass('show-list');
    // });
    // $('.navbar-report-item').mouseout(function(e){
    //     console.log("mouseout");
    //     $(this).removeClass('show-list');
    // });
    // Full height of sidebar


    fix_height();

    $("#logo-img-bar").click(function(){
        // console.log(getWindowSize());
        window.open("guide.html","_blank");
    });

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
    $("[data-toggle=popover]") .popover();
});

// Minimalize menu when screen is less than 768px
$(window).bind("resize", fix_height);

function fix_height() {
    var navbarHeigh = $('#nav-wrapper').height();
    var wrapperHeigh = $('#page-wrapper').height();

    if (navbarHeigh > wrapperHeigh) {
        $('#page-wrapper').css("min-height", navbarHeigh - 60 + "px");
    } else {
        $('#page-wrapper').css("min-height", $(window).height() - 60 + "px");
    }
}

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

function resetDivSize() {
    var size=getWindowSize();
    var height=size.height,width=size.width;
    console.log(width);
    if(width>=2560){ // >=2560
        commonPageNum.topSearch_person=10;//
        commonPageNum.topSearch_org=10;//
        commonPageNum.topSearch_company=10;//
        commonPageNum.orgFocuse=8;//
        commonPageNum.orgTeam=8;//
        commonPageNum.orgFund=8;//
        commonPageNum.orgElastic=16;//
        commonPageNum.orgFundInvEventNum=31;//
        commonPageNum.orgFundExitEventNum=31;//
        commonPageNum.cvReports=32;//
        commonPageNum.traderReports=32;//
        commonPageNum.newsList=25;//指定25个返回20个---20个正好
        commonPageNum.newsTraderReports=9;//
        commonPageNum.newsProjects=8;//
        commonPageNum.projectList=9;//
        commonPageNum.sysMonitorRpt=27;//
        commonPageNum.sysMonitorMerge=32;//
        commonPageNum.personNews=17;//指定17个返回16个---16个正好
        commonPageNum.personEvents=9;//
        commonPageNum.homeEventList=15;//没有足够多数据
        commonPageNum.homeNewsList=16;//指定16个返回15个---15个正好
        commonPageNum.homepageInvest=7;//没有足够多数据
        commonPageNum.homepageExit=7;//没有足够多数据
        commonPageNum.mutisearchCompany=8;//
        commonPageNum.mutisearchOrg=16;//
        commonPageNum.mutisearchCv=8;//
        commonPageNum.mutisearchTrader=8;//
        commonPageNum.mutiSearchNews=18;//
        commonPageNum.cusSetMonitor=10;//
        commonPageNum.simulation=30;//
    }else if(width>=1920){ // 1920---2560
        commonPageNum.topSearch_person=13;
        commonPageNum.topSearch_org=13;
        commonPageNum.topSearch_company=13;
        commonPageNum.orgFocuse=5;
        commonPageNum.orgTeam=5;
        commonPageNum.orgFund=5;
        commonPageNum.orgElastic=11;
        commonPageNum.orgFundInvEventNum=25;
        commonPageNum.orgFundExitEventNum=25;
        commonPageNum.cvReports=24;
        commonPageNum.traderReports=24;
        commonPageNum.newsList=13;
        commonPageNum.newsTraderReports=6;
        commonPageNum.newsProjects=6;
        commonPageNum.projectList=8;
        commonPageNum.sysMonitorRpt=17;
        commonPageNum.sysMonitorMerge=23;
        commonPageNum.personNews=10;
        commonPageNum.personEvents=6;
        commonPageNum.homeEventList=12;
        commonPageNum.homeNewsList=10;
        commonPageNum.homepageInvest=6;
        commonPageNum.homepageExit=6;
        commonPageNum.mutisearchCompany=6;
        commonPageNum.mutisearchOrg=12;
        commonPageNum.mutisearchCv=6;
        commonPageNum.mutisearchTrader=6;
        commonPageNum.mutiSearchNews=15;
        commonPageNum.cusSetMonitor=6;
        commonPageNum.simulation=20;
    }else if(width>=1600){ // 1600---1920
        commonPageNum.topSearch_person=13;
        commonPageNum.topSearch_org=13;
        commonPageNum.topSearch_company=13;
        commonPageNum.orgFocuse=5;
        commonPageNum.orgTeam=5;
        commonPageNum.orgFund=5;
        commonPageNum.orgElastic=11;
        commonPageNum.orgFundInvEventNum=25;
        commonPageNum.orgFundExitEventNum=25;
        commonPageNum.cvReports=24;
        commonPageNum.traderReports=24;
        commonPageNum.newsList=13;
        commonPageNum.newsTraderReports=6;
        commonPageNum.newsProjects=6;
        commonPageNum.projectList=8;
        commonPageNum.sysMonitorRpt=17;
        commonPageNum.sysMonitorMerge=23;
        commonPageNum.personNews=10;
        commonPageNum.personEvents=6;
        commonPageNum.homeEventList=12;
        commonPageNum.homeNewsList=10;
        commonPageNum.homepageInvest=6;
        commonPageNum.homepageExit=6;
        commonPageNum.mutisearchCompany=6;
        commonPageNum.mutisearchOrg=12;
        commonPageNum.mutisearchCv=6;
        commonPageNum.mutisearchTrader=6;
        commonPageNum.mutiSearchNews=15;
        commonPageNum.cusSetMonitor=6;
        commonPageNum.simulation=20;
    }else if(width>1440){ // 1440---1600
        commonPageNum.topSearch_person=12;
        commonPageNum.topSearch_org=12;
        commonPageNum.topSearch_company=12;
        commonPageNum.orgFocuse=5;
        commonPageNum.orgTeam=5;
        commonPageNum.orgFund=5;
        commonPageNum.orgElastic=9;
        commonPageNum.orgFundInvEventNum=21;
        commonPageNum.orgFundExitEventNum=21;
        commonPageNum.cvReports=22;
        commonPageNum.traderReports=22;
        commonPageNum.newsList=12;
        commonPageNum.newsTraderReports=6;
        commonPageNum.newsProjects=6;
        commonPageNum.projectList=8;
        commonPageNum.sysMonitorRpt=14;
        commonPageNum.sysMonitorMerge=23;
        commonPageNum.personNews=10;
        commonPageNum.personEvents=6;
        commonPageNum.homeEventList=10;
        commonPageNum.homeNewsList=10;
        commonPageNum.homepageInvest=6;
        commonPageNum.homepageExit=6;
        commonPageNum.mutisearchCompany=5;
        commonPageNum.mutisearchOrg=10;
        commonPageNum.mutisearchCv=5;
        commonPageNum.mutisearchTrader=5;
        commonPageNum.mutiSearchNews=13;
        commonPageNum.cusSetMonitor=6;
        commonPageNum.simulation=20;
    }else{ // <=1440

    }
}
