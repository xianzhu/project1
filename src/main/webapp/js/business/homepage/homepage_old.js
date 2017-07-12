/**
 * Created by a88u on 2017/6/19.
 */
menuList.homepage.isActive = true;

var EventType = {
    invest: "invest",
    exit: "exit"
}

var v_homepageModel = new Vue({
    el: "#v-homepageModel",
    data: {
        statOrgData: {total: 12345, current: 345},
        statEventData: {total: 23345, current: 645},
        statMergeData: {total: 35345, current: 322},
        statFundData: {total: 52345, current: 845},

        projDashData: {total: 0, current: 0},
        companyDashData: {total: 0, current: 0},
        reportDashData: {total: 0, current: 0},
        elasticDashData: {total: 0, current: 0},

        newsList: [], // 新闻列表
        eventPage: 0, // 事件页
        eventEnd: false,
        eventList: [] // 事件列表
    },
    methods: {
        openNews: function (url) {
            sendMonitor({url: url});
        },
        eventPageControlFilter: function (value) {
            if (value == 0) {
                return this.$data.eventPage != 0;
            } else {
                return !this.$data.eventEnd;
            }
        },
        changeEventPage: function (value) {
            if (value == 0) {
                this.$data.eventPage--;
            } else {
                this.$data.eventPage++;
            }
        },
        getEventDetail: function (title, type,ptype) {
            getEventByTitle(title, type,ptype);
        }
    },
    filters: {
        getNewsShortStrFilter: function (value) {
            if (value && value.toLowerCase() != "null") {
                return getSubString(value, 50);
            } else {
                return "";
            }
        },
        currentEventPageFilter: function (value) {
            return '第' + (value + 1) + '页';
        }

    }
});

getNewsList();
getRptData();
getPanelData();
getDashboardData();
getEventBarData();
getEventSubpage("");

