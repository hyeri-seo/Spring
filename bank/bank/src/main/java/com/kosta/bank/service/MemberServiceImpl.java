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
		if(member==null) throw new Exception("���̵� ����");
		if(!member.getPassword().equals(password)) throw new Exception("��й�ȣ ����");
		return member;
	}

	@Override
	public void join(Member member) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//	������̼� �̿��ϸ� �̰� �ʿ䰡 ����
//	public MemberDao getMemberDao() {
//		return memberDao;
//	}
//
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
	
	
}
