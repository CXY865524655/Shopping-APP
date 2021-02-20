package com.example.vant_demo_success.controller;

import com.example.vant_demo_success.dataobject.CategoryDO;
import com.example.vant_demo_success.dataobject.GoodsListDO;
import com.example.vant_demo_success.err.BusinessException;

import com.example.vant_demo_success.response.CommonReturnType;
import com.example.vant_demo_success.service.GoodsService;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.GoodsModel.GoodsModel;
import com.example.vant_demo_success.service.model.products.ProductModel;
import com.example.vant_demo_success.service.utils.GoodsUtils;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;


/**
 * @Package_Name: com.example.vant_demo_success.controller
 * @ClassName: GoodsController
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/25 17:45
 */
@Controller("goods")     // 加入Controller容器，命名为 goods
@RequestMapping("/goods")   // URL请求路径
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")  // 跨域设置
public class GoodsController extends BaseController{

    @Autowired
    private GoodsService goodsService;


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getGoodsList", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getGoodsList() throws BusinessException{

        GoodsModel goodsList = goodsService.getGoodsList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("HomeData", goodsList);

        return CommonReturnType.create(jsonObject);
    }

    @RequestMapping(value = "/searchGoods", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType searchGoods(
            @Param("searchContent") String searchContent,
            @Param("condition1") Integer condition1
    ) throws BusinessException{
        List<ProductModel> productModels = goodsService.searchGoods(searchContent,condition1);

        return CommonReturnType.create(productModels);
    }

    @RequestMapping(value = "/adminSearchGoods", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType adminSearchGoods(
            @Param("content") String content
    ) throws BusinessException{
        List<ProductModel> productModels = goodsService.adminSearchGoods(content);

        return CommonReturnType.create(productModels);
    }

    @RequestMapping(value = "/searchOrder", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType searchOrder(
            @Param("content") String content
    ) throws BusinessException{
        List<OrderModel> orderModels = goodsService.searchOrder(content);
        return CommonReturnType.create(orderModels);
    }




    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/delGood", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType delGood(
            @Param("goodsid") Integer goodsid
    ) throws BusinessException{

        goodsService.delGood(goodsid);

        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveGood", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType saveGood(
            @Param("goodDetail") String goodDetail
    ) throws BusinessException{
        GoodsListDO goodDO = getGoodDO(goodDetail);
        goodsService.saveGood(goodDO);
        return CommonReturnType.create(null);
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveOrder", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType saveOrder(
            @Param("orderDetail") String orderDetail
    ) throws BusinessException{
        OrderModel orderModel = getOrderModel(orderDetail);

        goodsService.saveOrder(orderModel);
        return CommonReturnType.create(null);
    }

    private OrderModel getOrderModel(String orderDetail) {
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(orderDetail);
        String address = jsonObject.getString("address");
        String getGoodPeople = jsonObject.getString("getGoodPeople");
        String goodName = jsonObject.getString("goodName");
        String image = jsonObject.getString("image");
        BigDecimal mallPrice  = jsonObject.getBigDecimal("mallPrice");
        Integer num = jsonObject.getInteger("num");
        String orderDate = jsonObject.getString("orderDate");
        String orderID = jsonObject.getString("orderID");
        String orderStatus = jsonObject.getString("orderStatus");
        String telephone = jsonObject.getString("telephone");
        Integer userid = jsonObject.getInteger("userid");

        OrderModel orderModel = new OrderModel();
        orderModel.setAddress(address);
        orderModel.setGetGoodPeople(getGoodPeople);
        orderModel.setGoodName(goodName);
        orderModel.setImage(image);
        orderModel.setMallPrice(mallPrice);
        orderModel.setNum(num);
        orderModel.setOrderDate(orderDate);
        orderModel.setOrderID(orderID);
        orderModel.setOrderStatus(orderStatus);
        orderModel.setTelephone(telephone);
        orderModel.setUserid(userid);

        return orderModel;
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/addGood", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType addGood(
            @Param("goodDetail") String goodDetail
    ) throws BusinessException{
        GoodsListDO goodDO = getGoodDO(goodDetail);
        int i = goodsService.addGood(goodDO);

        return CommonReturnType.create(i);
    }

    private GoodsListDO getGoodDO(String goodDetail) {
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodDetail);
        BigDecimal assess = jsonObject.getBigDecimal("assess");
        String describes = jsonObject.getString("describes");
        String details = jsonObject.getString("details");
        String goodsname = jsonObject.getString("goodsname");
        String image = jsonObject.getString("image");

        Integer goodsType = jsonObject.getInteger("goodsType");
        Integer goodsid = jsonObject.getInteger("goodsid");
        BigDecimal mallprice = jsonObject.getBigDecimal("mallprice");
        BigDecimal price = jsonObject.getBigDecimal("price");
        Integer sales = jsonObject.getInteger("sales");
        Integer stock = jsonObject.getInteger("stock");
        Integer subType = jsonObject.getInteger("subType");

        GoodsListDO goodsListDO = new GoodsListDO();
        goodsListDO.setAssess(assess);
        goodsListDO.setDescribes(describes);
        goodsListDO.setDetails(details);
        goodsListDO.setGoodsname(goodsname);
        goodsListDO.setImage(image);
        goodsListDO.setGoodsType(goodsType);
        goodsListDO.setGoodsid(goodsid);
        goodsListDO.setMallprice(mallprice);
        goodsListDO.setPrice(price);
        goodsListDO.setSales(sales);
        goodsListDO.setStock(stock);
        goodsListDO.setSubType(subType);
        return goodsListDO;
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveGoodImg", method = {RequestMethod.POST},produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public CommonReturnType saveGoodImg(
            @RequestParam("file") MultipartFile[] file
    ) throws BusinessException, IOException {
        //      D:\Code\Code\img    //tmp/imgs/goods/
            String path = "D:/Code/Code/img/";
//            String path = "C:/Users/cxy/Desktop/imgs/";
            StringBuilder iPath = new StringBuilder();
        if (file.length >= 1) {
            for (int i=0; i<file.length; i++) {
                File file1 = new File(path + file[i].getOriginalFilename());
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdirs();
                }
                if (!file1.exists()) {
                    file[i].transferTo(file1);
                }else {
                    System.out.println("已经存在同名文件");
                }
            }
        }
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveDetail", method = {RequestMethod.POST},produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public CommonReturnType saveDetail(
            @RequestParam("file") MultipartFile[] file
    ) throws BusinessException, IOException {
        //    D:/Code/Code/img/details/   //tmp/imgs/goods/details/
        String path = "D:/Code/Code/img/details";
        StringBuilder iPath = new StringBuilder();
        if (file.length >= 1) {
            for (int i=0; i<file.length; i++) {
                File file1 = new File(path + file[i].getOriginalFilename());
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdirs();
                }
                if (!file1.exists()) {
                    file[i].transferTo(file1);
                }else {
                    System.out.println("已经存在同名文件");
                }
            }
        }
        return CommonReturnType.create(null);
    }




    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/delGoodImgPath", method = {RequestMethod.POST},produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public CommonReturnType delGoodImgPath(
            @RequestParam("path") String path,
            @RequestParam("goodid") Integer goodid
    ) throws BusinessException, IOException {
    goodsService.delGoodImgPath(path, goodid);
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/delGoodImgPathDetail", method = {RequestMethod.POST},produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public CommonReturnType delGoodImgPathDetail(
            @RequestParam("path") String path,
            @RequestParam("goodid") Integer goodid
    ) throws BusinessException, IOException {
        goodsService.delGoodImgPathDetail(path, goodid);
        return CommonReturnType.create(null);
    }



    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/updateGoodImgPath", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateGoodImgPath(
            @RequestParam("filePath") String filePath,
            @RequestParam("goodid") Integer goodid
    ) throws BusinessException, IOException {
//        D:/Code/Code/img/    //tmp/imgs/goods/
        String path = "D:/Code/Code/img/";
        StringBuilder stringBuilder = null;
        int c = filePath.indexOf(";");
        if(c >= 0) {
            // 多个
            String[] split = filePath.split(";");
            for (int i=0; i<split.length;i++) {
                split[i] = path + split[i];
                if (StringUtils.isEmpty(stringBuilder)) {
                    stringBuilder = new StringBuilder(split[i]);
                }else {
                    stringBuilder.append(";").append(split[i]);
                }
            }
        }else {
            // 一个
            if (StringUtils.isEmpty(stringBuilder)) {
                stringBuilder = new StringBuilder(path + filePath);
            }else {
                stringBuilder.append(path + filePath);
            }
        }

        String s = stringBuilder.toString();
        System.out.println(stringBuilder.toString());

        goodsService.updateGoodImage(goodid, s);
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/updateDetailPath", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateDetailPath(
            @RequestParam("filePath") String filePath,
            @RequestParam("goodid") Integer goodid
    ) throws BusinessException, IOException {
//        C:/Users/cxy/Desktop/imgs/details/    //tmp/imgs/goods/details/
        String path = "D:/Code/Code/img/details";
        StringBuilder stringBuilder = null;
        int c = filePath.indexOf(";");
        if(c >= 0) {
            // 多个
            String[] split = filePath.split(";");
            for (int i=0; i<split.length;i++) {
                split[i] = path + split[i];
                if (StringUtils.isEmpty(stringBuilder)) {
                    stringBuilder = new StringBuilder(split[i]);
                }else {
                    stringBuilder.append(";").append(split[i]);
                }
            }
        }else {
            stringBuilder = new StringBuilder();
            if (!"".equals(filePath.trim())) {
                // 一个
                if (StringUtils.isEmpty(stringBuilder)) {
                    stringBuilder = new StringBuilder(path + filePath);
                }else {
                    stringBuilder.append(path + filePath);
                }
            }
        }

        String s = stringBuilder.toString();

        goodsService.updateDetail(goodid, s);
        return CommonReturnType.create(null);
    }




    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getGoodImg", method = {RequestMethod.POST},produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getGoodImg(
            @RequestParam("path") String path
            ) throws BusinessException, IOException {

         File file1 = new File(path);
        InputStream inputStream = new FileInputStream(file1);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        return bytes;
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getDetailImg", method = {RequestMethod.POST},produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] getDetailImg(
            @RequestParam("path") String path
    ) throws BusinessException, IOException {

        File file1 = new File(path);
        InputStream inputStream = new FileInputStream(file1);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        return bytes;
    }



    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getCategory", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getCategory(
            @Param("num") Integer num
    ) throws BusinessException{
        List<CategoryDO> category = goodsService.getCategory(num);
        List<CategoryModel> categoryModels = categoryDOtoModel(category);

        return CommonReturnType.create(categoryModels);
    }

    private List<CategoryModel> categoryDOtoModel(List<CategoryDO> categoryDOS) {
        if (categoryDOS == null) {
            return null;
        }

        List<CategoryModel> categoryModels = new ArrayList<>();
        CategoryModel categoryModel = null;
        for (int i=0; i<categoryDOS.size(); i++) {
            categoryModel = new CategoryModel();
            BeanUtils.copyProperties(categoryDOS.get(i), categoryModel);
            categoryModels.add(categoryModel);
        }
        return categoryModels;

    }







    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/initHomeData", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType initHomeData() throws BusinessException{
        HomeModel homeData = goodsService.initHomeData();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("homeData", homeData);

        return CommonReturnType.create(jsonObject);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/sendAssess", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType sendAssess(
            @Param("goodsid") Integer goodsid,
            @Param("assess") String assess,
            @Param("num") Integer num,
            @Param("buyNum") Integer buyNum
    ) throws BusinessException{
        goodsService.setAssess(goodsid, assess);
        goodsService.updateGoodAssess(goodsid,num,buyNum);

        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/initCategory", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType initCategory() throws BusinessException{

        // 全部 大 标签
        List<CategoryModel> categoryList = goodsService.getCategoryList();

        // 默认第一个大标签下的 所有 子标签
        List<SubCategoryModel> subCategoryModelList = goodsService.getSubCategoryList(1);

        // 默认第一个大标签下的第一个子标签的所有商品
        List<ProductModel> subProductList = goodsService.getSubProductList(1, 1);

        // initCategory
        InitCategory initCategory = goodsService.getInitCategory(categoryList, subCategoryModelList, subProductList);

        JSONObject jsonObject = GoodsUtils.initCategory(initCategory);
        jsonObject.put("success_msg", "success！");

        return CommonReturnType.create(jsonObject);
    }

    @RequestMapping(value = "/getSubProductList", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getSubProductList(
            @RequestParam(name = "categoryId") Integer categoryId,
            @RequestParam(name = "subCategoryId") Integer subCategoryId
    ) throws BusinessException{

        // 默认第一个大标签下的 所有 子标签
        List<SubCategoryModel> subCategoryModelList = goodsService.getSubCategoryList(categoryId);

        // 默认第一个大标签下的第一个子标签的所有商品
        List<ProductModel> subProductList = goodsService.getSubProductList(categoryId, subCategoryId);


        JSONObject jsonObject = GoodsUtils.getSubProductList(subCategoryModelList, subProductList);
        jsonObject.put("success_msg", "success！");

        return CommonReturnType.create(jsonObject);
    }

    @RequestMapping(value = "/getGoodAssess", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getGoodAssess(
            @RequestParam(name = "goodsid") Integer goodsid
    ) throws BusinessException{
        List<GoodAssessModel> goodAssess = goodsService.getGoodAssess(goodsid);
        return CommonReturnType.create(goodAssess);
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getGoodDetails", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getGoodDetails(@RequestParam(name = "goodsid") Integer goodsid) throws BusinessException{

        GoodDetailsModel goodsDetails = goodsService.getGoodsDetails(goodsid);
        JSONObject goodDetails = new JSONObject();
        goodDetails.put("goodDetails", goodsDetails);
        return CommonReturnType.create(goodDetails);
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/initAdminGoods", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType initAdminGoods() throws BusinessException{

        List<ProductModel> productModels = goodsService.initAdminGoods();

        return CommonReturnType.create(productModels);
    }

    @RequestMapping(value = "/initOrder", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType initOrder() throws BusinessException{

        List<OrderModel> orderModels = goodsService.initOrder();

        return CommonReturnType.create(orderModels);
    }
    @RequestMapping(value = "/delOrder", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType delOrder(
            @Param("userid") Integer userid,
            @Param("orderID") String orderID,
            @Param("orderStatus") String orderStatus,
            @Param("goodname") String goodname
    ) throws BusinessException{

        goodsService.delOrder(userid, orderID, orderStatus, goodname);

        return CommonReturnType.create(null);
    }
}
