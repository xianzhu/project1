/**
 * Created by a88u on 2016/11/2.
 */

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
                //console.log(data[item]);
                result=result+"<br>"+data[item].seriesName+":"+parseFloat(data[item].value).toFixed(2);
            }
            return result;
        }
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
