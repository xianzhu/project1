package com.cv.peseer.dao;

import java.util.List;

import com.cv.peseer.model.RptPeFund;

public interface RptPeFundMapper {
    int insert(RptPeFund record);

    int insertSelective(RptPeFund record);

    List<RptPeFund> getRptPeList();
}