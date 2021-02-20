package com.example.vant_demo_success.Receiver;

import com.example.vant_demo_success.dao.HomeDOMapper;
import com.example.vant_demo_success.dataobject.HomeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.Calendar;

/**
 * @Package_Name: com.example.vant_demo_success.Receiver
 * @ClassName: HomeDataReceiver
 * @Description:
 * @Author: cxy
 * @Date: 2020/2/29 18:17
 */
@Configuration
@Order(-100)
public class HomeDataReceiver implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private HomeDOMapper homeDOMapper;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long zero = calendar.getTimeInMillis();
//
        HomeDO homeDO = homeDOMapper.selectByPrimaryKey(1);
        Long time = homeDO.getTime();
        if(StringUtils.isEmpty(time)){
            homeDOMapper.updateTime(zero);
        }
//        System.out.println(homeDO.getTime());
//        System.out.println(homeDOMapper);
    }
}
