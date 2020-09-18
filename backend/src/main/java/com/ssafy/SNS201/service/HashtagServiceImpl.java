package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Hashtag;
import com.ssafy.SNS201.mapper.HashtagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HashtagServiceImpl implements HashtagService{

    @Autowired
    private HashtagMapper mapper;

    @Override
    public List<Hashtag> findAllHashtags(int postNo) {
        return mapper.selectHashtag(postNo);
    }

    @Override
    public boolean addHashtag(Hashtag hashtag) {
        return mapper.insertHashtag(hashtag);
    }

    @Override
    @Transactional
    public boolean removeHashtag(int hashtagNo) {
        return mapper.deleteHashtag(hashtagNo);
    }

    @Override
    public List<Hashtag> findAllHashtagsBySearch(String keyword) {
        return mapper.selectHashtagBySearch(keyword);
    }
}
