package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Path Parameter Not Found") //404
public class InvalidPathParameterException extends Exception {

	public InvalidPathParameterException(String type, String field) {
		super("InvalidPathParameterException type="+type+", field="+field);
	}
	
}
