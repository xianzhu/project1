package com.cv.kdata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cache.CategoryInfoCache;
import com.cv.kdata.cache.LoginInfoCache;
import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.model.UserInfoWithBLOBs;
import com.cv.kdata.util.ConstElasticClient;
import com.cv.kdata.util.StringUtil;
import com.jfinal.plugin.activerecord.Db;
import com.kdata.defined.model.Information;

@Service
public class ElasticSearchService {
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

	@Autowired
	UserInfoMapper userInfoMapper;

	public class Paramenter {
		private String callback; // 回调函数
		private String key; // 查询关键字
		private String token; // 用户验证token
		private int from; // 从第几页开始
		private int count; // 返回条数

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public String getCallback() {
			return callback;
		}

		public void setCallback(String callback) {
			this.callback = callback;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public Paramenter(HttpServletRequest req) {
			if (req == null) {
				return;
			}
			callback = req.getParameter("callback");
			key = req.getParameter("key");
//			token = req.getParameter("token");
			token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
			String fromItem = req.getParameter("from");
			String toItem = req.getParameter("count");
			from = 0; // default value
			count = 20; // default value
			if (!StringUtil.isNullOrEmpty(fromItem)) {
				int fromtmp = Integer.valueOf(fromItem).intValue();
				if (fromtmp >= 0) {
					from = fromtmp;
				}
			}
			if (!StringUtil.isNullOrEmpty(toItem)) {
				int endtmp = Integer.valueOf(toItem).intValue();
				if (endtmp > 0) {
					count = endtmp;
				}
			}
		}
	}

	public List<Information> searchByPersonal(HttpServletRequest req){
		// 定制查询，先查取topic id信息
		long start = System.currentTimeMillis();

		Paramenter para = new Paramenter(req);

		if (!StringUtil.isNullOrEmpty(para.getKey()) && para.getKey().matches("[a-zA-Z]+")) {
			para.setKey(para.getKey().toUpperCase());
		}

		List<Information> informations = null;
		UserInfoWithBLOBs extend = null;
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		if(!StringUtil.isNullOrEmpty(token)){
			String uid = LoginInfoCache.getInstance().getUid(token);
			if(!StringUtil.isNullOrEmpty(uid)){
				DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
				extend = userInfoMapper.selectByPrimaryKey(uid);
			}
		}


		informations = queryData(para.getKey(), para.getFrom(),para.getCount(), extend);

		if ("2".equals(req.getParameter("order"))) {
			// 按时间排序，默认按相关性排序
			Collections.sort(informations);
		}
		System.out.println(String.format("=====>Elasticsearch search service, key:%s, cost time:%d", para.getKey(),
				(System.currentTimeMillis() - start)));

		return informations;
	}

	public List<Information> searchJob(HttpServletRequest req){
		long start = System.currentTimeMillis();


		Paramenter para = new Paramenter(req);
		if (!StringUtil.isNullOrEmpty(para.getKey()) && para.getKey().matches("[a-zA-Z]+")) {
			para.setKey(para.getKey().toUpperCase());
		}

		List<Information> informations = ConstElasticClient.getElasticSeachClient().search_extend(para.getKey(), null,
				null, null, para.getFrom(), para.getCount());

		System.out.println(String.format("=====>Elasticsearch search service, key:%s, cost time:%d", para.getKey(),
				(System.currentTimeMillis() - start)));

		return informations;
	}

	public static List<Information> simpleQuery(String key) {
		return simpleQuery(key, new ArrayList<>());
	}

	public static List<Information> simpleQuery(String key, List<String> channelList) {
		List<Information> informations = ConstElasticClient.getElasticSeachClient().search_top(key, null, null,
				channelList, 0, 20);
		Collections.sort(informations);
		return informations;
	}

	public static List<Information> simpleQuery(HttpServletRequest request) {

		String key = request.getParameter("key");
		int from = StringUtil.parseInt(request.getParameter("from"), 0);
		int count = StringUtil.parseInt(request.getParameter("count"), 20);
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

		List<Information> informations = ConstElasticClient.getElasticSeachClient().search_top(key, null, null,
				channel, from, count);
		Collections.sort(informations);
		return informations;
	}

	/**
	 * 新添加，没有customerInfo
	 *
	 * @param key
	 * @param from
	 * @param extend
	 * @return
	 */
	public List<Information> queryData(String key, int from,int count, UserInfoWithBLOBs extend) {
		List<Information> information_list = null;

		List<String> channels = null;
		if(extend != null && !StringUtil.isNullOrEmpty(extend.getDomainTips())){
			channels = transferToChannel(extend.getDomainTips());
		}

		logger.info("417 Searh keyword is " + key);
		if(!StringUtil.isNullOrEmpty(key)){
			information_list = ConstElasticClient.getElasticSeachClient().search_extend(key, null, null, channels, from,count);
		}else{
			//如果key为null，则为推送新闻，返回较新的新闻
			information_list = ConstElasticClient.getElasticSeachClient().search_top(key, null, null, channels,from,count);
		}

		return information_list;
	}

	/**
	 * 把domain转化为channel
	 * @param domain
	 * @return
	 */
	public List<String> transferToChannel(String domain){
		if(StringUtil.isNullOrEmpty(domain)){
			return null;
		}
		List<String> channels = new ArrayList<>();

		String [] domains = domain.split(",");
		String tmpDomain = "";
		for(int i=0; i<domains.length; i++){
			String channel = CategoryInfoCache.getInstance().getIdFromDomain(domains[i]);
			if(!StringUtil.isNullOrEmpty(channel)){
//				channels.add(channel);
				tmpDomain = tmpDomain + channel+ " ";
			}
		}
		tmpDomain = tmpDomain.trim();
		tmpDomain = tmpDomain.replaceAll(" ", ",");


		if(!StringUtil.isNullOrEmpty(tmpDomain)){
			try{
				String sql = String.format("select topic_id from ops_category_media where biz_cat_id in (%s)", tmpDomain);
				List<Integer> topicIds =  Db.query(sql);
				for (Integer topicIdStr : topicIds) {
					channels.add(String.valueOf(topicIdStr));
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return channels;
	}

	/**
	 * 精确查询
	 * @param key
	 * @return
	 */
	public List<Information> accureQuery(String key, int from, int count) {
		List<Information> informations = ConstElasticClient.getElasticSeachClient().accurateSearch(key,from,count);
//		Collections.sort(informations);
		return informations;
	}
}
