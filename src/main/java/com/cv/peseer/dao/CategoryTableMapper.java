package com.cv.peseer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cv.peseer.model.CategoryTable;

public interface CategoryTableMapper {
    int deleteByPrimaryKey(Integer bizId);

    int insert(CategoryTable record);

    int insertSelective(CategoryTable record);

    CategoryTable selectByPrimaryKey(Integer bizId);

    int updateByPrimaryKeySelective(CategoryTable record);

    int updateByPrimaryKey(CategoryTable record);

    List<CategoryTable> getListbyDomain(@Param("list")List<String> domain);
    List<CategoryTable> getListbyBiz(@Param("list")List<String> biz);
    List<CategoryTable> getListbyMedia(@Param("list")List<String> meida);
}