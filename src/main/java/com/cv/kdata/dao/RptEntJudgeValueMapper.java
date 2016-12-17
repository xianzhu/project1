package com.cv.kdata.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.RptEntJudgeValue;

public interface RptEntJudgeValueMapper {
    int insert(RptEntJudgeValue record);

    int insertSelective(RptEntJudgeValue record);

    RptEntJudgeValue selectByUuid(@Param("uuid")String uuid);
}