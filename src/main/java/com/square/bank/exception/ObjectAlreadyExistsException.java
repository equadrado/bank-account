package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Object Already Exists")
public class ObjectAlreadyExistsException extends Exception {

	public ObjectAlreadyExistsException(String type, String value) {
		super("ObjectAlreadyExistsException type="+type+", value="+value);
	}

}
