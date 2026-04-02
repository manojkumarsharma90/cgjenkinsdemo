package com.cg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
	
	public List<Movie> findByMovieTitle(String title);
		
	@Query("from Movie m where YEAR(releaseDate)=:rdate")
	public List<Movie> getMovieByGivenYear(@Param("rdate") int year);

}
