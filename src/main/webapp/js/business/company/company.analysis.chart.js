/**
 * Created by a88u on 2016/11/2.
 */
var echarts;
require.config({
    paths:{
        echarts: 'js/plugins/echarts2'
    }
});



function showEnterpriseEcharts(data,id){
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

    //moption.title.text=data.title;
    moption.xAxis[0].data=data.xdata;
    moption.series[0].data=data.ydata.max;
    moption.series[1].data=data.ydata.min;
    moption.series[2].data=data.ydata.finace_value;
    moption.series[3].data=data.ydata.avg;

    myChart.setOption(moption,true);

    window.onresize = function (){
        myChart.resize&&myChart.resize();
    };
    });
}