var calendar;
$(document).ready(function () {
    setCalendar();
});
// 新闻
function getNewsList() {
    $.ajax({
        url: commonUrls.homeNewsUrl,
        type: "get",
        data: {
            from: 0,
            count: commonPageNum.homeNewsList
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure:", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
            } else if (res.status == "success") {
                var response = res;
                v_homepageModel.$data.newsList = [];
                // v_homepageModel.$data.newsList=response.list;
                for (var i = 0; i < response.list.length && i < commonPageNum.homeNewsList; i++) {
                    v_homepageModel.$data.newsList.push(response.list[i]);
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

// 日历
function setCalendar() {
    var nw = $("#homemodule_news_ibox").width(), nh = getCalendarHeight(nw);
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth();
    // console.log(nw, nh);
    $.ajax({
        url: commonUrls.homeCalendarUrl,
        type: "get",
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure:", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
            } else if (res.status == "success") {
                var response = res;
                var calendarList = response.list;
                $('#calendar').calendar({
                    width: nw,
                    height: nh,
                    data: calendarList,
                    onSelected: function (view, date, data) {
                        if (data) {
                            showCalendarInfo(date.format('yyyy-mm-dd'), data);
                        }
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
function showCalendarInfo(header, data) {
    var dom = $("#mask_modal_body");
    var cdata = data.replace(/\n/g, '<br>');

    dom.html(cdata);
    modal_mask_info.$data.header = header;
    modal_mask_info.$data.showModal = true;
}

var modal_event_info = initModelEventVue();

function getCalendarHeight(value) {
    var nh, vh = value * 0.618;
    if (vh < 168) {
        nh = 168;
    } else if (vh < 200) {
        nh = vh;
    } else {
        nh = 200;
    }
    return nh;
}

function initModelEventVue() {
    v_model_event_info = new Vue({
        el: "#v-model-event-info",
        data: {
            showModal: false,
            isInvest: true,
            isExit: false,
            title: "", // 标题
            entName: "", // 标的公司
            type: "", // 类型（轮次）
            desc: "", // 简介
            iList: [], // 投资相关方信息
            eList: [] // 退出相关方信息
        },
        methods: {
            closeEventDetail: function () {
                this.$data.showModal = false;
            }
        },
        filters: {
            formatEmptyFilter: function (value) {
                var result = "--";
                if (value && value != "") {
                    result = value;
                }
                return result;
            }
        }
    });
    return v_model_event_info;
}
// 一级市场投资者信心指数--
function getRptData() {
    $.ajax({
        url: commonUrls.homepageRptDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                showRptEcharts(response.tendsList, "orgInvestChart");
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
// 统计数据
function getPanelData() {
    $.ajax({
        url: commonUrls.homepagePanelDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                var orgPanelData={legendData:[],orgData:[]},
                    eventPanelData={durData:[],exitData:[],investData:[],sum:0},
                    mergePanelData={durData:[],proTransfer:[],sharePurchase:[],capIncrease:[],investment:[]},
                    fundPanelData={durData:[],stockFund:[],startFund:[]};

                for(var i=0;i<response.trend.length;i++){
                    var item=response.trend[i];
                    if(i==0){
                        orgPanelData.legendData.push("私募");
                        orgPanelData.legendData.push("券商");
                        orgPanelData.legendData.push("资管");
                        orgPanelData.legendData.push("信托");
                        orgPanelData.legendData.push("银行");
                        orgPanelData.legendData.push("保险");

                        orgPanelData.orgData.push(item.pe);
                        orgPanelData.orgData.push(item.broker);
                        orgPanelData.orgData.push(item.am);
                        orgPanelData.orgData.push(item.trust);
                        orgPanelData.orgData.push(item.bank);
                        orgPanelData.orgData.push(item.insurance);
                    }

                    eventPanelData.durData.unshift(item.statDate);
                    eventPanelData.exitData.unshift(-item.exitEvents);
                    eventPanelData.investData.unshift(item.investEvents);
                    if(eventPanelData.sum<item.exitEvents+item.investEvents){
                        eventPanelData.sum=item.exitEvents+item.investEvents;
                    }

                    mergePanelData.durData.unshift(item.statDate);
                    mergePanelData.proTransfer.unshift(item.proTransfer);
                    mergePanelData.sharePurchase.unshift(item.sharePurchase);
                    mergePanelData.capIncrease.unshift(item.capIncrease);
                    mergePanelData.investment.unshift(item.investment);

                    fundPanelData.durData.unshift(item.statDate);
                    fundPanelData.stockFund.unshift(item.stockFund);
                    fundPanelData.startFund.unshift(item.startFund);
                }

                showOrgPanelEcharts(orgPanelData, "org_panel_Chart"); // 机构调研
                showEventPanelEcharts(eventPanelData, "event_panel_Chart"); // 投退事件
                showMergePanelEcharts(mergePanelData, "merge_panel_Chart"); // 并购事件
                showFundPanelEcharts(fundPanelData, "fund_panel_Chart"); // 基金备案
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
// 仪表盘数据
function getDashboardData() {
    $.ajax({
        url: commonUrls.homepageDashDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;

                var dashP={count:0,statMax:0,statMin:0,statMedia:0},
                    dashC={count:0,statMax:0,statMin:0,statMedia:0},
                    dashR={count:0,statMax:0,statMin:0,statMedia:0},
                    dashE={count:0,statMax:0,statMin:0,statMedia:0};
                for(var i=0;i<response.count.length;i++) {
                    var item = response.count[i];
                    if (item.name == "企业") {
                        dashC = item;
                        v_homepageModel.$data.companyDashData = {
                            current: item.count, total: item.statMax
                        };
                    } else if (item.name == "报告") {
                        dashR = item;
                        v_homepageModel.$data.reportDashData = {
                            current: item.count, total: item.statMax
                        };
                    } else if (item.name == "情报") {
                        dashE = item;
                        v_homepageModel.$data.elasticDashData = {
                            current: item.count, total: item.statMax
                        };
                    } else if (item.name == "项目") {
                        dashP = item;
                        v_homepageModel.$data.projDashData = {
                            current: item.count, total: item.statMax
                        };
                    }
                }

                showProjDashEcharts(dashP, "project_dashboard_chart");
                showCompanyDashEcharts(dashC, "company_dashboard_chart");
                showReportDashEcharts(dashR, "report_dashboard_chart");
                showElasticDashEcharts(dashE, "elastic_dashboard_chart");
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
// 今日事件饼图、柱状图
function getEventBarData() { // 今日资本事件柱状图、饼图
    $.ajax({
        url: commonUrls.homepageBarDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                var pieData={eventOneData:[],eventTwoData:[],color:[]},
                    barData={durData:[],invEarly:[],invMiddle:[],invLate:[],invOther:[],exitOne:[],exitTwo:[]};
                for(var i=0;i<response.event1.length;i++){
                    var item=response.event1[i];
                    if(i==0){
                        if(item.investEarly!=0){
                            pieData.color.push('rgba(120,164,135,1)');
                            pieData.eventOneData.push({name:"早期投资",value:item.investEarly,type:"invest",
                                itemStyle:{normal:{color:'rgba(120,164,135,1)'}}
                            });
                        }
                        if(item.investMiddle!=0){
                            pieData.color.push('rgba(255,127,80,1)');
                            pieData.eventOneData.push({name:"中期投资",value:item.investMiddle,type:"invest",
                                itemStyle:{normal:{color:'rgba(255,127,80,1)'}}
                            });
                        }
                        if(item.investLate!=0){
                            pieData.color.push('rgba(135,206,250,1)');
                            pieData.eventOneData.push({name:"后期投资",value:item.investLate,type:"invest",
                                itemStyle:{normal:{color:'rgba(135,206,250,1)'}}
                            });
                        }
                        if(item.investOther!=0){
                            pieData.color.push('rgba(218,112,214,1)');
                            pieData.eventOneData.push({name:"其他投资",value:item.investOther,type:"invest",
                                itemStyle:{normal:{color:'rgba(218,112,214,1)'}}
                            });
                        }
                        if(item.exitOne!=0){
                            pieData.color.push('rgba(50,205,50,1)');
                            pieData.eventOneData.push({name:"一级市场退出",value:item.exitOne,type:"exit",
                                itemStyle:{normal:{color:'rgba(50,205,50,1)'}}
                            });
                        }
                        if(item.exitTwo!=0){
                            pieData.color.push('rgba(100,149,237,1)');
                            pieData.eventOneData.push({name:"二级市场退出",value:item.exitTwo,type:"exit",
                                itemStyle:{normal:{color:'rgba(100,149,237,1)'}}
                            });
                        }
                    }

                    barData.durData.unshift(item.countDate);
                    barData.invEarly.unshift(item.investEarly);
                    barData.invMiddle.unshift(item.investMiddle);
                    barData.invLate.unshift(item.investLate);
                    barData.invOther.unshift(item.investOther);
                    barData.exitOne.unshift(item.exitOne);
                    barData.exitTwo.unshift(item.exitTwo);
                }

                for(var j=0;j<response.event2.length;j++){
                    var item=response.event2[j];
                    pieData.eventTwoData.push({name:item.typeName,value:item.count,type:item.typeName});
                }
                showEventPieEcharts(pieData, "event_pie_charts");
                showEventBarEcharts(barData, "event_bar_charts");
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
// 今日事件列表
function getEventSubpage(type) {
    $.ajax({
        url: commonUrls.homeEventpageUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            from: v_homepageModel.$data.eventPage * commonPageNum.homeEventList,
            type: type,
            count: commonPageNum.homeEventList
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
                var elist = [];
                if (response.investEventList) {
                    console.log("get invest event list");
                    if (response.investEventList && response.investEventList.length > 0) {
                        for (var i = 0; i < response.investEventList.length && i < commonPageNum.homeEventList; i++) {
                            var item = response.investEventList[i];
                            elist.push({
                                eventClass: item.investType,
                                eventTitle: item.eventTitle,
                                entCnName: item.entCnName,
                                eventType:"invest"
                            });
                        }
                    }
                } else {
                    console.log("get exit event list");
                    if (response.exitEventList && response.exitEventList.length > 0) {
                        for (var i = 0; i < response.exitEventList.length && i < commonPageNum.homeEventList; i++) {
                            var item = response.exitEventList[i];
                            elist.push({
                                eventClass: item.exitType,
                                eventTitle: item.eventTitle,
                                entCnName: item.entCnName,
                                eventType:"exit"
                            });
                        }
                    }
                }

                v_homepageModel.$data.eventList = elist;
                if (v_homepageModel.$data.eventList && v_homepageModel.$data.eventList.length == commonPageNum.homeEventList) {
                    v_homepageModel.$data.eventEnd = false;
                } else {
                    v_homepageModel.$data.eventEnd = true;
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

// 查看今日资本事件详情
function getEventByTitle(title, type,ptype) {
    console.log(title, type);
    title = "每日优鲜获注资"; // for test
    $.ajax({
        url: commonUrls.homeEventDetailUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            event: title,
            type: type // invest exit
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == "success") {
                if (res.eventsList.length > 0) {
                    var eItem = res.eventsList[0];
                    modal_event_info.$data.title = eItem.eventTitle; // 标题
                    modal_event_info.$data.entName = eItem.entCnName; // 标的公司
                    modal_event_info.$data.type = eItem.eventType; // 类型（轮次）
                    modal_event_info.$data.desc = eItem.eventDesc; // 简介
                } else { // 没有对应的内容，不显示（数据不同步或数据错误，暂定不显示）
                    return;
                }
                if (ptype == EventType.invest) {
                    modal_event_info.$data.isInvest = true;
                    modal_event_info.$data.isExit = false;
                    $("#invest_data_table").DataTable().destroy();
                    modal_event_info.$data.iList = res.eventsList;
                    modal_event_info.$nextTick(function () {
                        bindSimpleDataTable("invest_data_table", commonPageNum.homepageInvest);
                        $("#v-model-mask-info").css("display", "block");
                    });
                } else { // EventType.exit
                    modal_event_info.$data.isInvest = false;
                    modal_event_info.$data.isExit = true;
                    $("#exit_data_table").DataTable().destroy();
                    modal_event_info.$data.eList = res.eventsList;
                    modal_event_info.$nextTick(function () {
                        console.log(".......");
                        bindSimpleDataTable("exit_data_table", commonPageNum.homepageExit);
                        $("#v-model-mask-info").css("display", "block");
                    });
                }
                modal_event_info.$data.showModal = true;
            }
        },
        fail: function (status) {
            console.error("error. status=", status);
        },
        statusCode: {
            /*            404: function() {
             goTo404();
             },
             500:function(){
             goTo500();
             }*/
        }
    });

}

function resizeDetailMask() {
    var height = $(window).height();
    var mheight = height - 121;
    // console.log(height, mheight);
    $(".modal-event-body").css('maxHeight', mheight);
}

$(document).ready(function () {
    resizeDetailMask();
});

$(window).resize(function () {
    var nw = $("#homemodule_news_ibox").width();
    var nh = getCalendarHeight(nw);
    console.log("resize:", nw, nh);
    $('#calendar').calendar('autoResize', nw, nh);
    resizeDetailMask();
});











