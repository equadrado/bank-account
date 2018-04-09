package com.square.bank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.square.bank.dto.ClientDTO;
import com.square.bank.exception.ClientAlreadyExistsException;
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
	
	public Optional<Client> findById(int id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			return client;
		} else {
			throw new ClientNotFoundException();
		}
	}
	
	@Transactional
	public Client newClient(ClientDTO client) {
		if (client.getId() != 0) { // primitive type can't be null, zero is the default "int" value
			Optional<Client> oldClient = clientRepository.findById(client.getId());
			if (oldClient.isPresent()) {
				throw new ClientAlreadyExistsException();
			}
		}
		if ( (client != null) && (!client.getName().equals("")) ) {
			return clientRepository.save(ClientDTO.bind(client));
		} else {
			throw new NewObjectCantBeNullException();
		}
	}
	
	@Transactional
	public Client updateClient(ClientDTO client) {
		Optional<Client> oldClient = clientRepository.findById(client.getId());
		if (!oldClient.isPresent()) {
			throw new ClientNotFoundException();
		} else if (client.getName().equals("")) {
			throw new MandatoryFieldNotProvidedException();
		} else {
			return clientRepository.save(ClientDTO.bind(client));
		}
	}
	
	@Transactional
	public void deleteClient(int id) {
		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new ClientNotFoundException();
		} else {
			clientRepository.delete(client.get());
		}
	}
	
	public List<Account> getAccountsByClientId(int id){
		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()) {
			throw new ClientNotFoundException();
		} else {
			return client.get().getAccounts();
		}
	}
}
