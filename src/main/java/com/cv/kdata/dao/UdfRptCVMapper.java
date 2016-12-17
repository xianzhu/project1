package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.UdfRptCV;

public interface UdfRptCVMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfRptCV record);

    int insertSelective(UdfRptCV record);

    UdfRptCV selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfRptCV record);

    int updateByPrimaryKey(UdfRptCV record);

    List<UdfRptCV> getCVListFromUUid(@Param("token")String uuid);

    List<UdfRptCV> selectByUid(@Param("uid")String uid);
}