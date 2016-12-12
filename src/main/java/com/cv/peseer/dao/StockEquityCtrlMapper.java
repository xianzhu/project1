package com.cv.peseer.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.StockEquityCtrl;

public interface StockEquityCtrlMapper {
    int insert(StockEquityCtrl record);

    int insertSelective(StockEquityCtrl record);

    StockEquityCtrl selectByStockCode(@Param("stockCode")String stockcode);
}