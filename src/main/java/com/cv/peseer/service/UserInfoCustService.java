package com.cv.peseer.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.peseer.cont.RDDWebConst;
import com.cv.peseer.dao.UdfConsultMapper;
import com.cv.peseer.dao.UdfEventMapper;
import com.cv.peseer.dao.UdfMonitorMapper;
import com.cv.peseer.dao.UdfQAMapper;
import com.cv.peseer.dao.UdfRptCVMapper;
import com.cv.peseer.dao.UdfRptTraderMapper;
import com.cv.peseer.datasource.DBContextHolder;
import com.cv.peseer.model.UdfConsult;
import com.cv.peseer.model.UdfEvent;
import com.cv.peseer.model.UdfMonitor;
import com.cv.peseer.model.UdfQA;
import com.cv.peseer.model.UdfRptCV;
import com.cv.peseer.model.UdfRptTrader;
import com.cv.peseer.response.UserInfoCustResponse;
import com.cv.peseer.util.LoginInfoCache;
import com.cv.peseer.util.MysqlHelper;
import com.cv.peseer.util.StringUtil;

@Service
public class UserInfoCustService {
	@Autowired
	UdfEventMapper udfEventMapper;
	@Autowired
	UdfRptTraderMapper udfRptTraderMapper;
	@Autowired
	UdfConsultMapper udfConsultMapper;
	@Autowired
	UdfMonitorMapper udfMonitorMapper;
	@Autowired
	UdfRptCVMapper udfRptCVMapper;
	@Autowired
	UdfQAMapper udfQAMapper;

	private static final HashSet<String> module_type_set = new HashSet<>();

	private static final HashSet<String> operation_type_set = new HashSet<>();

	private static final Map<String, String> time2String = new HashMap<>();

	static {
		String[] module_type_array = { "0"/* 查全部 */, "1", "2", "3", "4" };
		CollectionUtils.addAll(module_type_set, module_type_array);

		// String[] industry_array = { "新闻", "定制", "行业", "资讯", "竞品", "投后" };
		// CollectionUtils.addAll(information_industry_filter, industry_array);

		String[] operation_type_array = { "1"/* 增 */, "2"/* 删 */, "3"/* 改 */ };

		// 1.周；2.月；3.季度；4.半年；5.全年
		time2String.put("1", "每月");
		time2String.put("2", "每月");
		time2String.put("3", "每季");
		time2String.put("4", "半年");
		time2String.put("5", "全年");
		CollectionUtils.addAll(operation_type_set, operation_type_array);
	}

