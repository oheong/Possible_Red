package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findAllComments(int postNo);
    public boolean addComment(Comment comment);
    public boolean modifyComment(Comment comment);
    public boolean removeComment(int commentNo);
}
