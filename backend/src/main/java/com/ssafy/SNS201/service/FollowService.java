package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.dto.Follow;

import java.util.List;

public interface FollowService {
    public List<Follow> findAllFollowMe(int memberNo);
    public List<Follow> findAllFollowYou(int memberNo);
    public boolean addFollow(Follow follow);
    public boolean removeFollow(int me, int you);
    public Integer countFollowMe(int memberNo);
    public Integer countFollowYou(int memberNo);
}
