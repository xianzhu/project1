package com.cv.peseer.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.EntBasicInfoMapper;
import com.cv.peseer.dao.UdfMonitorMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.EntBasicInfo;
import com.cv.peseer.model.PMOrgInfo;
import com.cv.peseer.model.UdfMonitor;
import com.cv.peseer.response.MonitorResponse;
import com.cv.peseer.util.LoginInfoCache;
import com.cv.peseer.util.StringUtil;

@Service
public class MonitorService {
	@Autowired
	UdfMonitorMapper udfMonitorMapper;
	@Autowired
	EntBasicInfoMapper entMapper;
	/**
	 * 企业监控
	 */
	public void getMonitorEnt(HttpServletRequest req, MonitorResponse response){
		String uid = LoginInfoCache.getInstance().getUid((String) req.getSession().getAttribute(RDDWebConst.TOKEN));
		List<EntBasicInfo> entInfoList = new ArrayList<>();
		if(StringUtil.isNullOrEmpty(uid)){
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfMonitor> itemList = udfMonitorMapper.selectByUid(uid);
		if(itemList != null){

			for(int i=0; i<itemList.size(); i++){
				UdfMonitor tmp = itemList.get(i);
				if(1 == tmp.getType()){
					EntBasicInfo entInfo = new EntBasicInfo();
					entInfo.setEntId(tmp.getUuid());
					entInfo.setEntName(tmp.getContent());
					entInfoList.add(entInfo);
				}
			}
		}

		response.setObject(entInfoList);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("success");
	}

	/**
	 * 机构监控
	 */
	public void getMonitorOrg(HttpServletRequest req, MonitorResponse response){
		String uid = LoginInfoCache.getInstance().getUid((String) req.getSession().getAttribute(RDDWebConst.TOKEN));
		List<PMOrgInfo> orgInfoList = new ArrayList<>();
		if(StringUtil.isNullOrEmpty(uid)){
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfMonitor> itemList = udfMonitorMapper.selectByUid(uid);
		if(itemList != null){

			for(int i=0; i<itemList.size(); i++){
				UdfMonitor tmp = itemList.get(i);
				if(2 == tmp.getType()){
					PMOrgInfo orgInfo = new PMOrgInfo();
					orgInfo.setOrgCnShort(tmp.getContent());
					orgInfo.setOrgId(tmp.getOrgId());
					orgInfoList.add(orgInfo);
				}
			}
		}

		response.setObject(orgInfoList);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("success");
	}

}
