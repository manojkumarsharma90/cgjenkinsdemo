package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class CgValidationException extends RuntimeException {

	private List<FieldError> errors;
	
	public CgValidationException(String message) {
		super(message);
		
	}

	public CgValidationException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	
	
	

}
