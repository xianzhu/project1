/**
 * Created by a88u on 2017/6/19.
 */
require.config({
    paths: {
        echarts: 'js/plugins/echarts-2.2.7'
    }
});

var rptChart, orgPanelChart, eventPanelChart, mergePanelChart, fundPanelChart,
    projDashChart, companyDashChart, reportDashChart,elasticDashChart, eventMixChart;

// 股权投资行为趋势
function showRptEcharts2(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var data = {
        "durTime": [],
        "ydata": {
            "iNum": [],
            "eNum": [],
            "psIbmpa": []
        }
    }
    for (var i = 0; i < srcdata.length; i++) {
        var item = srcdata[i];
        data.durTime.push(item.durTime);
        data.ydata.iNum.push(item.iNum);
        data.ydata.eNum.push(item.eNum);
        data.ydata.psIbmpa.push(item.psIbmpa);
    }

    var moption = clone(orgTrendsOption);

    moption.xAxis.data = data.durTime;

    moption.series[0].data = [];
    var flag = true;
    for (var j = data.ydata.psIbmpa.length - 1; j >= 0; j--) {
        var yd = data.ydata.psIbmpa[j];
        if (flag) {
            if (yd == null || yd == 0) {
                continue;
            } else {
                flag = false;
                moption.series[0].data.unshift(yd);
            }
        } else {
            moption.series[0].data.unshift(yd);
        }
    }

    if (typeof rptChart == 'undefined') {
        require(['echarts', 'echarts/chart/line'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            rptChart = echarts.init(dom);

            rptChart.setOption(moption, true);

            window.addEventListener('resize', function () {
                rptChart.resize && rptChart.resize();
            });
        });
    } else {
        rptChart.setOption(moption, true);
    }
}

function showRptEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var data = {
        "durTime": [],
        "ydata": {
            "iNum": [],
            "eNum": [],
            "psIbmpa": []
        }
    }

    var srcdatat=[
        // {"durTime":1,"iNum":0.100184167,"eNum":0.654420922,"psIbmpa":0.114007745},
        // {"durTime":2,"iNum":0.103558471,"eNum":0.663432383,"psIbmpa":0.198198412},
        // {"durTime":3,"iNum":0.103709857,"eNum":0.663840097,"psIbmpa":0.197481281},
        // {"durTime":4,"iNum":0.123794837,"eNum":0.720684449,"psIbmpa":0.195493054},
        // {"durTime":5,"iNum":0.935107335,"eNum":0.155291005,"psIbmpa":0.130806622},
        // {"durTime":6,"iNum":0.944563298,"eNum":0.18579699,"psIbmpa":0.171527775},
        // {"durTime":7,"iNum":0.943336428,"eNum":0.181753834,"psIbmpa":0.179525863},
        // {"durTime":8,"iNum":0.12648611,"eNum":0.128739003,"psIbmpa":0.980646229},
        // {"durTime":9,"iNum":0.122201674,"eNum":0.115967247,"psIbmpa":0.904135432},
        // {"durTime":10,"iNum":0.100184167,"eNum":0.154420922,"psIbmpa":0.914007745}

        {"durTime":1,"iNum":0.900184167,"eNum":0.806302607,"psIbmpa":0.654420922},
        {"durTime":2,"iNum":0.903558471,"eNum":0.811830305,"psIbmpa":0.663432383},
        {"durTime":3,"iNum":0.903709857,"eNum":0.812079609,"psIbmpa":0.663840097},
        {"durTime":4,"iNum":0.923794837,"eNum":0.846206669,"psIbmpa":0.720684449},
        {"durTime":5,"iNum":0.935107335,"eNum":0.866417497,"psIbmpa":0.755291005},
        {"durTime":6,"iNum":0.944563298,"eNum":0.883914568,"psIbmpa":0.78579699},
        {"durTime":7,"iNum":0.943336428,"eNum":0.881611877,"psIbmpa":0.781753834},
        {"durTime":8,"iNum":0.92648611,"eNum":0.850946715,"psIbmpa":0.728739003},
        {"durTime":9,"iNum":0.922201674,"eNum":0.843420123,"psIbmpa":0.715967247},
        {"durTime":10,"iNum":0.900184167,"eNum":0.806302607,"psIbmpa":0.654420922}
    ];
    for (var i = 0; i < srcdatat.length; i++) {
        var item = srcdatat[i];
        data.durTime.push(item.durTime);
        data.ydata.iNum.push(item.iNum);
        data.ydata.eNum.push(item.eNum);
        data.ydata.psIbmpa.push(item.psIbmpa);
    }
    var moption = clone(orgTrendsOption);

    moption.xAxis.data = data.durTime;
    moption.series[2].data=data.ydata.iNum;
    moption.series[1].data=data.ydata.eNum;
    moption.series[0].data=data.ydata.psIbmpa;

    if (typeof rptChart == 'undefined') {
        require(['echarts', 'echarts/chart/line'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            rptChart = echarts.init(dom);
            rptChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                rptChart.resize && rptChart.resize();
            });
        });
    } else {
        rptChart.setOption(moption, true);
    }
}
// 统计信息 - 机构调研
function showOrgPanelEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(panelOrgOption);

    moption.legend.data=srcdata.legendData;
    moption.yAxis[0].data=srcdata.legendData;
    moption.series[0].data=srcdata.orgData;

    if (typeof orgPanelChart == 'undefined') {
        require(['echarts', 'echarts/chart/bar'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            orgPanelChart = echarts.init(dom);
            orgPanelChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                orgPanelChart.resize && orgPanelChart.resize();
            });
        });
    } else {
        orgPanelChart.setOption(moption, true);
    }
}
// 统计信息 - 投退事件
function showEventPanelEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }

    var echarts;
    var moption = clone(panelEventOption);
    moption.xAxis[0].data=srcdata.durData;
    moption.series[0].data=srcdata.exitData;
    moption.series[1].data=srcdata.investData;
    var sum=srcdata.sum*1.1;
    // moption.yAxis[0].min=Math.floor(-2*emax);
    // moption.yAxis[1].max=Math.floor(2*imax);

    moption.yAxis[0].min=-sum;
    moption.yAxis[1].max=sum;

    if (typeof eventPanelChart == 'undefined') {
        require(['echarts', 'echarts/chart/line'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            eventPanelChart = echarts.init(dom);
            eventPanelChart.setOption(moption, true);
            window.onresize = function () {
                eventPanelChart.resize && eventPanelChart.resize();
            };
        });
    } else {
        eventPanelChart.setOption(moption, true);
    }
}
// 统计信息 - 并购事件
function showMergePanelEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(panelMergeOption);
    moption.series[0].data=srcdata.proTransfer;
    moption.series[1].data=srcdata.sharePurchase;
    moption.series[2].data=srcdata.capIncrease;
    moption.series[3].data=srcdata.investment;

    moption.xAxis[0].data=srcdata.durData;

    if (typeof mergePanelChart == 'undefined') {
        require(['echarts', 'echarts/chart/line'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            mergePanelChart = echarts.init(dom);
            mergePanelChart.setOption(moption, true);

            window.addEventListener('resize', function () {
                mergePanelChart.resize && mergePanelChart.resize();
            });
        });
    } else {
        mergePanelChart.setOption(moption, true);
    }
}
// 统计信息 - 基金备案
function showFundPanelEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(panelFundOption);
    moption.xAxis[0].data=srcdata.durData;
    moption.series[0].data=srcdata.stockFund;
    moption.series[1].data=srcdata.startFund;

    if(typeof fundPanelChart=='undefined') {
        require(['echarts', 'echarts/chart/bar'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            fundPanelChart = echarts.init(dom);
            fundPanelChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                fundPanelChart.resize && fundPanelChart.resize();
            });
        });
    }else{
        fundPanelChart.setOption(moption, true);
    }
}
// 仪表盘 - 项目
function showProjDashEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(dashProjOption);
    moption.series[0].data[0].value = srcdata.count;

    if (typeof projDashChart == 'undefined') {
        require(['echarts', 'echarts/chart/gauge'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            projDashChart = echarts.init(dom);
            var ecConfig = require('echarts/config');
            projDashChart.on(ecConfig.EVENT.GAUGE_CLICKED,function(param){
                gotoCurrentProjectPage();
            });
            projDashChart.setOption(moption, true);

            window.addEventListener('resize', function () {
                projDashChart.resize && projDashChart.resize();
            });
        });
    } else {
        projDashChart.setOption(moption, true);
    }
}
// 仪表盘 - 企业
function showCompanyDashEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(dashCompanyOption);
    moption.series[0].data[0].value = srcdata.count;
    if (typeof companyDashChart == 'undefined') {
        require(['echarts', 'echarts/chart/gauge'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            companyDashChart = echarts.init(dom);
            var ecConfig = require('echarts/config');
            companyDashChart.on(ecConfig.EVENT.GAUGE_CLICKED,function(param){
                // gotoTraderReportPage();
            });
            companyDashChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                companyDashChart.resize && companyDashChart.resize();
            });
        });
    } else {

    }
}
// 仪表盘 - 报告
function showReportDashEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(dashReportOption);
    moption.series[0].data[0].value = srcdata.count;
    if (typeof reportDashChart == 'undefined') {
        require(['echarts', 'echarts/chart/gauge'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            reportDashChart = echarts.init(dom);
            var ecConfig = require('echarts/config');
            reportDashChart.on(ecConfig.EVENT.GAUGE_CLICKED,function(param){
                gotoTraderReportPage(); // 哪个report？
            });
            reportDashChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                reportDashChart.resize && reportDashChart.resize();
            });
        });
    } else {
        reportDashChart.setOption(moption, true);
    }
}
// 仪表盘 - 情报
function showElasticDashEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(dashElasticOption);
    // var moption = clone(dashReportOption);
    moption.series[0].data[0].value = srcdata.count;
    if (typeof elasticDashChart == 'undefined') {
        require(['echarts', 'echarts/chart/gauge'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            elasticDashChart = echarts.init(dom);
            var ecConfig = require('echarts/config');
            elasticDashChart.on(ecConfig.EVENT.GAUGE_CLICKED,function(param){
                gotoNewsPage();
            });
            elasticDashChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                elasticDashChart.resize && elasticDashChart.resize();
            });
        });
    } else {
        elasticDashChart.setOption(moption, true);
    }
}

