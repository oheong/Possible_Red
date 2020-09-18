package com.ssafy.SNS201.service;

import com.ssafy.SNS201.controller.PostAPIController;
import com.ssafy.SNS201.dto.Post;
import com.ssafy.SNS201.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private static final Logger logger = LoggerFactory.getLogger(PostAPIController.class);

    @Autowired
    PostMapper mapper;

    @Override
    public List<Post> findAllPosts() { return mapper.selectPost(); }

    @Override
    public boolean addPost(Post p) { return mapper.insertPost(p); }

    @Override
    public Post findPostByNo(int postNo) {
        return mapper.selectPostByNo(postNo);
    }

    @Override
    public boolean modifyPost(Post p) {
        return mapper.updatePost(p);
    }

    @Override
    public boolean removePost(int postNo) {
        return mapper.deletePost(postNo);
    }

    @Override
    public List<Post> findPostByMemberNo(int memberNo) {
        return mapper.selectPostByMemberNo(memberNo);
    }

    @Override
    public List<Post> findPostByMissionNo(int missionNo) {
        return mapper.selectPostByMissionNo(missionNo);
    }

    @Override
    public List<Post> findAllPostsBySearch(String keyword) {
        return mapper.selectPostBySearch(keyword);
    }

    @Override
    public int findMaxPostNo() {
        return mapper.selectMaxPostNo();
    }
}
