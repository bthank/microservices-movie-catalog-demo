package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		
		// get me the resource at this url and unmarshal it into this class
		// Movie movie = restTemplate.getForObject("http://localhost:8092/movies/2", Movie.class);
		
		// get all rated movie IDs
		UserRating ratings = restTemplate.getForObject("http://localhost:8093/ratingsdata/users/" + userId, UserRating.class);
		
		return ratings.getUserRating()
			.stream()
			.map(rating -> {
				// make a restTemplate call for the movie we want; for each movie Id, call movie info service and get the details
				Movie movie = restTemplate.getForObject("http://localhost:8092/movies/" + rating.getMovieId(), Movie.class);
				
				// make a WebClient call instead for the movie we want;  this piece of code will give us an instance of the Movie class
				/*
				Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8092/movies/" + rating.getMovieId())
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
				*/
				return new CatalogItem(movie.getName(), "Test description", rating.getRating());
			})
			.collect(Collectors.toList());
		
		// For each movie ID, call movie info service and get details 
		
		// Put them all together
		

		
	}
	
	
	
	
}
