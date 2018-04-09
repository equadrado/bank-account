package com.square.bank.exception;

//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
public class RestResponseEntityExceptionHandler extends RuntimeException {

	public void LogError(Exception e) {
		System.out.print(e.getMessage());
	}
	
}
