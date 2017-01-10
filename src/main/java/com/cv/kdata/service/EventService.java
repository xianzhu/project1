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
	public List<PMInvestEvent> getCurrentDateInvestEvents(int from){
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<PMInvestEvent> list = investEventMapper.getEventsAfterDate(date, from);
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

}