package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.StockManageTeam;

public interface StockManageTeamMapper {
    int insert(StockManageTeam record);

    int insertSelective(StockManageTeam record);

    List<StockManageTeam> selectByStockCode(@Param("stockCode")String stockcode);
}