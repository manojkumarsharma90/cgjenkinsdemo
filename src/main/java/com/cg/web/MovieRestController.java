package com.cg.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.MovieDto;
import com.cg.entity.Movie;
import com.cg.exceptions.CgValidationException;
import com.cg.repo.MovieRepo;
import com.cg.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")

public class MovieRestController {

	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private MovieService movieService;
	

	@GetMapping("/home")
	@Operation(summary="welcome page",tags="Home")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<String>("Hello welcome",HttpStatus.OK);
	}

	
	@Operation(description="Get all movies return array of json",tags="movie mgmt",summary="get all movies")
	@GetMapping("/viewall")
	public ResponseEntity<List<Movie>> viewAllMovies() {

		List<Movie> movies = movieRepo.findAll();

		return new ResponseEntity<>(movies, HttpStatus.OK);

	}
     
	@Operation(description="Get movie by id",tags="movie mgmt")
	@GetMapping("/viewbyid/{mid}")
	public ResponseEntity<Object> viewMovieByID(@PathVariable("mid") Integer movieId) {

		Optional<Movie> optMovie = movieRepo.findById(movieId);

		if (optMovie.isPresent()) {

			return new ResponseEntity<Object>(optMovie.get(), HttpStatus.OK);
		}

		else {
			return new ResponseEntity<Object>("Movie not found", HttpStatus.NOT_FOUND);
		}

	}

	@Operation(description="view all movies by title",tags="movie mgmt")
	@GetMapping("/viewbytitle")
	public ResponseEntity<Object> viewMovieByTitle(@RequestParam("title") String title) {

		List<Movie> lst = movieRepo.findByMovieTitle(title);

		return new ResponseEntity<>(lst, HttpStatus.OK);

	}
	
	
	@Operation(description="Get movie for the given year",tags="movie mgmt")
	
	@GetMapping("/viewbyyear/{year}")
	public ResponseEntity<Object> viewMovieByTitle(@PathVariable("year") int year) {

		List<Movie> lst = movieRepo.getMovieByGivenYear(year);

		return new ResponseEntity<>(lst, HttpStatus.OK);

	}

	@Operation(description="Add movie",tags="movie mgmt")
	@PostMapping("/addmovie")
	public ResponseEntity<String> addMovie(@Valid @RequestBody MovieDto movie,BindingResult br) {
		
		List<FieldError> errList=new ArrayList<>();
		
		if(br.hasErrors()) {
			errList.addAll(br.getFieldErrors());
		}
		
		LocalDate ldt=movieService.validateReleaseDate(errList, movie.getReleaseDate());
		
		if(!errList.isEmpty()) {
			throw new CgValidationException(errList);
		}
		
		int id=movieService.addMovie(movie, null);

		
		return new ResponseEntity<String>("Movie added and Id is "+id, HttpStatus.CREATED);

	}

}
