package com.example.vant_demo_success.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.vant_demo_success.annotate.UserLoginToken;
import com.example.vant_demo_success.dataobject.UserInfoDO;
import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.err.EnBusinessError;
import com.example.vant_demo_success.response.CommonReturnType;
import com.example.vant_demo_success.service.MailService;
import com.example.vant_demo_success.service.UserService;
import com.example.vant_demo_success.service.model.*;
import com.example.vant_demo_success.service.model.products.ProductModel;
import com.example.vant_demo_success.service.utils.GoodsUtils;
import com.example.vant_demo_success.service.utils.UserUtIls;
import com.example.vant_demo_success.utils.token.TokenUtils;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.controller
 * @ClassName: UserController
 * @Description:
 * @Author: cxy
 * @Date: 2019/11/17 14:39
 */
@Controller("user")     // 加入Controller容器，命名为 user
@RequestMapping("/user")   // URL请求路径
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")  // 跨域设置
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType login(
            @RequestParam(name = "user") String user
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        com.alibaba.fastjson.JSONObject loginInfo = com.alibaba.fastjson.JSONObject.parseObject(user);
        // 账号登录形式
        if (loginInfo.size() == 2 && loginInfo.containsKey("username") && loginInfo.containsKey("password")) {
            String username = loginInfo.getString("username");
            String password = loginInfo.getString("password");
            // 验证username 和 password
            // 判断手机号是否为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(username)) {
                throw new BusinessException(EnBusinessError.USER_IS_NULL);
            }

            // 判断验证码为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(password)){
                throw new BusinessException(EnBusinessError.PASSWORD_IS_NULL);
            }

            UserModel userModel = userService.loginByAccount(username, password);
            JSONObject returnUserInfo = UserUtIls.getReturnUserInfo(userModel);
            returnUserInfo.put("success_msg", "登录成功！");

            return CommonReturnType.create(returnUserInfo);
        }

        // 手机号登录形式
        if (loginInfo.size() == 3 && loginInfo.containsKey("telephone") &&
                loginInfo.containsKey("code") && loginInfo.containsKey("relCode")
        ) {
            String telephone = loginInfo.getString("telephone");
            String code = loginInfo.getString("code");
            String relCode = loginInfo.getString("relCode");
            // 验证telephone 和 code
            // 判断手机号是否为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(telephone)) {
                throw new BusinessException(EnBusinessError.TELEPHONE_IS_NULL);
            }

            // 判断验证码为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(code)){
                throw new BusinessException(EnBusinessError.VerificationCode_IS_NULL);
            }

            // 验证手机号和验证码是否一致
            if ("".equals(code) ||"".equals(relCode) || !code.equals(relCode)) {
                throw new BusinessException(EnBusinessError.CODE_ERR);
            }
            UserModel userModel = userService.getUserByTel(telephone);
            JSONObject returnUserInfo = UserUtIls.getReturnUserInfo(userModel);
            returnUserInfo.put("success_msg", "登录成功！");
//            httpServletRequest.getSession().removeAttribute(telephone);
            return CommonReturnType.create(returnUserInfo);
        }
        return CommonReturnType.create("传参无效");
    }

    @RequestMapping(value = "/updateUserName", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType updateUserName(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "id") Integer id
    ) throws BusinessException{
        UserModel userModel = userService.updateUserName(username, id);
        JSONObject returnUserInfo = UserUtIls.getReturnUserInfo(userModel);
        returnUserInfo.put("success_msg", "登录成功！");
        return CommonReturnType.create(returnUserInfo);
    }


    @RequestMapping(value = "/searchUsers", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType searchUsers(
            @Param("content") String content
    ) throws BusinessException{
        List<UserModel> userModels = userService.searchUsers(content);

        return CommonReturnType.create(userModels);
    }



    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveUserImg", method = {RequestMethod.POST},produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public CommonReturnType saveUserImg(
            @RequestParam("file") MultipartFile[] file
    ) throws BusinessException, IOException {
        //        D:/Code/Code/img/user    //tmp/imgs/user/
        String path = "D:/Code/Code/img/user";
        if (file.length >= 1) {
                File file1 = new File(path + file[0].getOriginalFilename());
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdirs();
                }
                if (!file1.exists()) {
                    file[0].transferTo(file1);
                }else {
                    System.out.println("已经存在同名文件");
                }
        }
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/updateUserImgPath", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateUserImgPath(
            @RequestParam(name = "path") String path,
            @RequestParam(name = "userid") Integer userid
    ) throws BusinessException{
        //        D:/Code/Code/img/user    //tmp/imgs/user/
        String basePath = "D:/Code/Code/img/user";
        StringBuilder stringBuilder = null;
        stringBuilder = new StringBuilder(basePath + path);
        String s = stringBuilder.toString();
        userService.saveUserImgPath(s, userid);
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/delUser", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType delUser(
            @Param("userid") Integer userid
    ) throws BusinessException{
        userService.delUser(userid);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/initUsers", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType initUsers() throws BusinessException{
        List<UserModel> userModels = userService.initUsers();


        return CommonReturnType.create(userModels);
    }


// consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/getUserImg", method = {RequestMethod.POST},produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getUserImg(
            @RequestParam("imgPath") String imgPath
    ) throws BusinessException, IOException {

        File file1 = new File(imgPath);
        InputStream inputStream = new FileInputStream(file1);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        return bytes;
    }
    @RequestMapping(value = "delUserImgPath", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType delUserImgPath(
            @RequestParam(name = "userid") Integer userid
    ) throws BusinessException{
        userService.delUserImgPath(userid);
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/saveUser", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType saveUser(
            @Param("user") String user
    ) throws BusinessException{
        UserInfoDO userDO = getUserDO(user);
        userService.saveUser(userDO);
        return CommonReturnType.create(null);
    }

    private UserInfoDO getUserDO(String user) {
        com.alibaba.fastjson.JSONObject userJSON = com.alibaba.fastjson.JSONObject.parseObject(user);
        Integer age = userJSON.getInteger("age");
        String birthday = userJSON.getString("birthday");
        Integer id = userJSON.getInteger("id");
        String image_url = userJSON.getString("image_url");
        String password = userJSON.getString("password");
        String payPWD = userJSON.getString("payPWD");
        String sex = userJSON.getString("sex");
        String telephone = userJSON.getString("telephone");
        String username = userJSON.getString("username");

        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setAge(age);
        userInfoDO.setBirthday(birthday);
        userInfoDO.setId(id);
        userInfoDO.setImage_url(image_url);
        userInfoDO.setPassword(password);
        userInfoDO.setPayPWD(payPWD);
        userInfoDO.setSex(sex);
        userInfoDO.setTelephone(telephone);
        userInfoDO.setUsername(username);
        return userInfoDO;
    }




    @RequestMapping(value = "updateUserSex", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType updateUserSex(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "sex") Integer sex
    ) throws BusinessException{
        UserModel userModel = userService.updateUserSex(sex, id);
        JSONObject returnUserInfo = UserUtIls.getReturnUserInfo(userModel);
        returnUserInfo.put("success_msg", "用户信息修改成功！");
        return CommonReturnType.create(returnUserInfo);
    }

    @RequestMapping(value = "updateUserBirthday", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType updateUserBirthday(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "birthday") String birthday
    ) throws BusinessException{
        UserModel userModel = userService.updateUserBirthday(birthday, id);
        JSONObject returnUserInfo = UserUtIls.getReturnUserInfo(userModel);
        returnUserInfo.put("success_msg", "用户信息修改成功！");
        return CommonReturnType.create(returnUserInfo);
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/loginByTel", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType loginByTel(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "code") String code
    ) throws BusinessException{
            // 判断手机号是否为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(telephone)) {
                throw new BusinessException(EnBusinessError.TELEPHONE_IS_NULL);
            }

            // 判断验证码为空
            if (org.apache.commons.lang3.StringUtils.isEmpty(code)){
                throw new BusinessException(EnBusinessError.VerificationCode_IS_NULL);
            }

            // 验证手机号和验证码是否一致
            if (!httpServletRequest.getSession().getAttribute(telephone).equals(code)) {
                throw new BusinessException(EnBusinessError.TEL_OR_CODE_IS_ERROR);
            }
            UserModel userModel = userService.getUserByTel(telephone);
            String token = TokenUtils.getToken(userModel);
            JSONObject userInfo = new JSONObject();
            userInfo.put("id", userModel.getId());
            userInfo.put("username", userModel.getUsername());
            userInfo.put("password", userModel.getPassword());
            userInfo.put("telephone", userModel.getTelephone());
            userInfo.put("sex", userModel.getSex());
            userInfo.put("age", userModel.getAge());
            userInfo.put("image_url", userModel.getImage_url());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_Info", userInfo);
            jsonObject.put("token", token);
            jsonObject.put("success_msg", "登录成功");
            httpServletRequest.getSession().removeAttribute(telephone);
            return CommonReturnType.create(jsonObject);
    }

    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/loginByAccount", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType loginByAccount(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 判断手机号是否为空
        if (org.apache.commons.lang3.StringUtils.isEmpty(username)) {
            throw new BusinessException(EnBusinessError.USER_IS_NULL);
        }

        // 判断验证码为空
        if (org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EnBusinessError.PASSWORD_IS_NULL);
        }


        UserModel userModel = userService.loginByAccount(username, password);
        String token = TokenUtils.getToken(userModel);

        JSONObject userInfo = new JSONObject();
        userInfo.put("id", userModel.getId());
        userInfo.put("username", userModel.getUsername());
        userInfo.put("password", userModel.getPassword());
        userInfo.put("telephone", userModel.getTelephone());
        userInfo.put("sex", userModel.getSex());
        userInfo.put("age", userModel.getAge());
        userInfo.put("birthday", userModel.getBirthday());

        userInfo.put("image_url", userModel.getImage_url());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_Info", userInfo);
        jsonObject.put("token", token);
        jsonObject.put("success_msg", "登录成功");
        return CommonReturnType.create(jsonObject);
    }



    @RequestMapping(value = "/loginSendCode", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType loginSendCode(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        String code = userService.LoginSendCode(telephone);
//        httpServletRequest.getSession().setAttribute(telephone, code);
        return  CommonReturnType.create(code);
    }

    @RequestMapping(value = "/registerSendCode", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType registerSendCode(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        String code = userService.RegisterSendCode(telephone);
//        httpServletRequest.getSession().setAttribute(telephone, code);
        return  CommonReturnType.create(code);
    }

    @RequestMapping(value = "/checkTel", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType checkTel(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        Boolean is_true = userService.checkTel(telephone);
        return  CommonReturnType.create(is_true);
    }
    @RequestMapping(value = "/getUserNameByTel", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getUserNameByTel(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        UserModel userByTel = userService.getUserByTel(telephone);
        String userName = userByTel.getUsername();
        return  CommonReturnType.create(userName);
    }

    @RequestMapping(value = "/getUserNameByID", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getUserNameByID(
            @RequestParam(name = "userid") Integer userid
    ) throws BusinessException {
        UserModel user = userService.getUserNameByID(userid);
        return  CommonReturnType.create(user);
    }


    @RequestMapping(value = "/initOrderDetail", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType initOrderDetail(
            @RequestParam(name = "userId") Integer userId
    ) throws BusinessException {
        BoughtModel boughtModel = userService.initOrderDetail(userId);

        return  CommonReturnType.create(boughtModel);
    }

    @RequestMapping(value = "/cartAdd", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType cartAdd(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "cart") String cart
    ) throws BusinessException {
        userService.cartAdd(userId, cart);
        return  CommonReturnType.create(null);
    }



    @RequestMapping(value = "/cartDel", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType cartDel(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "cart") String cart
    ) throws BusinessException {
        userService.cartDel(userid, cart);
        return  CommonReturnType.create(null);
    }

    @RequestMapping(value = "/cart_num_up1", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType cart_num_up1(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "goodsid") Integer goodsid
    ) throws BusinessException {
        userService.cartNumUp1(userid, goodsid);
        return  CommonReturnType.create(null);
    }


    @RequestMapping(value = "/removePro", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType removePro(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "selecteds") String selecteds
    ) throws BusinessException {
            userService.removePro(userid, selecteds);
        return  CommonReturnType.create(null);
    }

    @RequestMapping(value = "/set_unassess", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType set_unassess(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "unassess") String unassess
    ) throws BusinessException {
        userService.set_unassess(userId, unassess);

        return  CommonReturnType.create(null);
    }





    @RequestMapping(value = "/checkRegisterCode", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType checkRegisterCode(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "code") String code,
            @RequestParam(name = "relCode") String relCode
    ) throws BusinessException {
        if("".equals(code) || "".equals(relCode) || !code.equals(relCode)){
            throw new BusinessException(EnBusinessError.CODE_ERR);
        }
        return  CommonReturnType.create(true);
    }

    @RequestMapping(value = "/checkUser", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType checkUser(
            @RequestParam(name = "username") String username
    ) throws BusinessException {
        userService.checkUser(username);
        return  CommonReturnType.create(null);
    }

    @RequestMapping(value = "/checkPayPwd", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType checkPayPwd(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "pwd") String pwd
    ) throws BusinessException {
        userService.checkPayPwd(id, pwd);
        return CommonReturnType.create(null);
    }


    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/checkStar", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType checkStar(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "goodsid") Integer goodsid
    ) throws BusinessException{

        Boolean isStar = userService.checkStar(userId, goodsid);
        return CommonReturnType.create(isStar);

    }

    @RequestMapping(value = "/hasPayPwd", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType hasPayPwd(
            @RequestParam(name = "userID") Integer userID
    ) throws BusinessException {
        Boolean hasPayPwd = userService.hasPayPwd(userID);
        return CommonReturnType.create(hasPayPwd);
    }

    @RequestMapping(value = "/setPayPwd", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType setPayPwd(
            @RequestParam(name = "userID") Integer userID,
            @RequestParam(name = "payPwd") String payPwd
    ) throws BusinessException {
        userService.setPayPwd(userID,payPwd);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/pay_success", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType pay_success(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "bought") String bought,
            @RequestParam(name = "unreceived") String unreceived,
            @RequestParam(name = "unassess") String unassess,
            @RequestParam(name = "selecteds") String selecteds
    ) throws BusinessException {

        userService.pay_success(userId,bought,unreceived,unassess,selecteds);
        return CommonReturnType.create(null);
    }


    @RequestMapping(value = "/updateCart", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateCart(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "selecteds") String selecteds
    ) throws BusinessException {

        userService.updateCart(userid, selecteds);
        return CommonReturnType.create(null);
    }




    @RequestMapping(value = "/insertUser", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType insertUser(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        String pwd = PasswordUtil.encoderByMd5(password);
        UserModel userModel = new UserModel();
        userModel.setTelephone(telephone);
        userModel.setUsername(username);
        userModel.setPassword(password);
        UserModel userModel1 = userService.insertUser(userModel);

//        AddressModel addressModel = new AddressModel();
//        addressModel.setUserid(userModel1.getId());
//        addressModel.setAddress("");

        CollectsModel collectsModel = new CollectsModel();
        collectsModel.setUserid(userModel1.getId());
        collectsModel.setCollects("");

//        CartModel cartModel = new CartModel();
//        cartModel.setUserid(userModel1.getId());
//        cartModel.setGooddetail("");

        BoughtModel boughtModel = new BoughtModel();
        boughtModel.setUserid(userModel1.getId());
        boughtModel.setBought("");
        boughtModel.setUnassess("");
        boughtModel.setUnpaid("");
        boughtModel.setUnreceived("");


//        userService.initAddress(addressModel);
        userService.initCollects(collectsModel);
//        userService.initCart(cartModel);
        userService.initBought(boughtModel);


//        httpServletRequest.getSession().removeAttribute(telephone);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/setUserCollects", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType setUserCollects(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "collect") String collect
    ) throws BusinessException {
        userService.setUserCollects(userId, collect);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/setUserCart", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType setUserCart(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "cart") String cart
    ) throws BusinessException {
        userService.setUserCart(userId, cart);
        return CommonReturnType.create(null);
    }


    @RequestMapping(value = "/getCollectList", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getCollectList(
            @RequestParam(name = "collectStr") String collectStr
    ) throws BusinessException {
        if ("".equals(collectStr)) {
            return CommonReturnType.create(null);
        }else {
            String[] strings = collectStr.split(",");
            Integer[] collects = new Integer[strings.length];
            for(int i=0; i<collects.length; i++) {
                collects[i] = Integer.parseInt(strings[i]);
            }
            List<ProductModel> userCollectList = userService.getUserCollectList(collects);
            JSONObject collectsList = GoodsUtils.getCollectsList(userCollectList);
            collectsList.put("success_msg", "success！");
            return CommonReturnType.create(collectsList);
        }
    }

    @RequestMapping(value = "/getCartList", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getCartList(
            @RequestParam(name = "userid") Integer userid
    ) throws BusinessException {

        List<CartItemModel> cartList = userService.getCartList(userid);
        return CommonReturnType.create(cartList);
    }

    @RequestMapping(value = "/getCartItem", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getCartItem(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "goodsId") Integer goodsId
    ) throws BusinessException {
        CartItemModel cartItem = userService.getCartItem(userid, goodsId);
        return CommonReturnType.create(cartItem);
    }




    @RequestMapping(value = "/insertAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType insertAddress(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "address") String address
    ) throws BusinessException {
        userService.insertAddress(userId, address);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/ediAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType ediAddress(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "address") String address
    ) throws BusinessException {
        userService.ediAddress(userid, address);
        return CommonReturnType.create(null);
    }


    @RequestMapping(value = "/updateAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateAddress(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "addressList") String addressList
    ) throws BusinessException {
        userService.updateAddress(userId, addressList);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/addAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType addAddress(
            @RequestParam(name = "userId") Integer userId,
            @RequestParam(name = "address") String address
    ) throws BusinessException {
        userService.addAddress(userId, address);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/delAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType delAddress(
            @RequestParam(name = "userid") Integer userid,
            @RequestParam(name = "id") String id
    ) throws BusinessException {
        userService.delAddress(userid, id);
        return CommonReturnType.create(null);
    }


    @RequestMapping(value = "/getAddressList", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getAddressList(
            @RequestParam(name = "userId") Integer userId
    ) throws BusinessException {
        List<AddressModel> addressList = userService.getAddressList(userId);

        return CommonReturnType.create(addressList);
    }




    // consumes: 限定URL传递过来的Content-Type为 常量 CONTENT_TYPE_FROMED 的值
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FROMED})
    @ResponseBody
    public CommonReturnType register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "codeMsg") String codeMsg
    ) throws BusinessException{

        // 判断名字是否为空
        if (org.apache.commons.lang3.StringUtils.isEmpty(username)) {
            throw new BusinessException(EnBusinessError.USER_IS_NULL);
        }

        // 判断密码为空
        if (org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EnBusinessError.PASSWORD_IS_NULL);
        }

        // 判断手机号为空
        if (org.apache.commons.lang3.StringUtils.isEmpty(telephone)){
            throw new BusinessException(EnBusinessError.TELEPHONE_IS_NULL);
        }

        String sessionCode = (String) httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(sessionCode, codeMsg)) {
            throw new BusinessException(EnBusinessError.CODE_ERR);
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setTelephone(telephone);

        UserModel register = userService.register(userModel);

        JSONObject userInfo_JSON = new JSONObject();
        userInfo_JSON.put("id", register.getId());
        userInfo_JSON.put("username", register.getUsername());
        userInfo_JSON.put("password", register.getPassword());
        userInfo_JSON.put("telephone", register.getTelephone());
        userInfo_JSON.put("sex", register.getSex());
        userInfo_JSON.put("age", register.getAge());
        userInfo_JSON.put("image_url", register.getImage_url());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", userInfo_JSON);
        jsonObject.put("success_msg", "用户注册成功！");
        httpServletRequest.getSession().removeAttribute(telephone);

        return CommonReturnType.create(jsonObject);
    }





//        @RequestMapping(value = "/sendCodeMsg", method = {RequestMethod.POST})
//        @ResponseBody
//        public CommonReturnType sendCodeMsg(@RequestParam(name = "telephone") String telephone){
//            String codeMsg = userService.sendCodeMsg(telephone);
//            JSONObject returnObject = new JSONObject();
//            returnObject.put("codeMsg", codeMsg);
//            httpServletRequest.getSession().setAttribute(telephone, codeMsg);
//            return  CommonReturnType.create(returnObject);
//        }


    // 废弃
//        @RequestMapping(value = "/sendCode", method = {RequestMethod.POST})
//        @ResponseBody
//        public CommonReturnType sendCode(@RequestParam(name = "telephone") String telephone) throws BusinessException {
//              String code = userService.sendCode(telephone);
//              httpServletRequest.getSession().setAttribute(telephone, code);
//
//              String codeMsg = userService.sendCodeMsg(telephone);
//              JSONObject returnObject = new JSONObject();
//              returnObject.put("codeMsg", codeMsg);
//              httpServletRequest.getSession().setAttribute(telephone, codeMsg);
//              return  CommonReturnType.create(code);
//        }

    @UserLoginToken
    @RequestMapping(value = "/getMessage" , method = {RequestMethod.POST})
    @ResponseBody
    public String getMessage() {
        return "你已经通过验证";
    }


    @RequestMapping(value = "/test" , method = {RequestMethod.POST})
    @ResponseBody
    public String test() {
        return "你已经通过验证";
    }


    @RequestMapping(value = "/sendByMail",method = {RequestMethod.POST})
    @ResponseBody
    public String sendByMail(@RequestParam(value = "tel")String tel, @RequestParam(value = "msg")String msg){
//        mailService.sendMessage(tel, msg);
        return "success";
    }

    @RequestMapping(value = "/adminLogin",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType adminLogin(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "pwd")String pwd) throws BusinessException {
        AdminModel adminModel = userService.adminLogin(name, pwd);
        String token = TokenUtils.getAdminToken(adminModel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Admin", adminModel);
        jsonObject.put("token", token);


        return CommonReturnType.create(jsonObject);
    }
}
