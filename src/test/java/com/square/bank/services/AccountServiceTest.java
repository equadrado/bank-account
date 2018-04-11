package com.square.bank.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	private AccountDTO accountDTO, accountDTOWO;
	private Account account, accountWO;

	public AccountServiceTest() {		
		accountDTO = new AccountDTO();
		accountDTO.setBalance(0);
		accountDTO.setId((long)2);
		accountDTO.setNumber((long)20000);
		
		accountDTOWO = new AccountDTO();
		accountDTOWO.setBalance(0);
		accountDTOWO.setId((long)2);
		accountDTOWO.setNumber(null);
		
//		Client cli1, cli2;
//		cli1 = ClientServiceMockTest.cli1;
//		cli2 = ClientServiceMockTest.cli2;
		account = new Account();
		account.setBalance(0);
		account.setId((long)2);
		account.setNumber((long)20000);
//		account.setClients(new ArrayList<Client>(Arrays.asList(cli1, cli2)));

		accountWO = new Account();
		accountWO.setBalance(0);
		accountWO.setId((long)2);
		accountWO.setNumber(null);
	}
			
	@Mock
	AccountRepository accountRepositoryMock; 
	
	@InjectMocks
	AccountService accountService; 

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAccountWithAccountNumber() throws ObjectAlreadyExistsException, NewObjectCantBeNullException {
		when(accountService.createAccount(accountDTO)).thenReturn(account);
		assertTrue("Error, account number bellow 1000", account.getNumber() >= 1000);
		assertTrue("Error, account number over 100000", account.getNumber() <= 100000);
	}

	@Test
	public void testCreateAccountWithoutAccountNumber() throws ObjectAlreadyExistsException, NewObjectCantBeNullException {
		when(accountService.createAccount(accountDTOWO)).thenReturn(accountWO);
		assertTrue("Error, account number bellow 1000", accountWO.getNumber() >= 1000);
		assertTrue("Error, account number over 100000", accountWO.getNumber() <= 100000);
	}

	@Test
	public void testUpdateAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveClient() {
		fail("Not yet implemented");
	}

}
