package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Likey;

import java.util.List;

public interface LikeyService {
    public int findLikeyByNameAndPostNo(Likey likey);
    public boolean findIsLikey(Likey likey);
    public List<Likey> findLikeyByPostNo(int postNo);
    public boolean addLikey(Likey likey);
    public boolean removeLikey(Likey likey);
}
