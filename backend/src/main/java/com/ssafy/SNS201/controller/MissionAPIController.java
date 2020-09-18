package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.dto.Mission;
import com.ssafy.SNS201.service.MissionService;
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
@RequestMapping("/mission")
@Api(value="SSAFY")
public class MissionAPIController {

    public static final Logger logger = LoggerFactory.getLogger(MissionAPIController.class);

    @Autowired
    private MissionService missionService;

    @ApiOperation(value = "모든 미션의 정보를 반환한다", response = List.class)
    @GetMapping("/all")
    public ResponseEntity<List<Mission>> findMission() throws Exception{
        logger.debug("-----------mission list-----------");
        List<Mission> missions = missionService.findMission();
        if(missions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Mission>>(missions, HttpStatus.OK);
    }

    @ApiOperation(value = "어떤 사용자가 진행중인 미션의 리스트를 반환한다", response = List.class)
    @GetMapping("member/{memberNo}")
    public ResponseEntity<List<Mission>> findMissionByMember(@PathVariable int memberNo) throws Exception{
        logger.debug("-----------mission find by memberNo-----------");
        return new ResponseEntity<List<Mission>>(
                missionService.findMissionByMember(memberNo), HttpStatus.OK);
    }

    @ApiOperation(value = "입력된 검색어가 이름에 포함된 미션의 리스트를 반환한다", response = List.class)
    @GetMapping("search/{word}")
    public ResponseEntity<List<Mission>> findMissionByTitle(@PathVariable String word) throws Exception{
        logger.debug("-----------mission find by title-----------");
//        if (word.contains("#")) {
//
//        } else if (word.contains("@")) {
//
//        } else {
//            return new ResponseEntity<List<Mission>>(
//                    missionService.findMissionByTitle(word), HttpStatus.OK);
//        }

        return new ResponseEntity<List<Mission>>(
                missionService.findMissionByTitle(word), HttpStatus.OK);
    }

    @ApiOperation(value = "어떤 미션의 상세 정보를 반환한다", response = Member.class)
    @GetMapping("/{missionNo}")
    public ResponseEntity<Mission> findMissionByNo(@PathVariable int missionNo) throws Exception{
        logger.debug("-----------mission find by no-----------");
        return new ResponseEntity<Mission>(
                missionService.findMissionByNo(missionNo), HttpStatus.OK);
    }

    @ApiOperation(value = " 새로운 미션의 정보를 입력한다. 그리고 미션 번호를 반환한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<Integer> addMission(@RequestBody Mission mission) throws Exception{
        logger.debug("-----------mission add-----------");
        if(missionService.addMission(mission))
            return new ResponseEntity<Integer>(missionService.findMissionMaxCount(),HttpStatus.OK);
        return new ResponseEntity<Integer>(-1,HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 미션 정보를 수정한다. 그리고 성공여부를 반환한다.", response = String.class)
    @PutMapping()
    public ResponseEntity<String> modifyMission(@RequestBody Mission mission) throws Exception{
        logger.debug("-----------mission modify-----------");
        if(missionService.modifyMission(mission))
            return new ResponseEntity<String>("success",HttpStatus.OK);
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 미션 정보를 삭제한다. 그리고 성공여부를 반환한다.", response = String.class)
    @DeleteMapping("/{missionNo}")
    public ResponseEntity<String> removeMission(@PathVariable int missionNo) throws Exception{
        logger.debug("-----------mission remove-----------");
        if(missionService.removeMission(missionNo))
            return new ResponseEntity<String>("success",HttpStatus.OK);
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "카테고리별 미션 리스트를 반환한다.", response = List.class)
    @GetMapping("category/{missionCat}")
    public ResponseEntity<List<Mission>> findMissionByCategory(@PathVariable String missionCat) throws Exception{
        logger.debug("-----------mission find by category-----------");
        List<Mission> missions = missionService.findMissionByCategory(missionCat);
        if(missions.isEmpty())
            return new ResponseEntity<List<Mission>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Mission>>(missions, HttpStatus.OK);
    }

    @ApiOperation(value = "미션별 참여 인원수를 반환한다.", response = Integer.class)
    @GetMapping("count/{missionNo}")
    public ResponseEntity<Integer> findMissionCountByMissionNo(@PathVariable int missionNo) throws Exception{
        logger.debug("-----------counting entry by missionNo-----------");
        return new ResponseEntity<Integer>(missionService.findMissionCountByMissionNo(missionNo), HttpStatus.OK);
    }
}
