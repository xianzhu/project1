package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.PMInvestEvent;

public interface PMInvestEventMapper {
    int deleteByPrimaryKey(Integer eventId);

    int insert(PMInvestEvent record);

    int insertSelective(PMInvestEvent record);

    PMInvestEvent selectByPrimaryKey(Integer eventId);

    int updateByPrimaryKeySelective(PMInvestEvent record);

    int updateByPrimaryKey(PMInvestEvent record);

    List<PMInvestEvent> getOrgInvestEvent(String orgId);

    List<PMInvestEvent> getInvestorInvestEvent(@Param("userId")String userId,
    		@Param("filter")List<String> filter,
    		@Param("key")String key,
    		@Param("from")int from,
    		@Param("count")int count);

    List<PMInvestEvent> getFundInvestEvent(@Param("fundId")String fundId, @Param("filter")List<String> filter, @Param("key")String key, @Param("from")int from);

    List<PMInvestEvent> getFundInvestEventAll(@Param("fundId")String fundId);

    List<PMInvestEvent> getEventsAfterDate(@Param("date")String date, @Param("from")int from);

    List<PMInvestEvent> getEventsAfterDateByType(
    		@Param("date")String date,
    		@Param("type")String type,
    		@Param("from")int from,
    		@Param("count")int count);
}