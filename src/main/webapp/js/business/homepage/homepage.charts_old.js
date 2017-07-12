/**
 * Created by a88u on 2017/6/19.
 */
require.config({
    paths: {
        echarts: 'js/plugins/echarts-2.2.7'
    }
});

var rptChart, orgPanelChart, eventPanelChart, MergePanelChart, reportPanelChart,
    projDashChart, companyDashChart, reportDashChart,elasticDashChart, eventBarChart,eventPieChart;

// 股权投资行为趋势
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
            elasticDashChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                elasticDashChart.resize && elasticDashChart.resize();
            });
        });
    } else {
        elasticDashChart.setOption(moption, true);
    }
}

// 今日资本事件 - 饼图
function showEventPieEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(eventPieOption);

    // for(var i=srcdata.color.length-1;i>=0;i--){
    //     moption.color.unshift(srcdata.color[i]);
    // }
    // console.log(moption.color);
    moption.series[0].data=srcdata.eventOneData;
    moption.series[1].data=srcdata.eventTwoData;

    if (typeof eventPieChart == 'undefined') {
        require(['echarts', 'echarts/chart/pie'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            eventPieChart = echarts.init(dom);
            var ecConfig = require('echarts/config');

            eventPieChart.on(ecConfig.EVENT.PIE_SELECTED, function (param) {
                var selected = param.selected;
                var serie;
                var str = '当前选择： ';
                for (var idx in selected) {
                    serie = moption.series[idx];
                    // console.log(serie);
                    for (var i = 0, l = serie.data.length; i < l; i++) {
                        if (selected[idx][i]) {
                            str += '【系列' + idx + '】' + serie.name + ' : ' +
                                '【数据' + i + '】' + serie.data[i].name + ' ';
                            var type = serie.data[i].name,ptype=serie.data[i].type;
                            getEventSubpage(type,ptype);
                        }
                    }

                }
                // console.log(str);
            })
            eventPieChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                eventPieChart.resize && eventPieChart.resize();
            });
        });
    } else {
        eventPieChart.setOption(moption, true);
    }
}
// 今日资本事件 - 柱状图
function showEventBarEcharts(srcdata, id) {
    if (!srcdata) {
        console.log("data is undefined.");
        return;
    }
    var echarts;
    var moption = clone(eventBarOption);
    moption.xAxis[0].data=srcdata.durData;
    moption.series[0].data=srcdata.invEarly; // 早期投资
    moption.series[1].data=srcdata.invMiddle; // 中期投资
    moption.series[2].data=srcdata.invLate; // 后期投资
    moption.series[3].data=srcdata.invOther; // 其他投资
    moption.series[4].data=srcdata.exitOne; // 一级市场退出
    moption.series[5].data=srcdata.exitTwo; // 二级市场退出

    if (typeof eventBarChart == 'undefined') {
        require(['echarts', 'echarts/chart/bar'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            eventBarChart = echarts.init(dom);
            var ecConfig = require('echarts/config');

            eventBarChart.setOption(moption, true);
            window.addEventListener('resize', function () {
                eventBarChart.resize && eventBarChart.resize();
            });
        });
    } else {
        eventBarChart.setOption(moption, true);
    }
}



