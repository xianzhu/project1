/**
 * Created by a88u on 2016/10/23.
 */

var v_benchmarkModel=new Vue({
    el:"#v-benchmarkModel",
    data: {
        topic_content:"", // 经营范围
        is_state_hold:"", // 是否国资背景
        total_asset:"", // 资产总计
        opt_income:"", // 营业收入
        opt_profit:"", // 营业利润
        liab_ratio:"", // 资产负债率
        gross_profit_margin:"", // 销售毛利率

        showInputWarning:false,

        xsbMatchList:[], // 对标新三板
        listMatchList:[] // 对标上市公司
    },
    ready: function () {
    },
    methods: {
        goto:function(stockcode,type){
            //var uuid=id;
            console.log(stockcode);
            gotoCompanyByCode(stockcode,type);
        },

        doSearch:function(){
            if(this.topic_content==""){
                showInfo("提示","请输入经营范围");
                return;
            }
            doMatchSearch();
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

function doMatchSearch(){
    var data=v_benchmarkModel.$data;
    var params = {
        topic_content: data.topic_content,
        is_state_hold:data.is_state_hold,
        total_asset:data.total_asset,
        opt_income:data.opt_income,
        opt_profit:data.opt_profit,
        liab_ratio:data.liab_ratio,
        gross_profit_margin:data.gross_profit_margin
    };
    console.log(params);
    $.ajax({
        url: commonUrls.benchmarkUrl,//请求地址
        type: "POST",                       //请求方式
        data: params,        //请求参数
        dataType: "json",
        success: function (res) {
            if(res.status=="failure") {
                console.log("failure", res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success"){
                var response = res;
                v_benchmarkModel.$data.isFirst=false;
                v_benchmarkModel.$data.xsbMatchList = response.xsb_matching_list;

                v_benchmarkModel.$data.listMatchList = response.list_matching_list;
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





