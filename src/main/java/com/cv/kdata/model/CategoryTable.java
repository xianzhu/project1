package com.cv.kdata.model;

import java.io.Serializable;

public class CategoryTable implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String bizId;

    private String biz;

    private String domain;

    private String media;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz == null ? null : biz.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media == null ? null : media.trim();
    }
}