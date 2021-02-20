package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.CategoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDOMapper {

    List<CategoryDO> getCategory(@Param("num") Integer num);
    int deleteByPrimaryKey(Integer categoryid);

    int insert(CategoryDO record);

    int insertSelective(CategoryDO record);

    CategoryDO selectByPrimaryKey(Integer categoryid);

    List<CategoryDO> select();

    int updateByPrimaryKeySelective(CategoryDO record);

    int updateByPrimaryKey(CategoryDO record);

    // 大 标签
    List<CategoryDO> selectCategoryList();
}