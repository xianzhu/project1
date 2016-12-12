/**
 * Created by a88u on 2016/8/3.
 */
// 研报----行业类型
var trader_type = [
    //{"value": "2003001", "name": "互联网基础设施", "id": "2003001"},
    {"value":"100001","name":"O2O","id":"100001"},
    {"value":"100002","name":"TMT-互联网","id":"100002"},
    {"value":"100003","name":"TMT-移动互联网","id":"100003"},
    {"value":"100004","name":"现代服务业/大消费/消费升级","id":"100004"},
    {"value":"100005","name":"商务贸易/电子商务/跨境电商","id":"100005"},
    {"value":"100006","name":"房产/地产/建筑","id":"100006"},
    {"value":"100007","name":"新材料","id":"100007"},
    {"value":"100008","name":"国企改革/金融改革","id":"100008"},
    {"value":"100009","name":"金融创新/金融技术/征信风控/Fintech","id":"100009"},
    {"value":"100010","name":"新能源/节能环保/清洁能源","id":"100010"},
    {"value":"100011","name":"交通/物流/运输","id":"100011"},
    {"value":"100013","name":"国防/军工","id":"100013"},
    {"value":"100014","name":"农村经济/农业经济/农村互联网","id":"100014"},
    {"value":"100015","name":"企业营销/广告营销","id":"100015"},
    {"value":"100016","name":"人工智能","id":"100016"},
    {"value":"100017","name":"社会服务/社保就业/公共事业","id":"100017"},
    {"value":"100018","name":"医疗/保健","id":"100018"},
    {"value":"100019","name":"生命科学/生物工程","id":"100019"},
    {"value":"100020","name":"食品/食品卫生/食品安全","id":"100020"},
    {"value":"100021","name":"新媒体","id":"100021"},
    {"value":"100022","name":"虚拟现实/VR/AR","id":"100022"},
    {"value":"100023","name":"互联网基础设施服务","id":"100023"},
    {"value":"100024","name":"OTA","id":"100024"},
    {"value":"100025","name":"智能出行","id":"100025"},
    {"value":"100026","name":"先进制造/高端制造/智能硬件","id":"100026"},
    {"value":"100027","name":"机器人/无人机","id":"100027"},
    {"value":"100028","name":"汽车电商/后市场","id":"100028"},
    {"value":"100029","name":"电子支付/虚拟货币","id":"100029"},
    {"value":"100030","name":"养老产业/养老概念","id":"100030"},
    {"value":"100031","name":"航空科技/航天科技","id":"100031"},
    {"value":"100032","name":"传统能源","id":"100032"},
    {"value":"100033","name":"资产配置/财富管理","id":"100033"},
    {"value":"100034","name":"在线教育","id":"100034"},
    {"value":"100035","name":"信息安全/网络安全","id":"100035"},
    {"value":"100036","name":"体育产业","id":"100036"},
    {"value":"100037","name":"游戏/电竞","id":"100037"},
    {"value":"100038","name":"文化创意/知识产权","id":"100038"},
    {"value":"100039","name":"网络直播","id":"100039"},
    {"value":"100040","name":"数字音乐","id":"100040"},
    {"value":"100041","name":"文学/影视","id":"100041"},
    {"value":"100042","name":"新三板","id":"100042"},
    {"value":"100043","name":"资本事件","id":"100043"},
    {"value":"100044","name":"制度创新","id":"100044"},
    {"value":"100045","name":"城市规划","id":"100045"},
    {"value":"100046","name":"货币政策","id":"100046"},
    {"value":"100047","name":"全球财经","id":"100047"},
    {"value":"100048","name":"经济数据","id":"100048"},
    {"value":"100049","name":"国外科技资讯","id":"100049"}
];

// 情报---行业分析-------类型
var intellgence_type = [
    //{"value": 0, "name": "全部"},
    {"value": "2001001", "name": "VC/PE事件统计"},
    {"value": "2001002", "name": "IPO事件统计"},
    {"value": "2001003", "name": "并购事件统计"},
    {"value": "2001004", "name": "行业统计"},
    {"value": "2001005", "name": "专题分析"},
    {"value": "2001006", "name": "热点评论"}
    //{"value": 99999, "name": "自定义"}
];

var traderShowType = {
    //"V2003001":{"name":"互联网基础设施"},
    "V100001":{"name":"O2O"},
    "V100002":{"name":"TMT-互联网"},
    "V100003":{"name":"TMT-移动互联网"},
    "V100004":{"name":"现代服务业/大消费/消费升级"},
    "V100005":{"name":"商务贸易/电子商务/跨境电商"},
    "V100006":{"name":"房产/地产/建筑"},
    "V100007":{"name":"新材料"},
    "V100008":{"name":"国企改革/金融改革"},
    "V100009":{"name":"金融创新/金融技术/征信风控/Fintech"},
    "V100010":{"name":"新能源/节能环保/清洁能源"},
    "V100011":{"name":"交通/物流/运输"},
    "V100013":{"name":"国防/军工"},
    "V100014":{"name":"农村经济/农业经济/农村互联网"},
    "V100015":{"name":"企业营销/广告营销"},
    "V100016":{"name":"人工智能"},
    "V100017":{"name":"社会服务/社保就业/公共事业"},
    "V100018":{"name":"医疗/保健"},
    "V100019":{"name":"生命科学/生物工程"},
    "V100020":{"name":"食品/食品卫生/食品安全"},
    "V100021":{"name":"新媒体"},
    "V100022":{"name":"虚拟现实/VR/AR"},
    "V100023":{"name":"互联网基础设施服务"},
    "V100024":{"name":"OTA"},
    "V100025":{"name":"智能出行"},
    "V100026":{"name":"先进制造/高端制造/智能硬件"},
    "V100027":{"name":"机器人/无人机"},
    "V100028":{"name":"汽车电商/后市场"},
    "V100029":{"name":"电子支付/虚拟货币"},
    "V100030":{"name":"养老产业/养老概念"},
    "V100031":{"name":"航空科技/航天科技"},
    "V100032":{"name":"传统能源"},
    "V100033":{"name":"资产配置/财富管理"},
    "V100034":{"name":"在线教育"},
    "V100035":{"name":"信息安全/网络安全"},
    "V100036":{"name":"体育产业"},
    "V100037":{"name":"游戏/电竞"},
    "V100038":{"name":"文化创意/知识产权"},
    "V100039":{"name":"网络直播"},
    "V100040":{"name":"数字音乐"},
    "V100041":{"name":"文学/影视"},
    "V100042":{"name":"新三板"},
    "V100043":{"name":"资本事件"},
    "V100044":{"name":"制度创新"},
    "V100045":{"name":"城市规划"},
    "V100046":{"name":"货币政策"},
    "V100047":{"name":"全球财经"},
    "V100048":{"name":"经济数据"},
    "V100049":{"name":"国外科技资讯"}
};

