package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Mission;
import com.ssafy.SNS201.mapper.MissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService{

    @Autowired
    private MissionMapper mapper;

    @Override
    public List<Mission> findMission() {
        return mapper.selectMission();
    }

    @Override
    public List<Mission> findMissionByMember(int memberNo) {
        return mapper.selectMissionByMember(memberNo);
    }

    @Override
    public List<Mission> findMissionByTitle(String word) {
        return mapper.selectMissionByTitle(word);
    }

    @Override
    public Mission findMissionByNo(int missionNo) {
        return mapper.selectMissionByNo(missionNo);
    }

    @Override
    public boolean addMission(Mission mission) {
        return mapper.insertMission(mission) == 1;
    }

    @Override
    public boolean modifyMission(Mission mission) {
        return mapper.updateMission(mission) == 1;
    }

    @Override
    public boolean removeMission(int missionNo) {
        return mapper.deleteMission(missionNo) == 1;
    }

    @Override
    public List<Mission> findMissionByCategory(String missionCat) {
        return mapper.selectMissionByCategory(missionCat);
    }

    @Override
    public int findMissionMaxCount() {
        return mapper.selectMaxCount();
    }

    @Override
    public int findMissionCountByMissionNo(int missionNo) {
        return mapper.selectCountByMissionNo(missionNo);
    }


}
