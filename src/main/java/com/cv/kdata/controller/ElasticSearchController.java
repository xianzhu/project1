package com.cv.kdata.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.Information;
import com.cv.kdata.service.ElasticSearchService;
import com.cv.kdata.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;

@Controller
public class ElasticSearchController {

	@Autowired
	ElasticSearchService service;
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
//		ElasticSearchService service = new ElasticSearchService();


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

	@RequestMapping(value="/elasticsearch/accurate",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Map<String,Object> accurateSearch(HttpServletRequest request,Model model){

		String key = request.getParameter("key");
		int from = StringUtil.parseInt(request.getParameter("from"), 0);
		int count = StringUtil.parseInt(request.getParameter("count"), 0);
//		ElasticSearchService service = new ElasticSearchService();

		List<Information> infos = service.accureQuery(key,from,count);
		if ("2".equals(request.getParameter("order")) && infos != null && infos.size()>1) {
			// 按时间排序，默认按相关性排序
			Collections.sort(infos);
		}

		Map<String,Object> data = new HashMap<String,Object>();
		data.put("status", RDDWebConst.SUCCESS);
		data.put("list", infos);
		return data;
	}
}
