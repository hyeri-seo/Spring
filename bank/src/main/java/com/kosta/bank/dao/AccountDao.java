package com.kosta.bank.dao;

import java.util.List;
import java.util.Map;

import com.kosta.bank.dto.Account;

public interface AccountDao {
	void insertAccount(Account acc) throws Exception;
	Account selectAccount(String id) throws Exception;
	void updateAccountBalance(Map<String,Object> param) throws Exception;
	List<Account> selectAccountList() throws Exception;
}
