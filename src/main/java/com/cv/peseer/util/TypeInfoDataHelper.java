package com.cv.peseer.util;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.model.TypeInfo;



public class TypeInfoDataHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(TypeInfoDataHelper.class);

	private static final HashMap<String, TypeInfo> mediaCategory2TypeInfo = new HashMap<>(); // 行业分类对应类型信息
	private static final HashMap<String, HashSet<String>> industryId2CatNoSet = new HashMap<>(); // 行业分类id对应媒体分类Id

	// 一级分类对应二级分类list
	private static final HashMap<String, Collection<String>> firstType2SecTypeList = new HashMap<>();
	// 一级分类对应媒体分类list
	private static final HashMap<String, Collection<String>> firstType2MediaCatNoList = new HashMap<>();
	// 一级分类对应媒体分类list
	private static final HashMap<String, String> catNo2IndustryNameMap = new HashMap<>();

	public static void load() {
		String sql = "select cat_no, industry_id, industry_name from cfg_media_industry_mapping";
		ResultSet rs = null;
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.WEB_DB_NAME).getResultSet(sql);
			HashSet<String> first_industry_id_set = new HashSet<>();
			HashSet<String> sec_industry_id_set = new HashSet<>();
			HashSet<String> third_industry_id_set = new HashSet<>();
			HashSet<String> forth_industry_id_set = new HashSet<>();
			HashSet<String> fifth_industry_id_set = new HashSet<>();
			HashSet<String> sixth_industry_id_set = new HashSet<>();

			HashSet<String> first_media_cat_no_set = new HashSet<>();
			HashSet<String> sec_media_cat_no_set = new HashSet<>();
			HashSet<String> third_media_cat_no_set = new HashSet<>();
			HashSet<String> forth_media_cat_no_set = new HashSet<>();
			HashSet<String> fifth_media_cat_no_set = new HashSet<>();
			HashSet<String> sixth_media_cat_no_set = new HashSet<>();

			while (rs.next()) {
				String cat_no = rs.getString("cat_no");
				Integer industry_id = rs.getInt("industry_id");
				String industry_name = rs.getString("industry_name");
				/*
				 * 1. 行业资讯：对应cfg_event.module_type =1，cfg_event.event_type(或者
				 * cfg_industry.fst_id)=1001，cfg_industry.sec_id在 1001001 ~
				 * 1001037 范围内； 2. 全球资讯：cfg_industry.sec_id 为 1001038, 1001044
				 * 3. 政策与数据：cfg_industry.sec_id 为 1001042, 1001045； 4. 新三板：
				 * cfg_industry.sec_id 为 1001043； 5. 改革创新：cfg_industry.sec_id 为
				 * 1001040, 1001041；
				 */

				
				TypeInfo typeInfo = null;
				if (industry_id >= 1001001 && industry_id <= 1001037) {
					// String type_id, String type_name, String industry_id,
					// String industry_name
					typeInfo = new TypeInfo("1", "行业资讯", industry_id.toString(), industry_name);
					first_industry_id_set.add(industry_id.toString());
					first_media_cat_no_set.add(cat_no);
				} else if (1001038 == industry_id || 1001044 == industry_id) {
					typeInfo = new TypeInfo("2", "全球资讯", industry_id.toString(), industry_name);
					sec_industry_id_set.add(industry_id.toString());
					sec_media_cat_no_set.add(cat_no);
				} else if (1001042 == industry_id || 1001045 == industry_id) {
					typeInfo = new TypeInfo("3", "政策与数据", industry_id.toString(), industry_name);
					third_industry_id_set.add(industry_id.toString());
					third_media_cat_no_set.add(cat_no);
				} else if (1001043 == industry_id) {
					typeInfo = new TypeInfo("4", "新三板", industry_id.toString(), industry_name);
					forth_industry_id_set.add(industry_id.toString());
					forth_media_cat_no_set.add(cat_no);
				} else if (1001040 == industry_id || 1001041 == industry_id) {
					typeInfo = new TypeInfo("5", "改革创新", industry_id.toString(), industry_name);
					fifth_industry_id_set.add(industry_id.toString());
					fifth_media_cat_no_set.add(cat_no);
				} else if (1001039 == industry_id) {
					typeInfo = new TypeInfo("6", "资本事件", industry_id.toString(), industry_name);
					sixth_industry_id_set.add(industry_id.toString());
					sixth_media_cat_no_set.add(cat_no);
				} else {
					LOGGER.error(String.format("not define industry id:%d", industry_id));
				}

				if (typeInfo != null) {
					mediaCategory2TypeInfo.put(cat_no, typeInfo);
				}
				
				catNo2IndustryNameMap.put(cat_no, industry_name);
				
				HashSet<String> cat_no_set = industryId2CatNoSet.get(industry_id.toString());
				if (cat_no_set==null){
					cat_no_set = new HashSet<>();
					industryId2CatNoSet.put(industry_id.toString(), cat_no_set);
				}
				cat_no_set.add(cat_no);
			}

			firstType2SecTypeList.put("1", first_industry_id_set);
			firstType2SecTypeList.put("2", sec_industry_id_set);
			firstType2SecTypeList.put("3", third_industry_id_set);
			firstType2SecTypeList.put("4", forth_industry_id_set);
			firstType2SecTypeList.put("5", fifth_industry_id_set);
			firstType2SecTypeList.put("6", sixth_industry_id_set);

			firstType2MediaCatNoList.put("1", first_media_cat_no_set);
			firstType2MediaCatNoList.put("2", sec_media_cat_no_set);
			firstType2MediaCatNoList.put("3", third_media_cat_no_set);
			firstType2MediaCatNoList.put("4", forth_media_cat_no_set);
			firstType2MediaCatNoList.put("5", fifth_media_cat_no_set);
			firstType2MediaCatNoList.put("6", sixth_media_cat_no_set);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(sql, ex);
		} finally {
			MysqlHelper.getInstance(RDDWebConst.WEB_DB_NAME).close(rs);
		}
	}

	public static TypeInfo getTypeInfoWithCategoryNo(String cat_no) {
		return mediaCategory2TypeInfo.get(cat_no);
	}

	public static HashSet<String> getCatNoWithIndustryId(String industry_id) {
		return industryId2CatNoSet.get(industry_id);
	}

	// 得到行业分类集合
	public static Set<String> getIndustryIdSet() {
		return industryId2CatNoSet.keySet();
	}

	// 根据一级分类得到行业id 集合
	public static Collection<String> getIndustryIdSet(String first_type) {
		return firstType2SecTypeList.get(first_type);
	}

	// 根据一级分类得到行业id 集合
	public static Collection<String> getMediaCatNoSet(String first_type) {
		return firstType2MediaCatNoList.get(first_type);
	}
	
	//根据cat_no得到industry_name
	public static Collection<String> getIndustryName(Collection<String> cat_no_set) {
		HashSet<String> result_set = new HashSet<>();
		if (cat_no_set.isEmpty()){
			return result_set;
		}
		
		for (String cat_no:cat_no_set) {
			 String industry_name = catNo2IndustryNameMap.get(cat_no);
			 if (industry_name!=null){
				 result_set.add(industry_name);
			 }
		}
		
		return result_set;
	}
}
