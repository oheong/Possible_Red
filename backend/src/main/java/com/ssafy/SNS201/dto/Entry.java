package com.ssafy.SNS201.dto;

import org.springframework.stereotype.Component;

@Component
public class Entry {
    private int entryNo;
    private int memberNo;
    private int missionNo;
    private int reward;
    private int nonCnt;

    public int getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(int entryNo) {
        this.entryNo = entryNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(int missionNo) {
        this.missionNo = missionNo;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getNonCnt() {
        return nonCnt;
    }

    public void setNonCnt(int nonCnt) {
        this.nonCnt = nonCnt;
    }
}
