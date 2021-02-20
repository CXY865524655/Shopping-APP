package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.CartDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartDOMapper {
    int deleteByPrimaryKey(Integer userid);
    int cartNumUp1(
            @Param("userid") Integer userid,
            @Param("goodsid") Integer goodsid
    );

    int delPayedCartItem(
            @Param("userid") Integer userid,
            @Param("selecteds") Integer[] selecteds
    );

    int removePro(@Param("userid") Integer userid, @Param("selects") Integer[] selects);
    int cartAdd(CartDO cartDO);
    List<CartDO> getByUserId(@Param("userid") Integer userid);
    CartDO getByUseridAndGoodid(
            @Param("userid") Integer userid,
            @Param("goodsid") Integer goodsid
    );

    int insert(CartDO record);

    int insertSelective(CartDO record);

    CartDO selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(CartDO record);

    int updateByPrimaryKey(CartDO record);
}