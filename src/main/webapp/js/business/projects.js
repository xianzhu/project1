/**
 * Created by a88u on 2016/10/23.
 */
menuList.project.isActive=true;

var v_projParamModel = new Vue({
    el: "#v-projParamModel",
    data: {
        key_content: "", // 关键字
        proj_stage: "", // 项目阶段
        keyType: "", // 关键字类型
        industry_class: "", // 行业分类
        industry_class_filter:"", // 行业分类过滤

        ifilterKey:"",

        industry_list:clone(industrySelectionList),

        showInputWarning: false, // 显示输入提示
        showProjParamDiv: true
    },
    methods: {
        beginProjsearch: function () {
            // if (this.key_content == "") {
            //     this.$data.showInputWarning = true;
            //     return;
            // }
            doProjSearch();
        },
        cancelProjparam: function () {
            this.$data.showProjParamDiv = false;
        },
        selectedIndustryClass:function (item) {
            this.$data.industry_class=item.text;
            this.$data.ifilterKey=item.text;
            $("#filter_input_div").css('display','none');
            console.log("selectedIndustryClass111:",this.$data.industry_class);
        }
    },
    filters: {
        getValueFilter:function(item){
            return JSON.stringify(item);
        }
    }
});
var v_projectsModel = new Vue({
    el: "#v-projectsModel",
    data: {
        isFirstShow: false,
        projParamTags: {
            keyType:{key:"key",text:"",isSelected:false},// 关键字类型
            keyContent:{key:"keyContent",text:"",isSelected:false}, // 关键字
            projStage:{key:"projStage",text:"",isSelected:false}, // 是否国内项目
            industryClass:{key:"industryClass",text:"",isSelected:false} // 行业分类
        }, // 填写的参数列表
        projectList: [] // 项目列表
    },
    ready: function () {
    },
    methods: {
        gotoCompany: function (id) {
            console.log(id);
            gotoCompanybyId(id);
        },
        openProjectSearch: function () {
            v_projParamModel.$data.showProjParamDiv = true;
            v_projParamModel.$data.showInputWarning = false;
        },
        removeSelectedParam:function (key) {
            v_projectsModel.$data.projParamTags[key].isSelected=false;
        }
    },
    filters: {
        stateHoldFilter: function (value) {
            return value == 0 ? '否' : '是';
        },
        checkEmptyFilter: function (value) {
            var result = false;
            if (value && value.length > 0) {
                result = true;
            }
            return result;
        },
        formatStringFilter:function(value){
            if(value&&value.toLowerCase()!="null"){
                return value;
            }

            return "暂无";
        }
    }
});

function doProjSearch() {
    console.log("doproject search");
    var data = v_projParamModel.$data;
    // v_projectsModel.$data.projParamTags = [];
    if(data.industry_class=="全部"){
        data.industry_class="";
    }
    var params = {
        // key_type: data.key_content,
        // proj_stage: data.proj_stage,
        key: (data.key_content != "")? data.key_content:"",
        domain:data.industry_class,
        from:0,
        count:commonPageNum.projectList
    };
console.log(params);
    // var ktype = (data.keyType == "0") ? "公司名" : "品牌";
    // v_projectsModel.$data.projParamTags.keyType.text = "关键字类型: " + ktype;
    // v_projectsModel.$data.projParamTags.keyType.isSelected = (data.key_content != "");
    v_projectsModel.$data.projParamTags.keyContent.text = "关键字: " + data.key_content;
    // v_projectsModel.$data.projParamTags.keyContent.isSelected = (data.key_content != "");
    v_projectsModel.$data.projParamTags.keyContent.isSelected = (data.key_content != "");

    // v_projectsModel.$data.projParamTags.projStage.isSelected = (data.proj_stage != "");
    // if(data.proj_stage!=""){
    //     var stateHold = "";
    //     switch(data.proj_stage){
    //         case "0":
    //             stateHold="前期";
    //             break;
    //         case "1":
    //             stateHold="中期";
    //             break;
    //         case "2":
    //             stateHold="后期";
    //             break;
    //     }
    //     v_projectsModel.$data.projParamTags.projStage.text = "项目阶段: " + stateHold;
    // }
    v_projectsModel.$data.projParamTags.industryClass.text = "行业分类: " + data.industry_class;
    v_projectsModel.$data.projParamTags.industryClass.isSelected = (data.industry_class != "");
    // console.log(params);
    v_projParamModel.$data.showProjParamDiv=false;
    v_projectsModel.$data.isFirstShow=true;
    requestProjectInfo(params);
}

// 请求项目推荐
function requestProjectInfo(params){
    var url=commonUrls.projectUrl;
    v_projectsModel.$data.projectList=[];
    $.ajax({
        url:url,
        type:'post',
        dataType:'json',
        data:params,
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response=res;
                v_projectsModel.$data.projectList=response.projectList;
                v_projectsModel.$nextTick(function(){
                    // initPopover();
                });
            }
        }
    });
}

function oninputIndustryKey(event,value) {
    var event=event||window.event;
    console.log(value);
    v_projParamModel.$data.ifilterKey=value;
}

function showIndustryList() {
    console.log("show");
    $("#filter_input_div").css('opacity','1').css('display','inline-block');
    document.getElementById("filter_input_dom").focus();
}
var clearClick=false;
function hideIndustryList() {
    console.log("hide industry");
    $("#filter_input_div").css('opacity','0');
    setTimeout(function () {
        if(clearClick){ // 如果是clear
            $("#filter_input_div").css('opacity','1').css('display','inline-block');
        }else{
            $("#filter_input_div").css('opacity','1').css('display','none');
        }
        clearClick=false;
    },1000);
}

function clearIndustryInput() {
    console.log("clear");
    v_projParamModel.$data.industry_class="";
    v_projParamModel.$data.ifilterKey="";

    v_projParamModel.$nextTick(function () {
        console.log("next");
        clearClick=true;
        $("#filter_input_div").css('opacity','1').css('display','inline-block');
        document.getElementById("filter_input_dom").focus();
    });
}



