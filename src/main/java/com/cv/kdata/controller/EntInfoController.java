package com.cv.kdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.response.EntInfoResponse;
import com.cv.kdata.service.EntInfoService;
import com.cv.kdata.service.ToolsPlatformService;
import com.jfinal.kit.JsonKit;

@Controller
public class EntInfoController {
	@Autowired
	private EntInfoService entInfoService;
	@Autowired
	private ToolsPlatformService toolService;

	@RequestMapping(value="/entinfo/search",method={RequestMethod.GET,RequestMethod.POST}, produces = "application/json; charset=utf-8")
	@ResponseBody
    public Object search(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

//		if (!ServletHelper.paramenterVerification(token, basicResponse)){
//			return JsonKit.toJson(basicResponse);
//		}

		entInfoService.getSearchInfo(request,basicResponse);
		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/basic", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object basicInfo(HttpServletRequest request,Model model){

		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getEntBasicInfo(request, basicResponse);
		entInfoService.getEntChangeInfo(request, basicResponse);
		entInfoService.getEntHolderInfo(request, basicResponse);
		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/deep", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object deepInfo(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getEntInvestInfo(request, basicResponse);
		entInfoService.getEntBranchInfo(request, basicResponse);
		entInfoService.getEntSoftCopyRightsInfo(request, basicResponse);
		entInfoService.getEntPatentInfo(request, basicResponse);
		//entInfoService.getEntHolderInfo(request, basicResponse);
		entInfoService.getEntRelatedInfo(request, basicResponse);
		// 添加评分体系
		entInfoService.getEntJudge(request, basicResponse);

		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/credit", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object creditInfo(HttpServletRequest request,Model model){

		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getEntEquityInfo(request, basicResponse);
		entInfoService.getEntAbnormalInfo(request, basicResponse);
		//entInfoService.getEntCopyRightsInfo(request, basicResponse);
		entInfoService.getEntLawInfo(request, basicResponse);
		entInfoService.getEntMortgagesInfo(request, basicResponse);

		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/data", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object dataInfo(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

//		ToolsPlatformService toolService = new ToolsPlatformService();
		String code = toolService.TransferIdAndStock(request);

		entInfoService.getStockPotentialInfo(code, basicResponse);
		entInfoService.getStockXsbInfo(code, basicResponse);
		entInfoService.getChartsInfo(code, basicResponse);
		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/notice", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object noticeInfo(HttpServletRequest request,Model model){

		EntInfoResponse basicResponse = new EntInfoResponse();

//		ToolsPlatformService toolService = new ToolsPlatformService();
		String code = toolService.TransferIdAndStock(request);

		// 返回公告
		entInfoService.getManagerTeam(code, basicResponse);
		entInfoService.getStockHolder(code, basicResponse);
		entInfoService.getStockInstInvest(code, basicResponse);
		//entInfoService.getChartsInfo(request, basicResponse);
		entInfoService.getStockEquityCtrl(code, basicResponse);

		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/top", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object topInfo(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getRpt2MaList(request, basicResponse);
		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/invest", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object getEntInvest(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getEntInvestInfo(request, basicResponse);
		return JsonKit.toJson(basicResponse);
	}

	@RequestMapping(value="/entinfo/finance", produces = "application/json; charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
   public Object getEntFinance(HttpServletRequest request,Model model){
		EntInfoResponse basicResponse = new EntInfoResponse();

		entInfoService.getFinanceInfo(request, basicResponse);
		return JsonKit.toJson(basicResponse);
	}

}
