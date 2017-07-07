/**
 * Created by a88u on 2016/10/17.
 */

var traderSelectionsList = [ // 报告查询类型选择--Other
    {text: "O2O", value: "100001", isSelected: false},
    {text: "TMT(互联网)", value: "100002", isSelected: false},
    {text: "TMT(移动互联网)", value: "100003", isSelected: false},
    {text: "大消费/消费升级", value: "100004", isSelected: false},
    {text: "电商/跨境电商", value: "100005", isSelected: false},
    {text: "房地产/建筑", value: "100006", isSelected: false},
    {text: "新材料", value: "100007", isSelected: false},
    {text: "国企改革", value: "100008", isSelected: false},
    {text: "金融创新/征信风控", value: "100009", isSelected: false},
    {text: "新能源", value: "100010", isSelected: false},
    {text: "交通/物流", value: "100011", isSelected: false},
    {text: "国防军工", value: "100013", isSelected: false},
    {text: "农村经济", value: "100014", isSelected: false},
    {text: "企业营销", value: "100015", isSelected: false},
    {text: "人工智能", value: "100016", isSelected: false},
    {text: "社会服务", value: "100017", isSelected: false},
    {text: "医疗/保健", value: "100018", isSelected: false},
    {text: "生命科学/生物工程", value: "100019", isSelected: false},
    {text: "食品/食品安全", value: "100020", isSelected: false},
    {text: "新媒体", value: "100021", isSelected: false},
    {text: "虚拟现实", value: "100022", isSelected: false},
    {text: "互联网基础服务", value: "100023", isSelected: false},
    {text: "OTA", value: "100024", isSelected: false},
    {text: "智能出行", value: "100025", isSelected: false},
    {text: "先进制造/智能硬件", value: "100026", isSelected: false},
    {text: "机器人/无人机", value: "100027", isSelected: false},
    {text: "汽车电商/后市场", value: "100028", isSelected: false},
    {text: "电子支付/虚拟货币", value: "100029", isSelected: false},
    {text: "养老产业", value: "100030", isSelected: false},
    {text: "航空/航天", value: "100031", isSelected: false},
    {text: "传统能源", value: "100032", isSelected: false},
    {text: "资产配置/财富管理", value: "100033", isSelected: false},
    {text: "在线教育", value: "100034", isSelected: false},
    {text: "信息安全", value: "100035", isSelected: false},
    {text: "体育产业", value: "100036", isSelected: false},
    {text: "游戏/电竞", value: "100037", isSelected: false},
    {text: "文化创意/知识产权", value: "100038", isSelected: false},
    {text: "网络直播", value: "100039", isSelected: false},
    {text: "数字音乐", value: "100040", isSelected: false},
    {text: "文学/影视", value: "100041", isSelected: false},
    {text: "新三板", value: "100042", isSelected: false},
    {text: "资本事件", value: "100043", isSelected: false},
    {text: "制度创新", value: "100044", isSelected: false},
    {text: "城市规划", value: "100045", isSelected: false},
    {text: "货币政策", value: "100046", isSelected: false},
    {text: "全球财经", value: "100047", isSelected: false},
    {text: "经济数据", value: "100048", isSelected: false},
    {text: "国外科技资讯", value: "100049", isSelected: false}
];

