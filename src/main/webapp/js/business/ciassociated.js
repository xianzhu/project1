/**
 * Created by a88u on 2016/10/27.
 */
menuList.ciassociated.isActive=true;
var defaultFilterKey="pleaseSearch",
    noInputMsg="请在右上方输入关键字,选择统计表";

var v_industryToolsModel=new Vue({
    el:"#v-industryToolsModel",
    data:{
        selectTableTitle:"",
        hasSelected:false,
        showWait:false,
        filterKey:defaultFilterKey,
        inputMsg:noInputMsg,
        tableSelections:selectedTables,
        result:[],
        hasRecords:true,
        hasSearch:false,
        tableColumns:[]
    },
    methods:{
        changeSelect:function(value,title){
            getDataList(value, title);
        }
    },
    filters:{
        checkEmptyFilter:function(value){
            var result=true;
            if(value&&value.length>0){
                result=false;
            }
            if(this.$data.filterKey==defaultFilterKey){
                this.$data.inputMsg=noInputMsg;
            }else{
                this.$data.inputMsg="您输入的关键字没有匹配的统计数据，请重新输入！";
            }
            return result;
        }
    }
});

function filterKeyPress(event,value){
    var event = window.event||event; // 为了兼容firefox没有全局event对象
    if(event.keyCode==13) {
        doTableFilter(value);
    }
}
function beginFilter() {
    var value=$("#table_filter_input").val();
    doTableFilter(value);
}

function doTableFilter(value) {
    if (value != "") {
        v_industryToolsModel.$data.filterKey = value;
    } else {
        v_industryToolsModel.$data.filterKey = defaultFilterKey;
    }
}
function getDataList(value,title){
    v_industryToolsModel.$data.showWait=true;
    v_industryToolsModel.$data.hasSearch=true;
    $.ajax({
        url: commonUrls.industryToolsUrl,              //请求地址
        type: "POST",                            //请求方式
        timeout:1000*2,
        data: { //请求参数
            type:value
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=="success"){
                v_industryToolsModel.$data.selectTableTitle=title; // title
                v_industryToolsModel.$data.result=res.object;   // tbody
                if(v_industryToolsModel.$data.result&&v_industryToolsModel.$data.result.length>0){
                    v_industryToolsModel.$data.hasRecords=true;
                }else{
                    v_industryToolsModel.$data.hasRecords=false;
                }

                var columns=[];
                for(var item in res.propertities) {
                    if(item=="id"){
                        continue;
                    }else if(item=="statDate"||item=="statTime"||item=="statRate"||item=="happenDate"||item=="date"){
                        columns.unshift({value: item, name: res.propertities[item]});
                    }else{
                        columns.push({value: item, name: res.propertities[item]});    // thead
                    }
                }
                v_industryToolsModel.$data.tableColumns=columns;
            }
            console.log("close show wait");
            v_industryToolsModel.$data.showWait=false;
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
            v_industryToolsModel.$data.showWait=false;
        },
        statusCode: {
            404: function() {
                v_industryToolsModel.$data.showWait=false;
                goTo404();
            },
            500:function(){
                v_industryToolsModel.$data.showWait=false;
                goTo500();
            }
        }
    });
}

