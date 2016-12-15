
var menuList;
var menuList_stock = [
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.user,     // "用户",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"homePage",
                text:menuInfo.homepage     // "主页"
            },
            {
                isActive:false,
                pageurl:"userMonitor",
                text:menuInfo.usermonitor   // "监控页"
            },
            {
                isActive:false,
                pageurl:"workbench",
                text:menuInfo.workbench     // "个人设置"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"reportSearch",
        text:menuInfo.report,   // "报告",
        showChild:false,
        childMenu:[]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.person,   // "投资者信息",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"personal",
                text:menuInfo.personsearch    // "投资者查询"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.orgnazation,  // "机构信息",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"orgnazation",
                text:menuInfo.orgsearch    // "机构查询"
            }
        ]
    },
    {
        isActive:true,
        pageurl:"#",
        text:menuInfo.company,   // "企业",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"company",
                text:menuInfo.entsearch     // "企业查询"
            },
            {
                isActive:false,
                pageurl:"companyBasic",
                text:menuInfo.entbasic      // "基本信息"
            },
            {
                isActive:true,
                pageurl:"companyDepth",
                text:menuInfo.entdepth  // "深度报告"
            },
            {
                isActive:false,
                pageurl:"companyReport",
                text:menuInfo.entcredit     // "信用报告"
            },
            {
                isActive:false,
                pageurl:"companyAnalysis",
                text:menuInfo.entanalysis   // "数据分析"
            },
            {
                isActive:false,
                pageurl:"companyAnnounce",
                text:menuInfo.entannounce   // "公告"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.tools,     // "工具",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"userSearch",
                text:menuInfo.usersearch     // "综合查询",
            },
            {
                isActive:false,
                pageurl:"benchmark",
                text:menuInfo.benchmark     // "模拟计算"
            },
            {
                isActive:false,
                pageurl:"industryTools",
                text:menuInfo.ciassociated  // "行业统计"
            }
        ]
    }
];

var menuList_qxb = [
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.user,     // "用户",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"homePage",
                text:menuInfo.homepage     // "主页"
            },
            {
                isActive:false,
                pageurl:"userMonitor",
                text:menuInfo.usermonitor   // "监控页"
            },
            {
                isActive:false,
                pageurl:"workbench",
                text:menuInfo.workbench     // "个人设置"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"reportSearch",
        text:menuInfo.report,   // "报告",
        showChild:false,
        childMenu:[]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.person,   // "投资者信息",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"personal",
                text:menuInfo.personsearch    // "投资者查询"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.orgnazation,  // "机构信息",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"orgnazation",
                text:menuInfo.orgsearch    // "机构查询"
            }
        ]
    },
    {
        isActive:true,
        pageurl:"#",
        text:menuInfo.company,   // "企业",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"company",
                text:menuInfo.entsearch     // "企业查询"
            },
            {
                isActive:false,
                pageurl:"companyBasic",
                text:menuInfo.entbasic      // "基本信息"
            },
            {
                isActive:true,
                pageurl:"companyDepth",
                text:menuInfo.entdepth  // "深度报告"
            },
            {
                isActive:false,
                pageurl:"companyReport",
                text:menuInfo.entcredit     // "信用报告"
            }
        ]
    },
    {
        isActive:false,
        pageurl:"#",
        text:menuInfo.tools,     // "工具",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"userSearch",
                text:menuInfo.usersearch     // "综合查询",
            },
            {
                isActive:false,
                pageurl:"benchmark",
                text:menuInfo.benchmark     // "模拟计算"
            },
            {
                isActive:false,
                pageurl:"industryTools",
                text:menuInfo.ciassociated  // "行业统计"
            }
        ]
    }
];


