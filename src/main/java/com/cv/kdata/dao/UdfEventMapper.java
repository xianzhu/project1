package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.UdfEvent;

public interface UdfEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfEvent record);

    int insertSelective(UdfEvent record);

    UdfEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfEvent record);

    int updateByPrimaryKeyWithBLOBs(UdfEvent record);

    int updateByPrimaryKey(UdfEvent record);

    List<UdfEvent> selectByUid(@Param("uid")String uid);
}