package com.cg.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.cg.dto.MovieDto;
import com.cg.entity.Movie;
import com.cg.repo.MovieRepo;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepo movieRepo;
	
	public LocalDate validateReleaseDate(List<FieldError> errList,String dt) {
		LocalDate ldt=null;
		
		try {
			
			 ldt=LocalDate.parse(dt,DateTimeFormatter.ofPattern("yyyy-M-d"));
			
			 if(!ldt.isAfter(LocalDate.now())||!ldt.isEqual(LocalDate.now())) {
				 
					FieldError err=new FieldError("releaseDate","releaseDate","Release Date must be current or future date");
					errList.add(err);
			 }
			 
		}
		catch(DateTimeParseException ex) {
			
			FieldError err=new FieldError("releaseDate","releaseDate","Release Date must be in yyyy-MM-dd");
			errList.add(err);
		}
		
		return ldt;
	}
	
	@Override
	public Integer addMovie(MovieDto dto,LocalDate releaseDate) {
		
		Movie movie=new Movie();
		movie.setMovieTitle(dto.getMovieTitle());
		movie.setCast(dto.getMovieCast());
		movie.setzenre(dto.getZenre());
		movie.setReleaseDate(releaseDate);
		
		Movie savedMovie=movieRepo.save(movie);
		
		return savedMovie.getMovieId();
		
	
	}

}
