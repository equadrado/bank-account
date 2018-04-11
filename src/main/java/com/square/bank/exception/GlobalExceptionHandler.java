package com.square.bank.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.square.bank.dto.ExceptionJsonDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured") //returning 404 error code
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException handler executed");	
	}

	private ExceptionJsonDTO createJSONMessage(HttpServletRequest request, Exception ex) {
		logger.error(ex.getMessage());	
		
		ExceptionJsonDTO response = new ExceptionJsonDTO();
		response.setUrl(request.getRequestURL().toString());
		response.setMessage(ex.getMessage());

		return response;
	}
	
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public @ResponseBody ExceptionJsonDTO handleObjectAlreadyExistsException(HttpServletRequest request, Exception ex){		
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(AccountMustHaveOneClientException.class)
	public @ResponseBody ExceptionJsonDTO handleAccountMustHaveOneClientException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public @ResponseBody ExceptionJsonDTO handleAccountNotFoundException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public @ResponseBody ExceptionJsonDTO handleClientNotFoundException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(InvalidPathParameterException.class)
	public @ResponseBody ExceptionJsonDTO handleInvalidPathParameterException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(MandatoryFieldNotProvidedException.class)
	public @ResponseBody ExceptionJsonDTO handleMandatoryFieldNotProvidedException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
	@ExceptionHandler(NewObjectCantBeNullException.class)
	public @ResponseBody ExceptionJsonDTO handleNewObjectCantBeNullException(HttpServletRequest request, Exception ex){
		return createJSONMessage(request, ex);
	}
	
}
