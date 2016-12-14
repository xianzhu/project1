

var menuList = [
    {
        isActive:true,
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
                isActive:true,
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
        isActive:false,
        pageurl:"#",
        text:menuInfo.company,   // "企业",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"company",
                text:menuInfo.entsearch     // "企业查询"
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
                pageurl:"#",
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