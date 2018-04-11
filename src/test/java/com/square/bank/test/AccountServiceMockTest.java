package com.square.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.square.bank.dto.AccountDTO;
import com.square.bank.exception.NewObjectCantBeNullException;
import com.square.bank.exception.ObjectAlreadyExistsException;
import com.square.bank.model.Account;
import com.square.bank.repository.AccountRepository;
import com.square.bank.services.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceMockTest {
	private AccountDTO accountDTO;
	private Account account;
			
	public AccountServiceMockTest() {		
		accountDTO = new AccountDTO();
		accountDTO.setBalance(0);
		accountDTO.setId((long)2);
		accountDTO.setNumber((long)20000);
		
//		Client cli1, cli2;
//		cli1 = ClientServiceMockTest.cli1;
//		cli2 = ClientServiceMockTest.cli2;
		account = new Account();
		account.setBalance(0);
		account.setId((long)2);
		account.setNumber((long)20000);
//		account.setClients(new ArrayList<Client>(Arrays.asList(cli1, cli2)));

	}
			
	@Mock
	AccountRepository accountRepositoryMock; 
	
	@InjectMocks
	AccountService accountService; 
	
	@Test
	public void createAccountMockTest() throws ObjectAlreadyExistsException, NewObjectCantBeNullException {
		when(accountService.createAccount(accountDTO)).thenReturn(account);
		assertTrue("Error, account number bellow 1000", account.getNumber() >= 1000);
		assertTrue("Error, account number over 100000", account.getNumber() <= 100000);
	}
}
