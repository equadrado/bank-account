package com.square.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Mandatory field not provided")
public class MandatoryFieldNotProvidedException extends Exception {

	public MandatoryFieldNotProvidedException(String type, String field) {
		super("MandatoryFieldNotProvidedException type="+type+", field="+field);
	}

}
