/**
 * Created by a88u on 2017/7/21.
 */

var v_entParamModel = new Vue({
    el: "#v-entParamModel",
    data: {
        showEntParamDiv:false,
        key_content: "" // 关键字
    },
    methods: {
        beginEntsearch: function () {
            v_entSearchModel.$data.entPage=0;
            v_entSearchModel.$data.entEnd=false;
            doEntSearch();
        },
        cancelEntparam: function () {
            this.$data.showEntParamDiv = false;
        },
        selectedIndustryClass:function (item) {
            this.$data.industry_class=item.text;
            this.$data.ifilterKey=item.text;
            $("#filter_input_div").css('display','none');
            console.log("selectedIndustryClass111:",this.$data.industry_class);
        }
    },
    filters: {

    }
});
var v_entSearchModel = new Vue({
    el: "#v-entSearchModel",
    data: {
        showConditions:false,
        showConditionTypes:false,
        searchFilter:{
            key:"",
            range:[],
            stock:0
        },
        entParamTags: {
            keyContent:{key:"keyContent",text:"",isSelected:false}, // 关键字
            isStock:{key:"entStage",text:"",isSelected:false}, // 是否上市公司
            typeList:[] // 范围
        }, // 填写的参数列表
        entList: [], // 企业结果列表
        entPage:0,
        entEnd:false
    },
    ready: function () {
    },
    methods: {
        gotoCompany: function (id) {
            console.log(id);
            gotoCompanybyId(id);
        },
        openEntSearch: function () { // 根据entParamsTages设置状态
            v_entParamModel.$data.key_content=v_entSearchModel.$data.searchFilter.key;
            $('input:radio[name="isStock"]:checked').val(v_entSearchModel.$data.searchFilter.stock);
            if(v_entSearchModel.$data.searchFilter.range.length>0){
                $(".range-single").removeClass("selected");
                for(var i in v_entSearchModel.$data.searchFilter.range){
                    var key=v_entSearchModel.$data.searchFilter.range[i];
                    $("#range-item-"+key).addClass("selected");
                }
            }else{
                $(".range-all").addClass("selected");
                $(".range-single").removeClass("selected");
            }

            v_entParamModel.$data.showEntParamDiv = true;
            v_entParamModel.$data.showInputWarning = false;
        },
        removeSelectedParam:function (key) {
            v_entSearchModel.$data.entParamTags[key].isSelected=false;
        },
        pageControlFilter:function(value){
            var page=0;
            var isEnd=false;

            if(value==0){
                //console.log('上:',type,page!=0);
                return this.entPage!=0;
            }else{
                //console.log('下:',type,!isEnd);
                return !this.entEnd;
            }
        },
        changePage:function(value){
            if(value==0){
                this.entPage--;
            }else{
                this.entPage++;
            }
            doEntSearch();
        },
    },
    filters: {
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
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

requestEntInfo({
    key: "",
    range:"",
    from:0,
    count:commonPageNum.entSearchList
});

function doEntSearch() {
    var data = v_entParamModel.$data;

    var from=v_entSearchModel.$data.entPage*commonPageNum.entSearchList,
        count=from+commonPageNum.entSearchList>=60?(60-from):commonPageNum.entSearchList;
    var params = {
        key: (data.key_content != "")? data.key_content:"",
        from:from,
        count:count
    };

    v_entSearchModel.$data.searchFilter.key=data.key_content;
    v_entSearchModel.$data.entParamTags.keyContent.text = "关键字: " + data.key_content;
    v_entSearchModel.$data.entParamTags.keyContent.isSelected = (data.key_content != "");

    var val=$('input:radio[name="isStock"]:checked').val();
    v_entSearchModel.$data.searchFilter.stock=val;
    var stockType="";
    console.log(val);
    if(val=="1"){
        stockType="是";
        params.type=true;
        v_entSearchModel.$data.entParamTags.isStock.isSelected=true;
    }else if(val=="2"){
        stockType="否";
        params.type=false;
        v_entSearchModel.$data.entParamTags.isStock.isSelected=true;
    }else{
        v_entSearchModel.$data.entParamTags.isStock.isSelected=false;
    }
    v_entSearchModel.$data.entParamTags.isStock.text= "是否上市公司: " + stockType;

    v_entSearchModel.$data.entParamTags.typeList=[];
    v_entSearchModel.$data.searchFilter.range=[];
    if($(".range-all").hasClass("selected")){
        v_entSearchModel.$data.showConditionTypes=false;
    }else{
        var tags=$(".range-single.selected"),ptags=[];
        v_entSearchModel.$data.showConditionTypes=true;
        for(var i=0;i<tags.length;i++){
            var node=tags[i];
            var text=$(node).attr("item-data"),value=$(node).attr("value");
            ptags.push(value);
            v_entSearchModel.$data.searchFilter.range.push(value);
            v_entSearchModel.$data.entParamTags.typeList.push({"text":text,"value":value})
        }
        params.range=ptags.join(",");
    }

    v_entSearchModel.$data.showConditions=v_entSearchModel.$data.entParamTags.keyContent.isSelected||v_entSearchModel.$data.entParamTags.isStock.isSelected||v_entSearchModel.$data.showConditionTypes;
    v_entParamModel.$data.showEntParamDiv=false;

    if(from+count>=60){
        v_entSearchModel.$data.entEnd=true;
    }else{
        v_entSearchModel.$data.entEnd=false;
    }
    requestEntInfo(params);
}

// 请求企业查询
function requestEntInfo(params){
    var url=commonUrls.companyMutiSearchUrl;
    v_entSearchModel.$data.entList=[];
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
                v_entSearchModel.$data.entList=response.searchResult;
                v_entSearchModel.$nextTick(function(){
                    // initPopover();
                });
            }
        }
    });
}

$(window).ready(function () {
    $(".range-single").click(function (e) {
        $(this).toggleClass("selected");
        // 全部不选，默认全部
        if($(this).hasClass("selected")){ // 选择，则全部不选
            $(".range-all").removeClass("selected");
        }else{ // 不选，判断是否全没选
            var dnodes=$(".param-range-input").find(".range-single");
            console.log(dnodes);
            var flag=true;
            for(var i=0;i<dnodes.length;i++){
                var dnode=dnodes[i];
                if($(dnode).hasClass("selected")){
                    flag=false;
                    break;
                }
            }
            if(flag){
                $(".range-all").addClass("selected");
            }
        }
    });

    $(".range-all").click(function (e) {
        var target=$(this).addClass("selected");
        $(".range-single").removeClass("selected");
    });
});
