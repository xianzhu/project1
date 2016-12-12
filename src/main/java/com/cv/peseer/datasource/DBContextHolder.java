package com.cv.peseer.datasource;

public class DBContextHolder {    
	  
    /**  
     * 线程threadlocal  
     */    
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();    
  
    public static  String PESEER_ONLINE = "peseerOnline";    
    public static  String PESEER_LOGIN = "peseerLogin";
    public static  String PESEER_RPT = "peseerRpt";  
  
    public static String getDbType() {    
        String db = contextHolder.get();    
        if (db == null) {    
            db = PESEER_ONLINE;// 默认是读写库    
        }    
        return db;    
    }    
    public static void setDbType(String str) {    
        contextHolder.set(str);    
    }    
    public static void clearDBType() {    
        contextHolder.remove();    
    }    
} 
