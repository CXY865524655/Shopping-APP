package com.example.vant_demo_success;

//import com.example.vant_demo_success.Receiver.HomeDataReceiver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages =  {"com.example.vant_demo_success"})
@MapperScan(basePackages = {"com.example.vant_demo_success.dao"})
@RestController   // RestController === Controller + ResponseBody
public class VantDemoSuccessApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication o = new SpringApplication(VantDemoSuccessApplication.class);
        o.run(args);
    }
}
