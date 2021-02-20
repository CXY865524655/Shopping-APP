package com.example.vant_demo_success.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.vant_demo_success.dao.*;
import com.example.vant_demo_success.dataobject.*;
import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.err.EnBusinessError;
import com.example.vant_demo_success.service.UserService;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.products.ProductModel;
import com.example.vant_demo_success.utils.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDOMapper userInfoDOMapper;

    @Autowired
    private AddressDOMapper addressDOMapper;

    @Autowired
    private CollectsDOMapper collectsDOMapper;

    @Autowired
    private GoodsListDOMapper goodsListDOMapper;

    @Autowired
    private CartDOMapper cartDOMapper;

    @Autowired
    private BoughtDOMapper boughtDOMapper;

    @Autowired
    private AdminDOMapper adminDOMapper;

    @Autowired
    private AssessDOMapper assessDOMapper;


    @Override
    public AdminModel adminLogin(String name, String pwd) throws BusinessException {
        AdminModel adminModel = new AdminModel();
        AdminDO adminDO = adminDOMapper.selectByPrimaryKey(1);
        BeanUtils.copyProperties(adminDO, adminModel);
        String name1 = adminModel.getName();
        String password = adminModel.getPassword();
        if (!name1.equals(name) || !pwd.equals(password)) {
            throw new BusinessException(EnBusinessError.USER_LOGIN_FAIL);
        }
        return adminModel;
    }

    @Override
    public UserModel getUserNameByID(Integer userid) {
        UserModel userModel = new UserModel();
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(userid);
        BeanUtils.copyProperties(userInfoDO, userModel);
        return userModel;
    }

    @Override
    public UserModel validataLogin0(String username, String password) throws BusinessException {

        UserInfoDO userInfoDO = userInfoDOMapper.selectByUserName(username);

        if (userInfoDO == null) {
            throw new BusinessException(EnBusinessError.USER_LOGIN_FAIL);
        }
        String pwd = userInfoDO.getPassword();
        if (!password.equals(pwd)) {
            throw new BusinessException(EnBusinessError.USER_LOGIN_FAIL);
        }
        UserModel userModel = covertFromDO(userInfoDO);

        return userModel;
    }



    @Override
    public UserModel validataLogin1(String telphone, String code) throws BusinessException {

        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telphone);

        // 判断手机号是否存在
        if (userInfoDO == null) {
            throw new BusinessException(EnBusinessError.TELEPHONE_NOT_EXITS);
        }

        UserModel userModel = covertFromDO(userInfoDO);

        return userModel;
    }

    @Override
    public List<UserModel> initUsers() {
        List<UserInfoDO> userInfoDOS = userInfoDOMapper.initUsers();
        List<UserModel> userModels = ModelsToDOs(userInfoDOS);

        return userModels;
    }
    @Override
    public List<UserModel> searchUsers(String content) {
        List<UserInfoDO> userInfoDOS = userInfoDOMapper.searchUsers(content);
        List<UserModel> userModels = ModelsToDOs(userInfoDOS);

        return userModels;
    }

    private List<UserModel> ModelsToDOs(List<UserInfoDO> userInfoDOS) {
        if (StringUtils.isEmpty(userInfoDOS)) {
            return null;
        }
        List<UserModel> userModels = new ArrayList<>();
        UserModel userModel = null;
        for (int i=0; i<userInfoDOS.size(); i++) {
            userModel = new UserModel();
            BeanUtils.copyProperties(userInfoDOS.get(i), userModel);
            String password = userModel.getPassword();
            
            userModels.add(userModel);
        }
        return userModels;
    }

    @Override
    public UserModel getUserByUserId(Integer id) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(id);
        if (StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.USER_NOT_EXITS);
        }
        UserModel userModel = covertFromDO(userInfoDO);
        return userModel;
    }

    @Override
    public void delUserImgPath(Integer userid) throws BusinessException {
        try {
            userInfoDOMapper.delUserImgPath(userid);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.DEL_USER_PATH_ERR);
        }
    }

    @Override
    public void cartNumUp1(Integer userid, Integer goodsid) throws BusinessException {
        cartDOMapper.cartNumUp1(userid, goodsid);
    }

    @Override
    public void saveUser(UserInfoDO userDO) throws BusinessException {
        try {
            userInfoDOMapper.saveUser(userDO);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.SAVE_USER_ERR);
        }
    }

    @Override
    public void updateCart(
            Integer userid,
            String selecteds
    ) throws BusinessException {
        String[] selects = selecteds.split(";");
        Integer[] goodsids = new Integer[selects.length];
        for (int i=0; i<selects.length; i++) {
            Integer integer = Integer.valueOf(selects[i]);
            goodsids[i] = integer;
        }

        cartDOMapper.delPayedCartItem(userid, goodsids);
    }

    @Override
    public void saveUserImgPath(String imgPath, Integer userid) throws BusinessException {
        try {
            userInfoDOMapper.updateUserImgPath(imgPath,userid);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.SAVE_USER_IMAGE_PATH_ERR);
        }
    }

    @Override
    public Boolean checkTel(String telephone) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telephone);
        if(!StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.TELEPHONE_EXITS);
        }
        return true;
    }

    // Telephone得到用户信息
    @Override
    public UserModel getUserByTel(String telephone) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telephone);
        if(StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.TELEPHONE_NOT_EXITS);
        }

        UserModel userModel = covertFromDO(userInfoDO);
        return userModel;
    }

    @Override
    public UserModel loginByAccount(String username, String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByUserName(username);

        if (StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.USER_OR_PASSWORD_ERROR);
        }

        String password1 = userInfoDO.getPassword();
        if (!PasswordUtil.checkLogin(password,password1)) {
            throw new BusinessException(EnBusinessError.USER_OR_PASSWORD_ERROR);
        }

        Integer userID = userInfoDO.getId();

        List<AddressDO> addressLists = addressDOMapper.getAddressList(userID);

        CollectsDO collectsDO = collectsDOMapper.selectByUserId(userID);

        List<CartDO> cartDO = cartDOMapper.getByUserId(userID);

        UserModel userModel = covertFromDO(userInfoDO);
        userModel.setAddress(addressLists);
        userModel.setCollects(collectsDO.getCollects());
        userModel.setGoodDetail(cartDO);

        BoughtDO boughtDO = boughtDOMapper.selectByPrimaryKey(userID);
        userModel.setBought(boughtDO.getBought());
        userModel.setUnpaid(boughtDO.getUnpaid());
        userModel.setUnassess(boughtDO.getUnassess());
        userModel.setUnreceived(boughtDO.getUnreceived());

        return userModel;
    }


    @Override
    public String sendCodeMsg(String telephone) {
        // 按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(8999);
        randomInt += 1000;
        String otpCode = String.valueOf(randomInt);
        return otpCode;
    }

    // 废弃
