package com.ssafy.SNS201.controller;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.service.EntryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/entry")
@Api(value="SSAFY")
public class EntryAPIController {

    public static final Logger logger = LoggerFactory.getLogger(EntryAPIController.class);

    @Autowired
    private EntryService entryService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "모든 참여자의 정보를 반환한다.", response = List.class)
    @GetMapping("/all")
    public ResponseEntity<List<Entry>> findAllEntries() throws Exception {
        logger.info("1-------------findAllEntries-----------------------------"+new Date());
        List<Entry> entries = entryService.findAllEntries();
        if (entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Entry>>(entries, HttpStatus.OK);
    }

    @ApiOperation(value = "참여자의 상세 정보를 반환한다.", response = Entry.class)
    @GetMapping("/{entryNo}")
    public ResponseEntity<Entry> findEntryByNo(@PathVariable int entryNo) throws Exception {
        return new ResponseEntity<Entry>(
                entryService.findEntryByNo(entryNo), HttpStatus.OK
        );
    }

    @ApiOperation(value = " 새로운 참여자의 정보를 입력한다. 그리고 그 참여자의 번호를 반환한다.", response = String.class)
    @PostMapping()
    public ResponseEntity<String> addEntry(@RequestBody Entry entry) throws Exception {
        logger.info("5-------------addEntry-----------------------------" + entry);
        System.out.println(entry.toString());
        if(entryService.addEntry(entry)) return new ResponseEntity<String>("success", HttpStatus.OK);
        else return new ResponseEntity<String>("fail", HttpStatus.OK);
    }

    @ApiOperation(value = " 참여자의 리워드퍼센트와 참여안한카운터를 수정한다.", response = String.class)
    @PutMapping()
    public ResponseEntity<String> modifyEntry(@RequestBody Entry entry) throws Exception {
        logger.info("1-------------modifyEntry-----------------------------" + entry);
        if (entryService.modifyEntry(entry)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = " 해당 참여자의 정보를 삭제한다.", response = String.class)
    @DeleteMapping("/{entryNo}")
    public ResponseEntity<String> removeEntry(@PathVariable int entryNo) throws Exception {
        logger.info("1-------------removeEntry-----------------------------" + entryNo);
        if (entryService.removeEntry(entryNo)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "본인이 참여할 수 있는지 없는지 여부를 판단한다.", response = String.class)
    @GetMapping("/{memberNo}/{missionNo}")
    public ResponseEntity<String> findCountByMemberNoAndMissionNo(@PathVariable int memberNo,@PathVariable int missionNo ) throws Exception {
        logger.info("1-------------can i join this mission?-----------------------------"+new Date());
        if (entryService.findCountByMemberNoAndMissionNo(memberNo,missionNo)==0) {
            return new ResponseEntity<String>("success",HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "미션 넘버별 엔트리를 반환한다.", response = List.class)
    @GetMapping("/missionByEntry/{missionNo}")
    public ResponseEntity<List<Entry>> findMemberNoByMissionNo(@PathVariable int missionNo ) throws Exception {
        logger.info("1-------------get MemberNo Entry by Mission No-----------------------------"+new Date());
        List<Entry> list = entryService.findMemberByMissionNo(missionNo);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Entry>>(list,HttpStatus.OK);
    }
}
