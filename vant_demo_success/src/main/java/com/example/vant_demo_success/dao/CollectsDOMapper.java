package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.CollectsDO;
import org.apache.ibatis.annotations.Param;

public interface CollectsDOMapper {
    CollectsDO selectByUserId(@Param("userId") Integer userId);

    int initCollects(CollectsDO collectsDO);

    int deleteByPrimaryKey(Integer userid);

    int insert(CollectsDO record);

    int insertSelective(CollectsDO record);

    CollectsDO selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(CollectsDO record);

    int updateByPrimaryKeyWithBLOBs(CollectsDO record);

    int setUserCollects(@Param("userId") Integer userId, @Param("collect") String collect);

}