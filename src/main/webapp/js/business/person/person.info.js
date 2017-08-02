/**
 * Created by a88u on 2016/10/11.
 */
var pid=getUrlQueryStr("id",location.href);
var v_personBasicModel=new Vue({
    el:"#v-personBasicModel",
    data: {
        pName:"",
        organizeId:"",
        orgName:"",
        description:"",
        hasLongDesc:false, // 简介是否显示More
        focuse:"",
        focuseList:[],
        elasticList:[],
        isEnd:false,
        elasticOrder:2,
        elasticPage:0,
        elasticEmpty:true,
        elasticFilterKey:"",

        ivsTypeSelections:investTypeSelections,
        ivsCapitalList:[],
        ivsEnd:true,
        ivsPage:0,
        ivsSubType:"",

        extTypeSelections:exitTypeSelections,
        extCapitalList:[],
        extEnd:true,
        extPage:0,
        extSubType:""
    },
    ready: function () {
    },
    methods: {
        openElastic:function(url){
            sendMonitor({url:url});
        },
        changeOrder:function(){
            this.elasticPage=0;
            getSubElasticPage(this.elasticFilterKey);
        },
        elasticPageControlFilter:function(value){
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
        gotoOrgPage:function(id){
            gotoOrgPage(id);
        },
        pageControlFilter:function(type,value){
            var page=0;
            var end=false;
            if(type==1){
                page=this.ivsPage;
                end=this.ivsEnd;
            }else if(type==2){
                page=this.extPage;
                end=this.extEnd;
            }
            if(value==0){
                return page!=0;
            }else{
                return !end;
            }
        },
        changeDataPage:function(type,value){
            if(type==1){
                if(value==0){
                    this.ivsPage--;
                }else{
                    this.ivsPage++;
                }
                console.log("invest",this.ivsSubType);
                getSubDataPage(type,ivsFilterKey,this.ivsPage,this.ivsSubType);
            }else if(type==2){
                if(value==0){
                    this.extPage--;
                }else{
                    this.extPage++;
                }
                getSubDataPage(type,extFilterKey,this.extPage,this.extSubType);
            }
        },
        showCapDetail:function(id,type){
            var etype="invest";
            if(type==2){
                etype="exit";
            }
            $.ajax({
                url: commonUrls.personDetailUrl,              //请求地址
                type: "get",                            //请求方式
                data: { //请求参数
                    id:id,
                    type:etype
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
                        var response = res;
                        console.log("response", response);
                        //v_personBasicModel.$data.eventDetail=response.event;

                        showCapitalDetail(type,response);
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
        },
        showDescription:function (desc) {
            showDescDetail(desc);
        }
    },
    filters: {
        currentElasticPageFilter:function(value){
            var p=this.elasticPage+1;
            return "第"+p+"页";
        },
        formatFilter:function(value){
            var result="-";
            if(value&&value!=""&&value!="NULL"){
                result=value;
            }
            return result;
        },
        getShortDescFilter:function(value){
            var result=value;
            if(value&&value.length>100){
                result=value.substring(0,100)+" ...";
            }
            return result;
        },
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        ivsTypeSelectFilter:function(value){
            return "ivsTypeSelect_"+value;
        },
        extTypeSelectFilter:function(value){
            return "extTypeSelect_"+value;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        }
    }
});

getBasicInfo();
$(window).ready(function () {
    resizeDetailMask();
});
$(window).resize(function () {
    resizeDetailMask();
});
function resizeDetailMask() {
    var height = $(window).height();
    var mheight = height;
    console.log("modal-event-body");
    $(".modal-event-body").css('maxHeight', mheight - 181);
}

// 情报搜索
function doElasticFilter(event){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key=$("#elasticKey").val();
        v_personBasicModel.$data.elasticFilterKey=key;
        console.log("search key=",key);
        v_personBasicModel.$data.elasticPage=0;
        getSubElasticPage(key);
    }
}

// 基本信息
function getBasicInfo(){
    $.ajax({
        url: commonUrls.personBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:pid
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
                v_personBasicModel.$data.pName=res.investor.userName;

                v_personBasicModel.$data.organizeId=res.investor.organizeId;
                v_personBasicModel.$data.orgName=res.investor.organizeName;
                v_personBasicModel.$data.title=res.investor.title;

                var desc=res.investor.description;
                v_personBasicModel.$data.description=desc;
                v_personBasicModel.$data.focuse=res.investor.focusDomain;

                if(desc&&desc.length>100){
                    v_personBasicModel.$data.hasLongDesc=true;
                }else{
                    v_personBasicModel.$data.hasLongDesc=false;
                }
                var domains=[];
                if(res.investor.focusDomain&&res.investor.focusDomain.length>0&&res.investor.focusDomain!="NULL"&&res.investor.focusDomain!="null"){
                    var fdomains=res.investor.focusDomain.split(" ");
                    for(var i=0;i<fdomains.length;i++){
                        console.log(fdomains[i]);
                        var rcolor=Math.floor(Math.random()*10),rsize=Math.floor(Math.random()*3)*2+12;
                        var rclass='focuseDomain-color-'+rcolor+' focuseDomain-size-'+rsize;
                        domains.push({text:fdomains[i],classname:rclass});
                    }
                }
                v_personBasicModel.$data.focuseList=domains;

                $("#elasticKey").val(res.investor.userName);
                v_personBasicModel.$data.elasticFilterKey=res.investor.userName;
                if(res.investor&&res.investor.userName!=""){
                    getSubElasticPage(res.investor.userName);
                }

                updateInvestTable(res.inventList);
                updateExitTable(res.exitList);
            }
        },
        fail: function (status) {
            console.error("event id=", pid, " error. status=", status);
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

// 情报
function getSubElasticPage(key){
    console.log(key);
    $.ajax({
        url: commonUrls.personIntelgeUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:v_personBasicModel.$data.elasticPage*commonPageNum.personNews,
            count:commonPageNum.personNews,
            order:v_personBasicModel.$data.elasticOrder
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response = res;

                v_personBasicModel.$data.elasticList=response.list;

                if(response.list){
                    if(response.list.length==0){
                        v_personBasicModel.$data.elasticEmpty=true;
                    }else{
                        v_personBasicModel.$data.elasticEmpty=false;
                    }
                }else{
                    v_personBasicModel.$data.elasticEmpty=true;
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




