package com.example.vant_demo_success.dao;

import com.example.vant_demo_success.dataobject.SubcategoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubcategoryDOMapper {
    int deleteByPrimaryKey(Integer subcategoryid);

    int insert(SubcategoryDO record);

    int insertSelective(SubcategoryDO record);

    SubcategoryDO selectByPrimaryKey(Integer subcategoryid);

    List<SubcategoryDO> selectByCategoryId(Integer categoryId);

    int updateByPrimaryKeySelective(SubcategoryDO record);

    int updateByPrimaryKey(SubcategoryDO record);

    // 小 标签
    List<SubcategoryDO> selectSubCategoryByCategoryId(@Param("categoryId") Integer categoryId);

}