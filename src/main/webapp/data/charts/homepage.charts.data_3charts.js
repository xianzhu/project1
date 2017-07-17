/**
 * Created by a88u on 2017/6/19.
 */

// 市场投资信心指数
var orgTrendsOption = {
    textStyle: {color: '#3c3c3c'},
    title: {
        text: '',
        show: false,
        left: 'left',
        top: 30,
        textBaseline: 'middle',
        textStyle: {
            fontWeight: 'bold',
            fontSize: 16,
            color: '#aaaaaa'
        }
    },
    tooltip: {
        trigger: 'axis',
        // showDelay: 500,
        hideDelay:50,
        //backgroundColor:'rgba(0,0,250,0.4)',
        formatter: function (params) {
            // console.log(params);
            var tip = params[0].name + "<br>" + params[0].seriesName + ": ";
            if (typeof params[0].data == 'undefined') {
                tip += '-';
            } else {
                tip += params[0].data.toFixed(3);
            }
            return tip;
        }
    },
    grid: {
        borderWidth: 0,
        x: 25,
        x2: 25,
        y: 10,
        y2: 20
    },
    // calculable: true,
    legend: {
        show: false,
        data: [
            {name: 'PMICI'}
        ],
        x: "center",
        y: "top",
        textStyle: {
            color: '#3c3c3c'
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        // margin : 4,
        //nameGap : 1,
        // axisLabel : {
        //    show:true,
        //    textStyle : {
        //        color : '#000000',
        //        fontSize : 4
        //    },
        //    margin : 8  // 标签与X轴距离
        // },
        axisLine: {
            lineStyle: {
                color: 'rgba(25,25,25,1)',
                width: 1
            }
        },
        splitLine: {
            show: false
            // 是否显示网格
        },
        data: []
    },
    yAxis: [
        {
            type: 'value',
            name: "",
            min: 0,
            max: 1,
            scale: true,
            axisLabel: {
                textStyle: {
                    color: '#3c3c3c',
                    fontSize: 4
                },
                margin: 4
            },
            position: 'left',
            axisLine: {
                lineStyle: {
                    color: 'rgba(25,25,25,.8)',
                    width: 1
                }
            },
            splitLine: {
                show: false // 是否显示网格
            }
        }
    ],
    series: [
        {
            name: 'PMICI',
            type: 'line',
            smooth: true,
            symbol: 'none',
            lineStyle: {normal: {color: "#edafda"}},
            itemStyle: {
                normal: {
                    color: 'rgba(247,165,74,0.9)' //"#f7a54a", // "#817b01",
                }
            },
            data: [],
            markLine: {
                symbol: 'none',
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        }
                    }
                },
                data: [
                    {type: 'average', name: '平均值',tooltip:{show:false}},
                    [
                        {name: '参考值', value: 0.4, x: 25, y: 50,tooltip:{show:false}},
                        {x: 1000, y: 50}
                    ]
                ]
            },
            yAxisIndex: 0
        }
    ]
};

