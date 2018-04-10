package com.square.bank.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.square.bank.repository.AccountRepository;
import com.square.bank.services.AccountService;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceMockTest {

	@Mock
	AccountRepository accountRepositoryMock; 
	
	@InjectMocks
	AccountService accountService; 

//	@Test
//	public void generateAccountNumberMockTest() {
//		when(accountService.generateAccountNumber()).thenReturn((long) 10000);
//		assertEquals((long)10000, accountService.generateAccountNumber());
//	}
}
