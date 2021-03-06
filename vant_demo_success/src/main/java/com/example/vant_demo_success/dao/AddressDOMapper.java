package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.AddressDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    int deleteByPrimaryKey(String id);
    int delByUserid(@Param("userid") Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    int insert(AddressDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    int insertSelective(AddressDO record);
    int addAddress(AddressDO record);
    int ediAddress(AddressDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    AddressDO selectByPrimaryKey(String id);
    List<AddressDO> getAddressList(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    int updateByPrimaryKeySelective(AddressDO record);
    int updateOtherisDefaultFalse(@Param("userid") Integer userid,@Param("id") String id);
    int delAddress(@Param("userid") Integer userid,@Param("id") String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated Sun Apr 26 10:50:02 CST 2020
     */
    int updateByPrimaryKey(AddressDO record);
}