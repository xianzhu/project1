package com.cv.peseer.facade;

import com.alibaba.fastjson.JSONArray;
import com.cv.peseer.model.StockInstInvest;

public class StockInstInvestFacade extends StockInstInvest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JSONArray instInvestFacade;
	public JSONArray getInstInvestFacade() {
		return instInvestFacade;
	}
	public void setInstInvestFacade(JSONArray instInvestFacade) {
		this.instInvestFacade = instInvestFacade;
	}
}
