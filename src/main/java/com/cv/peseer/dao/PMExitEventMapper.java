package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.PMExitEvent;

public interface PMExitEventMapper {
    int deleteByPrimaryKey(Integer eventId);

    int insert(PMExitEvent record);

    int insertSelective(PMExitEvent record);

    PMExitEvent selectByPrimaryKey(Integer eventId);

    int updateByPrimaryKeySelective(PMExitEvent record);

    int updateByPrimaryKey(PMExitEvent record);

    List<PMExitEvent> getOrgExitEvent(String orgId);

    List<PMExitEvent> getInvestorExitEvent(@Param("userId")String userId, @Param("filter")List<String> filter, @Param("key")String key, @Param("from")int from);

    List<PMExitEvent> getFundExitEvent(@Param("fundId")String fundId, @Param("filter")List<String> filter, @Param("key")String key, @Param("from")int from);

    List<PMExitEvent> getFundExitEventAll(@Param("fundId")String fundId);
}