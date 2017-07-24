package com.cv.kdata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.cont.RedisPrexConst;
import com.cv.kdata.dao.CiMoneySupMapper;
import com.cv.kdata.dao.CiRcaPayMapper;
import com.cv.kdata.dao.CiSfScaMapper;
import com.cv.kdata.dao.EntBasicInfoMapper;
import com.cv.kdata.dao.PMInvestEventDetailMapper;
import com.cv.kdata.dao.RptEntJudgeValueMapper;
import com.cv.kdata.dao.RptToeMaMapper;
import com.cv.kdata.dao.StockEquityCtrlMapper;
import com.cv.kdata.dao.StockFeatureAllMapper;
import com.cv.kdata.dao.StockHolderLatestMapper;
import com.cv.kdata.dao.StockInstInvestMapper;
import com.cv.kdata.dao.StockManageTeamMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.facade.StockEquityCtrlFacade;
import com.cv.kdata.facade.StockInstInvestFacade;
import com.cv.kdata.model.CiMoneySup;
import com.cv.kdata.model.CiRcaPay;
import com.cv.kdata.model.CiSfSca;
import com.cv.kdata.model.EntBasicInfo;
import com.cv.kdata.model.PMInvestEventDetail;
import com.cv.kdata.model.StockACapitalDebtIndexResponse;
import com.cv.kdata.model.StockEquityCtrl;
import com.cv.kdata.model.StockInstInvest;
import com.cv.kdata.response.EntInfoResponse;
import com.cv.kdata.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kd.jfinal.redis.RedisJfinalModel;
import com.kd.model.general.EntCopyrights;
import com.kd.model.general.EntGeneralObject;

@Service
public class EntInfoService {
	@Autowired
	CiSfScaMapper ciSfScaMapper;
	@Autowired
	CiRcaPayMapper ciRcaPayMapper;
	@Autowired
	CiMoneySupMapper ciMoneySupMapper;
	@Autowired
	RptToeMaMapper RptToeMaMapper;
	@Autowired
	RptEntJudgeValueMapper RptEntJudgeValueMapper;
	@Autowired
	StockEquityCtrlMapper stockEquityCtrlMapper;
	@Autowired
	StockInstInvestMapper stockInstInvestMapper;
	@Autowired
	StockManageTeamMapper stockManageTeamMapper;
	@Autowired
	StockHolderLatestMapper stockHolderLatestMapper;
	@Autowired
	StockFeatureAllMapper stockFeatureAllMapper;
	@Autowired
	EntBasicInfoMapper entBasicInfoMapper;
	@Autowired
	PMInvestEventDetailMapper investEventDetailMapper;

