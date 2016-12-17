package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.UdfRptTrader;

public interface UdfRptTraderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfRptTrader record);

    int insertSelective(UdfRptTrader record);

    UdfRptTrader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfRptTrader record);

    int updateByPrimaryKey(UdfRptTrader record);

    List<UdfRptTrader> getTraderListFromUUid(@Param("token")String uuid);

    List<UdfRptTrader> selectByUid(@Param("uid")String uid);
}