package com.cv.kdata.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.model.PMOrgInfo;
import com.cv.kdata.model.PMUserInfo;
import com.cv.kdata.response.GeneralResponse;
import com.cv.kdata.response.TopSearchResponse;
import com.cv.kdata.util.BeanConverter;
import com.cv.kdata.util.CollectionUtil;
import com.cv.kdata.util.ExtendInfoUtil;
import com.cv.kdata.util.MysqlHelper;
import com.cv.kdata.util.StringUtil;
import com.cv.kdata.util.WordSegmentUtil;
import com.kdata.defined.model.RptSchedule;

@Service
public class TopSearchService {
	/**
	 * 对应精准搜索
	 */
	public final static int PEOPLE_MAX_DISP_LENGTH = 32;
	public final static int ORG_MAX_DISP_LENGTH = 40;

	private static final Logger logger = LoggerFactory.getLogger(TopSearchService.class);

	public void getBasicSearchInfo(HttpServletRequest req, TopSearchResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		List<PMUserInfo> userList = new ArrayList<>();
		List<PMOrgInfo> orgList = new ArrayList<>();

		String key = req.getParameter("key");
		if (searchUserInfo(key, userList, response) && searchOrgInfo(key, orgList, response)) {
			response.setUserBasicList(userList);
			response.setOrgBasicList(orgList);
		}
	}

	public void basicSearch(HttpServletRequest req, TopSearchResponse response) {
		// System.out.println(new Date() + "enter ExtendItemTask call");

		List<PMUserInfo> userList = new ArrayList<>();
		List<PMOrgInfo> orgList = new ArrayList<>();

		String key = req.getParameter("key");

		List<String> wordList2 = new ArrayList<>();
		wordList2.add(key);
		HashSet<String> key_set = new HashSet<>(wordList2);

		if(queryOrgAndPersonInfo(key_set, key_set, userList, orgList) == false
				|| (userList.isEmpty() && orgList.isEmpty())) {

			// 调用LTP分词服务

			List<String> wordList = WordSegmentUtil.getWordSegment(key);
//			List<WordWeight> wordWeightList = DistanceUtil.getExceptionWordWeight(wordList);
//			wordList.clear();
//			wordWeightList.forEach(o -> wordList.add(o.word));
			HashSet<String> key_set1 = new HashSet<>(wordList);
			if(queryOrgAndPersonInfo(key_set1, key_set1, userList, orgList)){
				response.setOrgBasicList(orgList);
				response.setUserBasicList(userList);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("Get basic info success!");
			}else{
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("Get basic info failed!");
			}
		}else{
			response.setOrgBasicList(orgList);
			response.setUserBasicList(userList);
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get basic info success!");
		}
	}

	public void extendSearch(HttpServletRequest req, TopSearchResponse response) {
		// System.out.println(new Date() + "enter ExtendItemTask call");

		List<PMUserInfo> userList = new ArrayList<>();
		List<PMOrgInfo> orgList = new ArrayList<>();

		String key = req.getParameter("key");
		// extend
		long start = System.currentTimeMillis();
		HashSet<String> extend_word_list = ExtendInfoUtil.getAllWord(key);
		long end = System.currentTimeMillis();
		System.out.println("get all word cost time:" + (end - start));
		extend_word_list.remove(key);

		HashSet<String> key_set = new HashSet<>(extend_word_list);

		if (queryOrgAndPersonInfo(key_set, key_set, userList, orgList)) {
			response.setUserExtendList(userList);
			response.setOrgExtendList(orgList);

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get extend info success!");
		} else {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get extend info failed!");
		}

	}

	public boolean searchUserInfo(String key, List<PMUserInfo> userList, TopSearchResponse response) {

		if (StringUtil.isNullOrEmpty(key)) {
			return false;
		}
		if (userList == null) {
			userList = new ArrayList<>();
		}

		String sql = "select organize_name, user_id, user_name, title from pm_user_info where user_name = ? limit 10";
		ResultSet rs = null;
		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, key);
			// ps.setString(2, "%" + key + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				PMUserInfo user = (PMUserInfo) BeanConverter.convert(rs, PMUserInfo.class);
				userList.add(user);
			}

		} catch (Exception ex) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("get invest list info failed!");
			return false;
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		return true;
	}

	public boolean searchOrgInfo(String key, List<PMOrgInfo> orgList, TopSearchResponse response) {
		if (StringUtil.isNullOrEmpty(key)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("key is null!");
			return false;
		}

		if (orgList == null) {
			orgList = new ArrayList<>();
		}
		String sql = "select org_id, org_cn_name from pm_org_info where org_cn_name=? || org_cn_short = ? ||org_nickname = ? limit 10";
		ResultSet rs = null;
		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, key);
			ps.setString(3, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				PMOrgInfo org = (PMOrgInfo) BeanConverter.convert(rs, PMOrgInfo.class);
				orgList.add(org);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("Get ent organize basic info success!");

		} catch (Exception ex) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("get stock equity control failed!");
			return false;
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
		return true;
	}

	public static boolean queryOrgAndPersonInfo(HashSet<String> person_key_set, HashSet<String> organize_key_set,
			List<PMUserInfo> userList, List<PMOrgInfo> orgList) {

		if (userList == null) {
			userList = new ArrayList<>();
		}
		if (orgList == null) {
			orgList = new ArrayList<>();
		}

		HashSet<Integer> orgIdSet = new HashSet<>();
		// 查询人物
		ResultSet rs = null;
		if (person_key_set.size() > 0) {
			String where = CollectionUtil.toStringWithSingleComma(person_key_set, true, ",");

			String sql = String.format(
					"select user_id, user_name, organize_name, organize_id from pm_user_info where user_name in (%s)", where);

			try {
				rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getResultSet(sql);
				while (rs.next()) {
					PMUserInfo user = (PMUserInfo) BeanConverter.convert(rs, PMUserInfo.class);
					userList.add(user);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			} finally {
				MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
			}
		}

		if (organize_key_set.size() > 0 || orgIdSet.size() > 0) {
			try {
				StringBuilder sb = new StringBuilder();
				for (String org_name : organize_key_set) {
					sb.append(String.format(
							"(org_cn_name='%s' || org_cn_short='%s' || org_nickname='%s' || org_nickname like  '%s %%' || org_nickname like '%% %s' || org_nickname like '%% %s %%') || ",
							org_name, org_name, org_name, org_name, org_name, org_name));
				}

				for (Integer orgId : orgIdSet) {
					sb.append(String.format("(org_id=%d) || ", orgId));
				}

				if (sb.length() > 0) {
					sb.delete(sb.length() - 4, sb.length());
				}
				rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE)
						.getResultSet(String.format(
								"select org_id,org_cn_name,left(org_cn_desc, %d) as description_sub from pm_org_info where %s",
								ORG_MAX_DISP_LENGTH, sb.toString()));
				while (rs.next()) {
					PMOrgInfo org = (PMOrgInfo) BeanConverter.convert(rs, PMOrgInfo.class);
					orgList.add(org);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			} finally {
				MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
			}
		}

		return true;
	}

	public void getDailySchedule(HttpServletRequest req, GeneralResponse response){

		String date = req.getParameter("date");
		String to = req.getParameter("to");
		int from = StringUtil.parseInt(req.getParameter("from"), 0);
		int count = StringUtil.parseInt(req.getParameter("count"), 40);

		List<RptSchedule> objectList = new ArrayList<>();
		List<Object> para = new ArrayList<>();

    	String sql = "select * from rpt_schedule where time > ? ";
		para.add(date);

		if(!StringUtil.isNullOrEmpty(to)){
			sql = sql + " and time < ?";
			para.add(to);
		}

		sql = sql + " order by time limit ?,? ";
		para.add(from);
		para.add(count);

		ResultSet rs = null;
		try {

			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, para);

			while (rs.next()) {
				RptSchedule record = (RptSchedule) BeanConverter.convert(rs,
						RptSchedule.class);
				if (record != null) {
					objectList.add(record);
				}
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("get daily schedule successful!");
			response.setObject(objectList);
		} catch (Exception e) {
			logger.error(e.toString());
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("get daily schedule failed!");
		}finally{
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}
}
