package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Hashtag;

import java.util.List;

public interface HashtagService {
    public List<Hashtag> findAllHashtags(int postNo);
    public boolean addHashtag(Hashtag hashtag);
    public boolean removeHashtag(int hashtagNo);
    public List<Hashtag> findAllHashtagsBySearch(String keyword);
}
