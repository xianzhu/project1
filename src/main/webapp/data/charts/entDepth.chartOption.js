/**
 * Created by a88u on 2016/11/2.
 */

var radar_option = {
    title : {
        text: ''
    },
    tooltip : {
        trigger: 'axis'
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
