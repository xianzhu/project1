package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiMoneySup;

public interface CiMoneySupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiMoneySup record);

    int insertSelective(CiMoneySup record);

    CiMoneySup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiMoneySup record);

    int updateByPrimaryKey(CiMoneySup record);

    List<CiMoneySup> getCiMoneySupList();
}