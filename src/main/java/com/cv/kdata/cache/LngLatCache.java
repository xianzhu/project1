package com.cv.kdata.cache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.LngLat;
import com.cv.kdata.util.MysqlHelper;

public class LngLatCache {
	private static Logger logger = LoggerFactory.getLogger(LngLatCache.class);
	private static HashMap<String, LngLat> cache = new HashMap<>();
	private static ArrayList<LngLat> lngLatList = new ArrayList<>();
	
	private LngLatCache() {
		String sql = "select * from ops_city_lnglat_info";
		ResultSet resultSet = null;
		try {
			resultSet = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				Double lng = resultSet.getDouble("lng");
				Double lat = resultSet.getDouble("lat");
				LngLat lngLat = new LngLat(lng, lat);
				cache.put(id, lngLat);
				lngLatList.add(lngLat);
			}
			//resultSet.close();
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}finally{
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(resultSet);
		}
	}

	private static LngLatCache instance = new LngLatCache();

	public static LngLatCache getInstance() {
		return instance;
	}
	
	public LngLat getRandomLngLat(){
		Integer  rnd = new Random().nextInt(lngLatList.size())%lngLatList.size();
		return lngLatList.get(rnd);
	}
}
