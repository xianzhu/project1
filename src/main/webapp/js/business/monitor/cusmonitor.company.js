/**
 * Created by a88u on 2017/7/28.
 */

var monitorType = {
    company: 1,
    org: 2
};
$(window).ready(function () {
    resize();
    var ele = $(".js-switch")[0];
    var switchery = new Switchery(ele, {color: "#1AB394"});
    $(".js-force-label")[0].onchange = function (e) {
        // console.log(e.target.checked);
        if (myOrgChart) {
            console.log(myOrgChart);
            var cates = myOrgChart._option.series[0].categories;
            for (var i = 0; i < cates.length; i++) {
                cates[i].itemStyle.normal.label.show = e.target.checked;
            }
            myOrgChart.refresh();
        }
    };

    $("#range_slider").slider({
        orientation: "vertical",
        range: true,
        max: 1,
        min: 0,
        values: [0, 1],
        slide: function (event, ui) {
            console.log(ui.values[0] + " - " + ui.values[1]);
            $("#range_slider .ui-slider-handle-min span").html(ui.values[0]);
            $("#range_slider .ui-slider-handle-max span").html(ui.values[1]);
        }
    });
});
function resize() {
    var bheight = $("body").height() - 152, rheight = $(".page-right-wrapper").height() - 71;
    var cheight = bheight > rheight ? bheight : rheight;
    if (cheight < 100) {
        cheight = 100;
    }
    console.log(bheight, rheight, cheight);
    resizeDetailMask();
    $("#orgForceChart").css("height", cheight + "px");
    $("#comForceChart").css("height", cheight + "px");
}
function resizeDetailMask() {
    var height = $(window).height();
    var mheight = height;
    $(".modal-changeinfo-body").css('maxHeight', mheight - 181);
    $(".modal-event-body").css('maxHeight', mheight - 181);
}

var nodeType = {
    company: 1,
    org: 2
};
var symbolType = {
    company: "circle",
    //company:"image://img/charts/company001.png",
    org: "circle"
};

require.config({
    paths: {
        echarts: 'js/plugins/echarts-2.2.7'
    }
});
require(['echarts', 'echarts/chart/force'], requireCallback);

Vue.component('change_info_modal', {
    template: '#change-info-template',
    props: {
        show: {
            type: Boolean,
            required: true,
            twoWay: true
        }
    }
});
var change_info_modal = initChangeModelVue();
function initChangeModelVue() {
    //console.log("change-info-template");
    v_change_info_modal = new Vue({
        el: "#v-change-info-modal",
        data: {
            showModal: false,
            changeInfo: {}
        }
    });
    return v_change_info_modal;
}

function requireCallback(ec) {
    echarts = ec;
    getMonitorList();
}
// 点击选中时显示
function focusCom(param) {
    console.log("focus click");
    var data = param.data;
    //console.log(data);
    var links = myComChart._option.series[0].links;
    var nodes = myComChart._option.series[0].nodes;
    if (data.source != null && data.target != null) { //点击的是边
        //var sourceNode = nodes.filter(function (n) {return n.id == data.source})[0];
        //var targetNode = nodes.filter(function (n) {return n.id == data.target})[0];
    } else { // 点击的是点
        selectNode = nodes.filter(function (n) {
            return n.name == data.name
        })[0];
        console.log("选中了" + data.name + '(' + data.name + ')');

        if (selectNode.level == 3) {
            showInfo("提示", "不提供更深扩展！");
            return;
        }
        requestComNewData();
    }
}
// 企业--投资企业
function requestComNewData() {
    if (selectNode) {
        var id = selectNode.name; // 唯一标识
        var type = selectNode.type; // 类型
        var cgy = selectNode.category; // 颜色、样式分类
        var level = selectNode.level; // 级别

        if (selectNode.uuid == "") {
            console.log("click empty node.");
            return;
        } else if (v_cusMonitorModel.$data.currentNodeId == selectNode.uuid) {
            console.log("click the same node.");
            return;
        }

        if (level == 0) {
            var uuid = selectNode.uuid;
            reSetOptionNodes("com_" + uuid);
            v_cusMonitorModel.$data.currentNodeId = selectNode.uuid;
            getCompanyExtendNode(id, type, cgy, level, uuid);
            getCompanyInfo(uuid); // 联动
        } else {
            console.log("showInfo");
            showInfo("提示", "不支持更深一级扩展！");
        }
    } else { // 初始，获取监控内容起始点
        console.log("init monitor point")
    }
}

$(window).resize(function () {
    resize();
    setTimeout(refresh, 1000);
});

// 更改监控对象响应
function changeMonitorSelection() {
    v_cusMonitorModel.$data.currentMonitorSelect = $("#currentMonitorSelect").val();
    var item = v_cusMonitorModel.$data.monitorList[v_cusMonitorModel.$data.currentMonitorSelect];
    v_cusMonitorModel.$data.monitorTypeSelect = item.type;
    updateData(item);
}

