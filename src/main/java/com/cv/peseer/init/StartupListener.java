package com.cv.peseer.init;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.jfinal.log.LogManager;

@Component
public class StartupListener implements ApplicationContextAware, ServletContextAware,
        InitializingBean, ApplicationListener<ContextRefreshedEvent> {
 
    //protected Logger logger = LogManager.getLogger();
 
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        System.out.println("1 => StartupListener.serApplicationContext");
    }
 
    @Override
    public void setServletContext(ServletContext context) {
    	System.out.println("2 => StartupListener.setServletContext");
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
    	System.out.println("3 => StartupListener.afterPropertiesSet");
    }
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
    	System.out.println("4.1 => MyApplicationListener.onApplicationEvent");
        if (evt.getApplicationContext().getParent() == null) {
        	System.out.println("4.2 => MyApplicationListener.onApplicationEvent");
        }
    }
 
}