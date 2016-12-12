package com.cv.peseer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.model.Information;
import com.cv.peseer.service.ElasticSearchService;
import com.jfinal.plugin.activerecord.Db;

@Controller
public class ElasticSearchController {

	@RequestMapping(value="/elasticsearch/top1",method={RequestMethod.GET,RequestMethod.POST})
	public String topSearch(HttpServletRequest request,Model model){
		String key = request.getParameter("key");
		List<Information> infos = ElasticSearchService.simpleQuery(key);
		model.addAttribute("list", infos);
		return "elasticsearch";
	}

	@RequestMapping(value="/elasticsearch/top",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public List<Information> simpleSearch2(HttpServletRequest request,Model model){
		String key = request.getParameter("key");
		List<String> channel = new ArrayList<>();
		String channelStr = request.getParameter("id");
		if(StringUtils.isNotBlank(channelStr)){
			try{
				int biz_cat_id = Integer.parseInt(channelStr);
				List<Integer> topicIds =  Db.query(" select topic_id from ops_category_media where biz_cat_id = ? ", biz_cat_id);
				for (Integer topicIdStr : topicIds) {
					channel.add(String.valueOf(topicIdStr));
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		List<Information> infos = ElasticSearchService.simpleQuery(key,channel);
		return infos;
	}

	@RequestMapping(value="/elasticsearch",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Map<String,Object> search(HttpServletRequest request,Model model){

		String mode = request.getParameter("mode");
		ElasticSearchService service = new ElasticSearchService();


		List<Information> infos = new ArrayList<>();
		if ("personal".equals(mode)){
			infos = service.searchByPersonal(request);
		}else{
			infos = service.searchJob(request);
		}

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("status", RDDWebConst.SUCCESS);
		data.put("list", infos);
		return data;
	}
}
