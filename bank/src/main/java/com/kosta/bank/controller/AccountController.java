package com.kosta.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	@RequestMapping(value="/makeaccount", method=RequestMethod.GET)
	public String makeAccount() {
		return "makeaccount";
	}
	
	@RequestMapping(value="/makeaccount", method=RequestMethod.POST)
	public String makeAccount(@ModelAttribute Account acc, Model model) {
		try {
			accountService.makeAccount(acc);
			model.addAttribute("acc", acc);		
			return "accountinfo";
		} catch(Exception e) {
			e.printStackTrace();
			return "err";
		}
	}
	
	@RequestMapping(value="/deposit", method=RequestMethod.GET)
	public String deposit() {
		return "deposit";
	}
	
	@RequestMapping(value="/withdraw", method=RequestMethod.GET)
	public String withdraw() {
		return "withdraw";
	}
	
	@RequestMapping(value="/accountinfo", method=RequestMethod.GET)
	public String accountinfo() {
		return "accountinfo";
	}
	
	@RequestMapping(value="/allaccountinfo", method=RequestMethod.GET)
	public String allaccountinfo() {
		return "allaccountinfo";
	}
}
