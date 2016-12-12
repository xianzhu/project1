package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.RptOrgOverallTrends;

public interface RptOrgOverallTrendsMapper {
    int insert(RptOrgOverallTrends record);

    int insertSelective(RptOrgOverallTrends record);

    List<RptOrgOverallTrends> getOverallTrends();
}