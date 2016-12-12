package com.cv.peseer.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.cv.peseer.model.CiChnFilmBox;
import com.cv.peseer.model.CiCorePayShare;
import com.cv.peseer.model.CiCptocSca;
import com.cv.peseer.model.CiDtourIncome;
import com.cv.peseer.model.CiOlVideoIncm;


public class CiResponse extends ResponseObject {
	@JSONField(ordinal = 3)
	private List<CiCptocSca> cprocSca; // 互联网金融-网络众筹-众筹市场规模
	@JSONField(ordinal = 4)
	private List<CiChnFilmBox> chnFilmBox; // 服务消费—全国电影票房收入
	@JSONField(ordinal = 5)
	private List<CiCorePayShare> corePayShare; // 互联网金融-第三方支付-核心支付企业市场份额
	@JSONField(ordinal = 6)
	private List<CiDtourIncome> dtourIncome; // 服务消费-国内旅游收入
	@JSONField(ordinal = 7)
	private List<CiOlVideoIncm> videoIncom; // 服务消费—在线视频收入

	public List<CiCptocSca> getCprocSca() {
		return cprocSca;
	}

	public void setCprocSca(List<CiCptocSca> cprocSca) {
		this.cprocSca = cprocSca;
	}

	public List<CiChnFilmBox> getChnFilmBox() {
		return chnFilmBox;
	}

	public void setChnFilmBox(List<CiChnFilmBox> chnFilmBox) {
		this.chnFilmBox = chnFilmBox;
	}

	public List<CiCorePayShare> getCorePayShare() {
		return corePayShare;
	}

	public void setCorePayShare(List<CiCorePayShare> corePayShare) {
		this.corePayShare = corePayShare;
	}

	public List<CiDtourIncome> getDtourIncome() {
		return dtourIncome;
	}

	public void setDtourIncome(List<CiDtourIncome> dtourIncome) {
		this.dtourIncome = dtourIncome;
	}

	public List<CiOlVideoIncm> getVideoIncom() {
		return videoIncom;
	}

	public void setVideoIncom(List<CiOlVideoIncm> videoIncom) {
		this.videoIncom = videoIncom;
	}

}