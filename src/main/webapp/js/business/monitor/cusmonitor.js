/**
 * Created by a88u on 2016/10/30.
 */
menuList.monitor.isActive = true;
menuList.monitor.showChild = true;
menuList.monitor.childMenu.cusMonitor.isActive = true;

var nodeLevel = {
    node_2: 1,
    node_3: 2
}
var categoryModel={
    source:0, // 监控源
    current:1, // 当前选中
    node_3:2, // 关联经营实体对外投资企业
    node_2:3, // 关联经营实体
    node_3_key:4, // 关键企业
    node_2_from:5, // 投资方
    node_2_to:6, // 被投资方
    normal:7 // 选中实体非相关

}

var v_cusMonitorModel = new Vue({
    el: "#v-cusMonitorModel",
    data: {
        monitorList: [],
        currentMonitorSelect: 0, // 当前监控项选择
        monitorTypeSelect: 0, // 当前监控类型选择  1--公司，2--机构

        investCompany: [], // 投资企业

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
        },
        showCapDetail: function (id) {
            var etype = "invest";
            $.ajax({
                url: commonUrls.monitorOrgEventDetailUrl,              //请求地址
                type: "get",                            //请求方式
                data: { //请求参数
                    id: id,
                    type: etype
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
                        showCapitalDetail(response);
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
        },
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

var selectNode, // 点击选中节点
    valueStep = 3, // 机构监控节点value step
    sliderMax = 0, // 关联度最大值
    echarts, // 绘图echarts
    myOrgChart,  // 机构图
    myComChart; // 企业图


function focusOrg(param) {
    // console.log("focus click");
    var data = param.data;
    //console.log(data);
    var links = myOrgChart._option.series[0].links;
    var nodes = myOrgChart._option.series[0].nodes;
    if (data.source != null && data.target != null) { //点击的是边
        //var sourceNode = nodes.filter(function (n) {return n.id == data.source})[0];
        //var targetNode = nodes.filter(function (n) {return n.id == data.target})[0];
    } else { // 点击的是点
        selectNode = nodes.filter(function (n) {
            return n.name == data.name
        })[0];
        console.log("选中了" + data.name, data);

        if (selectNode.level == 3) {
            showInfo("提示", "不提供更深扩展！");
            return;
        }
        requestOrgNewData();
    }
}

function getMonitorList() {
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

function requestOrgNewData() {
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

            if (level == nodeLevel.node_2) {
                var uuid = selectNode.uuid;
                reSetOptionNodes("com_" + uuid);
                v_cusMonitorModel.$data.currentNodeId = selectNode.uuid;
                getOrgComExtendNode(id, type, cgy, level, uuid);
                getOrgCompanyInfo(uuid); // 联动
            } else {
                console.log("showInfo");
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
    var name = item.mid, text = item.content;
    var myoption, ecConfig = require('echarts/config');

    if (item.type == monitorType.company) { // 公司
        $("#com_force_chart").css("display", 'block');
        $("#org_force_chart").css("display", 'none');
        if (myComChart && myComChart.dispose) {
            myComChart.dispose();
        }
        myoption = clone(relation_company_option);
        v_cusMonitorModel.$data.monitorTypeSelect = 1;
        myoption.title.text = item.content;
        myoption.series[0].nodes.push({ // 企业监控，监控源
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

        var cdom = document.getElementById("comForceChart");
        myComChart = echarts.init(cdom);
        myComChart.on(ecConfig.EVENT.CLICK, focusCom);
        window.addEventListener('resize', function () {
            myComChart.resize && myComChart.resize();
        });
        myComChart.setOption(myoption, true);
        getCompanyInfo(item.mid);
        getCompanyExtendNode("com_" + item.mid, nodeType.company, 0, 0, item.mid);
    } else if (item.type == monitorType.org) { // 机构
        $("#com_force_chart").css("display", 'none');
        $("#org_force_chart").css("display", 'block');
        if (myOrgChart && myOrgChart.dispose) {
            myOrgChart.dispose();
        }
        myoption = clone(relation_org_option);
        myoption.title.text = item.content;
        v_cusMonitorModel.$data.monitorTypeSelect = 2;

        sliderMax = 0;
        var odom = document.getElementById("orgForceChart");
        myoption.series[0].nodes.push({ // 添加机构监控 -- 监控源
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

        myOrgChart = echarts.init(odom);
        myOrgChart.on(ecConfig.EVENT.CLICK, focusOrg);
        window.addEventListener('resize', function () {
            myOrgChart.resize && myOrgChart.resize();
        });
        myOrgChart.setOption(myoption, true);
        myOrgChart._option.legend.data=['关联经营实体','关联经营实体对外投资企业','关键企业'];
        getOrgInfo(item.mid);
        getOrgExtendNode("org_" + item.mid, nodeType.org, 0, 0, item.mid);
    }
}

// 刷新charts
function refresh() {
    if (v_cusMonitorModel.$data.monitorTypeSelect == monitorType.company) {
        if (myComChart._option) {
            myComChart.refresh();
        }
    } else if (v_cusMonitorModel.$data.monitorTypeSelect == monitorType.org) {
        if (myOrgChart) {
            myOrgChart.refresh();
        }
    } else {
        console.log("re-fresh error", v_cusMonitorModel.$data.monitorTypeSelect);
    }
}

// force点击后，先把全部复位
function reSetOptionNodes(id) {
    var nodes = myOrgChart._option.series[0].nodes;
    for (var i = 0; i < nodes.length; i++) {
        var node = nodes[i];
        if (node.category == 0) { // 如果是监控源，保持不变
            continue;
        }
        if (id == node.name) {
            node.category = categoryModel.current;
        } else {
            node.category=categoryModel.normal;
        }
    }
}

// 获取机构关联--投资企业点
function getOrgComExtendNode(sid, type, cgy, level, uuid) {
    var links = myOrgChart._option.series[0].links, nodes = myOrgChart._option.series[0].nodes;
    for (var i = 0; i < links.length; i++) {
        var link = links[i];
        if (link.source == sid) { // 被投资方--〉tIndex
            var tnode = nodes[link.tIndex];
            if (tnode.level == nodeLevel.node_2) {
                tnode.category = 5;
            } else if (tnode.level == nodeLevel.node_3) {
                if (tnode.toNum == 1) {
                    tnode.category = 2;
                } else {
                    tnode.category = 4;
                }
            }
        } else if (link.target == sid) { // 投资方--〉sIndex
            var snode = nodes[link.sIndex];
            snode.category = 6;
        }
    }

    myOrgChart._option.legend.data=['当前选中','投资方','被投资方','关联经营实体对外投资企业','关键企业','选中实体非相关'];
    setTimeout(refresh, 1000);
}
function getOrgComExtendNode_old(sid, type, cgy, level, uuid) {
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

                var myopt = myOrgChart._option;
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].iUuid;
                        var name = data[i].name;
                        // 处理node
                        for (var j = 0; j < myopt.series[0].nodes.length; j++) {
                            if ("com_" + tid == myopt.series[0].nodes[j].name) {
                                // myopt.series[0].nodes[j].category = 3;
                                var cnode = myopt.series[0].nodes[j];
                                if (cnode.level == nodeLevel.node_2) {
                                    cnode.category = 3;
                                } else if (cnode.level == nodeLevel.node_3) {
                                    if (cnode.toNum == 1) {
                                        cnode.category = 2;
                                    } else {
                                        cnode.category = 4;
                                    }
                                }
                                break;
                            }
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

// 获取机构关联--投资企业点 列表信息
function getOrgCompanyInfo(uuid) {
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

// 机构族谱预测
function getOrgFamilyNode(sid, type, cgy, level, oid) {
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
                // console.log(response.extendList);

                $("#orgFamily_table").DataTable().destroy();
                var data = response.extendList;

                v_cusMonitorModel.$data.orgFamilyList = data;
                v_cusMonitorModel.$nextTick(function () {
                    bindSimpleDataTable("orgFamily_table", commonPageNum.cusMonitorOrgFamily);
                });

                if (data && data.length > 0) {
                    var subnodes = [];
                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].uuid;
                        var name = data[i].orgEntity;
                        var found = false;
                        var mid = "com_" + tid;

                        var subnode = {
                            id: mid,
                            type: nodeType.company,
                            category: 3,
                            level: level + 1,
                            uuid: tid,
                            indexId: -1
                        };

                        // 处理node
                        for (var j = 0; j < myOrgChart._option.series[0].nodes.length; j++) {
                            if ("com_" + tid == myOrgChart._option.series[0].nodes[j].name) {
                                found = true;
                                myOrgChart._option.series[0].nodes[j].category = 3;
                                break;
                            }
                        }
                        if (!found) {
                            myOrgChart._option.series[0].nodes.push({ // 添加机构监控 -- 机构族谱 -- 二级企业
                                name: mid,
                                type: nodeType.company,
                                symbol: symbolType.company,
                                category: 3,
                                text: name,
                                fromNum: 0,
                                toNum: 0,
                                label: formatLabel(name),
                                value: valueStep * 2,
                                level: level + 1,
                                uuid: tid
                            });
                            subnode.indexId = myOrgChart._option.series[0].nodes.length - 1;
                        }
                        subnodes.push(subnode);
                        // // 处理link
                        // var lfound = false;
                        // for (var j = 0; j < myOrgChart._option.series[0].links.length; j++) {
                        //     var link = myOrgChart._option.series[0].links[j];
                        //     if ("com_" + tid == link.target && sid == link.source) {
                        //         lfound = true;
                        //         break;
                        //     }
                        // }
                        // if (!lfound) {
                        //     var we = Math.random() * 50 + 1;
                        //     myOrgChart._option.series[0].links.push({
                        //         source: sid,
                        //         target: "com_" + tid,
                        //         text: "", // we, // "机构族谱",
                        //         //           weight: we
                        //     });
                        // }
                    }
                    refresh();
                    getMutiComExtendNode(subnodes);
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

// 获取所有机构关联企业的投资企业
function getMutiComExtendNode(comList) {
    for (var i = 0; i < comList.length; i++) {
        var item = comList[i];
        getOrgComInvestNode(item.indexId, item.id, item.type, item.category, item.level, item.uuid);
    }
}
// 机构--获取关联企业的投资企业（在此次设置企业的关联度）
function getOrgComInvestNode(sIndex, sid, type, cgy, level, uuid) {
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
                    // 处理投资企业node
                    var snode = myOrgChart._option.series[0].nodes[sIndex];
                    // var snode;
                    // for (var j = 0; j < myOrgChart._option.series[0].nodes.length; j++) {
                    //     var inode = myOrgChart._option.series[0].nodes[j];
                    //     if (sid == inode.name) {
                    //         snode=inode;
                    //         break;
                    //     }
                    // }

                    for (var i = 0; i < data.length; i++) {
                        var tid = data[i].iUuid; // 被投资企业
                        var name = data[i].name;
                        //var isNew=Math.random()>0.5;
                        var found = false;
                        // 处理被投资企业node
                        for (var j = 0; j < myOrgChart._option.series[0].nodes.length; j++) {
                            var iname = myOrgChart._option.series[0].nodes[j].name;
                            if ("com_" + tid == iname) {
                                found = true;
                                myOrgChart._option.series[0].nodes[j].toNum += 1;

                                if (myOrgChart._option.series[0].nodes[j].level == nodeLevel.node_3) { // 如果是三级节点，源点计数
                                    if (myOrgChart._option.series[0].nodes[j].toNum > 1) {
                                        myOrgChart._option.series[0].nodes[j].category = 4;
                                    }
                                    snode.fromNum += 1; // 只计算相关的三级企业
                                    snode.value += valueStep;
                                    // console.log("found",myOrgChart._option.series[0].nodes[j].toNum,myOrgChart._option.series[0].nodes[j].level);
                                    if (snode.fromNum > sliderMax) {
                                        resetSliderRange(snode.fromNum);
                                    } else {
                                        resetSliderRange(sliderMax);
                                    }
                                }

                                myOrgChart._option.series[0].links.push({
                                    source: sid,
                                    target: "com_" + tid,
                                    text: "", // "投资企业",
                                    sIndex: sIndex,
                                    tIndex: j
                                });
                                break;
                            }
                        }
                        if (!found) { // 投资企业
                            snode.fromNum += 1; // 只计算相关的三级企业
                            snode.value += valueStep;
                            if (snode.fromNum > sliderMax) {
                                resetSliderRange(snode.fromNum);
                            } else {
                                resetSliderRange(sliderMax);
                            }

                            myOrgChart._option.series[0].nodes.push({ // 添加机构监控 -- 关联企业的投资企业 -- 三级企业
                                name: "com_" + tid,
                                type: nodeType.company,
                                symbol: symbolType.company,
                                category: 2,
                                text: name,
                                fromNum: 0,
                                toNum: 1,
                                label: formatLabel(name),
                                value: valueStep,
                                level: level + 1,
                                uuid: tid
                            });
                            myOrgChart._option.series[0].links.push({
                                source: sid,
                                target: "com_" + tid,
                                text: "", // "投资企业",
                                sIndex: sIndex,
                                tIndex: myOrgChart._option.series[0].nodes.length - 1
                            });
                        }
                        // // 处理link
                        // var lfound = false;
                        // //console.log(tid, sid);
                        // for (var j = 0; j < myOrgChart._option.series[0].links.length; j++) {
                        //     var link = myOrgChart._option.series[0].links[j];
                        //     if ("com_" + tid == link.target && sid == link.source) {
                        //         lfound = true;
                        //         break;
                        //     }
                        // }
                        // if (!lfound) {
                        //     myOrgChart._option.series[0].links.push({
                        //         source: sid,
                        //         target: "com_" + tid,
                        //         text: "", // "投资企业",
                        //         //             weight: Math.random() * 50 + 1 // 20
                        //     });
                        // }
                    }
                    refresh();
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

function resetSliderRange(value) {
    $("#range_slider").slider({
        orientation: "vertical",
        range: true,
        max: value,
        min: 0,
        values: [0, value],
        slide: function (event, ui) {
            $("#range_slider .ui-slider-handle-min span").html(ui.values[0]);
            $("#range_slider .ui-slider-handle-max span").html(ui.values[1]);

            var max = ui.values[1], min = ui.values[0], nodes = myOrgChart._option.series[0].nodes;
            var tempList = [];
            for (var i = 1; i < nodes.length; i++) { // 对监控源不做判断,处理二级节点
                nodes[i].tempFrom = nodes[i].fromNum;
                nodes[i].tempTo = nodes[i].toNum;
                if (nodes[i].level == nodeLevel.node_2) {
                    if (nodes[i].tempFrom < min || nodes[i].tempFrom > max) {
                        tempList.push(nodes[i].name);
                        nodes[i].ignore = true;
                    } else {
                        nodes[i].ignore = false;
                    }
                } else {
                    nodes[i].ignore = false;
                }
            }

            for (var iN = 0; iN < tempList.length; iN++) {
                var name = tempList[iN];
                for (var ilink = 0; ilink < myOrgChart._option.series[0].links.length; ilink++) {
                    var link = myOrgChart._option.series[0].links[ilink];
                    if (name == link.source) {
                        var tname = link.target;
                        for (var i = 1; i < nodes.length; i++) { // 处理三级节点
                            var tnode = nodes[i];
                            if (tname == tnode.name) {
                                if (tnode.level == nodeLevel.node_3) {
                                    tnode.tempTo -= 1;
                                    if (tnode.tempTo <= 0) {
                                        tnode.ignore = true;
                                    } else {
                                        tnode.ignore = false;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            myOrgChart.refresh();
        }
    });
    $("#range_slider .ui-slider-handle-min span").html(0);
    $("#range_slider .ui-slider-handle-max span").html(value);
    for (var i = 0; i < myOrgChart._option.series[0].nodes.length; i++) {
        var cnode = myOrgChart._option.series[0].nodes[i];
        if (cnode.level == nodeLevel.node_2) {
            cnode.value = 6 + 10 * cnode.fromNum / value;
        }
    }
    sliderMax = value;
}

// 复位图谱
function resetChart() {
    var nodes = myOrgChart._option.series[0].nodes;
    for (var i = 1; i < nodes.length; i++) {
        var node = nodes[i];
        node.ignore = false;
        if (node.level == nodeLevel.node_2) {
            node.category = 3;
        } else if (node.level == nodeLevel.node_3) {
            if (node.toNum > 1) {
                node.category = 4;
            } else {
                node.category = 2;
            }
        }
    }
    $("#range_slider .ui-slider-handle-min span").html(0);
    $("#range_slider .ui-slider-handle-max span").html(sliderMax);
    $("#range_slider").slider("values", [0, sliderMax]);

    myOrgChart._option.legend.data=['关联经营实体','关联经营实体对外投资企业','关键企业'];
    myOrgChart.refresh();
}

function printOption(value) {
    var nodes = myOrgChart._option.series[0].nodes;
    for (var i = 1; i < nodes.length; i++) {
        var node = nodes[i];
        if (node.level == value) {
            console.log(i, node.category);
        }
    }
}


