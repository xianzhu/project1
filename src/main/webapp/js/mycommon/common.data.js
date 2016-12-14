/**
 * Created by a88u on 2016/11/2.
 */

var isTest = false; // 正式版
//var isTest = true; // 测试版

var hostUrl="http://192.168.0.67:28080/";
//var hostUrl="http://192.168.0.67:18083/";
var commonUrls_real = {
    homeBasicUrl: "top/person", // 用户信息
    //homeBasicMapUrl: "top/news", // 地图新闻(locationNewsId)
    homeBasicMapUrl:"testData/homemapnews.data.json",
    homeBasicNewsUrl: "elasticsearch?mode=personal", // 推送新闻(mode=personal)
    homeBasicReportUrl: "report/personal", // 报告(type)
    homeBasicStatUrl:"top/mediasync", // 信息同步--统计信息
    homeProjectUrl:"org/recommond", // 项目信息

    userSearchUrl: "topsearch", // 综合查询(key)
    userSearchNewsUrl: "elasticsearch", // 综合查询---新闻(key)
    userSearchReportUrl: "report", // 综合查询---报告(type)

    userMonitorUrl:"custInfo/query", // 监控页---监控列表
    monitorEntCreditUrl:"entinfo/credit", // 监控页--企业法务信息
    monitorEntBasicUrl:"entinfo/basic", // 监控页--企业变更信息
    monitorEntFinanceUrl:"entinfo/finance", // 监控页--企业融资情况
    monitorEntInvestUrl:"entinfo/invest", // 监控页--企业投资企业
    monitorOrgCaptialUrl:"org/captail", // 监控页--机构投资退出事件
    monitorOrgFamilyUrl:"org/extend", // 监控页--机构族谱

    orgSearchUrl: "org/search", // 机构--查询
    orgnazationBasicUrl: "org/basic", // 机构基本信息
    orgBasicNewsUrl:"elasticsearch", // 机构--新闻
    orgnazationFundUrl: "org/fund", // 机构基金信息

    orgRptDataUrl:"org/top", // 机构--查询--股权投资（查询页初始显示）

    orgFundBasicUrl:"fund/basic", // 机构--基金详情
    orgFundCaptailUrl:"fund/event", // 机构--基金事件列表
    orgEventDetailUrl:"fund/detail", // 机构--基金事件详情

    personUrl: "investor/top", // 投资者---榜单
    personSearchUrl:"investor/search", // 投资者--查询
    personBasicUrl: "investor/basic", // 投资者--基本信息
    personDetailUrl:"investor/detail", // 投资者--基金事件详情
    personEventUrl: "investor/event", // 投资者--基金事件列表
    personIntelgeUrl:"elasticsearch", // 投资者--情报

    reportUrl: "report", // 报告查询

    companySearchUrl:"entinfo/search", // 企业查询
    companyBasicUrl:"entinfo/basic", // 企业基本信息
    companyDepthUrl:"entinfo/deep", // 企业深度信息
    companyCreditUrl:"entinfo/credit", // 企业信用报告
    companyDataUrl:"entinfo/data", // 企业数据分析
    companyNoticeUrl:"entinfo/notice", // 企业公告数据
    companyTopUrl:"entinfo/top", // 企业并购监控

    companyIdUrl:"tools/transfer", // 企业uuid和stockCode转换

    industryToolsUrl:"tools/ciassociated", // 行业统计
    benchmarkUrl:"tools/xsblistmatching", // 模拟计算
    workbenchQueryUrl:"custInfo/query", // 工作台查询
    workbenchUpdateUrl:"custInfo", // 工作台更新
    workbenchDeleteUrl:"custInfo/del", // 工作台删除

    loginUrl:"login", // 登录
    loginoutUrl: "logout" // 退出
};

