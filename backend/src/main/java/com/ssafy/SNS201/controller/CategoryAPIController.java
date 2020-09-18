package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Category;
import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/category")
public class CategoryAPIController {
    public static final Logger logger = LoggerFactory.getLogger(CategoryAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "모든 카테고리의 정보를 반환한다.", response = List.class)
    @GetMapping()
    public ResponseEntity<List<Category>> findCategory() throws Exception {
        logger.info("find all category");
        List<Category> categories = categoryService.findCategory();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

}