	/**
	 * 返回事件列表
	 *
	 * @param req
	 * @param resp
	 * @param response
	 *
	 */
	public void getEventInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);

		if(StringUtil.isNullOrEmpty(uid)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid is valid");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfEvent> eventList = udfEventMapper.selectByUid(uid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get user defined event success!");
		response.setEventList(eventList);

	}

	/**
	 * 获取行业分析
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getCVInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
//		String uid = req.getParameter("uid");
		if(StringUtil.isNullOrEmpty(uid)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid is valid");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfRptCV> itemList = udfRptCVMapper.selectByUid(uid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get user defined cv report success!");
		response.setCvList(itemList);
	}

	/**
	 * 获取研究报告
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getTraderInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
//		String uid = req.getParameter("uid");
		if(StringUtil.isNullOrEmpty(uid)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid is valid");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfRptTrader> itemList = udfRptTraderMapper.selectByUid(uid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get user defined trader report success!");
		response.setTraderList(itemList);
	}

	/**
	 * 获取咨询信息
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getConsultInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
//		String uid = req.getParameter("uid");
		if(StringUtil.isNullOrEmpty(uid)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid is valid");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfConsult> itemList = udfConsultMapper.selectByUid(uid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get user defined cousult success!");
		response.setConsultList(itemList);

	}

	/**
	 * 获取监控信息
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void getMonitorInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}

		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
//		String uid = req.getParameter("uid");
		if(StringUtil.isNullOrEmpty(uid)){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid is valid");
			return;
		}

		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		List<UdfMonitor> itemList = udfMonitorMapper.selectByUid(uid);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("Get user defined cousult success!");
		response.setMonitorList(itemList);
	}

	/**
	 * 更新事件
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void updateEventInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		String id = req.getParameter("id");
//		String uid = req.getParameter("uid");
		String type = req.getParameter("type");
		String status = req.getParameter("status");
		String leader = req.getParameter("leader");
		String fund = req.getParameter("fund");
		String gp_name = req.getParameter("gp_name");
		String lp_name = req.getParameter("lp_name");
		String plan_line = req.getParameter("plan_line");
		String real_line = req.getParameter("real_line");
		String vc_name = req.getParameter("vc_name");
		String team_name = req.getParameter("team_name");
		String round = req.getParameter("round");
		String ma_pay = req.getParameter("ma_pay");
		String ma_buyer = req.getParameter("ma_buyer");
		String ma_target = req.getParameter("ma_target");
		String exit_name = req.getParameter("exit_name");
		String exit_bring_rate = req.getParameter("exit_bring_rate");
		String exit_irr = req.getParameter("exit_irr");
		String happen_date = req.getParameter("happen_date");
		String src_info = req.getParameter("src_info");
		String content = req.getParameter("content");
		String title = req.getParameter("title");

		// 操作类型
		// 1.判断取值范围
		String operation_type = req.getParameter("operation_type");

		if (StringUtils.isEmpty(operation_type)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is empty");
			return;
		} else if (!operation_type_set.contains(operation_type)) {
			// operation_type 取值范围 1:增 2:删 3:改
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is valid");
			return;
		}

		try {
			// 新增操作
			if (operation_type.equals(RDDWebConst.ADD)) {

				if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(type)
						|| StringUtils.isEmpty(title)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("uid or type or title is empty");
					return;
				}

				status = "1";
				String sql = "insert into udf_event (uid, type, status, leader, fund,gp_name,lp_name,plan_line,real_line,vc_name,team_name,round,ma_pay,ma_buyer,ma_target,exit_name,exit_bring_rate,exit_irr,happen_date,src_info,content,title) values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				Object[] parameters = { uid, type, status, leader, fund, gp_name, lp_name, plan_line, real_line,
						vc_name, team_name, round, ma_pay, ma_buyer, ma_target, exit_name, exit_bring_rate, exit_irr,
						happen_date, src_info, content, title };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("insert success!");
			} else if (operation_type.equals(RDDWebConst.DELETE)) {

				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}

				String sql = "delete from udf_event where id=?";
				Object[] parameters = { id };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("delete success!");
			} else if (operation_type.equals(RDDWebConst.UPDATE)) {

				if (StringUtils.isEmpty(id) || StringUtils.isEmpty(uid) || StringUtils.isEmpty(type)
						 || StringUtils.isEmpty(title)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("uid or type or title is empty");
					return;
				}

				status = "1";
				String sql = "update udf_event set uid=?, type=?, status=?, leader=?, fund=?,gp_name=?,lp_name=?,plan_line=?,real_line=?,vc_name=?,team_name=?,round=?,ma_pay=?,ma_buyer=?,ma_target=?,exit_name=?,exit_bring_rate=?,exit_irr=?,happen_date=?,src_info=?,content=?,title=? where id=?";
				Object[] parameters = { uid, type, status, leader, fund, gp_name, lp_name, plan_line, real_line,
						vc_name, team_name, round, ma_pay, ma_buyer, ma_target, exit_name, exit_bring_rate, exit_irr,
						happen_date, src_info, content, title, id };
				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("update success!");
			}
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("sql excute error");
		}
	}

	/**
	 * 更新行业报告
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void updateCVInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		String operation_type = req.getParameter("operation_type");

		String type = req.getParameter("type");
		String frequency = req.getParameter("frequency");
//		String uid = req.getParameter("uid");
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(operation_type)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is empty");
			return;
		}

		try {
			if (operation_type.equals(RDDWebConst.ADD)) {

				if (StringUtils.isEmpty(type) || StringUtils.isEmpty(frequency)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("type is empty");
					return;
				}

				String sql = "insert into udf_rpt_cv(uid, type,frequency) values (?,?,?)";

				Object[] parameters = { uid, type, frequency };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("insert success!");
			} else if (operation_type.equals(RDDWebConst.DELETE)) {

				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "delete from udf_rpt_cv where id = ?";
				Object[] parameters = { id };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("delete success!");
			} else if (operation_type.equals(RDDWebConst.UPDATE)) {

				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "update udf_rpt_cv set type=?, frequency=? where id=? ";
				Object[] parameters = { type, frequency, id };
				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("update success!");
			}
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation failed");
			return;
		}
	}

	/**
	 * 更新研究报告
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void updateTraderInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		String type = req.getParameter("type");
		String operation_type = req.getParameter("operation_type");
		String frequency = req.getParameter("frequency");
//		String uid = req.getParameter("uid");
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(operation_type)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is empty");
			return;
		}

		try {
			if (operation_type.equals(RDDWebConst.ADD)) {

				if (StringUtils.isEmpty(type) || StringUtils.isEmpty(frequency)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("type is empty");
					return;
				}

				String sql = "insert into udf_rpt_trader(uid, type,frequency) values (?,?,?)";

				Object[] parameters = { uid, type, frequency };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("insert success!");
			} else if (operation_type.equals(RDDWebConst.DELETE)) {

				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "delete from udf_rpt_trader where id = ?";
				Object[] parameters = { id };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("delete success!");
			} else if (operation_type.equals(RDDWebConst.UPDATE)) {

				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "update udf_rpt_trader set type=?, frequency=? where id=? ";
				Object[] parameters = { type, frequency, id };
				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("update success!");
			}
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation failed");
			return;
		}
	}

	/**
	 * 更新监控信息
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void updateMonitorInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		String type = req.getParameter("type");
		String operation_type = req.getParameter("operation_type");
		String content = req.getParameter("content");
//		String uid = req.getParameter("uid");
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(operation_type)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is empty");
			return;
		}

		try {
			if (operation_type.equals(RDDWebConst.ADD)) {

				String sql = "insert into udf_monitor(uid, type,content) values (?,?,?)";

				Object[] parameters = { uid, type, content };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("insert success!");
			} else if (operation_type.equals(RDDWebConst.DELETE)) {
				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "delete from udf_monitor where id = ?";
				Object[] parameters = { id };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("delete success!");
			} else if (operation_type.equals(RDDWebConst.UPDATE)) {
				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "update udf_monitor set type=?, content=? where id=? ";
				Object[] parameters = { type, content, id };
				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("update success!");
			}
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation failed");
			return;
		}
	}

	/**
	 * 更新业务信息
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void updateConsultInfo(HttpServletRequest req, UserInfoCustResponse response) {
		if (RDDWebConst.FAILURE.equals(response.getStatus())) {
			return;
		}
		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		String status = req.getParameter("status");
		String operation_type = req.getParameter("operation_type");
		String content = req.getParameter("content");
//		String uid = req.getParameter("uid");
		String id = req.getParameter("id");

		if (StringUtils.isEmpty(operation_type)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation_type is empty");
			return;
		}

		try {
			if (operation_type.equals(RDDWebConst.ADD)) {

				status = "1";
				String sql = "insert into udf_consult(uid, status,content) values (?,?,?)";

				Object[] parameters = { uid, status, content };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);

				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("insert success!");
			} else if (operation_type.equals(RDDWebConst.DELETE)) {
				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				String sql = "delete from udf_consult where id = ?";
				Object[] parameters = { id };

				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("delete success!");
			} else if (operation_type.equals(RDDWebConst.UPDATE)) {
				if (StringUtils.isEmpty(id)) {
					response.setStatus(RDDWebConst.FAILURE);
					response.setMessage("id is empty");
					return;
				}
				status = "1";
				String sql = "update udf_consult set status=?, content=? where id=? ";
				Object[] parameters = { status, content, id };
				MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
				response.setStatus(RDDWebConst.SUCCESS);
				response.setMessage("update success!");
			}
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation failed");
			return;
		}
	}

	/**
	 *
	 * @param req
	 * @param resp
	 * @param response
	 */
	public void feedbackSubmit(HttpServletRequest req, UserInfoCustResponse response) {
		String status = req.getParameter("status");
		String feedback = req.getParameter("content");
//		String uid = req.getParameter("uid");

		String token = (String) req.getSession().getAttribute(RDDWebConst.TOKEN);
		String uid = LoginInfoCache.getInstance().getUid(token);
		if (StringUtil.isNullOrEmpty(feedback)) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("feedback is empity!");
			return;
		}

		status = "1";
		String sql = "insert into udf_qa(uid, status, content) values (?,?,?)";
		Object[] parameters = { uid, status, feedback };

		try {
			MysqlHelper.getInstance(RDDWebConst.PESEER_LOGIN).executeUpdate(sql, parameters);
		} catch (SQLException e) {
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("operation failed");
			return;
		}

		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
		return;
	}

	public void updateEvent(UdfEvent event, UserInfoCustResponse response){
		if(event.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfEventMapper.updateByPrimaryKeySelective(event);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
	}

	public void addEvent(UdfEvent event, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(event.getUid())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfEventMapper.insertSelective(event);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}

	public void delEvent(UdfEvent event, UserInfoCustResponse response){
		if(event.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfEventMapper.deleteByPrimaryKey(event.getId());
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("delete success!");
	}

	public void updateConsult(UdfConsult record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfConsultMapper.updateByPrimaryKeySelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
	}

	public void addConsult(UdfConsult record, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(record.getUid())||StringUtil.isNullOrEmpty(record.getContent())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid and content cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfConsultMapper.insertSelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}

	public void delConsult(UdfConsult record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfConsultMapper.deleteByPrimaryKey(record.getId());
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("delete success!");
	}

	public void updateMonitor(UdfMonitor record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfMonitorMapper.updateByPrimaryKeySelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
	}

	public void addMonitor(UdfMonitor record, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(record.getUid())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfMonitorMapper.insertSelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}

	public void delMonitor(UdfMonitor record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfMonitorMapper.deleteByPrimaryKey(record.getId());
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("delete success!");
	}

	public void updateRptCV(UdfRptCV record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptCVMapper.updateByPrimaryKeySelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
	}

	public void addRptCV(UdfRptCV record, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(record.getUid())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptCVMapper.insertSelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}

	public void delRptCV(UdfRptCV record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptCVMapper.deleteByPrimaryKey(record.getId());
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("delete success!");
	}

	public void updateRptTrader(UdfRptTrader record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptTraderMapper.updateByPrimaryKeySelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("update success!");
	}

	public void addRptTrader(UdfRptTrader record, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(record.getUid())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptTraderMapper.insertSelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}

	public void delRptTrader(UdfRptTrader record, UserInfoCustResponse response){
		if(record.getId() < 0){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("id is invalid!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfRptTraderMapper.deleteByPrimaryKey(record.getId());
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("delete success!");
	}

	public void addQA(UdfQA record, UserInfoCustResponse response){
		if(StringUtil.isNullOrEmpty(record.getUid())){
			response.setStatus(RDDWebConst.FAILURE);
			response.setMessage("uid cannot be null!");
			return;
		}
		DBContextHolder.setDbType(DBContextHolder.PESEER_LOGIN);
		udfQAMapper.insertSelective(record);
		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("add success!");
	}
}
