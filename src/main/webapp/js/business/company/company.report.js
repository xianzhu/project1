/**
 * Created by a88u on 2016/10/26.
 */
var v_companyReportModel=new Vue({
    el:"#v-companyReportModel",
    data: {
        entLawList:[], // 法务 ent_law
        abnormalItemList:[], // 经营异常	ent_abnormal_item
        equityList:[],// 股权质押	ent_equity
        mortgagesList:[] //动产抵押	ent_mortgages
    },
    ready: function () {
    },
    methods: {
    },
    filters: {
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
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

getReportInfo();

function getReportInfo(){
    $.ajax({
        url: commonUrls.companyCreditUrl,
        type: "POST",
        data: {
            id:v_navModel.$data.id
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
                $("#law_table").DataTable().destroy();
                $("#abnormal_table").DataTable().destroy();
                $("#equity_table").DataTable().destroy();
                $("#mortgages_table").DataTable().destroy();
                v_companyReportModel.$data.entLawList=response.entLawInfos;
                v_companyReportModel.$data.abnormalItemList=response.entAbnormalItemInfos;
                v_companyReportModel.$data.equityList=response.entEquityInfos;
                v_companyReportModel.$data.mortgagesList=response.entMortgagesInfos;
                v_companyReportModel.$nextTick(function () {
                    bindExportedDataTable("law_table",10,"法务",{});
                    bindExportedDataTable("abnormal_table",10,"经营异常",{});
                    bindExportedDataTable("equity_table",10,"股权质押",{});
                    bindExportedDataTable("mortgages_table",10,"动产抵押",{});
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