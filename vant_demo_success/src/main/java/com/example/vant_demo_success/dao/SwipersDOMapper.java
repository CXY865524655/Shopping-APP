package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.SwipersDO;

import java.util.List;

public interface SwipersDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    int insert(SwipersDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    int insertSelective(SwipersDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    SwipersDO selectByPrimaryKey(Integer id);


    List<SwipersDO> select();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    int updateByPrimaryKeySelective(SwipersDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swipers
     *
     * @mbg.generated Mon Nov 25 18:26:33 CST 2019
     */
    int updateByPrimaryKey(SwipersDO record);
}