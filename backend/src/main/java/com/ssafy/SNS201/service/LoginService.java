package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Member;

import java.util.HashMap;

public interface LoginService {
    public Member login (String email, String pwd);
}
