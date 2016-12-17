package com.cv.kdata.conf;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationManager {
	   public static final String FILE_NAME = "web.config";
	    public static final String APPSETTINGS_PREFIX = "appSettings.";

	    private static XmlConfiguration cfg;

	    private static Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

	    private static String configPath = null;

	    private ConfigurationManager()
	    {
	    }

	    public static void initInstance(String path)
	    {
	        if(cfg == null)
	        {
	            synchronized(ConfigurationManager.class)
	            {
	                if(cfg == null)
	                {
	                	configPath = path;
	                    cfg = new XmlConfiguration();
	                    cfg.load(configPath);
	                }
	            }
	        }
	    }

	    public static void freeInstance()
	    {
	        if(cfg != null)
	        {
	            synchronized(ConfigurationManager.class)
	            {
	                if(cfg != null)
	                {
	                    cfg.unload();
	                    cfg = null;
	                }
	            }
	        }
	    }

	    public static String getSetting(String name, String defaultValue, boolean throwException)
	    {
	    	if(cfg == null)
	        {
				throw new ConfigurationException("Please initialize ConfigurationManager.");
	        }
	    	
	        if(!cfg.isValid())
	        {
	            cfg.load(configPath);
	        }
	        String value = defaultValue;
	        ConfigurationNode node = cfg.getNode(name);
	        if(node == null)
	        {
	            if(throwException)
	            {
	                throw new ConfigurationException("configuration node not found.");
	            }
	        }
	        else
	        {
	            value = node.getValue();
	        }
	        return value;
	    }

	    public static String getSetting(String name, String defaultValue)
	    {
	        return getSetting(name, defaultValue, false);
	    }

	    /**
	     * @throws ConfigurationException
	     */
	    public static String getSetting(String name)
	    {
	        return getSetting(name, null, true);
	    }

	    /**
	     * @param name should be other than null or empty
	     * @exception IllegalArgumentException
	     */
	    public static String getAppSetting(String name, String defaultValue)
	    {
	        if(StringUtils.isEmpty(name))
	        {
	            throw new IllegalArgumentException("argument 'name' is empty");
	        }
	        return getSetting(APPSETTINGS_PREFIX + name, defaultValue, false);
	    }

	    /**
	     * @exception IllegalArgumentException
	     */
	    public static String getAppSetting(String name)
	    {
	        if(name == null || name.trim() == "")
	        {
	            throw new IllegalArgumentException("argument 'name' is empty");
	        }
	        return getSetting(APPSETTINGS_PREFIX + name, null, false);
	    }

	    public static Pair<String, String>[] getSettings(String name)
	    {
	        if(name == null || name.trim() == "")
	        {
	            throw new IllegalArgumentException("argument 'name' is empty");
	        }
	    	if(cfg == null)
	        {
				throw new ConfigurationException("Please initialize ConfigurationManager.");
	        }
	        if(!cfg.isValid())
	        {
	            cfg.load(configPath);
	        }
	        return cfg.getNodePairs(name);
	    }
	    
	    public static HashMap<String, HashMap<String, String>> getEntityValues(String name)
	    {
	        if(name == null || name.trim() == "")
	        {
	            throw new IllegalArgumentException("argument 'name' is empty");
	        }
	    	if(cfg == null)
	        {
				throw new ConfigurationException("Please initialize ConfigurationManager.");
	        }
	        if(!cfg.isValid())
	        {
	            cfg.load(configPath);
	        }
	        return cfg.getNodeValues(name);
	    }
}
