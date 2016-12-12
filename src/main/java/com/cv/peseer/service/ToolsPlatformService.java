package com.cv.peseer.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cv.peseer.util.StockMapId;
@Service
public class ToolsPlatformService {
	
	public String TransferIdAndStock(HttpServletRequest req){
		return StockMapId.getInstance().getStockOrUUID(req.getParameter("id"));	
	}

}