// 今日资本事件 - 饼图、柱状图
function showEventMixEcharts(piedata,bardata, id) {
    if (!bardata||!piedata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(eventMixOption);

    moption.xAxis[0].data=bardata.durData;
    moption.series[0].data=bardata.invEarly; // 早期投资
    moption.series[1].data=bardata.invMiddle; // 中期投资
    moption.series[2].data=bardata.invLate; // 后期投资
    moption.series[3].data=bardata.invOther; // 其他投资
    moption.series[4].data=bardata.exitOne; // 一级市场退出
    moption.series[5].data=bardata.exitTwo; // 二级市场退出

    moption.series[6].data=piedata.eventOneData;
    moption.series[7].data=piedata.eventTwoData;

    if (typeof eventMixChart == 'undefined') {
        require(['echarts', 'echarts/chart/pie','echarts/chart/bar'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            eventMixChart = echarts.init(dom);
            var ecConfig = require('echarts/config');

            eventMixChart.on(ecConfig.EVENT.PIE_SELECTED, function (param) {
                var selected = param.selected;
                var serie;
                var str = '当前选择： ';
                var hasselect=false;
                for (var idx in selected) {
                    serie = moption.series[idx];
                    for (var i = 0, l = serie.data.length; i < l; i++) {
                        if (selected[idx][i]) {
                            str += '【系列' + idx + '】' + serie.name + ' : ' +
                                '【数据' + i + '】' + serie.data[i].name + ' ';
                            var type = serie.data[i].name;
                            modal_event_list.$data.eventType=type;
                            modal_event_list.$data.eventPage=0;
                            getEventSubpage(type);
                            hasselect=true;
                        }
                    }
                }
                if(!hasselect){
                    modal_event_list.$data.eventType='';
                    modal_event_list.$data.eventPage=0;
                    // getEventSubpage('');
                }
            })
            eventMixChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                eventMixChart.resize && eventMixChart.resize();
            });
        });
    } else {
        eventMixChart.setOption(moption, true);
    }
}

function resetEventMixChart(){
    eventMixChart.resetchart();
    eventMixChart.resize();
}

function allChartResize() {
    if(typeof rptChart!='undefined') {
        rptChart.resize && rptChart.resize();
    }
    if(typeof orgPanelChart!='undefined') {
        orgPanelChart.resize && orgPanelChart.resize();
    }
    if(typeof eventPanelChart!='undefined') {
        eventPanelChart.resize && eventPanelChart.resize();
    }
    if(typeof mergePanelChart!='undefined') {
        mergePanelChart.resize && mergePanelChart.resize();
    }
    if(typeof fundPanelChart!='undefined') {
        fundPanelChart.resize && fundPanelChart.resize();
    }

    if(typeof eventMixChart!='undefined') {
        eventMixChart.resize && eventMixChart.resize();
    }

}