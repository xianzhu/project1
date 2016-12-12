/**
 * Created by a88u on 2016/10/20.
 */
var chinaMap = new Map(); // 中国地图数据
//var newsArray = [];

var indexTimer;
var chinaLoop = 100; // 中国地图刷新频率


var config = {
    'cStaticConvas' : '#staticCav',
    'cAnimateConvas' : '#animatedCav',
    'cMap' : '#locationNews-map',
    'cMainDiv' : 'locationNews-map'
}

$(document).ready(function(){
    initVectorMap();
    setChinaCanvasSize();
    chinaMapInit();
    refresh();
    // 每隔1秒刷新
    setInterval(refresh, 1000 * 10);
    chinaMapShow();
});

// 初始化地图
function initVectorMap(){
    $('#locationNews-map').vectorMap({
        map: 'dijishi_merc',
        backgroundColor:"transparent",
        regionStyle: {
            initial: {
                fill: '#000',
                //fill:'#333',
                "fill-opacity": 0.9
            }
        }
    });
}

var locationNewsIndex=0;
var refresh = function() {
    var index=locationNewsIndex;

    $.ajax({
        url:commonUrls.homeBasicMapUrl,
        type:"get",
        data:{
            idList:1,
            locationNewsId:index
        },
        dataType:"json",
        success:function(res){
            if(res.status=="failure"){
                console.log("failure:",res.message);
            }else if(res.status=="success") {
                var response = res;
                // 中国地图[{id,lng,lat,news}]
                if(response.news&&response.news.length>0) {
                    for (var j = 0; j < response.news.length; j++) {
                        var chinaModel = response.news[j];

                        var info = new infoObj(
                            chinaModel.id,
                            chinaModel.lat,
                            chinaModel.lng,
                            chinaModel.msg);
                        info.add();
                    }
                    locationNewsIndex = response.news[response.news.length - 1].id;
                }
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
};

// 清空画布
function clearCanvas(context,width,height){
    context.clearRect(0, 0, width, height);
}





