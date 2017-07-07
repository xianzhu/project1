/**
 * Created by a88u on 2016/10/10.
 */
var ivsFilterKey="";
var extFilterKey="";

var onePageLength=-1; // 每页显示的数据条数

var investButtons=[],exitButtons=[];

// 初始化各条件
function initButtons(){
    v_fundBasicModel.$data.ivsPage=0;
    v_fundBasicModel.$data.ivsSubType=0;
    ivsFilterKey="";
    for(var i=0;i<investButtons.length;i++){
        investButtons[i].className="btn btn-default buttons-html5";
    }
    if(investButtons.length>0){
        investButtons[investButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }

    v_fundBasicModel.$data.extPage=0;
    v_fundBasicModel.$data.extSubType=0;
    extFilterKey="";
    for(var i=0;i<exitButtons.length;i++){
        exitButtons[i].className="btn btn-default buttons-html5";
    }
    if(exitButtons.length>0){
        exitButtons[exitButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }
}

initButtons();

// 初始化类型选择 --- 全选
function bothCheck(){
    var content=$(".type-check-list");

    var par=content.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");

    for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
        v_fundBasicModel.$data.ivsTypeSelections[i].isSelected=true;
    }
    for(var i=0;i<v_fundBasicModel.$data.extTypeSelections.length;i++){
        v_fundBasicModel.$data.extTypeSelections[i].isSelected=true;
    }
}

// 初始化特定事件类型选择 --- 全选
function typeRecheck(type){
    var id="";
    var content;
    if(type==1){
        content=$("#investTypeSelect").find(".type-check-list");
        for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
            v_fundBasicModel.$data.ivsTypeSelections[i].isSelected=true;
        }
    }else if(type==2){
        content=$("#exitTypeSelect").find(".type-check-list");
        for(var i=0;i<v_fundBasicModel.$data.extTypeSelections.length;i++){
            v_fundBasicModel.$data.extTypeSelections[i].isSelected=true;
        }
    }
    var par=content.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");
}

function updateInvestTable(data) {
    $('#invest_table').DataTable().destroy();
    v_fundBasicModel.$data.ivsCapitalList = data;
    if (data&&data.length != 0) {
        v_fundBasicModel.$data.ivsEnd = false;
    } else {
        v_fundBasicModel.$data.ivsEnd = true;
    }
    v_fundBasicModel.$nextTick(function () {
        var options={
            dom:'<"html5buttons"B><"investToolbar dataTables_filter">tp',
            initComplete:function(){
                $("div.investToolbar").html('<label>搜索:<input id="ivsFilterInput" type="search" onkeypress="doDataSearch(event,1,this.value)" class="form-control input-sm" placeholder="请输入查询..." value="' + ivsFilterKey + '"></label>');
            }
        }
        bindDataTable("invest_table",onePageLength,"机构资本--投资事件", investButtons,options);
    });
}

