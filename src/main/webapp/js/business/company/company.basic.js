/**
 * Created by a88u on 2016/10/23.
 */

var v_companyBasicModel=new Vue({
    el:"#v-companyBasicModel",
    data: {
        info:{},
        changeList:[],
        stockHolder:[]
    },
    ready: function () {
    },
    methods: {
    },
    filters: {
        checkDefinedFilter: function (value) {
            var result= typeof value=="undefined";
            return !result;
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        }
    }
});

getBasicInfo();

function getBasicInfo(){
    $.ajax({
        url: commonUrls.companyBasicUrl,              //请求地址
        type: "POST",                            //请求方式
        data: { //请求参数
            id:v_navModel.$data.id
        },
        dataType: "json",
        success: function (res) {
            if(res.status=="failure"){
                console.log("failure",res.message);
                goToNotlogon();
            }else if(res.status=="success"){
                 var response = res;
                console.log(response);
                $("#change_table").DataTable().destroy();
                v_companyBasicModel.$data.info=response.entBasicInfo;
                v_companyBasicModel.$data.changeList=response.entChgRecordInfos;
                v_companyBasicModel.$data.stockHolder=response.entHolderInfos; // 股东
                v_companyBasicModel.$nextTick(function(){
                    bindSimpleDataTable("change_table",10);
                    bindSimpleDataTable("holder_table",10);
                });
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