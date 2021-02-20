package com.example.vant_demo_success.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.vant_demo_success.annotate.PassToken;
import com.example.vant_demo_success.annotate.User.User;
import com.example.vant_demo_success.annotate.UserLoginToken;
import com.example.vant_demo_success.err.BusinessException;
import com.example.vant_demo_success.err.EnBusinessError;
import com.example.vant_demo_success.service.UserService;
import com.example.vant_demo_success.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Package_Name: com.example.vant_demo_success.interceptors
 * @ClassName: AuthenticationInterceptor
 * @Description:
 * @Author: cxy
 * @Date: 2020/1/2 12:38
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String tokenAll = request.getHeader("token");
//        String token = tokenAll.substring(7);
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        // 检查是否有passToken注释，有则跳过认证
//        if(method.isAnnotationPresent(PassToken.class)) {
//            PassToken annotation = method.getAnnotation(PassToken.class);
//            if (annotation.required()) {
//                return true;
//            }
//        }
//
//        // 检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new BusinessException(EnBusinessError.NO_TOKEN);
//                }
//
//                // 获取token中的 user id
//                String userId;
//
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                }catch (JWTDecodeException e) {
//                    throw new BusinessException(EnBusinessError.TOKEN_ERR);
//                }
//
//                UserModel user = userService.getUserByUserId(Integer.parseInt(userId));
//
//                // 验证token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                }catch (JWTVerificationException e) {
//                    throw new BusinessException(EnBusinessError.TOKEN_ERR);
//                }
//                return true;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
