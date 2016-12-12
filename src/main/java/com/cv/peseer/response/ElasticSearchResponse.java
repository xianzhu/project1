package com.cv.peseer.response;

import java.util.List;

import com.cv.peseer.model.Information;


public class ElasticSearchResponse extends ResponseObject{
	private List<Information> informations;

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}
}
