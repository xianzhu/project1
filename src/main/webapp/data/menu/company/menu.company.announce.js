/**
 * Created by a88u on 2016/12/13.
 */

var menuList;
var menuList_stock = [
    {
        isActive: false,
        pageurl: "#",
        text: "用户",
        showChild: true,
        childMenu: [
            {
                isActive: false,
                pageurl: "homePage",
                text: "主页"
            },
            {
                isActive:false,
                pageurl:"userMonitor",
                text:"监控页"
            },
            {
                isActive:false,
                pageurl:"workbench",
                text:"个人设置"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"userSearch",
        text:"综合查询"
    },
    {
        isActive:false,
        pageurl:"reportSearch",
        text:"报告",
        showChild:false
    },
    {
        isActive: false,
        pageurl: "#",
        text: "投资者信息",
        showChild: true,
        childMenu: [
            {
                isActive:false,
                pageurl:"personal",
                text:"投资者查询"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "#",
        text: "机构信息",
        showChild: true,
        childMenu: [
            {
                isActive:false,
                pageurl:"orgnazation",
                text:"机构查询"
            }
        ]
    },
    {
        isActive:true,
        pageurl:"#",
        text:"企业信息",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"company",
                text:"企业查询"
            },
            {
                isActive:false,
                pageurl:"companyBasic",
                text:"基本信息"
            },
            {
                isActive:false,
                pageurl:"companyDepth",
                text:"深度报告"
            },
            {
                isActive:false,
                pageurl:"companyReport",
                text:"信用报告"
            },
            {
                isActive:false,
                pageurl:"companyAnalysis",
                text:"数据分析"
            },
            {
                isActive:true,
                pageurl:"companyAnnounce",
                text:"公告"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "#",
        text: "工具",
        showChild: true,
        childMenu:[
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
];




