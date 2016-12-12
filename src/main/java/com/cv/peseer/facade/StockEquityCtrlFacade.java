package com.cv.peseer.facade;

import com.alibaba.fastjson.JSONArray;
import com.cv.peseer.model.StockEquityCtrl;

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