// 统计信息 - 机构调研-标准条形图
var panelOrgOption = {
    title: {
        show: false,
        text: '机构调研',
        subtext: '标准条形图'
    },
    tooltip: {
        trigger: 'item',
        hideDelay: 50,
        formatter: function (params) {
            var result = params.seriesName + "<br/>" + params.name + ":" + params.data;
            return result;
        },
        axisPointer: {
            type: 'none'
        }
    },
    legend: {
        show: false,
        data: []
    },
    toolbox: {
        show: false
    },
    // calculable : true,
    grid: {
        borderWidth: 0,
        x: 12,
        x2: 12,
        y: 28,
        y2: 15
    },
    xAxis: [
        {
            type: 'value',
            show: false
        }
    ],
    yAxis: [
        {
            type: 'category',
            // show:false,
            data: [],
            splitLine: {
                show: false // 是否显示网格
            },
            axisLabel: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                }
            }
        }
    ],
    series: [
        {
            name: '最近7天',
            type: 'bar',
            barWidth: 5,
            data: [], // 统一颜色显示
            itemStyle: {
                normal: {
                    color: '#fff',
                    label: {
                        show: true,
                        position: 'right',
                        formatter: function (params) {
                            return params.name;
                        }
                    }
                }
            },
        }
    ]
};
// 统计信息 - 投退事件-反向数值轴，面积图
var panelEventOption = {
    title: {
        show: false,
        text: '投退事件',
        subtext: '反向数值轴-面积图'
    },
    tooltip: {
        trigger: 'axis',
        hideDelay:50,
        transitionDuration:0,
        formatter: function (params) {
            return params[0].name + '<br/>'
                + params[0].seriesName + ' : ' + (-params[0].value) + '<br/>'
                + params[1].seriesName + ' : ' + params[1].value;
        }
    },
    legend: {
        show: false,
        data: ['投资', '退出']
    },
    toolbox: {
        show: false
    },
    grid: {
        borderWidth: 0,
        x: 12,
        x2: 12,
        y: 25,
        y2: 18
    },
    xAxis: [
        {
            type: 'category',
            show: false,
            data: [],
            axisLine: {onZero: false},
            boundaryGap: false,
            splitLine: {
                show: false
            },
            axisLabel: {
                margin: 4,
                show: false
            },
            axisTick: {
                length: 3
            }
        }
    ],
    yAxis: [
        {
            type: 'value', // 退出
            max: 0,
            min: -70,
            position: 'right',
            show: false,
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(210,120,21,0.8)'
                }
            }
        },
        {
            type: 'value', // 投资
            max: 500,
            show: false,
            position: 'left',
            axisLabel: {
                formatter: function (v) {
                    return -v;
                },
                margin: 4,
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(53,123,41,0.8)'
                }
            }
        }
    ],
    series: [
        {
            name: '退出',
            type: 'line',
            // smooth: true,
            symbol: 'none',
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default',
                        // color:'rgba(210,120,21,0.2)'
                        color: 'rgba(55,55,55,0.3)'
                        // color:'rgba(102,153,255,0.2)'
                    },
                    lineStyle: {
                        // color:'rgba(210,120,21,0.3)'
                        color: 'rgba(55,55,55,0.5)'
                        // color:'rgba(102,153,255,0.4)'
                    }
                }
            },
            yAxisIndex: 0,
            data: []
        },
        {
            name: '投资',
            type: 'line',
            // smooth: true,
            symbol: 'none',
            yAxisIndex: 1,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default',
                        // color:'rgba(53,123,41,0.1)'
                        // color:'rgba(48,224,224,0.1)'
                        // color:'#36ae7e'
                        color: 'rgba(240,240,240,0.5)'
                    },
                    lineStyle: {
                        // color:'rgba(53,123,41,0.4)'
                        color: 'rgba(240,240,240,1)'
                    }
                }
            },
            data: []
        }
    ]
};
// 统计信息 - 并购事件-标准面积图
var panelMergeOption = {
    title: {
        show: false,
        text: '并购事件',
        subtext: '标准面积图'
    },
    tooltip: {
        trigger: 'axis',
        hideDelay: 50,
        transitionDuration:0,
        position:function(x){
            return [0,0];
        },
        textStyle: {
            fontSize: 8
        }
    },
    legend: {
        show: false,
        data: ['协议转让', '发行股份购买', '增资', '对外投资']
    },
    toolbox: {
        show: false
    },
    // calculable : true,
    grid: {
        borderWidth: 0,
        x: 16,
        x2: 16,
        y: 35,
        y2: 20
    },
    xAxis: [
        {
            type: 'category',
            show: false,
            data: [],
            axisLine: {onZero: false},
            boundaryGap: false,
            splitLine: {
                show: false
            },
            axisLabel: {
                margin: 4
            },
            axisTick: {
                length: 3
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            show: false,
            splitLine: {
                show: false
            },
            axisLabel: {
                show: false
            }
        }
    ],
    series: [
        {
            name: '协议转让',
            type: 'line',
            smooth: true,
            symbol: 'emptyCircle',
            stack: '总量',
            itemStyle: {
                normal: {
                    color:'rgba(255,255,255,0.9)'
                }
            },
            data: []
        },
        {
            name: '发行股份购买',
            type: 'line',
            smooth: true,
            // symbol: 'arrow',
            stack: '总量',
            itemStyle: {
                normal: {
                    color:'rgba(48,224,224,1)'
                }
            },
            data: []
        },
        {
            name: '增资',
            type: 'line',
            smooth: true,
            // symbol: 'emptyDiamond',
            stack: '总量',
            itemStyle: {
                normal: {
                    color:'rgba(0,250,154,1)'
                }
            },
            data: []
        },
        {
            name: '对外投资',
            type: 'line',
            smooth: true,
            symbol: 'circle',
            stack: '总量',
            itemStyle: {
                normal: {
                    color: 'rgba(255,215,0,1)'
                }
            },
            data: []
        }
    ]
};
// 统计信息 - 基金备案-标准柱状图
var panelFundOption = {
    title: {
        show: false,
        text: '基金备案',
        subtext: '标准柱状图'
    },
    tooltip: {
        trigger: 'item',
        hideDelay: 50,
        axisPointer: {
            type: 'none'
        },
        formatter: function (params) {
            var result = params.name + "<br/>" + params.seriesName + ":" + params.data;
            return result;
        }
    },
    legend: {
        show: false,
        data: ['股权类投资基金', '创业类投资基金']
    },
    toolbox: {
        show: false
    },
    // calculable : true,
    grid: {
        borderWidth: 0,
        x: 12,
        x2: 12,
        y: 25,
        y2: 18
    },
    xAxis: [
        {
            type: 'category',
            data: [],
            axisLine: {onZero: false},
            splitLine: {
                show: false
            },
            axisLabel: {
                margin: 4,
                show: false
            },
            axisTick: {
                length: 3,
                show: false
            },
            axisLine: {
                show: false
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            show: false,
            splitLine: {
                show: false // 是否显示网格
            },
            axisLabel: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }
    ],
    series: [
        {
            name: '股权类',
            type: 'bar',
            barMaxWidth: 10,
            itemStyle: {
                normal: {
                    // color:'rgba(48,224,224,0.6)'
                    color: '#fff'
                }
            },
            data: []
        },
        {
            name: '创业类',
            type: 'bar',
            barMaxWidth: 10,
            itemStyle: {
                normal: {
                    // color:'rgba(12,13,105,0.7)'
                    color: '#2b2b2b'
                }
            },
            data: []
        }
    ]
};
// 仪表盘 - 项目
var dashProjOption = {
    tooltip: {
        formatter: function (params) {
            var name = params.name, value = params.data.value,
                rate = params.data.value + "%";
            return name + "<br/>" + value + "(" + rate + ")";
        }
    },
    series: [
        {
            name: '当日量',
            type: 'gauge',
            center: ['40%', '55%'],    // 默认全局居中
            // radius: '75%',
            radius: '95%',
            min: 0,
            max: 100,
            endAngle: 0,
            splitNumber: 5,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[0.2, 'lime'], [0.8, '#1e90ff'], [1, '#ff4500']],
                    width: 2,
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 10
                }
            },
            axisLabel: {            // 坐标轴小标记
                show: false,
                textStyle: {       // 属性lineStyle控制线条样式
                    fontWeight: 'bolder',
                    color: '#777',
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 5
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor: 'auto', //默认透明
                    shadowBlur: 2
                },
                splitNumber: 5
            },
            splitLine: {           // 分隔线
                length: 20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width: 2,
                    color: 'auto',
                    shadowColor: '#eee', //默认透明
                    shadowBlur: 10
                }
            },
            pointer: {
                width: 3,
                shadowColor: '#333', //默认透明
                shadowBlur: 3
            },
            title: {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontStyle: 'italic',
                    color: '#333',
                    shadowColor: '#333', //默认透明
                    shadowBlur: 10
                }
            },
            detail: {
                borderColor: '#333',
                shadowColor: '#333', //默认透明
                shadowBlur: 5,
                width: 80,
                height: 30,
                offsetCenter: [25, '20%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    color: '#333'
                }
            },
            data: [{value: 1005, name: '项目'}]
        }
    ]
};
// 仪表盘 - 企业
var dashCompanyOption = {
    tooltip: {
        formatter: function (params) {
            var name = params.name, value = params.data.value,
                rate = (params.data.value / 200).toFixed(2) + "%";
            return name + "<br/>" + value + "(" + rate + ")";
        }
    },
    series: [
        {
            name: '当日量',
            type: 'gauge',
            center: ['40%', '55%'],    // 默认全局居中
            // radius: '75%',
            radius: '95%',
            min: 0,
            max: 20000,
            endAngle: 0,
            splitNumber: 10,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[0.1, 'lime'], [0.75, '#1e90ff'], [1, '#ff4500']],
                    width: 2,
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 10
                }
            },
            axisLabel: {            // 坐标轴小标记
                show: false,
                textStyle: {       // 属性lineStyle控制线条样式
                    fontWeight: 'bolder',
                    color: '#777',
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 5
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor: 'auto', //默认透明
                    shadowBlur: 2
                },
                splitNumber: 4
            },
            splitLine: {           // 分隔线
                length: 20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width: 2,
                    color: 'auto',
                    shadowColor: '#eee', //默认透明
                    shadowBlur: 10
                }
            },
            pointer: {
                width: 3,
                shadowColor: '#333', //默认透明
                shadowBlur: 3
            },
            title: {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontStyle: 'italic',
                    color: '#333',
                    shadowColor: '#333', //默认透明
                    shadowBlur: 10
                }
            },
            detail: {
                borderColor: '#333',
                shadowColor: '#333', //默认透明
                shadowBlur: 5,
                width: 80,
                height: 30,
                offsetCenter: [25, '20%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    color: '#333'
                }
            },
            data: [{value: 1005, name: '企业'}]
        }
    ]
};
// 仪表盘 - 报告
var dashReportOption = {
    tooltip: {
        formatter: function (params) {
            var name = params.name, value = params.data.value,
                rate = (params.data.value / 7).toFixed(2) + "%";
            return name + "<br/>" + value + "(" + rate + ")";
        }
    },
    series: [
        {
            name: '当日量',
            type: 'gauge',
            center: ['40%', '55%'],    // 默认全局居中
            // radius: '75%',
            radius: '95%',
            min: 0,
            max: 700,
            endAngle: 0,
            splitNumber: 7,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[1 / 7, 'lime'], [5 / 7, '#1e90ff'], [1, '#ff4500']],
                    width: 2,
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 10
                }
            },
            axisLabel: {            // 坐标轴小标记
                show: false,
                textStyle: {       // 属性lineStyle控制线条样式
                    fontWeight: 'bolder',
                    color: '#777',
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 5
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor: 'auto', //默认透明
                    shadowBlur: 2
                },
                splitNumber: 5
            },
            splitLine: {           // 分隔线
                length: 20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width: 2,
                    color: 'auto',
                    shadowColor: '#eee', //默认透明
                    shadowBlur: 10
                }
            },
            pointer: {
                width: 3,
                shadowColor: '#333', //默认透明
                shadowBlur: 3
            },
            title: {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontStyle: 'italic',
                    color: '#333',
                    shadowColor: '#333', //默认透明
                    shadowBlur: 10
                }
            },
            detail: {
                borderColor: '#333',
                shadowColor: '#333', //默认透明
                shadowBlur: 5,
                width: 80,
                height: 30,
                offsetCenter: [25, '20%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    color: '#333'
                }
            },
            data: [{value: 1005, name: '报告'}]
        }
    ]
};
// 仪表盘 - 情报
var dashElasticOption = {
    tooltip: {
        formatter: function (params) {
            var name = params.name, value = params.data.value,
                rate = (params.data.value / 12).toFixed(2) + "%";
            return name + "<br/>" + value + "(" + rate + ")";
        }
    },
    series: [
        {
            name: '当日量',
            type: 'gauge',
            center: ['40%', '55%'],    // 默认全局居中
            radius: '95%',
            min: 0,
            max: 1200,
            endAngle: 0,
            splitNumber: 12,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: [[1 / 4, 'lime'], [5 / 6, '#1e90ff'], [1, '#ff4500']],
                    width: 2,
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 10
                }
            },
            axisLabel: {            // 坐标轴小标记
                show: false,
                textStyle: {       // 属性lineStyle控制线条样式
                    fontWeight: 'bolder',
                    color: '#777',
                    shadowColor: '#ccc', //默认透明
                    shadowBlur: 5
                }
            },
            axisTick: {            // 坐标轴小标记
                length: 12,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto',
                    shadowColor: 'auto', //默认透明
                    shadowBlur: 2
                },
                splitNumber: 4
            },
            splitLine: {           // 分隔线
                length: 20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    width: 2,
                    color: 'auto',
                    shadowColor: '#eee', //默认透明
                    shadowBlur: 10
                }
            },
            pointer: {
                width: 3,
                shadowColor: '#333', //默认透明
                shadowBlur: 3
            },
            title: {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontStyle: 'italic',
                    color: '#333',
                    shadowColor: '#333', //默认透明
                    shadowBlur: 10
                }
            },
            detail: {
                borderColor: '#333',
                shadowColor: '#333', //默认透明
                shadowBlur: 5,
                width: 80,
                height: 30,
                offsetCenter: [25, '20%'],       // x, y，单位px
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    color: '#333'
                }
            },
            data: [{value: 1005, name: '情报'}]
        }
    ]
};
// 今日资本事件 - 饼图
var eventPieOption = {
    tooltip: {
        trigger: 'item',
        showDelay: 500,
        hideDelay:10,
        formatter: "{b}<br>{c}<br>{d}%"
    },
    legend: {
        orient: 'vertical',
        x: 'right',
        data: ['一级市场退出', '二级市场退出','早期投资', '中期投资', '后期投资', '其他投资']
    },
    calculable: false,
    // color:[
    //         '#6495ed',
    //         'rgba(255,165,0,0.6)',
    //         '#6f5553',
    //         '#deb887',
    //         '#7eb00a',
    //         '#27727B',
    //         '#7fffd4',
    //         '#e2bbff',
    //         '#ba55d3',
    //         '#f5994e'
    // ],
    series: [
        {
            name: '资本事件',
            type: 'pie',
            radius: [0, '60%'],
            center: ['45%', '56%'],
            itemStyle: {
                normal: {
                    label: {
                        position: 'inner',
                        textStyle:{
                            color:'#fff'
                        }
                    },
                    labelLine: {
                        show: false,
                        length:-20,
                        lineStyle:{
                            color:'#333'
                        }
                    }
                }
            },
            data: []
        },
        {
            name: '二级分类',
            type: 'pie',
            selectedMode: 'single',
            radius: ['70%', '85%'],
            center: ['45%', '56%'],
            itemStyle: {
                normal: {
                    labelLine: {
                        length: 2
                    }
                }
            },
            data: []
        }
    ]
};
// 今日资本事件 - 柱状图
var eventBarOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        show:false,
        data: ['早期投资', '中期投资', '后期投资', '其他投资','一级市场退出', '二级市场退出']
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        y: 'center',
        feature: {
            mark: {show: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            splitLine: {show: false},
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value',
            splitLine: {show: false},
            splitArea: {show: false}
        }
    ],
    grid: {
        x: 40,
        y: 25,
        x2: 25,
        y2: 20
    },
    series: [
        {
            name: '早期投资',
            type: 'bar',
            stack: '投资',
            itemStyle: {
                normal: {
                    color: 'rgba(120,164,135,1)'
                }
            },
            barMaxWidth:15,
            data: []
        },
        {
            name: '中期投资',
            type: 'bar',
            stack: '投资',
            itemStyle: {
                normal: {
                    color: 'rgba(255,127,80,1)'
                }
            },
            barMaxWidth:15,
            data: []
        },
        {
            name: '后期投资',
            type: 'bar',
            stack: '投资',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(135,206,250,1)'
                }
            },
            data: []
        },
        {
            name: '其他投资',
            type: 'bar',
            stack: '投资',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(218,112,214,1)'
                }
            },
            data: []
        },
        {
            name: '一级市场退出',
            type: 'bar',
            stack: '退出',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(50,205,50,1)'
                }
            },
            data: []
        },
        {
            name: '二级市场退出',
            type: 'bar',
            stack: '退出',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(100,149,37,1)'
                }
            },
            data: []
        }
    ]
};

