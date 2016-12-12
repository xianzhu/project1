package com.cv.peseer.util;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cv.peseer.cont.RDDWebConst;


public class StockMapId {
	private static StockMapId instance = new StockMapId();
	private Map<String, String> IdStockMap = new HashMap<>();
	
	public static StockMapId getInstance() {
		return instance;
	}
	
	private StockMapId(){
		ResultSet rs = null;
		String sql = "select uuid, stock_code from id_ent_stock";
		try{
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (rs.next()){
				IdStockMap.put(rs.getString("uuid"), rs.getString("stock_code"));
				IdStockMap.put(rs.getString("stock_code"),rs.getString("uuid"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}
	
	public String getStockOrUUID(String uuidOrStock){
		return IdStockMap.get(uuidOrStock);
	}
}
