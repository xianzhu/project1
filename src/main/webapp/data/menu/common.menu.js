/*
 var menuList = [
 {
 isActive:false,
 pageurl:"homepage",
 text:menuInfo.homepage,     // "主页",
 showChild:false,
 ico:"fa fa-user"
 },
 {
 isActive:false,
 pageurl:"#",
 text:menuInfo.monitor,   // "监控",
 showChild:true,
 ico:"fa fa-newspaper-o",
 childMenu:[
 {
 isActive:false,
 pageurl:"sysmonitor",
 text:menuInfo.sysMonitor    // "系统监控"
 },
 {
 isActive:false,
 pageurl:"cusmonitor",
 text:menuInfo.cusMonitor    // "定制监控"
 }
 ]
 },
 {
 isActive:false,
 pageurl:"ciassociated",
 text:menuInfo.ciassociated,   // "行业统计",
 showChild:false,
 ico:"fa fa-male",
 childMenu:[]
 }
 ];*/

var menuList = {
    "homepage": {
        "isActive": false,
        "pageurl": "homepage",
        "text": "",
        "name": menuInfo.homepage,     // "主页",
        "showChild": false,
        "ico": "fa fa-user"
    },
    "monitor": {
        "isActive": false,
        "pageurl": "#",
        "text": "",
        "name": menuInfo.monitor,   // "监控",
        "showChild": true,
        "ico": "fa fa-laptop",
        "childMenu": {
            "sysMonitor": {
                "isActive": false,
                "pageurl": "sysmonitor",
                "text": "",
                "ico":"fa fa-desktop",
                "name": menuInfo.sysMonitor    // "系统监控"
            },
            "cusMonitor": {
                "isActive": false,
                "pageurl": "cusmonitor",
                "text": "",
                "ico":"fa fa-camera-retro",
                "name": menuInfo.cusMonitor    // "定制监控"
            }
        }
    },
    "ciassociated": {
        "isActive": false,
        "pageurl": "ciassociated",
        "text": "",
        "name": menuInfo.ciassociated,   // "行业统计",
        "showChild": false,
        "ico": "fa fa-bar-chart",
        "childMenu": []
    },
    "report": {
        "isActive": false,
        "pageurl": "#",
        "text": "",
        "name": menuInfo.report,     // "报告",
        "showChild": true,
        "ico": "fa fa-file-powerpoint-o",
        "childMenu": {
            "cvReport": {
                "isActive": false,
                "pageurl": "cvReports",
                "text": "",
                "name": menuInfo.cvReport,    // "行业分析"
                "ico":"fa fa-line-chart"
            },
            "traderReport": {
                "isActive": false,
                "pageurl": "traderReports",
                "text": "",
                "name": menuInfo.traderReport,    // "研究报告"
                "ico":"fa fa-list-alt"
            }
        }
    },
    "project": {
        "isActive": false,
        "pageurl": "projects",
        "text": "",
        "name": menuInfo.project,     // "",
        "showChild": false,
        "ico": "fa fa-list"
    },
    "news": {
        "isActive": false,
        "pageurl": "news",
        "text": "",
        "name": menuInfo.news,     // "主页",
        "showChild": false,
        "ico": "fa fa-newspaper-o"
    },
    "simulation": {
        "isActive": false,
        "pageurl": "simulation",
        "text": "",
        "name": menuInfo.simulation,     // "主页",
        "showChild": false,
        "ico": "fa fa-calculator"
    },
    "setting": {
        "isActive": false,
        "pageurl": "customerSetting",
        "text": "",
        "name": menuInfo.setting,     // "主页",
        "showChild": false,
        "ico": "fa fa-cog"
    }
};
