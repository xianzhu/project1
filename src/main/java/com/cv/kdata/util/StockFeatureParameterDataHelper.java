package com.cv.kdata.util;

import java.sql.ResultSet;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.StockFeatureParameter;



public class StockFeatureParameterDataHelper {
	private static Logger logger = LoggerFactory.getLogger(StockFeatureParameterDataHelper.class);
	private HashMap<String, StockFeatureParameter> cache = new HashMap<>();
	
	
	private static StockFeatureParameterDataHelper instance = new StockFeatureParameterDataHelper();

	public static StockFeatureParameterDataHelper getInstance() {
		return instance;
	}
	
	public  StockFeatureParameterDataHelper(){
		String sql = "select topic_industry,stock_type,avg_total_asset,avg_opt_profit_on_income, std_opt_profit_on_income,std_total_asset,avg_opt_income,std_opt_income,avg_opt_profit,std_opt_profit,avg_liab_ratio,std_liab_ratio,avg_gross_profit_margin,std_gross_profit_margin,avg_pe,std_pe from stock_feature_parameter";
		ResultSet rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
		try {
			while (rs.next()) {
				StockFeatureParameter stockFeatureParameter = new StockFeatureParameter();
				stockFeatureParameter.topic_industry = rs.getString("topic_industry");
				stockFeatureParameter.stock_type = rs.getString("stock_type");
				stockFeatureParameter.avg_total_asset = rs.getDouble("avg_total_asset");
				stockFeatureParameter.std_total_asset = rs.getDouble("std_total_asset");
				stockFeatureParameter.avg_opt_income = rs.getDouble("avg_opt_income");
				stockFeatureParameter.std_opt_income = rs.getDouble("std_opt_income");
				stockFeatureParameter.avg_opt_profit_on_income = rs.getDouble("avg_opt_profit_on_income");
				stockFeatureParameter.std_opt_profit_on_income = rs.getDouble("std_opt_profit_on_income");
				stockFeatureParameter.avg_opt_profit = rs.getDouble("avg_opt_profit");
				stockFeatureParameter.std_opt_profit = rs.getDouble("std_opt_profit");
				stockFeatureParameter.avg_liab_ratio = rs.getDouble("avg_liab_ratio");
				stockFeatureParameter.std_liab_ratio = rs.getDouble("std_liab_ratio");
				stockFeatureParameter.avg_gross_profit_margin = rs.getDouble("avg_gross_profit_margin");
				stockFeatureParameter.std_gross_profit_margin = rs.getDouble("std_gross_profit_margin");
				stockFeatureParameter.avg_pe = rs.getDouble("avg_pe");
				stockFeatureParameter.std_pe = rs.getDouble("std_pe");

				cache.put(stockFeatureParameter.topic_industry, stockFeatureParameter);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(sql, ex);
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}
	
	public StockFeatureParameter getStockFeatureParameter(String topic_industry){
		return cache.get(topic_industry);
	}
	
	public static void main(String[] args) {

	}

}
