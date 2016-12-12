package com.cv.peseer.response;

import java.util.List;

import com.cv.peseer.model.CiMoneySup;
import com.cv.peseer.model.CiRcaPay;
import com.cv.peseer.model.CiSfSca;
import com.cv.peseer.model.EntAbnormalItem;
import com.cv.peseer.model.EntBasicInfo;
import com.cv.peseer.model.EntBranch;
import com.cv.peseer.model.EntCopyRights;
import com.cv.peseer.model.EntEquity;
import com.cv.peseer.model.EntHolder;
import com.cv.peseer.model.EntInvest;
import com.cv.peseer.model.EntLaw;
import com.cv.peseer.model.EntMortgages;
import com.cv.peseer.model.EntPatent;
import com.cv.peseer.model.EntRelated;
import com.cv.peseer.model.EntSoftCopyrights;
import com.cv.peseer.model.RptEntJudgeValue;
import com.cv.peseer.model.RptToeMa;
import com.cv.peseer.model.StockHolderLatest;
import com.cv.peseer.model.StockManageTeam;

public class EntInfoResponse extends ResponseObject {

	private EntBasicInfo entBasicInfo; // 企业基本信息
	private List<EntInvest> entInvestInfos; // 企业投资信息
	private List<EntBranch> entBranchInfos; // 企业分支信息
	private List<EntHolder> entHolderInfos; // 企业股东信息
	private List<EntRelated> entRelatedInfos; // 企业关联信息
	private Object entChgRecordInfos; // 企业变更信息
	private List<EntLaw> entLawInfos; // 企业法务信息
	private List<EntSoftCopyrights> enSoftCopyrightsInfos; // 企业软件版权信息
	private List<EntEquity> entEquityInfos; // 企业股权质押信息

	private List<EntPatent> entPatentInfos; // 企业专利信息
	private List<EntAbnormalItem> entAbnormalItemInfos; // 企业异常信息
	private List<EntMortgages> entMortgagesInfos; // 企业资产抵押信息
	private List<EntCopyRights> entCopyrightsInfos; // 企业版权信息
	private Object stockPotentialMaInfo; // 标的预测信息
	private Object stockXsbMatchInfo; // 新三板对标信息
	private Object stockCharts; // 财务分析
	private Object stockEquityCtrl; //对外投资
	//private StockInstInvestFacade stockInstInvest;  //机构控股
	private Object stockInstInvest;  //机构控股
	private List<StockManageTeam> teams;	//董监高
	private List<StockHolderLatest> stockHolder; //上市公司股东
	private RptEntJudgeValue rptEntJudgeValue; //评分体系

	private List<RptToeMa> rptToMaList; //上市公司的并购事件

	private List<CiMoneySup> ciMoneySup; //货币供应量-货币供应量
	private List<CiRcaPay> ciRcaPay; //消费外流-居民境外消费总支出
	private List<CiSfSca> ciSfSca; //货币供应量-货币供应量
	private Object entFinancalList; //融资情况

	public Object getEntFinancalList() {
		return entFinancalList;
	}

	public void setEntFinancalList(Object entFinancalList) {
		this.entFinancalList = entFinancalList;
	}

	public List<CiMoneySup> getCiMoneySup() {
		return ciMoneySup;
	}

	public void setCiMoneySup(List<CiMoneySup> ciMoneySup) {
		this.ciMoneySup = ciMoneySup;
	}

	public List<CiRcaPay> getCiRcaPay() {
		return ciRcaPay;
	}

	public void setCiRcaPay(List<CiRcaPay> ciRcaPay) {
		this.ciRcaPay = ciRcaPay;
	}

	public List<CiSfSca> getCiSfSca() {
		return ciSfSca;
	}

	public void setCiSfSca(List<CiSfSca> ciSfSca) {
		this.ciSfSca = ciSfSca;
	}

	public List<RptToeMa> getRptToMaList() {
		return rptToMaList;
	}

	public void setRptToMaList(List<RptToeMa> rptToMaList) {
		this.rptToMaList = rptToMaList;
	}

	public RptEntJudgeValue getRptEntJudgeValue() {
		return rptEntJudgeValue;
	}

	public void setRptEntJudgeValue(RptEntJudgeValue rptEntJudgeValue) {
		this.rptEntJudgeValue = rptEntJudgeValue;
	}

	public Object getStockInstInvest() {
		return stockInstInvest;
	}

	public void setStockInstInvest(Object stockInstInvest) {
		this.stockInstInvest = stockInstInvest;
	}



	public Object getStockEquityCtrl() {
		return stockEquityCtrl;
	}

