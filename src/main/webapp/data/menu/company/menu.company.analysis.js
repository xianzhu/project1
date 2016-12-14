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
                text:"监控"
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
        text: "投资者",
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
        text: "机构",
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
        text:"��ҵ��Ϣ",
        showChild:true,
        childMenu:[
            {
                isActive:false,
                pageurl:"company",
                text:"��ҵ��ѯ"
            },
            {
                isActive:false,
                pageurl:"companyBasic",
                text:"������Ϣ"
            },
            {
                isActive:false,
                pageurl:"companyDepth",
                text:"��ȱ���"
            },
            {
                isActive:false,
                pageurl:"companyReport",
                text:"���ñ���"
            },
            {
                isActive:true,
                pageurl:"companyAnalysis",
                text:"���ݷ���"
            },
            {
                isActive:false,
                pageurl:"companyAnnounce",
                text:"����"
            }
        ]
    },
    {
        isActive: false,
        pageurl: "#",
        text: "����",
        showChild: true,
        childMenu:[
            {
                isActive:false,
                pageurl:"benchmark",
                text:"ģ�����"
            },
            {
                isActive:false,
                pageurl:"industryTools",
                text:"��ҵͳ��"
            }
        ]
    }
];