function updateExitTable(data) {
    $('#exit_table').DataTable().destroy();
    v_fundBasicModel.$data.extCapitalList = data;
    if (data&&data.length != 0) {
        v_fundBasicModel.$data.extEnd = false;
    } else {
        v_fundBasicModel.$data.extEnd = true;
    }
    v_fundBasicModel.$nextTick(function () {
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
    //console.log("do data search");
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        console.log("do data search");
        if(type==1){
            v_fundBasicModel.$data.ivsPage=0;
            v_fundBasicModel.$data.ivsSubType="";
        }else if(type==2){
            v_fundBasicModel.$data.extPage=0;
            v_fundBasicModel.$data.extSubType="";
        }
        typeRecheck(type);
        getSubDataPage(v_fundBasicModel.$data.fid,type,key,0,"");
    }
}

function showTypeFilter(type){
    //console.log("doTypeFilter");
    if(type==1){ // 设置类型过滤条件选择
        for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
            var item=v_fundBasicModel.$data.ivsTypeSelections[i];
            if(item.isSelected){
                $("#ivsTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
            }else{
                $("#ivsTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
            }
        }
        $("#investTypeSelect").show(500);
    }else if(type==2){
        for(var i=0;i<v_fundBasicModel.$data.extTypeSelections.length;i++){
            var item=v_fundBasicModel.$data.extTypeSelections[i];
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

            for (var i = 1; i < v_fundBasicModel.$data.ivsTypeSelections.length; i++) {
                var item = v_fundBasicModel.$data.ivsTypeSelections[i];
                if ($("#ivsTypeSelect_" + item.value).hasClass("fa-check-square")) {
                    v_fundBasicModel.$data.ivsTypeSelections[i].isSelected = true;
                    subTypes.push(item.value);
                } else if ($("#ivsTypeSelect_" + item.value).hasClass("fa-square-o")) {
                    v_fundBasicModel.$data.ivsTypeSelections[i].isSelected = false;
                }
            }
        if(subTypes.length==v_fundBasicModel.$data.ivsTypeSelections.length-1) {
            subType = "";
        }else{
            subType = subTypes.join(",");
        }

        key=ivsFilterKey;
        v_fundBasicModel.$data.ivsSubType=subType;
        v_fundBasicModel.$data.ivsPage=0;
    }else if(type==2){

            for (var i = 1; i < v_fundBasicModel.$data.extTypeSelections.length; i++) {
                var item = v_fundBasicModel.$data.extTypeSelections[i];
                if ($("#extTypeSelect_" + item.value).hasClass("fa-check-square")) {
                    subTypes.push(item.value);
                    v_fundBasicModel.$data.extTypeSelections[i].isSelected = true;
                } else if ($("#extTypeSelect_" + item.value).hasClass("fa-square-o")) {
                    v_fundBasicModel.$data.extTypeSelections[i].isSelected = false;
                }
            }
        if(subTypes.length==v_fundBasicModel.$data.extTypeSelections.length-1){
            subType="";
        }else {
            subType = subTypes.join(",");
        }
        key=extFilterKey;
        v_fundBasicModel.$data.extSubType=subType;
        v_fundBasicModel.$data.extPage=0;
    }
    closeSelect(type);
    getSubDataPage(v_fundBasicModel.$data.fid,type,key,0,subType);
}

function closeSelect(type){
    if(type==1){
        $("#investTypeSelect").hide(500);
    }else if(type==2){
        $("#exitTypeSelect").hide(500);
    }
}

Vue.component('modal_mask', {
    template: '#modal-template',
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
        el:"#v-model-mask-info",
        data:{
            showModal:false,
            information:{},
            dataTitle:""
        },
        filters:{
            formatDataFilter:function(value){
                return toDataFormat(value);
            }
        }
    });
    //console.log("model: ",v_model_mask_info);
    return v_model_mask_info;
}

function showCapitalDetail(type,capital) {
    //console.log(type,', ',capital);
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

function bindDataTable(domId,perLength,exportTitle,extendButtons,cOptions){
    var dom='#'+domId;

    var tableOptions={
        searching:false,
        order:[],
        ordering:false,
        language:{
            url:"data/dataTables_cn.json"
        },
        headerCallback: function( thead, data, start, end, display ) {
            $(thead).find("th").eq(0).removeClass("sorting_asc");
        },
        dom:'<"html5buttons"B>tp',
        buttons: [
            {extend: 'copy',text:"复制"},
            {extend: 'csv',title:exportTitle},
            {extend: 'print',title:exportTitle,
                customize: function (win){
                    $(win.document.body).addClass('white-bg');
                    $(win.document.body).css('font-size', '10px');

                    $(win.document.body).find('table')
                        .addClass('compact')
                        .css('font-size', 'inherit');
                }
            }
        ]
    };
    if(perLength>0){
        tableOptions.pageLength=perLength; // 设置每页显示项数
    }else{
        tableOptions.paginate=false; // 不分页
    }

    tableOptions=joinJsonObject(tableOptions,cOptions);
    for(var i=0;i<extendButtons.length;i++) {
        tableOptions.buttons.unshift(extendButtons[i]);
    }
    $(dom).DataTable(tableOptions);
}








