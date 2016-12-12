package com.cv.peseer.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.RptEntJudgeValue;

public interface RptEntJudgeValueMapper {
    int insert(RptEntJudgeValue record);

    int insertSelective(RptEntJudgeValue record);

    RptEntJudgeValue selectByUuid(@Param("uuid")String uuid);
}