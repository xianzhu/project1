package com.cv.kdata.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class MysqlHelper3 {

	private static Logger LOGGER = LoggerFactory.getLogger(MysqlHelper3.class);

	static {
		LOGGER.info("init database connection ");

		C3p0Plugin plugin = new C3p0Plugin("jdbc:mysql://10.47.113.150:3306/peseer_online?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull",
							"root", "password", "com.mysql.jdbc.Driver");
		plugin.start();

		ActiveRecordPlugin arp = new ActiveRecordPlugin(plugin);
		arp.start();

		LOGGER.info("end database connection ");
	}

	public static void main(String[] args){
		new MysqlHelper3();
		 String sql = "select * from pm_org_info where org_cn_name like ?";
		 String para = "%红杉%";

		 List<Record> results = Db.find(sql,para);

		 System.out.println("total results: " + results.size());
		 }
}
