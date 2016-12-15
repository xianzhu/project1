/**
 * Created by a88u on 2016/8/5.
 */

var colors={
    cpAnc_90:'#ef3d49',
    cpAnc_10:'#dedede',
    cpAnc_avg:'#b5b8cf',
    cpAnc_ent:'#1C84C6'
};

var companyAnnounceOption = {
    textStyle: {color:'#3c3c3c'},
    title: {
        text: '',
        left: 'center',
        top: 30,
        textBaseline: 'middle',
        textStyle: {
            fontWeight: 'bold',
            fontSize: 16,
            color:'#aaaaaa'
        }
    },
    tooltip: {
        trigger: 'axis',
        //backgroundColor:'rgba(0,0,250,0.4)',
        formatter:function(params){
            var tip=params[0].name;
            for(var i=0;i<params.length;i++){
                tip+="<br>"+params[i].seriesName+": "+parseFloat(params[i].value).toFixed(2);
            }
            return tip;
        }
    },
    grid : [ {
        left : 40,
        right : 23,
        top : 40,
        bottom : 25,
    } ],
    legend: {
        left: 50,
        data: [
            {name: '90分位数',textStyle: {color: colors.cpAnc_90}},
            {name:'10分位数',textStyle: {color: colors.cpAnc_10}},
            {name:'企业数据',textStyle: {color: colors.cpAnc_ent}},
            {name:'行业平均',textStyle: {color: colors.cpAnc_avg}}
        ],
        top: 0,
        textStyle: {
            color:'#3c3c3c'
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        margin : 0,
        nameGap : 1,
        axisLabel : {
            textStyle : {
                color : '#000000',
                fontSize : 4
            },
            margin : 8 
            // 标签与X轴距离
        },
        axisLine : {
            lineStyle : {
                color : 'rgba(25,25,25,1)'
            }
        },
        splitLine : {
            show : false
            // 是否显示网格
        },
        data: []
    },
    yAxis: {
        type: 'value',
        scale: true,
        boundaryGap:[20,20],
        axisLabel : {
            textStyle : {
                color : '#3c3c3c',
                fontSize : 4
            },
            margin : 8
        },
        axisLine : {
            lineStyle : {
                color : 'rgba(25,25,25,1)'
            }
        },
        splitLine : {
            show : false
            // 是否显示网格
        }
    },
    series: [
        {
            name: '90分位数',
            type: 'line',
            smooth:true,
            symbol: 'none',
            lineStyle: {normal: {color: colors.cpAnc_90}},
            itemStyle: {normal: {color: colors.cpAnc_90}},
            data: []
        },
        {
            name: '10分位数',
            type: 'line',
            smooth:true,
            symbol: 'none',
            lineStyle: {normal: {color: colors.cpAnc_10}},
            itemStyle: {normal: {color: colors.cpAnc_10}},
            data: []
        },
        {
            name: '企业数据',
            type: 'line',
            smooth:true,
            symbol: 'none',
            lineStyle: {normal: {color: colors.cpAnc_ent}},
            itemStyle: {normal: {color: colors.cpAnc_ent}},
            data: []
        },

        {
            name: '行业平均',
            type: 'line',
            smooth:true,
            symbol: 'none',
            lineStyle: {normal: {color: colors.cpAnc_avg}},
            itemStyle: {normal: {color: colors.cpAnc_avg}},
            data: []
        }
    ]
};

// 机构信息
var orgTrendsOption = {
    textStyle: {color:'#3c3c3c'},
    title: {
        text: '',
        left: 'center',
        top: 30,
        textBaseline: 'middle',
        textStyle: {
            fontWeight: 'bold',
            fontSize: 16,
            color:'#aaaaaa'
        }
    },
    tooltip: {
        trigger: 'axis',
        //backgroundColor:'rgba(0,0,250,0.4)',
        formatter:function(params){
            var tip=params[0].name;
            //for(var i=0;i<params.length;i++){
            //    tip+="<br>"+params[i].seriesName+": "+parseFloat(params[i].value).toFixed(2);
            //}
            tip+="<br>"+params[0].seriesName+": "+parseFloat(params[0].value).toFixed(0); // 投资事件
            tip+="<br>"+params[1].seriesName+": "+parseFloat(params[1].value).toFixed(0); // 退出事件
            tip+="<br>"+params[2].seriesName+": "+parseFloat(params[2].value).toFixed(2); // 边际收益率
            return tip;
        }
    },
    grid : [ {
        left : 40,
        right : 23,
        top : 40,
        bottom : 25,
        //height:"400px"
    } ],
    legend: {
        data: [
            //{name: '投资事件',textStyle: {color: colors.cpAnc_90}},
            //{name:'退出事件',textStyle: {color: colors.cpAnc_10}},
            //{name:'边际收益率',textStyle: {color: colors.cpAnc_ent}}
            {name: '投资事件'},
            {name:'退出事件'},
            {name:'边际收益率'}
        ],
        x:"right",
        y:"top",
        textStyle: {
            color:'#3c3c3c'
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        //margin : 10,
        //nameGap : 1,
        //axisLabel : {
        //    show:true,
        //    textStyle : {
        //        color : '#000000',
        //        fontSize : 4
        //    },
        //    margin : 8
        //    // 标签与X轴距离
        //},
        axisLine : {
            lineStyle : {
                color : 'rgba(25,25,25,1)'
            }
        },
        splitLine : {
            show : false
            // 是否显示网格
        },
        data: []
    },
    yAxis: [
        {
        type: 'value',
        scale: true,
        name:"数量",
        axisLabel : {
            textStyle : {
                color : '#3c3c3c',
                fontSize : 4
            },
            margin : 8
        },
        axisLine : {
            lineStyle : {
                color : 'rgba(25,25,25,1)'
            }
        },
        splitLine : {
            show : false
            // 是否显示网格
        },
    },
        {
            type: 'value',
            name:"边际收益率",
            min:0,
            max:1,
            scale: true,
            axisLabel : {
                textStyle : {
                    color : '#3c3c3c',
                    fontSize : 4
                },
                margin : 8
            },
            position:'right',
            axisLine : {
                lineStyle : {
                    color : 'rgba(25,25,25,1)'
                }
            },
            splitLine : {
                show : false
                // 是否显示网格
            },
        }
    ],
    series: [
        {
            name: '投资事件',
            type: 'line',
            smooth:true,
            symbol: 'none',
            //lineStyle: {normal: {color: '#516b91'}},
            itemStyle:{
                normal:{
                    color:"#93b7e3",
                    //lineStyle:{
                    //    color:"#93b7e3"
                    //}
                }
            },
            data: [],
            yAxisIndex:0,
            //itemStyle: {
            //    normal: {
            //        areaStyle: {
            //            // 区域图，纵向渐变填充
            //            color : 'rgba()'
            //        }
            //    }
            //}
        },
        {
            name: '退出事件',
            type: 'line',
            smooth:true,
            symbol: 'none',
            //lineStyle: {normal: {color: "#59c4e6"}},
            itemStyle:{
              normal:{
                  color:"#e95456",
                  //lineStyle:{
                  //    color:"#59c4e6"
                  //}
              }
            },
            data: [],
            yAxisIndex:0
        },
        {
            name: '边际收益率',
            type: 'line',
            smooth:true,
            symbol: 'none',
            lineStyle: {normal: {color: "#edafda"}},
            itemStyle:{
                normal:{
                    color:"#f7a54a", // "#817b01",
                    //lineStyle:{
                    //    color:"#a5e7f0"
                    //}
                }
            },
            data: [],
            yAxisIndex:1
        }
    ]
};

var orgTrendsOption_bak={
    title : {
        text: ''
    },
    tooltip : {
        trigger: 'axis'
        //trigger: 'axis',
        //formatter:function(data){
        //    var result=data[0][1];
        //    //console.log(data[0][1]);
        //    for(var item in data){
        //        //console.log(data[item]);
        //        result=result+"<br>"+data[item].seriesName+":"+parseFloat(data[item].value).toFixed(2);
        //    }
        //    return result;
        //}
    },
    legend: {
        data:['90分位数','10分位数','企业数据','均值']
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : [],
            splitLine : {
                show : false
                // 是否显示网格
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            },
            scale:true,
            boundaryGap:[5,5],
            splitLine : {
                show : false
                // 是否显示网格
            }

        },
        {
            type:'value',
            axisLabel : {
                formatter: '{value}'
            },
            position:'right',
            scale:true,
            boundaryGap:[0.05,0.05],
            splitLine : {
                show : false
                // 是否显示网格
            }
        }

    ],
    series : [
        {
            name:'90分位数',
            type:'line',
            smooth:true,
            data:[],
            markPoint : {
                data : [
                    //{type : 'min', name: '最小值'}
                ],
                symbolSize:1
            },
            markLine : {
                data : [
                    //{type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'10分位数',
            type:'line',
            smooth:true,
            data:[]
        },
        {
            name:'企业数据',
            smooth:true,
            type:'line',
            data:[],
            yAxisIndex:1
        },
        {
            name:'均值',
            type:'line',
            smooth:true,
            data:[],
            yAxisIndex:1
        }
    ]
};

