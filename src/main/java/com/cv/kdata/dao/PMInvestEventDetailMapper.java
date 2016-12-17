package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.PMInvestEventDetail;

public interface PMInvestEventDetailMapper {
    int deleteByPrimaryKey(Integer eventId);

    int insert(PMInvestEventDetail record);

    int insertSelective(PMInvestEventDetail record);

    PMInvestEventDetail selectByPrimaryKey(Integer eventId);

    int updateByPrimaryKeySelective(PMInvestEventDetail record);

    int updateByPrimaryKeyWithBLOBs(PMInvestEventDetail record);

    int updateByPrimaryKey(PMInvestEventDetail record);

    List<PMInvestEventDetail> selectByUuid(@Param("uuid")String uuid);
}