package com.cv.kdata.response;

import java.util.List;

import com.cv.kdata.model.Information;


public class ElasticSearchResponse extends ResponseObject{
	private List<Information> informations;

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}
}
