package com.virtualWallet.VirualWallet.ErrorHandling;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class Exceptions{
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<?> handleNumberFormatException(NumberFormatException e) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("Incorrect Input Format", "BAD_REQUEST"),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<?> handleInterruptedException(InterruptedException e) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("CANNOT PROCESS REQUEST", "REQUEST_TIMEOUT"),
				HttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleElementNotFound(NoSuchElementException e) {
		return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("Wallet/Account Not Found", "NOT_FOUND"),
				HttpStatus.NOT_FOUND);
	}

}
