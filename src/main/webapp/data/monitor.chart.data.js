/**
 * Created by a88u on 2016/11/30.
 */

var relation_company_option = {
    title : {
        text: '企业监控',
        x:'left',
        y:'top'
    },
    tooltip : {
        trigger: 'item',
        formatter: function(data){
            //var result="";
            ////if(data.data.type==1){ // 企业
            ////    result="企业:";
            ////}else if(data.data.type==2){ // 机构
            ////    result="机构:";
            ////}else if(data.data.type==3){ // 基金
            ////    result="基金:";
            ////}
            //
            //result=result+data.data.text;
            //console.log(data.data);
            return data.data.text;
        }
    },
    legend: {
        x: 'right',
        data:['监控源','当前点','普通点','扩展点','']
    },
    series : [
        {
            type:'force',
            ribbonType: false,
            name : "监控",
            categories : [
                {
                    name: '监控源',
                    itemStyle: {
                        normal: {
                            color : '#ff7f50'
                        }
                    }
                },
                {
                    name: '当前点',
                    itemStyle: {
                        normal: {
                            color : '#87cdfa'
                        }
                    }
                },
                {
                    name:'普通点',
                    itemStyle: {
                        normal: {
                            color : '#9acd32'
                        }
                    }
                },
                {
                    name:'扩展点',
                    itemStyle: {
                        normal: {
                            color : '#0afd82'
                        }
                    }
                },
                {
                    name:' ',
                    itemStyle:{
                        normal:{
                            color:'#ffffff',
                            nodeStyle:{
                                color:'#ffffff',
                                borderColor:'#ffffff',
                                borderWidth:0
                            },
                            label:{
                                show:false
                            }
                        },
                        enphasis:{
                            color:'#ffffff',
                            nodeStyle:{
                                color:'#ffffff',
                                borderColor:'#ffffff',
                                borderWidth:0
                            },
                            label:{
                                show:false
                            }
                        }
                    }
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: false,
                        textStyle: {
                            color: '#800080'
                        }
                    },
                    nodeStyle : {
                        brushType : 'both',
                        strokeColor : 'rgba(255,215,0,0.4)',
                        lineWidth : 1
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                },
                emphasis: {
                    label: {
                        show: false
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle : {
                        r: 30
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                }
            },
            //linkSymbol:"arrow",
            draggable: true,
            minRadius : 1,
            maxRadius : 25,
            density : 0.05,
            attractiveness: 1.2,
            nodes:[],
            links : []
        }
    ]
};

var relation_org_option = {
    title : {
        text: '机构监控',
        x:'left',
        y:'top'
    },
    tooltip : {
        trigger: 'item',
        showDelay:200,
        formatter: function(data){
            //var result="";
            ////if(data.data.type==1){ // 企业
            ////    result="企业:";
            ////}else if(data.data.type==2){ // 机构
            ////    result="机构:";
            ////}else if(data.data.type==3){ // 基金
            ////    result="基金:";
            ////}
            //
            //result=result+data.data.text;
            //console.log(data.data);
            return data.data.text;
        }
    },
    legend: {
        x: 'right',
        data:['监控源','当前点','普通点','扩展点','']
    },
    series : [
        {
            type:'force',
            ribbonType: false,
            name : "监控",
            categories : [
                {
                    name: '监控源',
                    itemStyle: {
                        normal: {
                            color : '#ff7f50'
                        }
                    }
                },
                {
                    name: '当前点',
                    itemStyle: {
                        normal: {
                            color : '#87cdfa'
                        }
                    }
                },
                {
                    name:'普通点',
                    itemStyle: {
                        normal: {
                            color : '#9acd32'
                        }
                    }
                },
                {
                    name:'扩展点',
                    itemStyle: {
                        normal: {
                            color : '#0afd82'
                        }
                    }
                },
                {
                    name:' ',
                    itemStyle:{
                        normal:{
                            color:'#ffffff',
                            nodeStyle:{
                                color:'#ffffff',
                                borderColor:'#ffffff',
                                borderWidth:0
                            },
                            label:{
                                show:false
                            }
                        },
                        enphasis:{
                            color:'#ffffff',
                            nodeStyle:{
                                color:'#ffffff',
                                borderColor:'#ffffff',
                                borderWidth:0
                            },
                            label:{
                                show:false
                            }
                        }
                    }
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: false,
                        textStyle: {
                            color: '#800080'
                        }
                    },
                    nodeStyle : {
                        brushType : 'both',
                        strokeColor : 'rgba(255,215,0,0.4)',
                        lineWidth : 1
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                },
                emphasis: {
                    label: {
                        show: false
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle : {
                        r: 30
                    },
                    linkStyle: { // 曲线
                        type: 'curve'
                    }
                }
            },
            //linkSymbol:"arrow",
            draggable: true,
            minRadius : 1,
            maxRadius : 20,
            density : 0.05,
            attractiveness: 1.2,
            nodes:[],
            links : []
        }
    ]
};

