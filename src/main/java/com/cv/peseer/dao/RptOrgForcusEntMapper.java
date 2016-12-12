package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.RptOrgForcusEnt;

public interface RptOrgForcusEntMapper {
    int insert(RptOrgForcusEnt record);

    int insertSelective(RptOrgForcusEnt record);

    List<RptOrgForcusEnt> getOrgFocusInfo(String orgId);
}