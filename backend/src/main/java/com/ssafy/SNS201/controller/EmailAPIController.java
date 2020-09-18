package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/email")
public class EmailAPIController {

    public static final Logger logger = LoggerFactory.getLogger(EmailAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private MailService mailService;

    /*
    * post로 이메일 보낼때 난수를 같이 front단으로 보낸다.
    * 프론트에서 맞는지 아닌지 확인함.
    */

    @ApiOperation(value = " 메일 보낼 이메일 받아오기 ", response = String.class)
    @PostMapping("{email}") // 뷰단에서 이메일을 받아와서 이메일을 보내주는 역할을 한다.
    public ResponseEntity<String> addMember(@PathVariable String email) throws Exception {
        int random  = new Random().nextInt(900000)+123456;
        String toRandom = Integer.toString(random);
        if(mailService.send("Team Possible Red - 이메일 인증 번호",toRandom,email)) {
            return new ResponseEntity<String>(toRandom, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
}
