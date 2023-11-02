package com.kosta.bank.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.dto.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate setSqlSession() {
		return sqlSession;
	}
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertAccount(Account acc) throws Exception {
		sqlSession.insert("mapper.account.insertAccount", acc);
	}

	@Override
	public Account selectAccount(String id) throws Exception {
		return sqlSession.selectOne("mapper.account.selectAccount", id);
	}

	@Override
	public void updateAccountBalance(Account acc) throws Exception {
		sqlSession.update("mapper.account.updateBalance", acc);
	}

	@Override
	public List<Account> selectAccountList() throws Exception {
		return sqlSession.selectList("mapper.account.selectAccountList");
	}

}