var commonUrls_real_local = {
    homeBasicUrl: hostUrl+"top/person", // 用户信息
    //homeBasicMapUrl: hostUrl+"top/news", // 地图新闻(locationNewsId)
    homeBasicMapUrl:"testData/homemapnews.data.json",
    homeBasicNewsUrl: hostUrl+"elasticsearch?mode=personal", // 推送新闻(mode=personal)
    homeBasicReportUrl: hostUrl+"report/personal", // 报告(type)
    homeBasicStatUrl:hostUrl+"top/mediasync", // 信息同步--统计信息
    homeProjectUrl:hostUrl+"org/recommond", // 项目信息

    userSearchUrl: hostUrl+"topsearch", // 综合查询(key)
    userSearchNewsUrl: hostUrl+"elasticsearch", // 综合查询---新闻(key)
    userSearchReportUrl: hostUrl+"report", // 综合查询---报告(type)

    userMonitorUrl:hostUrl+"custInfo/query", // 监控页---监控列表
    monitorEntCreditUrl:hostUrl+"entinfo/credit", // 监控页--企业法务信息
    monitorEntBasicUrl:hostUrl+"entinfo/basic", // 监控页--企业变更信息
    monitorEntFinanceUrl:hostUrl+"entinfo/finance", // 监控页--企业融资情况
    monitorEntInvestUrl:hostUrl+"entinfo/invest", // 监控页--企业投资企业
    monitorOrgCaptialUrl:hostUrl+"org/captail", // 监控页--机构投资退出事件
    monitorOrgFamilyUrl:hostUrl+"org/extend", // 监控页--机构族谱

    orgSearchUrl: hostUrl+"org/search", // 机构--查询
    orgnazationBasicUrl: hostUrl+"org/basic", // 机构基本信息
    orgBasicNewsUrl:hostUrl+"elasticsearch", // 机构--新闻
    orgnazationFundUrl: hostUrl+"org/fund", // 机构基金信息

    orgRptDataUrl:hostUrl+"org/top", // 机构--查询--股权投资（查询页初始显示）

    orgFundBasicUrl:hostUrl+"fund/basic", // 机构--基金详情
    orgFundCaptailUrl:hostUrl+"fund/event", // 机构--基金事件列表
    orgEventDetailUrl:hostUrl+"fund/detail", // 机构--基金事件详情

    personUrl: hostUrl+"investor/top", // 投资者---榜单
    personSearchUrl:hostUrl+"investor/search", // 投资者--查询
    personBasicUrl: hostUrl+"investor/basic", // 投资者--基本信息
    personDetailUrl:hostUrl+"investor/detail", // 投资者--基金事件详情
    personEventUrl: hostUrl+"investor/event", // 投资者--基金事件列表
    personIntelgeUrl:hostUrl+"elasticsearch", // 投资者--情报

    reportUrl: hostUrl+"report", // 报告查询

    companySearchUrl:hostUrl+"entinfo/search", // 企业查询
    companyBasicUrl:hostUrl+"entinfo/basic", // 企业基本信息
    companyDepthUrl:hostUrl+"entinfo/deep", // 企业深度信息
    companyCreditUrl:hostUrl+"entinfo/credit", // 企业信用报告
    companyDataUrl:hostUrl+"entinfo/data", // 企业数据分析
    companyNoticeUrl:hostUrl+"entinfo/notice", // 企业公告数据
    companyTopUrl:hostUrl+"entinfo/top", // 企业并购监控

    companyIdUrl:hostUrl+"tools/transfer", // 企业uuid和stockCode转换

    industryToolsUrl:hostUrl+"tools/ciassociated", // 行业统计
    benchmarkUrl:hostUrl+"tools/xsblistmatching", // 模拟计算
    workbenchQueryUrl:hostUrl+"custInfo/query", // 工作台查询
    workbenchUpdateUrl:hostUrl+"custInfo", // 工作台更新
    workbenchDeleteUrl:hostUrl+"custInfo/del", // 工作台删除

    loginUrl:hostUrl+"login", // 登录
    loginoutUrl: hostUrl+"logout" // 退出
};

var commonUrls = commonUrls_real;
if (isTest) {
    commonUrls = commonUrls_real_local;
}

var commonPageNum = {
    homeCvReport:10, // 用户主页--行业分析
    homeReport:10, // 用户主页--研究报告

    userSearchNews: 10, // 综合查询--情报
    userSearchCv: 10, // 综合查询--行业分析
    userSearchTrader: 10, // 综合查询--研究报告

    orgSearch:10, // 机构查询
    reportSearch:10,

    personNews:20, // 投资者信息 -- 情报

    entSearchMergePageNum:20, // 企业搜索--上市公司并购事件每日监控
    orgFundEventNum:10, // 机构基金--事件
    common: 10 // 默认
};

var menuInfo={
    user:"用户",
    homepage:"主页",
    usermonitor:"监控页",
    workbench:"个人设置",
    usersearch:"综合查询",
    report:"报告",
    orgnazation:"机构信息",
    orgsearch:"机构查询",
    orgbasic:"基本信息",
    orgfund:"机构基金",
    person:"投资者信息",
    personsearch:"投资者查询",
    personbasic:"基本信息",
    company:"企业信息",
    entsearch:"企业查询",
    entbasic:"基本信息",
    entdepth:"深度报告",
    entcredit:"信用报告",
    entannounce:"公告",
    entanalysis:"数据分析",
    tools:"工具",
    benchmark:"模拟计算",
    ciassociated:"行业统计"
};




















