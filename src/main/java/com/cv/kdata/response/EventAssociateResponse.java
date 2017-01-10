package com.cv.kdata.response;

public class EventAssociateResponse extends ResponseObject{
	Object investEventList;	//投资事件列表
	Object exitEventList;	//退出事件列表

	public Object getInvestEventList() {
		return investEventList;
	}
	public void setInvestEventList(Object investEventList) {
		this.investEventList = investEventList;
	}
	public Object getExitEventList() {
		return exitEventList;
	}
	public void setExitEventList(Object exitEventList) {
		this.exitEventList = exitEventList;
	}

}
