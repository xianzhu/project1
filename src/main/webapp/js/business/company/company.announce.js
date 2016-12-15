/**
 * Created by a88u on 2016/10/23.
 */
//var stockCode=getCompanyStockcode();
//console.log(stockCode);

var v_companyAnnounceModel=new Vue({
    el:"#v-companyAnnounceModel",
    data: {
        highlevelTitle:"监事会",
        highlevelTeams:[],
        highlevelSelect:1,
        entCtrl:[], // 对外投资，参控股子公司
        stockHolder:[], // 大股东
        orgholder:[] // 机构控股
    },
    ready: function () {
    },
    methods: {
        goto:function(url,id){
            gotoClickPage(url,id);
        },
        gotoCompany:function(item){
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
                bindSimpleDataTable("team_table", 10);
            });
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
        }
    }
});

getAnnounceInfo();

//getHighlevelTeams(1);
//drawChart();

// 投资关系
function getAnnounceInfo(){
    $.ajax({
        url: commonUrls.companyNoticeUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:v_navModel.$data.id
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
                //console.log("response", response);

                $("#team_table").DataTable().destroy();
                $("#invest_table").DataTable().destroy();
                $("#stockholder_table").DataTable().destroy();
                $("#orgholder_table").DataTable().destroy();
                v_companyAnnounceModel.$data.entCtrl=response.stockEquityCtrl; // 参控股子公司

                v_companyAnnounceModel.$data.orgholder=response.stockInstInvest; // 机构控股
                v_companyAnnounceModel.$data.stockHolder=response.stockHolder; // 大股东
                v_companyAnnounceModel.$data.highlevelTeams=response.teams;

                v_companyAnnounceModel.$nextTick(function(){
                    bindSimpleDataTable("team_table",10);
                    bindSimpleDataTable("invest_table",10);
                    bindSimpleDataTable("stockholder_table",10);
                    console.log($("#stockholder_table").DataTable());
                    bindSimpleDataTable("orgholder_table",10);
                    initPopover();
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





