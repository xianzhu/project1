package com.cv.peseer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.peseer.response.FundInfoResponse;
import com.cv.peseer.service.FundInfoService;

@Controller
public class FundInfoController {
	@Autowired
	private FundInfoService service;

	@RequestMapping(value = "/fund/search", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public FundInfoResponse search(HttpServletRequest request, Model model) {

		FundInfoResponse basicResponse = new FundInfoResponse();

		service.getIndexInfo(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value = "/fund/basic", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public FundInfoResponse basicInfo(HttpServletRequest request, Model model) {

		FundInfoResponse basicResponse = new FundInfoResponse();


		service.getFundDetail(request, basicResponse);
		service.getInvestEventAll(request, basicResponse);
		service.getExitEventAll(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value = "/fund/event", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public FundInfoResponse eventInfo(HttpServletRequest request, Model model) {
		FundInfoResponse basicResponse = new FundInfoResponse();

		String type = request.getParameter("type");
		if ("invest".equals(type)) {
			service.getInvestEvent(request, basicResponse);
		} else {
			service.getExitEvent(request, basicResponse);
		}

		return basicResponse;
	}

	@RequestMapping(value = "/fund/detail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public FundInfoResponse detailInfo(HttpServletRequest request, Model model) {
		FundInfoResponse basicResponse = new FundInfoResponse();

		String type = request.getParameter("type");
		if ("invest".equals(type)) {
			service.getInvestEventDetail(request, basicResponse);
		} else {
			service.getExitEventDetail(request, basicResponse);
		}

		return basicResponse;
	}
}
