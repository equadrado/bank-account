package com.square.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.square.bank.dto.AccountDTO;
import com.square.bank.dto.ClientDTO;
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
	
	@PostMapping("")
	public Account createAccount(@RequestBody() AccountDTO account) {
		return accountService.createAccount(account);
	}
	
	@PutMapping("")
	public Account updateAccount(@RequestBody() AccountDTO account) {
		return accountService.updateAccount(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable(name="id") int id) {
		if (id > 0) {
			accountService.deleteAccount(id);
		} else {
			throw new InvalidPathParameterException();
		}
	}
	
	@PutMapping("/{id}/clients")
	public Account addClients(@PathVariable(name="id") int id, @RequestBody() ClientDTO client) {
		return accountService.addClient(id, client);
	}
	
}
