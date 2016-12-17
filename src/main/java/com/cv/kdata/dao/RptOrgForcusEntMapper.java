package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.RptOrgForcusEnt;

public interface RptOrgForcusEntMapper {
    int insert(RptOrgForcusEnt record);

    int insertSelective(RptOrgForcusEnt record);

    List<RptOrgForcusEnt> getOrgFocusInfo(String orgId);
}