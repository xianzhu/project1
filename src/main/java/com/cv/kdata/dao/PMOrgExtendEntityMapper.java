package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.PMOrgExtendEntity;

public interface PMOrgExtendEntityMapper {
    int insert(PMOrgExtendEntity record);

    int insertSelective(PMOrgExtendEntity record);

    List<PMOrgExtendEntity> getOrgExtends(String orgId);
}