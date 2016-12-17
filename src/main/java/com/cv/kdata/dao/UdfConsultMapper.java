package com.cv.kdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.UdfConsult;

public interface UdfConsultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UdfConsult record);

    int insertSelective(UdfConsult record);

    UdfConsult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdfConsult record);

    int updateByPrimaryKeyWithBLOBs(UdfConsult record);

    int updateByPrimaryKey(UdfConsult record);

    List<UdfConsult> selectByUid(@Param("uid")String uid);
}