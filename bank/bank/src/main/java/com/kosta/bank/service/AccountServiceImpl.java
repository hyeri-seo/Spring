package com.kosta.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	
	public AccountDao getAccountDao() {
		return accountDao;
	}



	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}



	@Override
	public void makeAccount(Account acc) throws Exception {
		accountDao.insertAccount(acc);
	}



	@Override
	public Account accountInfo(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc==null) throw new Exception("계좌번호 오류");
		return acc;
	}



	@Override
	public void deposit(String id, Integer money) throws Exception {
		Account acc = accountInfo(id);
		acc.deposit(money);
		accountDao.updateAccountBalance(acc);
	}



	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Account acc = accountInfo(id);
		acc.withdraw(money);
		accountDao.updateAccountBalance(acc);
	}



	@Override
	public List<Account> allAccountInfo() throws Exception {
		return accountDao.selectAccountList();
	}

}
