/**
 * Created by a88u on 2016/11/2.
 */
var echarts;
require.config({
    paths:{
        echarts: 'js/plugins/echarts-2.2.7'
    }
});

var myCharts={};

function setEchart(id,moption) {
    var myChart;
    if(typeof myCharts[id]!='undefined'){
        console.log("get",myCharts[id]);
        myChart= myCharts[id];
        myChart.setOption(moption,true);
    }else {
        require(['echarts', 'echarts/chart/line'], function (ec) {
            echarts = ec;
            var dom = document.getElementById(id);
            myChart = echarts.init(dom);
            myCharts[id] = myChart;
            myChart.setOption(moption,true);
            window.addEventListener('resize',function () {
                console.log("resize",id);
                myChart.resize&&myChart.resize();
            })
        });
    }
}

function showEnterpriseEcharts(data,id){
    console.log(id);
    if(typeof data =='undefined'){
        console.log("data is undefined.");
        $("#"+id).css('display','none');
        return;
    }

    var moption=clone(company_analysis_option);

    if(typeof data.xdata!='undefined') {
        for (var i = 0; i < data.xdata.length; i++) {
            var item = data.xdata[i];
            if (item) {
                moption.xAxis[0].data.push(item);
                moption.series[0].data.push(data.ydata.max[i]);
                moption.series[1].data.push(data.ydata.min[i]);
                moption.series[2].data.push(data.ydata.finace_value[i]);
                moption.series[3].data.push(data.ydata.avg[i]);
            } else {
                //moption.xAxis[0].data.push("--");
            }
        }
    }
    setEchart(id,moption);
}

function showEnterpriseEcharts_old(data,id){
    console.log(id);
    if(typeof data =='undefined'){
        console.log("data is undefined.");
        $("#"+id).css('display','none');
        return;
    }

    require(['echarts', 'echarts/chart/line'],function(ec){
        echarts=ec;
        console.log(ec);

    var myChart;
    var dom=document.getElementById(id);
    myChart=echarts.init(dom);

    var moption=clone(company_analysis_option);

    // moption.title.text=data.title;
        for(var i=0;i<data.xdata.length;i++){
            var item=data.xdata[i];
            if(item){
                moption.xAxis[0].data.push(item);
                moption.series[0].data.push(data.ydata.max[i]);
                moption.series[1].data.push(data.ydata.min[i]);
                moption.series[2].data.push(data.ydata.finace_value[i]);
                moption.series[3].data.push(data.ydata.avg[i]);
            }else{
                //moption.xAxis[0].data.push("--");
            }
        }
    //moption.xAxis[0].data=data.xdata;
    //moption.series[0].data=data.ydata.max;
    //moption.series[1].data=data.ydata.min;
    //moption.series[2].data=data.ydata.finace_value;
    //moption.series[3].data=data.ydata.avg;

    myChart.setOption(moption,true);

    // window.onresize = function (){
    //     console.log("resize",id);
    //     myChart.resize&&myChart.resize();
    // };
        window.addEventListener('resize',function(){
            console.log("resize",id);
            myChart.resize&&myChart.resize();
        });
    });

}

function drawRadarChart(srcdata,id){
    console.log(id);
    if(typeof srcdata =='undefined'){
        console.log("data is undefined.");
        $("#"+id).css('display','none');
        return;
    }

    require(['echarts', 'echarts/chart/radar'],function(ec){
        echarts=ec;
        console.log(ec);

        var myChart;
        var dom=document.getElementById(id);
        myChart=echarts.init(dom);

        var moption=clone(radar_option);

        var score=[srcdata.bs,srcdata.ts,srcdata.crds,srcdata.caps,srcdata.ss];
        var avgscr=[srcdata.avg_bs,srcdata.avg_ts,srcdata.avg_crds,srcdata.avg_caps,srcdata.avg_ss];

        var sdata={"name":"评分","value":score};
        var avgdata={"name":"均值","value":avgscr}

        //moption.title.text=data.title;
        //moption.polar[0].indicator=data.category;
        moption.series[0].data=[sdata,avgdata];

        myChart.setOption(moption,true);

        window.onresize = function (){
            myChart.resize&&myChart.resize();
        };
    });
}