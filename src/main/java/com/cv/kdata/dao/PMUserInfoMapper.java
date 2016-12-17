package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.PMUserInfo;

public interface PMUserInfoMapper {
    int insert(PMUserInfo record);

    int insertSelective(PMUserInfo record);

    List<PMUserInfo> getOrgUserInfo(String orgId);

    PMUserInfo selectUserInfoById(@Param("userId")String userId);

    List<PMUserInfo> getIndexInfo(@Param("key")String key, @Param("from")Integer from);
}