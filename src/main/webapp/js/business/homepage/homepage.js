/**
 * Created by a88u on 2016/10/12.
 */
var v_homepageModel=new Vue({
    el:"#v-homepageModel",
    data:{
        basicInfo:{}, // 基本信息
        homeNews:[], // 新闻地图
        feedbacks:[], // 咨询反馈
        report:[], // 研究报告
        //cvReport:[], // 行业分析
        postProject:[], // 项目推荐
        newsFilterKey:"", // 新闻搜索
        newsPage:0,
        newsEnd:false,
        newsOrder:1,
        reportFilterKey:"", // 报告搜索
        reportPage:0,
        reportEnd:false,
        //cvReportPage:0,
        //cvReportEnd:false,
        currentRptSelect:1,
        statData:{
            report:{},
            ent:{},
            media:{},
            event:{}
        }, // 统计数据
        monitorShow:false, // 暂时拿掉
        monitorData:{}, // 监控数据
        monitorFreq:1
    },
    methods:{
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        monitorChange:function(value){
            //console.log(this.monitorFreq);
            this.monitorFreq=value;
            //console.log(this.monitorFreq);
            refreshChart(value);
        },
        gotoEntCompany:function(id){ // 项目推荐跳转的一定是非上市公司
            console.log(id);
            gotoCompanyPage("companyBasic",id,2);
        },
        pageControlFilter:function(value,type){
            //console.log(value,", ",this.newsPage,",",this.isEnd);
            var page=0;
            var isEnd=false;

            if(type==1){
                page=this.newsPage;
                isEnd=this.newsEnd;
                //console.log(isEnd);
            }else if(type==2){
                page=this.reportPage;
                isEnd=this.reportEnd;
            }
            //else if(type==3){
            //    //page=this.cvReportPage;
            //    //isEnd=this.cvReportEnd;
            //}

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
                getSubNewsPage(this.newsFilterKey,this.newsPage,this.newsOrder);
            }else if(type==2){ // 研究报告
                if(value==0){
                    this.reportPage--;
                }else{
                    this.reportPage++;
                }
                getSubReportPage(this.reportFilterKey,this.reportPage,this.currentRptSelect);
            }
            //else if(type==3){ // 行业分析
            //    if(value==0){
            //        this.cvReportPage--;
            //    }else{
            //        this.cvReportPage++;
            //    }
            //    getSubReportPage(this.reportFilterKey,this.cvReportPage,2);
            //}

        },
        changeOrder:function(){
            if(this.newsOrder==2){
                this.newsOrder=1;
            }else{
                this.newsOrder=2;
            }
            this.newsPage=0;
            getSubNewsPage(this.newsFilterKey,this.newsPage,this.newsOrder);
        },
        changeRptSelect:function(value){
            this.currentRptSelect=value;
            console.log("change: ",this.currentRptSelect);
            this.reportPage=0;
            //this.cvReportPage=0;
            getSubReportPage(this.reportFilterKey,0,value);
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        orderStringFilter:function(value){
            //console.log("order:", value);
            return value==1? "时间排序":"相关性排序";
        },
        reportUrlFilter:function(value){
            return value;
        },
        formatFilter:function(value){
            if(typeof value=="undefined"){
                return '--';
            }
            //return toRateFormat(value,2);
            return value;
        },
        amountFormatFilter:function(value) {
            if(typeof value=="undefined"){
                return '--';
            }
            return toAmountFormat(value, 0,'');
        },
        getShortStrFilter:function(value){
            var result=value;
          if(value&&value.length>100){
              result=result.substr(0,100)+"...";
          }
            return result;
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
        },
        checkCrtRpFilter:function(value){
            return value==this.currentRptSelect;
        }
    }
});

requestBasicInfo();
getSubNewsPage("",0,0);
getSubReportPage("",0,1);

getStatData();
requestProjectInfo();
//refreshChart(1);  // 暂时拿掉

//setInterval(getStatData,1000*60);

//function changeRptSelect(value){
//    console.log(v_homepageModel.$data.currentRptSelect);
//    v_homepageModel.$data.currentRptSelect=value;
//}

// 综合查询
function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = value;
        console.log("search key=#", key, "#");
        if (key != "") {
            //  执行搜索
            gotoSearchPage('userSearch',key);
        }
    }
}

