package com.kd.jfinal.redis;

import java.util.ArrayList;
import java.util.List;

import com.cv.kdata.cont.RedisPrexConst;
import com.cv.kdata.model.EntAdmin;
import com.kd.model.general.EntAbnormalItem;
import com.kd.model.general.EntBasicInfo;
import com.kd.model.general.EntBranch;
import com.kd.model.general.EntChgRecord;
import com.kd.model.general.EntCopyrights;
import com.kd.model.general.EntEquity;
import com.kd.model.general.EntGeneralObject;
import com.kd.model.general.EntIndustry;
import com.kd.model.general.EntRelated;
import com.kd.model.general.EntSoftCopyrights;
import com.kd.model.general.StockEquityctrl;
import com.kd.model.general.StockFeatureAll;
import com.kd.model.general.StockFeatureAllScaled;
import com.kd.model.general.StockFeatureParameter;
import com.kd.model.general.StockFinanceChart;
import com.kd.model.general.StockHolderLatest;
import com.kd.model.general.StockInfo;
import com.kd.model.general.StockInstInvest;
import com.kd.model.general.StockListPotentialMa;
import com.kd.model.general.StockManageTeam;
import com.kd.model.general.StockXsbListMatching;

public class RedisJfinalModel {

	public static EntAdmin getEntAdmin(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAdminSync, RedisPrexConst.SEPARATOR, uuid);
		EntAdmin tmp = JedisObjectUtil.getData(EntAdmin.class, key);
		return tmp;
	}

	public static EntBasicInfo getEntBasicInfo(String id) {
		String key = String.format("%s%s%s", RedisPrexConst.EntBasicInfoSync, RedisPrexConst.SEPARATOR, id);
		return JedisObjectUtil.getData(EntBasicInfo.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntBranch> getEntBranchFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntBranchSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntBranch>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntAdmin> getEntAdminFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAdminSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntAdmin>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntAbnormalItem> getEntAbnormalItemFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntAbnormalItemSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntAbnormalItem>) JedisObjectUtil.getData(List.class, key);
	}

//	@SuppressWarnings("unchecked")
//	public static ArrayList<EntChgRecord> getEntChgRecordFromList(String uuid) {
//		String key = String.format("%s%s%s", RedisPrexConst.EntChgRecordSync, RedisPrexConst.SEPARATOR, uuid);
//		return (ArrayList<EntChgRecord>) JedisObjectUtil.getData(List.class, key);
//	}
	public static EntChgRecord getEntChgRecord(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntChgRecordSync, RedisPrexConst.SEPARATOR, uuid);
		return JedisObjectUtil.getData(EntChgRecord.class, key);
	}

	/**
	 * 获取通用代码（uuid,record）
	 * @param uuid
	 * @param table
	 * @return
	 */
	public static EntGeneralObject getEntGeneralObject(String uuid,String table) {
		String key = String.format("%s%s%s", table, RedisPrexConst.SEPARATOR, uuid);
		return JedisObjectUtil.getData(EntGeneralObject.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntCopyrights> getEntCopyRightsFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntCopyRightsSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntCopyrights>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntEquity> getEntEquityFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntEquitySync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntEquity>) JedisObjectUtil.getData(List.class, key);
	}

	public static EntIndustry getEntIndustry(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntIndustrySync, RedisPrexConst.SEPARATOR, uuid);
		EntIndustry tmp = JedisObjectUtil.getData(EntIndustry.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntSoftCopyrights> getEntSoftCopyrightsFromList(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntSoftCopyrightsSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntSoftCopyrights>) JedisObjectUtil.getData(List.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<EntRelated> getEntRelated(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.EntRelatedSync, RedisPrexConst.SEPARATOR, uuid);
		return (ArrayList<EntRelated>) JedisObjectUtil.getData(List.class, key);
	}

	// this part is for stock

	public static StockFeatureAll getStockFeatureAll(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureAllSync, RedisPrexConst.SEPARATOR, uuid);
		StockFeatureAll tmp = JedisObjectUtil.getData(StockFeatureAll.class, key);
		return tmp;
	}

	public static StockXsbListMatching getStockXsbListMatch(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockXsbListMatchSync, RedisPrexConst.SEPARATOR, uuid);
		StockXsbListMatching tmp = JedisObjectUtil.getData(StockXsbListMatching.class, key);
		return tmp;
	}

	public static StockListPotentialMa getStockListPotentialMa(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockListPotentialMaSync, RedisPrexConst.SEPARATOR, uuid);
		StockListPotentialMa tmp = JedisObjectUtil.getData(StockListPotentialMa.class, key);
		return tmp;
	}

	public static StockFeatureAllScaled getStockFeatureAllScaled(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureAllScaledSync, RedisPrexConst.SEPARATOR, uuid);
		StockFeatureAllScaled tmp = JedisObjectUtil.getData(StockFeatureAllScaled.class, key);
		return tmp;
	}

	public static StockFeatureParameter getStockFeatureParameter(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFeatureParameterSync, RedisPrexConst.SEPARATOR, uuid);
		StockFeatureParameter tmp = JedisObjectUtil.getData(StockFeatureParameter.class, key);
		return tmp;
	}

	public static StockFinanceChart getStockFinanceChart(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockFinanceChartSync, RedisPrexConst.SEPARATOR, uuid);
		StockFinanceChart tmp = JedisObjectUtil.getData(StockFinanceChart.class, key);
		return tmp;
	}

	public static StockInfo getStockInfo(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockInfoSync, RedisPrexConst.SEPARATOR, code);
		StockInfo tmp = JedisObjectUtil.getData(StockInfo.class, key);
		return tmp;
	}

	public static StockEquityctrl getStockEquityCtrl(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockEquityCtrlSync, RedisPrexConst.SEPARATOR, code);
		StockEquityctrl tmp = JedisObjectUtil.getData(StockEquityctrl.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<StockHolderLatest> getStockHolderLatest(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockHolderLatestSync, RedisPrexConst.SEPARATOR, code);
		return (ArrayList<StockHolderLatest>) JedisObjectUtil.getData(List.class, key);
	}

	public static StockInstInvest getStockInstInvest(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.StockInstInvestSync, RedisPrexConst.SEPARATOR, uuid);
		StockInstInvest tmp = JedisObjectUtil.getData(StockInstInvest.class, key);
		return tmp;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<StockManageTeam> getStockManageTeam(String code) {
		String key = String.format("%s%s%s", RedisPrexConst.StockManageTeamSync, RedisPrexConst.SEPARATOR, code);
		return (ArrayList<StockManageTeam>) JedisObjectUtil.getData(List.class, key);
	}

	public static com.kd.model.general.RptEntJudgeValue getEntJudgeValue(String uuid) {
		String key = String.format("%s%s%s", RedisPrexConst.RptEntJudgeValueSync, RedisPrexConst.SEPARATOR,
				uuid);
		return JedisObjectUtil.getData(com.kd.model.general.RptEntJudgeValue.class, key);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<com.kd.model.general.RptToeMa> getRpt2MaList() {
		return (ArrayList<com.kd.model.general.RptToeMa>) JedisObjectUtil.getData(List.class, RedisPrexConst.RptToeMaSync);
	}

}
