package com.square.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.square.bank.dto.ClientDTO;
import com.square.bank.exception.ObjectAlreadyExistsException;
import com.square.bank.exception.ClientNotFoundException;
import com.square.bank.exception.MandatoryFieldNotProvidedException;
import com.square.bank.exception.NewObjectCantBeNullException;
import com.square.bank.model.Account;
import com.square.bank.model.Client;
import com.square.bank.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Optional<Client> findById(Long id) throws ClientNotFoundException {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			return client;
		} else {
			throw new ClientNotFoundException(id);
		}
	}
	
//	@Transactional
	public Client createClient(ClientDTO client) throws ObjectAlreadyExistsException, NewObjectCantBeNullException, MandatoryFieldNotProvidedException {
		if (client.getId() != null) { // primitive type can't be null, zero is the default "Long" value
			Optional<Client> oldClient = clientRepository.findById(client.getId());
			if (oldClient.isPresent()) {
				throw new ObjectAlreadyExistsException("Client", client.getId().toString());
			}
		}
		if (client.equals(null))  {
			throw new NewObjectCantBeNullException("Client");
		} else if (client.getName().isEmpty() ) {
			throw new MandatoryFieldNotProvidedException("Client", "Name");
		} else {
			return clientRepository.save(ClientDTO.bind(client));
		}
	}
	
//	@Transactional
	public Client updateClient(ClientDTO client) throws ClientNotFoundException, MandatoryFieldNotProvidedException {
		Optional<Client> oldClient = clientRepository.findById(client.getId());
		if (!oldClient.isPresent()) {
			throw new ClientNotFoundException(client.getId());
		} else if (client.getName().equals("")) {
			throw new MandatoryFieldNotProvidedException("Client", "Name");
		} else {
			return clientRepository.save(ClientDTO.bind(client));
		}
	}
	
//	@Transactional
	public void deleteClient(Long id) throws ClientNotFoundException {
		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new ClientNotFoundException(id);
		} else {
			clientRepository.delete(client.get());
		}
	}
	
	public List<Account> getAccountsByClientId(Long id) throws ClientNotFoundException{
		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new ClientNotFoundException(id);
		} else {
			return client.get().getAccounts();
		}
	}
}
