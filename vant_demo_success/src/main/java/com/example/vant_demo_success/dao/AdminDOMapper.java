package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.AdminDO;

public interface AdminDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    int insert(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    int insertSelective(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    AdminDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    int updateByPrimaryKeySelective(AdminDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbg.generated Thu Apr 09 17:27:57 CST 2020
     */
    int updateByPrimaryKey(AdminDO record);
}