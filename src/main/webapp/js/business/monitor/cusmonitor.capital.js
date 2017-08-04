/**
 * Created by a88u on 2017/7/25.
 */

/*var ivsFilterKey="";

var onePageLength=-1; // 每页显示的数据条数

var investButtons=[];

// 初始化各条件
function initButtons(){
    ivsFilterKey="";
    for(var i=0;i<investButtons.length;i++){
        investButtons[i].className="btn btn-default buttons-html5";
    }
    if(investButtons.length>0){
        investButtons[investButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }
}

initButtons();

function getCapitalInfo(key){
    var orgId = getUrlQueryStr("id", location.href);
    getSubDataPage(key,0,0);
}

function getSubDataPage(key,page,subType){
    var turl=commonUrls.orgFundInvEventNum;
    var dtype="invest";

    var rdata={
        id:pid,
        from:page*commonPageNum.personEvents,
        key:key,
        count:commonPageNum.personEvents,
        type:dtype
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
                ivsFilterKey=key;
                updateInvestTable(response.inventList);
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

    for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
        v_fundBasicModel.$data.ivsTypeSelections[i].isSelected=true;
    }
}

// 初始化特定事件类型选择 --- 全选
function typeRecheck(){
    var id="";
    var content;
    content=$("#investTypeSelect").find(".type-check-list");
    for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
        v_fundBasicModel.$data.ivsTypeSelections[i].isSelected=true;
    }
    var par=content.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");
}

function updateInvestTable(data) {
    $('#orgInvest_table').DataTable().destroy();
    v_fundBasicModel.$data.ivsCapitalList = data;
    if (data.length == 0) {
        v_fundBasicModel.$data.ivsEnd = true;
    } else {
        v_fundBasicModel.$data.ivsEnd = false;
    }
    v_fundBasicModel.$nextTick(function () {
        console.log("next tick");
        bindSimpleDataTable("orgInvest_table",onePageLength);
    });
}

// 各表自有搜索框
function doDataSearch(event,type,key){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var subType=0;
        key=key=$("#input-invest-key").val();
        ivsFilterKey=key;
        v_fundBasicModel.$data.ivsPage=0;
        v_fundBasicModel.$data.ivsSubType="";
        typeRecheck();
        getSubDataPage(key,0,"");
    }
}

function doSearch() {
    var key;
    key=key=$("#input-invest-key").val();
    ivsFilterKey=key;
    v_fundBasicModel.$data.ivsPage=0;
    v_fundBasicModel.$data.ivsSubType="";
    typeRecheck();
    getSubDataPage(key,0,"");
}

function showTypeFilter(){
    console.log("doTypeFilter");
    for(var i=0;i<v_fundBasicModel.$data.ivsTypeSelections.length;i++){
        var item=v_fundBasicModel.$data.ivsTypeSelections[i];
        if(item.isSelected){
            $("#ivsTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
        }else{
            $("#ivsTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
        }
    }
    $("#investTypeSelect").show(500);
}

function doTypeSelect(){
    var key="";
    var subTypes=[];
    var subType="";
    for (var i = 1; i < v_fundBasicModel.$data.ivsTypeSelections.length; i++) {
        var item = v_fundBasicModel.$data.ivsTypeSelections[i];
        if ($("#ivsTypeSelect_" + item.value).hasClass("fa-check-square")) {
            v_fundBasicModel.$data.ivsTypeSelections[i].isSelected = true;
            subTypes.push(item.value);
        } else if ($("#ivsTypeSelect_" + item.value).hasClass("fa-square-o")) {
            v_fundBasicModel.$data.ivsTypeSelections[i].isSelected = false;
        }
    }
    if(subTypes.length==v_fundBasicModel.$data.ivsTypeSelections.length-1){
        subType="";
    }else {
        subType = subTypes.join(",");
    }
    key=ivsFilterKey;
    v_fundBasicModel.$data.ivsSubType=subType;
    v_fundBasicModel.$data.ivsPage=0;
    closeSelect();
    getSubDataPage(key,0,subType);
}

function closeSelect(){
    $("#investTypeSelect").hide(500);
}*/

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
var modal_mask_detail = initModelMaskDetailVue();
function initModelMaskDetailVue(){
    v_model_mask_detail=new Vue({
        el:"#v-model-mask-capital",
        data:{
            showModal:false,
            information:{},
            dataTitle:""
        },
        filters:{
            strFormatFilter:function (value) {
                var result="-";
                if(value&&value!=null&&value!="null"&&value!=""){
                    result=value;
                }
                // console.log(value,result);
                return result;
            },
            dataFormatFilter:function (value) {
                var result="-";
                if(typeof value!="undefined"&&value!=null&&value!="null"){
                    result=value;
                }
                // console.log(value,result);
                return result;
            }
        }
    });
    return v_model_mask_detail;
}
function showCapitalDetail(capital,type) {
    if(type==1){
        $("#invest_data_row").css("display","block");
        $("#exit_data_row").css("display","none");
        modal_mask_detail.$data.information = capital.inventEvent;
        modal_mask_detail.$data.dataTitle="投资事件数据";
        modal_mask_detail.$data.showModal = true;
    }else{
        $("#invest_data_row").css("display","none");
        $("#exit_data_row").css("display","block");
        modal_mask_detail.$data.information = capital.exitEvent;
        modal_mask_detail.$data.dataTitle="退出事件数据";
        modal_mask_detail.$data.showModal = true;
    }
}








