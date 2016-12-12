package com.cv.peseer.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.model.StockFeatureAllScaled;


public class StockFeatureAllScaledDataHelper {
	private static Logger logger = LoggerFactory.getLogger(StockFeatureAllScaledDataHelper.class);
	private final Map<String, List<StockFeatureAllScaled>> xsb = new HashMap<>();
	private final Map<String, List<StockFeatureAllScaled>> stocka = new HashMap<>();

	private StockFeatureAllScaledDataHelper() {
		ResultSet rSet = null;
		try {
			rSet = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE)
					.getResultSet("select * from stock_feature_all_scaled");
			while (rSet.next()) {
				StockFeatureAllScaled duiBiaoParam = new StockFeatureAllScaled();
				String stock_type = rSet.getString("stock_type");
				duiBiaoParam.topic_industry = rSet.getString("topic_industry");
				duiBiaoParam.is_dev = rSet.getDouble("is_dev");
				duiBiaoParam.chairman_cum_ceo = rSet.getDouble("chairman_cum_ceo");
				duiBiaoParam.is_state_hold = rSet.getDouble("is_state_hold");
				duiBiaoParam.opt_profit_growth = rSet.getDouble("opt_profit_growth");
				duiBiaoParam.opt_profit = rSet.getDouble("opt_profit");
				duiBiaoParam.total_asset = rSet.getDouble("total_asset");
				duiBiaoParam.liab_ratio = rSet.getDouble("liab_ratio");
				duiBiaoParam.opt_income = rSet.getDouble("opt_income");
				duiBiaoParam.opt_profit_on_income = rSet.getDouble("opt_profit_on_income");
				duiBiaoParam.opt_income_growth = rSet.getDouble("opt_income_growth");
				duiBiaoParam.share_conc = rSet.getDouble("share_conc");
				duiBiaoParam.share_balance = rSet.getDouble("share_balance");
				duiBiaoParam.person_hold = rSet.getDouble("person_hold");
				duiBiaoParam.ceo_education = rSet.getDouble("ceo_education");
				duiBiaoParam.ceo_age = rSet.getDouble("ceo_age");
				duiBiaoParam.net_profit_on_asset = rSet.getDouble("net_profit_on_asset");
				duiBiaoParam.total_asset_growth = rSet.getDouble("total_asset_growth");
				duiBiaoParam.holder_equity_on_liab = rSet.getDouble("holder_equity_on_liab");
				duiBiaoParam.asset_turnover = rSet.getDouble("asset_turnover");
				duiBiaoParam.net_profit_on_income = rSet.getDouble("net_profit_on_income");
				duiBiaoParam.net_profit_margin = rSet.getDouble("net_profit_margin");
				duiBiaoParam.ma_fee_on_income = rSet.getDouble("ma_fee_on_income");
				duiBiaoParam.trade_cycle = rSet.getDouble("trade_cycle");
				duiBiaoParam.days_in_inventory = rSet.getDouble("days_in_inventory");
				duiBiaoParam.interest_liab_on_capital = rSet.getDouble("interest_liab_on_capital");
				duiBiaoParam.current_asset_ratio = rSet.getDouble("current_asset_ratio");
				duiBiaoParam.ROA = rSet.getDouble("ROA");
				duiBiaoParam.tangible_asset_on_liab = rSet.getDouble("tangible_asset_on_liab");
				duiBiaoParam.current_liab_ratio = rSet.getDouble("current_liab_ratio");
				duiBiaoParam.gross_profit_margin = rSet.getDouble("gross_profit_margin");
				duiBiaoParam.opt_cost_on_income = rSet.getDouble("opt_cost_on_income");
				duiBiaoParam.ROIC = rSet.getDouble("ROIC");
				duiBiaoParam.ROE = rSet.getDouble("ROE");
				duiBiaoParam.stock_code = rSet.getString("stock_code");

				if (stock_type.equals("0")) {//A股
					List<StockFeatureAllScaled> stockFeatureAllScaledList = stocka.get(duiBiaoParam.topic_industry);

					if (stockFeatureAllScaledList == null) {
						stockFeatureAllScaledList = new ArrayList<>();
						stocka.put(duiBiaoParam.topic_industry, stockFeatureAllScaledList);
					}
					stockFeatureAllScaledList.add(duiBiaoParam);

				} else {//新三板
					List<StockFeatureAllScaled> stockFeatureAllScaledList = xsb.get(duiBiaoParam.topic_industry);

					if (stockFeatureAllScaledList == null) {
						stockFeatureAllScaledList = new ArrayList<>();
						xsb.put(duiBiaoParam.topic_industry, stockFeatureAllScaledList);
					}
					stockFeatureAllScaledList.add(duiBiaoParam);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rSet);
		}
	}

	private static StockFeatureAllScaledDataHelper instance = new StockFeatureAllScaledDataHelper();

	public static StockFeatureAllScaledDataHelper getInstance() {
		return instance;
	}

	public List<StockFeatureAllScaled> getStockaFeatureAllScaled(String topic_industry){
		return stocka.get(topic_industry);
	}
	
	public List<StockFeatureAllScaled> getXsbFeatureAllScaled(String topic_industry){
		return xsb.get(topic_industry);
	}

	public static void main(String[] args) {
	}

}