// 情报----------类型
var industryShowType = {
    "V2001001":{"name":"VC/PE事件统计"},
    "V2001002":{"name":"IPO事件统计"},
    "V2001003":{"name":"并购事件统计"},
    "V2001004":{"name":"行业统计"},
    "V2001005":{"name":"专题分析"},
    "V2001006":{"name":"热点评论"}
    };


var intellgence_date_type = [
    //{"value": 0, "name": "全部"},
    {"value": 1, "name": "每月"},
    {"value": 2, "name": "每季"},
    {"value": 3, "name": "半年"},
    {"value": 4, "name": "每年"}
];

var trader_date_type = [
    //{"value": 0, "name": "全部"},
    {"value": 1, "name": "每月"},
    {"value": 2, "name": "每季"},
    {"value": 3, "name": "半年"},
    {"value": 4, "name": "每年"}
];

var cvDataType={
    "V1":{"value": 1, "name": "每月"},
    "V2":{"value": 2, "name": "每季"},
    "V3":{"value": 3, "name": "半年"},
    "V4":{"value": 4, "name": "每年"}
}

var traderDataType={
    "V1":{"value": 1, "name": "每月"},
    "V2":{"value": 2, "name": "每季"},
    "V3":{"value": 3, "name": "半年"},
    "V4":{"value": 4, "name": "每年"}
}

// 类别-----------------类型


// 事件类型 -------event
var event_type = [
    //{"value": 0, "name": "全部"},
    {"value": "1", "name": "募资事件"},
    {"value": "2", "name": "投资事件"},
    {"value": "3", "name": "并购事件"},
    {"value": "4", "name": "退出事件"},
    {"value": "5", "name": "行业情报"},
    {"value": "6", "name": "交易情报"}
];

var event_showType={
    "V4001":{"name":" 募资事件"},
    "V4002":{"name":" 投资事件"},
    "V4003":{"name":" 并购事件"},
    "V4004":{"name":" 退出事件"},
    "V4005":{"name":" 行业情报"},
    "V4006":{"name":" 交易情报"}
};

var event_Type_list={
    "V1001":{"id":"V1001","text":"投资方","value":["4002"]},
    "V1002":{"id":"V1002","text":"项目负责人","value":["4001","4002","4003","4004"]},
    "V1003":{"id":"V1003","text":"基金名","value":["4001","4002","4004"]},
    "V1004":{"id":"V1004","text":"轮次","value":["4002"]},
    "V1005":{"id":"V1005","text":"金额","value":["4002","4003"]},
    "V1006":{"id":"V1006","text":"被投资方","value":["4002"]},
    "V1007":{"id":"V1007","text":"并购方","value":["4003"]},
    "V1008":{"id":"V1008","text":"并购标的","value":["4003"]},
    "V1009":{"id":"V1009","text":"退出方","value":["4004"]},
    "V1010":{"id":"V1010","text":"账面回报率","value":["4004"]},
    "V1011":{"id":"V1011","text":" 账面IRR","value":["4004"]},
    "V1012":{"id":"V1012","text":"募集方","value":["4001"]},
    "V1013":{"id":"V1013","text":"计划募集额度","value":["4001"]},
    "V1014":{"id":"V1014","text":"实际募集额度","value":["4001"]},
    "V1015":{"id":"V1015","text":"LP名录","value":["4001"]}
    //"V1016":{"id":"V1016","text":"消息来源","value":["4001","4002","4003","4004","4005","4006"]},
    //"V1017":{"id":"V1017","text":"事件发生日期","value":["4001","4002","4003","4004","4005","4006"]},
    //"V1018":{"id":"V1018","text":"详情","value":["4001","4002","4003","4004","4005","4006"]}
}

var init_event_param={
    "V1001":"",
    "V1002":"",
    "V1003":"",
    "V1004":"",
    "V1005":"",
    "V1006":"",
    "V1007":"",
    "V1008":"",
    "V1009":"",
    "V1010":"",
    "V1011":"",
    "V1012":"",
    "V1013":"",
    "V1014":"",
    "V1015":"",
    "V1016":"",
    "V1017":"",
    "V1018":""
};

var monitor_type=[
    {"value":1,"name":"企业"},
    {"value":2,"name":"机构"}
    //{"value":3,"name":"基金"}, // 因基金填充内容过少，暂时不如监控
    //{"value":4,"name":"投融资"}
];



