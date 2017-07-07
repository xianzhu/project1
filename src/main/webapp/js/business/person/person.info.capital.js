/**
 * Created by a88u on 2016/10/10.
 */
var ivsFilterKey="";
var extFilterKey="";

var onePageLength=-1; // 每页显示的数据条数

var investButtons=[],exitButtons=[];

// 初始化各条件
function initButtons(){
    //v_personBasicModel.$data.ivsPage=0;
    //v_personBasicModel.$data.ivsSubType="";
    ivsFilterKey="";
    for(var i=0;i<investButtons.length;i++){
        investButtons[i].className="btn btn-default buttons-html5";
    }
    if(investButtons.length>0){
        investButtons[investButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }

    //v_personBasicModel.$data.extPage=0;
    //v_personBasicModel.$data.extSubType=0;
    extFilterKey="";
    for(var i=0;i<exitButtons.length;i++){
        exitButtons[i].className="btn btn-default buttons-html5";
    }
    if(exitButtons.length>0){
        exitButtons[exitButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }
}

initButtons();
//getCapitalInfo(0,"");

function getCapitalInfo(type,key){
    var orgId = getUrlQueryStr("id", location.href);
    if(type==0){
        getSubDataPage(1,key,0,0);
        getSubDataPage(2,key,0,0);
    }else if(type==1){
        getSubDataPage(1,key,0,0);
    }else{

        getSubDataPage(2,key,0,0);
    }
}

function getSubDataPage(type,key,page,subType){
    var turl=commonUrls.personEventUrl;
    var dtype="invest";
    if(type==2){
        dtype="exit";
    }else if(type==0){
        dtype="all";
    }
    var rdata={
        id:pid,
        from:page*commonPageNum.personEvents,
        key:key,
        count:commonPageNum.personEvents,
        type:dtype,   // 请求数据类型：投资，并购，退出
        //filter:subType // 请求数据子类型
    };
    if(subType!=""){
        rdata.filter=subType;
    }
console.log(type,',',subType);
    $.ajax({
        url: turl,              //请求地址
        type: "POST",                            //请求方式
        data: rdata,
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

                if(type==0){
                    ivsFilterKey=key;
                    extFilterKey=key;
                    updateInvestTable(response.inventList);
                    updateExitTable(response.exitList);
                }else if(type==1){
                    ivsFilterKey=key;
                    updateInvestTable(response.inventList);
                }else if(type==2){
                    extFilterKey=key;
                    updateExitTable(response.exitList);
                }
            }
        },
        fail: function (status) {
            console.error("error. status=", status);
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

// 初始化类型选择 --- 全选
function bothCheck(){
    var content=$(".type-check-list");

    var par=content.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");

    for(var i=0;i<v_personBasicModel.$data.ivsTypeSelections.length;i++){
        v_personBasicModel.$data.ivsTypeSelections[i].isSelected=true;
    }
    for(var i=0;i<v_personBasicModel.$data.extTypeSelections.length;i++){
        v_personBasicModel.$data.extTypeSelections[i].isSelected=true;
    }
}

// 初始化特定事件类型选择 --- 全选
function typeRecheck(type){
    var id="";
    var content;
    if(type==1){
        content=$("#investTypeSelect").find(".type-check-list");
        for(var i=0;i<v_personBasicModel.$data.ivsTypeSelections.length;i++){
            v_personBasicModel.$data.ivsTypeSelections[i].isSelected=true;
        }
    }else if(type==2){
        content=$("#exitTypeSelect").find(".type-check-list");
        for(var i=0;i<v_personBasicModel.$data.extTypeSelections.length;i++){
            v_personBasicModel.$data.extTypeSelections[i].isSelected=true;
        }
    }
    var par=content.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");
}

function updateInvestTable(data) {
    $('#invest_table').DataTable().destroy();
    v_personBasicModel.$data.ivsCapitalList = data;
    if (data.length == 0) {
        v_personBasicModel.$data.ivsEnd = true;
    } else {
        v_personBasicModel.$data.ivsEnd = false;
    }
    v_personBasicModel.$nextTick(function () {
        console.log("next tick");
        var options={
            dom:'<"html5buttons"B><"investToolbar dataTables_filter">tp',
            initComplete:function(){
                console.log(ivsFilterKey);
                $("div.investToolbar").html('<label>搜索:<input id="ivsFilterInput" type="search" onkeypress="doDataSearch(event,1,this.value)" class="form-control input-sm" placeholder="请输入查询..." value="' + ivsFilterKey + '"></label>');
            }
        }
        bindDataTable("invest_table",onePageLength,"机构资本--投资事件", investButtons,options);
    });
}

function updateExitTable(data) {
    $('#exit_table').DataTable().destroy();
    v_personBasicModel.$data.extCapitalList = data;
    if (data == 0) {
        v_personBasicModel.$data.extEnd = true;
    } else {
        v_personBasicModel.$data.extEnd = false;
    }
    v_personBasicModel.$nextTick(function () {
        var options={
            dom:'<"html5buttons"B><"exitToolbar dataTables_filter">tp',
            initComplete:function(){
                $("div.exitToolbar").html('<label>搜索:<input id="extFilterInput" type="search" onkeypress="doDataSearch(event,2,this.value)" class="form-control input-sm" placeholder="请输入查询..." value="' + extFilterKey + '"></label>');
            }
        }
        bindDataTable("exit_table", onePageLength, "机构资本--退出事件", exitButtons,options);
    });
}

// 各表自有搜索框
function doDataSearch(event,type,key){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var subType=0;
        if(type==1){
            v_personBasicModel.$data.ivsPage=0;
            v_personBasicModel.$data.ivsSubType="";
        }else if(type==2){
            v_personBasicModel.$data.extPage=0;
            v_personBasicModel.$data.extSubType="";
        }
        typeRecheck(type);
        getSubDataPage(type,key,0,"");
    }
}

function showTypeFilter(type){
    console.log("doTypeFilter");
    if(type==1){ // 设置类型过滤条件选择
        for(var i=0;i<v_personBasicModel.$data.ivsTypeSelections.length;i++){
            var item=v_personBasicModel.$data.ivsTypeSelections[i];
            if(item.isSelected){
                $("#ivsTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
            }else{
                $("#ivsTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
            }
        }
        $("#investTypeSelect").show(500);
    }else if(type==2){
        for(var i=0;i<v_personBasicModel.$data.extTypeSelections.length;i++){
            var item=v_personBasicModel.$data.extTypeSelections[i];
            if(item.isSelected){
                $("#extTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
            }else{
                $("#extTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
            }
        }
        $("#exitTypeSelect").show(500);
    }
}

function doTypeSelect(type){
    var key="";
    var subTypes=[];
    var subType="";
    if(type==1){

            for (var i = 1; i < v_personBasicModel.$data.ivsTypeSelections.length; i++) {
                var item = v_personBasicModel.$data.ivsTypeSelections[i];
                if ($("#ivsTypeSelect_" + item.value).hasClass("fa-check-square")) {
                    v_personBasicModel.$data.ivsTypeSelections[i].isSelected = true;
                    subTypes.push(item.value);
                } else if ($("#ivsTypeSelect_" + item.value).hasClass("fa-square-o")) {
                    v_personBasicModel.$data.ivsTypeSelections[i].isSelected = false;
                }
            }
        if(subTypes.length==v_personBasicModel.$data.ivsTypeSelections.length-1){
            subType="";
        }else {
            subType = subTypes.join(",");
        }
        key=ivsFilterKey;
        v_personBasicModel.$data.ivsSubType=subType;
        v_personBasicModel.$data.ivsPage=0;
    }else if(type==2) {

            for (var i = 1; i < v_personBasicModel.$data.extTypeSelections.length; i++) {
                var item = v_personBasicModel.$data.extTypeSelections[i];
                if ($("#extTypeSelect_" + item.value).hasClass("fa-check-square")) {
                    subTypes.push(item.value);
                    v_personBasicModel.$data.extTypeSelections[i].isSelected = true;
                } else if ($("#extTypeSelect_" + item.value).hasClass("fa-square-o")) {
                    v_personBasicModel.$data.extTypeSelections[i].isSelected = false;
                }
            }
        if(subTypes.length==v_personBasicModel.$data.extTypeSelections.length-1){
            subType="";
        }else {
            subType = subTypes.join(",");
        }
        key=extFilterKey;
        v_personBasicModel.$data.extSubType=subType;
        v_personBasicModel.$data.extPage=0;
    }
    closeSelect(type);
    getSubDataPage(type,key,0,subType);
}

function closeSelect(type){
    if(type==1){
        $("#investTypeSelect").hide(500);
    }else if(type==2){
        $("#exitTypeSelect").hide(500);
    }
}


/**
 * Created by a88u on 2016/8/18.
 */
Vue.component('modal_mask', {
    template: '#modal-capital-template',
    props: {
        show: {
            type: Boolean,
            required: true,
            twoWay: true
        }
    }
});
var modal_mask_info = initModelMaskVue();
function initModelMaskVue(){
    //console.log("initModelMaskVue");
    v_model_mask_info=new Vue({
        el:"#v-model-mask-capital",
        data:{
            showModal:false,
            information:{},
            dataTitle:""
        }
    });
    //console.log("model: ",v_model_mask_info);
    return v_model_mask_info;
}
function showCapitalDetail(type,capital) {
    if(type==1){
        $("#invest_data_row").css("display","block");
        $("#exit_data_row").css("display","none");
        modal_mask_info.$data.information = capital.inventEvent;
        modal_mask_info.$data.dataTitle="投资事件数据";
    }else if(type==2){
        $("#invest_data_row").css("display","none");
        $("#exit_data_row").css("display","block");
        modal_mask_info.$data.information = capital.exitEvent;
        modal_mask_info.$data.dataTitle="退出事件数据";
    }

    modal_mask_info.$data.showModal = true;
}

/**
 * Created by a88u on 2016/8/18.
 */
Vue.component('modal_mask', {
    template: '#modal-desc-template',
    props: {
        show: {
            type: Boolean,
            required: true,
            twoWay: true
        }
    }
});
var modal_mask_desc = initDescModelVue();
function initDescModelVue(){
    v_model_mask_desc=new Vue({
        el:"#v-model-mask-desc",
        data:{
            showModal:false,
            personDesc:""
        }
    });
    return v_model_mask_desc;
}
function showDescDetail(description) {
    modal_mask_desc.$data.personDesc = description;
    modal_mask_desc.$data.showModal = true;
}








