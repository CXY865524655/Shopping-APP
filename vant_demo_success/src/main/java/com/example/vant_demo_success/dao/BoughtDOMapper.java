package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.BoughtDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoughtDOMapper {
    int deleteByPrimaryKey(Integer userid);


    int insert(BoughtDO record);


    int insertSelective(BoughtDO record);

    int initBought(BoughtDO record);

    BoughtDO selectByPrimaryKey(Integer userid);

    List<BoughtDO> initOrder();

    int updateByPrimaryKeySelective(BoughtDO record);

    int updateUnAssess(
            @Param("userId") Integer userId,
            @Param("unassess") String unassess
    );

    int updateByPrimaryKeyWithBLOBs(BoughtDO record);

    int pay_success(
            @Param("userId") Integer userId,
            @Param("bought") String bought,
            @Param("unreceived") String unreceived,
            @Param("unassess") String unassess
    );
}