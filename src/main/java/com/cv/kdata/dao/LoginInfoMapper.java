package com.cv.kdata.dao;

import org.apache.ibatis.annotations.Param;

import com.cv.kdata.model.LoginInfo;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);

    LoginInfo selectByUserName(@Param("userName")String userName);

    LoginInfo selectByCookie(@Param("cookie")String cookie);
}