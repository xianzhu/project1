package com.cv.kdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.model.UdfConsult;
import com.cv.kdata.model.UdfEvent;
import com.cv.kdata.model.UdfMonitor;
import com.cv.kdata.model.UdfQA;
import com.cv.kdata.model.UdfRptCV;
import com.cv.kdata.model.UdfRptTrader;
import com.cv.kdata.response.CiAssociatedResponse;
import com.cv.kdata.response.CiResponse;
import com.cv.kdata.response.UserInfoCustResponse;
import com.cv.kdata.response.XsbListMatchingResponse;
import com.cv.kdata.service.CiAssociatedService;
import com.cv.kdata.service.ToolsPlatformService;
import com.cv.kdata.service.UserInfoCustService;
import com.cv.kdata.service.XsbListMatchingService;
import com.cv.kdata.util.StringUtil;

@Controller
public class ToolsPlatformController {
	@Autowired
	private CiAssociatedService ciService;

	@Autowired
	private XsbListMatchingService matchService;

	@Autowired
	private UserInfoCustService userService;

	@Autowired
	ToolsPlatformService toolService;

	@RequestMapping(value = "/tools/ciassociated", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public CiAssociatedResponse ciAssociatedInfo(HttpServletRequest request, Model model) {

		CiAssociatedResponse basicResponse = new CiAssociatedResponse();

		String type = request.getParameter("type");
		if(StringUtil.isNullOrEmpty(type)){
			basicResponse.setStatus(com.cv.kdata.cont.RDDWebConst.FAILURE);
			basicResponse.setMessage("Please provide a valid type!");
		}else{
			ciService.getCiAssociatedInfo(type, basicResponse);
		}

//		model.addAttribute("data",basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/tools/page/ciassociated", method = { RequestMethod.GET, RequestMethod.POST })
	public String ciAssociatedPage(HttpServletRequest request,Model model) {
		CiResponse basicResponse = new CiResponse();

		String type = request.getParameter("type");
		if ("1".equals(type)) {
			ciService.searchCiDtourIncome(basicResponse);
		} else if ("2".equals(type)) {
			ciService.searchCiChnFilmBox(basicResponse);
		} else if ("3".equals(type)) {
			ciService.searchCiCorePayShare(basicResponse);
		} else if ("4".equals(type)) {
			ciService.searchCiCptocSca(basicResponse);
		} else if ("5".equals(type)) {
			ciService.searchCiOlVideoIncm(basicResponse);
		} else {
			ciService.searchCiDtourIncome(basicResponse);
			ciService.searchCiChnFilmBox(basicResponse);
			ciService.searchCiCorePayShare(basicResponse);
			ciService.searchCiCptocSca(basicResponse);
			ciService.searchCiOlVideoIncm(basicResponse);
		}

		model.addAttribute("data", basicResponse);
		return "industryTools";
	}

	@RequestMapping(value = "/tools/xsblistmatching", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public XsbListMatchingResponse companyMatch(HttpServletRequest request, Model model) {

		XsbListMatchingResponse basicResponse = new XsbListMatchingResponse();

		matchService.search(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/query", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse custInfoQuery(HttpServletRequest request, Model model) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		String object = request.getParameter("object");
		if(null == object){
			object = "0";
		}

		switch (object) {
		case "1":// 事件定制
			userService.getEventInfo(request, basicResponse);
			break;
		case "2":// 行情报告定制
			userService.getCVInfo(request, basicResponse);
			break;
		case "3":// 研究报告定制
			userService.getTraderInfo(request, basicResponse);
			break;
		case "4":// 咨询意见
			userService.getConsultInfo(request, basicResponse);
			break;
		case "5":// 监控信息
			userService.getMonitorInfo(request, basicResponse);
			break;
		default:
			userService.getEventInfo(request, basicResponse);
			userService.getCVInfo(request, basicResponse);
			userService.getTraderInfo(request, basicResponse);
			userService.getConsultInfo(request, basicResponse);
			userService.getMonitorInfo(request, basicResponse);
		}
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse custInfoUpdate(HttpServletRequest request, Model model) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		String object = request.getParameter("object");

		switch (object) {
		case "1":// 事件定制
			userService.updateEventInfo(request, basicResponse);
			break;
		case "2":// 行情报告定制
			userService.updateCVInfo(request, basicResponse);
			break;
		case "3":// 研究报告定制
			userService.updateTraderInfo(request, basicResponse);
			break;
		case "4":// 咨询意见
			userService.updateConsultInfo(request, basicResponse);
			break;
		case "5":// 监控信息
			userService.updateMonitorInfo(request, basicResponse);
			break;
		case "6":// 反馈意见
			userService.feedbackSubmit(request, basicResponse);
			break;
		}
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update/event", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse eventUpdate(HttpServletRequest request, UdfEvent event) {
		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.updateEvent(event, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/event", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse eventAdd(HttpServletRequest request, UdfEvent event) {
		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addEvent(event, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/del/event", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse eventDel(HttpServletRequest request, UdfEvent event) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.delEvent(event, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/qa", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse qaAdd(HttpServletRequest request, UdfQA record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addQA(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/consult", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse consultAdd(HttpServletRequest request, UdfConsult record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addConsult(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update/consult", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse consultUpdate(HttpServletRequest request, UdfConsult record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.updateConsult(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/del/consult", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse consultDel(HttpServletRequest request, UdfConsult record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.delConsult(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/monitor", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse monitorAdd(HttpServletRequest request, UdfMonitor record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addMonitor(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update/monitor", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse monitorUpdate(HttpServletRequest request, UdfMonitor record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.updateMonitor(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/del/monitor", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse monitorDel(HttpServletRequest request, UdfMonitor record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.delMonitor(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/cv", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse rptCVAdd(HttpServletRequest request, UdfRptCV record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addRptCV(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update/cv", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse rptCVUpdate(HttpServletRequest request, UdfRptCV record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.updateRptCV(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/del/cv", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse rptCVDel(HttpServletRequest request, UdfRptCV record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.delRptCV(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/add/trader", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse traderAdd(HttpServletRequest request, UdfRptTrader record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.addRptTrader(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/update/trader", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse traderUpdate(HttpServletRequest request, UdfRptTrader record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.updateRptTrader(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/custInfo/del/trader", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public UserInfoCustResponse traderDel(HttpServletRequest request, UdfRptTrader record) {

		UserInfoCustResponse basicResponse = new UserInfoCustResponse();

		userService.delRptTrader(record, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value = "/tools/transfer", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String transferId(HttpServletRequest request, Model model) {
		 return toolService.TransferIdAndStock(request);
	}
}
