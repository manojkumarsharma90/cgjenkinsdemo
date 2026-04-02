package com.cg.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "abes_movie")
@DynamicUpdate

public class Movie {
	@Id
	@Column(name="movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;
	
	@Column(name="movie_title",length = 45,nullable = false,unique = true)
	private String movieTitle;
	
	@Column(name="cast_m",nullable = false,unique = true)
	private String cast;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
    
	@Column(name ="zenre" ,length = 45,nullable = false)
	private String zenre;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getZenre() {
		return zenre;
	}

	public void setzenre(String zenre) {
		this.zenre = zenre;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieTitle=" + movieTitle + ", cast=" + cast + ", releasedate="
				+ releaseDate + ", jener=" + zenre + "]";
	}
	
	
	
	
	

}
