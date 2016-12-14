/**
 * Created by a88u on 2016/10/23.
 */
var v_companySearchModel=new Vue({
    el:"#v-companySearchModel",
    data: {
        notShow:false,
        searchKey:"",
        showInputWarning:false,
        resultList:[],
        //searchSelections:clone(listedCompanyConditions),
        isListedSelected:false,
        //isShowXsbCondition:false,
        //selectedSelections:[],
        companyMergeList:[],
        mergePage:0,
        mergeIsEnd:false,

        test:""
    },
    ready: function () {
    },
    methods: {
        changeCheck:function(){
            //console.log("changeCheck");
            var value=$('#stockCompanyRadio').is(":checked");
            console.log(value);
            this.isListedSelected=value; // 指定是否上市公司
            //this.isShowXsbCondition=false;
            //this.selectedSelections=[];
            //this.resultList=[];
        },
        doSearch:function(){
            this.showInputWarning=false;
            if(this.searchKey==''){
                this.showInputWarning=true;
                return;
            }
            doCompanySearch();
        },
        gotoCompanyPage:function(id,type,stockType){
            console.log("company: ", id,', ',type,', ',stockType);

            //stock_type 0是A股 1是新三板
            if((typeof id!= undefined)&&id!=""&&id!=null){
                savecurrentSearchPage("key",this.searchKey);
                if(type==1){
                    if(stockType==0){//A股
                        gotoCompanyPage("companyBasic",id,0);
                    }else{//三板
                        gotoCompanyPage("companyBasic",id,1);
                    }
                }else{//未上市公司
                    gotoCompanyPage("companyBasic",id,2);
                }
            }else{
                console.log("undef:",typeof id!= "undefined",', empty:',id!="");
            }
        },
        gotoCompany:function(id){
console.log("gotocompany: ",id);
            if(id&&id!=""&&id!=null){
                console.log("goto");
                savecurrentSearchPage("key",this.searchKey);
                gotoCompanybyId(id);
            }else{
                console.log("undef:",typeof id!= "undefined",', empty:',id!="");
            }
        },
        showMergeDetail:function(item){
            modal_mask_info.$data.information=item;
            modal_mask_info.$data.showModal=true;
        },
        pageControlFilter:function(value){
            if(value==0){
                return this.mergePage!=0;
            }else{
                return !this.mergeIsEnd;
            }
        },
        changeMergePage:function(type){
            if(type==0){
                this.mergePage--;
                getCompanyIndexInfo(this.mergePage);
            }else if(type==1){
                this.mergePage++;
                getCompanyIndexInfo(this.mergePage);
            }
        }
    },
    filters: {
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        minSelectionFilter:function(value){
            return "sel_"+value+"_min";
        },
        maxSelectionFilter:function(value){
            return "sel_"+value+"_max";
        },
        selectFilter:function(value){
            return "conditionItem_"+value;
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

var key=getUrlQueryStr("key",window.location.href);
if(typeof key == "undefined"){
    key="";
}

if(key=="") {
    getCompanyIndexInfo(0);
}else{
    $("#top-search").val(key);
    v_companySearchModel.$data.searchKey=key;
    doCompanySearch();
}

function inputKeyPress(event,value){
    v_companySearchModel.$data.showInputWarning=false;
    console.log(event);
    var event = window.event||event; // 为了兼容firefox没有全局event对象
    console.log(event);
    if (event.keyCode == 13) { // 回车搜索
        doCompanySearch();
    }
}

//function initSearchCondition(){
//    $("#conditionSelections input:checkbox").attr("checked",false);
//
//    //v_companySearchModel.$data.listedChecked=1;
//    v_companySearchModel.$data.selectedSelections=[];
//}

function doCompanySearch(){
    var data=v_companySearchModel.$data;
    var key=data.searchKey;

    var params={
    	key:key,
        type:data.isListedSelected
    };

    $.ajax({
        url: commonUrls.companySearchUrl,              //请求地址
        type: "POST",                       //请求方式
        data: params,        //请求参数
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success") {
                var response = res;
                $('#result_table').DataTable().destroy();
                //console.log("ddddddd");
                v_companySearchModel.$data.resultList = response.searchResult; // 这里不能用this
                v_companySearchModel.$nextTick(function () {
                    bindExportedDataTable("result_table", 15, "", {dom: 'Tfgtp'});
                });
            }
        },
        fail: function (status) {
            console.error(" error. status=", status);
        },
        statusCode: {
            404: function () {
                //window.location.href="notFound.html";
                goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}

function getCompanyIndexInfo(page){
    var from=page*commonPageNum.entSearchMergePageNum;
    $.ajax({
        url: commonUrls.companyTopUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            from:from
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
                //$("#company_merge_table").DataTable().destroy();
                v_companySearchModel.$data.companyMergeList=response.rptToMaList; // 企业并购

                if(response.rptToMaList&&response.rptToMaList.length>=commonPageNum.entSearchMergePageNum){
                    v_companySearchModel.$data.mergeIsEnd=false;
                }else{
                    v_companySearchModel.$data.mergeIsEnd=true;
                }
                //v_companySearchModel.$nextTick(function(){
                //    bindSimpleDataTable("company_merge_table",50);
                //});
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

// 模态框显示企业并购事件每日监控详情
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
            information:{}
        }
    });
    //console.log("model: ",v_model_mask_info);
    return v_model_mask_info;
}