	public void setStockEquityCtrl(Object stockEquityCtrl) {
		this.stockEquityCtrl = stockEquityCtrl;
	}

	public List<StockManageTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<StockManageTeam> teams) {
		this.teams = teams;
	}

	public List<StockHolderLatest> getStockHolder() {
		return stockHolder;
	}

	public void setStockHolder(List<StockHolderLatest> stockHolder) {
		this.stockHolder = stockHolder;
	}

	public Object getStockCharts() {
		return stockCharts;
	}

	public void setStockCharts(Object stockCharts) {
		this.stockCharts = stockCharts;
	}

	public List<EntMortgages> getEntMortgagesInfos() {
		return entMortgagesInfos;
	}

	public void setEntMortgagesInfos(List<EntMortgages> entMortgagesInfos) {
		this.entMortgagesInfos = entMortgagesInfos;
	}


	public Object getStockPotentialMaInfo() {
		return stockPotentialMaInfo;
	}

	public void setStockPotentialMaInfo(Object stockPotentialMaInfo) {
		this.stockPotentialMaInfo = stockPotentialMaInfo;
	}

	public Object getStockXsbMatchInfo() {
		return stockXsbMatchInfo;
	}

	public void setStockXsbMatchInfo(Object stockXsbMatchInfo) {
		this.stockXsbMatchInfo = stockXsbMatchInfo;
	}

	public List<EntLaw> getEntLawInfos() {
		return entLawInfos;
	}

	public List<EntSoftCopyrights> getEnSoftCopyrightsInfos() {
		return enSoftCopyrightsInfos;
	}

	public void setEnSoftCopyrightsInfos(List<EntSoftCopyrights> enSoftCopyrightsInfos) {
		this.enSoftCopyrightsInfos = enSoftCopyrightsInfos;
	}

	public List<EntEquity> getEntEquityInfos() {
		return entEquityInfos;
	}

	public void setEntEquityInfos(List<EntEquity> entEquityInfos) {
		this.entEquityInfos = entEquityInfos;
	}

	public List<EntPatent> getEntPatentInfos() {
		return entPatentInfos;
	}

	public void setEntPatentInfos(List<EntPatent> entPatentInfos) {
		this.entPatentInfos = entPatentInfos;
	}

	public List<EntAbnormalItem> getEntAbnormalItemInfos() {
		return entAbnormalItemInfos;
	}

	public void setEntAbnormalItemInfos(List<EntAbnormalItem> entAbnormalItemInfos) {
		this.entAbnormalItemInfos = entAbnormalItemInfos;
	}

	public List<EntCopyRights> getEntCopyrightsInfos() {
		return entCopyrightsInfos;
	}

	public void setEntCopyrightsInfos(List<EntCopyRights> entCopyrightsInfos) {
		this.entCopyrightsInfos = entCopyrightsInfos;
	}

	public void setEntLawInfos(List<EntLaw> entLawInfos) {
		this.entLawInfos = entLawInfos;
	}

	public Object getEntChgRecordInfos() {
		return entChgRecordInfos;
	}

	public void setEntChgRecordInfos(Object entChgRecordInfos) {
		this.entChgRecordInfos = entChgRecordInfos;
	}

	public List<EntRelated> getEntRelatedInfos() {
		return entRelatedInfos;
	}

	public void setEntRelatedInfos(List<EntRelated> entRelatedInfos) {
		this.entRelatedInfos = entRelatedInfos;
	}

	public List<EntHolder> getEntHolderInfos() {
		return entHolderInfos;
	}

	public void setEntHolderInfos(List<EntHolder> entHolderInfos) {
		this.entHolderInfos = entHolderInfos;
	}

	public List<EntBranch> getEntBranchInfos() {
		return entBranchInfos;
	}

	public void setEntBranchInfos(List<EntBranch> entBranchInfos) {
		this.entBranchInfos = entBranchInfos;
	}

	public List<EntInvest> getEntInvestInfos() {
		return entInvestInfos;
	}

	public void setEntInvestInfos(List<EntInvest> entInvestInfos) {
		this.entInvestInfos = entInvestInfos;
	}

	public EntBasicInfo getEntBasicInfo() {
		return entBasicInfo;
	}

	public void setEntBasicInfo(EntBasicInfo entBasicInfo) {
		this.entBasicInfo = entBasicInfo;
	}
	/**
	 * 搜索结果
	 */
	private Object searchResult;

	/**
	 * 搜索结果
	 */
	public Object getSearchResult() {
		return searchResult;
	}

	/**
	 * 搜索结果
	 */
	public void setSearchResult(Object searchResult) {
		this.searchResult = searchResult;
	}

}
