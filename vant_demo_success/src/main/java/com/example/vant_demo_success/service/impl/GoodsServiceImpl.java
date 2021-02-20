package com.example.vant_demo_success.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.vant_demo_success.dao.*;

import com.example.vant_demo_success.dataobject.*;

import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.err.EnBusinessError;
import com.example.vant_demo_success.service.GoodsService;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.GoodsModel.GoodsModel;
import com.example.vant_demo_success.service.model.GoodsModel.somethings.*;

import com.example.vant_demo_success.service.model.products.ProductModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SwipersDOMapper swipersDOMapper;

    @Autowired
    private UserInfoDOMapper userInfoDOMapper;

    @Autowired
    private GoodsListDOMapper goodsListDOMapper;

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Autowired
    private SubcategoryDOMapper subcategoryDOMapper;

    @Autowired
    private AssessDOMapper assessDOMapper;

    @Autowired
    private HomeDOMapper homeDOMapper;

    @Autowired
    private BoughtDOMapper boughtDOMapper;


    @Override
    public void delOrder(Integer userid, String orderID, String orderStatus, String goodname) {
        BoughtDO boughtDO = boughtDOMapper.selectByPrimaryKey(userid);
        String bought = boughtDO.getBought();
        String unassess = boughtDO.getUnassess();
        if ("已完成".equals(orderStatus)) {
            JSONArray objects = JSONObject.parseArray(bought);
            Integer removeIndex = null;
            for (int i=0; i<objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String orderID1 = jsonObject.getString("orderID");
                String name = jsonObject.getJSONObject("productModel").getString("goodsname");
                if (orderID1.equals(orderID) && name.equals(goodname)) {
                    removeIndex = i;
                    break;
                }
            }
            int i = removeIndex.intValue();
            objects.remove(i);
            String s = objects.toString();
            boughtDO.setBought(s);
        }else if ("未评价".equals(orderStatus)) {
            JSONArray objects = JSONObject.parseArray(bought);
            Integer removeIndex = null;
            for (int i=0; i<objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String orderID1 = jsonObject.getString("orderID");
                String name = jsonObject.getJSONObject("productModel").getString("goodsname");

                if (orderID1.equals(orderID) && name.equals(goodname)) {
                    removeIndex = i;
                    break;
                }
            }
            int i = removeIndex.intValue();
            objects.remove(i);
            String s = objects.toString();
            boughtDO.setBought(s);

            JSONArray objects1 = JSONObject.parseArray(unassess);
            Integer removeIndex1 = null;
            for (int j=0; j<objects1.size(); j++) {
                JSONObject jsonObject = objects1.getJSONObject(j);
                String orderID1 = jsonObject.getString("orderID");
                String name = jsonObject.getJSONObject("productModel").getString("goodsname");
                if (orderID1.equals(orderID) && name.equals(goodname)) {
                    removeIndex1 = j;
                    break;
                }
            }
            int i1 = removeIndex1.intValue();
            objects1.remove(i1);
            String s1 = objects1.toString();
            boughtDO.setUnassess(s1);
        }

        boughtDOMapper.updateByPrimaryKeySelective(boughtDO);
    }

    @Override
    public List<OrderModel> initOrder() {
        List<BoughtDO> boughtDOS = boughtDOMapper.initOrder();
        List<OrderModel> boughtModels = new ArrayList<>();
        List<String> unassessInts = new ArrayList<>();

        String bought = null;
        String unassess = null;
        String unpaid = null;
        String unreceived = null;
        Integer userid = null;
        for (int i=0; i<boughtDOS.size(); i++) {
            BoughtDO boughtDO = boughtDOS.get(i);
            bought = boughtDO.getBought();
            unassess = boughtDO.getUnassess();
//            unpaid = boughtDO.getUnpaid();
//            unreceived = boughtDO.getUnreceived();
            userid = boughtDO.getUserid();

            JSONArray objects = JSONObject.parseArray(bought);
            JSONArray objects1 = JSONObject.parseArray(unassess);

            // bought
            if (!StringUtils.isEmpty(objects) && objects.size()>=1) {
                for (int k=0; k<objects.size(); k++) {
                    JSONObject jsonObject = objects.getJSONObject(k);
                    OrderModel orderModel = new OrderModel();
                    orderModel.setUserid(userid);
                    orderModel.setTelephone(jsonObject.getJSONObject("selectedAddress").getString("tel"));
                    orderModel.setOrderStatus("已完成");
                    orderModel.setOrderID(jsonObject.getString("orderID"));
                    orderModel.setMallPrice(jsonObject.getJSONObject("productModel").getBigDecimal("mallprice"));
                    orderModel.setImage(jsonObject.getJSONObject("productModel").getString("image"));
                    orderModel.setNum(jsonObject.getInteger("num"));

                    String orderID = jsonObject.getString("orderID");
                    String year = orderID.substring(0,4);
                    String month = orderID.substring(4,6);
                    String day = orderID.substring(6,8);
                    orderModel.setOrderDate(year+"-"+month+"-"+day);
                    orderModel.setGoodName(jsonObject.getJSONObject("productModel").getString("goodsname"));
                    orderModel.setGetGoodPeople(jsonObject.getJSONObject("selectedAddress").getString("name"));
                    orderModel.setAddress(jsonObject.getJSONObject("selectedAddress").getString("address"));
                    boughtModels.add(orderModel);
                }
            }

            if (!StringUtils.isEmpty(objects1) && objects1.size()>=1) {
                for (int i1=0; i1<objects1.size(); i1++) {
                    JSONObject jsonObject = objects1.getJSONObject(i1);
//                    unassessInts.add(jsonObject.getString("orderID"));
                    String id1 = jsonObject.getString("orderID");
                    String name1 = jsonObject.getJSONObject("productModel").getString("goodsname");
                    for (int k=0; k<boughtModels.size(); k++) {
                        String id2 = boughtModels.get(k).getOrderID();
                        String name2 = boughtModels.get(k).getGoodName();
                        if (id1.equals(id2) && name1.equals(name2)) {
                            boughtModels.get(k).setOrderStatus("未评价");
                        }
                    }
                }
            }
        }

//        if (unassessInts.size() >= 1) {
//            for (int i=0; i<boughtModels.size(); i++) {
//                String orderID = boughtModels.get(i).getOrderID();
//                if (unassessInts.contains(orderID)) {
//                    boughtModels.get(i).setOrderStatus("未评价");
//                }
//            }
//        }
        return boughtModels;
    }

    @Override
    public void updateGoodAssess(Integer goodsid, Integer num, Integer buyNum) {
        goodsListDOMapper.updateGoodAssess(goodsid,num,buyNum);
    }

    @Override
    public List<ProductModel> searchGoods(String searchContent,Integer condition1) {
        List<GoodsListDO> goodsListDOS = null;
        if (condition1==0) {
            goodsListDOS = goodsListDOMapper.searchGoods1(searchContent);
        }else if (condition1==1) {
            goodsListDOS = goodsListDOMapper.searchGoods2(searchContent);
        }else if (condition1==2) {
            goodsListDOS = goodsListDOMapper.searchGoods3(searchContent);
        }else if (condition1==3) {
            goodsListDOS = goodsListDOMapper.searchGoods4(searchContent);
        }
        List<ProductModel> productModels = GoodsListDOsToProductModels(goodsListDOS);
        return productModels;
    }

    @Override
    public List<ProductModel> adminSearchGoods(String content) {
        List<GoodsListDO> goodsListDOS = null;
        goodsListDOS = goodsListDOMapper.adminSearchGoods(content);
        List<ProductModel> productModels = GoodsListDOsToProductModels(goodsListDOS);
        return productModels;
    }


    @Override
    public List<OrderModel> searchOrder(String content) {
        List<OrderModel> search = new ArrayList<>();
        OrderModel orderModel = null;
        List<OrderModel> orderModels = initOrder();
        for (int i=0; i<orderModels.size(); i++){
            orderModel = orderModels.get(i);
            String orderID = orderModel.getOrderID();
            if (orderID.contains(content)) {
                search.add(orderModel);
            }
        }
        return search;
    }

    @Override
    public void delGood(Integer goodsid) throws BusinessException {
        try {
            goodsListDOMapper.deleteByPrimaryKey(goodsid);
            assessDOMapper.deleteByPrimaryKey(goodsid);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.DEL_GOOD_ERR);
        }

    }

    @Override
    public void saveGood(GoodsListDO goodsListDO) throws BusinessException {
        try {
            goodsListDOMapper.updateByPrimaryKeySelective(goodsListDO);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.SAVE_GOOD_ERR);
        }
    }

    @Override
    public void saveOrder(OrderModel orderModel) throws BusinessException {
        BoughtDO boughtDO = boughtDOMapper.selectByPrimaryKey(orderModel.getUserid());
        String orderStatus = orderModel.getOrderStatus();
        String orderID = orderModel.getOrderID();
        String bought = boughtDO.getBought();
        String unassess = boughtDO.getUnassess();

        if ("已完成".equals(orderStatus)) {
            JSONArray objects = JSONObject.parseArray(bought);
            JSONObject search = new JSONObject();
            int index = -1;
            for (int i=0; i<objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String orderID1 = jsonObject.getString("orderID");
                if (orderID1.equals(orderID)) {
                    search = jsonObject;
                    index = i;
                    break;
                }
            }
            JSONObject selectedAddress = search.getJSONObject("selectedAddress");
            selectedAddress.replace("address", orderModel.getAddress());
            selectedAddress.replace("name", orderModel.getGetGoodPeople());
            selectedAddress.replace("tel", orderModel.getTelephone());

            search.replace("selectedAddress", selectedAddress);
            Object remove = objects.remove(index);
            objects.add(index, search);
            String s = objects.toString();
            boughtDO.setBought(s);
        }else if ("未评价".equals(orderStatus)) {
            JSONArray objects = JSONObject.parseArray(bought);
            JSONObject search = new JSONObject();
            int index = -1;
            for (int i=0; i<objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String orderID1 = jsonObject.getString("orderID");
                if (orderID1.equals(orderID)) {
                    search = jsonObject;
                    index = i;
                    break;
                }
            }
            JSONObject selectedAddress = search.getJSONObject("selectedAddress");
            selectedAddress.replace("address", orderModel.getAddress());
            selectedAddress.replace("name", orderModel.getGetGoodPeople());
            selectedAddress.replace("tel", orderModel.getTelephone());

            search.replace("selectedAddress", selectedAddress);
            Object remove = objects.remove(index);
            objects.add(index, search);
            String s = objects.toString();
            boughtDO.setBought(s);



            JSONArray objects1 = JSONObject.parseArray(unassess);
            JSONObject search1 = new JSONObject();
            int index1 = -1;
            for (int j=0; j<objects1.size(); j++) {
                JSONObject jsonObject1 = objects1.getJSONObject(j);
                String orderID1 = jsonObject1.getString("orderID");
                if (orderID1.equals(orderID)) {
                    search1 = jsonObject1;
                    index1 = j;
                    break;
                }
            }
            JSONObject selectedAddress1 = search1.getJSONObject("selectedAddress");
            selectedAddress1.replace("address", orderModel.getAddress());
            selectedAddress1.replace("name", orderModel.getGetGoodPeople());
            selectedAddress1.replace("tel", orderModel.getTelephone());

            search1.replace("selectedAddress", selectedAddress1);
            Object remove1 = objects1.remove(index1);
            objects1.add(index1, search1);
            String s1 = objects1.toString();
            boughtDO.setUnassess(s1);
        }
        boughtDOMapper.updateByPrimaryKeySelective(boughtDO);
    }



    @Override
    public int addGood(GoodsListDO goodsListDO) throws BusinessException {
        try {
            goodsListDOMapper.addGood(goodsListDO);
            Integer goodsid = goodsListDO.getGoodsid();

            AssessDO assessDO = new AssessDO();
            assessDO.setGoodsid(goodsid);
            assessDO.setAssess("");
            assessDOMapper.insertSelective(assessDO);

            return goodsid;
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.ADD_GOOD_ERR);
        }
    }


    @Override
    public List<ProductModel> initAdminGoods() {
        List<ProductModel> productModels = goodsListDOMapper.initAdminGoods();
        return productModels;
    }

    @Override
    public GoodsModel getGoodsList() {
        List<SwipersDO> swipersDOS = swipersDOMapper.select();
        List<GoodsListDO> goodsListDOS = goodsListDOMapper.selectRecommendGoods();
        List<GoodsListDO> hotSelling = goodsListDOMapper.selectHotSelling();
        String floorName1 = "新鲜水果";
        String floorName2 = "中外名酒";
        String floorName3 = "营养奶品";
        String floorsName[] = {floorName1, floorName2, floorName3};
        List<GoodsListDO> floors1 = goodsListDOMapper.selectFloors(1);
        List<GoodsListDO> floors2 = goodsListDOMapper.selectFloors(2);
        List<GoodsListDO> floors3 = goodsListDOMapper.selectFloors(3);

        List<CategoryDO> select = categoryDOMapper.select();
        List<Category> categories = categoryCovertFromCategoryDO(select);
        for (int i=0; i<categories.size(); i++){
            Integer categoryid = categories.get(i).getCategoryid();
            List<SubcategoryDO> subcategoryDOS = subcategoryDOMapper.selectByCategoryId(categoryid);
            List<SubCategory> subCategories = subCategoryCovertFromSubcategoryDO(subcategoryDOS);
            categories.get(i).setSubCategories(subCategories);
        }

        List<RecommendGoods> recommendGoods = recommendGoodsCovertFromGoodsListDO(goodsListDOS);
        List<HotSellGoods> hotSellGoods = hotSellGoodsCovertFromGoodsListDO(hotSelling);
        List<SwiperImage> swiperImages = covertFromSwipersDO(swipersDOS);
        List<GoodsFloor> goodsFloors1 = floorsCovertFromGoodsListDO(floors1);
        List<GoodsFloor> goodsFloors2 = floorsCovertFromGoodsListDO(floors2);
        List<GoodsFloor> goodsFloors3 = floorsCovertFromGoodsListDO(floors3);

        AdvertesPicture advertesPicture = new AdvertesPicture();
        advertesPicture.setPICTURE_ADDRESS("http://images.baixingliangfan.cn/advertesPicture/20180404/20180404085441_850.gif");

        GoodsModel goodsModel = mergeGoodsModels(
                swiperImages,
                recommendGoods,
                hotSellGoods,
                goodsFloors1,
                goodsFloors2,
                goodsFloors3,
                floorsName,
                categories,
                advertesPicture);
        return goodsModel;
    }


    @Override
    public HomeModel initHomeData() {
//        HomeDataModel homeData = new HomeDataModel();
        HomeModel homeData = new HomeModel();
        HomeDO homeDO = homeDOMapper.selectByPrimaryKey(1);
        Integer id = homeDO.getId();
        Long time = homeDO.getTime();
        String goodprice = homeDO.getGoodprice();
        String headerswipes = homeDO.getHeaderswipes();
        String recommends1 = homeDO.getRecommends();

        homeData.setId(id);
        homeData.setTime(time);


        if (StringUtils.isEmpty(goodprice) || "".equals(goodprice.trim())) {
            List<Integer> goodPrice = goodsListDOMapper.getGoodPrice();
            String s = JSONObject.toJSONString(goodPrice);
            homeDOMapper.updateGoodPrice(s);
        }
        if (StringUtils.isEmpty(headerswipes) || "".equals(headerswipes.trim())) {
            List<Integer> headerSwipes = goodsListDOMapper.getHeaderSwipes();
            String s = JSONObject.toJSONString(headerSwipes);
            homeDOMapper.updateHeaderSwipes(s);
        }
        if (StringUtils.isEmpty(recommends1) || "".equals(recommends1.trim())) {
            List<Integer> recommends = goodsListDOMapper.getRecommends();
            String s = JSONObject.toJSONString(recommends);
            homeDOMapper.updateRecommends(s);
        }

        HomeDO homeDO2 = homeDOMapper.selectByPrimaryKey(1);

        String goodprice1 = homeDO2.getGoodprice().trim();
        goodprice1 = goodprice1.replace("[", "").replace("]", "");
        String[] split = goodprice1.split(",");
        List<ProductModel> productModels = new ArrayList<>();
        ProductModel productModel = null;
        for (int i=0; i<split.length; i++) {
            Integer s = Integer.valueOf(split[i]);
            GoodsListDO goodsListDO = goodsListDOMapper.selectByPrimaryKey(s);
            productModel = new ProductModel();
            BeanUtils.copyProperties(goodsListDO, productModel);
            productModels.add(productModel);
        }
        homeData.setGoodprice(productModels);



        String headerswipes1 = homeDO2.getHeaderswipes().trim();
        headerswipes1 = headerswipes1.replace("[", "").replace("]", "");
        String[] split1 = headerswipes1.split(",");
        List<ProductModel> productModels1 = new ArrayList<>();
        ProductModel productModel1 = null;
        for (int i=0; i<split1.length; i++) {
            Integer s = Integer.valueOf(split1[i]);
            GoodsListDO goodsListDO = goodsListDOMapper.selectByPrimaryKey(s);
            productModel1 = new ProductModel();
            BeanUtils.copyProperties(goodsListDO, productModel1);
            productModels1.add(productModel1);
        }
        homeData.setHeaderswipes(productModels1);

        String recommends = homeDO2.getRecommends().trim();
        recommends = recommends.replace("[", "").replace("]", "");
        String[] split2 = recommends.split(",");
        List<ProductModel> productModels2 = new ArrayList<>();
        ProductModel productModel2 = null;
        for (int i=0; i<split2.length; i++) {
            Integer s = Integer.valueOf(split2[i]);
            GoodsListDO goodsListDO = goodsListDOMapper.selectByPrimaryKey(s);
            productModel2 = new ProductModel();
            BeanUtils.copyProperties(goodsListDO, productModel2);
            productModels2.add(productModel2);
        }
        homeData.setRecommends(productModels2);

        return homeData;
    }

    @Override
    public GoodDetailsModel getGoodsDetails(Integer goodsid) {
        GoodsListDO goodsListDO = goodsListDOMapper.selectByGoodsId(goodsid);
        GoodDetailsModel goodDetailsModel = new GoodDetailsModel();
        BeanUtils.copyProperties(goodsListDO, goodDetailsModel);
        return goodDetailsModel;
    }

    @Override
    public List<CategoryDO> getCategory(Integer num) throws BusinessException {
        try {
            List<CategoryDO> category = categoryDOMapper.getCategory(num);
            return category;
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.GET_CATEGORY_ERR);
        }
    }

    @Override
    public void delGoodImgPath(String path, Integer goodid) {
        String goodImgPath = goodsListDOMapper.getGoodImgPath(goodid);
        String ll = null;
        int i = goodImgPath.indexOf(";");
        if (i >= 0) {
            String[] split = goodImgPath.split(";");
            Set<String> set=new HashSet<String>(Arrays.asList(split));
            if (set.contains(path)) {
                set.remove(path);
            }
            String[] nSp = set.toArray(new String[set.size()]);
            StringBuilder aa = new StringBuilder();

            for (int j=0; j<nSp.length; j++) {
                if (aa.length()==0) {
                    aa.append(nSp[j]);
                }else {
                    aa.append(";"+nSp[j]);
                }
            }
            ll = aa.toString();
        }else {
            if (path.equals(goodImgPath)) {
                ll = "";
            }
        }
        goodsListDOMapper.updateImagePathById(goodid,ll);
    }

    @Override
    public void delGoodImgPathDetail(String path, Integer goodid) {
        String goodImgPath = goodsListDOMapper.getGoodImgPathDetail(goodid);
        String ll = null;
        int i = goodImgPath.indexOf(";");
        if (i >= 0) {
            String[] split = goodImgPath.split(";");
            Set<String> set=new HashSet<String>(Arrays.asList(split));
            if (set.contains(path)) {
                set.remove(path);
            }
            String[] nSp = set.toArray(new String[set.size()]);
            StringBuilder aa = new StringBuilder();

            for (int j=0; j<nSp.length; j++) {
                if (aa.length()==0) {
                    aa.append(nSp[j]);
                }else {
                    aa.append(";"+nSp[j]);
                }
            }
            ll = aa.toString();
        }else {
            if (path.equals(goodImgPath)) {
                ll = "";
            }
        }
        goodsListDOMapper.updateDetailById(goodid,ll);
    }


    @Override
    public String getGoodImgPath(Integer goodsid) {
        String goodImgPath = goodsListDOMapper.getGoodImgPath(goodsid);

        return goodImgPath;
    }

    @Override
    public String getDetailPath(Integer goodsid) {
        String goodImgPath = goodsListDOMapper.getGoodImgPathDetail(goodsid);

        return goodImgPath;
    }

    @Override
    public void updateGoodImage(Integer goodId, String imgPath) throws BusinessException {
        try {
            goodsListDOMapper.updateImagePathById(goodId, imgPath);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.SAVE_GOOD_IMAGE_ERR);
        }
    }

    @Override
    public void updateDetail(Integer goodId, String imgPath) throws BusinessException {
        try {
            goodsListDOMapper.updateDetailById(goodId, imgPath);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.SAVE_DETAIL_ERR);
        }
    }

    @Override
    public List<CategoryModel> getCategoryList() {
        List<CategoryDO> categoryDOS = categoryDOMapper.selectCategoryList();
        List<CategoryModel> categoryModels = CategoryDOsToModels(categoryDOS);
        return categoryModels;
    }

    @Override
    public List<SubCategoryModel> getSubCategoryList(Integer categoryId) {
        List<SubcategoryDO> subcategoryDOS = subcategoryDOMapper.selectSubCategoryByCategoryId(categoryId);
        List<SubCategoryModel> subCategoryModelList = SubCategoryDOsToModels(subcategoryDOS);
        return subCategoryModelList;
    }

    @Override
    public List<ProductModel> getSubProductList(
            Integer goods_type,
            Integer sub_type
    ) {
        List<GoodsListDO> goodsListDOS = goodsListDOMapper.getSubProductList(goods_type, sub_type);
        List<ProductModel> productModels = GoodsListDOsToProductModels(goodsListDOS);
        return productModels;
    }

    @Override
    public String getAssess(Integer goodsid) {
        AssessDO assessDO = assessDOMapper.selectByPrimaryKey(goodsid);
        String str = assessDO.getAssess();
        return str;
    }

    @Override
    public List<GoodAssessModel> getGoodAssess(Integer goodsid) {
        List<AssessDO> assessDOs = assessDOMapper.getGoodAssess(goodsid);
        List<GoodAssessModel> goodAssessModels = new ArrayList<>();
        GoodAssessModel goodAssessModel = null;

        for (int i=0; i<assessDOs.size(); i++) {
            goodAssessModel = new GoodAssessModel();
            AssessDO assessDOTmp = assessDOs.get(i);
            Integer startNum = assessDOTmp.getStartNum();
            String sendTime = assessDOTmp.getSendTime();
            Integer userID = assessDOTmp.getUserID();
            UserInfoDO userInfoDO1 = userInfoDOMapper.selectByPrimaryKey(userID);
            String username1 = userInfoDO1.getUsername();
            String image_url1 = userInfoDO1.getImage_url();
            String assess = assessDOTmp.getAssess();
            goodAssessModel.setStarNum(startNum);
            goodAssessModel.setSendTime(sendTime);
            goodAssessModel.setUsername(username1);
            goodAssessModel.setAssess(assess);
            goodAssessModel.setImage_url(image_url1);
            goodAssessModels.add(goodAssessModel);
        }

        return goodAssessModels;
    }



    @Override
    public void setAssess(Integer goodsid, String assess) {
        JSONObject assessObject = JSONObject.parseObject(assess);
        Integer userID = assessObject.getInteger("userID");
        String assessMsg = assessObject.getString("assess");
        Integer starNum = assessObject.getInteger("starNum");
        String sendTime = assessObject.getString("sendTime");

        AssessDO assessDO = new AssessDO();
        assessDO.setAssess(assessMsg);
        assessDO.setGoodsid(goodsid);
        assessDO.setSendTime(sendTime);
        assessDO.setStartNum(starNum);
        assessDO.setUserID(userID);

        assessDOMapper.insertSelective(assessDO);
    }

    @Override
    public InitCategory getInitCategory(
            List<CategoryModel> categoryList,
            List<SubCategoryModel> subCategoryModelListm,
            List<ProductModel> subProductList
    ) {
        InitCategory initCategory = new InitCategory();
        initCategory.setCategoryList(categoryList);
        initCategory.setSubCategoryModelList(subCategoryModelListm);
        initCategory.setSubProductList(subProductList);
        return initCategory;
    }




    public List<CategoryModel> CategoryDOsToModels(List<CategoryDO> categoryDOS) {
        if (categoryDOS == null) {
            return null;
        }
        List<CategoryModel> categoryModels = new ArrayList<>();
        CategoryModel categoryModel = null;
        for (CategoryDO categoryDO : categoryDOS) {
            categoryModel = new CategoryModel();
            BeanUtils.copyProperties(categoryDO, categoryModel);
            categoryModels.add(categoryModel);
        }
        return categoryModels;
    }

    public List<SubCategoryModel> SubCategoryDOsToModels(List<SubcategoryDO> subcategoryDOS) {
        if (subcategoryDOS == null) {
            return null;
        }
        List<SubCategoryModel> subCategoryModelList = new ArrayList<>();
        SubCategoryModel subCategoryModel = null;
        for (SubcategoryDO subcategoryDO : subcategoryDOS) {
            subCategoryModel = new SubCategoryModel();
            BeanUtils.copyProperties(subcategoryDO, subCategoryModel);
            subCategoryModelList.add(subCategoryModel);
        }
        return subCategoryModelList;
    }

    public List<ProductModel> GoodsListDOsToProductModels(List<GoodsListDO> goodsListDOS) {
        if (goodsListDOS == null) {
            return null;
        }
        List<ProductModel> productModels = new ArrayList<>();
        ProductModel productModel = null;
        for (GoodsListDO goodsListDO : goodsListDOS) {
            productModel = new ProductModel();
            BeanUtils.copyProperties(goodsListDO, productModel);
            productModels.add(productModel);
        }
        return productModels;
    }


    public GoodsModel mergeGoodsModels(
            List<SwiperImage> swiperImages,
            List<RecommendGoods> recommendGoods,
            List<HotSellGoods> hotSellGoods,
            List<GoodsFloor> goodsFloors1,
            List<GoodsFloor> goodsFloors2,
            List<GoodsFloor> goodsFloors3,
            String[] floorsName,
            List<Category> categories,
            AdvertesPicture advertesPicture){

        GoodsModel goodsModel = new GoodsModel();
        goodsModel.setSwiperImage(swiperImages);
        goodsModel.setRecommendGoods(recommendGoods);
        goodsModel.setHotSellGoods(hotSellGoods);
        goodsModel.setFloor1(goodsFloors1);
        goodsModel.setFloor2(goodsFloors2);
        goodsModel.setFloor3(goodsFloors3);
        goodsModel.setFloorsName(floorsName);
        goodsModel.setCategories(categories);
        goodsModel.setAdvertesPicture(advertesPicture);
        return goodsModel;
    }
    public List<HotSellGoods> hotSellGoodsCovertFromGoodsListDO(List<GoodsListDO> goodsListDOList) {
        if (goodsListDOList == null) {
            return null;
        }

        List<HotSellGoods> hotSellGoods = new ArrayList<>();

        HotSellGoods hotSellGood = null;
        for (GoodsListDO goodsListDO : goodsListDOList){
            hotSellGood = new HotSellGoods();
            BeanUtils.copyProperties(goodsListDO, hotSellGood);
            hotSellGoods.add(hotSellGood);
        }
        return hotSellGoods;
    }
    public List<RecommendGoods> recommendGoodsCovertFromGoodsListDO(List<GoodsListDO> goodsListDOList) {
        if (goodsListDOList == null) {
            return null;
        }

        List<RecommendGoods> recommendGoodsList = new ArrayList<>();

        RecommendGoods recommendGoods = null;
        for (GoodsListDO goodsListDO : goodsListDOList){
            recommendGoods = new RecommendGoods();
            BeanUtils.copyProperties(goodsListDO, recommendGoods);
            recommendGoodsList.add(recommendGoods);
        }
        return recommendGoodsList;
    }
    public List<GoodsFloor> floorsCovertFromGoodsListDO(List<GoodsListDO> goodsListDOList) {
        if (goodsListDOList == null) {
            return null;
        }

        List<GoodsFloor> goodsFloors = new ArrayList<>();

        GoodsFloor goodsFloor = null;
        for (GoodsListDO goodsListDO : goodsListDOList){
            goodsFloor = new GoodsFloor();
            BeanUtils.copyProperties(goodsListDO, goodsFloor);
            goodsFloors.add(goodsFloor);
        }
        return goodsFloors;
    }
    public List<Category> categoryCovertFromCategoryDO(List<CategoryDO> categoryDOS) {
        if (categoryDOS == null) {
            return null;
        }

        List<Category> categories = new ArrayList<>();

        Category category = null;
        for (CategoryDO categoryDO : categoryDOS){
            category = new Category();
            BeanUtils.copyProperties(categoryDO, category);
            categories.add(category);
        }
        return categories;
    }
    public List<SubCategory> subCategoryCovertFromSubcategoryDO(List<SubcategoryDO> subcategoryDOS) {
        if (subcategoryDOS == null) {
            return null;
        }

        List<SubCategory> subCategories = new ArrayList<>();

        SubCategory subCategory = null;
        for (SubcategoryDO subcategoryDO : subcategoryDOS){
            subCategory = new SubCategory();
            BeanUtils.copyProperties(subcategoryDO, subCategory);
            subCategories.add(subCategory);
        }
        return subCategories;
    }
    public List<SwiperImage> covertFromSwipersDO(List<SwipersDO> swipersDOS) {
        if (swipersDOS == null) {
            return null;
        }

        List<SwiperImage> swiperImages = new ArrayList<>();
        SwiperImage swiperImage = null;
        for (SwipersDO swipersDO : swipersDOS){
            swiperImage = new SwiperImage();
            BeanUtils.copyProperties(swipersDO, swiperImage);

            swiperImages.add(swiperImage);
        }
        return swiperImages;
    }




}
