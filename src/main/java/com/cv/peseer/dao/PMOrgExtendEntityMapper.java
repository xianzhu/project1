package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.PMOrgExtendEntity;

public interface PMOrgExtendEntityMapper {
    int insert(PMOrgExtendEntity record);

    int insertSelective(PMOrgExtendEntity record);

    List<PMOrgExtendEntity> getOrgExtends(String orgId);
}