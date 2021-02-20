package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.GoodsListDO;
import com.example.vant_demo_success.dataobject.GoodsListDOWithBLOBs;
import com.example.vant_demo_success.service.model.products.ProductModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsListDOMapper {
    void addSales(@Param("goodsid") Integer goodsid, @Param("num") Integer num);
    void updateStock(@Param("goodsid") Integer goodsid, @Param("num") Integer num);
    void updateGoodAssess(
            @Param("goodsid") Integer goodsid,
            @Param("num") Integer num,
            @Param("buyNum") Integer buyNum
    );
    List<GoodsListDO> adminSearchGoods(@Param("content") String content);
    List<GoodsListDO> searchGoods1(@Param("searchContent") String searchContent);
    List<GoodsListDO> searchGoods2(@Param("searchContent") String searchContent);
    List<GoodsListDO> searchGoods3(@Param("searchContent") String searchContent);
    List<GoodsListDO> searchGoods4(@Param("searchContent") String searchContent);

    List<Integer> getGoodPrice();
    List<Integer> getHeaderSwipes();
    List<Integer> getRecommends();
    List<ProductModel> initAdminGoods();
    String getGoodImgPath(@Param("goodsid")Integer goodsid);
    String getGoodImgPathDetail(@Param("goodsid")Integer goodsid);

    int saveGood(@Param("goodsListDO") GoodsListDO goodsListDO);
    int addGood(GoodsListDO goodsListDO);

    int deleteByPrimaryKey(Integer goodsid);

    int insert(GoodsListDO record);

    int insertSelective(GoodsListDO record);

    GoodsListDO selectByPrimaryKey(Integer goodsid);

    GoodsListDO selectByGoodsId(Integer goodsid);

    List<GoodsListDO> selectRecommendGoods();

    List<GoodsListDO> selectHotSelling();

    List<GoodsListDO> selectFloors(Integer goodsType);

    int updateByPrimaryKeySelective(GoodsListDO record);

    int updateByPrimaryKey(GoodsListDO record);

    int updateImagePathById(@Param("goodid")Integer goodid, @Param("imgPath") String imgPath);
    void updateDetailById(@Param("goodid")Integer goodid, @Param("details") String details);

    // 获取子标签的 Goods 列表
    List<GoodsListDO> getSubProductList(
            @Param("goods_type") Integer goods_type,
            @Param("sub_type") Integer sub_type
    );

    List<GoodsListDO> selectByCollectArray(@Param("goodIds") Integer[] goodIds);

    List<GoodsListDO> selectByCartArray(@Param("goodIds") Integer[] goodIds);


}