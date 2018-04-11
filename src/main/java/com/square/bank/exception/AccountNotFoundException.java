package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Account Not Found") //404
public class AccountNotFoundException extends Exception {

	public AccountNotFoundException(Long id) {
		super("AccountNotFoundException with id="+id);
	}

}
