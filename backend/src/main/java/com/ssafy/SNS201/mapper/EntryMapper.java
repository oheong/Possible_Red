package com.ssafy.SNS201.mapper;

import com.ssafy.SNS201.dto.Entry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntryMapper {
    public List<Entry> selectEntry();
    public Entry selectEntryByNo(int entryNo);
    public int insertEntry(Entry entry);
    public int updateEntry(Entry entry);
    public int deleteEntry(int entryNo);
    public int selectCountByMemberNoAndMissionNo(int memberNo, int missionNo);
    public List<Entry> selectMemberByMissionNo(int missionNo);
}
