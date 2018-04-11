package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Account must have at least one client")
public class AccountMustHaveOneClientException extends Exception {

	public AccountMustHaveOneClientException() {
		super("AccountMustHaveOneClientException");
	}

}
