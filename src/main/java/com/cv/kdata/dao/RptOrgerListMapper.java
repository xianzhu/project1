package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.RptOrgerList;

public interface RptOrgerListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RptOrgerList record);

    int insertSelective(RptOrgerList record);

    RptOrgerList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptOrgerList record);

    int updateByPrimaryKeyWithBLOBs(RptOrgerList record);

    int updateByPrimaryKey(RptOrgerList record);

    List<RptOrgerList> getOrgList();
}