/**
 * Created by a88u on 2016/10/9.
 */
var orgId=getUrlQueryStr("id",location.href);


var v_orgBasicModel=new Vue({
    el:"#v-orgBasicModel",
    data:{
        orgName:"",
        entName:"",
        entUuid:"",
        orgDescription:"",
        orgTeam:[],
        orgTeamEmpty:true,
        familyOrg:[],
        focusCompany:[],
        orgNews:[],
        importNews:[],
        importNewsCount:0,
        isEnd:false,
        newsPage:0,
        newsFilterKey:""
    },
    ready:function(){

    },
    methods:{
        gotoCompany:function(id){
            gotoCompanybyId(id);
        },
        goto:function(url,id){
            gotoClickPage(url,id);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        pageControlFilter:function(value){
            //console.log(value,", ",this.newsPage,",",this.isEnd);
            if(value==0){
                return this.newsPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        changeNewsPage:function(value){
            if(value==0){
                this.newsPage--;
                getSubNewsPage(this.newsFilterKey);
            }else{
                this.newsPage++;
                getSubNewsPage(this.newsFilterKey);
            }
        }

    },
    filters:{
        currentNewsPageFilter:function(value){
            var p=this.newsPage+1;
            return "第"+p+"页";
        },
        isNewsEmpty:function(value){
            console.log(this.isEnd);
            return this.isEnd;
        },
        checkNewsFilter:function(value){
           var res= value!=0;
            console.log(res);
            return res;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        formatEmptyFilter:function(value){
            var result="--";
            if(value&&value!=""){
                result=value;
            }
            return result;
        }
    }
});

getBasicInfo();

function doNewsFilter(event){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = v_orgBasicModel.$data.newsFilterKey;
        console.log("search key=",key);
        v_orgBasicModel.$data.newsPage=0;
        getSubNewsPage(key);
    }
}

function getBasicInfo(){
    $.ajax({
        url: commonUrls.orgnazationBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:orgId
        },
        dataType: "json",
        success: function (res) {
            console.log(res);
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response = res;
                //$("#family_table").DataTable().destroy();
                $("#focus_table").DataTable().destroy();
                $("team_table").DataTable().destroy();
                v_orgBasicModel.$data.orgName=response.orgInfo.orgCnName;
                v_orgBasicModel.$data.orgDescription=response.orgInfo.orgCnDesc;

                v_orgBasicModel.$data.entName=response.orgInfo.orgMapEntity;
                v_orgBasicModel.$data.entUuid=response.orgInfo.orgMapEntityId;

                //v_orgBasicModel.$data.familyOrg=response.extendList;
                v_orgBasicModel.$data.focusCompany=response.focusList;

                //stopTabCloudAnimite();
                v_orgBasicModel.$data.orgTeam=response.teams;
                if(v_orgBasicModel.$data.orgTeam.length==0){
                    v_orgBasicModel.$data.orgTeamEmpty=true;
                }else{
                    v_orgBasicModel.$data.orgTeamEmpty=false;
                }

                //v_orgBasicModel.ready=function(){bindTable()};
                v_orgBasicModel.$nextTick(function(){
                    //bindExportedDataTable("team_table",10,"机构团队信息");
                    //bindSimpleDataTable("family_table",8);
                    bindSimpleDataTable("focus_table",10);
                    //setTabCloud("extend_tag_cloud");
                    bindSimpleDataTable("team_table",12);
                    initPopover();
                });
                v_orgBasicModel.$data.newsFilterKey=response.orgInfo.orgCnName;
                getSubNewsPage(response.orgInfo.orgCnName);
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

function getSubNewsPage(key){
    //var turl=commonUrls.orgnazationBasicUrl;
    
    $.ajax({
        url: commonUrls.orgBasicNewsUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:v_orgBasicModel.$data.newsPage*10
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

                v_orgBasicModel.$data.orgNews=res.list;
                if(res.list.length==0){
                    v_orgBasicModel.$data.isEnd=true;
                }else{
                    v_orgBasicModel.$data.isEnd=false;
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

function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = value;
        console.log("search key=#", key, "#");
        if (key != "") {
            //  执行搜索
            gotoSearchPage('orgnazation',key);
        }
    }
}

// 点击新闻提示铃铛，显示最新推送并购信息---暂时不用
function getImportNews(){
    var test=v_orgBasicModel.$data.importNewsCount;
    var burl=commonUrls.orgnazationBasicUrl;

    $.ajax({
        url:burl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:orgId
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
                var response = res.response;
                v_orgBasicModel.$data.importNews = response.importNews;
                v_orgBasicModel.$data.importNewsCount=v_orgBasicModel.$data.importNews.length;
                //console.log(v_orgBasicModel.$data.importNewsCount);
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

