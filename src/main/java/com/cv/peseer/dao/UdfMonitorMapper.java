package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.UdfMonitor;

public interface UdfMonitorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfMonitor record);

    int insertSelective(UdfMonitor record);

    UdfMonitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfMonitor record);

    int updateByPrimaryKey(UdfMonitor record);

    List<UdfMonitor> selectByUid(@Param("uid")String uid);
}