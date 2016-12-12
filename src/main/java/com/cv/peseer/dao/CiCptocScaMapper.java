package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiCptocSca;

public interface CiCptocScaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiCptocSca record);

    int insertSelective(CiCptocSca record);

    CiCptocSca selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiCptocSca record);

    int updateByPrimaryKey(CiCptocSca record);
    
    List<CiCptocSca> selectAll();
}