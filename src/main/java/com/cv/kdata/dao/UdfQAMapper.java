package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.UdfQA;

public interface UdfQAMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfQA record);

    int insertSelective(UdfQA record);

    UdfQA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfQA record);

    int updateByPrimaryKeyWithBLOBs(UdfQA record);

    int updateByPrimaryKey(UdfQA record);

    List<UdfQA> selectByUid(@Param("uid")String uid);
}