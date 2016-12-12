package com.cv.peseer.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.StockInstInvest;

public interface StockInstInvestMapper {
    int insert(StockInstInvest record);

    int insertSelective(StockInstInvest record);

    StockInstInvest selectByStockCode(@Param("stockCode")String stockcode);
}