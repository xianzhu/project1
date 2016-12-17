package com.cv.kdata.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.Information;
import com.cv.kdata.model.LoginInfo;
import com.cv.kdata.util.ConstElasticClient;
import com.cv.kdata.util.PersonalInfo;
import com.cv.kdata.util.StringUtil;

public class ElasticSearchService {
	private static Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

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

		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		LoginInfo extend = PersonalInfo.getLoginInfo(token);
		List<Information> informations = null;
		if (extend != null && extend.getDomainTips() != null) {
			informations = queryData(para.getKey(), para.getFrom(), extend);
		} else {
			informations = ConstElasticClient.getElasticSeachClient().search_extend(para.getKey(), null, null, null,
					para.getFrom());
		}
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

	/**
	 * 新添加，没有customerInfo
	 *
	 * @param key
	 * @param from
	 * @param extend
	 * @return
	 */
	public List<Information> queryData(String key, int from, LoginInfo extend) {
		List<Information> information_list = null;

		String key_word = null;
		key_word = extend.getDomainTips() + key;

		// a bug in elasticsearch. cannot parse the "/"
		if(!StringUtil.isNullOrEmpty(key_word)){
			key_word = key_word.replaceAll("/", " ");
		}

		logger.info("417 Searh keyword is " + key_word);
		information_list = ConstElasticClient.getElasticSeachClient().search_extend(key_word, null, null, null, from);

		return information_list;
	}
}
