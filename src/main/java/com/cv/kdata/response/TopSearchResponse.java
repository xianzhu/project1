package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.model.PMOrgInfo;
import com.cv.kdata.model.PMUserInfo;

public class TopSearchResponse extends ResponseObject{
	List<PMUserInfo> userBasicList;
	List<PMOrgInfo> orgBasicList;
	List<PMUserInfo> userExtendList;
	List<PMOrgInfo> orgExtendList;
	private static final int defaultCount = 10;
	public List<PMUserInfo> getUserBasicList() {
		return userBasicList;
	}
	public void setUserBasicList(List<PMUserInfo> userBasicList) {
		if(userBasicList.size()>defaultCount){
			this.userBasicList = userBasicList.subList(0, defaultCount-1);
		}else{
			this.userBasicList = userBasicList;
		}
	}
	public List<PMOrgInfo> getOrgBasicList() {
		return orgBasicList;
	}
	public void setOrgBasicList(List<PMOrgInfo> orgBasicList) {
		if(orgBasicList.size()>defaultCount){
			this.orgBasicList = orgBasicList.subList(0, defaultCount-1);
		}else{
			this.orgBasicList = orgBasicList;
		}
	}
	public List<PMUserInfo> getUserExtendList() {
		return userExtendList;
	}
	public void setUserExtendList(List<PMUserInfo> userExtendList) {
		if(userExtendList.size()>defaultCount){
			this.userExtendList = userExtendList.subList(0, defaultCount-1);
		}else{
			this.userExtendList = userExtendList;
		}
	}
	public List<PMOrgInfo> getOrgExtendList() {
		return orgExtendList;
	}
	public void setOrgExtendList(List<PMOrgInfo> orgExtendList) {
		
		if(orgExtendList.size()>defaultCount){
			this.orgExtendList = orgExtendList.subList(0, defaultCount-1);
		}else{
			this.orgExtendList = orgExtendList;
		}
	}
	
}
