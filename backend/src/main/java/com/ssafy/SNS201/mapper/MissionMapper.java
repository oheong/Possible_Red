package com.ssafy.SNS201.mapper;

import com.ssafy.SNS201.dto.Mission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MissionMapper {
    public List<Mission> selectMission();
    public List<Mission> selectMissionByMember(int memberNo);
    public List<Mission> selectMissionByTitle(String word);
    public Mission selectMissionByNo(int missionNo);
    public int insertMission(Mission mission);
    public int updateMission(Mission mission);
    public int deleteMission(int missionNo);
    public List<Mission> selectMissionByCategory(String missionCat);
    public int selectMaxCount();
    public int selectCountByMissionNo(int missionNo);
}
