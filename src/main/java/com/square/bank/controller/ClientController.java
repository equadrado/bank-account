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

import com.square.bank.dto.ClientDTO;
import com.square.bank.exception.InvalidPathParameterException;
import com.square.bank.model.Account;
import com.square.bank.model.Client;
import com.square.bank.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("")
	public List<Client> getAll() {
		return clientService.findAll();
	}
	
	@GetMapping("/{id}") 
	public Optional<Client> getClientById(@PathVariable(name="id") int id) {
		if (id > 0) {
			return clientService.findById(id);
		} else {
			throw new InvalidPathParameterException();
		}
	}
	
	@PutMapping("")
	public Client newClient(@RequestBody() ClientDTO client) {
		return clientService.newClient(client);
	}
	
	@PostMapping("")
	public Client updateClient(@RequestBody() ClientDTO client) {
		return clientService.updateClient(client);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable(name="id") int id) {
		if (id > 0) {
			clientService.deleteClient(id);
		} else {
			throw new InvalidPathParameterException();
		}
	}

	@GetMapping("/{id}/accounts") 
	public List<Account> getAccountsByClientId(@PathVariable(name="id") int id) {
		if (id > 0) {
			return clientService.getAccountsByClientId(id);
		} else {
			throw new InvalidPathParameterException();
		}
	}
}
