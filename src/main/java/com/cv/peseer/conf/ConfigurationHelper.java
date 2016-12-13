package com.cv.peseer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.peseer.util.StringUtil;



public class ConfigurationHelper {
	private static Logger log = LoggerFactory.getLogger(ConfigurationHelper.class);


	static {
		WEB_DB_DRIVER = getWEBDBDriver();
		WEB_DB_URL = getWebDBUrl();
		WEB_DB_USER = getWebDBUser();
		WEB_DB_PASSWORD = getWebDBPassword();
		WEB_DB_SLEEP_TIME = getWebDBSleepTime();
		WEB_DB_SYSTIME_QUERY = getWebDBSysTimeQuery();

		SPIDER_DB_DRIVER = getSpiderDBDriver();
		SPIDER_DB_URL = getSpiderDBUrl();
		SPIDER_DB_USER = getSpiderDBUser();
		SPIDER_DB_PASSWORD = getSpiderDBPassword();
		SPIDER_DB_SLEEP_TIME = getSpiderDBSleepTime();
		SPIDER_DB_SYSTIME_QUERY = getSpiderDBSysTimeQuery();

		SERVER_ID = getServerId();
		SEARCH_FOLDER_NAME = getSearchFolderName();
		ACCESS_FOLDER_NAME = getAccessFolderName();
		LOG_ROOT_FOLDER = getLogRootFolder();
		PEOPLE_MAX_DISP_LENGTH = getPeopleMaxDispLength();
		ORG_MAX_DISP_LENGTH = getOrgMaxDispLength();
		SOLR_CONTENT_DISPLAY_LENGTH = getSolrContentDisplayLength();
		SOLR_INFORMATION_RET_COUNTER = getSolrInformationRetCounter();
		SOLR_INVEST_EXIT_RET_COUNTER = getSolrInvestExitRetCounter();
		SOLR_MEDIA_MONITOR_LOG_URL = getSolrMediaMonitorLogUrl();
		SOLR_INVEST_EXIT_URL = getSolrInvestExitUrl();
		SOLR_WECHAT_SOGOU_URL = getSolrWechatSogouUrl();
		ROLLING_NEWS_RET_COUNTER = getRollingNewsRetCounter();

		// elasticSearch configuration
		ELASTICSEARCH_INDEX_NAME = getElasticIndexName();
		ELASTICSEARCH_RDD_CLUSTER = getElasticCluster();
		ELASTICSEARCH_RESULT_COUNT = getElasticResultCount();
	}

	/**
	 * Solr微信公众号监控Url
	 *
	 */
	public static final String SOLR_WECHAT_SOGOU_URL;

	private static String getSolrWechatSogouUrl() {
		String temp = ConfigurationManager.getAppSetting("solrWechatSogouUrl",
				"http://192.168.0.74:8080/solr/wechat_sogou_aticlenode");
		log.info(String.format("solrWechatSogouUrl:[%s]", temp));
		return temp;
	}

	/**
	 * Solr 投资退出事件Url
	 *
	 */
	public static final String SOLR_INVEST_EXIT_URL;

	private static String getSolrInvestExitUrl() {
		String temp = ConfigurationManager.getAppSetting("solrInvestExitUrl",
				"http://192.168.0.74:8080/solr/invent_exit_node");
		log.info(String.format("solrInvestExitUrl:[%s]", temp));
		return temp;
	}

	/**
	 * Solr媒体监控日志URL
	 *
	 */
	public static final String SOLR_MEDIA_MONITOR_LOG_URL;

	private static String getSolrMediaMonitorLogUrl() {
		String temp = ConfigurationManager.getAppSetting("solrMediaMonitorLogUrl",
				"http://192.168.0.74:8080/solr/media_monitor_log_node");
		log.info(String.format("solrMediaMonitorLogUrl:[%s]", temp));
		return temp;
	}

	/**
	 * Solr返回数据是内容显示长度
	 *
	 */
	public static final int SOLR_CONTENT_DISPLAY_LENGTH;

