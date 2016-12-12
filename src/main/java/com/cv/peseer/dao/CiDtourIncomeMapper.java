package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiDtourIncome;

public interface CiDtourIncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiDtourIncome record);

    int insertSelective(CiDtourIncome record);

    CiDtourIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiDtourIncome record);

    int updateByPrimaryKey(CiDtourIncome record);
    
    List<CiDtourIncome> selectAll();
}