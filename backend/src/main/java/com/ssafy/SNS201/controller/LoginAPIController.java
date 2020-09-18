package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/login")
public class LoginAPIController {
    private static final Logger logger = LoggerFactory.getLogger(LoginAPIController.class);

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "이메일과 비밀번호가 일치하는 사용자 정보를 반환한다.", response = Member.class)
    @PostMapping
    public ResponseEntity<Member> login(@RequestBody Member member) {
        logger.info("loginMember | " + member);
        Member memberInfo = loginService.login(member.getEmail(), member.getPwd());
        if (memberInfo != null) {
            return new ResponseEntity<Member>(memberInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<Member>(new Member(), HttpStatus.NO_CONTENT);
        }
    }
}
