package com.cv.kdata.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.StockFeatureAllScaled;
import com.cv.kdata.model.StockFeatureParameter;
import com.cv.kdata.response.XsbListMatchingResponse;
import com.cv.kdata.util.CollectionUtil;
import com.cv.kdata.util.MysqlHelper;
import com.cv.kdata.util.StockFeatureAllScaledDataHelper;
import com.cv.kdata.util.StockFeatureParameterDataHelper;
import com.cv.kdata.util.TopicClient;
import com.cv.kdata.xsbresponse.EntMatch;
import com.cv.kdata.xsbresponse.MatchingCalculateResult;
import com.cv.kdata.xsbresponse.MiddleReturnValue;

@Service
public class XsbListMatchingService {
	public MiddleReturnValue CalculateMatching(HttpServletRequest req, List<StockFeatureAllScaled> stocka_list,
			List<StockFeatureAllScaled> xsb_list, String topic_industry, double is_state_hold, double total_asset,
			double liab_ratio, double opt_income, double opt_profit_on_income, double gross_profit_margin) {
		double is_dev = 0;
		double chairman_cum_ceo = 0;
		double opt_profit_growth = 0;
		double opt_profit = 0;

		double opt_income_growth = 0;
		double share_conc = 0;
		double share_balance = 0;
		double person_hold = 0;
		double ceo_education = 0;
		double ceo_age = 0;
		double net_profit_on_asset = 0;
		double total_asset_growth = 0;
		double holder_equity_on_liab = 0;
		double asset_turnover = 0;
		double net_profit_on_income = 0;
		double net_profit_margin = 0;
		double ma_fee_on_income = 0;
		double trade_cycle = 0;
		double days_in_inventory = 0;
		double interest_liab_on_capital = 0;
		double current_asset_ratio = 0;
		double ROA = 0;
		double tangible_asset_on_liab = 0;
		double current_liab_ratio = 0;
		double opt_cost_on_income = 0;
		double ROIC = 0;
		double ROE = 0;

		double diff = 0;
		List<MatchingCalculateResult> compareResults_stocka = new ArrayList<>();
		List<MatchingCalculateResult> compareResults_xsb = new ArrayList<>();

		for (StockFeatureAllScaled stockFeatureAllScaled : stocka_list) {
			StockFeatureAllScaled t = stockFeatureAllScaled;
			diff = 0.03 * Math.abs(is_dev - t.is_dev) + 0.05 * Math.abs(chairman_cum_ceo - t.chairman_cum_ceo)
					+ 0.08 * Math.abs(is_state_hold - t.is_state_hold)
					+ 0.05 * Math.abs(opt_profit_growth - t.opt_profit_growth)
					+ 0.10 * Math.abs(opt_profit - t.opt_profit) + 0.05 * Math.abs(total_asset - t.total_asset)
					+ 0.10 * Math.abs(liab_ratio - t.liab_ratio) + 0.05 * Math.abs(opt_income - t.opt_income)
					+ 0.10 * Math.abs(opt_profit_on_income - t.opt_profit_on_income)
					+ 0.03 * Math.abs(opt_income_growth - t.opt_income_growth)
					+ 0.02 * Math.abs(share_conc - t.share_conc) + 0.02 * Math.abs(share_balance - t.share_balance)
					+ 0.02 * Math.abs(person_hold - t.person_hold) + 0.02 * Math.abs(ceo_education - t.ceo_education)
					+ 0.02 * Math.abs(ceo_age - t.ceo_age)
					+ 0.02 * Math.abs(net_profit_on_asset - t.net_profit_on_asset)
					+ 0.02 * Math.abs(total_asset_growth - t.total_asset_growth)
					+ 0.01 * Math.abs(holder_equity_on_liab - t.holder_equity_on_liab)
					+ 0.01 * Math.abs(asset_turnover - t.asset_turnover)
					+ 0.01 * Math.abs(net_profit_on_income - t.net_profit_on_income)
					+ 0.02 * Math.abs(net_profit_margin - t.net_profit_margin)
					+ 0.01 * Math.abs(ma_fee_on_income - t.ma_fee_on_income)
					+ 0.02 * Math.abs(trade_cycle - t.trade_cycle)
					+ 0.02 * Math.abs(days_in_inventory - t.days_in_inventory)
					+ 0.01 * Math.abs(interest_liab_on_capital - t.interest_liab_on_capital)
					+ 0.01 * Math.abs(current_asset_ratio - t.current_asset_ratio) + 0.02 * Math.abs(ROA - t.ROA)
					+ 0.01 * Math.abs(tangible_asset_on_liab - t.tangible_asset_on_liab)
					+ 0.01 * Math.abs(current_liab_ratio - t.current_liab_ratio)
					+ 0.02 * Math.abs(gross_profit_margin - t.gross_profit_margin)
					+ 0.01 * Math.abs(opt_cost_on_income - t.opt_cost_on_income) + 0.01 * Math.abs(ROIC - t.ROIC)
					+ 0.02 * Math.abs(ROE - t.ROE);

			MatchingCalculateResult compareResult = new MatchingCalculateResult();
			compareResult.stock_code = t.stock_code;
			compareResult.diff = diff;
			compareResults_stocka.add(compareResult);
		}

		for (StockFeatureAllScaled stockFeatureAllScaled : xsb_list) {
			StockFeatureAllScaled t = stockFeatureAllScaled;
			diff = 0.03 * Math.abs(is_dev - t.is_dev) + 0.05 * Math.abs(chairman_cum_ceo - t.chairman_cum_ceo)
					+ 0.08 * Math.abs(is_state_hold - t.is_state_hold)
					+ 0.05 * Math.abs(opt_profit_growth - t.opt_profit_growth)
					+ 0.10 * Math.abs(opt_profit - t.opt_profit) + 0.05 * Math.abs(total_asset - t.total_asset)
					+ 0.10 * Math.abs(liab_ratio - t.liab_ratio) + 0.05 * Math.abs(opt_income - t.opt_income)
					+ 0.10 * Math.abs(opt_profit_on_income - t.opt_profit_on_income)
					+ 0.03 * Math.abs(opt_income_growth - t.opt_income_growth)
					+ 0.02 * Math.abs(share_conc - t.share_conc) + 0.02 * Math.abs(share_balance - t.share_balance)
					+ 0.02 * Math.abs(person_hold - t.person_hold) + 0.02 * Math.abs(ceo_education - t.ceo_education)
					+ 0.02 * Math.abs(ceo_age - t.ceo_age)
					+ 0.02 * Math.abs(net_profit_on_asset - t.net_profit_on_asset)
					+ 0.02 * Math.abs(total_asset_growth - t.total_asset_growth)
					+ 0.01 * Math.abs(holder_equity_on_liab - t.holder_equity_on_liab)
					+ 0.01 * Math.abs(asset_turnover - t.asset_turnover)
					+ 0.01 * Math.abs(net_profit_on_income - t.net_profit_on_income)
					+ 0.02 * Math.abs(net_profit_margin - t.net_profit_margin)
					+ 0.01 * Math.abs(ma_fee_on_income - t.ma_fee_on_income)
					+ 0.02 * Math.abs(trade_cycle - t.trade_cycle)
					+ 0.02 * Math.abs(days_in_inventory - t.days_in_inventory)
					+ 0.01 * Math.abs(interest_liab_on_capital - t.interest_liab_on_capital)
					+ 0.01 * Math.abs(current_asset_ratio - t.current_asset_ratio) + 0.02 * Math.abs(ROA - t.ROA)
					+ 0.01 * Math.abs(tangible_asset_on_liab - t.tangible_asset_on_liab)
					+ 0.01 * Math.abs(current_liab_ratio - t.current_liab_ratio)
					+ 0.02 * Math.abs(gross_profit_margin - t.gross_profit_margin)
					+ 0.01 * Math.abs(opt_cost_on_income - t.opt_cost_on_income) + 0.01 * Math.abs(ROIC - t.ROIC)
					+ 0.02 * Math.abs(ROE - t.ROE);

			MatchingCalculateResult compareResult = new MatchingCalculateResult();
			compareResult.stock_code = t.stock_code;
			compareResult.diff = diff;
			compareResults_xsb.add(compareResult);
		}

		Collections.sort(compareResults_stocka, new Comparator<MatchingCalculateResult>(){
			@Override
			public int compare(MatchingCalculateResult o1, MatchingCalculateResult o2) {				
				return o1.diff>o2.diff?1:(o1.diff<o2.diff?-1:0);
			}
		});
		
		Collections.sort(compareResults_xsb, new Comparator<MatchingCalculateResult>(){
			@Override
			public int compare(MatchingCalculateResult o1, MatchingCalculateResult o2) {				
				return o1.diff>o2.diff?1:(o1.diff<o2.diff?-1:0);
			}
		});
		
		MiddleReturnValue middleReturnValue = new MiddleReturnValue();
		middleReturnValue.compareResults_stocka = compareResults_stocka;
		middleReturnValue.compareResults_xsb = compareResults_xsb;
		return middleReturnValue;
	}
	
