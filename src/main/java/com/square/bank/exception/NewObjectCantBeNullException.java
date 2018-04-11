package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="New Object Can't Be Null")
public class NewObjectCantBeNullException extends Exception {

	public NewObjectCantBeNullException(String type) {
		super("NewObjectCantBeNullException type="+type);
	}

}
