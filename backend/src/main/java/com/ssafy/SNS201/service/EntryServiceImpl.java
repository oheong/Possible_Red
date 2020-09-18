package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.mapper.EntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryMapper mapper;

    @Override
    public List<Entry> findAllEntries() {
        return mapper.selectEntry();
    }

    @Override
    public Entry findEntryByNo(int entryNo) {
        return mapper.selectEntryByNo(entryNo);
    }

    @Override
    public boolean addEntry(Entry entry) { return mapper.insertEntry(entry) == 1; }

    @Override
    @Transactional
    public boolean modifyEntry(Entry entry) {
        return mapper.updateEntry(entry) == 1;
    }

    @Override
    @Transactional
    public boolean removeEntry(int entryNo) {
        return mapper.deleteEntry(entryNo) == 1;
    }

    @Override
    public int findCountByMemberNoAndMissionNo(int memberNo, int missionNo) {
        return mapper.selectCountByMemberNoAndMissionNo(memberNo,missionNo);
    }

    @Override
    public List<Entry> findMemberByMissionNo(int missionNo) {
        return mapper.selectMemberByMissionNo(missionNo);
    }
}
