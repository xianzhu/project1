package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.RptOrgOverallTrends;

public interface RptOrgOverallTrendsMapper {
    int insert(RptOrgOverallTrends record);

    int insertSelective(RptOrgOverallTrends record);

    List<RptOrgOverallTrends> getOverallTrends();
}