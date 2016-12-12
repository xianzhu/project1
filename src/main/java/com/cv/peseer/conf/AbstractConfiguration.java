package com.cv.peseer.conf;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cv.peseer.init.RDDWebListener;



public abstract class AbstractConfiguration implements Configuration
{

    private static Log log = LogFactory.getLog(AbstractConfiguration.class);

    protected AbstractConfiguration()
    {
    }

    public final String findRealPath(String path)
    {
        String realPath = path;
        ServletContext ctx = RDDWebListener.getServletContext();
        if(ctx != null)
        { //If it's a web application
            if(path.startsWith("/") || path.startsWith("~"))
            {
                realPath = ctx.getRealPath(path);
            }
            else
            {
                String path0 = path;
                path = path.replace("\\", "/");
                int pos = 0;
                int posSlash = 0, posColon = 0;
                pos = path.indexOf("/");
                pos = (pos == -1) ? path.length() : pos;
                posSlash = pos;
                pos = path.indexOf(":");
                pos = (pos == -1) ? path.length() : pos;
                posColon = pos;
                if(posColon >= posSlash)
                {
                    realPath = ctx.getRealPath(path0);
                }
            }
        }
        else
        { //If it's NOT a web application
            ClassLoader loader = this.getClass().getClassLoader();
            if(loader != null)
            {
                URL url = loader.getResource(path);
                if(url != null)
                {
                    realPath = url.getPath();
                }
            }
        }
        return realPath;
    }

    /**
     * 
     * @param path		
     * @param isDefault	
     * @return
     */
    public boolean load(String path)
    {
    	if(StringUtils.isEmpty(path))
    	{
    		return false;
    	}
    	
        if(isValid())
        {
            unload();
        }
        InputStream is = null;
        try
        {
        	is = new FileInputStream(path);
            return load(is);
        }
        catch(FileNotFoundException e)
        {
            log.error("AbstractConfiguration load method got FileNotFoundException.");
            log.error(e.getMessage());
            return false;
        }
        catch(Exception e)
        {
            log.error("AbstractConfiguration load method got Exception.");
            log.error(e.getMessage());
            return false;
        }
        finally
        {
            if(is != null)
            {
                try
                {
                    is.close();
                }
                catch(IOException e)
                {
                    log.error("AbstractConfiguration load method got IOException.");
                    log.error(e.getMessage());
                }
                is = null;
            }
        }
    }

}
