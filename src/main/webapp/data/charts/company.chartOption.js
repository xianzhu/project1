/**
 * Created by a88u on 2017/6/14.
 */
var radar_option = {
    title : {
        text: ''
    },
    tooltip : {
        trigger: 'axis',
        formatter:function(params){
            var tip=params[0].indicator;
            for(var i=0;i<params.length;i++){
                console.log(i,": ",params[i]);
                tip+="<br>"+params[i].name+": "+parseFloat(params[i].value).toFixed(2);
            }
            return tip;
        }
    },
    legend: {
        data:['评分','均值'],
        x:"right",
        y:"top"
    },
    calculable : true,
    polar : [
        {
            indicator : [
                {text : '基础评分', max  : 1},
                {text : '技术壁垒', max  : 1},
                {text : '信用评分', max  : 1},
                {text : '资本运作', max  : 1},
                {text : '运营评分', max  : 1}
            ],
            radius: "60%"
        }
    ],
    series : [
        {
            name: '评分',
            type: 'radar',
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: 'default'
                    }
                }
            },
            data : [
                //{
                //    value : [97, 42, 88, 94, 90],
                //    name : '舍普琴科'
                //},
            ]
        }
    ]
};

var company_analysis_option = {
    title : {
        text: ''
    },
    tooltip : {
        trigger: 'axis',
        formatter:function(data){
            var result=data[0][1];
            //console.log(data[0][1]);
            for(var item in data){
                console.log(data[item]);
                result=result+"<br>"+data[item].seriesName+":"+parseFloat(data[item].value).toFixed(2);
            }
            return result;
        }
    },
    legend: {
        data:['90分位数','10分位数','企业数据','均值']
    },
    grid:{
        height:'65%'
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
            //boundaryGap:[5,5],
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
            symbol: 'none',
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
            symbol: 'none',
            data:[]
        },
        {
            name:'企业数据',
            smooth:true,
            symbol: 'none',
            type:'line',
            data:[]
        },
        {
            name:'均值',
            type:'line',
            smooth:true,
            symbol: 'none',
            data:[]
        }
    ]
};


