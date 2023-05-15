package com.te.deliverysystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.deliverysystem.exception.PartnerNameNotFoundException;
import com.te.deliverysystem.exception.UserNotValidException;

@RestControllerAdvice
public class DeliveryExceptionController {

	@ExceptionHandler(PartnerNameNotFoundException.class)
	public ResponseEntity<?> partnerExceptionHandling(PartnerNameNotFoundException notFoundException) {
		return new ResponseEntity<>(notFoundException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotValidException.class)
	public ResponseEntity<?> userExceptionHandling(UserNotValidException notFound) {
		return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
	}
}
