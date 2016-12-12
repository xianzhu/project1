package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.PMFundInfo;

public interface PMFundInfoMapper {
    int deleteByPrimaryKey(Integer fundId);

    int insert(PMFundInfo record);

    int insertSelective(PMFundInfo record);

    PMFundInfo selectByPrimaryKey(Integer fundId);

    int updateByPrimaryKeySelective(PMFundInfo record);

    int updateByPrimaryKeyWithBLOBs(PMFundInfo record);

    int updateByPrimaryKey(PMFundInfo record);

    List<PMFundInfo> getFundInfo(String orgId);

    List<PMFundInfo> getIndexInfo(@Param("key")String key, @Param("from")Integer from);
}