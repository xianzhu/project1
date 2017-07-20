package com.kd.model.general;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kd.model.general.base.BaseRptOpsStatTrend;

/**
 *
 */
@SuppressWarnings("serial")
public class RptOpsStatTrend extends BaseRptOpsStatTrend<RptOpsStatTrend> {
	public static final RptOpsStatTrend dao = new RptOpsStatTrend();

	public List<RptOpsStatTrend> selectAllRecords(){
		try{
			String sql = String.format("select * from %s order by dur_time asc",TableName);
			return transfer2Model(Db.find(sql));

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<RptOpsStatTrend> transfer2Model(List<Record> records){
		List<RptOpsStatTrend> trends = new ArrayList<>();
		for(Record tmp:records){
			RptOpsStatTrend trend = new RptOpsStatTrend();
			trend.setDurTime(tmp.getStr("dur_time"));
			trend.setIci(tmp.getDouble("ici"));
			trend.setIei(tmp.getDouble("iei"));
			trend.setIsi(tmp.getDouble("isi"));
			trends.add(trend);
		}

		return trends;
	}
}
