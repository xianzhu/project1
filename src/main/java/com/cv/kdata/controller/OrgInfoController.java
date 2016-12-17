package com.cv.kdata.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.response.OrganizeResponse;
import com.cv.kdata.service.OrganizeInfoService;

@Controller
public class OrgInfoController {
	@Autowired
	private OrganizeInfoService service;

	@RequestMapping(value="/org/search",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse search(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getIndexInfo(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/org/basic",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse basicInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getBasicOrgInfo(request, basicResponse);
		service.getOrgUserInfo(request, basicResponse);
		service.getOrgExtends(request, basicResponse);
		service.getOrgFocusInfo(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value="/org/fund",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse fundInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getFundInfo(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/org/captail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse captailInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getOrgInvestEvent(request, basicResponse);
		service.getOrgExitEvent(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value="/org/detail",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse detailInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		String type = request.getParameter("type");
		if ("invest".equals(type)) {
			service.getInvestEventDetail(request, basicResponse);
		} else {
			service.getExitEventDetail(request, basicResponse);
		}

		return basicResponse;
	}

	@RequestMapping(value="/org/top",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse topInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getOverallTrends(request, basicResponse);
		service.getRptPeList(request, basicResponse);

		return basicResponse;
	}

	@RequestMapping(value="/org/recommond",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse recommondInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();

		service.getProjectInfo(request, basicResponse);
		return basicResponse;
	}

	@RequestMapping(value="/org/extend",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public OrganizeResponse getExtendInfo(HttpServletRequest request,Model model){

		OrganizeResponse basicResponse = new OrganizeResponse();
		service.getOrgExtends(request, basicResponse);
		return basicResponse;
	}
}
