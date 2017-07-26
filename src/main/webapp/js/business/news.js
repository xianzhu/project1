/**
 * Created by a88u on 2016/10/12.
 */
menuList.news.isActive=true;

var v_newsModel=new Vue({
    el:"#v-newsModel",
    data:{
        report:[], // 研究报告
        postProject:[], // 项目推荐
        keyType:2, // 关键字类型 0-关键字；1-行业

        ifilterKey:"", // 类型过滤关键字
        filterKey:"", // 新闻搜索
        industryKey:"",// 行业搜索关键字
        iFilterType:"",
        newsList:[],
        newsPage:0,
        newsEnd:false,
        newsOrder:2,

        industry_list:clone(industrySelectionList),
        reportPage:0,
        reportEnd:false
    },
    methods:{
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        gotoEntCompany:function(id){ // 项目推荐跳转的一定是非上市公司
            console.log(id);
            gotoCompanybyId(id);
        },
        pageControlFilter:function(value,type){
            //console.log(value,", ",this.newsPage,",",this.isEnd);
            var page=0;
            var isEnd=false;

            if(type==1){ // news
                page=this.newsPage;
                isEnd=this.newsEnd;
                //console.log(isEnd);
            }else if(type==2){ // report
                page=this.reportPage;
                isEnd=this.reportEnd;
            }

            if(value==0){
                //console.log('上:',type,page!=0);
                return page!=0;
            }else{
                //console.log('下:',type,!isEnd);
                return !isEnd;
            }
        },
        changePage:function(value,type){
            if(type==1){ // 新闻
                if(value==0){
                    this.newsPage--;
                }else{
                    this.newsPage++;
                }
                getSubNewsPage();
            }else if(type==2){ // 研究报告
                if(value==0){
                    this.reportPage--;
                }else{
                    this.reportPage++;
                }
                getSubReportPage();
            }

        },
        changeOrder:function(){
            // this.newsOrder=order;
            this.newsPage=0;
            getSubNewsPage();
        },
        changeKeyType:function () {
            console.log("change");
            this.$data.filterKey="";
            // this.$data.newsOrder=1;
            this.$data.ifilterKey="";
            this.$data.iFilterType="";
            this.$nextTick(function () {
                $(".active-div button").attr("disabled",false);
                $(".unactive-div button").attr("disabled",true);
            });
        },
        selectedIndustryClass:function (item) {
            this.$data.industryKey=item.text;
            this.$data.iFilterType=item.value;
            this.$data.ifilterKey=item.text;
            doSearch();
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        formatStringFilter:function(value){
            if(value&&value.toLowerCase()!="null"){
                return value;
            }
            return "";
        },
        formatCommentsFilter:function(value){
            if(value&&value.toLowerCase()!="null"){
                return value;
            }
            return "暂无";
        },
        getNewsShortStrFilter: function (value) {
            if(value&&value.toLowerCase()!="null"){
                return getSubString(value,80);
            }else{
                return "";
            }
        },
        checkEmptyFilter:function(value){
            var result=true;
            if(value&&value.length>0){
                result=false;
            }
            return result;
        },
        checkNotEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        }
    }
});

getSubNewsPage();
getSubReportPage();

requestProjectInfo();

// 请求项目推荐
function requestProjectInfo(){
    var url=commonUrls.newsProjectUrl;
    var domain=v_newsModel.$data.keyType==1?v_newsModel.$data.industryKey:"";
    if(domain=="全部"){
        domain="";
    }
    v_newsModel.$data.postProject=[];
    $.ajax({
        url:url,
        type:'post',
        dataType:'json',
        data:{
            key:v_newsModel.$data.keyType==0?v_newsModel.$data.filterKey:"",
            domain:domain,
            keyType:v_newsModel.$data.keyType,
            from:0,
            count:commonPageNum.newsProjects
        },
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response=res;
                v_newsModel.$data.postProject=response.projectList;
                v_newsModel.$nextTick(function(){
                    initPopover();
                });
            }
        }
    });
}

