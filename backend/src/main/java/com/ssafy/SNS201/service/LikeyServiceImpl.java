package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Likey;
import com.ssafy.SNS201.mapper.LikeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeyServiceImpl implements LikeyService{

    @Autowired
    private LikeyMapper mapper;

    @Override
    public int findLikeyByNameAndPostNo(Likey likey) {
        return mapper.selectLikeyByNameAndPostNo(likey);
    }

    @Override
    public boolean findIsLikey(Likey likey) {
        return mapper.selectIsLikey(likey) == 1;
    }

    @Override
    public List<Likey> findLikeyByPostNo(int postNo) {
        return mapper.selectLikeyByPostNo(postNo);
    }

    @Override
    public boolean addLikey(Likey likey) {
        return mapper.insertLikey(likey) == 1;
    }

    @Override
    public boolean removeLikey(Likey likey) {
        return mapper.deleteLikey(likey) == 1;
    }

}