	private static int getSolrContentDisplayLength() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("solrContentDisplayLength"), 160);
		log.info(String.format("solrContentDisplayLength:[%d]", temp));
		return temp;
	}

	/**
	 * Solr投融资数据搜索返回最大条数
	 *
	 */
	public static final int SOLR_INVEST_EXIT_RET_COUNTER;

	private static int getSolrInvestExitRetCounter() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("solrInvestExitRetCounter"), 500);
		log.info(String.format("solrInvestExitRetCounter:[%d]", temp));
		return temp;
	}

	/**
	 * Solr情报搜索返回最大条数
	 */
	public static final int SOLR_INFORMATION_RET_COUNTER;

	private static int getSolrInformationRetCounter() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("solrInformationRetCounter"), 500);
		log.info(String.format("solrInformationRetCounter:[%d]", temp));
		return temp;
	}

	/**
	 * 滚动新闻返回条数
	 */
	public final static int ROLLING_NEWS_RET_COUNTER;

	private static int getRollingNewsRetCounter() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("rollingNewsRetCounter"), 400);
		log.info(String.format("rollingNewsRetCounter:[%d]", temp));
		return temp;
	}

	/**
	 * 人物简介显示最大长度
	 */
	public final static int PEOPLE_MAX_DISP_LENGTH;

	private static int getPeopleMaxDispLength() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("peopleMaxDispLength"), 32);
		log.info(String.format("peopleMaxDispLength:[%d]", temp));
		return temp;
	}

	/**
	 * 机构简介显示最大长度
	 */
	public final static int ORG_MAX_DISP_LENGTH;

	private static int getOrgMaxDispLength() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("orgMaxDispLength"), 40);
		log.info(String.format("orgMaxDispLength:[%d]", temp));
		return temp;
	}

	/**
	 * Log root folder
	 */
	public static final String LOG_ROOT_FOLDER;

	private static String getLogRootFolder() {
		String temp = ConfigurationManager.getAppSetting("logRootFolder", "/home/rdd/workdir/data/tomcat_log");
		log.info(String.format("logRootFolder:[%s]", temp));
		return temp;
	}

	/**
	 * Search Folder Name
	 */
	public static final String SEARCH_FOLDER_NAME;

	private static String getSearchFolderName() {
		String temp = ConfigurationManager.getAppSetting("searchFolderName", "search");
		log.info(String.format("searchFolderName:[%s]", temp));
		return temp;
	}

	/**
	 * Access Folder Name
	 */
	public static final String ACCESS_FOLDER_NAME;

	private static String getAccessFolderName() {
		String temp = ConfigurationManager.getAppSetting("accessFolderName", "access");
		log.info(String.format("accessFolderName:[%s]", temp));
		return temp;
	}

	/**
	 * Server Id
	 */
	public static final String SERVER_ID;

	private static String getServerId() {
		String temp = ConfigurationManager.getAppSetting("serverId", "");
		log.info(String.format("serverId:[%s]", temp));
		return temp;
	}

	/**
	 * WEB DB Driver
	 */
	/**
	 * DB Driver
	 */
	public static final String WEB_DB_DRIVER;

	private static String getWEBDBDriver() {
		String val = ConfigurationManager.getAppSetting("webDatabaseDriver", "com.mysql.jdbc.Driver");
		log.info(String.format("webDatabaseDriver:[%s]", val));
		return val;
	}

	/**
	 * WEB db 连接的url
	 */
	public static final String WEB_DB_URL;

	private static String getWebDBUrl() {
		String temp = ConfigurationManager.getAppSetting("webDatabaseUrl", "");
		log.info(String.format("webDatabaseUrl:[%s]", temp));
		return temp;
	}

	/**
	 * WEB db 连接的username
	 */
	public static final String WEB_DB_USER;

	private static String getWebDBUser() {
		String temp = ConfigurationManager.getAppSetting("webDatabaseUser", "root");
		log.info(String.format("webDatabaseUser:[%s]", temp));
		return temp;
	}

	/**
	 * WEB db 连接的url
	 */
	public static final String WEB_DB_PASSWORD;

	private static String getWebDBPassword() {
		String temp = ConfigurationManager.getAppSetting("webDatabasePassword", "password");
		log.info(String.format("webDatabasePassword:[%s]", temp));
		return temp;
	}

	/**
	 * WEB db 同步数据的时间间隔
	 */
	public static final int WEB_DB_SLEEP_TIME;

	private static int getWebDBSleepTime() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("webDatabaseSleepTime"), 120000);
		log.info(String.format("webDatabaseSleepTime:[%s]", temp));
		return temp;
	}

	/**
	 * WEB db 连接的url
	 */
	public static final String WEB_DB_SYSTIME_QUERY;

	private static String getWebDBSysTimeQuery() {
		String temp = ConfigurationManager.getAppSetting("webDatabaseSystemTimeQuery",
				"select sysdate() as Systemtime;");
		log.info(String.format("webDatabaseSystemTimeQuery:[%s]", temp));
		return temp;
	}

	/**
	 * SPIDER DB Driver
	 */
	public static final String SPIDER_DB_DRIVER;

	private static String getSpiderDBDriver() {
		String val = ConfigurationManager.getAppSetting("spiderDatabaseDriver", "com.mysql.jdbc.Driver");
		log.info(String.format("spiderDatabaseDriver:[%s]", val));
		return val;
	}

	/**
	 * SPIDER db 连接的url
	 */
	public static final String SPIDER_DB_URL;

	private static String getSpiderDBUrl() {
		String temp = ConfigurationManager.getAppSetting("spiderDatabaseUrl", "");
		log.info(String.format("spiderDatabaseUrl:[%s]", temp));
		return temp;
	}

	/**
	 * SPIDER db 连接的username
	 */
	public static final String SPIDER_DB_USER;

	private static String getSpiderDBUser() {
		String temp = ConfigurationManager.getAppSetting("spiderDatabaseUser", "");
		log.info(String.format("spiderDatabaseUser:[%s]", temp));
		return temp;
	}

	/**
	 * SPIDER db 连接的url
	 */
	public static final String SPIDER_DB_PASSWORD;

	private static String getSpiderDBPassword() {
		String temp = ConfigurationManager.getAppSetting("spiderDatabasePassword", "");
		log.info(String.format("spiderDatabasePassword:[%s]", temp));
		return temp;
	}

	/**
	 * SPIDER db 同步数据的时间间隔
	 */
	public static final int SPIDER_DB_SLEEP_TIME;

	private static int getSpiderDBSleepTime() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("spiderDatabaseSleepTime"), 120000);
		log.info(String.format("spiderDatabaseSleepTime:[%s]", temp));
		return temp;
	}

	/**
	 * SPIDER db 连接的url
	 */
	public static final String SPIDER_DB_SYSTIME_QUERY;

	private static String getSpiderDBSysTimeQuery() {
		String temp = ConfigurationManager.getAppSetting("spiderDatabaseSystemTimeQuery",
				"select sysdate() as Systemtime;");
		log.info(String.format("spiderDatabaseSystemTimeQuery:[%s]", temp));
		return temp;
	}

	/**
	 * Elasticsearch Index Name
	 */
	public static final String ELASTICSEARCH_INDEX_NAME;

	private static String getElasticIndexName() {
		String temp = ConfigurationManager.getAppSetting("elasticsearchIndexName", "monitor-2016.10.12");
		log.info(String.format("elasticsearchIndexName:[%s]", temp));
		return temp;
	}

	/**
	 * Elasticsearch cluster Name
	 */
	public static final String ELASTICSEARCH_RDD_CLUSTER;

	private static String getElasticCluster() {
		String temp = ConfigurationManager.getAppSetting("elasticsearchCluster", "rdd-es-cluster");
		log.info(String.format("elasticsearchCluster:[%s]", temp));
		return temp;
	}

	/**
	 * Elasticsearch result count
	 */
	public static final int ELASTICSEARCH_RESULT_COUNT;

	private static int getElasticResultCount() {
		int temp = StringUtil.parseInt(ConfigurationManager.getAppSetting("elasticsearchResultCount"), 20);
		log.info(String.format("elasticsearchResultCount:[%s]", temp));
		return temp;
	}

	public static final String DEFAULT_DB_URL = "jdbc:mysql://10.47.113.150:3306/%s?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true";
	public static final String DEFAULT_DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DEFAULT_USER_NAME = "root";
	public static final String DEFAULT_DB_PASSWORD = "password";

}
