package com.kosta.board.dao;

import java.lang.reflect.Member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
	
   @Autowired
   private SqlSessionTemplate sqlSession;
   
   public SqlSessionTemplate getSqlSession() {
      return sqlSession;
   }

   public void setSqlSession(SqlSessionTemplate sqlSession) {
      this.sqlSession = sqlSession;
   }

   @Override
   public Member selectMember(String id) throws Exception {
      // TODO Auto-generated method stub
      return sqlSession.selectOne("mapper.member.selectMember",id);
   }

   @Override
   public void insertMember(Member member) throws Exception {
      sqlSession.selectOne("mapper.member.insertMember",member);

   }

}