package com.cv.kdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.response.ProjectResponse;
import com.cv.kdata.service.ProjectInfoService;

@Controller
public class ProjectInfoController {
	@Autowired
	private ProjectInfoService service;

	@RequestMapping(value = "/project/search", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ProjectResponse search(HttpServletRequest request, Model model) {

		ProjectResponse basicResponse = new ProjectResponse();

		service.search(request, basicResponse);

		return basicResponse;
	}
}
