/**
 * Created by a88u on 2016/10/12.
 */
menuList.monitor.isActive=true;
menuList.monitor.showChild=true;
menuList.monitor.childMenu.sysMonitor.isActive=true;

getRptData();
getCompanyIndexInfo(0,true);
var v_sysmonitorModel=new Vue({
    el:"#v-sysmonitorModel",
    data:{
        rptPeFundList:[],
        rptModule:true,
        companyMergeList:[],
        mergePage:0,
        mergeIsEnd:false,
        showWaitDiv:true
    },
    ready:function(){
    },
    methods:{
        gotoCompany:function(id){
            console.log("gotocompany: ",id);
            if(id&&id!=""&&id!=null){
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
                getCompanyIndexInfo(this.mergePage,false);
            }else if(type==1){
                this.mergePage++;
                getCompanyIndexInfo(this.mergePage,false);
            }
        },
        changeModule:function (value) {
            if(value==0){
                this.$data.rptModule=true;
            }else if(value==1){
                this.$data.rptModule=false;
            }
            console.log(this.$data.rptModule);
        }
    },
    filters:{
        showIfEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
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

// 股权类投资基金监控
function getRptData(){
    $.ajax({
        url: commonUrls.sysMonitorRptUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            v_sysmonitorModel.$data.showWaitDiv=false;
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                //console.log("response", response);
                $("#rpt_table").DataTable().destroy();
                v_sysmonitorModel.$data.rptPeFundList=response.rptPeFundList;
                v_sysmonitorModel.$nextTick(function(){
                    bindSimpleDataTable("rpt_table",commonPageNum.sysMonitorRpt);
                });
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
            v_sysmonitorModel.$data.showWaitDiv=false;
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

// 企业并购事件每日监控
function getCompanyIndexInfo(page,isFirstRequest){
    var from=page*commonPageNum.sysMonitorMerge;
    if(!isFirstRequest) {
        v_sysmonitorModel.$data.showWaitDiv = true;
    }
    $.ajax({
        url: commonUrls.sysMonitorMergeUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            from:from,
            count:commonPageNum.sysMonitorMerge
        },
        dataType: "json",
        success: function (res) {
            if(!isFirstRequest) {
                v_sysmonitorModel.$data.showWaitDiv = false;
            }
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                console.log("response", response);
                v_sysmonitorModel.$data.companyMergeList=response.rptToMaList; // 上市公司并购事件每日监控

                if(response.rptToMaList&&response.rptToMaList.length>=commonPageNum.sysMonitorMerge){
                    v_sysmonitorModel.$data.mergeIsEnd=false;
                }else{
                    v_sysmonitorModel.$data.mergeIsEnd=true;
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
    v_model_mask_info=new Vue({
        el:"#v-model-mask-info",
        data:{
            showModal:false,
            information:{}
        }
    });
    return v_model_mask_info;
}

function resizeDetailMask() {
    var height=$(window).height();
    var mheight=height-70;
    console.log(height,mheight);
    $("#modal_detail_container").css('maxHeight',mheight);
}

$(document).ready(function () {
    resizeDetailMask();
});

$(window).resize(function () {
   resizeDetailMask();
});





























