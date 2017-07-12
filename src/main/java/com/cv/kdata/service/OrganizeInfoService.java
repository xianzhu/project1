package com.cv.kdata.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.CategoryTableMapper;
import com.cv.kdata.dao.LoginInfoMapper;
import com.cv.kdata.dao.PMExitEventDetailMapper;
import com.cv.kdata.dao.PMExitEventMapper;
import com.cv.kdata.dao.PMFundInfoMapper;
import com.cv.kdata.dao.PMInvestEventDetailMapper;
import com.cv.kdata.dao.PMInvestEventMapper;
import com.cv.kdata.dao.PMOrgExtendEntityMapper;
import com.cv.kdata.dao.PMOrgInfoMapper;
import com.cv.kdata.dao.PMStartupInfoMapper;
import com.cv.kdata.dao.PMUserInfoMapper;
import com.cv.kdata.dao.RptOrgForcusEntMapper;
import com.cv.kdata.dao.RptOrgOverallTrendsMapper;
import com.cv.kdata.dao.RptPeFundMapper;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.facade.PMOrgInfoFacade;
import com.cv.kdata.model.PMExitEvent;
import com.cv.kdata.model.PMExitEventDetail;
import com.cv.kdata.model.PMFundInfo;
import com.cv.kdata.model.PMInvestEvent;
import com.cv.kdata.model.PMInvestEventDetail;
import com.cv.kdata.model.PMOrgExtendEntity;
import com.cv.kdata.model.PMOrgInfo;
import com.cv.kdata.model.PMStartupInfo;
import com.cv.kdata.model.PMUserInfo;
import com.cv.kdata.model.RptOrgForcusEnt;
import com.cv.kdata.model.RptOrgOverallTrends;
import com.cv.kdata.model.RptPeFund;
import com.cv.kdata.model.UserInfoWithBLOBs;
import com.cv.kdata.response.OrganizeResponse;
import com.cv.kdata.util.StringUtil;

@Service
public class OrganizeInfoService {
	// 获取基本信息
	@Autowired
	PMExitEventDetailMapper exitEventDetailMapper;
	@Autowired
	PMExitEventMapper exitEventMapper;
	@Autowired
	PMInvestEventDetailMapper investEventDetailMapper;
	@Autowired
	PMInvestEventMapper investEventMapper;
	@Autowired
	PMOrgExtendEntityMapper orgExtendEntityMapper;
	@Autowired
	RptPeFundMapper rptPeFundMapper;
	@Autowired
	RptOrgOverallTrendsMapper rptOrgOverallTrendsMapper;
	@Autowired
	RptOrgForcusEntMapper rptOrgForcusEntMapper;
	@Autowired
	PMStartupInfoMapper startupInfoMapper;
	@Autowired
	PMFundInfoMapper fundInfoMapper;
	@Autowired
	PMUserInfoMapper userInfoMapper;
	@Autowired
	PMOrgInfoMapper orgInfoMapper;
	@Autowired
	LoginInfoMapper loginInfoMapper;
	@Autowired
	CategoryTableMapper categoryMapper;
	@Autowired
	UserInfoMapper usrInfoMapper;

	public void getBasicOrgInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
//			PMOrgInfoFacade organizeInfo = OrgInfoCache.getInstance().getOrgInfo(Integer.valueOf(orgId).intValue());
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			PMOrgInfo organizeInfo = orgInfoMapper.selectByPrimaryKey(StringUtil.parseInt(orgId, 0));
			PMOrgInfoFacade facade = new PMOrgInfoFacade(organizeInfo);
			response.setOrgInfo(facade);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent organize basic info success!");
		}
	}

	// 获取机构用户信息
	public void getOrgUserInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMUserInfo> teams = userInfoMapper.getOrgUserInfo(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent organize basic info success!");
			response.setTeams(teams);
		}
	}

	// 查询
	public void getIndexInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String key = req.getParameter("key");
		int from = 0;
		if (!StringUtil.isNullOrEmpty(req.getParameter("from"))) {
			from = Integer.valueOf(req.getParameter("from")).intValue();
		}
		from = from > 0 ? from : 0;

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<PMOrgInfo> orgList = orgInfoMapper.getIndexInfo(key, from);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get ent organize basic info success!");
		response.setOrgList(orgList);
	}

	// 获取机构投资事件
	public void getOrgInvestEvent(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMInvestEvent> eventList = investEventMapper.getOrgInvestEvent(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setInventList(eventList);
		}
	}

	// 获取机构退出事件
	public void getOrgExitEvent(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMExitEvent> eventList = exitEventMapper.getOrgExitEvent(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setExitList(eventList);
		}
	}

	// 获取投资事件详情
	public void getInvestEventDetail(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		// String eventId = req.getParameter("id");
		int eventId = StringUtil.parseInt(req.getParameter("id"), RDDWebConst.INVALID_VALUE);
		if (eventId < 0) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			PMInvestEventDetail event = investEventDetailMapper.selectByPrimaryKey(eventId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setInventEvent(event);
		}
	}

	// 获取退出事件详情
	public void getExitEventDetail(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		int eventId = StringUtil.parseInt(req.getParameter("id"), RDDWebConst.INVALID_VALUE);
		if (eventId < 0) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			PMExitEventDetail event = exitEventDetailMapper.selectByPrimaryKey(eventId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get exit event success!");
			response.setExitEvent(event);
		}
	}

	// 获取基金信息
	public void getFundInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMFundInfo> fundList = fundInfoMapper.getFundInfo(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get fund list success!");
			response.setFundList(fundList);
		}
	}

	// 获取最新收录项目信息
	public void getProjectInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		List<String> domain = null;
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);

		if(!StringUtil.isNullOrEmpty(token)){
			DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
			UserInfoWithBLOBs userInfo = null;
				String uid = LoginInfoCache.getInstance().getUid(token);
				if(!StringUtil.isNullOrEmpty(uid)){
					userInfo = usrInfoMapper.selectByPrimaryKey(uid);
				}

			if(null != userInfo){
				String customInfo = userInfo.getDomainTips();
				if(!StringUtil.isNullOrEmpty(customInfo)){

					String[] custom = customInfo.split(",");
					domain = Arrays.asList(custom);
				}
			}
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<PMStartupInfo> projectList = startupInfoMapper.getProjectInfo(domain);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get project list success!");
		response.setProjectList(projectList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getOrgFocusInfo(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<RptOrgForcusEnt> focusList = rptOrgForcusEntMapper.getOrgFocusInfo(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent focus info success!");
			response.setFocusList(focusList);
		}
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getOverallTrends(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<RptOrgOverallTrends> trendList = rptOrgOverallTrendsMapper.getOverallTrends();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get OverallTrends list success!");
		response.setTendsList(trendList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getRptPeList(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<RptPeFund> trendList = rptPeFundMapper.getRptPeList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get rpt_pe_fund list success!");
		response.setRptPeFundList(trendList);
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getOrgExtends(HttpServletRequest req, OrganizeResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		String orgId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(orgId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMOrgExtendEntity> extendList = orgExtendEntityMapper.getOrgExtends(orgId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get extends list success!");
			response.setExtendList(extendList);
		}

	}

}