// 请求项目推荐
function requestProjectInfo(){
    var url=commonUrls.homeProjectUrl;
    v_homepageModel.$data.postProject=[];
    $.ajax({
        url:url,
        type:'post',
        dataType:'json',
        data:{},
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response=res;
                v_homepageModel.$data.postProject=response.projectList;
                v_homepageModel.$nextTick(function(){
                    initPopover();
                });
            }
        }
    });
}

// 请求基本数据，项目推荐
function requestBasicInfo(){
    var url=commonUrls.homeBasicUrl;
    $.ajax({
        url:url,
        type:'post',
        dataType:'json',
        data:{},
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response=res;

                v_userModel.$data.organizeName=response.organizeName;
                v_userModel.$data.newMessageNum=0;
            }
        }
    });
}

// 监控数据--暂时不用 type=1:day  2:week  other:month
function refreshChart(type){
    $.ajax({
        url: commonUrls.homeBasicMonitorUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            type:type
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res.charts;
                var charts=[];
                drawChart(type,charts);
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
}

// 搜索新闻
function getSubNewsPage(key,page,order){
    v_homepageModel.$data.homeNews=[];

    var from=page*commonPageNum.userSearchNews;
    v_homepageModel.$data.newsEnd=true;

    $.ajax({
        url: commonUrls.homeBasicNewsUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            order:order,
            from:from
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success") {
                v_homepageModel.$data.homeNews = res.list;
                if (v_homepageModel.$data.homeNews.length == 0) {
                    v_homepageModel.$data.newsEnd = true;
                } else {
                    v_homepageModel.$data.newsEnd = false;
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
function getSubReportPage(key,page,rtype){
    var from=0;
    var type="";
    if(rtype==0){
        type="all";
        from=0;
    }else if(rtype==1){
        type="trader";
        from=page*commonPageNum.homeReport;
    }else if(rtype==2){
        type="cv";
        from=page*commonPageNum.homeCvReport;
    }
    v_homepageModel.$data.report=[];
    $.ajax({
        url: commonUrls.homeBasicReportUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            type:type
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure: ",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response=res;
                if(rtype==1){ // all 或 研究报告
                    v_homepageModel.$data.report=response.trader_report_list;
                    if(v_homepageModel.$data.report.length==0){
                        v_homepageModel.$data.reportEnd=true;
                    }else{
                        v_homepageModel.$data.reportEnd=false;
                    }
                }
                if(rtype==2){ // all 或 行业分析
                    v_homepageModel.$data.report=response.cv_report_list;
                    if(v_homepageModel.$data.report.length==0){
                        v_homepageModel.$data.reportEnd=true;
                    }else{
                        v_homepageModel.$data.reportEnd=false;
                    }
                    console.log()
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

//// 统计数据
function getStatData(){ // 暂时写死
    var results = {
        "media": {"itemName": "media", "total": 406650, "update": 1105},
        "report": {"itemName": "report", "total": 15874, "update": 345},
        "ent": {"itemName": "ent", "total": 1576206, "update": 373},
        "event": {"itemName": "event", "total": 55872, "update": 130}
    };
    v_homepageModel.$data.statData=results;
}

// 统计数据
function getStatData_ok(){
    $.ajax({
        url: commonUrls.homeBasicStatUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure:",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
            }else if(res.status=='success') {
                var response=res;
                v_homepageModel.$data.statData=response.results;
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
}

/*function getStatData(){
    $.ajax({
        url: commonUrls.homeBasicStatUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure:",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
            }else if(res.status=='success') {
                var response=res;
                v_homepageModel.$data.statData=response.results;
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
}*/

function drawChart(type,data){
    dataset1[0].data=data2;
    dataset1[1].data=data3;

    $.plot($("#monitor-chart-1"), dataset1, options1);

    dataset2[0].data=data2;
    dataset2[1].data=data3;

    $.plot($("#monitor-chart-2"), dataset2, options2);
}

function doFilterSearch(event,type){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = "";

        if(type==1){
            key = v_homepageModel.$data.newsFilterKey;
            console.log("search key=",key);
            v_homepageModel.$data.newsPage=0;
            v_homepageModel.$data.newsOrder=1;
            getSubNewsPage(key,0,1);
        }else if(type==2){
            key = v_homepageModel.$data.reportFilterKey;
            console.log("search key=",key);
            v_homepageModel.$data.reportPage=0;
            //this.cvReportPage=0;
            v_homepageModel.$data.currentRptSelect=1;
            getSubReportPage(key,0,1);
        }
    }
}




