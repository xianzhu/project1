package com.cv.kdata.facade;

import com.alibaba.fastjson.JSONArray;
import com.cv.kdata.model.StockEquityCtrl;

public class StockEquityCtrlFacade extends StockEquityCtrl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JSONArray getEquityCtrl() {
		return equityCtrl;
	}
	public void setEquityCtrl(JSONArray equityCtrl) {
		this.equityCtrl = equityCtrl;
	}
	JSONArray equityCtrl;
}
