package com.cv.kdata.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.kdata.util.CryptoUtil;

public class LoginInfo {
	private static Logger LOGGER = LoggerFactory.getLogger(LoginInfo.class);
    private Integer id;

    private String uid;

    private String pwd;

    private String cookie;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie == null ? null : cookie.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String decryptPwd() {
    	try {
			return CryptoUtil.aesDecrypt(pwd, CryptoUtil.CryptoKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.toString());
		}
        return pwd;
    }
}