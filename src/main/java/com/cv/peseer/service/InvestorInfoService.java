package com.cv.peseer.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.PMExitEventDetailMapper;
import com.cv.peseer.dao.PMExitEventMapper;
import com.cv.peseer.dao.PMInvestEventDetailMapper;
import com.cv.peseer.dao.PMInvestEventMapper;
import com.cv.peseer.dao.PMUserInfoMapper;
import com.cv.peseer.dao.RptAngelListMapper;
import com.cv.peseer.dao.RptOrgerListMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.PMExitEvent;
import com.cv.peseer.model.PMExitEventDetail;
import com.cv.peseer.model.PMInvestEvent;
import com.cv.peseer.model.PMInvestEventDetail;
import com.cv.peseer.model.PMUserInfo;
import com.cv.peseer.model.Redis2Module;
import com.cv.peseer.model.RptAngelList;
import com.cv.peseer.model.RptOrgerList;
import com.cv.peseer.response.InvestorInfoResponse;
import com.cv.peseer.util.StringUtil;

@Service
public class InvestorInfoService {
	@Autowired
	RptAngelListMapper rptAngelListMapper;
	@Autowired
	RptOrgerListMapper rptOrgerListMapper;

	@Autowired
	PMExitEventDetailMapper exitEventDetailMapper;
	@Autowired
	PMExitEventMapper exitEventMapper;
	@Autowired
	PMInvestEventDetailMapper investEventDetailMapper;
	@Autowired
	PMInvestEventMapper investEventMapper;
	@Autowired
	PMUserInfoMapper userInfoMapper;

	// 查询投资人
	public void getIndexInfo(HttpServletRequest req, InvestorInfoResponse response) {
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
		List<PMUserInfo> userList = userInfoMapper.getIndexInfo(key, from);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get invest list info success!");
		response.setUserList(userList);
	}

	// 查询投资人详情
	public void getInvestorDetail(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String userId = req.getParameter("id");
		if (StringUtil.isNullOrEmpty(userId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			PMUserInfo investor = userInfoMapper.selectUserInfoById(userId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get investor success!");
			response.setInvestor(investor);
		}
	}

	// 获取个人投资事件
	public void getExitEvent(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String userId = req.getParameter("id");

		String filter = req.getParameter("filter");
		String key = req.getParameter("key");
		int from = StringUtil.parseInt(req.getParameter("from"), 0);
		if (StringUtil.isNullOrEmpty(userId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<String> filters = null;
			if(!StringUtil.isNullOrEmpty(filter)){
				filters = Arrays.asList(filter.split(","));
			}
			List<PMExitEvent> eventList = exitEventMapper.getInvestorExitEvent(userId, filters, key, from);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setExitList(eventList);
		}
	}

	// 获取个人退出事件
	public void getInvestEvent(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String userId = req.getParameter("id");
		String filter = req.getParameter("filter");
		String key = req.getParameter("key");
		int from = StringUtil.parseInt(req.getParameter("from"), 0);

		if (StringUtil.isNullOrEmpty(userId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<String> filters = null;
			if(!StringUtil.isNullOrEmpty(filter)){
				filters = Arrays.asList(filter.split(","));
			}
			List<PMInvestEvent> eventList = investEventMapper.getInvestorInvestEvent(userId, filters,key, from);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setInventList(eventList);
		}
	}

	// 获取投资事件详情
	public void getInvestEventDetail(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
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
	public void getExitEventDetail(HttpServletRequest req, InvestorInfoResponse response) {
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

	/**
	 * 天使投资人榜单
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getAngelList(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		List<RptAngelList> list = Redis2Module.getRptAngelList();
		if (list != null && !list.isEmpty()) {
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setAngelList(list);
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<RptAngelList> angelList = rptAngelListMapper.getAngelList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get investor success!");
		response.setAngelList(angelList);
	}

	/**
	 * 机构投资人榜单
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getOrgList(HttpServletRequest req, InvestorInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		List<RptOrgerList> list = Redis2Module.getRptOrgList();
		if (list != null && !list.isEmpty()) {
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("success!");
			response.setOrglList(list);
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
		List<RptOrgerList> orgList = rptOrgerListMapper.getOrgList();
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get investor success!");
		response.setOrglList(orgList);
	}
}
