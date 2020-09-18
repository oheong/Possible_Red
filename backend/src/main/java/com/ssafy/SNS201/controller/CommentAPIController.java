package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Comment;
import com.ssafy.SNS201.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentAPIController {
    private static final Logger logger = LoggerFactory.getLogger(PostAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    CommentService commentService;

    @ApiOperation(value = "해당 게시글의 모든 댓글의 정보와 닉네임을 반환한다.", response = List.class)
    @GetMapping("{postNo}")
    public ResponseEntity<List<Comment>> findAllComments(@PathVariable int postNo) throws Exception {
        logger.info("findAllComments | " + postNo);
        List<Comment> comments = commentService.findAllComments(postNo);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }


    @ApiOperation(value = "해당 게시글에 새로운 댓글을 입력한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody Comment comment) throws Exception {
        logger.info("addComment | " + comment);
        if(commentService.addComment(comment)) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }


    @ApiOperation(value = "댓글의 정보를 수정한다.", response = String.class)
    @PutMapping("{commentNo}")
    public ResponseEntity<String> modifyComment(@RequestBody Comment comment){
        logger.info("modifyComment | " + comment);
        if (commentService.modifyComment(comment)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "해당 댓글의 정보를 삭제한다.", response = String.class)
    @DeleteMapping("{commentNo}")
    public ResponseEntity<String> removeComment(@PathVariable int commentNo){
        logger.info("modifyComment | " + commentNo);
        if (commentService.removeComment(commentNo)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
