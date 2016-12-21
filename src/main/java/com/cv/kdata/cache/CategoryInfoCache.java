package com.cv.kdata.cache;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.CategoryTable;
import com.cv.kdata.util.BeanConverter;
import com.cv.kdata.util.MysqlHelper;


public class CategoryInfoCache {
	private static CategoryInfoCache instance = new CategoryInfoCache();
	private Map<String, String> domainMap = new HashMap<>();
	private Map<String, String> bizMap = new HashMap<>();
	private Map<String, String> mediaMap = new HashMap<>();
	private Map<String, CategoryTable> categoryMap = new HashMap<>();

	public static CategoryInfoCache getInstance() {
		return instance;
	}

	private CategoryInfoCache(){
		ResultSet rs = null;
		String sql = "select * from id_category";
		try{
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (rs.next()){
//				loginMap.put(rs.getString("cookie"), rs.getString("uid"));
				CategoryTable objectItem = (CategoryTable) BeanConverter.convert(rs, CategoryTable.class);
				if(objectItem != null){
					if(objectItem.getDomain() != null)
					{
						domainMap.put(objectItem.getDomain(), objectItem.getBizId());
					}
					if(objectItem.getBiz() != null)
					{
						bizMap.put(objectItem.getBiz(), objectItem.getBizId());
					}
					if(objectItem.getMedia() != null)
					{
						mediaMap.put(objectItem.getMedia(), objectItem.getBizId());
					}

					categoryMap.put(objectItem.getBizId(), objectItem);

				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public String getIdFromDomain(String domain){
		return domainMap.get(domain);
	}

	public String getIdFromBiz(String Biz){
		return bizMap.get(Biz);
	}

	public String getIdFromMedia(String meida){
		return mediaMap.get(meida);
	}

	public CategoryTable getCategoryObject(String id){
		return categoryMap.get(id);
	}
}
