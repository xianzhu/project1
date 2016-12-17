package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.CiSfSca;

public interface CiSfScaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiSfSca record);

    int insertSelective(CiSfSca record);

    CiSfSca selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiSfSca record);

    int updateByPrimaryKey(CiSfSca record);

    List<CiSfSca> getCiSfScaList();
}