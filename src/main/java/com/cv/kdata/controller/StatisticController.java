package com.cv.kdata.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.response.DashboardResponse;
import com.cv.kdata.response.StatEventResponse;
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


	@RequestMapping(value="/stat/event",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public StatEventResponse statEvent(HttpServletRequest request,Model model){
		StatEventResponse response = new StatEventResponse();
		service.getEventTwoStat(request, response);
		service.getEventOneStat(request, response);
		return response;
	}

	/**
	 * 信心指数
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/stat/overall",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public TrendResponse overallTrend(HttpServletRequest request,Model model){
		TrendResponse response = new TrendResponse();
		service.getOverallTrends(request, response);
		return response;
	}

	/**
	 * 这个接口是统计日志操作，操作信息会被截取，不返回任何东西
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/stat/operation",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object otherOperations(HttpServletRequest request,Model model){
		//do nothing;
		return null;
	}
}
