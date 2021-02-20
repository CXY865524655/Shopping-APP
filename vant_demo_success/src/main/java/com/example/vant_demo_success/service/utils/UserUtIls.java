package com.example.vant_demo_success.service.utils;

import com.example.vant_demo_success.service.model.UserModel;
import com.example.vant_demo_success.utils.token.TokenUtils;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 * @Package_Name: com.example.vant_demo_success.service.utils
 * @ClassName: UserUtIls
 * @Description:
 * @Author: cxy
 * @Date: 2020/1/20 13:28
 */
public class UserUtIls {
    public static JSONObject getReturnUserInfo(UserModel userModel) {
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
        userInfo.put("address",StringUtils.isEmpty(userModel.getAddress())?new JSONObject():userModel.getAddress());
        userInfo.put("collects", userModel.getCollects());
        userInfo.put("goodDetail", StringUtils.isEmpty(userModel.getGoodDetail())?new JSONObject():userModel.getGoodDetail());

        userInfo.put("bought", StringUtils.isEmpty(userModel.getBought())?"":userModel.getBought());
        userInfo.put("unpaid", StringUtils.isEmpty(userModel.getUnpaid())?"":userModel.getUnpaid());
        userInfo.put("unassess", StringUtils.isEmpty(userModel.getUnassess())?"":userModel.getUnassess());
        userInfo.put("unreceived", StringUtils.isEmpty(userModel.getUnreceived())?"":userModel.getUnreceived());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_Info", userInfo);
        jsonObject.put("token", token);
        return jsonObject;
    }
}
