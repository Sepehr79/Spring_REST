package com.luv2code.springdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luv2code.springdemo.controller.exception.CustomerNotFoundException;
import com.luv2code.springdemo.controller.pojo.IllegalArgObject;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler
	public ResponseEntity<IllegalArgObject> getResponseEntity(IllegalArgumentException exception){
		
		IllegalArgObject object = new IllegalArgObject();
		object.setStatus(HttpStatus.BAD_REQUEST.value());
		object.setMessage(exception.getMessage());
		object.setTime(System.currentTimeMillis());
		
		return new ResponseEntity<IllegalArgObject>(object, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<IllegalArgObject> getResponseEntity(CustomerNotFoundException exception){
		IllegalArgObject object = new IllegalArgObject();
		object.setStatus(HttpStatus.NOT_FOUND.value());
		object.setMessage(exception.getMessage());
		object.setTime(System.currentTimeMillis());
		return new ResponseEntity<IllegalArgObject>(object, HttpStatus.NOT_FOUND);
	}
	
	
	
}
