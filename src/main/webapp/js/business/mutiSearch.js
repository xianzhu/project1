/**
 * Created by a88u on 2016/10/17.
 */
var key=getUrlQueryStr("key",location.href);

var v_mutiSearchModel=new Vue({
    el:"#v-mutiSearchModel",
    data:{
        key:"",
        news:[],
        newsOrder:2,
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
        extendEmpty:true
    },
    ready:function(){
    },
    methods: {
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        gotoCompany:function(id,type,stockType){
            console.log("company: ", id,', ',type,', ',stockType);
            var stockCode=""; // 综合查询返回的是未上市企业信息
            //stock_type 0是A股 1是新三板
            if((typeof id!= undefined)&&id!=""&&id!=null){
                if(type==1){
                    if(stockType==0){//A股
                        gotoCompanyPage(id,0,stockCode);
                    }else{//三板
                        gotoCompanyPage(id,1,stockCode);
                    }

                }else{//未上市公司
                    gotoCompanyPage(id,2,"");
                }
            }else{
                console.log("undef:",typeof id!= "undefined",', empty:',id!="");
            }
        },
        gotoPersonPage:function(id){
            gotoPersonPage(id);
        },
        gotoOrgPage:function(id){
            gotoOrgPage(id);
        },
        openNews: function (url) {
            sendMonitor({url: url});
        },
        onTagClick:function(item){
            // console.log(item);
          if(item.type==0){ // 人
              gotoPersonPage(item.id);
          }else{ // 机构
              gotoOrgPage(item.id);
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
        changeOrder:function(value){
            this.newsOrder=value;
            this.newsPage=0;
            if(this.key!="") {
                getSubNewsPage(this.key, this.newsPage);
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

if(key!=""){
    doSearch(key);
}else{
    console.log("key is empty");
}
// 机构、企业、内容推荐
function getReasultInfo(key){
console.log("get ",key);
    $.ajax({
        url: commonUrls.mutiSearchUrl,              //请求地址
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
                v_mutiSearchModel.$data.basicSearch.personInfos=response.userBasicList;
                v_mutiSearchModel.$data.basicSearch.organizeInfos=response.orgBasicList;
                //console.log(response.userBasicList.length,', ',response.orgBasicList.length);
//console.log(response.userBasicList.length,', ',response.orgBasicList.length);
                if(v_mutiSearchModel.$data.basicSearch.personInfos.length==0&&v_mutiSearchModel.$data.basicSearch.organizeInfos.length==0){
                    v_mutiSearchModel.$data.isBasicEmpty=true;
                }else{
                    v_mutiSearchModel.$data.isBasicEmpty=false;
                }

                stopTabCloudAnimite();
                var tagCloudList=[];
                for(var i=0;i<response.userExtendList.length;i++){
                    var item=response.userExtendList[i];
                    tagCloudList.push({id:item.userId,name:item.userName,type:0,desc:item.organizeName});
                }
                //console.log(tagCloudList.length);
                // for(var i=0;i<response.orgExtendList.length;i++){
                for(var i=0;i<response.orgExtendList.length;i++){
                    var item=response.orgExtendList[i];
                    tagCloudList.push({id:item.orgId,name:item.orgCnName,type:1,desc:item.orgMapEntity});
                }
                //console.log(tagCloudList.length);
                v_mutiSearchModel.$data.extendSearch=tagCloudList;
                if(response.userExtendList.length==0&&response.orgExtendList.length==0){
                    v_mutiSearchModel.$data.extendEmpty=true;
                }else{
                    v_mutiSearchModel.$data.extendEmpty=false;
                }
                v_mutiSearchModel.$nextTick(function(){ // 重新绑定标签云
                    setTabCloud("extend_tag_cloud");
                    initPopover();
                    bindSimpleDataTable("result_table",commonPageNum.mutisearchOrg);
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
                v_mutiSearchModel.$data.companyInfos = response.searchResult; // 这里不能用this
                v_mutiSearchModel.$nextTick(function(){
                    // bindExportedDataTable("company_table",commonPageNum.mutisearchCompany,"企业查询结果",{});
                    bindSimpleDataTable("company_table",commonPageNum.mutisearchCompany);
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

function doSearch(key){
    v_mutiSearchModel.$data.key = key;
    v_mutiSearchModel.$data.newsOrder=2;
    v_mutiSearchModel.$data.newsPage = 0;
    v_mutiSearchModel.$data.cvPage = 0;
    v_mutiSearchModel.$data.traderPage = 0;

    getReasultInfo(key);
    getCompanyResultInfo(key);
    getSubNewsPage(key, 0);

    getSubReportPage(key, 0, 0);
}

function getSubNewsPage(key,page){
    var from=page*commonPageNum.mutiSearchNews;
    var order=v_mutiSearchModel.$data.newsOrder;

    $.ajax({
        url: commonUrls.mutiSearchNewsUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            count:commonPageNum.mutiSearchNews,
            order:order
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

                v_mutiSearchModel.$data.news=response.list;
                if(v_mutiSearchModel.$data.news.length==0){
                    v_mutiSearchModel.$data.newsEnd=true;
                }else{
                    v_mutiSearchModel.$data.newsEnd=false;
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
    var count=10;
    var type="";

    if(rtype==0){
        type="all";
        from=0;
        count=commonPageNum.mutisearchTrader;
    }else if(rtype==1){
        type="trader";
        from=page*commonPageNum.mutisearchTrader;
        count=commonPageNum.mutisearchTrader;
    }else{
        type="cv";
        from=page*commonPageNum.mutisearchCv;
        count=commonPageNum.mutisearchCv;
    }

    $.ajax({
        url: commonUrls.mutiSearchReportUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            count:count,
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
                    v_mutiSearchModel.$data.cvReport=response.cv_report_list;
                    if(v_mutiSearchModel.$data.cvReport.length==0){
                        v_mutiSearchModel.$data.cvEnd=true;
                    }else{
                        v_mutiSearchModel.$data.cvEnd=false;
                    }
                }
                if(rtype!=2){
                    v_mutiSearchModel.$data.traderReport=response.trader_report_list;
                    if(v_mutiSearchModel.$data.traderReport.length==0){
                        v_mutiSearchModel.$data.traderEnd=true;
                    }else{
                        v_mutiSearchModel.$data.traderEnd=false;
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

























