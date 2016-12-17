package com.cv.kdata.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class StockACapitalDebtIndexResponse{
	@JSONField(ordinal = 1)
	public String stock_code;
	@JSONField(ordinal = 2)
	public String title;
	@JSONField(ordinal = 3)
	public List<String> xdata;
	
/*	@JSONField(ordinal = 4)
	public List<Object> finace_value;*/
	
	@JSONField(ordinal = 5)
	public Hashtable<String,List<Object>> ydata;
	
	
	@SuppressWarnings("serial")
	public static Hashtable<String,String> mapping = new Hashtable<String,String>(){{
		put("总资产周转率(次)", "turnover");
		put("资产负债率(%)", "debtRatio");
		put("营业利润／营业总收入(%)", "totalRevenue");
		put("营业周期(天)", "operatingCycle");
		put("净资产收益率-年化(%)", "rOEAnnualized");
		put("营业利润同比增长(%)", "opt_profit");
		put("销售净利率(%)", "netProfitMargin");
		put("投入资本回报率(%)", "responseRate");
		put("销售毛利率(%)", "grossMargin");
		put("总资产报酬率(%)", "rateReturn");
	}};

	public StockACapitalDebtIndexResponse(){
		xdata = new ArrayList<>();
		ydata = new Hashtable<>();
		
		//finace_value = new ArrayList<>();
	}
	
	public String getStock_code() {
		return stock_code;
	}


	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<String> getXdata() {
		return xdata;
	}


	public void setXdata(List<String> xdata) {
		this.xdata = xdata;
	}


	public Hashtable<String, List<Object>> getYdata() {
		return ydata;
	}


	public void setYdata(Hashtable<String, List<Object>> ydata) {
		this.ydata = ydata;
	}
}