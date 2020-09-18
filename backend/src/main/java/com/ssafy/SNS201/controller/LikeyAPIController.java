package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Likey;
import com.ssafy.SNS201.service.LikeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/likey")
@Api(value="SSAFY")
public class LikeyAPIController {

    public static final Logger logger = LoggerFactory.getLogger(LikeyAPIController.class);

    @Autowired
    private LikeyService likeyService;

    @ApiOperation(value = "포스트 번호별로 좋아요 리스트를 반환한다", response = List.class)
    @GetMapping("/{postNo}")
    public ResponseEntity<List<Likey>> findLikeyByPostNo(@PathVariable int postNo) throws Exception{
        logger.info("-----------likey find by post no-----------");
        return new ResponseEntity<List<Likey>>(
                likeyService.findLikeyByPostNo(postNo), HttpStatus.OK);
    }

    @ApiOperation(value = "로그인한 멤버가 해당 포스트의 좋아요 유무를 반환한다", response = List.class)
    @PostMapping("islike")
    public ResponseEntity<String> findIsLikey(@RequestBody Likey likey) throws Exception{
        logger.info("-----------likey find is likey-----------" + likey.toString());
        if(likeyService.findIsLikey(likey))
            return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 새로운 좋아요 정보를 입력한다. 그리고 성공여부를 반환한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> addLikey(@RequestBody Likey likey) throws Exception{
        logger.info("-----------likey add-----------");
        if(likeyService.addLikey(likey))
            return new ResponseEntity<String>("success",HttpStatus.OK);
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 멤버 넘버와 포스트 넘버로 좋아요 정보를 삭제한다. 그리고 성공여부를 반환한다.", response = String.class)
    @PostMapping("/delete")
    public ResponseEntity<String> removeLikey(@RequestBody Likey likey) throws Exception{
        logger.info("--------------likey remove-----------");
        if(likeyService.removeLikey(likey))
            return new ResponseEntity<String>("success",HttpStatus.OK);
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }
}
