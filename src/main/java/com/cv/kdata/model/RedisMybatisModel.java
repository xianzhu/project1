package com.cv.kdata.model;

import java.util.ArrayList;
import java.util.List;

import com.cv.kdata.cont.RedisPrexConst;
import com.cv.kdata.util.JedisObjectUtil;

public class RedisMybatisModel {
	public static EntAdmin getEntAdmin(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAdminSync, JedisObjectUtil.SEPARATOR, uuid);
		EntAdmin tmp = JedisObjectUtil.getData(EntAdmin.class, key);
		return tmp;
	}

	public static EntBasicInfo getEntBasicInfo(String id) {
		String key = String.format("%s%s%s", RedisPrexConst.ENT_BASIC_INFO, JedisObjectUtil.SEPARATOR, id);
		return JedisObjectUtil.getData(EntBasicInfo.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntBranch> getEntBranchFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntBranchSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntBranch>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntAdmin> getEntAdminFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAdminSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntAdmin>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntAbnormalItem> getEntAbnormalItemFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAbnormalItemSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntAbnormalItem>) JedisObjectUtil.getData(List.class, key);
	}

//	@SuppressWarnings("unchecked")
//	public static ArrayList<EntChgRecord> getEntChgRecordFromList(String uuid) {
//		String key = String.format("%s%s%s", RedisPrexConst.EntChgRecordSync, JedisObjectUtil.SEPARATOR, uuid);
//		return (ArrayList<EntChgRecord>) JedisObjectUtil.getData(List.class, key);
//	}
	public static EntChgRecord getEntChgRecord(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntChgRecordSync, JedisObjectUtil.SEPARATOR, uuid);
		return JedisObjectUtil.getData(EntChgRecord.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntCopyRights> getEntCopyRightsFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntCopyRightsSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntCopyRights>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntEquity> getEntEquityFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntEquitySync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntEquity>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntHolder> getEntHolderFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntHolderSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntHolder>) JedisObjectUtil.getData(List.class, key);
	}

	public static EntIndustry getEntIndustry(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntIndustrySync, JedisObjectUtil.SEPARATOR, uuid);
		EntIndustry tmp = JedisObjectUtil.getData(EntIndustry.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntInvest> getEntInvestFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntInvestSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntInvest>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntLaw> getEntLawFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntLawSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntLaw>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntPatent> getEntPatentFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntPatentSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntPatent>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntSoftCopyrights> getEntSoftCopyrightsFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntSoftCopyrightsSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntSoftCopyrights>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntRelated> getEntRelated(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntRelatedSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntRelated>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntMortgages> getEntMortgages(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntMortgagesSync, JedisObjectUtil.SEPARATOR, uuid);
		return (ArrayList<EntMortgages>) JedisObjectUtil.getData(List.class, key);
	}

	// this part is for stock

	public static StockFeatureAll getStockFeatureAll(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureAllSync, JedisObjectUtil.SEPARATOR, uuid);
		StockFeatureAll tmp = JedisObjectUtil.getData(StockFeatureAll.class, key);
		return tmp;
	}

	public static StockXsbListMatch getStockXsbListMatch(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockXsbListMatchSync, JedisObjectUtil.SEPARATOR, uuid);
		StockXsbListMatch tmp = JedisObjectUtil.getData(StockXsbListMatch.class, key);
		return tmp;
	}

	public static StockListPotentialMa getStockListPotentialMa(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockListPotentialMaSync, JedisObjectUtil.SEPARATOR, uuid);
		StockListPotentialMa tmp = JedisObjectUtil.getData(StockListPotentialMa.class, key);
		return tmp;
	}

	public static StockFeatureAllScaled getStockFeatureAllScaled(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureAllScaledSync, JedisObjectUtil.SEPARATOR, uuid);
		StockFeatureAllScaled tmp = JedisObjectUtil.getData(StockFeatureAllScaled.class, key);
		return tmp;
	}

	public static StockFeatureParameter getStockFeatureParameter(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureParameterSync, JedisObjectUtil.SEPARATOR, uuid);
		StockFeatureParameter tmp = JedisObjectUtil.getData(StockFeatureParameter.class, key);
		return tmp;
	}

	public static StockFinanceChart getStockFinanceChart(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFinanceChartSync, JedisObjectUtil.SEPARATOR, uuid);
		StockFinanceChart tmp = JedisObjectUtil.getData(StockFinanceChart.class, key);
		return tmp;
	}

	public static StockInfo getStockInfo(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockInfoSync, JedisObjectUtil.SEPARATOR, code);
		StockInfo tmp = JedisObjectUtil.getData(StockInfo.class, key);
		return tmp;
	}

	public static StockEquityCtrl getStockEquityCtrl(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockEquityCtrlSync, JedisObjectUtil.SEPARATOR, code);
		StockEquityCtrl tmp = JedisObjectUtil.getData(StockEquityCtrl.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<StockHolderLatest> getStockHolderLatest(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockHolderLatestSync, JedisObjectUtil.SEPARATOR, code);
		return (ArrayList<StockHolderLatest>) JedisObjectUtil.getData(List.class, key);
	}

	public static StockInstInvest getStockInstInvest(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockInstInvestSync, JedisObjectUtil.SEPARATOR, uuid);
		StockInstInvest tmp = JedisObjectUtil.getData(StockInstInvest.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<StockManageTeam> getStockManageTeam(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockManageTeamSync, JedisObjectUtil.SEPARATOR, code);
		return (ArrayList<StockManageTeam>) JedisObjectUtil.getData(List.class, key);
	}

	// this part is the PM event associated
	public static PMExitEvent getPMExitEvent(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.PMExitEventSync, JedisObjectUtil.SEPARATOR, code);
		PMExitEvent tmp = JedisObjectUtil.getData(PMExitEvent.class, key);
		return tmp;
	}

	public static PMExitEventDetail getPMExitEventDetail(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.PMExitEventDetailSync, JedisObjectUtil.SEPARATOR, code);
		PMExitEventDetail tmp = JedisObjectUtil.getData(PMExitEventDetail.class, key);
		return tmp;
	}

	public static PMInvestEvent getPMInvestEvent(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.PMInvestEventSync, JedisObjectUtil.SEPARATOR, code);
		PMInvestEvent tmp = JedisObjectUtil.getData(PMInvestEvent.class, key);
		return tmp;
	}

	public static PMInvestEventDetail getPMInvestEventDetail(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.PMInvestEventDetailSync, JedisObjectUtil.SEPARATOR, code);
		PMInvestEventDetail tmp = JedisObjectUtil.getData(PMInvestEventDetail.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<OpsMonitorMediaIndex> getMeidaList() {
		return (ArrayList<OpsMonitorMediaIndex>) JedisObjectUtil.getData(List.class, RedisPrexConst.Media_Update);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<OpsMonitorMediaIndex> getMeidaTotal() {
		return (ArrayList<OpsMonitorMediaIndex>) JedisObjectUtil.getData(List.class, RedisPrexConst.Media_Total);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<RptAngelList> getRptAngelList() {
		return (ArrayList<RptAngelList>) JedisObjectUtil.getData(List.class, RedisPrexConst.RptAngelListSync);
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<RptOrgerList> getRptOrgList() {
		return (ArrayList<RptOrgerList>) JedisObjectUtil.getData(List.class, RedisPrexConst.RptOrgerListSync);
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<RptToeMa> getRpt2MaList() {
		return (ArrayList<RptToeMa>) JedisObjectUtil.getData(List.class, RedisPrexConst.RptToeMaSync);
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<RptPeFund> getRptPeFundList() {
		return (ArrayList<RptPeFund>) JedisObjectUtil.getData(List.class, RedisPrexConst.RptPeFundSync);
	}

	public static RptEntJudgeValue getEntJudgeValue(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.RptEntJudgeValueSync, JedisObjectUtil.SEPARATOR,
				uuid);
		return JedisObjectUtil.getData(RptEntJudgeValue.class, key);
	}

	public static StaticsInfo getStaticsInfo(){
		String key = String.format("%s", RedisPrexConst.STATICS_INFO);
		return JedisObjectUtil.getData(StaticsInfo.class,key);
	}

}
