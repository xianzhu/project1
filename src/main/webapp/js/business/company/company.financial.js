/**
 * Created by a88u on 2016/10/23.
 */

if(ctype==2){ // 未上市
    console.log("未上市");
}else{
    if (ctype == 0) { // 上市
        v_companyInfoModel.$data.analysisModelFirst = "并购标的预测";
        v_companyInfoModel.$data.analysisModelSecond = "对标新三板公司";
    } else if (ctype == 1) { // 新三板显示
        v_companyInfoModel.$data.analysisModelFirst = "潜在并购方";
        v_companyInfoModel.$data.analysisModelSecond = "对标上市公司";
    }
    getAnnounceInfo();
    getAnalysisInfo();
}

// 投资关系
function getAnnounceInfo(){
    $.ajax({
        url: commonUrls.companyNoticeUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:cid
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
                $("#subinvest_table").DataTable().destroy();
                $("#stockholder_table").DataTable().destroy();
                $("#orgholder_table").DataTable().destroy();
                v_companyInfoModel.$data.entCtrl=response.stockEquityCtrl; // 参控股子公司

                v_companyInfoModel.$data.orgholder=response.stockInstInvest; // 机构控股
                v_companyInfoModel.$data.stockHolderLatest=response.stockHolder; // 大股东
                v_companyInfoModel.$data.highlevelTeams=response.teams; // 董监高

                v_companyInfoModel.$nextTick(function(){
                    bindSimpleDataTable("team_table",commonPageNum.companyTeam); // 董监高
                    bindSimpleDataTable("subinvest_table",commonPageNum.companyEntCtrl); // 参控股子公司
                    bindSimpleDataTable("stockholder_table",commonPageNum.companyStockHolder); // 大股东
                    bindSimpleDataTable("orgholder_table",commonPageNum.companyOrgHolder); // 机构控股
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

// 财务信息
function getAnalysisInfo(){
    $.ajax({
        url: commonUrls.companyDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:cid
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
                console.log("response", res);

                $("#analysis_table_1").DataTable().destroy();
                $("#analysis_table_2").DataTable().destroy();
                v_companyInfoModel.$data.firstModel=res.stockXsbMatchInfo;
                v_companyInfoModel.$data.secondModel=res.stockPotentialMaInfo;
                v_companyInfoModel.$data.finacialData.debtRatio=res.stockCharts.debtRatio;
                v_companyInfoModel.$data.finacialData.rOEAnnualized=res.stockCharts.rOEAnnualized;
                v_companyInfoModel.$data.finacialData.totalRevenue=res.stockCharts.totalRevenue;
                v_companyInfoModel.$data.finacialData.turnover=res.stockCharts.turnover;
                v_companyInfoModel.$data.finacialData.netProfitMargin=res.stockCharts.netProfitMargin;
                v_companyInfoModel.$data.finacialData.opt_profit=res.stockCharts.opt_profit;
                v_companyInfoModel.$nextTick(function(){
                    bindSimpleDataTable("analysis_table_1",commonPageNum.companyAnalysis_1); // 对标预测 - 潜在并购方
                    bindSimpleDataTable("analysis_table_2",commonPageNum.companyAnalysis_2); // 对标预测 - 对标上市公司
                    // drawFinacialCharts();
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

function drawFinacialCharts() {

    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.debtRatio,'analysis-chart-debtRatio');
    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.rOEAnnualized,'analysis-chart-rOEAnnualized');
    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.totalRevenue,'analysis-chart-totalRevenue');
    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.turnover,'analysis-chart-turnover');
    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.netProfitMargin,'analysis-chart-netProfitMargin');
    showEnterpriseEcharts(v_companyInfoModel.$data.finacialData.opt_profit,'analysis-chart-optprofit');
}





