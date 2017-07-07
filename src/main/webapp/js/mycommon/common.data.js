/**
 * Created by a88u on 2016/11/2.
 */

var isTest = false; // 正式版
// var isTest = true; // 测试版

var hostUrl="http://116.62.52.41:17081/";

var commonUrls_real = {
    loginUrl:"login", // 登录
    loginoutUrl: "logout", // 退出

    homeNewsUrl: "elasticsearch?mode=personal", // 首页--新闻
    homepageRptDataUrl:"stat/overall", // 首页-- 一级市场信心指数
    homepagePanelDataUrl:"stat/trend", // 首页--右上角统计数据
    homepageBarDataUrl:"stat/event", // 首页--今日资本事件柱状图、饼图
    homeEventpageUrl:"top/dayevent", // 首页--今日资本事件列表
    homeEventDetailUrl:"top/daydetail", // 首页--今日资本事件详情
    homepageDashDataUrl:"stat/dashboard", // 首页--仪表盘数据
    // homeCalendarUrl:"top/schedule", // 首页 -- 日历接口数据
    homeCalendarUrl:"testData/rpt.schedule.json",

    mutiSearchUrl: "topsearch", // 综合查询--基本查询
    mutiSearchNewsUrl: "elasticsearch", // 综合查询--新闻(key)
    mutiSearchReportUrl: "report", // 综合查询--报告(type)

    custMonitorUrl:"custInfo/query", // 定制监控页--监控列表

    monitorEntCreditUrl:"entinfo/credit", // 定制监控页--企业法务信息
    monitorEntBasicUrl:"entinfo/basic", // 定制监控页--企业变更信息
    monitorEntFinanceUrl:"entinfo/finance", // 定制监控页--企业融资情况
    monitorEntInvestUrl:"entinfo/invest", // 定制监控页--企业投资企业
    monitorOrgCaptialUrl:"org/captail", // 定制监控页--机构投资退出事件
    monitorOrgFamilyUrl:"org/extend", // 定制监控页--机构族谱

    sysMonitorMergeUrl:"entinfo/top", // 系统监控页--企业并购每日监控
    sysMonitorRptUrl:"org/top", // 系统监控页--股权类投资基金监控

    orgSearchUrl: "org/search", // 机构--查询
    orgnazationBasicUrl: "org/basic", // 机构--基本信息
    orgBasicElasticUrl:"elasticsearch/accurate", // 机构--新闻
    orgnazationFundUrl: "org/fund", // 机构--基金信息

    orgFundBasicUrl:"fund/basic", // 机构基金--基金详情
    orgFundCaptailUrl:"fund/event", // 机构基金--基金事件列表
    orgEventDetailUrl:"fund/detail", // 机构基金--基金事件详情

    personSearchUrl:"investor/search", // 投资者--查询
    personBasicUrl: "investor/basic", // 投资者--基本信息
    personDetailUrl:"investor/detail", // 投资者--基金事件详情
    personEventUrl: "investor/event", // 投资者--基金事件列表
    personIntelgeUrl:"elasticsearch/accurate", // 投资者--情报

    reportUrl: "report", // 报告查询
    newsUrl:"elasticsearch", // 新闻搜索 - 关键字、行业
    newsProjectUrl:"project/search", // 新闻搜索 - 项目信息

    projectUrl:"project/search", // 项目页 - 项目信息

    workbenchQueryUrl:"custInfo/query", // 个人设置--工作台查询
    workbenchUpdateUrl:"custInfo", // 个人设置--工作台更新
    workbenchDeleteUrl:"custInfo/del", // 个人设置--工作台删除

    companySearchUrl:"entinfo/search", // 企业查询
    companyBasicUrl:"entinfo/basic", // 企业--基本信息
    companyDepthUrl:"entinfo/deep", // 企业企业--深度信息
    companyCreditUrl:"entinfo/credit", // 企业--信用报告
    companyNoticeUrl:"entinfo/notice", // 企业--公告数据 -- 上市公司独有
    companyDataUrl:"entinfo/data", // 企业--数据分析 -- 上市公司独有

    companyIdUrl:"tools/transfer", // 企业uuid和stockCode转换

    industryToolsUrl:"tools/ciassociated", // 行业统计
    simulationUrl:"tools/xsblistmatching", // 模拟计算
};