// 获取企业表信息--变更、法务、经营异常、股权质押、动产抵押，融资情况
function getCompanyInfo(id) {
    if (v_cusMonitorModel.$data.monitorTypeSelect == 2) { // 机构关联族谱企业点
        v_cusMonitorModel.$data.currentSelect = 3;
        //getCompanyReportInfo(id);
    } else { // 监控企业点
        v_cusMonitorModel.$data.currentSelect = 1;
        getCompanyReportInfo(id);
        getCompanyBasicInfo(id);
        getCompanyFinanceInfo(id);
    }
}
// 法务、经营异常、股权质押、动产抵押
function getCompanyReportInfo(id) {
    $.ajax({
        url: commonUrls.monitorEntCreditUrl,
        type: "POST",
        data: {
            id: id
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                //goToLoginout();
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                //console.log("response", response);

                var entLawInfos = [];
                if (response.entLawInfos && response.entLawInfos.length > 0) {
                    entLawInfos.push(response.entLawInfos[0]);
                }

                var entAbnormalItemInfos = [];
                if (response.entAbnormalItemInfos && response.entAbnormalItemInfos.length > 0) {
                    entAbnormalItemInfos.push(response.entAbnormalItemInfos[0]);
                }

                var entEquityInfos = [];
                if (response.entEquityInfos && response.entEquityInfos.length > 0) {
                    entEquityInfos.push(response.entEquityInfos[0]);
                }

                var entMortgagesInfos = [];
                if (response.entMortgagesInfos && response.entMortgagesInfos.length > 0) {
                    entMortgagesInfos.push(response.entMortgagesInfos[0]);
                }
                v_cusMonitorModel.$data.entLawList = entLawInfos;
                v_cusMonitorModel.$data.abnormalItemList = entAbnormalItemInfos;
                v_cusMonitorModel.$data.equityList = entEquityInfos;
                v_cusMonitorModel.$data.mortgagesList = entMortgagesInfos;
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
var flag = true;
// 变更
function getCompanyBasicInfo(id) {
    var url = flag ? commonUrls.monitorEntBasicUrl : commonUrls.monitorEntBasicUrl_2;
    flag = !flag;
    $.ajax({
        url: url,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: id
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == "success") {
                var response = res;
                var entChgRecordInfos = [];
                if (response.entChgRecordInfos && response.entChgRecordInfos.length > 0) {
                    entChgRecordInfos.push(res.entChgRecordInfos[0]);
                }
                v_cusMonitorModel.$data.changeList = entChgRecordInfos;
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
// 企业融资情况
function getCompanyFinanceInfo(id) {
    $.ajax({
        url: commonUrls.monitorEntFinanceUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: id
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == "success") {
                var response = res;
                $("#entInvest_table").DataTable().destroy();
                $("#entExit_table").DataTable().destroy();

                v_cusMonitorModel.$data.entInvestFinanceList = response.entFinancalList;
                v_cusMonitorModel.$data.entExitFinanceList = response.entExitEventList;

                v_cusMonitorModel.$nextTick(function () {
                    bindSimpleDataTable("entInvest_table", commonPageNum.cusMonitorComInvest);
                    bindSimpleDataTable("entExit_table", commonPageNum.cusMonitorComExit);
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

// 获取机构表信息--投资事件
function getOrgInfo(id) {
    v_cusMonitorModel.$data.currentSelect = 2;
    $.ajax({
        url: commonUrls.monitorOrgCaptialUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: id
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                //goToLoginout();
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;

                $("#orgInvest_table").DataTable().destroy();
                v_cusMonitorModel.$data.orgInvestList = res.inventList;
                v_cusMonitorModel.$nextTick(function () {
                    bindSimpleDataTable("orgInvest_table", commonPageNum.cusMonitorOrgInvest);
                })
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

// 分行显示公司名、机构名
function formatLabel(source) {
    var result = "";
    var len = source.length;

    if (len > 6) {
        var pos = parseInt(len / 2);
        result = source.substring(0, pos) + "\n" + source.substring(pos, len);
    } else {
        result = source;
    }
    return result;
}

// 获取投资企业点
function getCompanyExtendNode(sid, type, cgy, level, uuid) {
    $.ajax({
        url: commonUrls.monitorEntInvestUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: uuid
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;

                $("#invest_table").DataTable().destroy();
                var data = response.entInvestInfos;

                v_cusMonitorModel.$data.investCompany = data;
                v_cusMonitorModel.$nextTick(function () {
                    bindSimpleDataTable('invest_table', commonPageNum.cusMonitorEntInvest);
                });

                var myopt = myComChart._option;
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].iUuid;
                        var name = data[i].name;
                        //var isNew=Math.random()>0.5;
                        var found = false;
                        // 处理node
                        for (var j = 0; j < myopt.series[0].nodes.length; j++) {
                            if ("com_" + tid == myopt.series[0].nodes[j].name) {
                                found = true;
                                myopt.series[0].nodes[j].category = 3;
                                break;
                            }
                        }
                        if (!found) { // 投资企业
                            myopt.series[0].nodes.push({ // 企业监控--被投资企业
                                name: "com_" + tid,
                                symbol: symbolType.company,
                                type: nodeType.company,
                                category: 3,
                                text: name,
                                label: formatLabel(name),
                                value: 10 - level * 5,
                                level: level + 1,
                                uuid: tid
                            });
                        }
                        // 处理link
                        var lfound = false;
                        //console.log(tid, sid);
                        for (var j = 0; j < myopt.series[0].links.length; j++) {
                            var link = myopt.series[0].links[j];
                            if ("com_" + tid == link.target && sid == link.source) {
                                lfound = true;
                                break;
                            }
                        }
                        if (!lfound) {
                            myopt.series[0].links.push({
                                source: sid,
                                target: "com_" + tid,
                                text: "", // "投资企业",
                                //           weight: Math.random() * 50 + 1 // 20
                            });
                        }
                    }
                }
                setTimeout(refresh, 1000);
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
