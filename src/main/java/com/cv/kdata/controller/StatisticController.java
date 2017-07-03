package com.cv.kdata.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.response.DashboardResponse;
import com.cv.kdata.response.TrendResponse;
import com.cv.kdata.service.StatisticInfoService;

@Controller
public class StatisticController {
	@Autowired
	StatisticInfoService service;
	@RequestMapping(value="/stat/trend",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public TrendResponse trend(HttpServletRequest request,Model model){
		TrendResponse response = new TrendResponse();
		service.getTrendInfo(request, response);
		return response;
	}

	@RequestMapping(value="/stat/dashboard",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public DashboardResponse dashboard(HttpServletRequest request,Model model){
		DashboardResponse response = new DashboardResponse();
		service.getDashboardInfo(request, response);
		return response;
	}

	@RequestMapping(value="/stat/event1",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public TrendResponse statEvent1(HttpServletRequest request,Model model){
		TrendResponse response = new TrendResponse();
		service.getEventOneStat(request, response);
		return response;
	}

	@RequestMapping(value="/stat/event2",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public TrendResponse statEvent2(HttpServletRequest request,Model model){
		TrendResponse response = new TrendResponse();
		service.getEventTwoStat(request, response);
		return response;
	}
}
