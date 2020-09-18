package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Hashtag;
import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.dto.Post;
import com.ssafy.SNS201.service.HashtagService;
import com.ssafy.SNS201.service.MemberService;
import com.ssafy.SNS201.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/search")
public class SearchAPIController {
    private static final Logger logger = LoggerFactory.getLogger(SearchAPIController.class);

    @Autowired
    MemberService memberService;
    @Autowired
    PostService postService;
    @Autowired
    HashtagService hashtagService;

    @ApiOperation(value = "# 를 사용해 해시태그 검색을 한다.", response = List.class)
    @GetMapping("/hashtag/{keyword}")
    public ResponseEntity<List<Hashtag>> findAllHashtagsBySearch(@PathVariable String keyword){
        logger.debug("findHashtagBySearch");
        List<Hashtag> hashtags = hashtagService.findAllHashtagsBySearch(keyword);
        if (hashtags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Hashtag>>(hashtags, HttpStatus.OK);
    }

    @ApiOperation(value = "@ 를 사용해 사용자 검색을 한다.", response = List.class)
    @GetMapping("/member/{keyword}")
    public ResponseEntity<List<Member>> findAllMembersBySearch(@PathVariable String keyword){
        logger.debug("findMemberBySearch");
        List<Member> members = memberService.findAllMembersBySearch(keyword);
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @ApiOperation(value = " '' 를 사용해 포스트내용 검색을 한다.", response = List.class)
    @GetMapping("/post/{keyword}")
    public ResponseEntity<List<Post>> findAllPostsBySearch(@PathVariable String keyword){
        logger.debug("findPostBySearch");
        List<Post> posts = postService.findAllPostsBySearch(keyword);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
