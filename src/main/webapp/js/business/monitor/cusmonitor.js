/**
 * Created by a88u on 2016/10/30.
 */
menuList.monitor.isActive=true;
menuList.monitor.showChild=true;
menuList.monitor.childMenu.cusMonitor.isActive=true;

resize();

function resize() {
    var cheight = $("#page-wrapper").height()+7;
    if (cheight < 100) {
        cheight = 100;
    }
    console.log(cheight);
    $("#inventForceChart").css("height", cheight + "px");
}

var monitorType = {
    company: 1,
    org: 2
};

var v_cusMonitorModel = new Vue({
    el: "#v-cusMonitorModel",
    data: {
        monitorList: [],
        currentMonitorSelect: 0, // 当前监控项选择
        monitorTypeSelect: 0, // 当前监控类型选择

        investCompany: [], // 投资企业
        //familyOrg:[],
        //focusCompany:[],
        //ivsCapitalList:[],
        //extCapitalList:[],

        orgInvestList: [], // 投资事件
        orgFamilyList: [], // 关联族谱

        entLawList: [], // 法务 ent_law
        abnormalItemList: [], // 经营异常	ent_abnormal_item
        equityList: [],// 股权质押	ent_equity
        mortgagesList: [], //动产抵押	ent_mortgages
        changeList: [], // 变更

        entInvestFinanceList: [], // 企业投资情况
        entExitFinanceList: [], // 企业退出情况
        currentSelect: 0,
        currentNodeId: "" // 当前选中点id
    },
    ready: function () {
    },
    methods: {
        getShortStrFilter: function (value) {
            var result;
            if (value && value.length > 60) {
                result = value.substr(0, 60) + "...";
            }
            return result;
        },
        checkShowFilter: function (value) {
            return value == this.currentSelect;
        },
        gotoCompany: function (id) {
            //console.log(id,value);
            gotoCompanybyId(id);
        },
        showChangeDetail: function (item) {
            change_info_modal.$data.changeInfo = item;
            console.log(change_info_modal.$data.changeInfo);
            change_info_modal.$data.showModal = true;
        }
    },
    filters: {
        checkEmptyFilter: function (value) {
            var result = false;
            if (value && value.length > 0) {
                result = true;
            }
            return result;
        },
        monitorSelectionFilter: function (item) {
            var result = "";
            if (item.type == monitorType.company) {
                result = "企业--" + item.content;
            } else if (item.type == monitorType.org) {
                result = "机构--" + item.content;
            }
            return result;
        },
        formatStringFilter: function (value) {
            var result = "--";
            if (value && value.toLowerCase() != "null") {
                result = value;
            }
            return result;
        },
        strFormatShortFilter: function (value) {
            var result = "";
            if (value) {
                if (value.length > 8) {
                    result = value.substr(0, 8) + "...";
                } else {
                    result = value;
                }
            } else {
                result = "--";
            }
            return result;
        }
    }
});

var selectNode;
var echarts;
var myChart;
var myoption = clone(relation_company_option);

var nodeType = {
    company: 1,
    org: 2
};

var symbolType = {
    company: "circle",
    //company:"image://img/charts/company001.png",
    org: "rectangle"
};

getMonitorList();

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
}

// 点击选中时显示
function focus(param) {
    //console.log(param);
    var data = param.data;
    //console.log(data);
    var links = myoption.series[0].links;
    var nodes = myoption.series[0].nodes;
    if (
        data.source != null
        && data.target != null
    ) { //点击的是边
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
        requestNewData();
    }
}

