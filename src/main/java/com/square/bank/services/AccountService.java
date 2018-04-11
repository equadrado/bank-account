package com.square.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.square.bank.dto.AccountDTO;
import com.square.bank.dto.ClientDTO;
import com.square.bank.exception.AccountMustHaveOneClientException;
import com.square.bank.exception.AccountNotFoundException;
import com.square.bank.exception.ClientNotFoundException;
import com.square.bank.exception.MandatoryFieldNotProvidedException;
import com.square.bank.exception.ObjectAlreadyExistsException;
import com.square.bank.exception.NewObjectCantBeNullException;
import com.square.bank.model.Account;
import com.square.bank.model.Client;
import com.square.bank.repository.AccountRepository;
import com.square.bank.repository.ClientRepository;
import com.square.bank.specification.AccountSpecification;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ClientService clientService;
	
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	public Optional<Account> findById(Long id) throws AccountNotFoundException {
		if (id != 0) {
			return accountRepository.findById(id);
		} else {
			throw new AccountNotFoundException(id);
		}
	}
	
	@Transactional
	public Account createAccount(AccountDTO accountDTO) 
			throws ObjectAlreadyExistsException, NewObjectCantBeNullException{
		if (accountDTO.getId() != null) { // primitive type can't be null, zero is the default "Long" value
			Optional<Account> oldAccount = accountRepository.findById(accountDTO.getId());
			if (oldAccount.isPresent()) {
				throw new ObjectAlreadyExistsException("Account", oldAccount.get().getNumber().toString());
			}
		}
		if (accountDTO.getNumber() != 0) { // check if account number already exists
//			List<Account> oldAccount = accountRepository.findAll(AccountSpecification.equalNumber(account.getNumber()));
//			if (!oldAccount.isEmpty()) {
			Account oldAccount = accountRepository.findByNumber(accountDTO.getNumber());
			if (oldAccount != null) {
				throw new ObjectAlreadyExistsException("Account", oldAccount.getNumber().toString());
			}
		} else {
			accountDTO.setNumber(generateAccountNumber());
		}
		if ( (accountDTO != null) && (accountDTO.getNumber() != 0) ) {
			return accountRepository.save(AccountDTO.bind(accountDTO));
		} else {
			throw new NewObjectCantBeNullException("Account");
		}
	}
	
	private Long generateAccountNumber() { // private
		long accountNr = 0;
		long counter = 0;

		Random r = new Random();
		int Low = 1000;
		int High = 100000;
		boolean found = true;
		while (found) {
			counter++;
			accountNr = r.nextInt(High-Low) + Low;
//			List<Account> oldAccount = accountRepository.findAll(AccountSpecification.equalNumber(accountNr));
//			if (oldAccount.isEmpty()) {
			Account oldAccount = accountRepository.findByNumber(accountNr);
			if (oldAccount.equals(null)) {
				found = false;
			} else if (counter > 200000) { // too many tries
				accountNr = 0;
				found = false;
			}
		}
		return (Long)accountNr;
	}
	
	@Transactional
	public Account updateAccount(AccountDTO account) throws AccountNotFoundException, MandatoryFieldNotProvidedException {
		Optional<Account> oldAccount;
		if ( (account != null) && (account.getId() != 0)) { 
			oldAccount = accountRepository.findById(account.getId());
			if (!oldAccount.isPresent()) {
				throw new AccountNotFoundException(account.getId());
			}
		}
		if (account.getNumber() != 0) { // check if account number is valid
			throw new MandatoryFieldNotProvidedException("Account", "Number");
		} else {
			return accountRepository.save(AccountDTO.bind(account));
		}
	}
	
	@Transactional
	public void deleteAccount(Long id) throws AccountNotFoundException {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			accountRepository.delete(account.get());
		} else {
			throw new AccountNotFoundException(id);
		}
	}
	
	@Transactional
	public Account addClient(Long id, ClientDTO dto) throws ObjectAlreadyExistsException, ClientNotFoundException, AccountNotFoundException {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			// search for this client
			Optional<Client> client = clientService.findById(dto.getId());
			if (client.isPresent()) {
				// get this account
				Account oldAccount = account.get();
				// get actual client list from this account
				List<Client> actualClients = oldAccount.getClients();
				// check if this client is already registered to this account
				if (!actualClients.contains(client.get())) {
					// add the new client
					actualClients.add(client.get());
					// update the client list
					oldAccount.setClients(actualClients);
					// save the account changed
//					accountRepository.save(oldAccount);  // need save the account ?
					return oldAccount;
				} else { // this client is already registered to this account
					throw new ObjectAlreadyExistsException("Client/Account", client.get().getId().toString());
				}
			} else { // the client was not found
				throw new ClientNotFoundException(dto.getId());
			}
		} else { // the account was not found
			throw new AccountNotFoundException(id);
		}
	}
	
	@Transactional
	public Account removeClient(Long id, ClientDTO dto) throws AccountMustHaveOneClientException, ClientNotFoundException, AccountNotFoundException {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			// search for this client
			Optional<Client> client = clientService.findById(dto.getId());
			if (client.isPresent()) {
				// get this account
				Account oldAccount = account.get();
				// get actual client list from this account
				List<Client> actualClients = oldAccount.getClients();
				if (actualClients.size() == 1) {
					throw new AccountMustHaveOneClientException();
				} else if (actualClients.contains(client.get())) { // check if this client is already registered to this account
					// add the new client
					actualClients.remove(client.get());
					// update the client list
					oldAccount.setClients(actualClients);
					// save the account changed
//					accountRepository.save(oldAccount); // need save the account ?
					return oldAccount;
				} else { // this client is already registered to this account
					throw new ClientNotFoundException(client.get().getId());
				}
			} else { // the client was not found
				throw new ClientNotFoundException(dto.getId());
			}
		} else { // the account was not found
			throw new AccountNotFoundException(id);
		}
	}
}
