package com.cv.kdata.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.StockInstInvest;

public interface StockInstInvestMapper {
    int insert(StockInstInvest record);

    int insertSelective(StockInstInvest record);

    StockInstInvest selectByStockCode(@Param("stockCode")String stockcode);
}