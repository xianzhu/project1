/**
 * Created by a88u on 2016/10/30.
 */

resize();


$(window).resize(function(){
    resize();
});

function resize(){
    var cheight=$(window).height()-200;
    if(cheight<100){
        cheight=100;
    }
    console.log(cheight);
    $("#inventForceChart").css("height",cheight+"px");
}

    var monitorType={
    company:1,
    org:2
};

var v_userMonitorModel = new Vue({
    el: "#v-userMonitorModel",
    data: {
        monitorList:[],
        currentMonitorSelect:0, // 当前监控项选择
        monitorTypeSelect:0, // 当前监控类型选择

        investCompany:[], // 投资企业
        //familyOrg:[],
        //focusCompany:[],
        //ivsCapitalList:[],
        //extCapitalList:[],

        orgInvestList:[], // 投资事件
        orgFamilyList:[], // 关联族谱

        entLawList:[], // 法务 ent_law
        abnormalItemList:[], // 经营异常	ent_abnormal_item
        equityList:[],// 股权质押	ent_equity
        mortgagesList:[], //动产抵押	ent_mortgages
        changeList:[], // 变更
        entFinanceList:[], // 企业融资情况
        currentSelect:0
    },
    ready: function () {
    },
    methods: {
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        },
        checkShowFilter:function(value){
            return value==this.currentSelect;
        },
        gotoCompany:function(id){
            var params;
            gotoCompanybyId(id);
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
            var result="";
            if(item.type==monitorType.company){
                result = "企业--"+item.content;
            }else if(item.type==monitorType.org){
                result = "机构--"+item.content;
            }
            return result;
        },
        formatStringFilter:function(value){
            var result="--";
            if(value&&value.toLowerCase()!="null"){
                result=value;
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

var symbolType={
    company: "circle",
    org:"rectangle"
};

getMonitorList();

require.config({
    paths: {
        echarts: 'js/plugins/echarts2'
    }
});

require(['echarts', 'echarts/chart/force'], requireCallback);

function requireCallback(ec) {
    echarts = ec;
}

function focus(param) {
    //console.log(param);
    var data = param.data;
    console.log(data);
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

function getMonitorList(){
    $.ajax({
        url: commonUrls.userMonitorUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            object:5,
            uid:v_userModel.$data.uname
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

                var result=[];
                for(var i=0;i<res.monitorList.length;i++){
                    var item=res.monitorList[i];
                    //console.log(item);
                    if(item.type==monitorType.company){ // 企业
                        result.push({index:result.length,content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.uuid});
                    }else if(item.type==monitorType.org){ // 机构
                        result.push({index:result.length,content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.orgId});
                    }
                }

                v_userMonitorModel.$data.monitorList=result;

                v_userMonitorModel.$nextTick(function(){
                    if(result.length>0){
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
            404: function() {
                goTo404();
            },
            500:function(){
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

        if (type == nodeType.company) {
            console.log(v_userMonitorModel.$data.monitorTypeSelect);
            if(level==0||(v_userMonitorModel.$data.monitorTypeSelect==2&&level==1)) {
                var uuid = selectNode.uuid;
                reSetOptionNodes("com_" + uuid);

                getCompanyExtendNode(id, type, cgy, level, uuid);
                getCompanyInfo(uuid); // 联动
            }else{
                showInfo("提示","不支持更深一级扩展！");
            }
        }
        else if (type == nodeType.org) {
            var oid = selectNode.oid;
            reSetOptionNodes("org_" + oid);

            getOrgExtendNode(id, type, cgy, level, oid);
            getOrgInfo(oid);
        }
    } else { // 初始，获取监控内容起始点
        console.log("init monitor point")
    }
}

// 选择监控点之后更新监控点信息
function updateData(item) {
    var name=item.mid;
    var text=item.content;

    if(item.type==monitorType.company) {
        myoption=clone(relation_company_option);
        v_userMonitorModel.$data.monitorTypeSelect=1;

        myoption.series[0].nodes.push({
            category: 0,
            name: "com_" + item.mid,
            symbol: symbolType.company,
            value: 15,
            type: nodeType.company,
            text: item.content,
            level: 0,
            uuid: item.mid
        });

        getCompanyInfo(item.mid);
    }else if(item.type==monitorType.org){
        myoption=clone(relation_org_option);
        v_userMonitorModel.$data.monitorTypeSelect=2;
        //v_userMonitorModel.$data.currentSelect=2;
        myoption.series[0].nodes.push({
            category: 0,
            name: "org_" + item.mid,
            symbol: symbolType.org,
            value: 14,
            type: nodeType.org,
            text: item.content,
            level: 0,
            oid: item.mid
        });
        getOrgInfo(item.mid);
    }

    // 增加支点
    myoption.series[0].nodes.push({
        category: 4,
        name: "com_random",
        //symbol: symbolType.company,
        value: 0,
        type: nodeType.company,
        text: "",
        level: 0,
        uuid: ""
    });
    setTimeout(refresh, 1000);
}

function refresh() {
    console.log("refresh");

    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    var dom = document.getElementById("inventForceChart");
    myChart = echarts.init(dom);

    myChart.setOption(myoption, true);

    myChart.on("click", focus);
}


$(window).resize(function(){
    setTimeout(refresh, 1000);
});

function reSetOptionNodes(id) {
    console.log(id);
    for (var i = 0; i < myoption.series[0].nodes.length; i++) {
        var node = myoption.series[0].nodes[i];
        if(node.category==0){ // 如果是监控源，保持不变
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
function changeMonitorSelection(){
    v_userMonitorModel.$data.currentMonitorSelect=$("#currentMonitorSelect").val();
    var item=v_userMonitorModel.$data.monitorList[v_userMonitorModel.$data.currentMonitorSelect];
    v_userMonitorModel.$data.monitorTypeSelect=item.type;
    console.log(v_userMonitorModel.$data.monitorTypeSelect);
    updateData(item);
}



// 获取企业表信息--变更、法务、经营异常、股权质押、动产抵押，融资情况
function getCompanyInfo(id){
    if(v_userMonitorModel.$data.monitorTypeSelect==2){
        v_userMonitorModel.$data.currentSelect=3;
    }else{
        v_userMonitorModel.$data.currentSelect=1;
    }
    console.log(v_userMonitorModel.$data.monitorTypeSelect,', ',v_userMonitorModel.$data.currentSelect);
    getCompanyReportInfo(id);
    getCompanyBasicInfo(id);
    getCompanyFinanceInfo(id);
}
// 法务、经营异常、股权质押、动产抵押
function getCompanyReportInfo(id){
    $.ajax({
        url: commonUrls.monitorEntCreditUrl,
        type: "POST",
        data: {
            id:id
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

                var entLawInfos=[];
                if(response.entLawInfos&&response.entLawInfos.length>0){
                    entLawInfos.push(response.entLawInfos[0]);
                }

                var entAbnormalItemInfos=[];
                if(response.entAbnormalItemInfos&&response.entAbnormalItemInfos.length>0){
                    entAbnormalItemInfos.push(response.entAbnormalItemInfos[0]);
                }

                var entEquityInfos=[];
                if(response.entEquityInfos&&response.entEquityInfos.length>0){
                    entEquityInfos.push(response.entEquityInfos[0]);
                }

                var entMortgagesInfos=[];
                if(response.entMortgagesInfos&&response.entMortgagesInfos.length>0){
                    entMortgagesInfos.push(response.entMortgagesInfos[0]);
                }
                v_userMonitorModel.$data.entLawList=entLawInfos;
                v_userMonitorModel.$data.abnormalItemList=entAbnormalItemInfos;
                v_userMonitorModel.$data.equityList=entEquityInfos;
                v_userMonitorModel.$data.mortgagesList=entMortgagesInfos;
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
// 变更
function getCompanyBasicInfo(id){
    $.ajax({
        url: commonUrls.monitorEntBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:id
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=="success"){
                var response = res;
                var entChgRecordInfos=[];
                if(response.entChgRecordInfos&&response.entChgRecordInfos.length>0){
                    entChgRecordInfos.push(res.entChgRecordInfos[0]);
                }
                v_userMonitorModel.$data.changeList=entChgRecordInfos;
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
// 企业融资情况
function getCompanyFinanceInfo(id){
    $.ajax({
        url: commonUrls.monitorEntFinanceUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:id
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=="success"){
                var response = res;
                $("#finance_table").DataTable().destroy();
                //var entFinanceInfos=[];
                //if(response.entFinancalList&&response.entFinancalList.length>0){
                //    entFinanceInfos.push(res.entFinancalList[0]);
                //}
                v_userMonitorModel.$data.entFinanceList=response.entFinancalList;
                v_userMonitorModel.$nextTick(function () {
                    bindExportedDataTable("finance_table",5,"企业融资情况",{});
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

// 获取机构表信息--投资事件
function getOrgInfo(id){
    v_userMonitorModel.$data.currentSelect=2;
    $.ajax({
        url: commonUrls.monitorOrgCaptialUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:id
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
                $("#orgInvest_table").DataTable().destroy();
                v_userMonitorModel.$data.orgInvestList=res.inventList;
                v_userMonitorModel.$nextTick(function () {
                    bindExportedDataTable("orgInvest_table",10,"机构投资事件",{});
                })
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

// 获取投资企业点
function getCompanyExtendNode(sid, type, cgy, level, uuid) {
    console.log("getCompanyExtendNode:", sid, type, cgy, level, uuid);
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
                console.log("failure",res.message);
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                console.log(response);
//response.entInvestInfos: iUuid,name
                $("#invest_table").DataTable().destroy();
                var data = response.entInvestInfos;
                v_userMonitorModel.$data.investCompany=data;
                v_userMonitorModel.$nextTick(function(){
                    bindExportedDataTable("invest_table",10,"投资企业",{});
                });
                if(data&&data.length>0) {
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
                        if (!found) {
                            myoption.series[0].nodes.push({
                                name: "com_" + tid,
                                symbol: symbolType.company,
                                type: nodeType.company,
                                category: 3,
                                text: name,
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
                                text: "投资企业",
                                weight: 20
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
    console.log("getOrgExtendNode:", id, type, cgy, level, oid);
    //v_userMonitorModel.$data.currentSelect=2;
    if(level==0){ // 机构族谱预测
        getOrgFamilyNode(sid,type,cgy,level,oid);
    }
}

function getOrgFamilyNode(sid, type, cgy, level, oid) {
    console.log("getOrgExtendNode:", id, type, cgy, level, oid);

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
                console.log("failure",res.message);
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                console.log(response);
                $("#orgFamily_table").DataTable().destroy();
                    var data = response.extendList;
                    v_userMonitorModel.$data.orgFamilyList=data;
                    v_userMonitorModel.$nextTick(function(){
                        bindExportedDataTable("orgFamily_table",8,"关联族谱",{});
                    });
                    if (data && data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            var tid = data[i].uuid;
                            var name = data[i].orgEntity;
                            var found = false;
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
                                    name: "com_" + tid,
                                    type: nodeType.company,
                                    symbol: symbolType.company,
                                    category: 3,
                                    text: name,
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
                                myoption.series[0].links.push({source: sid, target: "com_" + tid,text:"机构族谱", weight: 30});
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

