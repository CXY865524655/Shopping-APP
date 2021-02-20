package com.example.vant_demo_success.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.vant_demo_success.annotate.User.User;
import com.example.vant_demo_success.service.model.AdminModel;
import com.example.vant_demo_success.service.model.UserModel;

/**
 * @Package_Name: com.example.vant_demo_success.utils.token
 * @ClassName: TokenUtils
 * @Description:
 * @Author: cxy
 * @Date: 2020/1/2 12:12
 */
public class TokenUtils {
    public static String getToken(UserModel user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String getAdminToken(AdminModel admin) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(admin.getId()))
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }
}
