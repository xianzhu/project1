/**
 * Created by a88u on 2016/11/30.
 */

var relation_org_option = {
    title : {
        text: '',
        x:'left',
        y:'top'
    },
    tooltip : {
        show:true,
        trigger: 'item',
        showDelay:200,
        formatter: function(data){
            return data.data.text;
        }
    },
    legend: {
        x: 'right',
        padding:[5,75,5,5],
        data:['关联经营实体','关联经营实体对外投资企业','关键企业','当前选中','投资方','被投资方','选中实体非相关'],
        selected: {
            '监控源': false,
            '关联经营实体':true,
            '关联经营实体对外投资企业':true,
            '关键企业':true,
            '当前选中':true,
            '投资方':true,
            '被投资方':true,
            '选中实体非相关':true
        }
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
                                show: false,
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
                    name:'关联经营实体对外投资企业',
                    itemStyle: {
                        normal: {
                            // color : '#9acd32'
                            color : '#9293ea',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#9293ea',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#800080',
                                    size:8
                                }
                            }
                        }
                    }
                },
                {
                    name:'关联经营实体',
                    itemStyle: {
                        normal: {
                            color : '#079709',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#079709',
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
                },
                {
                    name:'关键企业',
                    itemStyle: {
                        normal: {
                            color : '#f72719',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#f72719',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#00707f',
                                    size:8
                                }
                            }
                        }
                    }
                },
                {
                    name:'投资方',
                    itemStyle: {
                        normal: {
                            color : '#3eff14',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#3eff14',
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
                },
                {
                    name:'被投资方',
                    itemStyle: {
                        normal: {
                            color : '#899724',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#899724',
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
                },
                {
                    name:'选中实体非相关',
                    itemStyle: {
                        normal: {
                            // color : '#9acd32'
                            color : '#eee',
                            nodeStyle : {
                                brushType : 'both',
                                strokeColor : '#c1daea',
                                lineWidth : 5
                            },
                            label: {
                                show: false,
                                position:'top',
                                textStyle: {
                                    color: '#ccc',
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
            maxRadius : 16,
            linkSymbol: 'arrow',
            linkSymbolSize:[10,5],
            gravity: 1.1,
            scaling: 1.2,
            coolDown:0.9,
            // roam: 'move',
            roam: true,
            nodes:[],
            links : []
        }
    ]
};

var relation_company_option = {
    title : {
        text: '',
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
        data:['监控源','被投资企业']
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
            minRadius : 5,
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



