package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiOlVideoIncm;

public interface CiOlVideoIncmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiOlVideoIncm record);

    int insertSelective(CiOlVideoIncm record);

    CiOlVideoIncm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiOlVideoIncm record);

    int updateByPrimaryKey(CiOlVideoIncm record);
    
    List<CiOlVideoIncm> selectAll();
}