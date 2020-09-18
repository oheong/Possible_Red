package com.ssafy.SNS201.dto;

import java.util.Date;

public class Mission {
    private int missionNo; // ai
    private String missionTitle;
    private Date startDate;
    private Date endDate;
    private boolean isStart;
    private int memberNo; // fk
    private int point;
    private int minusPoint;
    private int cutCnt;
    private int joinMem; // count
    private String missionPhoto;
    private String missionCat;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public int getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(int missionNo) {
        this.missionNo = missionNo;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getMinusPoint() {
        return minusPoint;
    }

    public void setMinusPoint(int minusPoint) {
        this.minusPoint = minusPoint;
    }

    public int getCutCnt() {
        return cutCnt;
    }

    public void setCutCnt(int cutCnt) {
        this.cutCnt = cutCnt;
    }

    public int getJoinMem() {
        return joinMem;
    }

    public void setJoinMem(int joinMem) {
        this.joinMem = joinMem;
    }

    public String getMissionPhoto() {
        return missionPhoto;
    }

    public void setMissionPhoto(String missionPhoto) {
        this.missionPhoto = missionPhoto;
    }

    public String getMissionCat() {
        return missionCat;
    }

    public void setMissionCat(String missionCat) {
        this.missionCat = missionCat;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionNo=" + missionNo +
                ", missionTitle='" + missionTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isStart=" + isStart +
                ", memberNo=" + memberNo +
                ", point=" + point +
                ", minusPoint=" + minusPoint +
                ", cutCnt=" + cutCnt +
                ", joinMem=" + joinMem +
                ", missionPhoto='" + missionPhoto + '\'' +
                ", missionCat='" + missionCat + '\'' +
                '}';
    }
}
