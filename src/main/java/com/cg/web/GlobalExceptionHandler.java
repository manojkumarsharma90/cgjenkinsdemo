package com.cg.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.ErrorMessageDto;
import com.cg.exceptions.CgValidationException;
import com.cg.exceptions.NotAvaliableException;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageDto handleLocalDate(HttpMessageNotReadableException ex) {
		String msg=null;
		if(ex.getMessage().contains("LocalDate")) {
			
			msg="Enter the date in yyyy-MM-dd format"; 
		}
		
		ErrorMessageDto dto=new ErrorMessageDto();
		dto.setErrormsg(msg);
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatus(HttpStatus.BAD_REQUEST.toString());
		return dto;
	}
	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleValidation(CgValidationException ex) {
		Map<String,List<String>> errMap=new HashMap<>();
		
		List<FieldError> err=ex.getErrors();
		
		for(FieldError fr:err) {
			if(errMap.containsKey(fr.getField())) {
				
				List<String> lst=errMap.get(fr.getField());
				lst.add(fr.getDefaultMessage());
				errMap.put(fr.getField(), lst);
			}
			
			else {
				List<String> lst=new ArrayList<>();
				lst.add(fr.getDefaultMessage());
				errMap.put(fr.getField(), lst);
				
			}
		}
		
		ErrorMessageDto dto=new ErrorMessageDto();
		dto.setErrormsg("validation failed");
		dto.setErrMap(errMap);
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatus(HttpStatus.NOT_FOUND.toString());
		return dto;
		
		
		
		
	}
	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageDto handleNotFound(NotAvaliableException ex) {
		ErrorMessageDto dto=new ErrorMessageDto();
		dto.setErrormsg(ex.getMessage());
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatus(HttpStatus.NOT_FOUND.toString());
		return dto;
	}
	

}
