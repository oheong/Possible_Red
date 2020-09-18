package com.ssafy.SNS201.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Follow {
    private int followNo;
    private int me;
    private int you;
    private Date followDate;

    public int getFollowNo() {
        return followNo;
    }

    public void setFollowNo(int followNo) {
        this.followNo = followNo;
    }

    public int getMe() {
        return me;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public int getYou() {
        return you;
    }

    public void setYou(int you) {
        this.you = you;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }
}
