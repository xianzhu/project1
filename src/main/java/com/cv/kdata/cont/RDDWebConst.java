package com.cv.kdata.cont;

import java.util.HashSet;
import java.util.Set;

public class RDDWebConst {
	public static final String PESEER_DB_ONLINE = "peseer_online";
	public static final String PESEER_RPT_CHOICE = "peseer_rpt_choice";
	public static final String PESEER_LOGIN = "peseer_login";
	public static final String ops_rdd = "ops_rdd";
	public static final String datasource = "data_source";
	public static final String qxb = "qxb";

	public static final String ADD = "1";
	public static final String DELETE = "2";
	public static final String UPDATE = "3";

	public static final Set<String> capital_key_set = new HashSet<>();
	static {
		capital_key_set.add("投资");
		capital_key_set.add("并购");
		capital_key_set.add("退出");
	}

	public static final String TOPICSERVICEIP = "10.27.70.43";

	//返回类型
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String TIMEOUT = "timeout";
	public static final int INVALID_VALUE = -1;

	public static final String TOKEN = "cookie_code";
	public static final String USERNAME = "user_name";

	//tracking
	public static final String KAFKA_HOST = "10.27.224.63:9192";
	public static final String TRACKTOPIC = "tracking";
}
