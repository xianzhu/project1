package com.cv.kdata.util;

public class ConstElasticClient {
	private static ElasticSearchClient esClient;
	public static void initClient(){
		if(esClient == null){
			esClient = new ElasticSearchClient();
		}
	}
	public static ElasticSearchClient getElasticSeachClient(){
		initClient();
		return esClient;
	}
}
