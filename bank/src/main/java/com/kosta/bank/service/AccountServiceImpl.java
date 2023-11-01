package com.kosta.bank.service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;

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

}
