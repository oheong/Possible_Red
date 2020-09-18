package com.ssafy.SNS201.controller;
import java.io.File;
import java.util.Date;
import java.util.List;

import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.FailedLoginException;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberAPIController {

    public static final Logger logger = LoggerFactory.getLogger(MemberAPIController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String imagePath = "/home/ubuntu/backend/src/main/webapp/profile/";

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "모든 사용자의 정보를 반환한다.", response = List.class)
    @GetMapping("all")
    public ResponseEntity<List<Member>> findAllMembers() throws Exception {
        logger.info("findAllMembers | " + new Date());
        List<Member> members = memberService.findAllMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자의 상세 정보를 반환한다.", response = Member.class)
    @GetMapping("{memberNo}")
    public ResponseEntity<Member> findMember(@PathVariable int memberNo) throws Exception {
        logger.info("findMember | " + memberNo);
        return new ResponseEntity<Member>(
                memberService.findMemberByNo(memberNo), HttpStatus.OK
        );
    }

    @ApiOperation(value = "이메일이 일치하는 사용자의 번호를 반환한다.", response = Member.class)
    @GetMapping("email/{email}")
    public ResponseEntity<Member> findMemberByEmail(@PathVariable String email) throws Exception {
        logger.info("findMemberByEmail | " + email);
        return new ResponseEntity<Member>(
                memberService.findMemberByEmail(email), HttpStatus.OK
        );
    }

    @ApiOperation(value = " 해당 사용자의 정보를 삭제한다.", response = String.class)
    @DeleteMapping("{memberNo}")
    public ResponseEntity<String> removeMember(@PathVariable int memberNo) throws Exception {
        logger.info("removeMember | " + memberNo);
        if (memberService.removeMember(memberNo)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = " 새로운 사용자의 정보를 입력한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody Member member) throws Exception {
        logger.info("addMember | " + member);

        if(memberService.addMember(member)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = " 사용자의 정보를 수정한다.", response = String.class)
    @PutMapping
    public ResponseEntity<String> modifyMember(Member member,
            @RequestPart("memberImg") MultipartFile file ) throws Exception {
        logger.info("modifyMember | " + member);

        // 여기에서 이미지 작업 해야함

        String originalFileName = file.getOriginalFilename();
        File dest = new File(imagePath + originalFileName);
        file.transferTo(dest);

        if(originalFileName.length() == 0)
            member.setMemberPhoto(null);
        else
            member.setMemberPhoto(imagePath + originalFileName);

        if (memberService.modifyMember(member)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 사용자의 포인트를 수정한다.", response = String.class)
    @PutMapping("point/{memberNo}/{point}")
    public ResponseEntity<String> modifyMemberPoint(@PathVariable int memberNo, @PathVariable int point) throws Exception {
        logger.info("modifyMemberPoint | " + memberNo + "update Point to "+point);
        if(memberService.modifyMemberPoint(memberNo, point))
            return new ResponseEntity<String>(SUCCESS,HttpStatus.NO_CONTENT);
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 사용자의 비밀번호를 수정한다.", response = String.class)
    @PutMapping("password")
    public ResponseEntity<String> modifyPassword(@RequestBody Member member) throws Exception {
        logger.info("password update");
        if(memberService.modifyPassword(member))
            return new ResponseEntity<String>(SUCCESS,HttpStatus.NO_CONTENT);
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
    }
}
