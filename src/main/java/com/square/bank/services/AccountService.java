package com.square.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.square.bank.exception.AccountNotFoundException;
import com.square.bank.model.Account;
import com.square.bank.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	public Optional<Account> findById(int id) {
		if (id != 0) {
			return accountRepository.findById(id);
		} else {
			throw new AccountNotFoundException();
		}
	}
}