	// 获取企业基本信息
	public void getEntBasicInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		com.kd.model.general.EntBasicInfo basicInfo = null;
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			// basicInfo = RedisMybatisModel.getEntBasicInfo(entId);
			basicInfo = RedisJfinalModel.getEntBasicInfo(entId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent info success!");
			response.setEntBasicInfo(basicInfo);
		}
	}

	// 获取投资信息--ent_invest
	public void getEntInvestInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			EntGeneralObject record = RedisJfinalModel.getEntGeneralObject(entId, RedisPrexConst.EntInvestSync);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent invest info success!");
			if (record != null) {
				response.setEntInvestInfos(StringUtil.toJsonArray(record.getRecord()));
			}
		}
	}

	// 获取子公司信息--ent_branch
	public void getEntBranchInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			// List<EntBranch> BranchList =
			// RedisMybatisModel.getEntBranchFromList(entId);
			List<com.kd.model.general.EntBranch> BranchList = RedisJfinalModel.getEntBranchFromList(entId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent Branch info success!");
			response.setEntBranchInfos(BranchList);
		}
	}

	// 获取股东信息--ent_holder
	public void getEntHolderInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			com.kd.model.general.EntGeneralObject record = RedisJfinalModel.getEntGeneralObject(entId,
					RedisPrexConst.EntHolderSync);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent Branch info success!");
			if (record != null) {
				response.setEntHolderInfos(StringUtil.toJsonArray(record.getRecord()));
			}
		}
	}

	// 获取关联信息--ent_related
	public void getEntRelatedInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			com.kd.model.general.EntBasicInfo basicInfo = RedisJfinalModel.getEntBasicInfo(entId);
			if (basicInfo != null && !StringUtil.isNullOrEmpty(basicInfo.getEmail())) {
				String mail = basicInfo.getEmail();
				String subffix = mail.substring(mail.indexOf("@") + 1);
				List<com.kd.model.general.EntRelated> RelatedList = RedisJfinalModel.getEntRelated(subffix);
				response.setEntRelatedInfos(RelatedList);
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent Branch info success!");
		}
	}

	// 添加营运统计

	// 获取企业变更
	public void getEntChangeInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			// EntChgRecord record = RedisMybatisModel.getEntChgRecord(entId);
			com.kd.model.general.EntChgRecord record = RedisJfinalModel.getEntChgRecord(entId);
			if (record != null && !StringUtil.isNullOrEmpty(record.getRecord())) {
				String tmpRecord = record.getRecord();
				tmpRecord = tmpRecord.replaceAll("\"entChgRecordInfos\":", "");
				response.setEntChgRecordInfos(StringUtil.toJsonArray(tmpRecord));
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent Branch info success!");
		}
	}

	// 获取法务信息
	public void getEntLawInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			// List<EntLaw> chgRecordList =
			// RedisMybatisModel.getEntLawFromList(entId);
			com.kd.model.general.EntGeneralObject record = RedisJfinalModel.getEntGeneralObject(entId,
					RedisPrexConst.EntLawSync);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent Branch info success!");
			if (record != null) {
				response.setEntLawInfos(StringUtil.toJsonArray(record.getRecord()));
			}
		}
	}

	// 获取版权信息
	public void getEntCopyRightsInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			List<EntCopyrights> copyRightsList = RedisJfinalModel.getEntCopyRightsFromList(entId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent copyrights info success!");
			response.setEntCopyrightsInfos(copyRightsList);
		}
	}

	// 获取软件版权信息
	public void getEntSoftCopyRightsInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			List<com.kd.model.general.EntSoftCopyrights> copyRightsList = RedisJfinalModel
					.getEntSoftCopyrightsFromList(entId);
			if (copyRightsList != null) {
				Collections.sort(copyRightsList);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent software copyrights info success!");
			response.setEnSoftCopyrightsInfos(copyRightsList);
		}
	}

	// 获取股权质押信息
	public void getEntEquityInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			List<com.kd.model.general.EntEquity> equityList = RedisJfinalModel.getEntEquityFromList(entId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent software copyrights info success!");
			response.setEntEquityInfos(equityList);
		}
	}

	// 获取资产抵押信息
	public void getEntMortgagesInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			com.kd.model.general.EntGeneralObject record = RedisJfinalModel.getEntGeneralObject(entId,
					RedisPrexConst.EntMortgagesSync);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent mortgages info success!");
			if (record != null) {
				response.setEntMortgagesInfos(StringUtil.toJsonArray(record.getRecord()));
			}
		}
	}

	// 获取专利信息
	public void getEntPatentInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			EntGeneralObject record = RedisJfinalModel.getEntGeneralObject(entId, RedisPrexConst.EntPatentSync);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent patent info success!");
			if (record != null) {
				response.setEntPatentInfos(StringUtil.toJsonArray(record.getRecord()));
			}
		}
	}

	// 获取异常信息
	public void getEntAbnormalInfo(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			List<com.kd.model.general.EntAbnormalItem> abnormalList = RedisJfinalModel
					.getEntAbnormalItemFromList(entId);
			if (abnormalList != null) {
				Collections.sort(abnormalList);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent abnormal item info success!");
			response.setEntAbnormalItemInfos(abnormalList);
		}
	}

	public void getStockPotentialInfo(String code, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {

			Object stock_list_potential_ma;
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			if (code.startsWith("4") || code.startsWith("8")) {
				stock_list_potential_ma = stockFeatureAllMapper.getStockPotentialInfo(code);
			} else {
				stock_list_potential_ma = stockFeatureAllMapper.getXsbPotentialInfo(code);
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get stock potential ma info success!");
			response.setStockPotentialMaInfo(stock_list_potential_ma);
		}
	}

	public void getStockXsbInfo(String code, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {

			Object stock_xsb_list_matching;
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			if (code.startsWith("4") || code.startsWith("8")) {
				stock_xsb_list_matching = stockFeatureAllMapper.getStockInfo(code);
			} else {
				stock_xsb_list_matching = stockFeatureAllMapper.getXsbInfo(code);
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get stock xsb info success!");
			response.setStockXsbMatchInfo(stock_xsb_list_matching);
		}
	}

	public void getChartsInfo(String code, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {
			String sql = " select b.percentile_10,b.avg_value,a.finance_value,b.finance_item,b.percentile_90,"
					+ " case b.report_date when '2015年一季报' then '2015-03' when '2015年中报' then '2015-06' when '2015年三季报' then '2015-09'  when '2015年年报' then '2015-12' "
					+ " when '2016年一季报' then '2016-03' when '2016年中报' then '2016-06' when '2016年三季报' then '2016-09'  end  as report_date  "
					+ " from (select * from stock_finance_chart where stock_code = ?) a  "
					+ " left join stock_finance_chart_industry b on a.em_industry = b.em_industry and a.report_date = b.report_date and a.finance_item = b.finance_item "
					+ " order by report_date ";

			List<Record> records = Db.find(sql, code);

			Hashtable<String, StockACapitalDebtIndexResponse> dic = new Hashtable<>();
			for (Record record : records) {

				String finance_item = record.getStr("finance_item");

				if (StringUtils.isBlank(finance_item)) {
					continue;
				} else {
					finance_item = finance_item.trim();
				}

				Double min_value = record.getDouble("percentile_10");
				Double avg_value = record.getDouble("avg_value");
				Double finance_value = record.getDouble("finance_value");

				if (finance_item.equalsIgnoreCase("营业利润(%)")) {
					finance_item = "营业利润同比增长(%)";
				}
				// String em_industry = record.getStr("em_industry").trim();
				String report_date = record.getStr("report_date");
				if (!StringUtil.isNullOrEmpty(report_date)) {
					report_date = report_date.trim();
				}
				Double max_value = record.getDouble("percentile_90");

				String enKey = StockACapitalDebtIndexResponse.mapping.get(finance_item);
				if (!dic.containsKey(enKey)) {
					dic.put(enKey, new StockACapitalDebtIndexResponse());
				}
				StockACapitalDebtIndexResponse model = dic.get(enKey);
				model.setStock_code(code);
				model.setTitle(finance_item);

				List<String> xdata = model.xdata;
				xdata.add(report_date);

				Hashtable<String, List<Object>> ydata = model.ydata;
				if (!ydata.containsKey("max"))
					ydata.put("max", new ArrayList<>());
				if (!ydata.containsKey("min"))
					ydata.put("min", new ArrayList<>());
				if (!ydata.containsKey("avg"))
					ydata.put("avg", new ArrayList<>());
				if (!ydata.containsKey("finace_value"))
					ydata.put("finace_value", new ArrayList<>());

				ydata.get("max").add(max_value);
				ydata.get("min").add(min_value);
				ydata.get("avg").add(avg_value);
				ydata.get("finace_value").add(finance_value);
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get finance chart success!");
			response.setStockCharts(dic);
		}
	}

	/**
	 * 董监高
	 */
	public void getManagerTeam(String code, EntInfoResponse response) {

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {
			List<com.kd.model.general.StockManageTeam> manageTeam = RedisJfinalModel.getStockManageTeam(code);

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setTeams(manageTeam);
			return;

		}
	}

	/**
	 * 大股东
	 */
	public void getStockHolder(String code, EntInfoResponse response) {

		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {
			// 先取redis数据，如果取不到，再取mysql
			List<com.kd.model.general.StockHolderLatest> holderTeam = RedisJfinalModel.getStockHolderLatest(code);

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setStockHolder(holderTeam);
			return;

		}
	}

	/**
	 * 机构控股
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getStockInstInvest(String code, EntInfoResponse response) {

		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			StockInstInvest item1 = stockInstInvestMapper.selectByStockCode(code);
			StockInstInvestFacade item = new StockInstInvestFacade();
			if (item1 != null) {
				item.setInstInvestFacade(StringUtil.toJsonArray(item1.getInstInv()));
			}
			// item.setInstInv(null);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setStockInstInvest(item.getInstInvestFacade());
		}
	}

	/**
	 * 上市公司对外投资
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getStockEquityCtrl(String code, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		if (StringUtil.isNullOrEmpty(code)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild code");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			StockEquityCtrl item1 = stockEquityCtrlMapper.selectByStockCode(code);
			StockEquityCtrlFacade item = new StockEquityCtrlFacade();
			if (item1 != null) {
				item.setEquityCtrl(StringUtil.toJsonArray(item1.getEqComp()));
			}
			// item.setEqComp(null);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setStockEquityCtrl(item.getEquityCtrl());
		}
	}

	/**
	 * 搜索处理
	 *
	 * @param request
	 * @param response
	 * @param basicResponse
	 */
	public void getSearchInfo(HttpServletRequest request, EntInfoResponse basicResponse) {
		Boolean isStock = Boolean.parseBoolean(request.getParameter("type"));

		List<Object> params = new ArrayList<>();
		String sql = "select a.ent_id,a.ent_name,a.biz_area,a.legal_person,a.biz_stat,a.opt_time_start,case when length(b.stock_code)>0 then true else false end as is_stock,b.stock_code,b.stock_type from ent_basic_info a %s stock_feature_all b on a.ent_name = b.cn_name where 1 = 1 ";

		if (isStock) {// 上市公司
			sql = String.format(sql, " inner join ");
		} else {
			sql = String.format(sql, " left join ");
		}

		Enumeration<String> pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String name = pNames.nextElement();

			String value = request.getParameter(name);
			if (StringUtils.isNotBlank(value)) {
				if (name.equalsIgnoreCase("key")) {
					sql += " and a.ent_name like ? ";
					params.add("%" + value + "%");
				} else if (name.equalsIgnoreCase("isXsb")) {
					sql += " and  b.stock_type = ? ";
					params.add(Double.parseDouble(value));
				} else if (name.endsWith("_max")) {
					name = name.replace("_max", "");
					sql += " and " + name + " <= ? ";
					params.add(Double.parseDouble(value));
				} else if (name.endsWith("_min")) {
					name = name.replace("_min", "");
					sql += " and " + name + " >= ? ";
					params.add(Double.parseDouble(value));
				}
			}
		}

		sql += " limit 50 ";

		System.out.println(sql);
		List<Record> results = params.size() > 0 ? Db.find(sql, params.toArray()) : Db.find(sql);

		basicResponse.setStatus(RDDWebConst.SUCCESS);
		basicResponse.setMessage("success!");
		basicResponse.setSearchResult(results);
	}


	/**
	 * 对一下信息进行定制搜索
	 * 1. 是否上市公司
	 * 2. 关键字
	 * 3. 关键字对应的字段（公司、法人、股东）
	 *
	 * @param request
	 * @param response
	 * @param basicResponse
	 */
	public void search(HttpServletRequest request, EntInfoResponse basicResponse) {
		Boolean isStock = Boolean.parseBoolean(request.getParameter("type"));

		List<Object> params = new ArrayList<>();


		String key = request.getParameter("key");
		int range = StringUtil.parseInt(request.getParameter("range"),111);
		int from = StringUtil.parseInt(request.getParameter("from"),0);
		int count = StringUtil.parseInt(request.getParameter("count"),20);

		String holderSql = "";	//股东查询
		String entSql = "";	//企业查询+法人查询
		String condition = "";

		if(!StringUtil.isNullOrEmpty(key) && range <=111){

			if(1 == range/100){
				//企业查询
				condition += "ent_name like ? ";
				params.add("%" + key + "%");
			}
			if(1 == (range/10)%10){
				//法人查询
				if(StringUtil.isNullOrEmpty(condition)){
					condition += " legal_person=? ";

				}else{
					condition +=" or legal_person=? ";
				}
				params.add(key);
			}


			if(1 == range%10){
				//股东查询，分上市公司和非上市公司
				if(isStock){
					//上市公司从上市公司对应的关系查询
					holderSql = "(select d.uuid from stock_feature_all_uuid d left join "
							+ " stock_holder_latest e on d.stock_code = e.stock_code "
							+ " where e.holder_name=?)";
					params.add(key);

				}else{
					//非上市公司从企业股东查询
					holderSql = " (select uuid from ent_holder where name=?) ";
					params.add(key);

				}

				if(StringUtil.isNullOrEmpty(condition)){
					condition += String.format(" ent_id in " + holderSql);

				}else{
					condition += String.format(" or ent_id in " + holderSql);
				}

			}
		}

		if(StringUtil.isNullOrEmpty(condition)){
			condition = "1=1";
		}
		entSql = String.format("select ent_id, ent_name,biz_area from "
				+ "ent_basic_info where %s "
				+ "order by update_time desc limit ?,?",condition);
		params.add(from);
		params.add(count);

		System.out.println(entSql);
		List<Record> results = Db.find(entSql, params.toArray());

		basicResponse.setStatus(RDDWebConst.SUCCESS);
		basicResponse.setMessage("success!");
		basicResponse.setSearchResult(results);
	}

	/**
	 *
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getEntJudge(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String entId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(entId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			com.kd.model.general.RptEntJudgeValue list = RedisJfinalModel.getEntJudgeValue(entId);

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setRptEntJudgeValue(list);
			return;

		}
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getRpt2MaList(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		int from = StringUtil.parseInt(req.getParameter("from"), 0);
		int count = StringUtil.parseInt(req.getParameter("count"), 18);
//		List<com.kd.model.general.RptToeMa> list = RedisJfinalModel.getRpt2MaList();
//		if (list != null && !list.isEmpty()) {
//			response.setStatus(RDDWebConst.SUCCESS);
//			response.setMessage("success!");
//
//			if (from < list.size()) {
//				response.setRptToMaList(list.subList(from, from + count));
//			} else {
//				response.setMessage(String.format("from is more than %d", list.size()));
//			}
//			return;
//		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<com.cv.kdata.model.RptToeMa> trendList = RptToeMaMapper.getRpt2MaList(from,count);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get rpt_toe_ma list success!");
		response.setRptToMaList(trendList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getCiMoneySupList(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiMoneySup> trendList = ciMoneySupMapper.getCiMoneySupList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get ci_money_sup list success!");
		response.setCiMoneySup(trendList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getCiSfScaList(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiSfSca> trendList = ciSfScaMapper.getCiSfScaList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get CiSfSca list success!");
		response.setCiSfSca(trendList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getCiRcaPayList(HttpServletRequest req, EntInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_RPT);
		List<CiRcaPay> trendList = ciRcaPayMapper.getCiRcaPayList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get CiRcaPay list success!");
		response.setCiRcaPay(trendList);
	}

	public List<EntBasicInfo> getEntInfoByLike(String entName) {
		List<EntBasicInfo> list = entBasicInfoMapper.selectLikeName(entName);
		return list;
	}

	public void getFinanceInfo(HttpServletRequest req, EntInfoResponse response) {
		String uuid = req.getParameter("id");
		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<PMInvestEventDetail> list = investEventDetailMapper.selectByUuid(uuid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get FinanceInfo list success!");
		response.setEntFinancalList(list);
	}
}
