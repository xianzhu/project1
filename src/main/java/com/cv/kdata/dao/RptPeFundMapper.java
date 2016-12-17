package com.cv.kdata.dao;

import java.util.List;

import com.cv.kdata.model.RptPeFund;

public interface RptPeFundMapper {
    int insert(RptPeFund record);

    int insertSelective(RptPeFund record);

    List<RptPeFund> getRptPeList();
}