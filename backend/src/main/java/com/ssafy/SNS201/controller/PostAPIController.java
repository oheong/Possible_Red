package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Post;
import com.ssafy.SNS201.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/post")
public class PostAPIController {
    private static final Logger logger = LoggerFactory.getLogger(PostAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String imagePath = "/home/ubuntu/backend/src/main/webapp/";

    @Autowired
    PostService postService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
    @GetMapping("all")
    public ResponseEntity<List<Post>> findAllPosts() throws Exception {
        logger.info("findAllPosts | " + new Date());
        List<Post> posts = postService.findAllPosts();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 번호를 통해 게시글을 조회한다.", response = Post.class)
    @GetMapping("{no}")
    public ResponseEntity<Post> findPostByNo(@PathVariable int no){
        logger.debug("findPostByNo");
        return new ResponseEntity<Post>(postService.findPostByNo(no), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 번호를 통해 게시글을 수정한다.", response = String.class)
    @PutMapping()
    public ResponseEntity<String> modifyPost(@RequestBody Post post){
        logger.debug("modifyPost");
        if (postService.modifyPost(post)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "게시글 번호를 통해 게시글을 삭제한다.", response = String.class)
    @DeleteMapping("{no}")
    public ResponseEntity<String> removePost(@PathVariable int no){
        logger.debug("removePost");
        if (postService.removePost(no)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "게시글을 작성한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> addPost(
            @RequestPart("postImg") MultipartFile file,
            Post post
    ) throws IOException {
        logger.info("addPost | " + post.toString());
        logger.info(file.toString());

        String originalFileName = file.getOriginalFilename();
        post.setPostPhoto(imagePath + originalFileName);
        File dest = new File(imagePath+ originalFileName);
        file.transferTo(dest);

        if (postService.addPost(post)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "멤버 번호를 통해 게시글을 조회한다.", response = List.class)
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<List<Post>> findPostByMemberNo(@PathVariable int memberNo){
        logger.debug("findPostByMemberNo");
        return new ResponseEntity<List<Post>>(postService.findPostByMemberNo(memberNo), HttpStatus.OK);
    }

    @ApiOperation(value = "미션별 포스트 리스트를 반환한다.", response = List.class)
    @GetMapping("/mission/{missionNo}")
    public ResponseEntity<List<Post>> findPostByMissionNo(@PathVariable int missionNo){
        logger.debug("findPostByMissionNo");
        return new ResponseEntity<List<Post>>(postService.findPostByMissionNo(missionNo), HttpStatus.OK);
    }

    @ApiOperation(value = "가장 마지막에 추가된 포스트의 번호를 반환한다", response = Integer.class)
    @GetMapping("/last")
    public ResponseEntity<Integer> findMaxPostNo(){
        Integer result = postService.findMaxPostNo();
        logger.debug("findMaxPostNo | " + result);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
