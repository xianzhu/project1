/**
 * Created by a88u on 2016/10/12.
 */
var v_testTemplateModel=new Vue({
    el:"#templateModel",
    data: {
        testData:"",
        demo_1:"",
        demo_2:"",
        demo_3:"",
        demo_4:"",
    },
    ready: function () {
    },
    methods: {
    },
    filters: {
    }
});

function searchBarPress(event,value){
    var event = event || window.event; // 为了兼容firefox没有全局event对象
    if (event.keyCode == 13) { // 回车搜索
        var key = value;
        console.log("search key=#", key, "#");
        if (key != "") {
            //  执行搜索
            gotoSearchPage('orgnazation',key);
        }
    }
}

getTemplate();

function getTemplate(){
    $.ajax({
        url: "testData/temp/template.json",              //请求地址
        type: "get",                            //请求方式
        data: { //请求参数
            id:"123"
        },
        dataType: "json",
        success: function (res) {
            console.log(res);
            if(res.status=='failure'){
                //goToLoginout();
                console.log("failure");
            }else if(res.status=='success') {
                var response = res.response;
                console.log("response", response);
//$("#test_inner1").html(response.data1);
//                console.log($("#test_inner1").html());
                v_testTemplateModel.$data.testData=response.data1;
                v_testTemplateModel.$data.demo_1=response.data1_dat;
                v_testTemplateModel.$data.demo_2=response.data2_dat;
                v_testTemplateModel.$data.demo_3=response.data3_dat;
                v_testTemplateModel.$data.demo_4=response.data4_dat;
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

















