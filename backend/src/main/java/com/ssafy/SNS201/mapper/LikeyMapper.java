package com.ssafy.SNS201.mapper;

import com.ssafy.SNS201.dto.Likey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeyMapper {
    public int selectLikeyByNameAndPostNo(Likey likey);
    public int selectIsLikey(Likey likey);
    public List<Likey> selectLikeyByPostNo(int postNo);
    public int insertLikey(Likey likey);
    public int deleteLikey(Likey likey);
}
