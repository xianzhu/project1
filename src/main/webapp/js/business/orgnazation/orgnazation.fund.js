/**
 * Created by a88u on 2016/10/9.
 */

var v_orgFundModel = new Vue({
    el: "#v-orgFundModel",
    data: {
        fundList: [],
        description:"",
        isEnd:false
    },
    ready: function () {
    },
    methods: {
        getFundInfo:function(fitem,event){
            var mevent=window.event||event;
            //console.log(event);
            console.log(mevent.target);

            var ele=$(mevent.target);
            console.log(ele.eq(0));

            var eleItems=ele.eq(0).parent().parent().find("tr");
            for(var i=0;i<eleItems.length;i++){
                var item=$(eleItems[i]);
                item.removeClass("selected");
            }

            ele.eq(0).parent().addClass("selected");

            v_fundBasicModel.$data.fName=fitem.fundName;
            v_fundBasicModel.$data.fid=fitem.fundId;

            getFundBasicInfo(fitem.fundId); // 点击机构基金，更新基金信息
        }
    },
    filters: {
        isDataEmpty:function(value){
            return this.isEnd;
        },
        cutStringFilter:function(value){
            if(value.length<=90){
                return value;
            }else{
                return value.substr(0,90)+"...";
            }
        },
        checkEmptyFilter:function(value){
            var result=false;
            if(value&&value.length>0){
                result=true;
            }
            return result;
        },
        getShortStrFilter:function(value){
            var result=value;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        },
        formatDateFilter:function(value){
            return toDateFormat(value);
        }
    }
});

getFundListInfo();

function getFundListInfo() {
    $.ajax({
        url: commonUrls.orgnazationFundUrl,              //请求地址
        type: "get",                            //请求方式
        data: { //请求参数
            id:v_navModel.$data.id
        },
        dataType: "json",
        success: function (res) {
            if (res.status == 'failure') {
                //goToLoginout();
                console.log("failure",res.message);
            }else if(res.status=="timeout"){
                console.log("timeout");
                goToNotlogon();
            } else if (res.status == 'success') {
                var response = res;
                console.log("response", response);
                $('#fund_table').DataTable().destroy();
                v_orgFundModel.$data.fundList = response.fundList;

                if(!checkNotEmptyList(v_orgFundModel.$data.fundList)){
                    v_orgFundModel.$data.isEnd=true;
                }else{
                    v_orgFundModel.$data.isEnd=false;
                    var fitem=v_orgFundModel.$data.fundList[0];
                    var fid=v_orgFundModel.$data.fundList[0].fundId;

                    v_fundBasicModel.$data.fName=fitem.fundName;
                    v_fundBasicModel.$data.fid=fid;
                    v_orgFundModel.$data.description=fitem.fundDesc; // 注意这个地方和基金是不同的Vue

                    getFundBasicInfo(fid); // 选中第一条基金
                }

                v_orgFundModel.$nextTick(function () {
                    bindExportedDataTable("fund_table", 13, "机构基金信息",{searching:true,dom:'<"html5buttons"B>ftp'});
                    var fundListItems=$("#fundList_tbody").children();
                    if(fundListItems.length>0){
                        fundListItems.first().addClass("selected");
                    }
                });
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function () {
                goTo404();
            },
            500: function () {
                goTo500();
            }
        }
    });
}













