/**
 * Created by a88u on 2016/10/23.
 */
var cid=getUrlQueryStr("id",location.href);
var ctype=getUrlQueryStr("comtype",location.href);
var cisStock=false;
if(ctype==0||ctype==1){
    cisStock=true;
}

var v_companyInfoModel=new Vue({
    el:"#v-companyInfoModel",
    data: {
        info:{},
        isStockCmp:cisStock,
        cId:cid,
        curPageModule:1,
        // basicModule:0, // 1-基本信息；2-关于我们
        stockRightModule:0, // 1-股东；2-投资企业；3-分支机构；4-董监高；5-参控股子公司；6-大股东；7-机构控股；8-关注企业
        // copyRightModule:0, // 1-专利；2-软件著作权
        // lawModule:0, // 1-法务；2-经营异常；3-股权质押；4-
        isInMonitor:checkEntIfInMonitor(cid),

        changeList:[], // 企业变更
        stockHolder:[], // 股东

        finacialData:{
            debtRatio:{},
            rOEAnnualized:{},
            totalRevenue:{},
            turnover:{},
            netProfitMargin:{},
            opt_profit:{}
        },

        analysisModelFirst:"并购标的预测", //
        firstModel:[], //
        analysisModelSecond:"对标新三板公司", //
        secondModel:[],

        investCompany:[], // 投资企业
        branchs:[], // 分支机构
        perRelation:[], // 关联预测
        softCopyrightsList:[],// 软件著作权	ent_soft_copyrights
        patentList:[], // 专利 ent_patent

        entLawList:[], // 法务 ent_law
        abnormalItemList:[], // 经营异常	ent_abnormal_item
        equityList:[],// 股权质押	ent_equity
        mortgagesList:[], //动产抵押	ent_mortgages

        highlevelTitle:"监事会",
        highlevelTeams:[],
        highlevelSelect:1,
        entCtrl:[], // 对外投资，参控股子公司
        stockHolderLatest:[], // 十大股东
        orgholder:[] // 机构控股
    },
    ready: function () {
    },
    methods: {
        addToMonitor:function(content,id){
            if(!v_companyInfoModel.$data.isInMonitor){
                var type=customerSettings.entMonitorType;
                setMonitorData(type,content,id);
                setTimeout(refreshEntMonitor,1000*2);
            }
        },
        changePageModule:function (value) {
            this.$data.curPageModule=value;
            console.log(value);
            this.$nextTick(function () {
                if(value==2){
                    drawFinacialCharts();
                }
            });
        },
        // changeBasic:function (value) {
        //     this.$data.basicModule=value;
        //     console.log(value);
        // },
        changeStockRightModule:function (value) {
            this.$data.stockRightModule=value;
            console.log("stockM:",value);
        },
        // changeCopyRightModule:function (value) {
        //     this.$data.copyRightModule=value;
        //     console.log("copy:",value);
        // },
        // changeLawModule:function (value) {
        //     this.$data.lawModule=value;
        //     console.log("law:",value);
        // },
        goto:function(url,id){
            gotoClickPage(url,id);
        },
        gotoCompanyByItem:function(item){
            console.log(item);
            var params;
            if(item.type==0||item.type==1){ // 上市 新三板
                console.log('type=',item.type,',id=',item.stock_code);
                params=item.stock_code+"&comtype="+item.type;
            }else{ // 未上市
                console.log('type=',item.type,',id=',item.id);
                params=item.id+"&comtype="+item.type
            }
            gotoClickPage('companyBasic',params);
        },
        highlevelChange:function(type){
            if(type==1){
                this.highlevelTitle="监事会";
            }else if(type==2){
                this.highlevelTitle="董事会";
            }else if(type==3){
                this.highlevelTitle="高管";
            }
            $("#team_table").DataTable().destroy();
            this.highlevelSelect=type;
            this.$nextTick(function() {
                bindSimpleDataTable("team_table", commonPageNum.companyTeam);
            });
        },

        gotoCompany:function(stock_code,type){
            console.log(stock_code,type);
            gotoCompanyByCode(stock_code,type);
        },
        gotoCompanyById:function(id){
            gotoCompanybyId(id);
        }
    },
    filters: {
        stateHoldFilter:function(value){
            return value==0? '否':'是';
        },
        highlevelFilter:function(value){
            var result=[];
            for(var i=0;i<value.length;i++){
                var item=value[i];
                if(item.posType==this.highlevelTitle){
                    result.push(item);
                }
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
        checkIfEmptyFilter:function(value){
            var result=true;
            if(value&&value.length>0){
                result=false;
            }
            return result;
        },
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        }
    }
});
getUserMonitorList();
getBasicInfo();

function getBasicInfo(){
    $.ajax({
        url: commonUrls.companyBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:cid
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success"){
                 var response = res;
                console.log(response);
                $("#change_table").DataTable().destroy();
                v_companyInfoModel.$data.info=response.entBasicInfo;
                v_companyInfoModel.$data.changeList=response.entChgRecordInfos;
                v_companyInfoModel.$data.stockHolder=response.entHolderInfos; // 股东
                v_companyInfoModel.$nextTick(function(){
                    bindSimpleDataTable("change_table",commonPageNum.companyChange); // 企业变更
                    bindSimpleDataTable("holder_table",commonPageNum.companyEntHolder); // 股东
                    v_companyInfoModel.$data.basicModule=1;
                    v_companyInfoModel.$data.stockRightModule=1;
                });
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

function refreshEntMonitor(){
    v_companyInfoModel.$data.isInMonitor=checkEntIfInMonitor(cid);
}

// $(document).ready(function () {
//     getUserMonitorList();
// });