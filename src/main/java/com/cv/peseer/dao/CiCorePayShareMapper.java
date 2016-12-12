package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiCorePayShare;

public interface CiCorePayShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiCorePayShare record);

    int insertSelective(CiCorePayShare record);

    CiCorePayShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiCorePayShare record);

    int updateByPrimaryKey(CiCorePayShare record);
    
    List<CiCorePayShare> selectAll();
}