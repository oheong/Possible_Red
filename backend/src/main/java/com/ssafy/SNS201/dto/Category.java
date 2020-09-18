package com.ssafy.SNS201.dto;

public class Category {
    private int categoryNo; // ai
    private String categoryContent;

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public String getCategoryContent() {
        return categoryContent;
    }
}
