package com.ssafy.SNS201.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.SNS201.dto.Member;

@Mapper
public interface LoginMapper {
    public Member login(String email, String pwd);
}
