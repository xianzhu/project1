/**
 * Created by a88u on 2016/10/23.
 */
menuList.setting.isActive=true;

var cOperationType={
    insert:"add",
    delete:"del",
    update:"update"
}

var commonObjType={
    event:{id:1,value:"event"}, // del
    cv:{id:2,value:"cv"},
    trader:{id:3,value:"trader"},
    consult:{id:4,value:"consult"}, // del
    monitor:{id:5,value:"monitor"},
    feedback:{id:6,value:"qa"} // del
}

$('#eventTimePicker').datepicker({dateFormat:'yy-mm-dd'});

var v_deleteModel=new Vue({
    el:"#v-deleteConformModel",
    data:{
        showDeleteDiv:false,
        id:"",
        obj:""
    },
    methods:{
        doOK:function () {
            v_deleteModel.$data.showDeleteDiv=false;
            doItemDelete(v_deleteModel.$data.id,v_deleteModel.$data.obj);
        },
        doCancel:function () {
            v_deleteModel.$data.showDeleteDiv=false;
        }
    }
});

var v_maskmodalModel=new Vue({
    el:"#v-maskmodalModel",
    data:{
        modifyItemId:"",

        currentIntelModel:0,// 行业分析
        intelTypeList: intellgence_type, // 行业类型列表
        intelDateList: intellgence_date_type, // 行业时间列表
        intelTypeSelect:"", // 行业类型选择
        intelDateSelect:"", // 行业时间选择
        intelCustContent:"", // 行业自定义内容
        showIntelligenceDiv:false,// 行业分析

        currentResearchModel:0,// 研究报告
        researchTypeList: trader_type, // 研报类型列表
        researchDateList: trader_date_type, // 研报时间列表
        researchTypeSelect:"", // 研报类型选择
        researchDateSelect:"", // 研报时间选择
        showResearchDiv:false,// 研究报告

        // 事件
        // currentEventModel:0,
        // eventTypeList: event_type, // 事件类型列表
        // eventTypeSelect:"", // 事件类型选择
        // eventTitle:"", // 事件标题
        // eventParams:clone(init_event_param), // 用于数据绑定的事件参数
        // showEventDiv:false,

        currentConsultModel:0, // 业务咨询
        consultContent:"", // 业务咨询内容
        consultReply:"", // 业务咨询回复
        showConsultDiv:false,

        currentMonitorModel:0, // 监控
        monitorTypeList:monitor_type, // 监控类型列表
        monitorTypeSelect:"", // 监控类型选择
        monitorContent:"", // 监控关键字
        monitorId:"", // 监控id
        monitorSearchShow:false, // 监控查询
        monitorResultList:[],
        monitorSearchKey:"",
        showMonitorDiv:false, // 监控
    },
    methods:{
        cancelIntel: function () {
            this.showIntelligenceDiv = false;
            this.currentIntelModel = 0;
        },
        cancelResearch: function () {
            this.showResearchDiv = false;
            this.currentResearchModel = 0;
        },
        cancelMonitor: function () {
            this.showMonitorDiv = false;
            this.currentMonitorModel = 0;
        },
        cancelConsult:function () {
            this.showConsultDiv=false;
            this.currentConsultModel=0;
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
        submitConsult:function(){
            if(this.consultContent==""){
                showInfo("提示","请填写咨询内容！");
                return;
            }
            var ccontent=this.consultContent;

            var datas={
                content:ccontent
            };
            var operation_type="";
            if(this.currentConsultModel==1){ // edit
                datas.id=this.modifyItemId;
                operation_type=cOperationType.update;
            }else if(this.currentConsultModel==2){ // add
                operation_type=cOperationType.insert;
                datas.uid=v_userModel.$data.uname;
            }
            doItemOperation(datas,operation_type,commonObjType.consult);
            this.showConsultDiv=false;
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

var v_cusSettingModel=new Vue({
    el:"#v-cusSettingModel",
    data: {
        commonObj:commonObjType,
        // eventList:[], // 事件
        researchList:[], // 研究报告
        consultList:[], // 业务咨询
        //feedbackList:[],
        intelList:[], // 行业分析

        // feedbackContent:"", // 问题反馈
        monitorList:"" // 监控关键字
    },
    ready: function () {
    },
    methods: {
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
                bindSimpleDataTable("monitor_result_table",commonPageNum.cusSetMonitor);
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
            if (v_cusSettingModel.$data.monitorList.length >= customerSettings.monitorMaxNum) {
                showInfo("提示", "目前仅支持"+customerSettings.monitorMaxNum+"条监控设置，如需帮助，请联系我们！");
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
                bindSimpleDataTable("monitor_result_table",commonPageNum.cusSetMonitor);
            });
        },
        doConsultEdit:function(item){
            v_maskmodalModel.$data.modifyItemId = item.id;
            v_maskmodalModel.$data.consultContent=item.content;

            v_maskmodalModel.$data.showConsultDiv = true;
            v_maskmodalModel.$data.currentConsultModel = 1;
        },
        doConsultView:function (item) {
            v_maskmodalModel.$data.consultContent=item.content;
            v_maskmodalModel.$data.consultReply=item.reply;

            v_maskmodalModel.$data.showConsultDiv = true;
            v_maskmodalModel.$data.currentConsultModel = 0;
        },
        doConsultAdd: function () {
            v_maskmodalModel.$data.consultContent="";
            v_maskmodalModel.$data.showConsultDiv = true;
            v_maskmodalModel.$data.currentConsultModel = 2;
        },
        doDelete:function(id,obj){ // 加一个监控，需要根据servlet配置，待定
// 增加删除确认提示
            v_deleteModel.$data.id=id;
            v_deleteModel.$data.obj=obj;
            v_deleteModel.$data.showDeleteDiv=true;
            // doItemDelete(id,obj);
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
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                v_cusSettingModel.$data.intelList=response.cvList; // 行业分析
                v_cusSettingModel.$data.researchList=response.traderList; // 研究报告
                v_cusSettingModel.$data.consultList=response.consultList; // 业务咨询

                getMonitorList(response.monitorList); // 监控
                // v_cusSettingModel.$data.eventList=response.eventList; // 事件
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
            }else if(res.status=="timeout"){
                console.log("timeout");
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
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                if(objId==commonObjType.cv.id){ // 行业分析
                    v_cusSettingModel.$data.intelList=response.cvList;
                }else if(objId==commonObjType.consult.id){ // 业务咨询
                    v_cusSettingModel.$data.consultList=response.consultList;
                }else if(objId==commonObjType.trader.id){ // 研究报告
                    v_cusSettingModel.$data.researchList=response.traderList;
                }else if(objId==commonObjType.monitor.id){ // 监控
                    getMonitorList(response.monitorList);
                // }else if(objId==commonObjType.event.id){ // 事件
                //     v_cusSettingModel.$data.eventList=response.eventList;
                // }else if(objId==commonObjType.feedback.id){
                //     //v_cusSettingModel.$data.feedbackList=response.consultList;
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
    var entResult=[];
    var orgResult=[];
    for(var i=0;i<list.length;i++){
        var item=list[i];
        //console.log(item);
        if(item.type==1){
            entResult.push(item.uuid);
            result.push({content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.uuid});
        }else if(item.type==2){
            orgResult.push(item.orgId);
            result.push({content:item.content,type:item.type,id:item.id,uid:item.uid,mid:item.orgId});
        }
        setCookie("entMonitor",entResult.join("|"),10);
        setCookie("orgMonitor",orgResult.join("|"),10);
    }
    //console.log(result);
    v_cusSettingModel.$data.monitorList=result;
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
            }else if(res.status=="timeout"){
                console.log("timeout");
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
                // goTo500();
                showInfo("提示","操作未成功.请联系我们。状态码：500");
            }
        }
    });

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
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
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
                    bindSimpleDataTable("monitor_result_table",commonPageNum.cusSetMonitor);
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
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
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
                    bindSimpleDataTable("monitor_result_table",commonPageNum.cusSetMonitor);
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
    console.log(v_maskmodalModel.$data.currentMonitorModel);
    if(v_maskmodalModel.$data.currentMonitorModel==1){ // Edit状态下不能更改监控类型
        $("#monitorTypeSelect").val(v_maskmodalModel.$data.monitorTypeSelect);
    }else{ // add状态下清空之前所选
        v_maskmodalModel.$data.monitorSearchShow=false;
        v_maskmodalModel.$data.monitorSearchKey="";
        v_maskmodalModel.$data.monitorContent="";
    }
}

// function changeEventSelection(){
//     if(v_maskmodalModel.$data.currentEventModel==1){ // Edit状态下不能更改监控类型
//         $("#eventTypeSelect").val(v_maskmodalModel.$data.eventTypeSelect);
//     }else{
//         for(item in event_Type_list){
//             if(!isEventParamShow(item,v_maskmodalModel.$data.eventTypeSelect)){
//                 v_maskmodalModel.$data.eventParams[item]="";
//             }
//         }
//     }
// }
//
// function isEventParamShow(value,pid){
//     var temp=event_Type_list[value].value;
//     var curType="400"+pid;
//     for(var i=0;i<temp.length;i++){
//         if(temp[i]==curType){
//             return true;
//         }
//     }
//     return false;
// }
function resizeDetailMask() {
    // console.log("resize Detail Mask");
    var height = $(window).height();
    var mheight = height;
    // console.log(height, mheight);
    $(".modal-consult-body").css('maxHeight', mheight - 181);
    $(".modal-monitor-body").css('maxHeight', mheight - 181);
}
$(document).ready(function () {
    resizeDetailMask();
});
$(window).resize(function () {
    resizeDetailMask();
});

