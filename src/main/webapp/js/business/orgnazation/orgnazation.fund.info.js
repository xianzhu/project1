/**
 * Created by a88u on 2016/10/11.
 */
var fid=getUrlQueryStr("id",location.href);
var orgId=getUrlQueryStr("oid",location.href);
require.config({
    paths:{
        echarts: 'js/plugins/echarts-2.2.7'
    }
});
var extFilterKey="",ivsFilterKey;
var v_fundBasicModel=new Vue({
    el: '#v-fundBasicModel',
    data: {
        fName:"",
        fid:fid,
        description:"",
        // time:"",
        // amount:"",
        // isEnd:false,
        curEventModule:0,

        ivsTypeSelections:investTypeSelections,
        ivsCapitalList:[],
        ivsEnd:false,
        ivsPage:0,
        ivsSubType:0,

        extTypeSelections:exitTypeSelections,
        extCapitalList:[],
        extEnd:false,
        extPage:0,
        extSubType:0
    },
    ready: function () {
    },
    methods: {
        gobackOrg:function (value) {
            console.log(value);
            gotoOrgPage(orgId);
        },
        changeEventModule:function (value) {
            this.$data.curEventModule=value;
        },
        pageCaptialFilter:function(type,value){
            var page=0;
            var end=false;
            if(type==1){
                page=this.ivsPage;
                end=this.ivsEnd;
            }else if(type==2){
                page=this.extPage;
                end=this.extEnd;
            }
            if(value==0){
                return page!=0;
            }else{
                return !end;
            }
        },
        changeDataPage:function(type,value){
            //console.log(type,value);
            if(type==1){
                if(value==0){
                    this.ivsPage--;
                }else{
                    this.ivsPage++;
                }
                //console.log(this.ivsPage);
                getSubDataPage(this.fid,type,ivsFilterKey,this.ivsPage,this.ivsSubType);
            }else if(type==2){
                if(value==0){
                    this.extPage--;
                }else{
                    this.extPage++;
                }
                //console.log(this.extPage);
                getSubDataPage(this.fid,type,extFilterKey,this.extPage,this.extSubType);
            }
        },
        showCapDetail:function(id,type){
            var etype="invest";
            if(type==2){
                etype="exit";
            }
            $.ajax({
                url: commonUrls.orgEventDetailUrl,              //请求地址
                type: "get",                            //请求方式
                data: { //请求参数
                    id:id,
                    type:etype
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
                        //console.log("response", response);

                        showCapitalDetail(type,response);
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
    },
    filters:{
        currentPageFilter:function(value){
            var p=value+1;
            return "第"+p+"页";
        },
        ivsTypeSelectFilter:function(value){
            return "ivsTypeSelect_"+value;
        },
        extTypeSelectFilter:function(value){
            return "extTypeSelect_"+value;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        formatDataFilter:function(value){
            return toDataFormat(value);
        }
    }
});

getFundBasicInfo(fid);
// fund 基本信息、资本事件
function getFundBasicInfo(id){
    console.log(id);
    getFundEventInfo(id);
    $.ajax({
        url: commonUrls.orgFundBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:id
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

                v_fundBasicModel.$data.fName=response.fundInfo.fundName;
                v_fundBasicModel.$data.description=response.fundInfo.fundDesc; // 注意这个地方和基金是不同的Vue
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

// 更新基金事件列表
function getFundEventInfo(id){
    // 恢复事件搜索框和页码初始化设置
    v_fundBasicModel.$data.ivsPage=0;
    v_fundBasicModel.$data.extPage=0;
    ivsFilterKey="";
    extFilterKey="";

    v_fundBasicModel.$data.ivsSubType="";
    v_fundBasicModel.$data.extSubType="";

    getSubDataPage(v_fundBasicModel.$data.fid,0,"",0,"");
}

function getSubDataPage(fid,type,key,page,subType){
    var turl=commonUrls.orgFundCaptailUrl;
    var from,count, etype="invest";
    if(type==0){
        etype="all";
        from=page*commonPageNum.orgFundInvEventNum;
        count=commonPageNum.orgFundInvEventNum;
    }else if(type==1){
        etype="invest";
        from=page*commonPageNum.orgFundInvEventNum;
        count=commonPageNum.orgFundInvEventNum;
    }else{
        etype="exit";
        from=page*commonPageNum.orgFundExitEventNum;
        count=commonPageNum.orgFundExitEventNum;
    }
    var rdata={
        id:fid,
        from:from,
        count:count,
        key:key,
        type:etype   // 请求数据类型：投资，并购，退出
    };
    if(subType!=""){
        rdata.filter=subType;
    }

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
                if(type==0){
                    drawChart(response.inventList,response.exitList);
                    updateInvestTable(response.inventList);
                    updateExitTable(response.exitList);
                }else if(type==1){
                    drawChart(response.inventList,v_fundBasicModel.$data.extCapitalList);
                    ivsFilterKey=key;
                    updateInvestTable(response.inventList);
                }else if(type==2){
                    drawChart(v_fundBasicModel.$data.ivsCapitalList,response.exitList);
                    extFilterKey=key;
                    updateExitTable(response.exitList);
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

var echarts;
var myChart;
function drawTreeChart(investdata,exitdata){
    //console.log(investdata.length,exitdata.length);
    var domId="ivs_ext_event_chats";

    require(['echarts', 'echarts/chart/tree'],function(ec){
        echarts=ec;
        var fName=v_fundBasicModel.$data.fName;

/*        var myoption=clone(tree_force_option);
        myoption.series[0].nodes.push({name:fName,category:0,value:100,text:fName});
        myoption.series[0].nodes.push({name:"invest",category:1,value:50,text:"投资节点",symbol:'rectangle'});
        myoption.series[0].nodes.push({name:"exit",category:2,value:50,text:"退出节点",symbol:'rectangle'});
        myoption.series[0].links.push({"source":0,"target":1,weight:300,text:"公司投资事件"});
        myoption.series[0].links.push({"source":0,"target":2,weight:300,text:"公司退出事件"});

        for(var i=0;i<investdata.length;i++){
            var item=investdata[i];
            myoption.series[0].nodes.push({name:"i"+item.eventId,category:3,value:30,text:item.entCnName});
            myoption.series[0].links.push({"source":"invest","target":"i"+item.eventId,weight:Math.random()*100,text:"投资："+item.entCnName});
        }
        for(var i=0;i<exitdata.length;i++){
            var item=exitdata[i];
            myoption.series[0].nodes.push({name:"e"+item.eventId,category:4,value:30,text:item.entCnName});
            myoption.series[0].links.push({"source":"exit","target":"e"+item.eventId,weight:Math.random()*100,text:"退出："+item.entCnName});
        }*/

        var myoption=clone(invest_exit_tree_option);

        var mchildren=[];
        for(var i=0;i<investdata.length;i++){
            var item=investdata[i];
            mchildren.push({
                name:item.entCnName,text:item.entCnName,symbol:'circle',symbolSize:20,
                itemStyle:{
                    normal:{
                        label:{position:'right'},
                        color:'#fa6900',
                        brushType: 'stroke',
                        borderWidth: 1,
                        borderColor: '#999966'
                    },
                    emphasis: {
                        borderWidth: 0
                    }
                }
            });
        }
        for(var i=0;i<exitdata.length;i++){
            var item=exitdata[i];
            mchildren.push({
                name:item.entCnName,text:item.entCnName,symbol:'circle',symbolSize:20,
                itemStyle:{
                    normal:{
                        label:{position:'right'},
                        color:'#6900fa',
                        brushType: 'stroke',
                        borderWidth: 1,
                        borderColor: '#669999'
                    },
                    emphasis: {
                        borderWidth: 0
                    }
                }
            });
        }
        myoption.series[0].data.push({name:fName,children:mchildren});
console.log(myoption.series[0].data);
        if(typeof myChart=='undefined') {
            var dom = document.getElementById(domId);
            myChart = echarts.init(dom);
            myChart.setOption(myoption, true);
            window.addEventListener('resize', function () {
                myChart.resize && myChart.resize();
            });
        }else{
            myChart.setOption(myoption, true);
        }
    });
}

function drawChartNoAssetNode(investdata,exitdata){
    //console.log(investdata.length,exitdata.length);
    var domId="ivs_ext_event_chats";

    require(['echarts', 'echarts/chart/force'],function(ec){
        echarts=ec;
        var fName=v_fundBasicModel.$data.fName;

        var myoption=clone(tree_force_option);
         myoption.series[0].nodes.push({name:fName,category:0,value:100,text:fName});
         myoption.series[0].nodes.push({name:"invest",category:1,value:50,text:"投资节点",symbol:'rectangle'});
         myoption.series[0].nodes.push({name:"exit",category:2,value:50,text:"退出节点",symbol:'rectangle'});
         myoption.series[0].links.push({"source":0,"target":1,weight:300,text:"公司投资事件"});
         myoption.series[0].links.push({"source":0,"target":2,weight:300,text:"公司退出事件"});

         for(var i=0;i<investdata.length;i++){
         var item=investdata[i];
            myoption.series[0].nodes.push({name:"i"+item.eventId,category:3,value:30,text:item.entCnName});
            myoption.series[0].links.push({"source":"invest","target":"i"+item.eventId,weight:Math.random()*100,text:"投资："+item.entCnName});
         }
         for(var i=0;i<exitdata.length;i++){
         var item=exitdata[i];
             myoption.series[0].nodes.push({name:"e"+item.eventId,category:4,value:30,text:item.entCnName});
             myoption.series[0].links.push({"source":"exit","target":"e"+item.eventId,weight:Math.random()*100,text:"退出："+item.entCnName});
         }

        console.log(myoption.series[0].data);
        if(typeof myChart=='undefined') {
            var dom = document.getElementById(domId);
            myChart = echarts.init(dom);
            myChart.setOption(myoption, true);
            window.addEventListener('resize', function () {
                myChart.resize && myChart.resize();
            });
        }else{
            myChart.setOption(myoption, true);
        }
    });
}

function drawChart(investdata,exitdata){
    //console.log(investdata.length,exitdata.length);
    var domId="ivs_ext_event_chats";

    require(['echarts', 'echarts/chart/force'],function(ec){
        echarts=ec;
        var fName=v_fundBasicModel.$data.fName;

        var myoption=clone(tree_force_option_nonode);
        myoption.series[0].nodes.push({name:"root",category:0,value:50,text:fName});

        if(investdata&&investdata.length>0) {
            for (var i = 0; i < investdata.length; i++) {
                var item = investdata[i];
                myoption.series[0].nodes.push({name: "i" + item.eventId, category: 1, value: 30, text: item.entCnName});
                myoption.series[0].links.push({
                    "source": "root",
                    "target": "i" + item.eventId,
                    weight: Math.random() * 100,
                    text: "投资：" + item.entCnName
                });
            }
        }
        if(exitdata&&exitdata.length>0) {
            for (var i = 0; i < exitdata.length; i++) {
                var item = exitdata[i];
                myoption.series[0].nodes.push({name: "e" + item.eventId, category: 2, value: 30, text: item.entCnName});
                myoption.series[0].links.push({
                    "source": "root",
                    "target": "e" + item.eventId,
                    weight: Math.random() * 100,
                    text: "退出：" + item.entCnName
                });
            }
        }

        // console.log(myoption.series[0].data);
        if(typeof myChart=='undefined') {
            var dom = document.getElementById(domId);
            myChart = echarts.init(dom);
            myChart.setOption(myoption, true);
            window.addEventListener('resize', function () {
                myChart.resize && myChart.resize();
            });
        }else{
            myChart.setOption(myoption, true);
        }
    });
}

function resizeDetailMask() {
    var height=$(window).height();
    var mheight=height-121;
    console.log(height,mheight);
    $(".modal-event-body").css('maxHeight',mheight);
}

$(document).ready(function () {
    resizeDetailMask();
});

$(window).resize(function () {
    resizeDetailMask();
});