//    @Override
//    public String sendCode(String telephone) throws BusinessException {
//
//        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telephone);
//        if (StringUtils.isEmpty(userInfoDO)) {
//            throw new BusinessException(EnBusinessError.TELEPHONE_NOT_EXITS);
//        }
//        // 按照一定的规则生成OTP验证码
//        Random random = new Random();
//        int randomInt = random.nextInt(8999);
//        randomInt += 1000;
//        String otpCode = String.valueOf(randomInt);
//
//        return otpCode;
//    }

    @Override
    public String LoginSendCode(String telephone) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telephone);
        if (StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.TELEPHONE_NOT_EXITS);
        }
        String code = getCode();
        return code;
    }

    @Override
    public String RegisterSendCode(String telephone) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByTelephone(telephone);
        if (!StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.TELEPHONE_EXITS);
        }
        String code = getCode();
        return code;
    }

    @Override
    public void checkUser(String username) throws BusinessException {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByUserName(username);
        if (!StringUtils.isEmpty(userInfoDO)) {
            throw new BusinessException(EnBusinessError.USER_EXITS);
        }
    }

    @Override
    public void checkPayPwd(Integer userId, String pwd) throws BusinessException {
        UserPayPasswordDO userPayPasswordDO = userInfoDOMapper.selectPayPWDByUserID(userId);

        if (StringUtils.isEmpty(userPayPasswordDO)) {
            throw new BusinessException(EnBusinessError.USER_NOT_EXITS);
        }
        String payPWD = userPayPasswordDO.getPayPWD();
        if (!payPWD.equals(pwd)) {
            throw new BusinessException(EnBusinessError.PASSWORD_ERROR);
        }
    }

    @Override
    public void delUser(Integer userid) throws BusinessException {
        try {
            userInfoDOMapper.deleteByPrimaryKey(userid);
            collectsDOMapper.deleteByPrimaryKey(userid);
            cartDOMapper.deleteByPrimaryKey(userid);
            boughtDOMapper.deleteByPrimaryKey(userid);
            assessDOMapper.delByUserID(userid);

            addressDOMapper.delByUserid(userid);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.DEL_USER_ERR);
        }


    }



    @Override
    public Boolean hasPayPwd(Integer userID) throws BusinessException {
        UserPayPasswordDO userPayPasswordDO = userInfoDOMapper.selectPayPWDByUserID(userID);

        if (StringUtils.isEmpty(userPayPasswordDO)) {
            throw new BusinessException(EnBusinessError.USER_NOT_EXITS);
        }
        String payPWD = userPayPasswordDO.getPayPWD();
        if ("".equals(payPWD)) {
            return false;
        }
        return true;
    }

    @Override
    public void setPayPwd(Integer userID, String payPwd) throws BusinessException {
        try {
            userInfoDOMapper.updatePayPwdByUserID(userID, payPwd);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.UPDATE_USER_PAY_PWD_ERROR);
        }
    }

    @Override
    public void pay_success(
            Integer userId,
            String bought,
            String unreceived,
            String unassess,
            String selecteds
    ) throws BusinessException {


        try {
            boughtDOMapper.pay_success(userId, bought, unreceived, unassess);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.PAY_ERROR);
        }

        JSONArray objects = JSONObject.parseArray(selecteds);
        for (int i=0; i<objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            Integer goodsid = jsonObject.getInteger("goodsid");
            Integer num = jsonObject.getInteger("num");
            goodsListDOMapper.addSales(goodsid, num);
            goodsListDOMapper.updateStock(goodsid, num);

        }
    }



    @Override
    public UserModel insertUser(UserModel userModel) throws BusinessException {
        UserInfoDO userInfoDO = covertFromModel(userModel);
        try{
            userInfoDOMapper.insertSelective(userInfoDO);
        }catch (Exception e){
            throw new BusinessException(EnBusinessError.USER_EXITS);
        }
        UserInfoDO userInfoDO1 = userInfoDOMapper.selectByTelephone(userModel.getTelephone());
        UserModel userModel1 = covertFromDO(userInfoDO1);
        return userModel1;
    }

    @Override
    public void cartAdd(
            Integer userId,
            String cart
    ) throws BusinessException {
        JSONObject cartObject = JSONObject.parseObject(cart);

        Integer goodsId = cartObject.getInteger("goodsId");
        Integer num = cartObject.getInteger("num");
        CartDO cartDO = new CartDO();
        cartDO.setGoodsid(goodsId);
        cartDO.setNum(num);
        cartDO.setUserId(userId);
        CartDO byUseridAndGoodid = cartDOMapper.getByUseridAndGoodid(userId, goodsId);
        if (byUseridAndGoodid == null) {
            cartDOMapper.cartAdd(cartDO);
        }else {
            byUseridAndGoodid.setNum(byUseridAndGoodid.getNum() + 1);
            cartDOMapper.updateByPrimaryKeySelective(byUseridAndGoodid);
        }
    }

    @Override
    public void cartDel(Integer userId, String cart) throws BusinessException {
        JSONObject cartObject = JSONObject.parseObject(cart);
        Integer goodsId = cartObject.getInteger("goodsId");
        CartDO byUseridAndGoodid = cartDOMapper.getByUseridAndGoodid(userId, goodsId);
        byUseridAndGoodid.setNum(byUseridAndGoodid.getNum() - 1);
        cartDOMapper.updateByPrimaryKeySelective(byUseridAndGoodid);
    }

    @Override
    public void removePro(Integer userid, String selecteds) throws BusinessException {
        String[] split = selecteds.split(";");
        Integer[] selects = new Integer[split.length];
        for (int i =0; i< split.length; i++){
            selects[i]  =  Integer.parseInt(split[i]);
        }
        try {
            cartDOMapper.removePro(userid, selects);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItemModel> getCartList(Integer userid) throws BusinessException {
        List<CartDO> cartDOS = cartDOMapper.getByUserId(userid);
        List<CartItemModel> cartItemModels = new ArrayList<>();
        CartItemModel cartItemModel = null;
        CartDO cartDO = null;
        ProductModel productModel = null;
        for (int i =0 ;i<cartDOS.size(); i++) {
            cartDO = cartDOS.get(i);
            cartItemModel = new CartItemModel();
            productModel = new ProductModel();

            Integer goodsid = cartDO.getGoodsid();
            GoodsListDO goodsListDO = goodsListDOMapper.selectByGoodsId(goodsid);
            BeanUtils.copyProperties(goodsListDO, productModel);

            Integer num = cartDO.getNum();

            cartItemModel.setProductModel(productModel);
            cartItemModel.setNum(num);
            cartItemModel.setUserid(userid);
            cartItemModels.add(cartItemModel);
        }
        return cartItemModels;
    }

    @Override
    public CartItemModel getCartItem(Integer userid, Integer goodsId) throws BusinessException {
        CartDO byUseridAndGoodid = cartDOMapper.getByUseridAndGoodid(userid, goodsId);
        Integer goodsid = byUseridAndGoodid.getGoodsid();
        GoodsListDO goodsListDO = goodsListDOMapper.selectByGoodsId(goodsid);
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(goodsListDO, productModel);
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setUserid(userid);
        cartItemModel.setNum(1);
        cartItemModel.setProductModel(productModel);
        return cartItemModel;
    }

    // 生成验证码
    @Override
    public String getCode() throws BusinessException {
        // 按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(8999);
        randomInt += 1000;
        String otpCode = String.valueOf(randomInt);

        return otpCode;
    }


    @Override
    @Transactional
    public UserModel register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(EnBusinessError.PARAMETER_VALIDATION_ERROR, "请输入对应的用户信息");
        }

        UserInfoDO userInfoDO = covertFromModel(userModel);

        try {
            userInfoDOMapper.insertSelective(userInfoDO);
            UserInfoDO userInfoDO1 = userInfoDOMapper.selectByUserName(userInfoDO.getUsername());
            UserModel userModel1 = covertFromDO(userInfoDO1);
            return userModel1;
        }catch (DuplicateKeyException e){
            throw new BusinessException(EnBusinessError.PARAMETER_VALIDATION_ERROR, "用户名已经被注册");
        }
    }

    @Override
    public UserModel updateUserName(String username, Integer id) throws BusinessException {
        try {
            userInfoDOMapper.updateUserName(username, id);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.USER_EXITS);
        }
        UserModel userModel = getUserInfo(id);
        return userModel;
    }

    UserModel getUserInfo(Integer id) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(id);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfoDO, userModel);
        Integer userID = userInfoDO.getId();

        List<AddressDO> addressLists = addressDOMapper.getAddressList(userID);
        CollectsDO collectsDO = collectsDOMapper.selectByUserId(userID);
