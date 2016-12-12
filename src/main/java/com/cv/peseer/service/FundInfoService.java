package com.cv.peseer.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.PMExitEventDetailMapper;
import com.cv.peseer.dao.PMExitEventMapper;
import com.cv.peseer.dao.PMFundInfoMapper;
import com.cv.peseer.dao.PMInvestEventDetailMapper;
import com.cv.peseer.dao.PMInvestEventMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.PMExitEvent;
import com.cv.peseer.model.PMExitEventDetail;
import com.cv.peseer.model.PMFundInfo;
import com.cv.peseer.model.PMInvestEvent;
import com.cv.peseer.model.PMInvestEventDetail;
import com.cv.peseer.response.FundInfoResponse;
import com.cv.peseer.util.StringUtil;

@Service
public class FundInfoService {
	@Autowired
	PMFundInfoMapper fundInfoMapper;
	@Autowired
	PMExitEventDetailMapper exitEventDetailMapper;
	@Autowired
	PMExitEventMapper exitEventMapper;
	@Autowired
	PMInvestEventDetailMapper investEventDetailMapper;
	@Autowired
	PMInvestEventMapper investEventMapper;

	// 查询基金
	public void getIndexInfo(HttpServletRequest req, FundInfoResponse response) {
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
		List<PMFundInfo> fundList = fundInfoMapper.getIndexInfo(key, from);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get fund list info success!");
		response.setFundList(fundList);
	}

	// 查询基金详情
	public void getFundDetail(HttpServletRequest req, FundInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		int fundId = StringUtil.parseInt(req.getParameter("id"), RDDWebConst.INVALID_VALUE);
		if (fundId < 0) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {
			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			PMFundInfo fund = fundInfoMapper.selectByPrimaryKey(fundId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get fund success!");
			response.setFundInfo(fund);
		}
	}

	// 获取基金投资事件
	public void getInvestEvent(HttpServletRequest req, FundInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String fundId = req.getParameter("id");
		String filter = req.getParameter("filter");
		String key = req.getParameter("key");
		int from = StringUtil.parseInt(req.getParameter("from"), 0);

		if (StringUtil.isNullOrEmpty(fundId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<String> filters = null;
			if(!StringUtil.isNullOrEmpty(filter)){
				filters = Arrays.asList(filter.split(","));
			}
			List<PMInvestEvent> eventList = investEventMapper.getFundInvestEvent(fundId, filters, key, from);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setInventList(eventList);
		}
	}

	public void getInvestEventAll(HttpServletRequest req, FundInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String fundId = req.getParameter("id");

		if (StringUtil.isNullOrEmpty(fundId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMInvestEvent> eventList = investEventMapper.getFundInvestEventAll(fundId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setInventList(eventList);
		}
	}

	// 获取基金退出事件
	public void getExitEvent(HttpServletRequest req, FundInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String fundId = req.getParameter("id");
		String filter = req.getParameter("filter");
		String key = req.getParameter("key");
		int from = StringUtil.parseInt(req.getParameter("from"), 0);
		if (StringUtil.isNullOrEmpty(fundId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<String> filters = null;
			if(!StringUtil.isNullOrEmpty(filter)){
				filters = Arrays.asList(filter.split(","));
			}
			List<PMExitEvent> eventList = exitEventMapper.getFundExitEvent(fundId, filters, key, from);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setExitList(eventList);
		}
	}

	public void getExitEventAll(HttpServletRequest req, FundInfoResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String fundId = req.getParameter("id");

		if (StringUtil.isNullOrEmpty(fundId)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Please input a vaild id");
		} else {

			DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);
			List<PMExitEvent> eventList = exitEventMapper.getFundExitEventAll(fundId);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get invest event success!");
			response.setExitList(eventList);
		}
	}

	// 获取投资事件详情
	public void getInvestEventDetail(HttpServletRequest req, FundInfoResponse response) {
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
	public void getExitEventDetail(HttpServletRequest req, FundInfoResponse response) {
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
}
