package com.example.vant_demo_success.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.vant_demo_success.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Package_Name: com.example.vant_demo_success.service.impl
 * @ClassName: MailServiceImpl
 * @Description:
 * @Author: cxy
 * @Date: 2020/4/8 23:14
 */

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;


    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Override
    public boolean sendMessage(String tel, String msg) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "易购APP");
        request.putQueryParameter("TemplateCode", template_code);
        JSONObject object=new JSONObject();
        object.put("code",msg);
        request.putQueryParameter("TemplateParam", object.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
