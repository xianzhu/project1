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
        projDashData: {total: 0, current: 0},
        companyDashData: {total: 0, current: 0},
        reportDashData: {total: 0, current: 0},
        elasticDashData: {total: 0, current: 0},

        newsList: [] // 新闻列表
    },
    methods: {
        openNews: function (url) {
            sendMonitor({url: url});
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
        toThousandsFormat:function (value) {
            return toThousandsFormat(value);
        }
    }
});

getNewsList();
getRptData();
getPanelData();
getDashboardData();
getEventBarData();

var calendar;
$(document).ready(function () {
    setCalendar();
    resizeDetailMask();
    resizeLeftSide(true);
});

// 今日资本事件列表
var modal_event_list = initModelEventListVue();
function initModelEventListVue() {
    v_model_event_list = new Vue({
        el: "#v-model-event-list",
        data: {
            showModal: false,
            type: "", // 类型（轮次）
            eventList:[],
            dataEmpty:true,
            eventPage: 0, // 事件页
            eventEnd: false,
            eventType:'' // 当前选中事件类型
        },
        methods: {
            closeEventList: function () {
                this.$data.showModal = false;
                resetEventMixChart();
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
                getEventSubpage(this.$data.eventType);
            },
            getEventDetail: function (element) {
                getEventByTitle(element);
            }
        },
        filters: {
            formatEmptyFilter: function (value) {
                var result = "--";
                if (value && value != "") {
                    result = value;
                }
                return result;
            },
            currentEventPageFilter: function (value) {
                return '第' + (value + 1) + '页';
            },
        }
    });
    return v_model_event_list;
}
// 今日资本事件详情
var modal_event_info = initModelEventDetail();
function initModelEventDetail() {
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
                modal_event_list.$data.showModal =true;
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

// 新闻
function getNewsList() {
    // console.log("getNewsList");
    $.ajax({
        url: commonUrls.homeNewsUrl,
        type: "get",
        data: {
            from: 0,
            count: commonPageNum.homeNewsList-1
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure:", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == "success") {
                var response = res;
                v_homepageModel.$data.newsList = [];
                // v_homepageModel.$data.newsList=response.list;
                for (var i = 0; i < response.list.length && i < commonPageNum.homeNewsList; i++) {
                    v_homepageModel.$data.newsList.push(response.list[i]);
                }
                v_homepageModel.$nextTick(function () {
                    resizeLeftSide(false);
                    setTimeout(function () {
                        allChartResize();
                    }, 1000);
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
// 一级市场投资者信心指数--
function getRptData() {
    // console.log("getRptData");
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
                showRptEcharts(response.trend, "orgInvestChart");
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
    // console.log("getPanelData");
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
                var orgPanelData = {legendData: [], orgData: []},
                    eventPanelData = {durData: [], exitData: [], investData: [], sum: 0},
                    mergePanelData = {durData: [], proTransfer: [], sharePurchase: [], capIncrease: [], investment: []},
                    fundPanelData = {durData: [], stockFund: [], startFund: []};

                for (var i = 0; i < response.trend.length; i++) {
                    var item = response.trend[i];
                    if (i == 0) {
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
                    if (eventPanelData.sum < item.exitEvents + item.investEvents) {
                        eventPanelData.sum = item.exitEvents + item.investEvents;
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
    // console.log("get DashboardData");
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

                var dashP = {count: 0, statMax: 0, statMin: 0, statMedia: 0},
                    dashC = {count: 0, statMax: 0, statMin: 0, statMedia: 0},
                    dashR = {count: 0, statMax: 0, statMin: 0, statMedia: 0},
                    dashE = {count: 0, statMax: 0, statMin: 0, statMedia: 0};
                for (var i = 0; i < response.count.length; i++) {
                    var item = response.count[i];
                    if (item.name == "企业") {
                        dashC = item;
                        v_homepageModel.$data.companyDashData = {
                            current: item.count, total: item.total
                        };
                    } else if (item.name == "报告") {
                        dashR = item;
                        v_homepageModel.$data.reportDashData = {
                            current: item.count, total: item.total
                        };
                    } else if (item.name == "情报") {
                        dashE = item;
                        v_homepageModel.$data.elasticDashData = {
                            current: item.count, total: item.total
                        };
                    } else if (item.name == "项目") {
                        dashP = item;
                        v_homepageModel.$data.projDashData = {
                            current: item.count, total: item.total
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
    // console.log("getEventBarData");
    var colors = [
        '#99cccc',
        '#6699cc',
        '#669999',
        '#669966',
        '#99cc99',
        '#666699',
        '#9966cc',
        '#996699',

        // 'rgba(141, 111, 108, 1)',  //  '#6f5553',
        // 'rgba(246,165,81,1)',
        // 'rgba(39, 114, 123, 1)', // '#27727B',
        // 'rgba(82,142,149,1)' , //   '#7fafd4',
        // 'rgba(127, 175, 212, 1)', // '#e2bbff',
        // 'rgba(136, 166, 86, 1)', //  '#7eb00a',
    ];
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
                var pieData = {eventOneData: [], eventTwoData: [], color: []},
                    barData = {
                        durData: [],
                        invEarly: [],
                        invMiddle: [],
                        invLate: [],
                        invOther: [],
                        exitOne: [],
                        exitTwo: []
                    };
                for (var i = 0; i < response.event1.length; i++) {
                    var item = response.event1[i];
                    if (i == 0) {
                        if (item.exitOne != 0) {
                            pieData.color.push('rgba(100,149,237,1)');
                            pieData.eventOneData.push({
                                name: "一级市场退出", value: item.exitOne, type: "exit",
                                itemStyle: {normal: {color: 'rgba(100,149,237,1)'}}
                            });
                        }
                        if (item.exitTwo != 0) {
                            pieData.color.push('rgba(50,205,50,1)');
                            pieData.eventOneData.push({
                                name: "二级市场退出", value: item.exitTwo, type: "exit",
                                itemStyle: {normal: {color: 'rgba(50,205,50,1)'}}
                            });
                        }
                        if (item.investEarly != 0) {
                            pieData.color.push('rgba(120,164,135,1)');
                            pieData.eventOneData.push({
                                name: "早期投资", value: item.investEarly, type: "invest",
                                itemStyle: {normal: {color: 'rgba(120,164,135,1)'}}
                            });
                        }
                        if (item.investMiddle != 0) {
                            pieData.color.push('rgba(255,127,80,1)');
                            pieData.eventOneData.push({
                                name: "中期投资", value: item.investMiddle, type: "invest",
                                itemStyle: {normal: {color: 'rgba(255,127,80,1)'}}
                            });
                        }
                        if (item.investLate != 0) {
                            pieData.color.push('rgba(135,206,250,1)');
                            pieData.eventOneData.push({
                                name: "后期投资", value: item.investLate, type: "invest",
                                itemStyle: {normal: {color: 'rgba(135,206,250,1)'}}
                            });
                        }
                        if (item.investOther != 0) {
                            pieData.color.push('rgba(218,112,214,1)');
                            pieData.eventOneData.push({
                                name: "其他投资", value: item.investOther, type: "invest",
                                itemStyle: {normal: {color: 'rgba(218,112,214,1)'}}
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

                for (var j = 0; j < response.event2.length; j++) {
                    var item = response.event2[j];
                    pieData.eventTwoData.push({
                        name: item.typeName, value: item.count, type: item.typeName,
                        itemStyle: {
                            normal: {
                                color: colors[j],
                                label: {textStyle: {color: colors[j]}},
                                labelLine: {lineStyle: {color: colors[j]}}
                            }
                        }
                    });
                }
                showEventMixEcharts(pieData, barData, "event_mix_charts");
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
    // console.log("getEventSubpage");
    $.ajax({
        url: commonUrls.homeEventpageUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            from: modal_event_list.$data.eventPage * commonPageNum.homeEventList,
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
                                happenDate:item.happenDate,
                                eventType: "invest"
                            });
                        }
                    }
                } else {
                    // console.log("get exit event list");
                    if (response.exitEventList && response.exitEventList.length > 0) {
                        for (var i = 0; i < response.exitEventList.length && i < commonPageNum.homeEventList; i++) {
                            var item = response.exitEventList[i];
                            elist.push({
                                eventClass: item.exitType,
                                eventTitle: item.eventTitle,
                                entCnName: item.entCnName,
                                happenDate:item.happenDate,
                                eventType: "exit"
                            });
                        }
                    }
                }
                modal_event_list.$data.eventList=elist;
                modal_event_list.$data.dataEmpty=elist.length<=0;
                modal_event_list.$data.eventEnd=elist.length<commonPageNum.homeEventList;
                // modal_event_list.$data.eventEnd=(elist.length==0);
                modal_event_list.$data.showModal=true;
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
function getEventByTitle(element) {
    var title=element.eventTitle,type=element.eventClass,ptype=element.eventType;
    // console.log("getEventByTitle");
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
                    modal_event_info.$data.title = element.eventTitle; // 标题
                    modal_event_info.$data.entName = element.entCnName; // 标的公司
                    modal_event_info.$data.type = element.eventType; // 类型（轮次）
                    modal_event_info.$data.desc = "--"; // 简介
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
                        bindSimpleDataTable("exit_data_table", commonPageNum.homepageExit);
                        $("#v-model-mask-info").css("display", "block");
                    });
                }
                modal_event_list.$data.showModal = false;
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

// 日历
function setCalendar() {
    var nw = $("#homemodule_news_ibox").width(), nh = getCalendarHeight(nw),
        winheight = $('body').height() - 60;
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth();
    // console.log("setCalendar", nw, nh, winheight);
    $.ajax({
        url: commonUrls.homeCalendarUrl,
        type: "get",
        data: {
            date: '2017-01-01',
            to: '2017-12-31',
            count:1000
        },
        dataType: "json",
        success: function (res) {
            if (res.status == "failure") {
                console.log("failure:", res.message);
            } else if (res.status == "timeout") {
                console.log("timeout");
            } else if (res.status == "success") {
                var response = res;
                var calendarList = [], calendarobj = {};

                for (var i = 0; i < response.object.length; i++) {
                    var item = response.object[i];
                    // console.log(item);
                    if (typeof calendarobj[item.time] == 'undefined') {
                        calendarobj[item.time] = {
                            date: item.time, value: [{
                                category: item.category,
                                comment: item.comment,
                                geo: item.geo,
                                name: item.name,
                                orgCnShort: item.orgCnShort,
                                type: item.type,
                                url: item.url,
                                domId: "accordion1"
                            }]
                        };
                    } else {
                        var domid = "accordion" + calendarobj[item.time].value.length;
                        calendarobj[item.time].value.push({
                            category: item.category, comment: item.comment, geo: item.geo,
                            name: item.name, orgCnShort: item.orgCnShort, type: item.type, url: item.url, domId: domid
                        });
                    }
                }
                for (var key in calendarobj) {
                    var citem = calendarobj[key];
                    calendarList.push({date: citem.date, value: citem.value});
                }
                $('#calendar').calendar({
                    width: nw,
                    height: nh,
                    data: calendarList,
                    onSelected: function (view, date, data) {
                        console.log(data);
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
    // console.log("showCalendarInfo");
    var cListData = [];
    for (var key in data) {
        var kitem = data[key];
        cListData.push({
            domId: "#" + kitem.domId, domid: kitem.domId, category: kitem.category,
            comment: kitem.comment, geo: kitem.geo, name: kitem.name, orgCnShort: kitem.orgCnShort,
            type: kitem.type, url: kitem.url
        });
    }
// console.log(cListData);
    modal_mask_info.$data.header = header;
    modal_mask_info.$data.showModal = true;
    modal_mask_info.$data.information = cListData;
    modal_mask_info.$nextTick(function () {
        bindCalendarItem();
    });
}
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
function bindCalendarItem() {
    var d = document,
        accordionToggles = d.querySelectorAll('#mask_modal_body .js-accordionTrigger'),
        setAria,
        setAccordionAria,
        switchAccordion,
        touchSupported = ('ontouchstart' in window),
        pointerSupported = ('pointerdown' in window);
    console.log(accordionToggles);
    skipClickDelay = function (e) {
        e.preventDefault();
        e.target.click();
    }

    setAriaAttr = function (el, ariaType, newProperty) {
        el.setAttribute(ariaType, newProperty);
    };
    setAccordionAria = function (el1, el2, expanded) {
        switch (expanded) {
            case "true":
                setAriaAttr(el1, 'aria-expanded', 'true');
                setAriaAttr(el2, 'aria-hidden', 'false');
                break;
            case "false":
                setAriaAttr(el1, 'aria-expanded', 'false');
                setAriaAttr(el2, 'aria-hidden', 'true');
                break;
            default:
                break;
        }
    };
//function
    switchAccordion = function (e) {
        // console.log("triggered");
        e.preventDefault();
        closeAllAccordion(e);
        var thisAnswer = e.target.parentNode.nextElementSibling;
        var thisQuestion = e.target;
        if (thisAnswer.classList.contains('is-collapsed')) {
            setAccordionAria(thisQuestion, thisAnswer, 'true');
        } else {
            setAccordionAria(thisQuestion, thisAnswer, 'false');
        }
        thisQuestion.classList.toggle('is-collapsed');
        thisQuestion.classList.toggle('is-expanded');
        thisAnswer.classList.toggle('is-collapsed');
        thisAnswer.classList.toggle('is-expanded');

        thisAnswer.classList.toggle('animateIn');
    };

    closeAllAccordion = function (e) {
        var parent = e.target.parentNode.parentNode.parentNode;
        var dtTargets = parent.querySelectorAll('dt a');
        for (var i = 0, len = dtTargets.length; i < len; i++) {
            var titem = dtTargets[i];
            if (titem != e.target) {
                var thisAnswer = titem.parentNode.nextElementSibling;
                var thisQuestion = titem;
                if (thisAnswer.classList.contains('is-collapsed')) {
                    setAccordionAria(thisQuestion, thisAnswer, 'true');
                } else {
                    setAccordionAria(thisQuestion, thisAnswer, 'false');
                }
                thisQuestion.classList.add('is-collapsed');
                thisQuestion.classList.remove('is-expanded');
                thisAnswer.classList.add('is-collapsed');
                thisAnswer.classList.remove('is-expanded');
                thisAnswer.classList.remove('animateIn');
            }
        }
    };
    for (var i = 0, len = accordionToggles.length; i < len; i++) {
        if (touchSupported) {
            accordionToggles[i].addEventListener('touchstart', skipClickDelay, false);
        }
        if (pointerSupported) {
            accordionToggles[i].addEventListener('pointerdown', skipClickDelay, false);
        }
        accordionToggles[i].addEventListener('click', switchAccordion, false);
    }
}

function resizeDetailMask() {
    // console.log("resize Detail Mask");
    var height = $(window).height();
    var mheight = height;
    // console.log(height, mheight);
    $(".modal-eventlist-body").css('maxHeight', mheight - 181);
    $(".modal-event-body").css('maxHeight', mheight - 181);
}
function resizeLeftSide(isLoad) {
    // console.log("resizeLeftSide");
    var bwidth = $('body').width();

    var nw = $("#homemodule_news_ibox").width(), btmH = getCalendarHeight(nw)-2,
        pheight = $("#page-wrapper").height() - btmH;

    if (!isLoad) {
        pheight = $("#homemodule_news_row").height();
    }
    console.log(bwidth, pheight, btmH, isLoad);
    if (pheight < 440) {
        if (!isLoad) {
            return;
        } else {
            pheight = 578;
        }
    }

    var topH = Math.floor(pheight * 0.4), midH = Math.floor(pheight * 0.6) - 50;
    // console.log("ResizeLeftSide", $("#homemodule_news_row").height(), pheight, topH, midH);
    if (bwidth > 1194) { // >1200
        $("#orgInvestChart").css('height', topH - 72);
        $("#org_panel_Chart").css('height', topH / 2 - 8);
        $("#merge_panel_Chart").css('height', topH / 2 - 8);
        $("#fund_panel_Chart").css('height', topH / 2 - 8);
        $("#event_panel_Chart").css('height', topH / 2 - 8);

        $("#event_mix_charts").css('height', midH-20);

        $("#project_dashboard_chart").css('height', btmH);
        $("#company_dashboard_chart").css('height', btmH);
        $("#report_dashboard_chart").css('height', btmH);
        $("#elastic_dashboard_chart").css('height', btmH);
    } else { // <1200
        $("#orgInvestChart").css('height', 164);
        $("#org_panel_Chart").css('height', 110);
        $("#merge_panel_Chart").css('height', 110);
        $("#fund_panel_Chart").css('height', 110);
        $("#event_panel_Chart").css('height', 110);
        $("#event_mix_charts").css('height', 200);

        $("#project_dashboard_chart").css('height', 140);
        $("#company_dashboard_chart").css('height', 140);
        $("#report_dashboard_chart").css('height', 140);
        $("#elastic_dashboard_chart").css('height', 140);
    }
}
$(window).resize(function () {
    // console.log("window resize");
    var nw = $("#homemodule_news_ibox").width(), newH = $("#homemodule_news_row").height();//,
    winheight = $('body').height() - 60;
    var nh = getCalendarHeight(nw);
    // console.log("resize:", nw, nh,winheight);
    $('#calendar').calendar('autoResize', nw, nh);
    resizeDetailMask();
    resizeLeftSide(false);
    // getUserMonitorList();
});









