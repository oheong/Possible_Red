package com.ssafy.SNS201.mapper;


import com.ssafy.SNS201.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<Comment> selectComment(int postNo);
    public boolean insertComment(Comment comment);
    public boolean updateComment(Comment comment);
    public boolean deleteComment(int commentNo);
}
