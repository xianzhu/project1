package com.cv.kdata.dao;

import com.cv.kdata.model.UserInfo;
import com.cv.kdata.model.UserInfoWithBLOBs;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String uid);

    int insert(UserInfoWithBLOBs record);

    int insertSelective(UserInfoWithBLOBs record);

    UserInfoWithBLOBs selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(UserInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserInfoWithBLOBs record);

    int updateByPrimaryKey(UserInfo record);
}