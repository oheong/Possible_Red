package com.ssafy.SNS201.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Comment {
    private int commentNo;
    private int memberNo;
    private String commentContent;
    private int postNo;
    private Date commentDate;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public int getCommentNo() {
        return commentNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public int getPostNo() {
        return postNo;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
