package com.cv.kdata.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.cache.EventTypeCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.LocationNews;
import com.cv.kdata.response.DailyEventResponse;
import com.cv.kdata.response.EventAssociateResponse;
import com.cv.kdata.response.GeneralResponse;
import com.cv.kdata.response.MediaSyncResponse;
import com.cv.kdata.response.MonitorResponse;
import com.cv.kdata.response.TopSearchResponse;
import com.cv.kdata.response.UserInfoResponse;
import com.cv.kdata.service.DailyEventService;
import com.cv.kdata.service.EventService;
import com.cv.kdata.service.MediaSyncService;
import com.cv.kdata.service.MonitorService;
import com.cv.kdata.service.TopSearchService;
import com.cv.kdata.service.UserInfoService;
import com.cv.kdata.util.StringUtil;


@Controller
public class TopSearchController {
	@Autowired
	private TopSearchService service;

	@Autowired
	private MediaSyncService mediaService;

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private MonitorService monitorService;
	@Autowired
	private EventService eventService;

	@Autowired
	private DailyEventService dailyEventService;

	private static Logger logger = LoggerFactory.getLogger(TopSearchController.class);
	@RequestMapping(value="/topsearch",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public TopSearchResponse search(HttpServletRequest request,Model model){
		TopSearchResponse basicResponse = new TopSearchResponse();

		String mode = request.getParameter("mode");

		if("basic".equals(mode)){
			service.getBasicSearchInfo(request, basicResponse);
		}else if("extend".equals(mode)){
			service.extendSearch(request, basicResponse);
		}else{
			service.basicSearch(request, basicResponse);
			service.extendSearch(request, basicResponse);
		}

		return basicResponse;
	}

	@RequestMapping(value="/top/mediasync",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public MediaSyncResponse mediaSync(HttpServletRequest request,Model model){
		MediaSyncResponse basicResponse = new MediaSyncResponse();

		mediaService.getStaticInfo(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/top/news",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public MediaSyncResponse newsSync(HttpServletRequest request,Model model){
		MediaSyncResponse basicResponse = new MediaSyncResponse();

		try {
			List<LocationNews> news = mediaService.getNewsByLocation(request);
			basicResponse.setNews(news);
			basicResponse.setStatus(RDDWebConst.SUCCESS);
		} catch (SQLException e) {
			basicResponse.setStatus(RDDWebConst.FAILURE);
			logger.error(e.getMessage());
		}

		return basicResponse;
	}

	@RequestMapping(value="/top/person",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public UserInfoResponse personInfo(HttpServletRequest request,Model model){
		String token = (String) request.getSession().getAttribute(RDDWebConst.TOKEN);
		UserInfoResponse basicResponse = new UserInfoResponse();

		userInfoService.getSimpleInfo(token, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/monitor/entInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public MonitorResponse entInfoMonitor(HttpServletRequest request,Model model){
		MonitorResponse response = new MonitorResponse();
		monitorService.getMonitorEnt(request,response);
		return response;
	}

	@RequestMapping(value="/monitor/orgInfo",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public MonitorResponse orgInfoMonitor(HttpServletRequest request,Model model){
		MonitorResponse response = new MonitorResponse();
		monitorService.getMonitorOrg(request,response);
		return response;
	}

	@RequestMapping(value="/top/dayevent",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public EventAssociateResponse getCurrentDayEvent(HttpServletRequest request,Model model){
		EventAssociateResponse response = new EventAssociateResponse();
		String type = request.getParameter("type");
		int from = StringUtil.parseInt(request.getParameter("from"), 0);
		int count = StringUtil.parseInt(request.getParameter("count"), 10);
		String investOrExit = EventTypeCache.getInstance().isInvestOrExit(type);
		DBContextHolder.setDbType(DBContextHolder.PESEER_ONLINE);

		if("invest".equals(investOrExit)){
			response.setInvestEventList(dailyEventService.getCurrentDateInvestEvents(type,from,count));
		}else if("exit".equals(investOrExit)){
			response.setExitEventList(dailyEventService.getCurrentDateExitEvents(type,from,count));
		}else{
			//默认获取投资事件前十条
			response.setInvestEventList(dailyEventService.getCurrentDateInvestEvents(null,from,count));
		}
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("get current date events success!");
		return response;
	}

	@RequestMapping(value="/top/daydetail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object getDailyEventDetail(HttpServletRequest request,Model model){
		String eventName = request.getParameter("event");
		String type = request.getParameter("type");

		DailyEventResponse response = new DailyEventResponse();
		dailyEventService.getDailyEventDetail(eventName, type, response);

		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("get events success!");
		return response;
	}

	@RequestMapping(value="/top/schedule",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object getDailySchedule(HttpServletRequest request,Model model){
		GeneralResponse response = new GeneralResponse();
		service.getDailySchedule(request, response);
		return response;
	}

}