	public void search(HttpServletRequest req, XsbListMatchingResponse xsbListMatchingResponse){
		
		String topic_content = req.getParameter("topic_content");
		if (StringUtils.isEmpty(topic_content)) {
			xsbListMatchingResponse.setStatus(RDDWebConst.FAILURE);
			xsbListMatchingResponse.setMessage("topic_content is missing or empty!!");
			return;
		}

		String topic_industry = TopicClient.getIndustryTopic(topic_content);
		if (topic_industry == null) {
			
			xsbListMatchingResponse.setStatus(RDDWebConst.FAILURE);
			xsbListMatchingResponse.setMessage("topic_industry calculate error!!");
			return;
		}
		StockFeatureParameter stockFeatureParameter = StockFeatureParameterDataHelper.getInstance()
				.getStockFeatureParameter(topic_industry);
		List<StockFeatureAllScaled> stocka_list = StockFeatureAllScaledDataHelper.getInstance()
				.getStockaFeatureAllScaled(topic_industry);
		List<StockFeatureAllScaled> xsb_list = StockFeatureAllScaledDataHelper.getInstance()
				.getXsbFeatureAllScaled(topic_industry);
		if (stockFeatureParameter == null) {
			
			xsbListMatchingResponse.setStatus(RDDWebConst.FAILURE);
			xsbListMatchingResponse.setMessage("failed to get stockFeatureParameter");
			return;
		}

		if (stocka_list == null) {
			
			xsbListMatchingResponse.setStatus(RDDWebConst.FAILURE);
			xsbListMatchingResponse.setMessage("failed to get stocka_list");
			return;
		}

		if (xsb_list == null) {
			
			xsbListMatchingResponse.setStatus(RDDWebConst.FAILURE);
			xsbListMatchingResponse.setMessage("failed to get xsb_list");
			return;
		}

		double is_state_hold = 0;
		String input_is_state_hold_str = req.getParameter("is_state_hold");
		if (!StringUtils.isEmpty(input_is_state_hold_str) && input_is_state_hold_str.matches("[0-9]+")) {
			is_state_hold = Double.valueOf(input_is_state_hold_str);
		}

		double input_total_asset = 0;
		double input_liab_ratio = 0;
		double input_opt_profit = 0;
		double input_opt_income = 0;
		double input_gross_profit_margin = 0;

		String input_total_asset_str = req.getParameter("total_asset");
		if (!StringUtils.isEmpty(input_total_asset_str) && input_total_asset_str.matches("[0-9]+.")) {
			is_state_hold = Double.valueOf(input_total_asset_str);
		}
		String input_liab_ratio_str = req.getParameter("input_liab_ratio");
		if (!StringUtils.isEmpty(input_liab_ratio_str) && input_liab_ratio_str.matches("[0-9]+.")) {
			is_state_hold = Double.valueOf(input_liab_ratio_str);
		}
		String input_opt_profit_str = req.getParameter("input_opt_profit");
		if (!StringUtils.isEmpty(input_opt_profit_str) && input_opt_profit_str.matches("[0-9]+.")) {
			is_state_hold = Double.valueOf(input_opt_profit_str);
		}
		String input_opt_income_str = req.getParameter("input_opt_income");
		if (!StringUtils.isEmpty(input_opt_income_str) && input_opt_income_str.matches("[0-9]+.")) {
			is_state_hold = Double.valueOf(input_opt_income_str);
		}
		String input_gross_profit_margin_str = req.getParameter("input_gross_profit_margin");
		if (!StringUtils.isEmpty(input_gross_profit_margin_str) && input_gross_profit_margin_str.matches("[0-9]+.")) {
			is_state_hold = Double.valueOf(input_gross_profit_margin_str);
		}

		double total_asset = (input_total_asset - stockFeatureParameter.avg_total_asset) * 1.0
				/ stockFeatureParameter.std_total_asset;
		double liab_ratio = (input_liab_ratio - stockFeatureParameter.avg_liab_ratio) * 1.0
				/ stockFeatureParameter.std_liab_ratio;
		double opt_income = (input_opt_profit - stockFeatureParameter.avg_opt_income) * 1.0
				/ stockFeatureParameter.std_opt_income;
		double opt_profit_on_income = 0;
		if (input_opt_income != 0) {
			opt_profit_on_income = (input_opt_profit * 1.0 / input_opt_income
					- stockFeatureParameter.avg_opt_profit_on_income) * 1.0
					/ stockFeatureParameter.std_opt_profit_on_income;
		}

		double gross_profit_margin = (input_gross_profit_margin - stockFeatureParameter.avg_gross_profit_margin) * 1.0
				/ stockFeatureParameter.std_gross_profit_margin;

		MiddleReturnValue middleReturnValue = CalculateMatching(req, stocka_list, xsb_list, topic_industry,
				is_state_hold, total_asset, liab_ratio, opt_income, opt_profit_on_income, gross_profit_margin);
		int index = 0;

		List<EntMatch> xsb_match_list = new ArrayList<>();
		List<EntMatch> stocka_match_list = new ArrayList<>();

		List<String> stock_code_all = new ArrayList<>();
		for (MatchingCalculateResult matchingCalculateResult : middleReturnValue.compareResults_stocka) {
			if (index == 10) {
				break;
			}
			stock_code_all.add(matchingCalculateResult.stock_code);
			index++;
		}
		index = 0;
		for (MatchingCalculateResult matchingCalculateResult : middleReturnValue.compareResults_xsb) {
			if (index == 10) {
				break;
			}
			stock_code_all.add(matchingCalculateResult.stock_code);
			index++;
		}

		String condSql = CollectionUtil.toStringWithSingleComma(stock_code_all, true, ",");
		String sql = String.format("select stock_code, cn_name, stock_type, is_state_hold, total_asset, opt_income, opt_profit, opt_profit_on_income, liab_ratio, gross_profit_margin from stock_feature_all where stock_code in (%s)", condSql);
		ResultSet rs = null;
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (rs.next()) {
				EntMatch entMatch = new EntMatch();
				entMatch.stock_code = rs.getString("stock_code");
				entMatch.company_name = rs.getString("cn_name");
				entMatch.is_state_hold = rs.getInt("is_state_hold") == 1 ? "是" : "否"; // 是否国资背景（不精确）
				entMatch.total_asset = rs.getDouble("total_asset"); // 资产总计(元)
				entMatch.opt_income = rs.getDouble("opt_income"); // 营业收入(元)
				entMatch.opt_profit = rs.getDouble("opt_profit"); // 营业利润(元)
				entMatch.opt_profit_on_income = rs.getDouble("opt_profit_on_income"); // 营业利润／营业总收入(%)
				entMatch.liab_ratio = rs.getDouble("liab_ratio"); // 资产负债率(%)
				entMatch.gross_profit_margin = rs.getDouble("gross_profit_margin"); // 销售毛利率(%)
				int stock_type = rs.getInt("stock_type");
				if (stock_type == 0) { // 0-沪深A股，1-新三板
					stocka_match_list.add(entMatch);
				} else {
					xsb_match_list.add(entMatch);
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		
		xsbListMatchingResponse.setStatus(RDDWebConst.SUCCESS);
		xsbListMatchingResponse.setMessage("calculate ok");
		xsbListMatchingResponse.list_matching_list = stocka_match_list;
		xsbListMatchingResponse.xsb_matching_list = xsb_match_list;
	}
}
