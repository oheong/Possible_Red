package com.ssafy.SNS201.mapper;

import com.ssafy.SNS201.dto.Entry;
import com.ssafy.SNS201.dto.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    public List<Follow> selectFollowMe(int memberNo);
    public List<Follow> selectFollowYou(int memberNo);
    public int insertFollow(Follow follow);
    public int deleteFollow(int me, int you);
    public Integer countFollowMe(int memberNo);
    public Integer countFollowYou(int memberNo);
}
