/**
 * Created by a88u on 2016/10/23.
 */

var cOperationType={
    insert:"add",
    delete:"del",
    update:"update"
}

var commonObjType={
    event:{id:1,value:"event"},
    cv:{id:2,value:"cv"},
    trader:{id:3,value:"trader"},
    cousult:{id:4,value:"consult"},
    monitor:{id:5,value:"monitor"},
    feedback:{id:6,value:"qa"}
}

$('#eventTimePicker').datepicker({dateFormat:'yy-mm-dd'});

var v_maskmodalModel=new Vue({
    el:"#v-maskmodalModel",
    data:{
        modifyItemId:"",

        currentEventModel:0,
        currentIntelModel:0,
        currentResearchModel:0,
        currentAdvisoryModel:0,
        currentMonitorModel:0,

        intelTypeList: intellgence_type, // 行业类型列表
        intelDateList: intellgence_date_type, // 行业时间列表
        intelTypeSelect:"", // 行业类型选择
        intelDateSelect:"", // 行业时间选择
        intelCustContent:"", // 行业自定义内容

        researchTypeList: trader_type, // 研报类型列表
        researchDateList: trader_date_type, // 研报时间列表
        researchTypeSelect:"", // 研报类型选择
        researchDateSelect:"", // 研报时间选择

        eventTypeList: event_type, // 事件类型列表
        eventTypeSelect:"", // 事件类型选择
        eventTitle:"", // 事件标题
        eventParams:clone(init_event_param), // 用于数据绑定的事件参数

        advisoryContent:"",
        feedbackContent:"",

        monitorTypeList:monitor_type, // 监控类型列表
        monitorTypeSelect:"", // 监控类型选择
        monitorContent:"", // 监控关键字
        monitorId:"", // 监控id
        monitorSearchShow:false, // 监控查询
        monitorResultList:[],
        monitorSearchKey:"",

        showEventDiv:false,
        showResearchDiv:false,
        showIntelligenceDiv:false,
        showAdvisoryDiv:false,
        showMonitorDiv:false,
        showFeedbackDiv:false
    },
    methods:{
        cancelEvent:function(){
            this.showEventDiv=false;
            this.currentEventModel=0;
        },
        cancelIntel: function () {
            this.showIntelligenceDiv = false;
            this.currentIntelModel = 0;
        },
        cancelResearch: function () {
            this.showResearchDiv = false;
            this.currentResearchModel = 0;
        },
        cancelAdvisory: function () {
            this.showAdvisoryDiv = false;
            this.currentAdvisoryModel = 0;
        },
        cancelMonitor: function () {
            this.showMonitorDiv = false;
            this.currentMonitorModel = 0;
        },
        cancelFeedback:function(){
            this.showFeedbackDiv = false;
            this.currentFeedbackModel = 0;
        },
        submitFeedback:function(){
            console.log(this.feedbackContent);
            var datas={
                content:this.feedbackContent
            };
            var operation_type="";
            if(this.currentAdvisoryModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentAdvisoryModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.feedback);
            this.showFeedbackDiv=false;
        },
        submitEvent:function(){
            var etype=this.eventTypeSelect;
            var eTitle=this.eventTitle;
            //var econtent=JSON.stringify(this.eventParams);

            if(eTitle==""){
                showInfo("提示","标题不能为空");
                return;
            }

            var datas={
                type:etype,
                title:eTitle,
                leader:this.eventParams.V1002, // 项目负责人
                fund:this.eventParams.V1003, // 基金名称
                gpName:this.eventParams.V1012, // 募资 - 资金募集方
                lpName:this.eventParams.V1015, // 募资 - 资金提供方
                planLine:this.eventParams.V1013, // 募资 - 计划募集额度
                realLine:this.eventParams.V1014, // 募资 - 实际募集额度
                vcName:this.eventParams.V1001, // 投资 - 投资方
                teamName:this.eventParams.V1006, // 投资 - 被投资方
                round:this.eventParams.V1004, // 投资 - 轮次
                maPay:this.eventParams.V1005, // 并购 - 金额
                maBuyer:this.eventParams.V1007, // 并购 - 买方
                maTarget:this.eventParams.V1008, // 并购 - 标的
                exitName:this.eventParams.V1009, // 退出 - 退出方
                exitBringRate:this.eventParams.V1010, // 退出 - 账面回报率
                exitIrr:this.eventParams.V1011, // 退出 - 账面IRR
                happenDate:this.eventParams.V1017, // 发生日期
                srcInfo:this.eventParams.V1016, // 消息来源
                content:this.eventParams.V1018 // 事件详情
            };
            var operation_type="";
            if(this.currentEventModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentEventModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.event);
            this.showEventDiv=false;
        },
        submitAdvisory:function(){
            var econtent=this.advisoryContent;

            if(econtent==""){
                return;
            }

            var datas={
                content:econtent
            };

            var operation_type="";
            if(this.currentAdvisoryModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentAdvisoryModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.cousult);
            this.showAdvisoryDiv=false;
        },
        submitIntel:function(){
            var etype=this.intelTypeSelect;
            var eFreq=this.intelDateSelect;
            var econtent=this.intelCustContent;

            var datas={
                type:etype,
                frequency:eFreq
                //content:econtent
            };
            var operation_type="";
            if(this.currentIntelModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentIntelModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.cv);
            this.showIntelligenceDiv=false;
        },
        submitResearch:function(){
            var etype=this.researchTypeSelect;
            var eFreq=this.researchDateSelect;

            var datas={
                type:etype,
                frequency:eFreq
            };
            var operation_type="";
            if(this.currentResearchModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentResearchModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.trader);
            this.showResearchDiv=false;
        },
        submitMonitor:function(){
            var etype=this.monitorTypeSelect;
            var ekey=this.monitorContent;
            var eid=this.monitorId;

            if(ekey==""){
                showInfo("提示","请选择监控内容！");
                return;
            }

            var datas={
                type:etype,
                content:ekey
            };
            if(etype==1){ // 企业
                datas.uuid=eid;
            }else{ // 机构
                datas.orgId=eid;
            }
            var operation_type="";
            if(this.currentMonitorModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentMonitorModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            console.log(datas);
            doItemOperation(datas,operation_type,commonObjType.monitor);
            this.showMonitorDiv=false;
        },
        doMonitorSearch:function(){
            var key=this.monitorSearchKey;
            if(key==""){
                showInfo("提示","请输入查询关键字！");
                return;
            }

            var type=this.monitorTypeSelect;
            console.log(type,key);
            if(type==1){ // 企业
                searchCompanyInfo(key);
            }else if(type==2){ // 机构
                searchOrgInfo(key);
            }
            //this.monitorSearchShow=true;
        },
        selectedMonitor:function(sItem,event){

            var mevent=window.event||event;
            //console.log(event);
            console.log(sItem.name,sItem.id);

            var ele=$(mevent.target);
            console.log(ele.eq(0));

            var eleItems=ele.eq(0).parent().parent().find("tr");
            for(var i=0;i<eleItems.length;i++){
                var item=$(eleItems[i]);
                item.removeClass("selected");
            }

            ele.eq(0).parent().addClass("selected");

            this.monitorContent=sItem.name;
            this.monitorId=sItem.id;
        }
    },
    filters:{
        checkEventParam:function(value){
            return isEventParamShow(value,this.eventTypeSelect);
        },
        checkIntelParam:function(value){
            if(this.intelTypeSelect==99999){
                return true;
            }
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        }
    }
});

var v_workbenchModel=new Vue({
    el:"#v-workbenchModel",
    data: {
        commonObj:commonObjType,
        eventList:[], // 事件
        researchList:[], // 研究报告
        advisoryList:[], // 业务咨询
        //feedbackList:[],
        intelList:[], // 行业分析

        feedbackContent:"", // 问题反馈
        monitorList:"" // 监控关键字
    },
    ready: function () {
    },
    methods: {
        doEventEdit:function(item){
            v_maskmodalModel.$data.modifyItemId = item.id;

            v_maskmodalModel.$data.eventParams = getEventItemJson(item);
            console.log(v_maskmodalModel.$data.eventParams);
            v_maskmodalModel.$data.eventTitle=item.title;
            v_maskmodalModel.$data.eventTypeSelect = item.type;
            v_maskmodalModel.$data.showEventDiv = true;
            v_maskmodalModel.$data.currentEventModel = 1;
        },
        doEventAdd:function(item){
            v_maskmodalModel.$data.eventTypeSelect ="1";
            v_maskmodalModel.$data.eventTitle="";
            v_maskmodalModel.$data.eventParams = clone(init_event_param);
            v_maskmodalModel.$data.showEventDiv = true;
            v_maskmodalModel.$data.currentEventModel = 2;
        },
        doAdvisoryEdit:function(item){
            v_maskmodalModel.$data.modifyItemId = item.id;
            v_maskmodalModel.$data.advisoryContent=item.content;
            v_maskmodalModel.$data.showAdvisoryDiv = true;
            v_maskmodalModel.$data.currentAdvisoryModel = 1;
        },
        doAdvisoryAdd:function(item){
            v_maskmodalModel.$data.advisoryContent="";
            v_maskmodalModel.$data.showAdvisoryDiv = true;
            v_maskmodalModel.$data.currentAdvisoryModel = 2;
        },
        doIntelEdit:function(item){
            v_maskmodalModel.$data.modifyItemId = item.id;

            v_maskmodalModel.$data.intelTypeSelect=item.type; // 行业类型选择
            v_maskmodalModel.$data.intelDateSelect=item.frequency; // 行业时间选择
            //v_maskmodalModel.$data.intelCustContent=item.content; // 行业自定义内容

            v_maskmodalModel.$data.showIntelligenceDiv = true;
            v_maskmodalModel.$data.currentIntelModel = 1;
        },
        doMonitorEdit:function(item){
            $("#monitor_result_table").DataTable().destroy();
            v_maskmodalModel.$data.modifyItemId = item.id;
            v_maskmodalModel.$data.monitorTypeSelect=item.type;
            v_maskmodalModel.$data.monitorContent=item.content;
            v_maskmodalModel.$data.monitorSearchKey="";
            v_maskmodalModel.$data.monitorId=item.mid;
            v_maskmodalModel.$data.showMonitorDiv=true;
            v_maskmodalModel.$data.monitorSearchShow=false;
            v_maskmodalModel.$data.monitorResultList=[];
            v_maskmodalModel.$data.currentMonitorModel=1;
            v_maskmodalModel.$nextTick(function(){
                bindSimpleDataTable("monitor_result_table",10);
            });
        },
        doIntelAdd:function(item){
            v_maskmodalModel.$data.intelTypeSelect="2001001"; // 行业类型选择
            v_maskmodalModel.$data.intelDateSelect=1; // 行业时间选择
            v_maskmodalModel.$data.intelCustContent=""; // 行业自定义内容

            v_maskmodalModel.$data.showIntelligenceDiv = true;
            v_maskmodalModel.$data.currentIntelModel = 2;
        },
        doResearchEdit:function(item){
            v_maskmodalModel.$data.modifyItemId = item.id;
            v_maskmodalModel.$data.researchTypeSelect=item.type;
            v_maskmodalModel.$data.researchDateSelect=item.frequency;

            v_maskmodalModel.$data.showResearchDiv = true;
            v_maskmodalModel.$data.currentResearchModel = 1;
        },
        doResearchAdd:function(item){
            v_maskmodalModel.$data.researchTypeSelect="100001";
            v_maskmodalModel.$data.researchDateSelect=1;

            v_maskmodalModel.$data.showResearchDiv = true;
            v_maskmodalModel.$data.currentResearchModel = 2;
        },
        doMonitorAdd: function () {
            if (v_workbenchModel.$data.monitorList.length >= 3) {
                showInfo("提示", "目前仅支持3条监控设置，如需帮助，请联系我们！");
                return;
            }
            $("#monitor_result_table").DataTable().destroy();
            v_maskmodalModel.$data.monitorTypeSelect = "1";
            v_maskmodalModel.$data.monitorContent = "";
            v_maskmodalModel.$data.monitorSearchKey="";
            v_maskmodalModel.$data.monitorResultList=[];
            v_maskmodalModel.$data.monitorId="";

            v_maskmodalModel.$data.showMonitorDiv = true;
            v_maskmodalModel.$data.monitorSearchShow=false;

            v_maskmodalModel.$data.currentMonitorModel = 2;
            v_maskmodalModel.$nextTick(function(){
                bindSimpleDataTable("monitor_result_table",10);
            });
        },

        doDelete:function(id,obj){ // 加一个监控，需要根据servlet配置，待定
            doItemDelete(id,obj);
        },
        doFeedbackSubmit:function(){
            var content=this.feedbackContent;
            console.log(content);
            if(content==""){
                showInfo("请输入","请填写反馈内容后提交。谢谢！");
                return;
            }
            var datas={
                content:content
            };
            feedbackSubmit(datas);
        }
    },
    filters: {
        statusShowFilter: function (value) {
            var result = "";
            if (value == 1) {
                result = "未处理";
            } else if (value == 2) {
                result = "审核中";
            } else if (value == 3) {
                result = "审核无效";
            } else if (value == 4) {
                result = "审核成功";
            } else {
                result = "未处理";
            }
            return result;
        },
        checkEmptyFilter: function (value) {
            var result = false;
            if (value && value.length > 0) {
                result = true;
            }
            return result;
        },
        eventShowFilter: function (value) {
            if(event_showType["V400" + value]) {
                var result = event_showType["V400" + value].name;
                if (result) {
                    return result;
                }
            }
            return "";
        },
        cvShowFilter: function (value) {
            if(industryShowType["V" + value]) {
                var result = industryShowType["V" + value].name;
                console.log(value, ', ', result);
                if (result) {
                    return result;
                }
            }
            return "";
        },
        cvFrequencyFilter: function (value) {
            if (cvDataType["V" + value]) {
                var result = cvDataType["V" + value].name;
                if (result) {
                    return result;
                }
            }
            return "";
        },
        traderShowFilter: function (value) {
            if(traderShowType["V" + value]) {
                var result = traderShowType["V" + value].name;
                console.log(value, ', ', result);
                if (result) {
                    return result;
                }
            }
            return "";
        },
        traderFrequencyFilter: function (value) {
            if(traderDataType["V"+value]){
                var result=traderDataType["V"+value].name;
                if(result){
                    return result;
                }
            }
            return "";
        },
        monitorShowFilter: function (value) {
            var result = "";
            //console.log(value, ', ', result);
            if (value == "1") {
                result = "企业";
            } else if (value == "2") {
                result = "机构";
            }
            //else if (value == "3") { // 去掉基金选项
            //    result = "基金";
            //}
            return result;
        }
    }
});

getDataList();

// 初始化数据
function getDataList(){
    $.ajax({
        url: commonUrls.workbenchQueryUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            object:""
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                v_workbenchModel.$data.intelList=response.cvList; // 行业分析
                v_workbenchModel.$data.advisoryList=response.consultList; // 业务咨询
                v_workbenchModel.$data.researchList=response.traderList; // 研究报告

                getMonitorList(response.monitorList); // 监控
                v_workbenchModel.$data.eventList=response.eventList; // 事件
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

// 删除
function doItemDelete(id,object){
    console.log(object);
    var durl=commonUrls.workbenchDeleteUrl+"/"+object.value;
    $.ajax({
        url: durl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            id:id
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure: ",res.message);
                showInfo("提示","操作未成功."+res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                showInfo("成功","删除成功!");
                refreshData(object.id);
                return;
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
            showInfo("提示","操作未成功."+status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });

}

// 刷新
function refreshData(objId){
    $.ajax({
        url: commonUrls.workbenchQueryUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            object:objId
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                if(objId==commonObjType.cv.id){ // 行业分析
                    v_workbenchModel.$data.intelList=response.cvList;
                }else if(objId==commonObjType.cousult.id){ // 业务咨询
                    v_workbenchModel.$data.advisoryList=response.consultList;
                }else if(objId==commonObjType.trader.id){ // 研究报告
                    v_workbenchModel.$data.researchList=response.traderList;
                }else if(objId==commonObjType.monitor.id){ // 监控
                    getMonitorList(response.monitorList);
                }else if(objId==commonObjType.event.id){ // 事件
                    v_workbenchModel.$data.eventList=response.eventList;
                }else if(objId==commonObjType.feedback.id){
                    //v_workbenchModel.$data.feedbackList=response.consultList;
                }
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

function getMonitorList(list){
    var result=[];
    for(var i=0;i<list.length;i++){
        var item=list[i];
        //console.log(item);
        if(item.type==1){
            result.push({content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.uuid});
        }else if(item.type==2){
            result.push({content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.orgId});
        }
    }
    //console.log(result);
    v_workbenchModel.$data.monitorList=result;
}

function doItemOperation(datas,operaType,object){
    console.log(datas);
    var ourl=commonUrls.workbenchUpdateUrl+"/"+operaType+"/"+object.value;

    $.ajax({
        url: ourl,              //请求地址
        type: "POST",                            //请求方式
        data: datas,
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure: ",res.message);
                showInfo("提示","操作未成功."+res.message);
                goToNotlogon();
            }else if(res.status=='success') {
                showInfo("成功","操作成功!");
                refreshData(object.id);
                return;
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
            showInfo("提示","操作未成功."+status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });

}

// 问题反馈
function feedbackSubmit(data){
    var furl=commonUrls.workbenchUpdateUrl+"/"+cOperationType.insert+"/"+commonObjType.feedback.value;
    console.log(furl);
    $.ajax({
        url: furl,              //请求地址
        type: "POST",                            //请求方式
        data: {
            content:data.content,
            uid:v_userModel.$data.uname
        },
        dataType: "json",
        success: function (res) {
            if (res.status != 'success') {
                showInfo("操作失败！", res.message);
                return;
            } else {
                showInfo("成功", "感谢您的反馈，我们将尽快处理。谢谢！");
                return;
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
            showInfo("提示","操作未成功."+status);
        },
        statusCode: {
            404: function() {
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

function getEventItemJson(item){
    var data={
        V1002:item.leader, // 项目负责人
        V1003:item.fund, // 基金名称
        V1012:item.gpName, // 募资 - 资金募集方
        V1015:item.lpName, // 募资 - 资金提供方
        V1013:item.planLine, // 募资 - 计划募集额度
        V1014:item.realLine, // 募资 - 实际募集额度
        V1001:item.vcName, // 投资 - 投资方
        V1006:item.teamName, // 投资 - 被投资方
        V1004:item.round, // 投资 - 轮次
        V1005:item.maPay, // 并购 - 金额
        V1007:item.maBuyer, // 并购 - 买方
        V1008:item.maTarget, // 并购 - 标的
        V1009:item.exitName, // 退出 - 退出方
        V1010:item.exitBringRate, // 退出 - 账面回报率
        V1011:item.exitIrr, // 退出 - 账面IRR
        V1017:item.happenDate, // 发生日期
        V1016:item.srcInfo, // 消息来源
        V1018:item.content // 事件详情
    };
    console.log(data);
    return data;
}

function searchCompanyInfo(key){
    $.ajax({
        url:commonUrls.companySearchUrl,
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            type:false // 不指定上市公司
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                showInfo("提示","企业查询失败!");
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                var resultList=[];
                $("#monitor_result_table").DataTable().destroy();
                for(var i=0;i<response.searchResult.length;i++){
                    var item=response.searchResult[i];
                    resultList.push({name:item.ent_name,id:item.ent_id})
                }
                v_maskmodalModel.$data.monitorResultList=resultList;
                v_maskmodalModel.$nextTick(function(){
                    bindSimpleDataTable("monitor_result_table",10);
                    v_maskmodalModel.$data.monitorSearchShow=true;
                });
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        }
    });
}

function searchOrgInfo(key){
    var from=0;
    $.ajax({
        url:commonUrls.orgSearchUrl,
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from
        },
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
                showInfo("提示","机构查询失败!");
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                var resultList=[];
                $("#monitor_result_table").DataTable().destroy();
                for(var i=0;i<response.orgList.length;i++){
                    var item=response.orgList[i];
                    resultList.push({name:item.orgCnName,id:item.orgId});
                }
                v_maskmodalModel.$data.monitorResultList=resultList;

                v_maskmodalModel.$nextTick(function(){
                    bindSimpleDataTable("monitor_result_table",10);
                    v_maskmodalModel.$data.monitorSearchShow=true;
                });
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        }
    });
}

function changeMonitorSelection(){
    if(v_maskmodalModel.$data.currentMonitorModel==1){ // Edit状态下不能更改监控类型
        $("#monitorTypeSelect").val(v_maskmodalModel.$data.monitorTypeSelect);
    }
}

function changeEventSelection(){
    if(v_maskmodalModel.$data.currentEventModel==1){ // Edit状态下不能更改监控类型
        $("#eventTypeSelect").val(v_maskmodalModel.$data.eventTypeSelect);
    }else{
        for(item in event_Type_list){
            if(!isEventParamShow(item,v_maskmodalModel.$data.eventTypeSelect)){
                v_maskmodalModel.$data.eventParams[item]="";
            }
        }
    }
}

function isEventParamShow(value,pid){
    var temp=event_Type_list[value].value;
    var curType="400"+pid;
    for(var i=0;i<temp.length;i++){
        if(temp[i]==curType){
            return true;
        }
    }
    return false;
}




