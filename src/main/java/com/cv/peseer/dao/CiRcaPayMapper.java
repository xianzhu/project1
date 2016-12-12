package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiRcaPay;

public interface CiRcaPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiRcaPay record);

    int insertSelective(CiRcaPay record);

    CiRcaPay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiRcaPay record);

    int updateByPrimaryKey(CiRcaPay record);

    List<CiRcaPay> getCiRcaPayList();
}