/**
 * Created by a88u on 2016/10/23.
 */
getDepthInfo();
getReportInfo();

function getDepthInfo(){
    console.log("depth");
    $.ajax({
        url: commonUrls.companyDepthUrl,
        type: "POST",
        data: {
            id:cid
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
                v_companyInfoModel.$data.investCompany=response.entInvestInfos; // 投资企业
                v_companyInfoModel.$data.branchs=response.entBranchInfos; // 分支机构

                var relateList=[],flag=false;
                if(response&&response.entRelatedInfos) {
                    for (var i = 0; i < response.entRelatedInfos.length; i++) {
                        var item = response.entRelatedInfos[i];
                        if (flag) {
                            relateList[relateList.length - 1].push(item);
                        } else {
                            relateList.push([item]);
                        }
                        flag = !flag;
                    }
                }
                v_companyInfoModel.$data.perRelation=relateList;
                v_companyInfoModel.$data.softCopyrightsList=response.enSoftCopyrightsInfos; // 软件著作权
                v_companyInfoModel.$data.patentList=response.entPatentInfos; // 专利

                v_companyInfoModel.$nextTick(function(){
                    bindSimpleDataTable("invest_table",commonPageNum.companyInvest); // "投资企业",{});
                    bindSimpleDataTable("branch_table",commonPageNum.companyBranch); // "分支机构",{});
                    bindSimpleDataTable("softCopy_table",commonPageNum.companySoftCopy); // "软件著作权",{});
                    bindSimpleDataTable("patent_table",commonPageNum.companyPatent); // "专利",{});
                    bindSimpleDataTable("relation_table",commonPageNum.companyRelation); // "关联预测",{});
                    v_companyInfoModel.$data.copyRightModule=1;
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

function getReportInfo(){
    $.ajax({
        url: commonUrls.companyCreditUrl,
        type: "POST",
        data: {
            id:cid
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
                $("#law_table").DataTable().destroy();
                $("#abnormal_table").DataTable().destroy();
                $("#equity_table").DataTable().destroy();
                $("#mortgages_table").DataTable().destroy();
                v_companyInfoModel.$data.entLawList=response.entLawInfos;
                v_companyInfoModel.$data.abnormalItemList=response.entAbnormalItemInfos;
                v_companyInfoModel.$data.equityList=response.entEquityInfos;
                v_companyInfoModel.$data.mortgagesList=response.entMortgagesInfos;
                v_companyInfoModel.$nextTick(function () {
                    bindSimpleDataTable("law_table",commonPageNum.companyLaw); // "法务",{});
                    bindSimpleDataTable("abnormal_table",commonPageNum.companyAbnormal); // "经营异常",{});
                    bindSimpleDataTable("equity_table",commonPageNum.companyEquity); // "股权质押",{});
                    bindSimpleDataTable("mortgages_table",commonPageNum.companyMortgages); // "动产抵押",{});
                    v_companyInfoModel.$data.lawModule=1;
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