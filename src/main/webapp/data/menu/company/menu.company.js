/**
 * Created by a88u on 2016/10/12.
 */

var menuList = [
    {
        isActive: false,
        pageurl: "#",
        text: "用户",
        showChild: true,
        childMenu: [
            {
                isActive: false,
                pageurl: "homePage",
                text: "用户主页"
            },
            {
                isActive: false,
                pageurl: "userSearch",
                text: "综合查询"
            },
            {
                isActive:false,
                pageurl:"userMonitor",
                text:"监控页"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "orgnazation",
        text: "机构信息",
        showChild: false,
        childMenu: [
            {
                isActive: false,
                pageurl: "orgBasic",
                text: "基本信息"
            },
            {
                isActive: false,
                pageurl: "orgFund",
                text: "基金"
            },
            {
                isActive: false,
                pageurl: "orgCapital",
                text: "资本"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "personal",
        text: "投资者信息",
        showChild: false,
        childMenu: [
            {
                isActive: false,
                pageurl: "personalBasic",
                text: "基本信息"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "reportSearch",
        text: "报告",
        showChild: false
    },
    {
        isActive:true,
        pageurl:"company",
        text:"企业",
        showChild:false,
    },
    {
        isActive:false,
        pageurl:"#",
        text:"工具",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"workbench",
                text:"工作台"
            },
            {
                isActive:false,
                pageurl:"benchmark",
                text:"模拟计算"
            },
            {
                isActive:false,
                pageurl:"industryTools",
                text:"行业统计"
            }
        ]
    }
]