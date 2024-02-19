package com.RSstudies.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandller {
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<String> NoDataFoundException(NoDataFoundException ndE) {
		return new ResponseEntity<String>(ndE.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> NoHandlerFound(NoHandlerFoundException nhE) {
		return new ResponseEntity<String>("There is no such Endpoint", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodeArgumentNotValide(MethodArgumentNotValidException mnE) {
		return new ResponseEntity<String>(mnE.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> GeneralException(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}