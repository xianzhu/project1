/**
 * Created by a88u on 2016/10/12.
 */
menuList.report.isActive=true;
menuList.report.showChild=true;
menuList.report.childMenu.cvReport.isActive=true;

var cvReportButtons=[];

// 初始化各条件
function initButtons(){
    for(var i=0;i<cvReportButtons.length;i++){
        cvReportButtons[i].className="btn btn-default buttons-html5";
    }
    if(cvReportButtons.length>0){
        cvReportButtons[cvReportButtons.length-1].className="btn btn-default buttons-html5 type-select";
    }
}
initButtons();

var v_cvReportModel=new Vue({
    el:"#v-cvReportModel",
    data:{
        key:"",
        cvtypeSelections:cvTypeSelections,
        cvResult:[],
        showConditions:false,
        showConditionTypes:false,
        cvIsEnd:false,
        cvResultPage:0,
        cvParamTags: {
            keyContent: {key: "keyContent", text: "", isSelected: false}, // 关键字
            typeList: []
        },
        cvResultTypes:"",
        cvFilterKey:""
    },
    ready:function(){
    },
    methods:{
        openPdfOnline:function(path){
            openFilesOnline(path);
        },
        pageControlFilter:function(type,value){
            if(value==0){
                return this.cvResultPage!=0;
            }else{
                return !this.cvIsEnd;
            }
        },
        changeResultPage:function(type,value){
            var key=$("#top-search").val();
            if(value==0){ // 上一页
                this.cvResultPage--;
            }else{ // 下一页
                this.cvResultPage++;
            }
            getSubResultPage(this.cvFilterKey,this.cvResultPage,this.cvResultTypes);
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
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
        checkIfSelectFilter:function(value){
            for(var i=0;i<this.pitem.length;i++){
                var ic=this.pitem[i];
                if(value==ic.value){
                    return true;
                }
            }
            return false;
        }
    }
});

getSubResultPage("",0,"");

function getSubResultPage(key,page,types){ // rType: 0--all;1--cv;2--other
    console.log(key,types);
    v_cvReportModel.$data.cvParamTags.keyContent.text="关键字: "+key;
    v_cvReportModel.$data.cvParamTags.keyContent.isSelected=(key!="");

    var flag=false,splittypes=[];
    v_cvReportModel.$data.cvParamTags.typeList=[];
    if(types.length>0) {
        splittypes=types.split(",");
        console.log(splittypes);
        if (splittypes.length > 0 && splittypes[0] != "0") {
            for (var i = 0; i < splittypes.length; i++) {
                var item = splittypes[i];
                v_cvReportModel.$data.cvParamTags.typeList.push({"text": cvTypeItems["T" + item]});
            }
        }
    }
    v_cvReportModel.$data.showConditionTypes=(v_cvReportModel.$data.cvParamTags.typeList.length>0&&v_cvReportModel.$data.cvParamTags.typeList.length!=6);
    if(v_cvReportModel.$data.cvParamTags.keyContent.isSelected||v_cvReportModel.$data.showConditionTypes){
        v_cvReportModel.$data.showConditions=true;
    }else{
        v_cvReportModel.$data.showConditions=false;
    }

    var from=page*commonPageNum.cvReports;
    $.ajax({
        url: commonUrls.reportUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from,
            count:commonPageNum.cvReports,
            type:"cv",
            filter:types
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
                updateCvTable(response.cv_report_list);
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
    $('#cvResult_table').DataTable().destroy();
    v_cvReportModel.$data.cvResult=data;
    if(data.length==0){
        v_cvReportModel.$data.cvIsEnd=true;
    }else{
        v_cvReportModel.$data.cvIsEnd=false;
    }
    v_cvReportModel.$nextTick(function () {
        // var options={
        //     dom:'<"html5buttons"B><"searchToolbar dataTables_filter">tp',
        //     initComplete:function(){
        //         // console.log(key);
        //         $("div.searchToolbar").html('<label>搜索:<input id="cvFilterInput" type="search" onkeypress="doDataSearch(event,this.value)" class="form-control input-sm" placeholder="请输入查询..." value="' + v_cvReportModel.$data.cvFilterKey + '"></label>');
        //     }
        // }
        // bindDataTable("cvResult_table",-1,"行业分析报告查询结果", cvReportButtons,options);
        bindSimpleDataTable("cvResult_table",-1);
    });
}

function bothCheck(){
    var cvcontent=$(".type-check-list");

    var par=cvcontent.find("li").find("a").find("i");
    par.removeClass("fa-square-o").addClass("fa-check-square");

    for(var i=0;i<v_cvReportModel.$data.cvtypeSelections.length;i++){
        v_cvReportModel.$data.cvtypeSelections[i].isSelected=true;
    }
}

function showTypeFilter(){
    for(var i=0;i<v_cvReportModel.$data.cvtypeSelections.length;i++){
        var item=v_cvReportModel.$data.cvtypeSelections[i];
        if(item.isSelected){
            $("#cvTypeSelect_"+item.value).removeClass("fa-square-o").addClass("fa-check-square");// 勾选
        }else{
            $("#cvTypeSelect_"+item.value).addClass("fa-square-o").removeClass("fa-check-square");// 不勾选
        }
    }
    $("#cvTypeSelect").show(500);
}

function doTypeSelect(){
    var subTypes=[];
    var subType="";

    for(var i=0;i<v_cvReportModel.$data.cvtypeSelections.length;i++){
        var item=v_cvReportModel.$data.cvtypeSelections[i];
        if($("#cvTypeSelect_"+item.value).hasClass("fa-check-square")){
            v_cvReportModel.$data.cvtypeSelections[i].isSelected=true;
            subTypes.push(item.value);
        }else if($("#cvTypeSelect_"+item.value).hasClass("fa-square-o")){
            v_cvReportModel.$data.cvtypeSelections[i].isSelected=false;
        }
    }
    subType=subTypes.join(",");
    v_cvReportModel.$data.cvResultTypes=subType;
    v_cvReportModel.$data.cvResultPage=0;
    closeSelect();
    getSubResultPage(v_cvReportModel.$data.cvFilterKey,0,subType);
}

function closeSelect(){
    $("#cvTypeSelect").hide(500);
}

// 各表自有搜索框
function doDataSearch(event,key){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        v_cvReportModel.$data.cvFilterKey=key;
        v_cvReportModel.$data.cvResultPage=0;
        v_cvReportModel.$data.cvResultTypes="";
        bothCheck();
        getSubResultPage(key,0,"");
    }
}

function doSearch() {
    var key=$("#input-filter-key").val();
    console.log(key);
    v_cvReportModel.$data.cvFilterKey=key;
    v_cvReportModel.$data.cvResultPage=0;
    v_cvReportModel.$data.cvResultTypes="";
    bothCheck();
    getSubResultPage(key,0,"");
}
