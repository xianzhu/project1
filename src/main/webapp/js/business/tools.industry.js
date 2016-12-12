/**
 * Created by a88u on 2016/10/27.
 */
var isFirstSelect=true;

var v_industryToolsModel=new Vue({
    el:"#v-industryToolsModel",
    data:{
        selectTableTitle:"",
        tableSelections:selectedTables,
        result:[],
        tableColumns:[]
},
    methods:{

    },
    filters:{
    checkEmptyFilter:function(value){
        var result=false;
        if(value&&value.length>0){
            result=true;
        }
        return result;
    }
    }
});

function changeTableSelect(){
    var obj = document.getElementById("selectedTableModel"); //selectid
    var index = obj.selectedIndex; // 选中索引
    if(index>0) {
        var title = obj.options[index].text; // 选中文本
        var value = obj.options[index].value; // 选中值

        getDataList(value, title);
    }
}

function getDataList(value,title){
    //var url=commonUrls.industryToolsUrl;
    //var url="testData/ciaTables/selectTable_"+value+".json";
    $.ajax({
        url: commonUrls.industryToolsUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            type:value
        },
        dataType: "json",
        //beforeSend:function(){
        //    $(".waitloadingmask").css('display','block');
        //},
        //complete:function(){
        //    $(".waitloadingmask").css('display','none');
        //},
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=="success"){
                v_industryToolsModel.$data.selectTableTitle=title; // title
                v_industryToolsModel.$data.result=res.object;   // tbody

                var columns=[];
                for(var item in res.propertities) {
                    if(item!="id") {
                        columns.push({value: item, name: res.propertities[item]});    // thead
                    }
                }

                v_industryToolsModel.$data.tableColumns=columns;
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

