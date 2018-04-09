package com.square.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.square.bank.exception.InvalidPathParameterException;
import com.square.bank.model.Account;
import com.square.bank.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("")
	public List<Account> getAll() {
		return accountService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Account> getAccountById(@PathVariable(name="id") int id) {
		if (id != 0) {
			return accountService.findById(id);
		} else {
			throw new InvalidPathParameterException();
		}
	}
	
}
