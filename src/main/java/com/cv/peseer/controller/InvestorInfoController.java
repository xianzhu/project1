package com.cv.peseer.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.peseer.response.InvestorInfoResponse;
import com.cv.peseer.service.InvestorInfoService;

@Controller
public class InvestorInfoController {
	@Autowired
	private InvestorInfoService service;

	@RequestMapping(value="/investor/search",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public InvestorInfoResponse search(HttpServletRequest request,Model model){

		InvestorInfoResponse basicResponse = new InvestorInfoResponse();

		service.getIndexInfo(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/investor/top",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public InvestorInfoResponse topInfo(HttpServletRequest request,Model model){

		InvestorInfoResponse basicResponse = new InvestorInfoResponse();

		service.getAngelList(request, basicResponse);
		service.getOrgList(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/investor/basic",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public InvestorInfoResponse basicInfo(HttpServletRequest request,Model model){

		InvestorInfoResponse basicResponse = new InvestorInfoResponse();

		service.getInvestorDetail(request, basicResponse);
		service.getInvestEvent(request, basicResponse);
		service.getExitEvent(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value="/investor/detail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public InvestorInfoResponse detailInfo(HttpServletRequest request,Model model){

		String type = request.getParameter("type");
		InvestorInfoResponse basicResponse = new InvestorInfoResponse();

		if ("invest".equals(type)) {
			service.getInvestEventDetail(request, basicResponse);
		} else {
			service.getExitEventDetail(request, basicResponse);
		}
		return basicResponse;
	}

	@RequestMapping(value="/investor/event",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public InvestorInfoResponse eventInfo(HttpServletRequest request,Model model){

		String type = request.getParameter("type");
		InvestorInfoResponse basicResponse = new InvestorInfoResponse();

		if ("invest".equals(type)) {
			service.getInvestEvent(request, basicResponse);
		} else {
			service.getExitEvent(request, basicResponse);
		}

		return basicResponse;
	}
}
