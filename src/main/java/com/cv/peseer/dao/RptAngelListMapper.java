package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.RptAngelList;

public interface RptAngelListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RptAngelList record);

    int insertSelective(RptAngelList record);

    RptAngelList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptAngelList record);

    int updateByPrimaryKeyWithBLOBs(RptAngelList record);

    int updateByPrimaryKey(RptAngelList record);

    List<RptAngelList> getAngelList();
}