//        CartDO_old cartDO = cartDOMapper.getByUserId(userID);
        userModel.setAddress(addressLists);
        userModel.setCollects(collectsDO.getCollects());
//        userModel.setGoodDetail(cartDO.getGooddetail());

        BoughtDO boughtDO = boughtDOMapper.selectByPrimaryKey(userID);
        userModel.setBought(boughtDO.getBought());
        userModel.setUnpaid(boughtDO.getUnpaid());
        userModel.setUnassess(boughtDO.getUnassess());
        userModel.setUnreceived(boughtDO.getUnreceived());

        return userModel;
    }
    @Override
    public UserModel updateUserSex(Integer sex, Integer id) throws BusinessException {
        try {
            userInfoDOMapper.updateUserSex(sex, id);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.UPDATE_USER_SEX_ERROR);
        }
        UserModel userModel = getUserInfo(id);

        return userModel;
    }

    @Override
    public UserModel updateUserBirthday(String birthday, Integer id) throws BusinessException {
        try {
            userInfoDOMapper.updateUserBirthday(birthday, id);
        }catch (Exception e) {
            throw new BusinessException(EnBusinessError.UPDATE_USER_BIRTHDAY_ERROR);
        }
        UserModel userModel = getUserInfo(id);


        return userModel;
    }

    @Override
    public void insertAddress(Integer userId, String address) throws BusinessException {

//        addressDOMapper.updateAddressByPrimaryKeySelective(userId, address+';');
    }


    @Override
    public void ediAddress(Integer userid, String addressStr) throws BusinessException {
        JSONObject addressJSON = JSONObject.parseObject(addressStr);
        String name = addressJSON.getString("name");
        String tel = addressJSON.getString("tel");
        String country = addressJSON.getString("country");
        String province = addressJSON.getString("province");
        String city = addressJSON.getString("city");
        String county = addressJSON.getString("county");
        String areaCode = addressJSON.getString("areaCode");
        String postalCode = addressJSON.getString("postalCode");
        String addressDetail = addressJSON.getString("addressDetail");
        Boolean isDefault = addressJSON.getBoolean("isDefault");
        String id = addressJSON.getString("id");
        String address = addressJSON.getString("address");

        AddressDO addressDO = new AddressDO();
        addressDO.setAddress(address);
        addressDO.setCity(city);
        addressDO.setCountry(country);
        addressDO.setCounty(county);
        addressDO.setId(id);
        addressDO.setName(name);
        addressDO.setProvince(province);
        addressDO.setTel(tel);
        addressDO.setUserid(userid);
        addressDO.setAreaCode(areaCode);
        addressDO.setIsDefault(String.valueOf(isDefault));
        addressDO.setPostalCode(postalCode);
        addressDO.setAddressDetail(addressDetail);
        try {
            addressDOMapper.ediAddress(addressDO);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if (isDefault) {
            try {
                addressDOMapper.updateOtherisDefaultFalse(userid, id);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void addAddress(Integer userid, String addressStr) throws BusinessException {
        JSONObject addressJSON = JSONObject.parseObject(addressStr);
        String name = addressJSON.getString("name");
        String tel = addressJSON.getString("tel");
        String country = addressJSON.getString("country");
        String province = addressJSON.getString("province");
        String city = addressJSON.getString("city");
        String county = addressJSON.getString("county");
        String areaCode = addressJSON.getString("areaCode");
        String postalCode = addressJSON.getString("postalCode");
        String addressDetail = addressJSON.getString("addressDetail");
        Boolean isDefault = addressJSON.getBoolean("isDefault");
        String id = addressJSON.getString("id");
        String address = addressJSON.getString("address");

        AddressDO addressDO = new AddressDO();
        addressDO.setAddress(address);
        addressDO.setCity(city);
        addressDO.setCountry(country);
        addressDO.setCounty(county);
        addressDO.setId(id);
        addressDO.setName(name);
        addressDO.setProvince(province);
        addressDO.setTel(tel);
        addressDO.setUserid(userid);
        addressDO.setAreaCode(areaCode);
        addressDO.setIsDefault(String.valueOf(isDefault));
        addressDO.setPostalCode(postalCode);
        addressDO.setAddressDetail(addressDetail);
        try {
            addressDOMapper.addAddress(addressDO);
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (isDefault) {
            try {
                addressDOMapper.updateOtherisDefaultFalse(userid, id);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void delAddress(Integer userid, String id) throws BusinessException {
        try {
            addressDOMapper.delAddress(userid, id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserCollects(Integer userId, String collect) throws BusinessException {
        try {
            collectsDOMapper.setUserCollects(userId, collect);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setUserCart(Integer userId, String cart) throws BusinessException {
        try {
//            cartDOMapper.setUserCart(userId, cart);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<ProductModel> getUserCollectList(Integer[] goodIds) throws BusinessException {
            List<GoodsListDO> goodsListDOS = goodsListDOMapper.selectByCollectArray(goodIds);
            List<ProductModel> productModels = GoodsListDOsToProductModels(goodsListDOS);
            return productModels;
    }
    @Override
    public List<ProductModel> getUserCartList(Integer[] goodIds) throws BusinessException {
            List<GoodsListDO> goodsListDOS = goodsListDOMapper.selectByCollectArray(goodIds);
            List<ProductModel> productModels = GoodsListDOsToProductModels(goodsListDOS);
            return productModels;
    }

    @Override
    public void set_unassess(Integer userId, String unassess) {
        boughtDOMapper.updateUnAssess(userId, unassess);
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





    @Override
    public List<AddressModel> getAddressList(Integer userId) throws BusinessException {
        List<AddressDO> address = addressDOMapper.getAddressList(userId);
        List<AddressModel> addressModels = addressDOToModel(address);
        return addressModels;
    }

    @Override
    public BoughtModel initOrderDetail(Integer userId) throws BusinessException {
        BoughtDO boughtDO = boughtDOMapper.selectByPrimaryKey(userId);
        BoughtModel boughtModel = new BoughtModel();
        BeanUtils.copyProperties(boughtDO, boughtModel);

        return boughtModel;
    }

    public List<AddressModel> addressDOToModel(List<AddressDO> addressDO) {
        if(addressDO == null) {
            return null;
        }
        if (addressDO.size() == 0) {
            return null;
        }
        AddressDO addressDO1 = null;
        AddressModel addressModel = null;
        List<AddressModel> addressModels = new ArrayList<>();
        for (int i=0; i<addressDO.size(); i++){
            addressDO1 = addressDO.get(i);
            addressModel = new AddressModel();
            BeanUtils.copyProperties(addressDO1, addressModel);
            addressModels.add(addressModel);
        }
        return addressModels;
    }



    @Override
    public void initAddress(AddressModel addressModel) throws BusinessException {
        if(addressModel != null) {
            AddressDO addressDO = new AddressDO();
            BeanUtils.copyProperties(addressModel, addressDO);
//            addressDOMapper.initAddress(addressDO);
        }
    }

    @Override
    public void updateAddress(Integer userId, String addressList) throws BusinessException {

    }

    @Override
    public void initCollects(CollectsModel collectsModel) throws BusinessException {
        if(collectsModel != null) {
            CollectsDO collectsDO = new CollectsDO();
            BeanUtils.copyProperties(collectsModel, collectsDO);
            collectsDOMapper.initCollects(collectsDO);
        }
    }

    @Override
    public void initCart(CartModel cartModel) throws BusinessException {
        if(cartModel != null) {
            CartDO_old cartDO = new CartDO_old();
            BeanUtils.copyProperties(cartModel, cartDO);
//            cartDOMapper.initCart(cartDO);
        }
    }

    @Override
    public void initBought(BoughtModel boughtModel) throws BusinessException {
        if(boughtModel != null) {
            BoughtDO boughtDO = new BoughtDO();
            BeanUtils.copyProperties(boughtModel, boughtDO);
            boughtDOMapper.initBought(boughtDO);
        }
    }



    @Override
    public Boolean checkStar(Integer userId, Integer goodsid) {
        CollectsDO collectsDO = collectsDOMapper.selectByUserId(userId);
        String collects = collectsDO.getCollects();
        String[] split = collects.split(";");
        boolean contains = Arrays.asList(split).contains(goodsid.toString());
        return contains;
    }



    public UserInfoDO covertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(userModel, userInfoDO);
        return userInfoDO;
    }

    public UserModel covertFromDO(UserInfoDO userInfoDO) {
        if (userInfoDO == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfoDO, userModel);
        return userModel;
    }
}

//        try {
//            CollectsDO collectsDO = collectsDOMapper.selectByUserId(userInfoDO.getId());
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }