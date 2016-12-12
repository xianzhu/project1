package com.cv.peseer.response;

import java.util.List;

import com.cv.peseer.model.LocationNews;
import com.cv.peseer.model.StaticsInfo;

public class MediaSyncResponse extends ResponseObject {
	private StaticsInfo results; //返回统计信息
	private List<LocationNews> news; //返回新闻地理信息

	public List<LocationNews> getNews() {
		return news;
	}

	public void setNews(List<LocationNews> news) {
		this.news = news;
	}

	public StaticsInfo getResults() {
		return results;
	}

	public void setResults(StaticsInfo results) {
		this.results = results;
	}
	
}
