package com.kosta.bank.dao;

import java.util.List;

import com.kosta.bank.dto.Account;

public interface AccountDao {
	void insertAccount(Account acc) throws Exception;
	Account selectAccount(String id) throws Exception;
	void updateAccountBalance(Account acc) throws Exception;
	List<Account> selectAccountList() throws Exception;
}
