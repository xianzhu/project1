package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.PMStartupInfo;

public interface PMStartupInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PMStartupInfo record);

    int insertSelective(PMStartupInfo record);

    PMStartupInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PMStartupInfo record);

    int updateByPrimaryKeyWithBLOBs(PMStartupInfo record);

    int updateByPrimaryKey(PMStartupInfo record);

    List<PMStartupInfo> getProjectInfo(@Param("custom")List<String> custom);
    List<PMStartupInfo> searchProjectInfo(
    		@Param("domain") String domain,
    		@Param("key") String key,
    		@Param("from") int from,
    		@Param("from") int count);
}