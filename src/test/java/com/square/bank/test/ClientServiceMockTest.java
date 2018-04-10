package com.square.bank.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.square.bank.model.Client;
import com.square.bank.repository.ClientRepository;
import com.square.bank.services.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceMockTest {
	private Client cli1, cli2;
	
	public ClientServiceMockTest() {
		cli1 = new Client();
		cli1.setId(1);
		cli1.setName("Client1");
		
		cli2 = new Client();
		cli1.setId(2);
		cli1.setName("Client2");
	}

	@Mock
	ClientRepository clientRepositoryMock; 
	
	@InjectMocks
	ClientService clientService; 

	@Test
	public void testFindAll() {
		List<Client> mockClientList = new ArrayList<Client>();
		
		mockClientList.add(cli1);		
		mockClientList.add(cli2);
		
		when(clientRepositoryMock.findAll()).thenReturn(mockClientList);
		assertEquals(mockClientList, clientService.findAll());
	}

}
