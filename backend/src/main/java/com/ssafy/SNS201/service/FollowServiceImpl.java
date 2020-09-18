package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.dto.Follow;
import com.ssafy.SNS201.mapper.EntryMapper;
import com.ssafy.SNS201.mapper.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper mapper;

    @Override
    public List<Follow> findAllFollowMe(int memberNo) {
        return mapper.selectFollowMe(memberNo);
    }

    @Override
    public List<Follow> findAllFollowYou(int memberNo) {
        return mapper.selectFollowYou(memberNo);
    }

    @Override
    public boolean addFollow(Follow follow) { return mapper.insertFollow(follow) == 1; }

    @Override
    @Transactional
    public boolean removeFollow(int me, int you) {
        return mapper.deleteFollow(me, you) == 1;
    }

    @Override
    public Integer countFollowMe(int memberNo) { return mapper.countFollowMe(memberNo); }

    @Override
    public Integer countFollowYou(int memberNo) { return mapper.countFollowYou(memberNo); }
}