// 今日资本事件 - 饼图、柱状图
var eventMixOption = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        show: true,
        x: 'right',
        data: ['早期投资', '中期投资', '后期投资', '其他投资','一级市场退出', '二级市场退出']
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        y: 'center',
        feature: {
            mark: {show: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: false,

    xAxis: [
        {
            type: 'category',
            splitLine: {show: false},
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value',
            position:'left',
            splitLine: {show: false},
            splitArea: {show: false}
        }
    ],
    grid: {
        x: '48%',
        y: 40,
        x2: 25,
        y2: 30
    },

    series: [
        {
            name: '早期投资',
            type: 'bar',
            stack: '投资',
            symbol:'none',
            itemStyle: {
                normal: {
                    color: 'rgba(120,164,135,1)'
                }
            },
            barMaxWidth:15,
            data: []
        },
        {
            name: '中期投资',
            type: 'bar',
            stack: '投资',
            symbol:'none',
            itemStyle: {
                normal: {
                    color: 'rgba(255,127,80,1)'
                }
            },
            barMaxWidth:15,
            data: []
        },
        {
            name: '后期投资',
            type: 'bar',
            stack: '投资',
            symbol:'none',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(135,206,250,1)'
                }
            },
            data: []
        },
        {
            name: '其他投资',
            type: 'bar',
            stack: '投资',
            symbol:'none',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(218,112,214,1)'
                }
            },
            data: []
        },
        {
            name: '一级市场退出',
            type: 'bar',
            stack: '退出',
            symbol:'none',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(100,149,247,1)'
                }
            },
            data: []
        },
        {
            name: '二级市场退出',
            type: 'bar',
            stack: '退出',
            symbol:'none',
            barMaxWidth:15,
            itemStyle: {
                normal: {
                    color: 'rgba(50,205,50,1)'
                }
            },
            data: []
        },

        {
            name: '资本事件',
            type: 'pie',
            radius: [0, '55%'],
            center: ['23%', '55%'],
            tooltip: {
                trigger: 'item',
                showDelay: 500,
                formatter: "{b}<br>{c}({d}%)"
            },
            itemStyle: {
                normal: {
                    label: {
                        position: 'inner',
                        textStyle:{
                            color:'#fff'
                        },
                        formatter:function (params) {
                            var result=params.percent+"%";
                            if(params.percent<15){ // 如果饼图值过小，不显示
                                result="";
                            }
                            return result;
                        }
                    },
                    labelLine: {
                        show: false,
                        length:20,
                        lineStyle:{
                            color:'#333'
                        }
                    }
                }
            },
            data: []
        },
        {
            name: '二级分类',
            type: 'pie',
            selectedMode: 'single',
            radius: ['62%', '75%'],
            center:['23%','55%'],
            tooltip: {
                trigger: 'item',
                showDelay: 500,
                formatter: "{b}<br>{c}({d}%)"
            },
            itemStyle: {
                normal: {
                    // label:{
                    //     position: 'outer',
                    //     textStyle:{
                    //         color:'#000'
                    //     },
                    // },
                    labelLine: {
                        length: 1
                    }
                }
            },
            data: []
        }
    ]
};
