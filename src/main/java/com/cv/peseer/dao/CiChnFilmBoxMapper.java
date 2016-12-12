package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.CiChnFilmBox;

public interface CiChnFilmBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CiChnFilmBox record);

    int insertSelective(CiChnFilmBox record);

    CiChnFilmBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CiChnFilmBox record);

    int updateByPrimaryKey(CiChnFilmBox record);
    
    List<CiChnFilmBox> selectAll();
}