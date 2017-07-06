package com.cv.kdata.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cv.kdata.cache.LngLatCache;
import com.cv.kdata.model.LngLat;
import com.cv.kdata.model.LocationNews;
import com.cv.kdata.model.OpsMonitorMediaIndex;
import com.cv.kdata.model.RedisMybatisModel;
import com.cv.kdata.model.StaticsInfo;
import com.cv.kdata.response.MediaSyncResponse;

@Service
public class MediaSyncService {

	private static Random randSeed = new Random();
	private static LngLat[] g_english_lngLatArray = new LngLat[4];
	static {
		LngLat beijing = new LngLat(116.42, 39.93);
		LngLat shanghai = new LngLat(121.48, 31.23);
		LngLat guangzhou = new LngLat(121.48, 31.23);
		LngLat shenzhen = new LngLat(121.48, 31.23);
		g_english_lngLatArray[0] = beijing;
		g_english_lngLatArray[1] = shanghai;
		g_english_lngLatArray[2] = guangzhou;
		g_english_lngLatArray[3] = shenzhen;
	}

	public void getStaticInfo(HttpServletRequest req,MediaSyncResponse response){
		//add the redis process
		StaticsInfo staticInfo = RedisMybatisModel.getStaticsInfo();
		response.setResults(staticInfo);

		response.setStatus("success");
		response.setMessage("news result success");
	}

	public List<LocationNews> getNewsByLocation(HttpServletRequest req) throws SQLException {

		String id = req.getParameter("locationNewsId");

		List<OpsMonitorMediaIndex> tmpList = new ArrayList<>();
		List<OpsMonitorMediaIndex> mediaList = new ArrayList<>();
		if("0".equals(id)){
			mediaList = RedisMybatisModel.getMeidaTotal();
		}else{
			int tmpId = Integer.valueOf(id).intValue();
			int index = 0;
			tmpList = RedisMybatisModel.getMeidaList();
//			int len = tmpList.size();
			for(OpsMonitorMediaIndex tmp:tmpList){
				if(tmp.getId() == tmpId){
					break;
				}
				index++;
			}
			if(tmpList != null && tmpList.size()-1 > index){
				mediaList = tmpList.subList(index+1, tmpList.size()-1);
			}
		}

		if(mediaList == null){
			return null;
		}

		List<LocationNews> locationNewsList = new ArrayList<LocationNews>();
		for (OpsMonitorMediaIndex media:mediaList) {
			LocationNews locationNews = new LocationNews();
			locationNews.setId(String.valueOf(media.getId()).toString());
			LngLat lngLat = LngLatCache.getInstance().getRandomLngLat();
			locationNews.setLng(Double.valueOf(lngLat.Lng));
			locationNews.setLat(Double.valueOf(lngLat.Lat));
			locationNews.setMsg(media.getTitle());
			// 临时需求, 如果标题中包含全英文, 则设置位置为随机北上广深
			if (media.getTitle() != null && media.getTitle().matches("[a-zA-Z,;!:\"]+")) {
				int rand = randSeed.nextInt(4);
				lngLat = g_english_lngLatArray[rand];
				locationNews.setLngLat(lngLat);
			}
			locationNewsList.add(locationNews);
		}

		return locationNewsList;
	}

}
