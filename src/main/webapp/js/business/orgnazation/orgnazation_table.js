/**
 * Created by a88u on 2016/10/12.
 */

var v_orgnazationModel=new Vue({
    el:"#v-orgnazationModel",
    data:{
        result:[],
        isEnd:false,
        resultPage:0,
        key:"",
        doSearch:false,
        rptPeFundList:[],
        rptOrgOveralTreds:[]
    },
    ready:function(){

    },
    methods:{
        goto:function(url,id){
            savecurrentSearchPage("key",this.key);
            gotoClickPage(url,id);
        },
        gotoCompany:function(id){
            savecurrentSearchPage("key",this.key);
            gotoCompanybyId(id);
        },
        open:function(url,id){
            openClickPage(url,id);
        },
        openNews:function(url){
            sendMonitor({url:url});
        },
        pageControlFilter:function(value){
            //console.log(value,", ",this.resultPage,",",this.isEnd);
            if(value==0){
                return this.resultPage!=0;
            }else{
                return !this.isEnd;
            }
        },
        changeResultPage:function(value){
            if(value==0){ // 上一页
                this.resultPage--;
                getSubResultPage(this.key,this.resultPage);
            }else{ // 下一页
                this.resultPage++;
                getSubResultPage(this.key,this.resultPage);
            }
        }
    },
    filters:{
        currentPageFilter:function(value){
            var p=this.resultPage+1;
            return "第"+p+"页";
        },
        isResultEmpty:function(value){
            console.log(this.isEnd);
            return this.isEnd;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        },
        checkNotEmptyFilter:function(value){
            var result=false;
            if(this.doSearch){ // 查询开始，不显示图表
                result=true;
            }
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        showIfEmptyFilter:function(value){
            var result=true;
            if(this.doSearch){ // 查询开始，不显示图表
                result=false;
            }
            if(value&&value.length>0){
                result=false;
            }
            return result;
        },
        showNoMoreFilter: function (value) {
            if(value){
                if(this.result.length==0){
                    return true;
                }
            }
            return false;
        }
    }
});

var key=getUrlQueryStr("key",location.href);
if(typeof key == "undefined"){
    key="";
}

if(key!="") {
    $("#top-search").val(key);
    v_orgnazationModel.$data.key=key;
    getSubResultPage(key, 0);
}else {
    getRptData();
}

function getSubResultPage(key,page){
    v_orgnazationModel.$data.doSearch=true;
    var from=page*commonPageNum.orgSearch;

    $.ajax({
        url: commonUrls.orgSearchUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            key:key,
            from:from
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;

                $('#result_table').DataTable().destroy();
                v_orgnazationModel.$data.result=response.orgList;
                console.log(v_orgnazationModel.$data.result.length);
                if(v_orgnazationModel.$data.result&&v_orgnazationModel.$data.result.length==0){
                    v_orgnazationModel.$data.isEnd=true;
                }else{
                    v_orgnazationModel.$data.isEnd=false;
                }
                v_orgnazationModel.$nextTick(function () {
                    bindSimpleDataTable("result_table",-1);
                });
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

function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key=value;
        v_orgnazationModel.$data.resultPage=0;
        v_orgnazationModel.$data.key=key;
        getSubResultPage(key,0);
    }
}

function getRptData(){
    $.ajax({
        url: commonUrls.orgRptDataUrl,              //请求地址
        type: "POST",                            //请求方式
        data: {},
        dataType: "json",
        success: function (res) {
            if(res.status=='failure'){
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            }else if(res.status=='success') {
                var response = res;
                //console.log("response", response);
                $("#rpt_table").DataTable().destroy();
                v_orgnazationModel.$data.rptPeFundList=response.rptPeFundList;
                v_orgnazationModel.$nextTick(function(){
                    console.log("simpletable");
                    bindSimpleDataTable_new("rpt_table",10);
                });

                showRptEcharts(response.tendsList,"rptEcharts");
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


function showRptEcharts(srcdata,id){
    if(!srcdata){
        console.log("data is undefined.");
        //$("#"+id).css('display','none');
        return;
    }
    var echarts;
    require.config({
        paths:{
            echarts: 'js/plugins/echarts2'
        }
    });
    require(['echarts', 'echarts/chart/line'],function(ec){
        echarts=ec;
        console.log(ec);

        var dom=document.getElementById(id);
        var myChart=echarts.init(dom);

        var data={
            "durTime":[],
            "ydata":{
                "iNum":[],
                "eNum":[],
                "psIbmpa":[]
            }
        }

        for(var i=0;i<srcdata.length;i++){
            var item=srcdata[i];
            data.durTime.push(item.durTime);
            data.ydata.iNum.push(item.iNum);
            data.ydata.eNum.push(item.eNum);
            data.ydata.psIbmpa.push(item.psIbmpa);
        }

        console.log(data);
        var moption=clone(orgTrendsOption);

        moption.xAxis.data=data.durTime;
        //console.log(moption.xAxis.data);
        moption.series[0].data=data.ydata.iNum;
        moption.series[1].data=data.ydata.eNum;
        moption.series[2].data=[];
        var flag=true;
        for(var j=data.ydata.psIbmpa.length-1;j>=0;j--){
            var yd=data.ydata.psIbmpa[j];
            if(flag){
                if(yd==null||yd==0){
                    continue;
                }else{
                    flag=false;
                    moption.series[2].data.unshift(yd);
                }
            }else{
                moption.series[2].data.unshift(yd);
            }
        }
        //moption.series[2].data=data.ydata.psIbmpa;
console.log(moption);
        myChart.setOption(moption,true);

        window.onresize = function (){
            myChart.resize&&myChart.resize();
        };
    });

}




















