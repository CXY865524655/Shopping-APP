package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.HomeDO;
import com.example.vant_demo_success.dataobject.HomeDOWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface HomeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomeDO record);

    int insertSelective(HomeDO record);

    HomeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeDO record);

    int updateByPrimaryKeyWithBLOBs(HomeDO record);

    int updateGoodPrice(@Param("goodPrice") String goodPrice);
    int updateHeaderSwipes(@Param("headerSwipes") String headerSwipes);
    int updateRecommends(@Param("recommends") String recommends);
    int updateTime(@Param("time") Long time);
}