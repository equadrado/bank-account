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
import com.square.bank.exception.AccountNotFoundException;
import com.square.bank.exception.ClientNotFoundException;
import com.square.bank.exception.InvalidPathParameterException;
import com.square.bank.exception.MandatoryFieldNotProvidedException;
import com.square.bank.exception.NewObjectCantBeNullException;
import com.square.bank.exception.ObjectAlreadyExistsException;
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
	public Optional<Account> getAccountById(@PathVariable(name="id") Long id) 
			throws AccountNotFoundException, InvalidPathParameterException {
		if (id != 0) {
			return accountService.findById(id);
		} else {
			throw new InvalidPathParameterException("Account", "Id");
		}
	}
	
	@PostMapping("")
	public Account createAccount(@RequestBody() AccountDTO account) 
			throws ObjectAlreadyExistsException, NewObjectCantBeNullException {
		return accountService.createAccount(account);
	}
	
	@PutMapping("")
	public Account updateAccount(@RequestBody() AccountDTO account) 
			throws AccountNotFoundException, MandatoryFieldNotProvidedException {
		return accountService.updateAccount(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable(name="id") Long id) 
			throws AccountNotFoundException, InvalidPathParameterException {
		if (id > 0) {
			accountService.deleteAccount(id);
		} else {
			throw new InvalidPathParameterException("Account", "Id");
		}
	}
	
	@PutMapping("/{id}/clients")
	public Account addClients(@PathVariable(name="id") Long id, @RequestBody() ClientDTO client) 
			throws ObjectAlreadyExistsException, ClientNotFoundException, AccountNotFoundException {
		return accountService.addClient(id, client);
	}
	
}