var commonUrls_real_local = {
    loginUrl:hostUrl+"login", // 登录
    loginoutUrl: hostUrl+"logout", // 退出

    homeNewsUrl: hostUrl+"elasticsearch?mode=personal", // 首页--新闻
    homepageRptDataUrl:hostUrl+"stat/overall", // 首页-- 一级市场信心指数
    homepagePanelDataUrl:hostUrl+"stat/trend", // 首页--右上角统计数据
    homepageBarDataUrl:hostUrl+"stat/event", // 首页--今日资本事件柱状图、饼图
    homeEventpageUrl:hostUrl+"top/dayevent", // 首页--今日资本事件列表
    homeEventDetailUrl:hostUrl+"top/daydetail", // 首页--今日资本事件详情
    homepageDashDataUrl:hostUrl+"stat/dashboard", // 首页--仪表盘数据
    // homeCalendarUrl:hostUrl+"top/schedule", // 首页 -- 日历接口数据
    homeCalendarUrl:"testData/rpt.schedule.json",

    mutiSearchUrl: hostUrl+"topsearch", // 综合查询--基本查询
    mutiSearchNewsUrl: hostUrl+"elasticsearch", // 综合查询--新闻(key)
    mutiSearchReportUrl: hostUrl+"report", // 综合查询--报告(type)

    custMonitorUrl:hostUrl+"custInfo/query", // 定制监控页--监控列表

    monitorEntCreditUrl:hostUrl+"entinfo/credit", // 定制监控页--企业法务信息
    monitorEntBasicUrl:hostUrl+"entinfo/basic", // 定制监控页--企业变更信息
    monitorEntFinanceUrl:hostUrl+"entinfo/finance", // 定制监控页--企业融资情况
    monitorEntInvestUrl:hostUrl+"entinfo/invest", // 定制监控页--企业投资企业
    // monitorEntInvestUrl:"testData/cusmonitor.entinvest.json",
    monitorOrgCaptialUrl:hostUrl+"org/captail", // 定制监控页--机构投资退出事件
    monitorOrgFamilyUrl:hostUrl+"org/extend", // 定制监控页--机构族谱

    sysMonitorMergeUrl:hostUrl+"entinfo/top", // 系统监控页--企业并购每日监控
    sysMonitorRptUrl:hostUrl+"org/top", // 系统监控页--股权类投资基金监控

    orgSearchUrl: hostUrl+"org/search", // 机构--查询
    orgnazationBasicUrl: hostUrl+"org/basic", // 机构--基本信息
    orgBasicElasticUrl:hostUrl+"elasticsearch/accurate", // 机构--新闻
    orgnazationFundUrl: hostUrl+"org/fund", // 机构--基金信息

    orgFundBasicUrl:hostUrl+"fund/basic", // 机构基金--基金详情
    orgFundCaptailUrl:hostUrl+"fund/event", // 机构基金--基金事件列表
    orgEventDetailUrl:hostUrl+"fund/detail", // 机构基金--基金事件详情

    personSearchUrl:hostUrl+"investor/search", // 投资者--查询
    personBasicUrl: hostUrl+"investor/basic", // 投资者--基本信息
    personDetailUrl:hostUrl+"investor/detail", // 投资者--基金事件详情
    personEventUrl: hostUrl+"investor/event", // 投资者--基金事件列表
    personIntelgeUrl:hostUrl+"elasticsearch/accurate", // 投资者--情报

    reportUrl: hostUrl+"report", // 报告查询
    newsUrl:hostUrl+"elasticsearch", // 新闻搜索 - 关键字、行业
    newsProjectUrl:hostUrl+"project/search", // 新闻搜索 - 项目信息

    projectUrl:hostUrl+"project/search", // 项目页 - 项目信息

    workbenchQueryUrl:hostUrl+"custInfo/query", // 个人设置--工作台查询
    workbenchUpdateUrl:hostUrl+"custInfo", // 个人设置--工作台更新
    workbenchDeleteUrl:hostUrl+"custInfo/del", // 个人设置--工作台删除

    companySearchUrl:hostUrl+"entinfo/search", // 企业查询
    // companyBasicUrl:"testData/company.basic.json",
    // companyDepthUrl:"testData/company.depth.json",
    // companyCreditUrl:"testData/company.credit.json",
    // companyNoticeUrl:"testData/company.announce.json",
    // companyDataUrl:"testData/company.data.json",
    companyBasicUrl:hostUrl+"entinfo/basic", // 企业--基本信息
    companyDepthUrl:hostUrl+"entinfo/deep", // 企业企业--深度信息
    companyCreditUrl:hostUrl+"entinfo/credit", // 企业--信用报告
    companyNoticeUrl:hostUrl+"entinfo/notice", // 企业--公告数据 -- 上市公司独有
    companyDataUrl:hostUrl+"entinfo/data", // 企业--数据分析 -- 上市公司独有

    companyIdUrl:hostUrl+"tools/transfer", // 企业uuid和stockCode转换

    industryToolsUrl:hostUrl+"tools/ciassociated", // 行业统计
    simulationUrl:hostUrl+"tools/xsblistmatching", // 模拟计算

};

