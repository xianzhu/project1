package com.cv.peseer.cache;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.facade.PMOrgInfoFacade;
import com.cv.peseer.util.BeanConverter;
import com.cv.peseer.util.MysqlHelper;

public class OrgInfoCache {
	private static OrgInfoCache instance = new OrgInfoCache();
	private Map<Integer, PMOrgInfoFacade> organizeInfoMap = new HashMap<>();

	public static OrgInfoCache getInstance() {
		return instance;
	}

	private OrgInfoCache(){
		ResultSet rs = null;
		String sql = "select  org_id,  org_cn_name,  org_cn_desc, org_map_entity from pm_org_info";
		try{
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
			while (rs.next()){
				PMOrgInfoFacade organizeInfo = (PMOrgInfoFacade) BeanConverter.convert(rs, PMOrgInfoFacade.class);
				organizeInfoMap.put(organizeInfo.getOrgId(), organizeInfo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public PMOrgInfoFacade getOrgInfo(int org_id){
		return organizeInfoMap.get(org_id);
	}
}
