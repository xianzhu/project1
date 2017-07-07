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
        fundList:[],
        focusCompany:[],
        orgElasticList:[],
        elasticEmpty:true,
        isEnd:false,
        elasticOrder:2,
        elasticPage:0,
        elasticFilterKey:"",
        orgId:orgId,
        isInMonitor:!checkOrgIfInMonitor(orgId)
    },
    ready:function(){

    },
    methods:{
        gotoCompany:function(id){
            gotoCompanybyId(id);
        },
        gotoPerson:function(id){
            gotoPersonPage(id);
        },
        openElastic:function(url){
            sendMonitor({url:url});
        },
        changeOrder:function(value){
            this.elasticOrder=value;
            this.elasticPage=0;
            getSubElasticPage(this.elasticFilterKey);
        },
        gotoOrgFund:function (element,event) {
            console.log("fund:",element.fundId,"org:",orgId);
            gotoOrgFundPage(element.fundId,orgId);
        },
        pageControlFilter:function(value){
            if(value==0){
                return this.elasticPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        changeElasticPage:function(value){
            if(value==0){
                this.elasticPage--;
                getSubElasticPage(this.elasticFilterKey);
            }else{
                this.elasticPage++;
                getSubElasticPage(this.elasticFilterKey);
            }
        },
        addToMonitor:function(content,id){
            var type=customerSettings.orgMonitorType;
            setMonitorData(type,content,id);
            setTimeout(refreshOrgMonitor,1000*2);
        }
    },
    filters:{
        currentElasticPageFilter:function(value){
            var p=this.elasticPage+1;
            return "第"+p+"页";
        },
        getShortStrFilter:function(value){
            var result=value;

            if(value){
                if(value.toLowerCase()=="null"){
                    result="";
                }else if(value.length>50) {
                    result = value.substr(0, 50) + "...";
                }
            }else{
                result="";
            }
            return result;
        },
        formatFilter:function(value){
            var result="-";
            if(value&&value!=""&&value!="NULL"){
                result=value;
            }
            return result;
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
getFundListInfo();

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

                v_orgBasicModel.$data.focusCompany=response.focusList;

                //stopTabCloudAnimite();
                v_orgBasicModel.$data.orgTeam=response.teams;
                if(v_orgBasicModel.$data.orgTeam.length==0){
                    v_orgBasicModel.$data.orgTeamEmpty=true;
                }else{
                    v_orgBasicModel.$data.orgTeamEmpty=false;
                }

                v_orgBasicModel.$nextTick(function(){
                    bindSimpleDataTable("focus_table",commonPageNum.orgFocuse);
                    //setTabCloud("extend_tag_cloud");
                    bindSimpleDataTable("team_table",commonPageNum.orgTeam);
                    initPopover();
                });
                v_orgBasicModel.$data.elasticFilterKey=response.orgInfo.orgCnShort;
                getSubElasticPage(response.orgInfo.orgCnShort);
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

function getSubElasticPage(key){
    //var turl=commonUrls.orgnazationBasicUrl;
    var from=v_orgBasicModel.$data.elasticPage*commonPageNum.orgElastic;
    var order=v_orgBasicModel.$data.elasticOrder;
    $.ajax({
        url: commonUrls.orgBasicElasticUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            count:commonPageNum.orgElastic,
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
                v_orgBasicModel.$data.orgElasticList=res.list;
                if(res.list){
                    if(res.list.length==0){
                        v_orgBasicModel.$data.elasticEmpty=true;
                    }else{
                        v_orgBasicModel.$data.elasticEmpty=false;
                    }
                }else{
                    v_orgBasicModel.$data.elasticEmpty=true;
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

function doOrgElasticFilter(event){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = v_orgBasicModel.$data.elasticFilterKey;
        console.log("search key=#", key, "#");
        if (key != "") {
            //  执行搜索
            //gotoSearchPage('orgnazation',key);
            v_orgBasicModel.$data.elasticPage=0;
            v_orgBasicModel.$data.elasticOrder=2;
            getSubElasticPage(key);
        }
    }
}

function getFundListInfo() {
    $.ajax({
        url: commonUrls.orgnazationFundUrl,              //请求地址
        type: "get",                            //请求方式
        data: { //请求参数
            id:orgId
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                console.log("response", response);
                $('#fund_table').DataTable().destroy();
                v_orgBasicModel.$data.fundList = response.fundList;

                v_orgBasicModel.$nextTick(function () {
                    bindExportedDataTable("fund_table", commonPageNum.orgFund, "机构基金信息",{searching:true,dom:'<"html5buttons"B>ftp'});
                });
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function () {
                goTo404();
            },
            500: function () {
                goTo500();
            }
        }
    });
}

function refreshOrgMonitor(){
    v_orgBasicModel.$data.isInMonitor=!checkOrgIfInMonitor(orgId);
}

$(document).ready(function () {
    getUserMonitorList();
});