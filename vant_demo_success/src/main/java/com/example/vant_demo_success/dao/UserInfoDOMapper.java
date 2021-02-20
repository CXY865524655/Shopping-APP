package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.UserInfoDO;
import com.example.vant_demo_success.dataobject.UserPayPasswordDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    List<UserInfoDO> initUsers();
    List<UserInfoDO> searchUsers(@Param("content") String content);

    void delUserImgPath(@Param("userid") Integer userid);

    UserInfoDO selectByPrimaryKey(Integer id);

    UserInfoDO selectByUserName(@Param("username") String username);

    UserInfoDO selectByTelephone(String telephone);

    UserPayPasswordDO selectPayPWDByUserID(@Param("id") Integer id);

    int updateByPrimaryKeySelective(UserInfoDO record);


    void saveUser(UserInfoDO record);
    int updateByPrimaryKey(UserInfoDO record);

    void updateUserImgPath(@Param("imgPath") String imgPath, @Param("userid") Integer userid);

    int updateUserName(
            @Param("username") String username,
            @Param("id") Integer id
    );

    int updatePayPwdByUserID(
            @Param("userID") Integer userID,
            @Param("payPwd") String payPwd
    );




    int updateUserSex(
            @Param("sex") Integer sex,
            @Param("id") Integer id
    );

    int updateUserBirthday(
            @Param("birthday") String birthday,
            @Param("id") Integer id
    );
}