var traderList = [
    {
        text: "互联网", isSelected: true, children: [
        {text: "在线教育", value: "100034", isSelected: true, isAll: false},
        {text: "TMT(互联网)", value: "100002", isSelected: true, isAll: false},
        {text: "TMT(移动互联网)", value: "100003", isSelected: true, isAll: false},
        {text: "互联网基础服务", value: "100023", isSelected: true, isAll: false},
        {text: "信息安全", value: "100035", isSelected: true, isAll: false},
        {text: "企业营销", value: "100015", isSelected: true, isAll: false},
    ]
    },
    {
        text: "文娱产业", isSelected: false, children: [
        {text: "新媒体", value: "100021", isSelected: true, isAll: false},
        {text: "数字音乐", value: "100040", isSelected: true, isAll: false},
        {text: "游戏/电竞", value: "100037", isSelected: true, isAll: false},
        {text: "文学/影视", value: "100041", isSelected: true, isAll: false},
        {text: "网络直播", value: "100039", isSelected: true, isAll: false},
        {text: "体育产业", value: "100036", isSelected: true, isAll: false},
    ]
    },
    {
        text: "公共服务", isSelected: false, children: [
        {text: "城市规划", value: "100045", isSelected: true, isAll: false},
        {text: "社会服务", value: "100017", isSelected: true, isAll: false},
        {text: "文化创意/知识产权", value: "100038", isSelected: true, isAll: false},
        {text: "国外科技资讯", value: "100049", isSelected: true, isAll: false},
        {text: "房地产/建筑", value: "100006", isSelected: true, isAll: false},
        {text: "交通/物流", value: "100011", isSelected: true, isAll: false},
    ]
    },
    {
        text: "经济资讯", isSelected: false, children: [
        {text: "国企改革", value: "100008", isSelected: true, isAll: false},
        {text: "制度创新", value: "100044", isSelected: true, isAll: false},
        {text: "农村经济", value: "100014", isSelected: true, isAll: false},
        {text: "经济数据", value: "100048", isSelected: true, isAll: false},
        {text: "资本事件", value: "100043", isSelected: true, isAll: false},
        {text: "全球财经", value: "100047", isSelected: true, isAll: false},
    ]
    },
    {
        text: "智能制造", isSelected: false, children: [
        {text: "人工智能", value: "100016", isSelected: true, isAll: false},
        {text: "虚拟现实", value: "100022", isSelected: true, isAll: false},
        {text: "机器人/无人机", value: "100027", isSelected: true, isAll: false},
        {text: "先进制造/智能硬件", value: "100026", isSelected: true, isAll: false},
        {text: "国防军工", value: "100013", isSelected: true, isAll: false},
        {text: "航空/航天", value: "100031", isSelected: true, isAll: false},
    ]
    },
    {
        text: "生活消费", isSelected: false, children: [
        {text: "OTA", value: "100024", isSelected: true, isAll: false},
        {text: "智能出行", value: "100025", isSelected: true, isAll: false},
        {text: "汽车电商/后市场", value: "100028", isSelected: true, isAll: false},
        {text: "大消费/消费升级", value: "100004", isSelected: true, isAll: false},
        {text: "电商/跨境电商", value: "100005", isSelected: true, isAll: false},
        {text: "O2O", value: "100001", isSelected: true, isAll: false},
    ]
    },
    {
        text: "节能环保", isSelected: false, children: [
        {text: "新能源", value: "100010", isSelected: true, isAll: false},
        {text: "传统能源", value: "100032", isSelected: true, isAll: false},
        {text: "新材料", value: "100007", isSelected: true, isAll: false},
    ]
    },
    {
        text: "医疗卫生", isSelected: false, children: [
        {text: "医疗/保健", value: "100018", isSelected: true, isAll: false},
        {text: "生命科学/生物工程", value: "100019", isSelected: true, isAll: false},
        {text: "食品/食品安全", value: "100020", isSelected: true, isAll: false},
        {text: "养老产业", value: "100030", isSelected: true, isAll: false},
    ]
    },
    {
        text: "多元金融", isSelected: false, children: [
        {text: "新三板", value: "100042", isSelected: true, isAll: false},
        {text: "金融创新/征信风控", value: "100009", isSelected: true, isAll: false},
        {text: "电子支付/虚拟货币", value: "100029", isSelected: true, isAll: false},
        {text: "资产配置/财富管理", value: "100033", isSelected: true, isAll: false},
        {text: "货币政策", value: "100046", isSelected: true, isAll: false},
    ]
    }
];

var traderItems = {
    "T100001": "O2O",
    "T100002": "TMT(互联网)",
    "T100003": "TMT(移动互联网)",
    "T100004": "大消费/消费升级",
    "T100005": "电商/跨境电商",
    "T100006": "房地产/建筑",
    "T100007": "新材料",
    "T100008": "国企改革",
    "T100009": "金融创新/征信风控",
    "T100010": "新能源",
    "T100011": "交通/物流",
    "T100013": "国防军工",
    "T100014": "农村经济",
    "T100015": "企业营销",
    "T100016": "人工智能",
    "T100017": "社会服务",
    "T100018": "医疗/保健",
    "T100019": "生命科学/生物工程",
    "T100020": "食品/食品安全",
    "T100021": "新媒体",
    "T100022": "虚拟现实",
    "T100023": "互联网基础服务",
    "T100024": "OTA",
    "T100025": "智能出行",
    "T100026": "先进制造/智能硬件",
    "T100027": "机器人/无人机",
    "T100028": "汽车电商/后市场",
    "T100029": "电子支付/虚拟货币",
    "T100030": "养老产业",
    "T100031": "航空/航天",
    "T100032": "传统能源",
    "T100033": "资产配置/财富管理",
    "T100034": "在线教育",
    "T100035": "信息安全",
    "T100036": "体育产业",
    "T100037": "游戏/电竞",
    "T100038": "文化创意/知识产权",
    "T100039": "网络直播",
    "T100040": "数字音乐",
    "T100041": "文学/影视",
    "T100042": "新三板",
    "T100043": "资本事件",
    "T100044": "制度创新",
    "T100045": "城市规划",
    "T100046": "货币政策",
    "T100047": "全球财经",
    "T100048": "经济数据",
    "T100049": "国外科技资讯"
};





