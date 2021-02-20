package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.CartDO;
import com.example.vant_demo_success.dataobject.CartDO_old;
import org.apache.ibatis.annotations.Param;

public interface CartDOMapper_old {
    int deleteByPrimaryKey(Integer userid);


    int insert(CartDO_old record);


    int insertSelective(CartDO_old record);

    int initCart(CartDO_old cartDO);

    CartDO_old selectByPrimaryKey(Integer userid);

    CartDO_old getByUserId(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(CartDO_old record);

    int setUserCart(@Param("userId") Integer userId, @Param("cartList") String cartList);

    int updateByPrimaryKeyWithBLOBs(CartDO_old record);
}