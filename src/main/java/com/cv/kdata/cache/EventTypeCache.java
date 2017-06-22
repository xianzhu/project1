package com.cv.kdata.cache;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.util.MysqlHelper;
import com.kdata.defined.model.EventTypeMap;


public class EventTypeCache {
	private static EventTypeCache instance = new EventTypeCache();
//	private Map<String, String> EventTypeMap = new HashMap<>();
	private Map<String, EventTypeMap> eventMap = new HashMap<>();

	public static EventTypeCache getInstance() {
		return instance;
	}

	private EventTypeCache(){
		ResultSet rs = null;
		String sql = "select a.id, a.round_name, b.id, b.round_name from "
				+ " event_round_two a left join event_round_one b "
				+ " on a.father_id=b.id ";

		try{
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (rs.next()){
				EventTypeMap tmpMap = new EventTypeMap();
				tmpMap.setChildId(rs.getInt("a.id"));
				tmpMap.setChildType(rs.getString("a.round_name"));
				tmpMap.setFatherId(rs.getInt("b.id"));
				tmpMap.setFatherType(rs.getString("b.round_name"));

				eventMap.put(tmpMap.getChildType(), tmpMap);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public String getRoundOneType(String second){
		EventTypeMap map = eventMap.get(second);
		return map.getFatherType();
	}

	public String isInvestOrExit(String second){
		EventTypeMap map = eventMap.get(second);
		if(map == null){
			return null;
		}

		if(map.getFatherId() == 1 || map.getFatherId() == 2){
			return "exit";
		}else{
			return "invest";
		}

	}
}
