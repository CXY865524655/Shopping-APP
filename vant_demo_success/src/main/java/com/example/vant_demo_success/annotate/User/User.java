package com.example.vant_demo_success.annotate.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package_Name: com.example.vant_demo_success.annotate.User
 * @ClassName: User
 * @Description:
 * @Author: cxy
 * @Date: 2020/1/2 12:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String username;
    String password;
}
