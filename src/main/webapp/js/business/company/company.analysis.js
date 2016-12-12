/**
 * Created by a88u on 2016/10/23.
 */

//var stockCode=getCompanyStockcode();
//console.log(stockCode);

var v_companyAnalysisModel=new Vue({
    el:"#v-companyAnalysisModel",
    data: {
        analysisModelFirst:"并购标的预测", //
        premodelShow:false,
        firstModel:[], //
        analysisModelSecond:"对标新三板公司", //
        secondModel:[],
        otherModel:[]
    },
    ready: function () {
    },
    methods: {
        gotoCompany:function(stock_code,type){
            var params;
            //params=stock_code+"&comtype="+type;
            //gotoClickPage('companyBasic',params);
            gotoCompanyByCode(stock_code,type);
        }
    },
    filters: {
        stateHoldFilter:function(value){
            return value==0? '否':'是';
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

if (v_navModel.$data.comType == 0) { // 上市
    v_companyAnalysisModel.$data.premodelShow = true;
    v_companyAnalysisModel.$data.analysisModelFirst = "并购标的预测";
    v_companyAnalysisModel.$data.analysisModelSecond = "对标新三板公司";

} else if (v_navModel.$data.comType == 1) { // 新三板显示
    v_companyAnalysisModel.$data.premodelShow = true;
    v_companyAnalysisModel.$data.analysisModelFirst = "潜在并购方";
    v_companyAnalysisModel.$data.analysisModelSecond = "对标上市公司";
}
getAnalysisInfo();

function getAnalysisInfo(){
    $.ajax({
        url: commonUrls.companyDataUrl,              //请求地址
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
                console.log("response", res);

                $("#analysis_table_1").DataTable().destroy();
                $("#analysis_table_2").DataTable().destroy();
                v_companyAnalysisModel.$data.firstModel=res.stockXsbMatchInfo;
                v_companyAnalysisModel.$data.secondModel=res.stockPotentialMaInfo;
                v_companyAnalysisModel.$nextTick(function(){
                    bindSimpleDataTable("analysis_table_1",10);
                    bindSimpleDataTable("analysis_table_2",10);
                })

                var resData=res.stockCharts;
                showEnterpriseEcharts(resData.debtRatio,'analysis-chart-debtRatio');
                showEnterpriseEcharts(resData.rOEAnnualized,'analysis-chart-rOEAnnualized');
                showEnterpriseEcharts(resData.totalRevenue,'analysis-chart-totalRevenue');
                showEnterpriseEcharts(resData.turnover,'analysis-chart-turnover');
                showEnterpriseEcharts(resData.netProfitMargin,'analysis-chart-netProfitMargin');
                showEnterpriseEcharts(resData.opt_profit,'analysis-chart-optprofit');
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




