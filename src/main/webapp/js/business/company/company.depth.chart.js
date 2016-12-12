/**
 * Created by a88u on 2016/11/2.
 */
var echarts;
require.config({
    paths:{
        echarts: 'js/plugins/echarts2'
    }
});



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
        var avgscr=[srcdata.avgBs,srcdata.avgTs,srcdata.avgCrds,srcdata.avgCaps,srcdata.avgSs];

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

