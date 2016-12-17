package com.cv.kdata.dao;

import com.cv.kdata.model.PMExitEventDetail;

public interface PMExitEventDetailMapper {
    int deleteByPrimaryKey(Integer eventId);

    int insert(PMExitEventDetail record);

    int insertSelective(PMExitEventDetail record);

    PMExitEventDetail selectByPrimaryKey(Integer eventId);

    int updateByPrimaryKeySelective(PMExitEventDetail record);

    int updateByPrimaryKeyWithBLOBs(PMExitEventDetail record);

    int updateByPrimaryKey(PMExitEventDetail record);
}