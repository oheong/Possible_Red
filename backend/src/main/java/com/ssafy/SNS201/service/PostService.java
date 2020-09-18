package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Hashtag;
import com.ssafy.SNS201.dto.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> findAllPosts();
    boolean addPost(Post p);
    Post findPostByNo(int postNo);
    boolean modifyPost(Post p);
    boolean removePost(int postNo);
    List<Post> findPostByMemberNo(int memberNo);
    List<Post> findPostByMissionNo(int missionNo);
    public List<Post> findAllPostsBySearch(String keyword);
    public int findMaxPostNo();
}
