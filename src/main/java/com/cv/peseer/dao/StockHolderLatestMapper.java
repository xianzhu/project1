package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.StockHolderLatest;

public interface StockHolderLatestMapper {
    int insert(StockHolderLatest record);

    int insertSelective(StockHolderLatest record);

    List<StockHolderLatest> selectByStockCode(@Param("stockCode")String stockcode);
}