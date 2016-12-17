package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.StockFeatureAll;

public interface StockFeatureAllMapper {
    int insert(StockFeatureAll record);

    int insertSelective(StockFeatureAll record);

    List<StockFeatureAll> getStockPotentialInfo(@Param("stockCode")String stockCode);
    List<StockFeatureAll> getXsbPotentialInfo(@Param("stockCode")String stockCode);
    List<StockFeatureAll> getStockInfo(@Param("stockCode")String stockCode);
    List<StockFeatureAll> getXsbInfo(@Param("stockCode")String stockCode);
}