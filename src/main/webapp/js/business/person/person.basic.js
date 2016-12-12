/**
 * Created by a88u on 2016/10/11.
 */

var v_personBasicModel=new Vue({
    el:"#v-personBasicModel",
    data: {
        pName:"",
        organizeId:"",
        orgName:"",
        description:"",
        focuse:"",
        investCase:"",
        reportList:[],
        isEnd:false,
        reportPage:0,
        reportFilterKey:"",

        ivsTypeSelections:investTypeSelections,
        ivsCapitalList:[],
        ivsEnd:false,
        ivsPage:0,
        ivsSubType:"",

        extTypeSelections:exitTypeSelections,
        extCapitalList:[],
        extEnd:false,
        extPage:0,
        extSubType:""
    },
    ready: function () {
    },
    methods: {
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        reportPageControlFilter:function(value){
            //console.log(value,", ",this.reportPage,",",this.isEnd);
            if(value==0){
                return this.reportPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        changeReportPage:function(value){
            if(value==0){
                this.reportPage--;
                getSubReportPage(this.reportFilterKey);
            }else{
                this.reportPage++;
                getSubReportPage(this.reportFilterKey);
            }
        },
        gotoClickPage:function(url,id){
            gotoClickPage(url,id);
        },
        pageControlFilter:function(type,value){
            //console.log(value,", ",this.newsPage,",",this.isEnd);
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
        }
    },
    filters: {
        currentReportPageFilter:function(value){
            var p=this.reportPage+1;
            return "第"+p+"页";
        },
        isReportEmpty:function(value){
            console.log(this.isEnd);
            return this.isEnd;
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

// 情报搜索
function doReportFilter(event){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key=$("#reportKey").val();
        v_personBasicModel.$data.reportFilterKey=key;
        console.log("search key=",key);
        v_personBasicModel.$data.reportPage=0;
        getSubReportPage(key);
    }
}

// 基本信息
function getBasicInfo(){
    //var pId = getUrlQueryStr("id", location.href);
    $.ajax({
        url: commonUrls.personBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:v_navModel.$data.id
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                v_personBasicModel.$data.pName=res.investor.userName;

                v_personBasicModel.$data.organizeId=res.investor.organizeId;
                v_personBasicModel.$data.orgName=res.investor.organizeName;
                v_personBasicModel.$data.title=res.investor.title;

                v_personBasicModel.$data.description=res.investor.description;
                v_personBasicModel.$data.focuse=res.investor.focusDomain;
                v_personBasicModel.$data.investCase=res.investor.investProjects;

                //console.log(res.investor.userName);
                $("#reportKey").val(res.investor.userName);
                v_personBasicModel.$data.reportFilterKey=res.investor.userName;
                if(res.investor&&res.investor.userName!=""){
                    getSubReportPage(res.investor.userName);
                }

                updateInvestTable(res.inventList);
                updateExitTable(res.exitList);
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

// 情报
function getSubReportPage(key){
    console.log(key);
    $.ajax({
        url: commonUrls.personIntelgeUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:v_personBasicModel.$data.reportPage*10,
            count:10
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response = res;

                v_personBasicModel.$data.reportList=response.list;

                if(response.list&&response.list.length==0){
                    v_personBasicModel.$data.isEnd=true;
                }else{
                    v_personBasicModel.$data.isEnd=false;
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
            gotoSearchPage('person',key);
        }
    }
}



