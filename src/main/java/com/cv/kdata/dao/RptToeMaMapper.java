package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.RptToeMa;

public interface RptToeMaMapper {
    int insert(RptToeMa record);

    int insertSelective(RptToeMa record);

    List<RptToeMa> getRpt2MaList(@Param("from")int from,@Param("count")int count);
}