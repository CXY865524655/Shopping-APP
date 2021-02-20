package com.example.vant_demo_success.service.utils;

import com.example.vant_demo_success.service.model.InitCategory;
import com.example.vant_demo_success.service.model.SubCategoryModel;
import com.example.vant_demo_success.service.model.products.ProductModel;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.service.utils
 * @ClassName: GoodsUtils
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/4 0:33
 */
public class GoodsUtils {
    public static JSONObject initCategory(InitCategory initCategory) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoryList", initCategory.getCategoryList());
        jsonObject.put("subCategoryModelList", initCategory.getSubCategoryModelList());
        jsonObject.put("subProductList", initCategory.getSubProductList());
        return jsonObject;
    }

    public static JSONObject getSubProductList(
            List<SubCategoryModel> subCategoryModelList,
            List<ProductModel> subProductList
            ) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subCategoryModelList", subCategoryModelList);
        jsonObject.put("subProductList", subProductList);
        return jsonObject;
    }

    public static JSONObject getCollectsList(
            List<ProductModel> collect
    ) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("collects", collect);
        return jsonObject;
    }
    public static JSONObject getCartList(
            List<ProductModel> cartList
    ) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cart", cartList);
        return jsonObject;
    }
}