// 搜索新闻--推送新闻
function getSubNewsPage(){
    var from=v_newsModel.$data.newsPage*commonPageNum.newsList;
    v_newsModel.$data.newsEnd=true;
    var order=v_newsModel.$data.newsOrder;

    var url=commonUrls.newsUrl;
    $.ajax({
        url: url,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:v_newsModel.$data.keyType==0?v_newsModel.$data.filterKey:"",
            keyType:v_newsModel.$data.keyType,
            id:v_newsModel.$data.keyType==1?v_newsModel.$data.iFilterType:"",
            order:order,
            from:from,
            count:commonPageNum.newsList
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
                v_newsModel.$data.newsList=[];
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success") {
                v_newsModel.$data.newsList = res.list;
                if (v_newsModel.$data.newsList.length == 0) {
                    v_newsModel.$data.newsEnd = true;
                } else {
                    v_newsModel.$data.newsEnd = false;
                }
            }
        },
        fail: function (status) {
            console.error("error. status=", status);
        },
        statusCode: {
/*            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }*/
        }
    });
}

// 搜索报告
function getSubReportPage(){
    var from=0;
    var count=0;
    var type="";

    type="trader";
    from=v_newsModel.$data.reportPage*commonPageNum.newsTraderReports;
    count=commonPageNum.newsTraderReports;

    $.ajax({
        url: commonUrls.reportUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:v_newsModel.$data.keyType==0?v_newsModel.$data.filterKey:"",
            filter:v_newsModel.$data.keyType==1?v_newsModel.$data.iFilterType:"",
            keyType:v_newsModel.$data.keyType,
            from:from,
            count:count,
            type:type
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure: ",res.message);
                v_newsModel.$data.report=[];
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response=res;
                v_newsModel.$data.report=response.trader_report_list;
                if(v_newsModel.$data.report.length==0){
                    v_newsModel.$data.reportEnd=true;
                }else{
                    v_newsModel.$data.reportEnd=false;
                }
            }
        },
        fail: function (status) {
            console.error("error. status=", status);
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
}

// 回车执行搜索
function doInputSearch(event){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        doSearch();
    }
}

// 搜索
function doSearch() {
    console.log("do search");
    var key = v_newsModel.$data.filterKey;
    var industry=v_newsModel.$data.industryKey;
    var type=v_newsModel.$data.keyType;
    var industryType=v_newsModel.$data.iFilterType;

    if(type==1){ // 类型搜索若行业类型为空，如何处理？
        if(industry==""||industryType==""){
            console.log("no industry selected");
            // return;
        }
    }

    console.log("search key=",key);
    v_newsModel.$data.newsPage=0;
    v_newsModel.$data.newsOrder=2;
    getSubNewsPage();

    v_newsModel.$data.reportPage=0;
    getSubReportPage();
    requestProjectInfo();
}

function showIndustryList() {
    v_newsModel.$data.showIndustryList=true;
}

function hideIndustryList() {
    console.log("hide industry");
    // return;
    $("#filter_input_div").css('opacity','0');
    setTimeout(function () {
        if(clearClick){ // 如果是clear
            $("#filter_input_div").css('opacity','1').css('display','inline-block');
        }else{
            $("#filter_input_div").css('opacity','1').css('display','none');
        }
        clearClick=false;
    },500);
}

function showIndustryKeyFunc() {
    console.log("show input Key div");
    v_newsModel.$data.ifilterKey="";
    $("#filter_input_div").css('opacity','1').css('display','inline-block');
    document.getElementById("ifilterKey_input").focus();
}
function oninputIndustryKey(event,value) {
    var event=event||window.event;
    console.log(value);
    v_newsModel.$data.ifilterKey=value;
}
var clearClick=false;
function clearIndustryInput() {
    v_newsModel.$data.ifilterKey="";
    v_newsModel.$data.industryKey="";
    v_newsModel.$data.iFilterType="";
    console.log("next");
    clearClick=true;
    v_newsModel.$nextTick(function () {
        $("#filter_input_div").css('opacity','1').css('display','inline-block');
        document.getElementById("ifilterKey_input").focus();
    });
}
