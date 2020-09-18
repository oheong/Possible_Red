package com.ssafy.SNS201.service;

import com.ssafy.SNS201.dto.Hashtag;
import com.ssafy.SNS201.dto.Member;

import java.util.List;

public interface MemberService {
    public List<Member> findAllMembers();
    public Member findMemberByNo(int memberNo);
    public Member findMemberByEmail(String Email);
    public boolean addMember(Member member);
    public boolean modifyMember(Member member);
    public boolean removeMember(int memberNo);
    public List<Member> findAllMembersBySearch(String keyword);
    public boolean modifyMemberPoint(int memberNo, int point);
    public boolean modifyPassword(Member member);
}
