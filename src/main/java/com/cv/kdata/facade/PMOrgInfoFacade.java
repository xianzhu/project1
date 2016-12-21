package com.cv.kdata.facade;

import java.sql.ResultSet;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.PMOrgInfo;
import com.cv.kdata.util.MysqlHelper;
import com.cv.kdata.util.StringUtil;

public class PMOrgInfoFacade extends PMOrgInfo {
	public PMOrgInfoFacade() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String orgMapEntityId; // 对应实体id

	public PMOrgInfoFacade(PMOrgInfo orgInfo){
		if(orgInfo != null){
			this.setOrgId(orgInfo.getOrgId());
			this.setFundInfo(orgInfo.getFundInfo());
			this.setInvestInfo(orgInfo.getInvestInfo());
			this.setOrgCnDesc(orgInfo.getOrgCnDesc());
			this.setOrgCnName(orgInfo.getOrgCnName());
			this.setOrgCnShort(orgInfo.getOrgCnShort());
			this.setOrgMapEntity(orgInfo.getOrgMapEntity());
			this.setOrgNickname(orgInfo.getOrgNickname());
		}
	}

	public String getOrgMapEntityId() {
		if (!StringUtil.isNullOrEmpty(this.getOrgMapEntity()) && StringUtil.isNullOrEmpty(orgMapEntityId)) {
			ResultSet rs = null;
			String sql = "select ent_id from ent_basic_info where ent_name=?";
			Object[] parameters = { this.getOrgMapEntity() };
			try {
				rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, parameters);
				if (rs.next()) {
					orgMapEntityId = rs.getString("ent_id");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
			}
		}
		return orgMapEntityId;
	}

	public void setOrgMapEntityId(String orgMapEntityId) {
		this.orgMapEntityId = orgMapEntityId;
	}

}
