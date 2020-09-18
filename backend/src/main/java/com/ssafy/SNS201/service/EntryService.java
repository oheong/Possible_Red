package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.dto.Member;

import java.util.List;

public interface EntryService {
    public List<Entry> findAllEntries();
    public Entry findEntryByNo(int entryNo);
    public boolean addEntry(Entry entry);
    public boolean modifyEntry(Entry entry);
    public boolean removeEntry(int entryNo);
    public int findCountByMemberNoAndMissionNo(int memberNo, int missionNo);
    public List<Entry> findMemberByMissionNo(int missionNo);
}
