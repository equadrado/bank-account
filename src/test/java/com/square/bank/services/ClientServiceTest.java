package com.square.bank.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.square.bank.dto.ClientDTO;
import com.square.bank.exception.ClientNotFoundException;
import com.square.bank.exception.MandatoryFieldNotProvidedException;
import com.square.bank.exception.NewObjectCantBeNullException;
import com.square.bank.exception.ObjectAlreadyExistsException;
import com.square.bank.model.Client;
import com.square.bank.repository.ClientRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
	static Client cli1, cli2;
	static ClientDTO cliDTO1;
	static Optional<Client> optCli1;

	static {
		cli1 = new Client();
		cli1.setId((long) 1);
		cli1.setName("Client1");
		
		cli2 = new Client();
		cli1.setId((long) 2);
		cli1.setName("Client2");
		
		cliDTO1 = new ClientDTO();
		cliDTO1.setId((long) 1);
		cliDTO1.setName("");

		optCli1 = Optional.of(new Client());
		optCli1.get().setId((long) 1);
		optCli1.get().setName("Client1");
		
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
		assertEquals("Must return all clients", mockClientList, clientService.findAll());
	}

	@Test
	public void testFindById() throws ClientNotFoundException {
		when(clientRepositoryMock.findById((long) 1)).thenReturn(optCli1);
		assertEquals("Must return client ID 1", optCli1, clientService.findById((long) 1));
	}

	@Test(expected = MandatoryFieldNotProvidedException.class)
	public void testMandatoryFieldNotProvidedExceptionIsThrown() throws ObjectAlreadyExistsException, NewObjectCantBeNullException, MandatoryFieldNotProvidedException {
		clientService.createClient(cliDTO1);
	}

	@Test
	public void testCreateClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccountsByClientId() {
		fail("Not yet implemented");
	}

}
