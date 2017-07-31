/**
 * Created by a88u on 2016/10/23.
 */
menuList.simulation.isActive=true;

var v_simulParamModel = new Vue({
    el: "#v-simulParamModel",
    data: {
        topic_content: "", // 经营范围
        is_state_hold: "", // 是否国资背景
        total_asset: "", // 资产总计
        opt_income: "", // 营业收入
        opt_profit: "", // 营业利润
        liab_ratio: "", // 资产负债率
        gross_profit_margin: "", // 销售毛利率

        showInputWarning: false, // 显示输入提示
        showSimulParamDiv: true
    },
    methods: {
        beginSimulation: function () {
            if (this.topic_content == "") {
                // showInfo("提示","请输入经营范围");
                this.$data.showInputWarning = true;
                return;
            }
            doSimulation();
        },
        cancelSimulation: function () {
            this.$data.showSimulParamDiv = false;
        }
    },
    filters: {}
});
var v_simulationModel = new Vue({
    el: "#v-simulationModel",
    data: {
        isFirstShow: false,
        // currentModule:0, // 当前Tab，0--对标新三板，1--对标上市公司
        simParamTags: {
            topicContent:{key:"topicContent",text:"",isSelected:false},
            isStateHold:{key:"isStateHold",text:"",isSelected:false},
            totalAsset:{key:"totalAsset",text:"",isSelected:false},
            optIncome:{key:"optIncome",text:"",isSelected:false},
            optProfit:{key:"optProfit",text:"",isSelected:false},
            liabRatio:{key:"liabRatio",text:"",isSelected:false},
            grossProfitMargin:{key:"grossProfitMargin",text:"",isSelected:false}
        }, // 填写的参数列表
        xsbMatchList: [], // 对标新三板
        listMatchList: [] // 对标上市公司
    },
    ready: function () {
    },
    methods: {
        gotoCompanyByCode: function (stockcode, type) {
            // gotoCompanyByCode(stockcode, type);
            openCompanyByCode(stockcode, type);

        },
        // changeModule:function (value) {
        //     this.$data.currentModule=value;
        // },
        openSimulation: function () {
            v_simulParamModel.$data.showSimulParamDiv = true;
            v_simulParamModel.$data.showInputWarning = false;
        },
        removeSelectedParam:function (key) {
            v_simulationModel.$data.simParamTags[key].isSelected=false;
        }
    },
    filters: {
        stateHoldFilter: function (value) {
            return value == 0 ? '否' : '是';
        },
        checkEmptyFilter: function (value) {
            var result = false;
            if (value && value.length > 0) {
                result = true;
            }
            return result;
        }
    }
});

function doSimulation() {
    var data = v_simulParamModel.$data;
    // v_simulationModel.$data.simParamTags = [];
    var params = {
        from:0,
        count:commonPageNum.simulation,
        topic_content: data.topic_content,
        is_state_hold: data.is_state_hold,
        total_asset: data.total_asset,
        opt_income: data.opt_income,
        opt_profit: data.opt_profit,
        liab_ratio: data.liab_ratio,
        gross_profit_margin: data.gross_profit_margin
    };

    v_simulationModel.$data.simParamTags.topicContent.text = "经营范围: " + data.topic_content;
    v_simulationModel.$data.simParamTags.topicContent.isSelected = (data.topic_content != "");
    var stateHold = (data.is_state_hold == "1") ? "是" : "否";
    v_simulationModel.$data.simParamTags.isStateHold.text = "是否国资背景: " + stateHold;
    v_simulationModel.$data.simParamTags.isStateHold.isSelected = (data.is_state_hold != "");
    v_simulationModel.$data.simParamTags.totalAsset.text = "资产总计: " + data.total_asset+" 元 ";
    v_simulationModel.$data.simParamTags.totalAsset.isSelected = (data.total_asset != "");
    v_simulationModel.$data.simParamTags.optIncome.text = "营业收入: " + data.opt_income+" 元 ";
    v_simulationModel.$data.simParamTags.optIncome.isSelected = (data.opt_income != "");
    v_simulationModel.$data.simParamTags.optProfit.text = "营业利润: " + data.opt_profit+" 元 ";
    v_simulationModel.$data.simParamTags.optProfit.isSelected = (data.opt_profit != "");
    v_simulationModel.$data.simParamTags.liabRatio.text = "资产负债率: " + data.liab_ratio+" % ";
    v_simulationModel.$data.simParamTags.liabRatio.isSelected = (data.liab_ratio != "");
    v_simulationModel.$data.simParamTags.grossProfitMargin.text = "销售毛利率: " + data.gross_profit_margin+" % ";
    v_simulationModel.$data.simParamTags.grossProfitMargin.isSelected = (data.gross_profit_margin != "");

    console.log(params);
    $.ajax({
        url: commonUrls.simulationUrl,//请求地址
        type: "POST",                       //请求方式
        data: params,        //请求参数
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure", res.message);
                v_simulParamModel.$data.showSimulParamDiv = false;
                showInfo("计算失败","计算失败：无法分析输入的经营范围，请重新输入！");
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == "success") {
                var response = res;
                v_simulationModel.$data.isFirstShow = true;
                v_simulationModel.$data.xsbMatchList = response.xsb_matching_list;
                v_simulationModel.$data.listMatchList = response.list_matching_list;
                v_simulParamModel.$data.showSimulParamDiv = false;
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
            500: function () {
                goTo500();
            }
        }
    });
}





