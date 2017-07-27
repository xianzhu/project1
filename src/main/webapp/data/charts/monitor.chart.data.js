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
        show:false,
        trigger: 'item',
        formatter: function(data){
            return data.data.text;
        }
    },
    legend: {
        x: 'right',
        data:['监控源','当前选中','其他','被投资企业']
    },
    series : [
        {
            type:'force',
            name : "监控",
            categories : [
                {
                    name: '监控源',
                    itemStyle: {
                        normal: {
                            color : '#ff7f50',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#ff7f50',
                                lineWidth : 5
                            }
                        }
                    }
                },
                {
                    name: '当前选中',
                    itemStyle: {
                        normal: {
                            //color : '#87cdfa'
                            color:'#38a6ec',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#38a6ec',
                                lineWidth : 5
                            }
                        }
                    }
                },
                {
                    name:'其他',
                    itemStyle: {
                        normal: {
                            color : '#c1daea',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#c1daea',
                                lineWidth : 5
                            }
                        }
                    }
                },
                {
                    name:'被投资企业',
                    itemStyle: {
                        normal: {
                            color : '#0afd82',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#0afd82',
                                lineWidth : 5
                            }
                        }
                    }
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position:'top',
                        textStyle: {
                            color: '#800080'
                        }
                    },
                    linkStyle: {
                        // type: 'curve'
                    }
                },
                emphasis: {
                    label: {
                        show: true
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    }
                }
            },
            // linkSymbol:"arrow",
            draggable: false,
            minRadius : 3,
            maxRadius : 5,
            gravity: 1.1,
            scaling: 1.2,
            coolDown:0.9,
            roam: 'move',
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
        show:false,
        trigger: 'item',
        showDelay:200,
        formatter: function(data){
            return data.data.text;
        }
    },
    legend: {
        x: 'right',
        data:['监控源','当前选中','其他','被投资企业']
    },
    animationDurationUpdate: 0,
    animation:false,
    series : [
        {
            type:'force',
            name : "监控",
            categories : [
                {
                    name: '监控源',
                    itemStyle: {
                        normal: {
                            color : '#000000',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#000000',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#ff0000',
                                    size:8
                                }
                            }
                        }
                    }
                },
                {
                    name: '当前选中',
                    itemStyle: {
                        normal: {
                            // color:'#38a6ec',
                            color:'#0000ff',
                            nodeStyle : {
                                brushType : 'both',
                                // strokeColor : '#38a6ec',
                                strokeColor : '#0000ff',
                                lineWidth : 5
                            },
                            label: {
                                show: true,
                                position:'top',
                                textStyle: {
                                    color: '#000080',
                                    size:8
                                }
                            }
                        }
                    }
                },
                {
                    name:'其他',
                    itemStyle: {
                        normal: {
                            // color : '#9acd32'
                            color : '#c1daea',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#c1daea',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#ffff00',
                                    size:8
                                }
                            }
                        }
                    }
                },
                {
                    name:'被投资企业',
                    itemStyle: {
                        normal: {
                            color : '#ff0000',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#ff0000',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#0000ff',
                                    size:8
                                }
                            }
                        }
                    }
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        position:'top',
                        textStyle: {
                            color: '#800080',
                            size:8
                        }
                    }
                },
                emphasis: {
                    label: {
                        show: false
                    }
                }
            },
            draggable: false,
            minRadius : 3,
            maxRadius : 10,
            gravity: 1.1,
            scaling: 1.2,
            coolDown:0.9,
            roam: 'move',
            nodes:[],
            links : []
        }
    ]
};

