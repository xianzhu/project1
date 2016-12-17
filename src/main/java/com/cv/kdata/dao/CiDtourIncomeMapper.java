package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.CiDtourIncome;

public interface CiDtourIncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiDtourIncome record);

    int insertSelective(CiDtourIncome record);

    CiDtourIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiDtourIncome record);

    int updateByPrimaryKey(CiDtourIncome record);
    
    List<CiDtourIncome> selectAll();
}