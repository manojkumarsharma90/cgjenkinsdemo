package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.FieldError;

import com.cg.dto.MovieDto;

public interface MovieService {
	
	public LocalDate validateReleaseDate(List<FieldError> errList,String dt);
	
	public Integer addMovie(MovieDto dto,LocalDate releaseDate);
		
	

}
