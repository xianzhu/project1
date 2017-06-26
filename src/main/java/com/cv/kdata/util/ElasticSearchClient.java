package com.cv.kdata.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.conf.ConfigurationHelper;
import com.kdata.defined.model.Information;


public class ElasticSearchClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchClient.class);

	String indexName;
	String[] index;
	String cluster;
	int indexCount;

	TransportClient client;

	QueryBuilder filter = null; // 过滤条件
	QueryBuilder query = null; // 查询条件

	public ElasticSearchClient() {
		prepareConfig();
		client = initClient();
	}

	public void prepareConfig() {
		if (!"".equals(ConfigurationHelper.ELASTICSEARCH_RDD_CLUSTER)) {
			cluster = ConfigurationHelper.ELASTICSEARCH_RDD_CLUSTER;
		} else {
			cluster = "rdd-es-cluster";
		}

		if (!"".equals(ConfigurationHelper.ELASTICSEARCH_INDEX_NAME)) {
			indexName = ConfigurationHelper.ELASTICSEARCH_INDEX_NAME;
		} else {
			indexName = "media";
		}

		if (0 >= ConfigurationHelper.ELASTICSEARCH_RESULT_COUNT) {
			indexCount = 20;
		} else {
			indexCount = ConfigurationHelper.ELASTICSEARCH_RESULT_COUNT;
		}

		if (!StringUtils.isEmpty(indexName)) {
			String[] tmp = indexName.split(",");
			index = new String[tmp.length];
			for (int i = 0; i < tmp.length; i++) {
				index[i] = tmp[i].trim();
			}
		}
	}

	public TransportClient initClient() {
		Settings settings = Settings.settingsBuilder().put("cluster.name", cluster)
				.put("client.transport.ignore_cluster_name", false).put("node.client", true)
				.put("client.transport.sniff", true).build();

		TransportClient client = null;
		try {
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.26.201.247"), 9300));
			if (client == null) {
				LOGGER.info("Init Elasticsearch client failed!");
			} else {
				LOGGER.info("Init Elasticsearch client successul!");
			}
		} catch (UnknownHostException e) {
			LOGGER.error("client initlization failed!");
			LOGGER.error(e.toString());
		}

		return client;
	}

	/**
	 * 精确匹配
	 * @param key
	 * @return
	 */
	public List<Information> accurateSearch(String key, int from, int count) {
		// 1. 建立查询规则，key为空查询所有
		QueryBuilder builder = QueryBuilders.matchAllQuery();
		if (!StringUtils.isEmpty(key)) {
			key = "\""+key+"\"";
			builder = QueryBuilders.queryStringQuery(key);
		}

		// 2. 查询
		from = from < 0 ? 0 : from;
		count = count < 1 ? indexCount : count;

		long startTime = System.currentTimeMillis();
		SearchResponse res = client.prepareSearch(index).setTypes().setQuery(builder)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setFrom(from).setSize(count).setExplain(true)
				.execute().actionGet();
		LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));

		// 3. 解析
		return analysisResult(res);
	}

	public void generateSearchRule(String key, Date begin_time, Date end_time, Collection<String> channel_list) {

		QueryBuilder timeFilter = null;
		QueryBuilder channelFilter = null;

		// 1. 建立查询规则，key为空查询所有
		// String[] fields = {"doc.title","doc.content","doc.source"};
		if (!StringUtils.isEmpty(key)) {
			query = QueryBuilders.queryStringQuery(key);
			// query = QueryBuilders.multiMatchQuery(key, fields);
		} else {
			query = QueryBuilders.matchAllQuery();
		}

		// 2. 建立时间过滤规则，过滤时间段
//		if (!StringUtils.isEmpty(begin_time) && !StringUtils.isEmpty(end_time)) {
		if (begin_time!=null && end_time != null) {
			timeFilter = QueryBuilders.rangeQuery("doc.create_time").from(begin_time).to(end_time);
		}else if(begin_time!=null){
			timeFilter = QueryBuilders.rangeQuery("doc.create_time").gt(begin_time);
		}else if(end_time != null){
			timeFilter = QueryBuilders.rangeQuery("doc.create_time").lt(end_time);
		}

		// 3. 建立channel过滤规则，过滤个人设置channel
		if (channel_list != null && !channel_list.isEmpty()) {
			channelFilter = QueryBuilders.termsQuery("doc.channel", channel_list);
		}

		// 创建2天以内的微信信息
//		QueryBuilder wchatTypeFilter = QueryBuilders.termQuery("doc.media_type", 2);
//		QueryBuilder wchatTimeFilter = QueryBuilders.rangeQuery("doc.create_time").lt(TimeUtil.getDaysBefore(2));
////		from().to(TimeUtil.getCurrentTime());
//		QueryBuilder wChatFilters = QueryBuilders.boolQuery().must(wchatTypeFilter).must(wchatTimeFilter);
		// 4. 合并过滤规则
		if (timeFilter != null && channelFilter != null) {
			filter = QueryBuilders.boolQuery().must(timeFilter).must(channelFilter);
		} else if (timeFilter == null && channelFilter != null) {
			filter = channelFilter;
		} else if (timeFilter != null && channelFilter == null) {
			filter = timeFilter;
		} else {
			filter = null;
			LOGGER.info("Filter is empty!");
		}
	}

	public List<Information> analysisResult(SearchResponse res) {
		List<Information> list = new ArrayList<>();

		SearchHits shs = res.getHits();
		for (SearchHit it : shs) {
			Map<String, Object> fields = it.getSource();
			if (!fields.isEmpty()) {
				@SuppressWarnings("unchecked")
				Map<String, Object> field = (Map<String, Object>) fields.get("doc");
				if (field != null && !field.isEmpty()) {
					String title = (String) field.get("title");

					String channel = String.valueOf(field.get("channel"));

					String content = (String) field.get("content");
					if (!StringUtils.isEmpty(content)
							&& content.length() >= ConfigurationHelper.SOLR_CONTENT_DISPLAY_LENGTH)
						content = content.substring(0, ConfigurationHelper.SOLR_CONTENT_DISPLAY_LENGTH);
					String url = (String) field.get("url");
					String createTime = (String) field.get("create_time");
					if(!StringUtil.isNullOrEmpty(createTime)){
						createTime = createTime.replace("T", " ");
						createTime = createTime.replace(".000+0000","");
					}
					int mediaType = (int) field.get("media_type");

					if (mediaType == 2) {
						//删除两天前的微信记录
						String daysBefore = TimeUtil.getDaysBefore(2);
						if (daysBefore.compareToIgnoreCase(createTime) >= 0) {
							continue;
						}
					}
					com.kdata.defined.model.Information info = new com.kdata.defined.model.Information(channel,null,title, content, url, createTime);
					list.add(info);
				}
			}
		}

		return list;

	}

	public List<Information> search_extend(String key, Date begin_time, Date end_time,
			Collection<String> channel_list, int from) {
		long startTime = System.currentTimeMillis();

		// 1. 生成查询规则
		generateSearchRule(key, begin_time, end_time, channel_list);

		// 3. 查询
		from = from < 0 ? 0 : from;

		SearchResponse res = null;
		LOGGER.info(String.format("create the search is %d\n", System.currentTimeMillis() - startTime));
		LOGGER.info("print the client " + client.toString() + "\n");
		if (filter != null) {
			LOGGER.info(query.toString() + filter.toString());

			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setPostFilter(filter)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setFrom(from).setSize(indexCount).setExplain(true)
					.execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));
		} else {
			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setFrom(from).setSize(indexCount).setExplain(true).execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));

		}

		// 3. 解析结果
		return analysisResult(res);
	}

	public List<Information> search_extend(String key, Date begin_time, Date end_time,
			Collection<String> channel_list, int from, int count) {
		long startTime = System.currentTimeMillis();

		// 1. 生成查询规则
		generateSearchRule(key, begin_time, end_time, channel_list);

		// 2. 查询
		from = from < 0 ? 0 : from;
		count = count < 1 ? indexCount : count;

		SearchResponse res = null;
		LOGGER.info(String.format("create the search is %d\n", System.currentTimeMillis() - startTime));
		LOGGER.info("print the client " + client.toString() + "\n");
		if (filter != null) {
			LOGGER.info(query.toString() + filter.toString());

			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setPostFilter(filter)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setFrom(from).setSize(count).setExplain(true)
					.execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));
		} else {
			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
					.setFrom(from).setSize(count).setExplain(true).execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));

		}

		// 3. 解析结果
		return analysisResult(res);
	}

	//用作首页搜索
	public List<Information> search_top(String key, Date begin_time, Date end_time,
			Collection<String> channel_list, int from, int count) {
		long startTime = System.currentTimeMillis();

		// 1. 生成查询规则
		generateSearchRule(key, begin_time, end_time, channel_list);

		// 2. 查询
		from = from < 0 ? 0 : from;
		count = count < 1 ? indexCount : count;

		SearchResponse res = null;
		LOGGER.info(String.format("create the search is %d\n", System.currentTimeMillis() - startTime));
		LOGGER.info("print the client " + client.toString() + "\n");
		if (filter != null) {
			LOGGER.info(query.toString() + filter.toString());

			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setPostFilter(filter).addSort("@timestamp", SortOrder.DESC)
					.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setFrom(from).setSize(count).setExplain(true)
					.execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));
		} else {
			startTime = System.currentTimeMillis();
			res = client.prepareSearch(index).setTypes().setQuery(query).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addSort("@timestamp", SortOrder.DESC)
					.setFrom(from).setSize(count).setExplain(true).execute().actionGet();
			LOGGER.info(String.format("total time is %d\n", System.currentTimeMillis() - startTime));

		}

		// 3. 解析结果
		return analysisResult(res);
	}
}
