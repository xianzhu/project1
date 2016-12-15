/**
 * Created by a88u on 2016/10/23.
 */

var v_companyDepthModel=new Vue({
    el:"#v-companyDepthModel",
    data: {
        investCompany:[], // 投资企业
        branchs:[], // 分支机构
        //stockHolder:[],
        perRelation:[], // 关联预测
        //operaStat:[],

        softCopyrightsList:[],// 软件著作权	ent_soft_copyrights
        //copyrightsList:[], // 版权	ent_copyrights
        patentList:[] // 专利 ent_patent
    },
    ready: function () {
    },
    methods: {
        gotoCompany:function(id){
            var params;
            gotoCompanybyId(id);
        }
    },
    filters: {
    checkEmptyFilter:function(value){
        //console.log(value);
        var result=false;
        if(value&&value!=null&&value.length>0){
            result=true;
        }
        console.log(value,', ',result);
        return result;
    },
        getShortStrFilter:function(value){
            var result;
            if(value&&value.length>60){
                result=value.substr(0,60)+"...";
            }
            return result;
        }
    }
});

getDepthInfo();

function getDepthInfo(){
    $.ajax({
        url: commonUrls.companyDepthUrl,
        type: "POST",
        data: {
            id:v_navModel.$data.id
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
                $("#invest_table").DataTable().destroy();
                $("#branch_table").DataTable().destroy();
                $("#softCopy_table").DataTable().destroy();
                $("#patent_table").DataTable().destroy();
                $("#relation_table").DataTable().destroy();
                v_companyDepthModel.$data.investCompany=response.entInvestInfos; // Ͷ投资企业
                v_companyDepthModel.$data.branchs=response.entBranchInfos; // 分支机构
                //v_companyDepthModel.$data.stockHolder=response.entHolderInfos; // �ɶ�
                v_companyDepthModel.$data.perRelation=response.entRelatedInfos; // 关联预测
                //v_companyDepthModel.$data.operaStat=response.stock_onclick; // ��Ӫͳ��

                v_companyDepthModel.$data.softCopyrightsList=response.enSoftCopyrightsInfos; // 软件著作权
                //v_companyDepthModel.$data.copyrightsList=response.entCopyrightsInfos; //
                v_companyDepthModel.$data.patentList=response.entPatentInfos; // 专利

                v_companyDepthModel.$nextTick(function(){
                    bindExportedDataTable("invest_table",10,"投资企业",{});
                    bindExportedDataTable("branch_table",10,"分支机构",{});
                    bindExportedDataTable("softCopy_table",10,"软件著作权",{});
                    bindExportedDataTable("patent_table",10,"专利",{});
                    bindExportedDataTable("relation_table",30,"关联预测",{});
                });

                drawRadarChart(response.rptEntJudgeValue,"scoreSystem");
            }
        },
        fail: function (status) {
            console.error("event id=", id, " error. status=", status);
        },
        statusCode: {
            404: function() {
                //goTo404();
            },
            500:function(){
                goTo500();
            }
        }
    });
}