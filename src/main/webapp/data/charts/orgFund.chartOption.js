/**
 * Created by a88u on 2016/12/9.
 */

// 力导向图，机构基金页面

var tree_force_option_nonode = {
    title: {
        show: false,
        text: '',
        x: 'right',
        y: 'bottom'
    },
    tooltip: {
        trigger: 'item',
        formatter: function (data) {
            //var result="hh:";
            //for(var i in data.data){
            //    result=result+"<br>"+i+":"+data.data[i];
            //}
            var result = data.data.text;
            //console.log(data.data);
            return result;
        }
    },
    toolbox: {
        show: true,
        feature: {
            restore: {show: false},
            magicType: {show: false, type: ['force', 'chord']},
            saveAsImage: {show: true}
        }
    },
    legend: {
        x: 'left',
        data: ['基金', '投资事件', '退出事件']
    },
    series: [
        {
            type: 'force',
            name: "Force tree",
            ribbonType: false,
            categories: [
                {
                    name: '基金'
                },
                {
                    name: '投资事件'
                },
                {
                    name: '退出事件'
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: false,
                        //formatter: function (params, ticket, callback) {
                        //    console.log(params.name, params.data);
                        //    var res= params;
                        //    setTimeout(function (){callback(ticket, res);}, 1000);
                        //    return 'loading';
                        //},

                        //formatter:function (params,ticket,callback) {
                        //
                        //    var result="hh:";
                        //    for(var i in params){
                        //        result=result+"\n"+i+":"+params[i];
                        //    }
                        //    alert(result);
                        //    return result;
                        //    //setTimeout(function (){callback(ticket, res);}, 1000);
                        //    //return 'loading';
                        //}
                        //position:"left",
                        //formatter:"{a}:{c}:{d}:{b}"

                    },
                    nodeStyle: {
                        brushType: 'both',
                        borderColor: 'rgba(255,215,0,0.6)',
                        borderWidth: 1
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                }
            },
            minRadius: 10,
            maxRadius: 20,
            nodes: [],
            links: [],
            steps: 1
        }
    ]
};

var tree_force_option = {
    title: {
        show: false,
        text: '',
        x: 'right',
        y: 'bottom'
    },
    tooltip: {
        trigger: 'item',
        formatter: function (data) {
            //var result="hh:";
            //for(var i in data.data){
            //    result=result+"<br>"+i+":"+data.data[i];
            //}
            var result = data.data.text;
            //console.log(data.data);
            return result;
        }
    },
    toolbox: {
        show: true,
        feature: {
            restore: {show: false},
            magicType: {show: false, type: ['force', 'chord']},
            saveAsImage: {show: true}
        }
    },
    legend: {
        x: 'left',
        data: ['基金', '投资', '退出', '投资事件', '退出事件']
    },
    series: [
        {
            type: 'force',
            name: "Force tree",
            ribbonType: false,
            categories: [
                {
                    name: '基金'
                },
                {
                    name: '投资'
                },
                {
                    name: '退出'
                },
                {
                    name: '投资事件'
                },
                {
                    name: '退出事件'
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: false,
                        //formatter: function (params, ticket, callback) {
                        //    console.log(params.name, params.data);
                        //    var res= params;
                        //    setTimeout(function (){callback(ticket, res);}, 1000);
                        //    return 'loading';
                        //},

                        //formatter:function (params,ticket,callback) {
                        //
                        //    var result="hh:";
                        //    for(var i in params){
                        //        result=result+"\n"+i+":"+params[i];
                        //    }
                        //    alert(result);
                        //    return result;
                        //    //setTimeout(function (){callback(ticket, res);}, 1000);
                        //    //return 'loading';
                        //}
                        //position:"left",
                        //formatter:"{a}:{c}:{d}:{b}"

                    },
                    nodeStyle: {
                        brushType: 'both',
                        borderColor: 'rgba(255,215,0,0.6)',
                        borderWidth: 1
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                }
            },
            minRadius: 10,
            maxRadius: 30,
            coolDown: 0.995,
            //steps: 1,
            nodes: [],
            links: [],
            steps: 1
        }
    ]
};

var invest_exit_tree_option = {
    title: {
        text: '投资-退出关系图谱',
        show:false
    },
    toolbox: {
        show: true,
        feature: {
            saveAsImage: {show: true}
        }
    },
    series: [
        {
            name: '树图',
            type: 'tree',
            orient: 'horizontal',  // vertical horizontal
            rootLocation: {x: 100, y: 230}, // 根节点位置  {x: 100, y: 'center'}
            nodePadding: 8,
            layerPadding: 200,
            hoverable: false,
            roam: true,
            symbolSize: 6,
            itemStyle: {
                normal: {
                    color: '#4883b4',
                    label: {
                        show: true,
                        position: 'right',
                        formatter: "{b}",
                        textStyle: {
                            color: '#000',
                            fontSize: 5
                        }
                    },
                    lineStyle: {
                        color: '#ccc',
                        type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                    }
                },
                emphasis: {
                    color: '#4883b4',
                    label: {
                        show: false
                    },
                    borderWidth: 0
                }
            },
            data: []
        }
    ]
};



