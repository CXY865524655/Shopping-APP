package com.example.vant_demo_success.service;

import com.example.vant_demo_success.dataobject.UserInfoDO;
import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.SubCategoryAndGoods.somtings.Product;
import com.example.vant_demo_success.service.model.products.ProductModel;
import org.apache.ibatis.annotations.Param;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    AdminModel adminLogin(String name, String pwd) throws BusinessException;

    UserModel getUserNameByID(Integer userid);
    // 校验用户登录
    UserModel validataLogin0(String username, String password) throws BusinessException;
    UserModel validataLogin1(String telphone, String code) throws BusinessException;

    List<UserModel> initUsers();
    List<UserModel> searchUsers(String content);
    // Telephone 得到用户信息
    UserModel getUserByTel(String telephone) throws BusinessException;

    void delUser(Integer userid) throws BusinessException;
    // UserName 得到用户信息
    UserModel getUserByUserId(Integer id) throws BusinessException;

    // UserName 得到用户信息
    void delUserImgPath(Integer userid) throws BusinessException;

    void cartNumUp1(Integer userid, Integer goodsid) throws BusinessException;

    void saveUser(UserInfoDO userDO) throws BusinessException;

    void updateCart(Integer userid, String selecteds) throws BusinessException;

    void saveUserImgPath(String imgPath, Integer userid) throws BusinessException;

    // 判断Telephone是否存在
    Boolean checkTel(String telephone) throws BusinessException;

    // 验证用户名和密码
    UserModel loginByAccount(String username, String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    String sendCodeMsg(String telephone);

    // 废弃
    // 发送验证码
//    String sendCode(String telephone) throws BusinessException;

    // 登录发送验证码
    String LoginSendCode(String telephone) throws  BusinessException;

    // 注册发送验证码
    String RegisterSendCode(String telephone) throws BusinessException;

    // 检查用户名是否存在
    void checkUser(String username) throws BusinessException;

    void checkPayPwd(Integer userId, String pwd) throws BusinessException;

    Boolean hasPayPwd(Integer userID) throws BusinessException;

    void setPayPwd(Integer userID, String payPwd) throws BusinessException;

    void pay_success(
            Integer userId,
            String bought,
            String unreceived,
            String unassess,
            String selecteds
    ) throws BusinessException;

    // initCategory
    Boolean checkStar(
            Integer userId,
            Integer goodsid
    );

    // 注册User
    UserModel insertUser(UserModel userModel) throws BusinessException;

    // 获取验证码
    String getCode() throws BusinessException;


    UserModel register(UserModel userModel) throws BusinessException;

    // update 用户信息
    UserModel updateUserName(String username, Integer id) throws  BusinessException;

    // update 用户信息
    UserModel updateUserSex(Integer sex, Integer id) throws  BusinessException;

    // update 用户信息
    UserModel updateUserBirthday(String birthday, Integer id) throws  BusinessException;

    // register insert Address
    void insertAddress(Integer id, String addressStr) throws BusinessException;


    void addAddress(Integer id, String address) throws BusinessException;
    void ediAddress(Integer id, String address) throws BusinessException;
    void delAddress(Integer userid, String id) throws BusinessException;
    void cartAdd(Integer userId, String cart) throws BusinessException;
    void cartDel(Integer userid, String cart) throws BusinessException;
    void removePro(Integer userid, String selecteds) throws BusinessException;
    List<CartItemModel> getCartList(Integer userid) throws BusinessException;
    CartItemModel getCartItem(Integer userid, Integer goodsId) throws BusinessException;
    // 获取用户的地址列表
    List<AddressModel> getAddressList(Integer userId) throws BusinessException;


    BoughtModel initOrderDetail(Integer userId) throws BusinessException;



    // 新增用户地址列表 默认为 ''
    void initAddress(AddressModel addressModel) throws BusinessException;


    // 修改用户地址列表
    void updateAddress(Integer userId, String addressList) throws BusinessException;

    // 新增用户收藏列表 默认为 ''
    void initCollects(CollectsModel collectsModel) throws BusinessException;

    // 新增用户购物车列表 默认为 ''
    void initCart(CartModel cartModel) throws BusinessException;

    // 新增用户订单 默认为 ''
    void initBought(BoughtModel boughtModel) throws BusinessException;



    void setUserCollects(Integer userId, String collect) throws BusinessException;

    void setUserCart(Integer userId, String cart) throws BusinessException;


    // 获取当前用户的收藏Goods List
    List<ProductModel> getUserCollectList(Integer[] goodIds) throws BusinessException;

    // 获取当前用户的购物车 List
    List<ProductModel> getUserCartList(Integer[] goodIds) throws BusinessException;


    void set_unassess(Integer userId, String unassess);


}
