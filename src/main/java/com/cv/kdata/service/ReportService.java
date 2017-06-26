package com.cv.kdata.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.UdfRptCVMapper;
import com.cv.kdata.dao.UdfRptTraderMapper;
import com.cv.kdata.datasource.DBContextHolder;
import com.cv.kdata.facade.ReportInfo;
import com.cv.kdata.model.UdfRptCV;
import com.cv.kdata.model.UdfRptTrader;
import com.cv.kdata.response.ReportResponse;
import com.cv.kdata.response.ResponseObject;
import com.cv.kdata.util.JschUtil;
import com.cv.kdata.util.MysqlHelper;
import com.cv.kdata.util.StringUtil;
@Service
public class ReportService {
	@Autowired
	UdfRptTraderMapper udfRptTraderMapper;
	@Autowired
	UdfRptCVMapper udfRptCVMapper;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
	private static final HashMap<String, String> time2Internal = new HashMap<>(); // 时间串对应间隔天数

	private static JschUtil sftp = new JschUtil();

	static {
		time2Internal.put("2", "30"); // 月
		time2Internal.put("3", "90"); // 季度
		time2Internal.put("4", "180"); // 半年
		time2Internal.put("5", "365"); // 年

		sftp.setUsername("appuser");
        sftp.setPassword("1QAZ3edc2WSX");
        sftp.setHost("10.27.224.63");
        sftp.setPort(22);
	}

	public List<UdfRptCV> getRptCV(String token) {
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfRptCV> itemList = udfRptCVMapper.getCVListFromUUid(token);
		return itemList;
	}

	public List<UdfRptTrader> getRptTrader(String token) {

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfRptTrader> itemList = udfRptTraderMapper.getTraderListFromUUid(token);
		return itemList;
	}


	// trader_report对应研究报告，cv_report对应行业分析
	// 这里返回个人定制的研究报告
	public List<ReportInfo> getTraderListByPersonal(String key, String token, int from, int count,
			ReportResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return null;
		}
		List<ReportInfo> research_list = new ArrayList<>();

		// HashSet<String> conditionSet = getConditionSet(token, 2);// 返回研究报告条件集
		List<UdfRptTrader> conditionSet = this.getRptTrader(token);

		String sql = null;

		if (!conditionSet.isEmpty() && !StringUtil.isNullOrEmpty(traderCondition(conditionSet, true, " or "))) {
			sql = String.format(
					"select * from rpt_trader where %s and title like ? order by report_date desc limit ?,?",
					traderCondition(conditionSet, true, " or "));
		} else {
			sql = "select * from rpt_trader where title like ? order by report_date desc limit ?,?";
		}

		ResultSet rs = null;
		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setInt(2, from);
			ps.setInt(3, count);

			LOGGER.info("execute the sql: " + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportInfo reportInfo = new ReportInfo();
				reportInfo.id = rs.getString("id");
				reportInfo.time = rs.getDate("report_date").toString();
				reportInfo.author = rs.getString("author_list");
				reportInfo.industry_id = rs.getString("type");
				// reportInfo.industry_type = rs.getString("industry_type");
				reportInfo.url = String.format("trader_rpt?id=%s", reportInfo.id);
				reportInfo.report_name = rs.getString("title");
				research_list.add(reportInfo);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("search report OK");
			response.setTrader_report_list(research_list);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(sql, ex);
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("获取行业分析报告失败");
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		return research_list;
	}

	// trader_report对应研究报告，cv_report对应行业分析
	// 这里返回研究报告
	public List<ReportInfo> getTraderList(String key, int from, int count, String filter, ReportResponse response) {
		List<ReportInfo> research_list = new ArrayList<>();
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return null;
		}

		String sql = "select * from rpt_trader where title like ? order by report_date desc limit ?,?";
		if (!StringUtil.isNullOrEmpty(filter)) {
			sql = String.format(
					"select * from rpt_trader where type in (%s) and title like ? order by report_date desc limit ?,?",
					filter);
		}

		ResultSet rs = null;

		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setInt(2, from);
			ps.setInt(3, count);

			LOGGER.info("execute the sql: " + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportInfo reportInfo = new ReportInfo();
				reportInfo.id = rs.getString("id");
				reportInfo.time = rs.getDate("report_date").toString();
				reportInfo.author = rs.getString("author_list");
				reportInfo.report_name = rs.getString("title");
				// String rpt_type = rs.getString("rpt_type");
				reportInfo.url = String.format("trader_rpt?id=%s", reportInfo.id);
				// reportInfo.rpt_type = "trader_rpt";
				reportInfo.industry_id = rs.getString("type");
				// reportInfo.industry_type = rs.getString("industry_type");
				research_list.add(reportInfo);
			}

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("search report OK");
			response.setTrader_report_list(research_list);

		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(sql, ex);
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("获取研究报告失败");
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		return research_list;
	}

	// trader_report对应研究报告，cv_report对应行业分析
	// 这里返回个人定制的行业分析
	public List<ReportInfo> getCVListByPersonal(String key, String token, int from, int count,
			ReportResponse response) {
		List<ReportInfo> research_list = new ArrayList<>();
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return null;
		}
		// HashSet<String> conditionSet = getConditionSet(token, 1);// 返回行业分析条件集
		List<UdfRptCV> conditionSet = this.getRptCV(token);

		String sql = null;

		if (!conditionSet.isEmpty() && !StringUtil.isNullOrEmpty(cvCondition(conditionSet, true, " or "))) {
			sql = String.format(
					"select * from rpt_cv where %s and report_name like ? order by pub_time desc limit ?,?",
					cvCondition(conditionSet, true, " or "));
		} else {
			sql = "select * from rpt_cv where report_name like ? order by pub_time desc limit ?,?";
		}

		ResultSet rs = null;
		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setInt(2, from);
			ps.setInt(3, count);

			LOGGER.info("execute the sql: " + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportInfo reportInfo = new ReportInfo();
				reportInfo.id = rs.getString("rid");
				reportInfo.time = rs.getDate("pub_time").toString();
				reportInfo.industry_id = rs.getString("industry_id");
				reportInfo.industry_type = rs.getString("industry_type");
				reportInfo.url = String.format("cv_rpt?id=%s", reportInfo.id);
				reportInfo.report_name = rs.getString("report_name");
				research_list.add(reportInfo);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("search report OK");
			response.setCv_report_list(research_list);
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(sql, ex);
			response.setStatus("failure");
			response.setMessage("获取行业分析报告失败");
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		return research_list;
	}

	// trader_report对应研究报告，cv_report对应行业分析
	// 这里返回行业分析报告
	public List<ReportInfo> getCVList(String key, int from, int count, String filter, ReportResponse response) {
		List<ReportInfo> research_list = new ArrayList<>();
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return null;
		}

		String sql = "select * from rpt_cv where report_name like ? order by pub_time desc limit ?,?;";
		if (!StringUtil.isNullOrEmpty(filter)) {
			sql = String.format(
					"select * from rpt_cv where industry_id in (%s) and report_name like ? order by pub_time desc limit ?,?;",
					filter);
		}
		ResultSet rs = null;

		try {
			PreparedStatement ps = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).getConn()
					.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setInt(2, from);
			ps.setInt(3, count);

			LOGGER.info("execute the sql: " + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportInfo reportInfo = new ReportInfo();
				reportInfo.id = rs.getString("rid");
				reportInfo.time = rs.getDate("pub_time").toString();
				reportInfo.report_name = rs.getString("report_name");
				// String rpt_type = rs.getString("rpt_type");
				reportInfo.url = String.format("cv_rpt?id=%s", reportInfo.id);
				// reportInfo.rpt_type = "trader_rpt";
				reportInfo.industry_id = rs.getString("industry_id");
				reportInfo.industry_type = rs.getString("industry_type");
				research_list.add(reportInfo);
			}
			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("search report OK");
			response.setCv_report_list(research_list);

		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(sql, ex);
			response.setStatus("failure");
			response.setMessage("获取行业分析报告失败");
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}

		return research_list;
	}

	public static String traderCondition(List<UdfRptTrader> valueSet, boolean drop_empty_object, String separator) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder();

		for (UdfRptTrader value : valueSet) {
			if (!StringUtil.isNullOrEmpty(value.getCondition())) {
				sBuilder.append(value.getCondition()).append(separator);
			}
		}

		if (!StringUtil.isNullOrWhitespace(sBuilder.toString())) {
			sBuilder.insert(0, "(");
			sBuilder.delete(sBuilder.length() - separator.length(), sBuilder.length()).append(")");
		}

		return sBuilder.toString();
	}

	public static String cvCondition(List<UdfRptCV> valueSet, boolean drop_empty_object, String separator) {
		if (valueSet.isEmpty())
			return "";

		StringBuilder sBuilder = new StringBuilder();

		for (UdfRptCV value : valueSet) {
			if (!StringUtil.isNullOrEmpty(value.getCondition())) {
				sBuilder.append(value.getCondition()).append(separator);
			}
		}

		if (!StringUtil.isNullOrWhitespace(sBuilder.toString())) {
			sBuilder.insert(0, "(");
			sBuilder.delete(sBuilder.length() - separator.length(), sBuilder.length()).append(")");
		}
		return sBuilder.toString();
	}

	public void getCVReport(HttpServletRequest req, HttpServletResponse resp,ResponseObject response){

		String id = req.getParameter("id");

		if (StringUtils.isEmpty(id)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get CV report failed!");
			return;
		}

		String root_folder = "/home/appuser/workdir/report";
		ResultSet rs = null;
		String sql = "select report_path from rpt_cv where rid=?";
		Object[] parameters = { id };
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, parameters);
			if (rs.next()) {
				String report_path = rs.getString("report_path");
				report_path = report_path.replaceAll(" ", "_");
				Path tempPath = Paths.get(root_folder, report_path);
				//String realPath = String.format("%s%s", root_folder, report_path);
				//File file = new File(realPath);
				File file = tempPath.toFile();
				if (file.exists()) {
					//resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(report_name, "UTF-8"));
					if (report_path.endsWith(".pdf")) {
						resp.setContentType("application/pdf");
					} else if (report_path.endsWith(".doc") || report_path.endsWith(".docx")) {
						resp.setContentType("application/msword");
					} else if (report_path.endsWith(".xls") || report_path.endsWith(".xlsx")) {
						resp.setContentType("application/vnd.ms-excel");
					} else if (report_path.endsWith(".ppt") || report_path.endsWith(".pptx")) {
						resp.setContentType("application/vnd.ms-powerpoint");
					}

					OutputStream outputStream = resp.getOutputStream();
					InputStream inputStream = new FileInputStream(file);
					byte[] buffer = new byte[1024];
					int i = -1;
					while ((i = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, i);
					}
					outputStream.flush();
					outputStream.close();
					inputStream.close();

				} else {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is invaild!");
				}
			} else {
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("id is invaild!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public void getRemoteCVReport(HttpServletRequest req, HttpServletResponse resp,ResponseObject response){

		String id = req.getParameter("id");

		if (StringUtils.isEmpty(id)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get CV report failed!");
			return;
		}

		String root_folder = "/home/appuser/workdir/service/web-spider/report";
		ResultSet rs = null;
		String sql = "select report_path from rpt_cv where rid=?";
		Object[] parameters = { id };
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, parameters);
			if (rs.next()) {
				String report_path = rs.getString("report_path");
				report_path = report_path.replaceAll(" ", "_");

				String realPath = String.format("%s%s", root_folder, report_path);

				OutputStream outputStream = resp.getOutputStream();

				if(sftp.getRemoteFile(realPath, outputStream)){
					//resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(report_name, "UTF-8"));
					if (report_path.endsWith(".pdf")) {
						resp.setContentType("application/pdf");
					} else if (report_path.endsWith(".doc") || report_path.endsWith(".docx")) {
						resp.setContentType("application/msword");
					} else if (report_path.endsWith(".xls") || report_path.endsWith(".xlsx")) {
						resp.setContentType("application/vnd.ms-excel");
					} else if (report_path.endsWith(".ppt") || report_path.endsWith(".pptx")) {
						resp.setContentType("application/vnd.ms-powerpoint");
					}
				}else {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is invaild!");
				}
			} else {
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("id is invaild!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public void getTraderReport(HttpServletRequest req, HttpServletResponse resp,ResponseObject response){
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(id)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get CV report failed!");
			return;
		}

		String root_folder = "/home/appuser/workdir/report";
		ResultSet rs = null;
		String sql = "select attachment from rpt_trader where id=?";
		Object[] parameters = { id };
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, parameters);
			if (rs.next()) {
				String report_path = rs.getString("attachment");
				report_path = report_path.replaceAll(" ", "_");
				Path tempPath = Paths.get(root_folder, report_path);

				//String realPath = String.format("%s%s", root_folder, report_path);
//				File file = new File(realPath);
				File file = tempPath.toFile();
				if (file.exists()) {
					if (report_path.endsWith(".pdf")) {
//						resp.setContentType("application/pdf");
						resp.setHeader("Content-type", "application/pdf;charset=UTF-8");
					} else if (report_path.endsWith(".doc") || report_path.endsWith(".docx")) {
						resp.setContentType("application/msword");
					} else if (report_path.endsWith(".xls") || report_path.endsWith(".xlsx")) {
						resp.setContentType("application/vnd.ms-excel");
					} else if (report_path.endsWith(".ppt") || report_path.endsWith(".pptx")) {
						resp.setContentType("application/vnd.ms-powerpoint");
					}

					OutputStream outputStream = resp.getOutputStream();
					InputStream inputStream = new FileInputStream(file);
					byte[] buffer = new byte[1024];
					int i = -1;
					while ((i = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, i);
					}
					outputStream.flush();
					outputStream.close();
					inputStream.close();

				} else {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is invaild!");
				}
			} else {
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("id is invaild!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}

	public void getRemoteTraderReport(HttpServletRequest req, HttpServletResponse resp,ResponseObject response){
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(id)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("Get CV report failed!");
			return;
		}

		String root_folder = "/home/appuser/workdir/service/web-spider/report";
		ResultSet rs = null;
		String sql = "select attachment from rpt_trader where id=?";
		Object[] parameters = { id };
		try {
			rs = MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).executeQuery(sql, parameters);
			if (rs.next()) {
				String report_path = rs.getString("attachment");
				report_path = report_path.replaceAll(" ", "_");
				String realPath = String.format("%s%s", root_folder, report_path);

				OutputStream outputStream = resp.getOutputStream();

				if(sftp.getRemoteFile(realPath, outputStream)){
					//resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(report_name, "UTF-8"));
					if (report_path.endsWith(".pdf")) {
//						resp.setContentType("application/pdf");
						resp.setHeader("Content-type", "application/pdf;charset=UTF-8");
					} else if (report_path.endsWith(".doc") || report_path.endsWith(".docx")) {
						resp.setContentType("application/msword");
					} else if (report_path.endsWith(".xls") || report_path.endsWith(".xlsx")) {
						resp.setContentType("application/vnd.ms-excel");
					} else if (report_path.endsWith(".ppt") || report_path.endsWith(".pptx")) {
						resp.setContentType("application/vnd.ms-powerpoint");
					}
				} else {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is invaild!");
				}
			} else {
				response.setStatus(RDDWebConst.FAILURE);
				response.setMessage("id is invaild!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			MysqlHelper.getInstance(RDDWebConst.PESEER_DB_ONLINE).close(rs);
		}
	}
}
