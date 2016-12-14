/**
 * Created by a88u on 2016/10/17.
 */
var v_userSearchModel=new Vue({
    el:"#v-userSearchModel",
    data:{
        key:"",
        news:[],
        newsPage:0,
        newsEnd:true,

        cvReport:[],
        cvPage:0,
        cvEnd:true,

        traderReport:[],
        traderPage:0,
        traderEnd:true,

        basicSearch:{
            personInfos:[],
            organizeInfos:[]
        },
        companyInfos:[],
        isBasicEmpty:false,

        extendSearch:[],
        extendEmpty:false
    },
    ready:function(){
    },
    methods: {
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        //goto: function (url, id) {
        //    savecurrentSearchPage("key",this.key);
        //    gotoClickPage(url, id);
        //},
        gotoCompany:function(id,type,stockType){
            console.log("company: ", id,', ',type,', ',stockType);
            //stock_type 0是A股 1是新三板
            if((typeof id!= undefined)&&id!=""&&id!=null){
                savecurrentSearchPage("key",this.key);
                if(type==1){
                    if(stockType==0){//A股
                        gotoCompanyPage("companyBasic",id,0);
                    }else{//三板
                        gotoCompanyPage("companyBasic",id,1);
                    }

                }else{//未上市公司
                    gotoCompanyPage("companyBasic",id,2);
                }
            }else{
                console.log("undef:",typeof id!= "undefined",', empty:',id!="");
            }
        },
        gotoPage:function(url,id){
            savecurrentSearchPage("key",this.key);
            gotoClickPage(url,id);
        },
        openNews: function (url) {
            sendMonitor({url: url});
        },
        onTagClick:function(item){
          if(item.type==0){ // 人
              savecurrentSearchPage("key",this.key);
              var param=item.id+"&from="+item.from;
              gotoClickPage("personalBasic",param);
          }else{ // 机构
              savecurrentSearchPage("key",this.key);
              gotoClickPage("orgBasic",item.id);
          }
        },
        pageControlFilter:function(value,type){
            var page=0;
            var isEnd=true;
            if(type==1){ // 新闻
                page=this.newsPage;
                isEnd=this.newsEnd;
            }else if(type==2){ // 研究报告
                page=this.traderPage;
                isEnd=this.traderEnd;
            }else if(type==3){ // 行业分析
                page=this.cvPage;
                isEnd=this.cvEnd;
            }
            if(value==0){
                return page!=0;
            }else{
                return !isEnd;
            }
        },
        changePage:function(value,type){
            if(type==1){
                if(value==0){
                    this.newsPage--;
                }else{
                    this.newsPage++;
                }
                getSubNewsPage(this.key,this.newsPage);
            }else if(type==2){
                if(value==0){
                    this.traderPage--;
                }else{
                    this.traderPage++;
                }
                getSubReportPage(this.key,this.traderPage,1);
            }else if(type==3){
                if(value==0){
                    this.cvPage--;
                }else{
                    this.cvPage++;
                }
                getSubReportPage(this.key,this.cvPage,2);
            }
        }
    },
    filters:{
        currentPageFilter:function(value){
            var page=value+1;
            return "第"+page+"页";
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        checkBasicNotEmptyFilter:function(value){
            var result= !value;
            console.log(result);
            return result;
        }
    }
});

//console.log(window.location.search);
var key=getUrlQueryStr("key",location.href);
//console.log("key=",key);

if(typeof key== "undefined") { // 若url中不存在查询关键字，即链接由导航栏直接跳转来
    key="";
}
if(key!=""){
    $("#top-search").val(key);
    doSearch(key);
}

function getReasultInfo(key){
console.log("get ",key);
    $.ajax({
        url: commonUrls.userSearchUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key
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
                console.log("send ajax success");
                var response = res;

                $("#result_table").DataTable().destroy();
                v_userSearchModel.$data.basicSearch.personInfos=response.userBasicList;
                v_userSearchModel.$data.basicSearch.organizeInfos=response.orgBasicList;
                //console.log(response.userBasicList.length,', ',response.orgBasicList.length);
//console.log(response.userBasicList.length,', ',response.orgBasicList.length);
                if(v_userSearchModel.$data.basicSearch.personInfos.length==0&&v_userSearchModel.$data.basicSearch.organizeInfos.length==0){
                    v_userSearchModel.$data.isBasicEmpty=true;
                }else{
                    v_userSearchModel.$data.isBasicEmpty=false;
                }

                stopTabCloudAnimite();
                var tagCloudList=[];
                for(var i=0;i<response.userExtendList.length;i++){
                    var item=response.userExtendList[i];
                    tagCloudList.push({id:item.userId,name:item.userName,type:0,desc:item.organizeName});
                }
                //console.log(tagCloudList.length);
                for(var i=0;i<response.orgExtendList.length;i++){
                    var item=response.orgExtendList[i];
                    tagCloudList.push({id:item.orgId,name:item.orgCnName,type:1,desc:item.orgMapEntity});
                }
                //console.log(tagCloudList.length);
                v_userSearchModel.$data.extendSearch=tagCloudList;
                if(response.userExtendList.length==0&&response.orgExtendList.length==0){
                    v_userSearchModel.$data.extendEmpty=true;
                }else{
                    v_userSearchModel.$data.extendEmpty=false;
                }
                v_userSearchModel.$nextTick(function(){ // 重新绑定标签云
                    setTabCloud("extend_tag_cloud");
                    initPopover();
                    bindSimpleDataTable("result_table",10);
                });
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

function getCompanyResultInfo(key){
    $.ajax({
        url: commonUrls.companySearchUrl,              //请求地址
        type: "POST",                       //请求方式
        data: {
            key:key
        },        //请求参数
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success"){
                var response = res;
                $("#company_table").DataTable().destroy();
                v_userSearchModel.$data.companyInfos = response.searchResult; // 这里不能用this
                v_userSearchModel.$nextTick(function(){
                    bindExportedDataTable("company_table",10,"企业查询结果",{});
                });
            }
        },
        fail: function (status) {
            console.error(" error. status=", status);
        },
        statusCode: {
            404: function () {
                //window.location.href="notFound.html";
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

// 搜索条回车开始搜索
function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        //var curKey=window.location.search;
        //
        //var newstr=replaceUrlQueryStr("key",curKey,value);
        //window.location.search=newstr;
        var key = value;
        if(key!="") {
            console.log("doSearch:", value);
            doSearch(key);
        }
    }
}

function doSearch(key){
    v_userSearchModel.$data.key = key;
    v_userSearchModel.$data.newsPage = 0;
    v_userSearchModel.$data.cvPage = 0;
    v_userSearchModel.$data.traderPage = 0;

    getReasultInfo(key);
    getCompanyResultInfo(key);
    getSubNewsPage(key, 0);

    getSubReportPage(key, 0, 0);
}

function getSubNewsPage(key,page){
    var from=page*commonPageNum.userSearchNews;

    $.ajax({
        url: commonUrls.userSearchNewsUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from
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
                //console.log("send ajax success");
                var response = res;

                v_userSearchModel.$data.news=response.list;
                if(v_userSearchModel.$data.news.length==0){
                    v_userSearchModel.$data.newsEnd=true;
                }else{
                    v_userSearchModel.$data.newsEnd=false;
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

// 报告查询
function getSubReportPage(key,page,rtype){
    var from=0;
    var type="";

    if(rtype==0){
        type="all";
        from=0;
    }else if(rtype==1){
        type="trader";
        from=page*commonPageNum.userSearchTrader;
    }else{
        type="cv";
        from=page*commonPageNum.userSearchCv;
    }

    $.ajax({
        url: commonUrls.userSearchReportUrl,              //请求地址
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
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response = res;

                if(rtype!=1){
                    v_userSearchModel.$data.cvReport=response.cv_report_list;
                    if(v_userSearchModel.$data.cvReport.length==0){
                        v_userSearchModel.$data.cvEnd=true;
                    }else{
                        v_userSearchModel.$data.cvEnd=false;
                    }
                }
                if(rtype!=2){
                    v_userSearchModel.$data.traderReport=response.trader_report_list;
                    if(v_userSearchModel.$data.traderReport.length==0){
                        v_userSearchModel.$data.traderEnd=true;
                    }else{
                        v_userSearchModel.$data.traderEnd=false;
                    }
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

























