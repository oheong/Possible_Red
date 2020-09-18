package com.ssafy.SNS201.dto;



import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Likey {
    private int likeyNo;
    private int memberNo;
    private int postNo;
    private Date pushDate;

    public int getLikeyNo() {
        return likeyNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setLikeyNo(int likeyNo) {
        this.likeyNo = likeyNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    @Override
    public String toString() {
        return "Likey{" +
                "likeyNo=" + likeyNo +
                ", memberNo=" + memberNo +
                ", postNo=" + postNo +
                ", pushDate=" + pushDate +
                '}';
    }
}