function getMonitorList() {
    console.log(v_userModel.$data.uname);
    $.ajax({
        url: commonUrls.custMonitorUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            object: 5,
            uid: v_userModel.$data.uname
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                //goToLoginout();
                console.log("failure", res.message);
                goToNotlogon();
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                //console.log("response", response);

                var result = [];
                for (var i = 0; i < res.monitorList.length; i++) {
                    var item = res.monitorList[i];
                    //console.log(item);
                    if (item.type == monitorType.company) { // 企业
                        result.push({
                            index: result.length,
                            content: item.content,
                            type: item.type,
                            id: item.id,
                            uid: item.uid,
                            mid: item.uuid
                        });
                    } else if (item.type == monitorType.org) { // 机构
                        result.push({
                            index: result.length,
                            content: item.content,
                            type: item.type,
                            id: item.id,
                            uid: item.uid,
                            mid: item.orgId
                        });
                    }
                }

                v_cusMonitorModel.$data.monitorList = result;

                v_cusMonitorModel.$nextTick(function () {
                    if (result.length > 0) {
                        $("#currentMonitorSelect").val(result[0].index);
                        changeMonitorSelection();
                    }
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

function requestNewData() {
    if (selectNode) {
        var id = selectNode.name; // 唯一标识
        var type = selectNode.type; // 类型
        var cgy = selectNode.category; // 颜色、样式分类
        var level = selectNode.level; // 级别

        if (type == nodeType.company) { // 点击企业
            if (selectNode.uuid == "") {
                console.log("click empty node.");
                return;
            } else if (v_cusMonitorModel.$data.currentNodeId == selectNode.uuid) {
                console.log("click the same node.");
                return;
            }

            if ((level == 0) || (v_cusMonitorModel.$data.monitorTypeSelect == 2 && level == 1)) {
                var uuid = selectNode.uuid;
                reSetOptionNodes("com_" + uuid);
                v_cusMonitorModel.$data.currentNodeId = selectNode.uuid;
                getCompanyExtendNode(id, type, cgy, level, uuid);
                getCompanyInfo(uuid); // 联动
            } else {
                showInfo("提示", "不支持更深一级扩展！");
            }
        }
        else if (type == nodeType.org) { // 点击机构
            var oid = selectNode.oid;
            if (v_cusMonitorModel.$data.currentNodeId == oid) {
                console.log("click the same node.");
                return;
            }

            reSetOptionNodes("org_" + oid);
            v_cusMonitorModel.$data.currentNodeId = oid;
            getOrgExtendNode(id, type, cgy, level, oid);
            getOrgInfo(oid);
        }
    } else { // 初始，获取监控内容起始点
        console.log("init monitor point")
    }
}

// 选择监控点之后更新监控点信息
function updateData(item) {
    var name = item.mid;
    var text = item.content;

    if (item.type == monitorType.company) { // 公司
        myoption = clone(relation_company_option);
        v_cusMonitorModel.$data.monitorTypeSelect = 1;

        myoption.series[0].nodes.push({
            category: 0,
            name: "com_" + item.mid,
            symbol: symbolType.company,
            value: 15,
            type: nodeType.company,
            text: item.content,
            label: formatLabel(item.content),
            level: 0,
            uuid: item.mid
        });
        v_cusMonitorModel.$data.currentNodeId = "companyParent"; // 为初始化企业监控的当前选中点
        getCompanyInfo(item.mid);
        getCompanyExtendNode("com_"+item.mid, nodeType.company, 0, 0, item.mid);
    } else if (item.type == monitorType.org) { // 机构
        myoption = clone(relation_org_option);
        v_cusMonitorModel.$data.monitorTypeSelect = 2;
        //v_cusMonitorModel.$data.currentSelect=2;
        myoption.series[0].nodes.push({
            category: 0,
            name: "org_" + item.mid,
            symbol: symbolType.org,
            value: 14,
            type: nodeType.org,
            text: item.content,
            label: formatLabel(item.content),
            level: 0,
            oid: item.mid
        });
        v_cusMonitorModel.$data.currentNodeId = item.mid;
        getOrgInfo(item.mid);
        getOrgExtendNode("org_" + item.mid, nodeType.org, 0, 0, item.mid);
    }

    // 增加支点
    myoption.series[0].nodes.push({
        category: 4,
        name: "com_random",
        //symbol: symbolType.company,
        value: 0,
        type: nodeType.company,
        text: "",
        label: '',
        level: 0,
        uuid: ""
    });
    setTimeout(refresh, 1000);
}

// 刷新charts
function refresh() {
    //console.log("refresh");
    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    var dom = document.getElementById("inventForceChart");
    myChart = echarts.init(dom);

    myChart.setOption(myoption, true);

    myChart.on("click", focus);
}

$(window).resize(function () {
    setTimeout(refresh, 1000);
});

function reSetOptionNodes(id) {
    //console.log(id);
    for (var i = 0; i < myoption.series[0].nodes.length; i++) {
        var node = myoption.series[0].nodes[i];
        if (node.category == 0) { // 如果是监控源，保持不变
            continue;
        }
        if (id == node.name) {
            node.category = 1;
        } else {
            node.category = 2;
        }
    }
}

// 更改监控对象响应
function changeMonitorSelection() {
    v_cusMonitorModel.$data.currentMonitorSelect = $("#currentMonitorSelect").val();
    var item = v_cusMonitorModel.$data.monitorList[v_cusMonitorModel.$data.currentMonitorSelect];
    v_cusMonitorModel.$data.monitorTypeSelect = item.type;
    //console.log(v_cusMonitorModel.$data.monitorTypeSelect);
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
    //console.log(v_cusMonitorModel.$data.monitorTypeSelect,', ',v_cusMonitorModel.$data.currentSelect);

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
var flag=true;
// 变更
function getCompanyBasicInfo(id) {
    var url=flag? commonUrls.monitorEntBasicUrl:commonUrls.monitorEntBasicUrl_2;
    flag=!flag;
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
                    bindExportedDataTable("orgInvest_table", commonPageNum.cusMonitorOrgInvest, "机构投资事件", {});
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

// 获取投资企业点
function getCompanyExtendNode(sid, type, cgy, level, uuid) {
    //console.log("getCompanyExtendNode:", sid, type, cgy, level, uuid);
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
                    bindExportedDataTable("invest_table", commonPageNum.cusMonitorEntInvest, "投资企业", {});
                });

                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].iUuid;
                        var name = data[i].name;
                        //var isNew=Math.random()>0.5;
                        var found = false;
                        // 处理node
                        for (var j = 0; j < myoption.series[0].nodes.length; j++) {
                            if ("com_" + tid == myoption.series[0].nodes[j].name) {
                                found = true;
                                myoption.series[0].nodes[j].category = 3;
                                break;
                            }
                        }
                        if (!found) { // 投资企业
                            myoption.series[0].nodes.push({
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
                        for (var j = 0; j < myoption.series[0].links.length; j++) {
                            var link = myoption.series[0].links[j];
                            if ("com_" + tid == link.target && sid == link.source) {
                                lfound = true;
                                break;
                            }
                        }
                        if (!lfound) {
                            myoption.series[0].links.push({
                                source: sid,
                                target: "com_" + tid,
                                text: "", // "投资企业",
                                weight: Math.random() * 50 + 1 // 20
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

// 获取机构扩展点--1级机构族谱，2级投资企业
function getOrgExtendNode(sid, type, cgy, level, oid) {
    if (level == 0) { // 机构族谱预测
        getOrgFamilyNode(sid, type, cgy, level, oid);
    }
}

function getOrgFamilyNode(sid, type, cgy, level, oid) {
    // 机构族谱预测
    $.ajax({
        url: commonUrls.monitorOrgFamilyUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: oid
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
                console.log(response.extendList);

                $("#orgFamily_table").DataTable().destroy();
                var data = response.extendList;

                v_cusMonitorModel.$data.orgFamilyList = data;
                v_cusMonitorModel.$nextTick(function () {
                    bindExportedDataTable("orgFamily_table", commonPageNum.cusMonitorOrgFamily, "关联族谱", {});
                });

                if (data && data.length > 0) {
                    var subnodes = [];
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].uuid;
                        var name = data[i].orgEntity;
                        var found = false;
                        var mid = "com_" + tid;

                        subnodes.push({
                            id: mid,
                            type: nodeType.company,
                            category: 3,
                            level: level + 1,
                            uuid: tid
                        });
                        // 处理node
                        for (var j = 0; j < myoption.series[0].nodes.length; j++) {
                            if ("com_" + tid == myoption.series[0].nodes[j].name) {
                                found = true;
                                myoption.series[0].nodes[j].category = 3;
                                break;
                            }
                        }
                        if (!found) {
                            myoption.series[0].nodes.push({
                                name: mid,
                                type: nodeType.company,
                                symbol: symbolType.company,
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
                        for (var j = 0; j < myoption.series[0].links.length; j++) {
                            var link = myoption.series[0].links[j];
                            if ("com_" + tid == link.target && sid == link.source) {
                                lfound = true;
                                break;
                            }
                        }
                        if (!lfound) {
                            var we = Math.random() * 50 + 1;
                            //console.log(we);
                            myoption.series[0].links.push({
                                source: sid,
                                target: "com_" + tid,
                                text: "", // we, // "机构族谱",
                                weight: we
                            });
                        }
                    }
//console.log(subnodes);
                    getMutiComExtendNode(subnodes);
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
    //console.log(len, result);
    return result;
}

function getMutiComExtendNode(comList) {
    //console.log("getMutiComExtendNode", comList.length);
    for (var i = 0; i < comList.length; i++) {
        var item = comList[i];
        getOrgComInvestNode(item.id, item.type, item.category, item.level, item.uuid);
    }
}

function getOrgComInvestNode(sid, type, cgy, level, uuid) {
    //console.log("getCompanyExtendNode:", sid, type, cgy, level, uuid);
    $.ajax({
        url: commonUrls.monitorEntInvestUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id: uuid
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

                var data = response.entInvestInfos;

                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].iUuid;
                        var name = data[i].name;
                        //var isNew=Math.random()>0.5;
                        var found = false;
                        // 处理node
                        for (var j = 0; j < myoption.series[0].nodes.length; j++) {
                            if ("com_" + tid == myoption.series[0].nodes[j].name) {
                                found = true;
                                //myoption.series[0].nodes[j].category = 2;
                                break;
                            }
                        }
                        if (!found) { // 投资企业
                            myoption.series[0].nodes.push({
                                name: "com_" + tid,
                                symbol: symbolType.company,
                                type: nodeType.company,
                                category: 2,
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
                        for (var j = 0; j < myoption.series[0].links.length; j++) {
                            var link = myoption.series[0].links[j];
                            if ("com_" + tid == link.target && sid == link.source) {
                                lfound = true;
                                break;
                            }
                        }
                        if (!lfound) {
                            myoption.series[0].links.push({
                                source: sid,
                                target: "com_" + tid,
                                text: "", // "投资企业",
                                weight: Math.random() * 50 + 1 // 20
                            });
                        }
                    }
                }
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
