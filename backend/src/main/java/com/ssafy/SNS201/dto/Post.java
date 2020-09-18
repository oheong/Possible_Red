package com.ssafy.SNS201.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Component
public class Post {
    private int postNo;
    private int memberNo;
    private Date postDate;
    private String postPhoto;
    private String postContent;
    private int missionNo;

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(String postPhoto) {
        this.postPhoto = postPhoto;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getMissionNo() {
        return missionNo;
    }

    public void setMissionNo(int missionNo) {
        this.missionNo = missionNo;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postNo=" + postNo +
                ", memberNo=" + memberNo +
                ", postDate=" + postDate +
                ", postPhoto='" + postPhoto + '\'' +
                ", postContent='" + postContent + '\'' +
                ", missionNo=" + missionNo +
                '}';
    }
}
