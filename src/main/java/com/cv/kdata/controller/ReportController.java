package com.cv.kdata.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.response.ReportResponse;
import com.cv.kdata.service.ReportService;
import com.cv.kdata.util.StringUtil;

@Controller
public class ReportController {
	@Autowired
	private ReportService service;

	@RequestMapping(value="/report/personal",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public ReportResponse personalSearch(HttpServletRequest request,Model model){
		String token = (String) request.getSession().getAttribute(RDDWebConst.TOKEN);
		ReportResponse searchResponse = new ReportResponse();

		String key = request.getParameter("key");
		String type = request.getParameter("type");

		int from = StringUtil.parseInt(request.getParameter("from"), 0);
		int count = StringUtil.parseInt(request.getParameter("count"), 10);
		from = from < 0 ? 0 : from;
		count = count < 1 ? 10 : count;

		if ("trader".equals(type)) {
			// type为trader，返回研究报告
			service.getTraderListByPersonal(key, token, from, count, searchResponse);
		} else if ("cv".equals(type)) {
			service.getCVListByPersonal(key, token, from, count, searchResponse);
		} else {
			service.getTraderListByPersonal(key, token, from, count, searchResponse);
			service.getCVListByPersonal(key, token, from, count, searchResponse);
		}

		return searchResponse;
	}

	@RequestMapping(value="/report",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public ReportResponse search(HttpServletRequest request,Model model){

		ReportResponse searchResponse = new ReportResponse();

		String key = request.getParameter("key");
		String type = request.getParameter("type");
		String filter = request.getParameter("filter");

		int from = StringUtil.parseInt(request.getParameter("from"), 0);
		int count = StringUtil.parseInt(request.getParameter("count"), 10);
		from = from < 0 ? 0 : from;
		count = count < 1 ? 10 : count;

		if ("trader".equals(type)) {
			// type为trader，返回研究报告
			service.getTraderList(key, from, count, filter, searchResponse);
		} else if ("cv".equals(type)) {
			service.getCVList(key, from, count,filter, searchResponse);
		} else {
			service.getTraderList(key, from, count, filter, searchResponse);
			service.getCVList(key, from, count,filter, searchResponse);
		}

		return searchResponse;
	}

	@RequestMapping(value="/trader_rpt",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public ReportResponse getTraderRpt(HttpServletRequest request,HttpServletResponse resp, Model model){

		ReportResponse searchResponse = new ReportResponse();
		service.getRemoteTraderReport(request,resp,searchResponse);
		return searchResponse;
	}

	@RequestMapping(value="/cv_rpt",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public ReportResponse getCvRpt(HttpServletRequest request,HttpServletResponse resp, Model model){

		ReportResponse searchResponse = new ReportResponse();
		service.getRemoteCVReport(request,resp,searchResponse);
		return searchResponse;
	}
}
