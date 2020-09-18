package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Category;
import com.ssafy.SNS201.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<Category> findCategory() {
        return mapper.selectCategory();
    }
}
