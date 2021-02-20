package com.example.vant_demo_success.task;

import com.alibaba.fastjson.JSONObject;
import com.example.vant_demo_success.dao.GoodsListDOMapper;
import com.example.vant_demo_success.dao.HomeDOMapper;
import com.example.vant_demo_success.dataobject.HomeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.List;

/**
 * @Package_Name: com.example.vant_demo_success.task
 * @ClassName: ApplicationStartTask
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/29 18:23
 */
@Configuration
@EnableScheduling
public class ApplicationStartTask{

    Long oldTime;
    Calendar calendar;
    Long zero;

    @Autowired
    @SuppressWarnings("all")
    HomeDOMapper homeDOMapper;

    @Autowired
    @SuppressWarnings("all")
    private GoodsListDOMapper goodsListDOMapper;


    @Scheduled(fixedRate = 1000)
    private void doSomething() {

        if (!StringUtils.isEmpty(oldTime)) {
            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            zero = calendar.getTimeInMillis();
            if((zero - oldTime)>=(1000*60*60*24)) {
                homeDOMapper.updateTime(zero);
                oldTime = zero;
                List<Integer> goodPrice = goodsListDOMapper.getGoodPrice();
                String s1 = JSONObject.toJSONString(goodPrice);
                homeDOMapper.updateGoodPrice(s1);
                List<Integer> headerSwipes = goodsListDOMapper.getHeaderSwipes();
                String s2 = JSONObject.toJSONString(headerSwipes);
                homeDOMapper.updateHeaderSwipes(s2);
                List<Integer> recommends = goodsListDOMapper.getRecommends();
                String s3 = JSONObject.toJSONString(recommends);
                homeDOMapper.updateRecommends(s3);
            }
        }else {
            HomeDO homeDO = homeDOMapper.selectByPrimaryKey(1);
            oldTime = homeDO.getTime();
        }
    }
}
