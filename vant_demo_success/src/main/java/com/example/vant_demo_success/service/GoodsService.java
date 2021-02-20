package com.example.vant_demo_success.service;

import com.example.vant_demo_success.dataobject.CategoryDO;
import com.example.vant_demo_success.dataobject.GoodsListDO;
import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.GoodsModel.GoodsModel;
import com.example.vant_demo_success.service.model.products.ProductModel;

import java.util.List;

public interface GoodsService {
    void delOrder(Integer userid, String orderID, String orderStatus, String goodname);
    List<OrderModel> initOrder();
    void updateGoodAssess(Integer goodsid, Integer num, Integer buyNum);
    List<ProductModel> searchGoods(String searchContent, Integer condition1);
    List<ProductModel> adminSearchGoods(String content);
    List<OrderModel> searchOrder(String content);
    void saveOrder(OrderModel orderModel) throws BusinessException;

    void delGood(Integer goodsid) throws BusinessException;
    void saveGood(GoodsListDO goodsListDO) throws BusinessException;
    int addGood(GoodsListDO goodsListDO) throws BusinessException;
    List<ProductModel> initAdminGoods();
    GoodsModel getGoodsList();
    GoodDetailsModel getGoodsDetails(Integer goodsid);

    List<CategoryDO> getCategory(Integer num) throws BusinessException;
    void delGoodImgPath(String path, Integer goodid);
    void delGoodImgPathDetail(String path, Integer goodid);

    String getGoodImgPath(Integer goodsid);
    String getDetailPath(Integer goodsid);
    void updateGoodImage(Integer goodId, String imgPath) throws BusinessException;
    void updateDetail(Integer goodId, String imgPath) throws BusinessException;
    // 获取所有大标签
    List<CategoryModel> getCategoryList();

    // 获取对应大标签下的子标签
    List<SubCategoryModel> getSubCategoryList(Integer categoryId);

    // 获取子标签商品
    List<ProductModel> getSubProductList(
            Integer goods_type,
            Integer sub_type
    );

    String getAssess(
            Integer goodsid
    );

    List<GoodAssessModel> getGoodAssess(
            Integer goodsid
    );

    HomeModel initHomeData();

    void setAssess(
            Integer goodsid,
            String assess
    );
    // initCategory
    InitCategory getInitCategory(
            // 全部 大 标签
            List<CategoryModel> categoryList,

            // 默认第一个大标签下的 所有 子标签
            List<SubCategoryModel> subCategoryModelListm,

            // 默认第一个大标签下的第一个子标签的所有商品
            List<ProductModel> subProductList
    );





}
