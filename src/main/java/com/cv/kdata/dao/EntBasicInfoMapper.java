package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cv.kdata.model.EntBasicInfo;
import com.cv.kdata.model.EntBasicInfoExample;
import com.cv.kdata.model.EntBasicInfoWithBLOBs;

@Repository
public interface EntBasicInfoMapper {
    int countByExample(EntBasicInfoExample example);

    int deleteByExample(EntBasicInfoExample example);

    int insert(EntBasicInfoWithBLOBs record);

    int insertSelective(EntBasicInfoWithBLOBs record);

    List<EntBasicInfoWithBLOBs> selectByExampleWithBLOBs(EntBasicInfoExample example);

    List<EntBasicInfo> selectByExample(EntBasicInfoExample example);

    List<EntBasicInfo> selectByNameList(@Param("entList")List<String> entList);
    List<EntBasicInfo> selectByUidList(@Param("entList")List<String> entList);
    EntBasicInfo selectByName(@Param("entName")String entName);
    List<EntBasicInfo> selectLikeName(@Param("entName")String entName);
}