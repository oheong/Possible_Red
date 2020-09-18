package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Comment;
import com.ssafy.SNS201.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper mapper;

    @Override
    public List<Comment> findAllComments(int postNo) {
        return mapper.selectComment(postNo);
    }

    @Override
    public boolean addComment(Comment comment) {
        return mapper.insertComment(comment);
    }

    @Override
    @Transactional
    public boolean modifyComment(Comment comment) {
        return mapper.updateComment(comment);
    }

    @Override
    @Transactional
    public boolean removeComment(int commentNo) {
        return mapper.deleteComment(commentNo);
    }
}