var commonUrls = commonUrls_real;
if (isTest) {
    commonUrls = commonUrls_real_local;
}

var commonPageNum = {
    topSearch_person:10, // 头部导航查询 - 投资者
    topSearch_org:10, // 头部导航查询 - 机构
    topSearch_company:10, // 头部导航查询 - 公司

    orgFocuse:5, // 机构信息页 - 最近关注企业
    orgTeam:5, // 机构信息页 - 团队
    orgFund:5, // 机构信息页 - 基金
    orgElastic:8, // 机构信息页 - 新闻
    orgFundInvEventNum:15, // 机构基金--事件
    orgFundExitEventNum:15, // 机构基金--投资退出事件

    cvReports:18, // 行业分析报告
    traderReports:18, // 研究报告

    newsList:10, // 新闻页 - 新闻
    newsTraderReports:5, // 新闻页 - 研究报告
    newsProjects:5, // 新闻页 - 项目

    projectList:8, // 项目页 - 项目列表

    sysMonitorRpt:12, // 系统监控 - 股权类投资基金监控
    sysMonitorMerge:18, // 系统监控 - 上市公司并购事件每日监控

    cusMonitorOrgInvest:6, // 定制监控 - 机构投资事件
    cusMonitorOrgFamily:6, // 定制监控 - 机构关联族谱
    cusMonitorComInvest:3, // 定制监控 - 企业投资事件
    cusMonitorComExit:3, // 定制监控 - 企业退出事件
    cusMonitorEntInvest:8, // 定制监控 - 企业投资企业

    personNews:15, // 投资者信息 -- 情报
    personEvents:5, // 投资者信息 -- 投退事件

    companyChange:2, // 企业信息 -- 企业变更

    companyEntHolder:5, // 企业信息 -- 股东
    companyInvest:5, // 企业信息 -- 投资企业
    companyBranch:5, // 企业信息 -- 分支机构
    companyTeam:5, // 企业信息 -- 高管
    companyEntCtrl:5, // 企业信息 -- 参控股子公司
    companyStockHolder:5, // 企业信息 -- 大股东
    companyOrgHolder:5, // 企业信息 -- 机构控股
    companyRelation:5, // 企业信息 -- 关联预测

    companySoftCopy:5, // 企业信息 -- 软件著作权
    companyPatent:5, // 企业信息 -- 专利

    companyLaw:5, // 企业信息 -- 法务
    companyAbnormal:5, // 企业信息 -- 经营异常
    companyEquity:5, // 企业信息 -- 股权质押
    companyMortgages:5, // 企业信息 -- 动产抵押

    companyAnalysis_1:10, // 对标预测 - 潜在并购方
    companyAnalysis_2:10, // 对标预测 - 对标上市公司

    homeEventList:7, // 主页 - 今日事件列表
    homeNewsList:6, // 主页 - 新闻列表
    homepageInvest:5, // 主页 - 投资事件弹框
    homepageExit:5, // 主页 - 退出事件弹框

    mutisearchCompany:5, // 综合查询 - 企业
    mutisearchOrg:10, // 综合查询 - 机构结果
    mutisearchCv:5, // 综合查询 - 行业分析
    mutisearchTrader:5, // 综合查询 - 研究报告
    mutiSearchNews: 13, // 综合查询--情报

    cusSetMonitor:6, // 个人设置 - 监控设置查询结果
    simulation:8, // 模拟计算















    homeCvReport:10, // 用户主页--行业分析
    homeReport:10, // 用户主页--研究报告



    orgSearch:10, // 机构查询
    reportSearch:10,


    entSearchMergePageNum:20, // 企业搜索--上市公司并购事件每日监控

    common: 10 // 默认
};

var menuInfo={
    homepage:"主页",
    monitor:"监控",
    sysMonitor:"系统监控",
    cusMonitor:"定制监控",
    ciassociated:"行业统计",
    report:"报告",
    cvReport:"行业分析",
    traderReport:"研究报告",
    project:"项目",
    news:"新闻",
    simulation:"模拟计算",
    setting:"个人设置"
};

var customerSettings={
    orgMonitorType:2, // 监控设置--机构类型
    entMonitorType:1, // 监控设置--企业类型
    monitorMaxNum:10 // 工作台 监控设置最大条数
}



















