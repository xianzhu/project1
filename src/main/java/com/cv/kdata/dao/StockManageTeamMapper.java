package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.StockManageTeam;

public interface StockManageTeamMapper {
    int insert(StockManageTeam record);

    int insertSelective(StockManageTeam record);

    List<StockManageTeam> selectByStockCode(@Param("stockCode")String stockcode);
}