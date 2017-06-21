package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.model.PMStartupInfo;

public class ProjectResponse extends ResponseObject{

	private List<PMStartupInfo> projectList;  //最新项目列表


	public List<PMStartupInfo> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<PMStartupInfo> projectList) {
		this.projectList = projectList;
	}

}
