package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Mission;

import java.util.List;

public interface MissionService {
    public List<Mission> findMission();
    public List<Mission> findMissionByMember(int memberNo);
    public List<Mission> findMissionByTitle(String word);
    public Mission findMissionByNo(int missionNo);
    public boolean addMission(Mission mission);
    public boolean modifyMission(Mission mission);
    public boolean removeMission(int missionNo);
    public List<Mission> findMissionByCategory(String missionCat);
    public int findMissionMaxCount();
    public int findMissionCountByMissionNo(int missionNo);
}
