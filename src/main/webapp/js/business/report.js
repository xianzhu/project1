/**
 * Created by a88u on 2016/10/12.
 */

    var reportType={
        cv:1,
        trader:2
    };

var v_reportModel=new Vue({
    el:"#v-reportModel",
    data:{
        key:"",
        cvtypeSelections:cvTypeSelections,
        cvResult:[],
        cvIsEnd:false,
        cvResultPage:0,
        cvResultTypes:"",
        cvFilterKey:"",

        otypeSelections:oTypeSelections,
        oResult:[],
        oIsEnd:false,
        oResultPage:0,
        oResultTypes:"",
        oFilterKey:"",
    },
    ready:function(){

    },
    methods:{
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        pageControlFilter:function(type,value){
            //console.log(value,", ",this.cvResultPage,",",this.cvIsEnd);
            if(type==reportType.cv){
                if(value==0){
                    return this.cvResultPage!=0;
                }else{
                    return !this.cvIsEnd;
                }
            }else if(type==reportType.trader){
                if(value==0){
                    return this.oResultPage!=0;
                }else{
                    return !this.oIsEnd;
                }
            }

        },
        changeResultPage:function(type,value){
            var key=$("#top-search").val();
            if(type==reportType.cv){
                if(value==0){ // 上一页
                    this.cvResultPage--;
                }else{ // 下一页
                    this.cvResultPage++;
                }
                getSubResultPage(type,key,this.cvResultPage,this.cvResultTypes);
            }else if(type==reportType.trader){
                if(value==0){ // 上一页
                    this.oResultPage--;
                }else{ // 下一页
                    this.oResultPage++;
                }
                getSubResultPage(type,key,this.oResultPage,this.oResultTypes);
            }
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        oTypeSelectFilter:function(value){
            return "oTypeSelect_"+value;
        },
        cvTypeSelectFilter:function(value){
            return "cvTypeSelect_"+value;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        showRaderReportTypeFilter:function(value){
            return oTypeItems["T"+value];
        }
    }
});

var key=getUrlQueryStr("key",location.href);

if(typeof key== "undefined") { // 若url中不存在查询关键字，即链接由导航栏直接跳转来
    key="";
}
$("#top-search").val(key);

v_reportModel.$data.key=key; // 记录当前查询关键字，防止后面用户在搜索栏输入但不查询的刷新
getReasultInfo(key);

function getReasultInfo(skey){
    //var rId=getUrlQueryStr("id",location.href);

    getSubResultPage(reportType.cv,skey,0,"");
    getSubResultPage(reportType.trader,skey,0,"");
}

function getSubResultPage(type,key,page,types){ // rType: 0--all;1--cv;2--other
    var rType="cv";
    if(type==reportType.trader){
        rType="trader";
    }

    var from=page*commonPageNum.reportSearch;
    $.ajax({
        url: commonUrls.reportUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            type:rType,
            filter:types
        },
        dataType: "json",
        success: function (res) {
            console.log(typeof res);
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                //console.log("send ajax success");
                var response = res;

                if(type==0){
                    //updateCvTable(response.result);
                    //updateOTable(response.result);
                }else if(type==reportType.cv){
                    console.log(type,response.cv_report_list.length);
                    updateCvTable(response.cv_report_list);
                }else if(type==reportType.trader){
                    console.log(type,response.trader_report_list.length);
                    updateOTable(response.trader_report_list);
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

function updateCvTable(data){
    //if(v_reportModel.$data.cvResult.length>0){
    //$('#cvResult_table').DataTable().destroy();
    //}
    $('#cvResult_table').DataTable().destroy();
    v_reportModel.$data.cvResult=data;
    if(data.length==0){
        v_reportModel.$data.cvIsEnd=true;
    }else{
        v_reportModel.$data.cvIsEnd=false;
    }
    v_reportModel.$nextTick(function () {
        bindExportedDataTable("cvResult_table", -1, "行业分析报告查询结果",{});
    });
}
function updateOTable(data){

    //if(v_reportModel.$data.oResult.length>0) {
    //    $('#oResult_table').DataTable().destroy();
    //}
    $('#oResult_table').DataTable().destroy();
    v_reportModel.$data.oResult=data;
    console.log("show",v_reportModel.$data.oResult.length);
    if(data.length==0){
        v_reportModel.$data.oIsEnd=true;
    }else{
        v_reportModel.$data.oIsEnd=false;
    }
    v_reportModel.$nextTick(function () {
        bindExportedDataTable("oResult_table", -1, "研究报告查询结果",{});
    });
}

function bothCheck(){
    var cvcontent=$(".type-check-list");

    var par=cvcontent.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");

    for(var i=0;i<v_reportModel.$data.cvtypeSelections.length;i++){
        v_reportModel.$data.cvtypeSelections[i].isSelected=true;
    }
    for(var i=0;i<v_reportModel.$data.otypeSelections.length;i++){
        v_reportModel.$data.otypeSelections[i].isSelected=true;
    }
}

function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = value;
        console.log("search key=#", key, "#");
            bothCheck();
            v_reportModel.$data.cvResultPage=0;
            v_reportModel.$data.cvResultTypes="";

            v_reportModel.$data.oResultPage=0;
            v_reportModel.$data.oResultTypes="";
            getReasultInfo(key);
        //}
    }
}

function showTypeFilter(type){
    console.log("doTypeFilter");
    if(type==reportType.cv){ // 设置类型过滤条件选择
        for(var i=0;i<v_reportModel.$data.cvtypeSelections.length;i++){
            var item=v_reportModel.$data.cvtypeSelections[i];
            if(item.isSelected){
                $("#cvTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
            }else{
                $("#cvTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
            }
        }
        $("#cvTypeSelect").show(500);
    }else if(type==reportType.trader){
        for(var i=0;i<v_reportModel.$data.otypeSelections.length;i++){
            var item=v_reportModel.$data.otypeSelections[i];
            if(item.isSelected){
                $("#oTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
            }else{
                $("#oTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
            }
        }
        $("#oTypeSelect").show(500);
    }
}

function doTypeSelect(type){
    var key=$("#top-search").val();
    var subTypes=[];
    var subType="";
    console.log(type);
    if(type==reportType.cv){
        for(var i=0;i<v_reportModel.$data.cvtypeSelections.length;i++){
            var item=v_reportModel.$data.cvtypeSelections[i];
            if($("#cvTypeSelect_"+item.value).hasClass("fa-check-square")){
                v_reportModel.$data.cvtypeSelections[i].isSelected=true;
                subTypes.push(item.value);
            }else if($("#cvTypeSelect_"+item.value).hasClass("fa-square-o")){
                v_reportModel.$data.cvtypeSelections[i].isSelected=false;
            }
        }
        subType=subTypes.join(",");
        v_reportModel.$data.cvResultTypes=subType;
        v_reportModel.$data.cvResultPage=0;
    }else if(type==reportType.trader){
        for(var i=0;i<v_reportModel.$data.otypeSelections.length;i++){
            var item=v_reportModel.$data.otypeSelections[i];
            if($("#oTypeSelect_"+item.value).hasClass("fa-check-square")){
                subTypes.push(item.value);
                v_reportModel.$data.otypeSelections[i].isSelected=true;
            }else if($("#oTypeSelect_"+item.value).hasClass("fa-square-o")){
                v_reportModel.$data.otypeSelections[i].isSelected=false;
            }
        }
        subType=subTypes.join(",");
        console.log(subType);
        v_reportModel.$data.oResultTypes=subType;
        v_reportModel.$data.oResultPage=0;
    }
    closeSelect(type);
    getSubResultPage(type,key,0,subType);
}

function closeSelect(type){
    if(type==reportType.cv){
        $("#cvTypeSelect").hide(500);
    }else if(type==reportType.trader){
        $("#oTypeSelect").hide(500);
    }
}




