package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.PMOrgInfo;

public interface PMOrgInfoMapper {
    int deleteByPrimaryKey(Integer orgId);

    int insert(PMOrgInfo record);

    int insertSelective(PMOrgInfo record);

    PMOrgInfo selectByPrimaryKey(Integer orgId);

    int updateByPrimaryKeySelective(PMOrgInfo record);

    int updateByPrimaryKeyWithBLOBs(PMOrgInfo record);

    int updateByPrimaryKey(PMOrgInfo record);

    List<PMOrgInfo> getIndexInfo(String key, int from);
}