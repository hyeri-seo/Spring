package com.kosta.board.dao;

import java.lang.reflect.Member;

public interface MemberDao {
   Member selectMember(String id) throws Exception;
   void insertMember(Member member) throws Exception;
}