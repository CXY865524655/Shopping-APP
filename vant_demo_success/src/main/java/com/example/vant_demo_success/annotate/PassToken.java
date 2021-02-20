package com.example.vant_demo_success.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package_Name: com.example.vant_demo_success.annotate
 * @ClassName: PassToken
 * @Description:
 * @Author: cxy
 * @Date: 2020/1/2 11:49
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
