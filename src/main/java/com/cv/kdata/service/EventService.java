package com.cv.kdata.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.dao.PMExitEventMapper;
import com.cv.kdata.dao.PMInvestEventMapper;
import com.cv.kdata.model.PMExitEvent;
import com.cv.kdata.model.PMInvestEvent;


@Service
public class EventService {
	@Autowired
	PMExitEventMapper exitEventMapper;
	@Autowired
	PMInvestEventMapper investEventMapper;

	/**
	 * 获取当天投资时间
	 * @return
	 */
	public List<PMInvestEvent> getCurrentDateInvestEvents(int from, int count){
//		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String date = "2016-12-12";//为了测试修改的固定日期
		List<PMInvestEvent> list = investEventMapper.getEventsAfterDate(date, from);
		return list;
	}

	/**
	 * 根据类型获取当天投资时间
	 * @return
	 */
	public List<PMInvestEvent> getCurrentDateInvestEvents(String type, int from, int count){
//		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String date = "2016-12-12";
		List<PMInvestEvent> list = investEventMapper.getEventsAfterDateByType(date, type,from,count);
		return list;
	}

	/**
	 * 获取当天退出事件
	 * @return
	 */
	public List<PMExitEvent> getCurrentDateExitEvents(int from){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<PMExitEvent> list = exitEventMapper.getEventsAfterDate(date, from);
		return list;
	}

	/**
	 * 根据类型获取当天退出事件
	 * @return
	 */
	public List<PMExitEvent> getCurrentDateExitEvents(String type, int from, int count){
//		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String date = "2016-12-12";
		List<PMExitEvent> list = exitEventMapper.getEventsAfterDateByType(date,type, from,count);
		return list;
	}

}
