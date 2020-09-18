package com.ssafy.SNS201.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Hashtag {
    private int hashtagNo;
    private int postNo;
    private String hashtagContent;

    public int getHashtagNo() {
        return hashtagNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public String getHashtagContent() {
        return hashtagContent;
    }

    public void setHashtagNo(int hashtagNo) {
        this.hashtagNo = hashtagNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setHashtagContent(String hashtagContent) {
        this.hashtagContent = hashtagContent;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtagNo=" + hashtagNo +
                ", postNo=" + postNo +
                ", hashtagContent='" + hashtagContent + '\'' +
                '}';
    }
}
