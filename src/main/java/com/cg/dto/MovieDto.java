package com.cg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MovieDto {
	
	@NotBlank(message="Movie Title is mandatory")
	@Size(min=3 ,max=45,message="Movietitle must between 3 and 45 chars")
	@Pattern(regexp="^[a-zA-Z]+( [a-zA-Z]+)*$", 
    message="Movie title contains only alphabets and space")
	private String movieTitle;
	
	@NotBlank(message="Movie cast is mandatory")
	@Size(min=3 ,max=200,message="Movietitle must between 3 and 200 chars")
	@Pattern(regexp="([a-zA-Z]+)|([a-zA-Z]+[,a-zA-Z]+)",message="Movie cast contains only alphabets and comma")
	private String movieCast;
	
	@NotBlank(message="zenre is required")
	@Pattern(regexp="drama|comedy|action", message="zenre must be drama comedy or action")
	private String Zenre;
	
	@NotBlank(message="release date is required")
	private String releaseDate;
	
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieCast() {
		return movieCast;
	}
	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}
	public String getZenre() {
		return Zenre;
	}
	public void setZenre(String zenre) {
		Zenre = zenre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	

}
