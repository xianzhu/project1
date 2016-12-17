package com.cv.kdata.init;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.cache.OrgInfoCache;
import com.cv.kdata.conf.ConfigurationHelper;
import com.cv.kdata.conf.ConfigurationManager;
import com.cv.kdata.util.ConstElasticClient;
import com.cv.kdata.util.LogWriterHelper;
import com.cv.kdata.util.MysqlHelper;
import com.cv.kdata.util.MysqlHelper3;
import com.cv.kdata.util.StockFeatureAllScaledDataHelper;
import com.cv.kdata.util.StockFeatureParameterDataHelper;


/**
 * Application Lifecycle Listener implementation class RDDWebListenr
 *
 */
@WebListener("initial")
public class RDDWebListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(RDDWebListener.class);
	
	static volatile ServletContext servletContext;
	static protected MysqlHelper3 localForInit = new MysqlHelper3();
	
    /**
     * Default constructor. 
     */
    public RDDWebListener() {}
    
    public static ServletContext getServletContext()
    {
        return servletContext;
    }


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		MysqlHelper.closeAll();
	}

	//创建日志目录
	private void createLogFolder(){
		String access_folder=String.format("%s/access", ConfigurationHelper.LOG_ROOT_FOLDER);
		String search_folder=String.format("%s/search", ConfigurationHelper.LOG_ROOT_FOLDER);
		
		File access_folder_file = new File(access_folder);
		File search_folder_file = new File(search_folder);
		
		if (!access_folder_file.exists()){
			access_folder_file.mkdirs();
		}
		
		if (!search_folder_file.exists()){
			search_folder_file.mkdirs();
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		
    	logger.info("tradingos WebApplication Starting...");

        final String webApplicationPath = getServletContext().getRealPath("/");
        ConfigurationManager.initInstance(webApplicationPath
                + "/WEB-INF/web.config");
		
		//初始化elastic search client
		ConstElasticClient.initClient();
		
		StockFeatureAllScaledDataHelper.getInstance();
		StockFeatureParameterDataHelper.getInstance();

		OrgInfoCache.getInstance();

		
		//创建日志目录
		createLogFolder();
		LogWriterHelper.startConsume();
	}
	
}
