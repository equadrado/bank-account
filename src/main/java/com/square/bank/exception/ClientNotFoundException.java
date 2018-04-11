package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Client Not Found") //404
public class ClientNotFoundException extends Exception {

	public ClientNotFoundException(Long id) {
		super("ClientNotFoundException with id="+id);
	}
}
