package com.cv.peseer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class MysqlHelper3 {

	private static Logger LOGGER = LoggerFactory.getLogger(MysqlHelper3.class);

	static {
		LOGGER.info("init database connection ");

		C3p0Plugin plugin = new C3p0Plugin("jdbc:mysql://10.47.113.150:3306/peseer_online","root", "password", "com.mysql.jdbc.Driver");
		plugin.start();

		ActiveRecordPlugin arp = new ActiveRecordPlugin(plugin);
		arp.start();

		LOGGER.info("end database connection ");
	}
}
