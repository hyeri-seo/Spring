package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public Member login(String id, String password) throws Exception {
		Member member = memberDao.selectMember(id);
		if(member==null) throw new Exception("아이디 오류");
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member;
	}

	@Override
	public void join(Member member) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//	어노테이션 이용하면 이게 필요가 없음
//	public MemberDao getMemberDao() {
//		return memberDao;
//	}
//
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
	
	
}
