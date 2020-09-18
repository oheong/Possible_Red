package com.ssafy.SNS201.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.SNS201.dto.Member;
import com.ssafy.SNS201.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginMapper mapper;

    @Override
    public Member login(String email, String pwd) {
        if(email == null || pwd == null)
            return null;
        else return mapper.login(email,pwd);
    }
}
