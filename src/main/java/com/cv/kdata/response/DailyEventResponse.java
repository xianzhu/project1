package com.cv.kdata.response;

import java.util.Collections;
import java.util.List;

import com.kdata.defined.model.DailyEventGeneralObject;


public class DailyEventResponse extends ResponseObject{
	int from;
	int total;
	List<DailyEventGeneralObject> eventsList;


	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<DailyEventGeneralObject> getEventsList() {
		return eventsList;
	}
	public void setEventsList(List<DailyEventGeneralObject> eventsList) {
		this.eventsList = eventsList;
	}

	public void addEventList(List<DailyEventGeneralObject> list){
		if(eventsList != null){
			eventsList.addAll(list);
		}else{
			eventsList = list;
		}
	}
	public void sortAndCut(){
		if(null != eventsList && !eventsList.isEmpty()){
			Collections.sort(eventsList);
			if(total>0){
				List<DailyEventGeneralObject> tmpList = eventsList.subList(0, total);
				eventsList = tmpList;
			}
		}
	}
}
