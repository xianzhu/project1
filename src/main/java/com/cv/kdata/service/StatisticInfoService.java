package com.cv.kdata.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cv.kdata.cont.RDDWebConst;
import com.cv.kdata.dao.LoginInfoMapper;
import com.cv.kdata.dao.UserInfoMapper;
import com.cv.kdata.response.DashboardResponse;
import com.cv.kdata.response.TrendResponse;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kdata.defined.model.StatDashboard;
import com.kdata.defined.model.StatEventOne;
import com.kdata.defined.model.StatEventTwo;
import com.kdata.defined.model.StatTrendAll;

@Service
public class StatisticInfoService {
	@Autowired
	LoginInfoMapper loginInfoMapper;
	@Autowired
	UserInfoMapper userInfoMapper;

	private static Logger LOGGER = LoggerFactory.getLogger(StatisticInfoService.class);

	/**
	 * 获取趋势统计信息
	 */
	public void getTrendInfo(HttpServletRequest req, TrendResponse response) {

		try{
		// 获取机构调研趋势
		String sql = "select * from stat_trend_all order by stat_date desc limit 7 ";
		List<Record> records = Db.find(sql);
		response.setTrend(transferToTrend(records));

		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("get trend successful!");
		}catch(Exception e){
			LOGGER.error("get trend error" + e.getMessage());
		}

	}

	/**
	 * 获取仪表盘统计信息
	 */
	public void getDashboardInfo(HttpServletRequest req, DashboardResponse response) {

		try{
		// 获取仪表盘统计信息
		String sql = "select * from stat_dashboard "
				+ "order by count_date desc limit 4 ";
		List<Record> records = Db.find(sql);
		response.setCount(transferToDashboard(records));

		response.setStatus(RDDWebConst.SUCCESS);
		response.setMessage("get dashboard successful!");

		}catch(Exception e){
			LOGGER.error("get dashboard error" + e.getMessage());
		}

	}

	/**
	 * 获取第一级别事件统计
	 * @param req
	 * @param response
	 */
	public void getEventOneStat(HttpServletRequest req, TrendResponse response){
		try{
			// 获取仪表盘统计信息
			String sql = "select * from stat_event_round_one "
					+ "order by count_date desc limit 7 ";
			List<Record> records = Db.find(sql);
			response.setTrend(transferToEvent1(records));

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("get event statistic successful!");

			}catch(Exception e){
				LOGGER.error("get event statistic error" + e.getMessage());
			}
	}

	public void getEventTwoStat(HttpServletRequest req, TrendResponse response){
		try{
			// 获取仪表盘统计信息
			String sql = "select a.*, b.round_name from stat_event_round_two a "
					+ "left join event_round_two b "
					+ "on a.type_id=b.id ";
			List<Record> records = Db.find(sql);
			response.setTrend(transferToEvent2(records));

			response.setStatus(RDDWebConst.SUCCESS);
			response.setMessage("get event statistic successful!");

			}catch(Exception e){
				LOGGER.error("get event statistic error" + e.getMessage());
			}
	}

	/**
	 * 把record列表转化成趋势统计列表
	 *
	 * @param records
	 * @return
	 */
	public List<StatTrendAll> transferToTrend(List<Record> records) {
		if (records == null) {
			return null;
		}
		List<StatTrendAll> trendList = new ArrayList<>();
		for (Record record : records) {
			StatTrendAll trend = new StatTrendAll();
			trend.setId(record.get("id"));
			trend.setAm(record.getDouble("am"));
			trend.setBroker(record.getDouble("broker"));
			trend.setBank(record.getDouble("bank"));
			trend.setCapIncrease(record.getDouble("cap_increase"));
			trend.setExitEvents(record.getDouble("exit_events"));
			trend.setInsurance(record.getDouble("insurance"));
			trend.setInvestEvents(record.getDouble("invest_events"));
			trend.setInvestment(record.getDouble("investment"));
			trend.setPe(record.getDouble("pe"));
			trend.setProTransfer(record.getDouble("pro_transfer"));
			trend.setSharePurchase(record.getDouble("share_purchase"));
			trend.setStartFund(record.getDouble("start_fund"));
			trend.setStatDate(record.getStr("stat_date"));
			trend.setStockFund(record.getDouble("stock_fund"));
			trend.setTrust(record.getDouble("trust"));

			trendList.add(trend);
		}

		return trendList;
	}

	/**
	 * 把record列表转化成仪表盘统计列表
	 *
	 * @param records
	 * @return
	 */
	public List<StatDashboard> transferToDashboard(List<Record> records) {
		if (records == null) {
			return null;
		}
		List<StatDashboard> trendList = new ArrayList<>();
		for (Record record : records) {
			StatDashboard trend = new StatDashboard();
			trend.setId(record.get("id"));
			trend.setName(record.getStr("name"));
			trend.setStatMin(record.getInt("std_min"));
			trend.setStatMin(record.getInt("std_median"));
			trend.setStatMin(record.getInt("std_max"));
			trend.setCount(record.getInt("daily_count"));
			trend.setStatDate(record.getStr("count_date"));
			trendList.add(trend);
		}

		return trendList;
	}

	/**
	 * 把record列表转换成一级事件统计列表
	 * @param records
	 * @return
	 */
	public List<StatEventOne> transferToEvent1(List<Record> records) {
		if (records == null) {
			return null;
		}
		List<StatEventOne> trendList = new ArrayList<>();
		for (Record record : records) {
			StatEventOne trend = new StatEventOne();
			trend.setId(record.get("id"));
			trend.setExitOne(record.getInt("exit_one"));
			trend.setExitTwo(record.getInt("exit_two"));
			trend.setInvestEarly(record.getInt("invest_early"));
			trend.setInvestMiddle(record.getInt("invest_middle"));
			trend.setInvestLate(record.getInt("invest_late"));
			trend.setInvestOther(record.getInt("invest_other"));
			trend.setCountDate(record.getStr("count_date"));
			trendList.add(trend);
		}

		return trendList;
	}

	/**
	 * 把record列表转换成二级事件统计列表
	 * @param records
	 * @return
	 */
	public List<StatEventTwo> transferToEvent2(List<Record> records) {
		if (records == null) {
			return null;
		}
		List<StatEventTwo> trendList = new ArrayList<>();
		for (Record record : records) {
			StatEventTwo trend = new StatEventTwo();
			trend.setId(record.get("id"));
			trend.setTypeName(record.getStr("round_name"));
			trend.setTypeId(record.getInt("type_id"));
			trend.setCount(record.getInt("count"));
			trend.setCountDate(record.getStr("count_date"));
			trendList.add(trend);
		}

		return trendList;
	}
}
