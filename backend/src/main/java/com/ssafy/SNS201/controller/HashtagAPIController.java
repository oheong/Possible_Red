package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Comment;
import com.ssafy.SNS201.dto.Hashtag;
import com.ssafy.SNS201.service.CommentService;
import com.ssafy.SNS201.service.HashtagService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/hashtag")
public class HashtagAPIController {
    private static final Logger logger = LoggerFactory.getLogger(HashtagAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    HashtagService hashtagService;

    @ApiOperation(value = "해당 게시글의 모든 해시태그 정보를 반환한다.", response = List.class)
    @GetMapping("{postNo}")
    public ResponseEntity<List<Hashtag>> findAllHashtags(@PathVariable int postNo) throws Exception {
        logger.info("findAllHashtags | postNo |" + postNo);
        List<Hashtag> hashtags = hashtagService.findAllHashtags(postNo);
        return new ResponseEntity<List<Hashtag>>(hashtags, HttpStatus.OK);
    }

    @ApiOperation(value = "해당 미션에 새로운 해시태그를 등록한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> addHashtag(@RequestBody Hashtag hashtag) throws Exception {
        logger.info("addHashtag | " + hashtag);
        if(hashtagService.addHashtag(hashtag)) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "해당 미션에 선택한 해시태그 정보를 삭제한다.", response = String.class)
    @DeleteMapping("{hashtagNo}")
    public ResponseEntity<String> removeHashtag(@PathVariable int hashtagNo){
        logger.info("modifyHashtag | " + hashtagNo);
        if (hashtagService.removeHashtag(hashtagNo)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
