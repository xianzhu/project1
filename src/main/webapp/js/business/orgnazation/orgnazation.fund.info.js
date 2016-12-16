/**
 * Created by a88u on 2016/10/11.
 */

var v_fundBasicModel=new Vue({
    el: '#v-fundBasicModel',
    data: {
        fName:"",
        fid:"",
        description:"",
        time:"",
        amount:"",

        isEnd:false,

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
        pageCaptialFilter:function(type,value){
            //console.log(value,", ",this.newsPage,",",this.isEnd);
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
                console.log(this.ivsPage);
                getSubDataPage(this.fid,type,ivsFilterKey,this.ivsPage,this.ivsSubType);
            }else if(type==2){
                if(value==0){
                    this.extPage--;
                }else{
                    this.extPage++;
                }
                console.log(this.extPage);
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
                        console.log("response", response);

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

// fund 基本信息、资本事件
function getFundBasicInfo(id){
//console.log("getFundBasicInfo");
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

                v_orgFundModel.$data.description=response.fundInfo.fundDesc; // 注意这个地方和基金是不同的Vue
                //v_fundBasicModel.$nextTick(function () {
                //    drawChart(response.inventList,response.exitList);
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

// 更新基金事件列表
function getFundEventInfo(id){
    // 恢复事件搜索框和页码初始化设置
    v_fundBasicModel.$data.ivsPage=0;
    v_fundBasicModel.$data.extPage=0;
    ivsFilterKey="";
    extFilterKey="";

    v_fundBasicModel.$data.ivsSubType="";
    v_fundBasicModel.$data.extSubType="";
    //updateInvestTable(response.inventList);
    //updateExitTable(response.exitList);
    getSubDataPage(v_fundBasicModel.$data.fid,1,"",0,"");
    getSubDataPage(v_fundBasicModel.$data.fid,2,"",0,"");
}

var echarts;
var myChart;
function drawChart(investdata,exitdata){
    console.log(investdata.length,exitdata.length);
    var domId="ivs_ext_event_chats";
    require.config({
        paths:{
            echarts: 'js/plugins/echarts2'
        }
    });
    require(['echarts', 'echarts/chart/force'],function(ec){
        echarts=ec;
var fName=v_fundBasicModel.$data.fName;

        if(myChart && myChart.dispose){
            myChart.dispose();
        }
        var dom=document.getElementById(domId);
        myChart=echarts.init(dom);
        var myoption=clone(tree_force_option);
        myoption.series[0].nodes.push({"name":fName,"category":0,"value":100,"text":fName});
        myoption.series[0].nodes.push({"name":"invest","category":1,"value":70,"text":"投资节点"});
        myoption.series[0].nodes.push({"name":"exit","category":2,"value":70,"text":"退出节点"});
        myoption.series[0].links.push({"source":0,"target":1,"weight":300,"text":"公司投资事件"});
        myoption.series[0].links.push({"source":0,"target":2,"weight":300,"text":"公司退出事件"});

        for(var i=0;i<investdata.length;i++){
            var item=investdata[i];
            myoption.series[0].nodes.push({"name":"i"+item.eventId,"category":3,"value":30,"text":item.entCnName});
            myoption.series[0].links.push({"source":"invest","target":"i"+item.eventId,"weight":Math.random()*100,"text":"投资："+item.entCnName});
        }
        for(var i=0;i<exitdata.length;i++){
            var item=exitdata[i];
            myoption.series[0].nodes.push({"name":"e"+item.eventId,"category":4,"value":30,"text":item.entCnName});
            myoption.series[0].links.push({"source":"exit","target":"e"+item.eventId,"weight":Math.random()*100,"text":"退出："+item.entCnName});
        }
        myChart.setOption(myoption,true);

        window.onresize = function (){
            myChart.resize&&myChart.resize();
        };
    });
}

