package com.ssafy.SNS201.mapper;

import com.ssafy.SNS201.dto.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public List<Category> selectCategory();
}
